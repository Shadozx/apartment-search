package com.shadoww.ApartmentSearch.util.apartment.instances;


import com.shadoww.ApartmentSearch.models.Apartment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class DistrictInstance {


    private String nameUa;
    private String nameEng;
    private String nameRu;


    private List<ApartmentInstance> apartments;

    public DistrictInstance(String nameUa, String nameRu, String nameEng) {
        this.nameUa = nameUa;
        this.nameEng = nameEng;
        this.nameRu = nameRu;
    }

    public boolean equalWithoutLang(String name) {
        return nameUa.equals(name) || nameEng.equals(name) || nameRu.equals(name);
    }


    public void addApartment(ApartmentInstance apartment) {
        if (apartments == null) apartments = new ArrayList<>();

        apartments.add(apartment);
    }

    public void addApartments(List<ApartmentInstance> newApartments) {
        if (apartments == null) apartments = new ArrayList<>();

        apartments.addAll(newApartments);
    }
}
