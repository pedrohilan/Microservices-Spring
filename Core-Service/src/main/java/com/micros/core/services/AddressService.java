package com.micros.core.services;

import com.micros.core.repositories.AddressRepository;
import com.micros.core.models.AddressModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {
    final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<AddressModel> findAll() {
        return addressRepository.findByIsDeletedFalse();
    }

    @Transactional
    public Object save(AddressModel addressModel){
        return addressRepository.save(addressModel);
    }

    public Optional<AddressModel> findById(UUID id) {
        return addressRepository.findById(id);
    }

    @Transactional
    public void delete(UUID id) {
        addressRepository.updateIsDeletedById(id);
    }
}
