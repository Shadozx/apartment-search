package com.shadoww.ApartmentSearch.services;

import com.shadoww.ApartmentSearch.models.Apartment;
import com.shadoww.ApartmentSearch.models.District;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DistrictsService {


    private DistrictsRepository districtsRepository;

    public DistrictsService(DistrictsRepository districtsRepository) {
        this.districtsRepository = districtsRepository;
    }


    public Optional<District> findOne(int id) {
        return districtsRepository.findById(id);
    }

    public Optional<District> findOneByName(String name) {
        return districtsRepository.findByName(name);
    }

    @Transactional
    public void save(District district) {
        districtsRepository.save(district);
    }

    @Transactional
    public void deleteById(int id) {
        districtsRepository.deleteById(id);
    }

}
