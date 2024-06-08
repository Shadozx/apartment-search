package com.shadoww.ApartmentSearch.util.apartment.instances;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class CityInstance {


    private String nameUa;
    private String nameEng;
    private String nameRu;

    private List<DistrictInstance> districtInstances;


    public CityInstance(String nameUa, String nameEng, String nameRu) {
        this.nameUa = nameUa;
        this.nameEng = nameEng;
        this.nameRu = nameRu;
    }

    public void addDistrictInstance(DistrictInstance districtInstance) {
        if (districtInstances == null) districtInstances = new ArrayList<>();

        districtInstances.add(districtInstance);
    }

    public boolean equalWithoutLang(String name) {
        return nameUa.equals(name) || nameEng.equals(name) || nameRu.equals(name);
    }

}
