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
 * Клас, що представляє об'єкт "Район" у місті.
 * Кожен район має назву та містить кілька квартир.
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
public class District {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    /*private String nameRu;

    private String nameEng;
*/
    @ManyToOne()
    @JoinColumn(name = "city")
    private City city;


    @OneToMany(mappedBy = "district", orphanRemoval = true, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE})
    private List<Apartment> apartments;

    @Transient
    private String url;

    /*public District(String nameUa, String nameRu, String nameEng) {
        this.nameUa = nameUa;
        this.nameRu = nameRu;
        this.nameEng = nameEng;
    }


    public boolean equalWithoutLang(String name) {
        return nameUa.equals(name) || nameEng.equals(name) || nameRu.equals(name);
    }*/

    public District(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        District district = (District) o;
        return Objects.equals(name, district.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    public void addApartment(Apartment apartment) {
        if (apartments == null) apartments = new ArrayList<>();

        apartments.add(apartment);
    }

    public void addApartments(List<Apartment> newApartments) {
        if (apartments == null) apartments = new ArrayList<>();

        for (var a : newApartments) a.setDistrict(this);

        apartments.addAll(newApartments);
    }


    public String getUrl() {
        return "/districts/" + getId();
    }
}
