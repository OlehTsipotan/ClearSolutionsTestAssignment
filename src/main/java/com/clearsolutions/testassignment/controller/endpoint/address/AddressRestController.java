package com.clearsolutions.testassignment.controller.endpoint.address;

import com.clearsolutions.testassignment.model.dto.AddressDto;
import com.clearsolutions.testassignment.model.dto.response.SearchResponseDto;
import com.clearsolutions.testassignment.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressRestController {

    private final AddressService addressService;

    @GetMapping
    public SearchResponseDto<AddressDto> findAll() {
        return addressService.findAll();
    }

    @PostMapping
    public void create(@RequestBody AddressDto addressDto) {
        addressService.create(addressDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        addressService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable UUID id, @RequestBody AddressDto addressDto) {
        addressService.update(id, addressDto);
    }

}
