package com.shadoww.ApartmentSearch.repositories;

import com.shadoww.ApartmentSearch.models.Apartment;
import com.shadoww.ApartmentSearch.models.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentsRepository extends JpaRepository<Apartment, Integer> {


    List<Apartment> findByDistrict(District district);
}
