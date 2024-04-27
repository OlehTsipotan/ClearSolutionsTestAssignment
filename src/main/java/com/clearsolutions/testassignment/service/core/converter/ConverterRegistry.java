package com.clearsolutions.testassignment.service.core.converter;

import com.clearsolutions.testassignment.exception.ApplicationException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter.ConvertiblePair;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ConverterRegistry extends GenericConversionService {

    private final Set<CreateConverter<?, ?>> createConverters;
    private final Set<UpdateConverter<?, ?>> updateConverters;

    @PostConstruct
    public void initialize() {
        Map<ConvertiblePair, CreateConverter<?, ?>> createConvertersPairs = buildCreateConverterPairs();
        Map<ConvertiblePair, UpdateConverter<?, ?>> updateConvertersPairs = buildUpdateConverterPairs();

        Set<CombinedConverterAdapter> converters =
                buildCombinedConverters(createConvertersPairs, updateConvertersPairs);
        converters.forEach(this::addConverter);
    }

    public <T> CombinedConverterAdapter find(Object source, Class<T> targetClass) {
        TypeDescriptor sourceType = Objects.requireNonNull(TypeDescriptor.forObject(source));
        TypeDescriptor targetType = TypeDescriptor.valueOf(targetClass);

        CombinedConverterAdapter converter = (CombinedConverterAdapter) getConverter(sourceType, targetType);
        if (converter == null) {
            throw new ApplicationException(
                    "Converter from [" + source.getClass() + "] to [" + targetClass + "] not found");
        }
        return converter;
    }

    private Set<CombinedConverterAdapter> buildCombinedConverters(
            Map<ConvertiblePair, CreateConverter<?, ?>> createConvertersPairs,
            Map<ConvertiblePair, UpdateConverter<?, ?>> updateConvertersPairs) {

        Set<CombinedConverterAdapter> result = new HashSet<>();

        Set<ConvertiblePair> convertiblePairs = new HashSet<>();
        convertiblePairs.addAll(createConvertersPairs.keySet());
        convertiblePairs.addAll(updateConvertersPairs.keySet());

        for (ConvertiblePair convertiblePair : convertiblePairs) {
            CreateConverter<?, ?> createConverter = createConvertersPairs.get(convertiblePair);
            UpdateConverter<?, ?> updateConverter = updateConvertersPairs.get(convertiblePair);

            CombinedConverterAdapter converter = obtainCombinedConverter(createConverter, updateConverter);
            result.add(converter);
        }
        return result;
    }

    private Map<ConvertiblePair, CreateConverter<?, ?>> buildCreateConverterPairs() {
        Map<ConvertiblePair, CreateConverter<?, ?>> result = new HashMap<>();
        for (CreateConverter<?, ?> createConverter : createConverters) {
            ConvertiblePair convertiblePair = buildConvertiblePair(createConverter.getClass(), CreateConverter.class);

            if (result.containsKey(convertiblePair)) {
                throw new IllegalStateException("Found duplicate converter for: " + convertiblePair);
            }
            result.put(convertiblePair, createConverter);
        }
        return result;
    }

    private Map<ConvertiblePair, UpdateConverter<?, ?>> buildUpdateConverterPairs() {
        Map<ConvertiblePair, UpdateConverter<?, ?>> result = new HashMap<>();
        for (UpdateConverter<?, ?> updateConverter : updateConverters) {
            ConvertiblePair convertiblePair = buildConvertiblePair(updateConverter.getClass(), UpdateConverter.class);

            if (result.containsKey(convertiblePair)) {
                throw new IllegalStateException("Found duplicate converter for: " + convertiblePair);
            }
            result.put(convertiblePair, updateConverter);
        }
        return result;
    }

    private CombinedConverterAdapter obtainCombinedConverter(CreateConverter<?, ?> createConverter,
                                                             UpdateConverter<?, ?> updateConverter) {

        Set<CombinedConverterAdapter> converters = new LinkedHashSet<>();
        converters.add(buildCombinedConverterAdapter(createConverter));
        converters.add(buildCombinedConverterAdapter(updateConverter));
        converters.add(buildCombinedConverterAdapter(createConverter, updateConverter));

        CombinedConverterAdapter currentConverter = null;

        for (CombinedConverterAdapter nextConverter : converters) {
            if (nextConverter.getConverter() == null || nextConverter.getConvertibleTypes().size() != 1) {
                continue;
            }

            if (currentConverter == null || currentConverter.getConverter().equals(nextConverter.getConverter())) {
                currentConverter = nextConverter;
            } else {
                currentConverter = mergeCombinedConverters(currentConverter, nextConverter);
            }
        }
        return currentConverter;
    }

    @SuppressWarnings("unchecked")
    private CombinedConverterAdapter buildCombinedConverterAdapter(CreateConverter<?, ?> createConverter,
                                                                   UpdateConverter<?, ?> updateConverter) {

        CombinedConverter.CombinedConverterBuilder combinedConverter = CombinedConverter.builder();
        Set<ConvertiblePair> convertiblePairs = new LinkedHashSet<>();

        if (createConverter != null) {
            combinedConverter.createConverter((CreateConverter<Object, Object>) createConverter);
            convertiblePairs.add(buildConvertiblePair(createConverter.getClass(), CreateConverter.class));
        }
        if (updateConverter != null) {
            combinedConverter.updateConverter((UpdateConverter<Object, Object>) updateConverter);
            convertiblePairs.add(buildConvertiblePair(updateConverter.getClass(), UpdateConverter.class));
        }
        return new CombinedConverterAdapter(combinedConverter.build(), convertiblePairs);
    }

    @SuppressWarnings("unchecked")
    private CombinedConverterAdapter buildCombinedConverterAdapter(Object converter) {
        if (converter == null) {
            return new CombinedConverterAdapter(null, Set.of());
        }

        CombinedConverter.CombinedConverterBuilder combinedConverter = CombinedConverter.builder();
        Set<ConvertiblePair> convertiblePairs = new LinkedHashSet<>();

        if (converter instanceof CreateConverter<?, ?> createConverter) {
            combinedConverter.createConverter((CreateConverter<Object, Object>) createConverter);
            convertiblePairs.add(buildConvertiblePair(converter.getClass(), CreateConverter.class));
        }
        if (converter instanceof UpdateConverter<?, ?> updateConverter) {
            combinedConverter.updateConverter((UpdateConverter<Object, Object>) updateConverter);
            convertiblePairs.add(buildConvertiblePair(converter.getClass(), UpdateConverter.class));
        }
        return new CombinedConverterAdapter(combinedConverter.build(), convertiblePairs);
    }

    private ConvertiblePair buildConvertiblePair(Class<?> converterClass, Class<?> interfaceClass) {
        ResolvableType[] types = determineGenericTypes(converterClass, interfaceClass);
        ResolvableType sourceType = types[0];
        ResolvableType targetType = types[1];
        return new ConvertiblePair(sourceType.toClass(), targetType.toClass());
    }

    private ResolvableType[] determineGenericTypes(Class<?> instanceClass, Class<?> genericsHolderClass) {
        ResolvableType resolvableType = ResolvableType.forClass(instanceClass).as(genericsHolderClass);
        ResolvableType[] generics = resolvableType.getGenerics();

        if (generics.length < 2) {
            throw new IllegalStateException("Failed to determine generics for " + instanceClass);
        }

        Class<?> sourceType = generics[0].resolve();
        Class<?> targetType = generics[1].resolve();

        if (sourceType == null || targetType == null) {
            throw new IllegalStateException("Failed to determine generics for " + instanceClass);
        }
        return generics;
    }

    private CombinedConverterAdapter mergeCombinedConverters(CombinedConverterAdapter first,
                                                             CombinedConverterAdapter second) {

        CreateConverter<Object, Object> createConverter = null;
        UpdateConverter<Object, Object> updateConverter = null;

        if (first.getConverter().getCreateConverter() != null) {
            createConverter = first.getConverter().getCreateConverter();
        }
        if (second.getConverter().getCreateConverter() != null) {
            updateConverter = first.getConverter().getUpdateConverter();
        }

        if (second.getConverter().getCreateConverter() != null) {
            createConverter = second.getConverter().getCreateConverter();
        }
        if (second.getConverter().getUpdateConverter() != null) {
            updateConverter = second.getConverter().getUpdateConverter();
        }

        CombinedConverter converter = new CombinedConverter(createConverter, updateConverter);

        Set<ConvertiblePair> convertiblePairs = new LinkedHashSet<>();
        convertiblePairs.addAll(first.getConvertibleTypes());
        convertiblePairs.addAll(second.getConvertibleTypes());

        return new CombinedConverterAdapter(converter, convertiblePairs);
    }

}
