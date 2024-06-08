package com.shadoww.ApartmentSearch.repositories;

import com.shadoww.ApartmentSearch.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CitiesRepository extends JpaRepository<City, Integer> {

//    Optional<City> findByNameUa(String nameUa);
    Optional<City> findByName(String nameUa);
//    Optional<City> findByNameEng(String nameEng);
//    Optional<City> findByNameRu(String nameRu);
}
