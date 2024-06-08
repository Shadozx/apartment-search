package com.shadoww.ApartmentSearch.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Клас, що представляє об'єкт "Місто" з розподілом на райони та квартири.
 * Кожне місто містить кілька районів, а кожен район містить кілька квартир.
 */

@Entity
@NoArgsConstructor
@Setter
@Getter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name cannot be empty")
    private String name;



    @OneToMany(mappedBy = "city", orphanRemoval = true, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE})
    private List<District> districts;


    @Transient
    private String url;

    public void addDistrict(District district) {
        if (districts == null) districts = new ArrayList<>();
        district.setCity(this);

        districts.add(district);
    }

    /*public City(String nameUa, String nameRu, String nameEng) {
        this.nameUa = nameUa;
        this.nameRu = nameRu;
        this.nameEng = nameEng;
    }


    public boolean equalWithoutLang(String name) {
        return nameUa.equals(name) || nameEng.equals(name) || nameRu.equals(name);
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getUrl() {
        return "/cities/" + getId();
    }

}
