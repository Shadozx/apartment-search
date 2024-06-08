package com.shadoww.ApartmentSearch.util.apartment.instances;

import com.shadoww.ApartmentSearch.models.Apartment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;


@NoArgsConstructor
@Setter
@Getter
public class ApartmentInstance {

    private String districtName;

    private Apartment apartment;


    public void district(String name) {
        districtName = name;
    }


    public void createApartment(String title, String address, String url) {
        apartment = new Apartment(title, address, url, 0);
    }

    @Override
    public String toString() {
        return "Apartment instance - [dist: " + districtName + ", apartment: " + apartment + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApartmentInstance instance = (ApartmentInstance) o;
        return Objects.equals(districtName, instance.districtName) && Objects.equals(apartment, instance.apartment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(districtName, apartment);
    }
}
