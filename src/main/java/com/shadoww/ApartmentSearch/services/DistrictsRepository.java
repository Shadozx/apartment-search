package com.shadoww.ApartmentSearch.services;

import com.shadoww.ApartmentSearch.models.City;
import com.shadoww.ApartmentSearch.models.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistrictsRepository extends JpaRepository<District, Integer> {


    Optional<District> findByName(String name);
//    Optional<District> findByNameUa(String nameUa);
//    Optional<District> findByNameEng(String nameEng);
//    Optional<District> findByNameRu(String nameRu);


//    Optional<District> findByNameUaContainingOrNameRuContainingOrNameEngContaining(String nameUa, String nameRu, String nameEng);
}
