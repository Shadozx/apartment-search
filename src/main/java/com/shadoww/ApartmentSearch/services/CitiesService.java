package com.shadoww.ApartmentSearch.services;


import com.shadoww.ApartmentSearch.models.City;
import com.shadoww.ApartmentSearch.repositories.CitiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CitiesService {

    private CitiesRepository citiesRepository;

    private DistrictsService districtsService;

    private ApartmentsService apartmentsService;


    public CitiesService(CitiesRepository citiesRepository, DistrictsService districtsService, ApartmentsService apartmentsService) {
        this.citiesRepository = citiesRepository;
        this.districtsService = districtsService;
        this.apartmentsService = apartmentsService;
    }

    public List<City> findAll() {
        return citiesRepository.findAll();
    }

    public Optional<City> findOneById(int id) {
        return citiesRepository.findById(id);
    }

    public Optional<City> findOneByName(String name) {
        return citiesRepository.findByName(name);
    }
//    public Optional<City> findOneByNameUa(String nameUa) {
//        return citiesRepository.findByNameUa(nameUa);
//    }
//
//    public Optional<City> findOneByNameEng(String nameEng) {
//        return citiesRepository.findByNameUa(nameEng);
//    }
//
//    public Optional<City> findOneByNameRu(String nameRu) {
//        return citiesRepository.findByNameRu(nameRu);
//    }

    @Transactional
    public void save(City city) {
        citiesRepository.save(city);
    }

    @Transactional
    public void saveCity(City city) {

        for (var dist : city.getDistricts()) {



            for (var a : dist.getApartments()) {

                apartmentsService.save(a);
            }

            districtsService.save(dist);
        }
        save(city);

    }

    @Transactional
    public void deleteById(int id) {
        citiesRepository.deleteById(id);
    }
}
