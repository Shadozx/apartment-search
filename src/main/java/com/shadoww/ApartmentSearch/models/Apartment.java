package com.shadoww.ApartmentSearch.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.Objects;


/**
 * Клас, що представляє об'єкт "Квартира" у певному районі.
 * Кожна квартира має унікальний номер або назву.
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;


    private String address;

    private String url;

    private double price;

    private LocalDateTime added;

    @ManyToOne()
    @JoinColumn(name = "district")
    private District district;

    public Apartment(String title, String address, String url, double price, LocalDateTime added) {
        this.title = title;
        this.address = address;
        this.url = url;
        this.price = price;
        this.added = added;
    }

//    якщо квартира має бути додана зараз
    public Apartment(String title, String address, String url, double price) {
        this(title, address, url, price, LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return Objects.equals(title, apartment.title) && Objects.equals(url, apartment.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", url='" + url + '\'' +
                ", price=" + price +
                '}';
    }
}
