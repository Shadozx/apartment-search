package com.shadoww.ApartmentSearch.services;

import com.shadoww.ApartmentSearch.models.Apartment;
import com.shadoww.ApartmentSearch.models.District;
import com.shadoww.ApartmentSearch.repositories.ApartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ApartmentsService {

    private ApartmentsRepository apartmentsRepository;

    @Autowired
    public ApartmentsService(ApartmentsRepository apartmentsRepository) {
        this.apartmentsRepository = apartmentsRepository;
    }


    public List<Apartment> findAll() {
        return apartmentsRepository.findAll();
    }

    public List<Apartment> findByDistrict(District district) {
        return apartmentsRepository.findByDistrict(district);
    }

    @Transactional
    public void save(Apartment apartment) {
        apartmentsRepository.save(apartment);
    }

    @Transactional
    public void deleteById(int id) {
        apartmentsRepository.deleteById(id);
    }


}
