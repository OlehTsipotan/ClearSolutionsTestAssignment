package com.clearsolutions.testassignment.repository.jpa.specification.user;

import com.clearsolutions.testassignment.model.entity.UserEntity;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class UserSpecification {

    private UserSpecification() {
    }

    public static Specification<UserEntity> byDateOfBirthRange(LocalDate dateOfBirthFrom, LocalDate dataOfBirthTo) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (dateOfBirthFrom != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.greaterThanOrEqualTo(root.get(UserEntity.Fields.dateOfBirth), dateOfBirthFrom));
            }
            if (dataOfBirthTo != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.lessThanOrEqualTo(root.get(UserEntity.Fields.dateOfBirth), dataOfBirthTo));
            }
            root.fetch(UserEntity.Fields.address, JoinType.LEFT);
            return predicate;
        };

    }
}
