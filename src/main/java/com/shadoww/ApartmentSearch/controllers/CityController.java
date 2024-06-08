package com.shadoww.ApartmentSearch.controllers;

import com.shadoww.ApartmentSearch.models.Apartment;
import com.shadoww.ApartmentSearch.models.City;
import com.shadoww.ApartmentSearch.models.District;
import com.shadoww.ApartmentSearch.services.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cities")
public class CityController {

    private CitiesService citiesService;

    @Autowired
    public CityController(CitiesService citiesService) {
        this.citiesService = citiesService;

        List<City> cities = List.of(createLviv(), createLvivTest());
        cities.forEach(this::saveCity);
    }


    @GetMapping("/")
    public String showCities(Model model) {
        model.addAttribute("cities", citiesService.findAll());

        return "cities/all";
    }

    @GetMapping("/{id}")
    public String showCity(@PathVariable int id, Model model) {

        model.addAttribute("city", citiesService.findOneById(id).get());
        return "cities/city";
    }

    private void saveCity(City city) {
//        City city = createLviv();
        int i = 1;
        for (var d : city.getDistricts()) {
            d.addApartments(
                    List.of(
                            new Apartment("t" + i, "a" + i, "u" + i, 0),
                            new Apartment("t" + (i + 1), "a" + (i + 1), "u" + (i + 1), 0)
                    )
            );
            i++;
        }

        citiesService.saveCity(city);
    }

    private City createLviv() {
        City newCity = new City();

        newCity.setName("Львівi");
//        instance.setNameEng("Lviv");
//        instance.setNameRu("Львов");
        newCity.addDistrict(new District("Шевченківський"));
        newCity.addDistrict(new District("Галицький"));
        newCity.addDistrict(new District("Личаківський"));
        newCity.addDistrict(new District("Франківський"));
        newCity.addDistrict(new District("Залізничний"));
        newCity.addDistrict(new District("Сихівський"));

//        newCity.addDistrict(new District("Шевченківський"/*, "Шевченковский", "Shevchenkivskyi"*/));
//        instance.addDistrictInstance(new DistrictInstance("Галицький", "Галицкий", "Halychskyi"));
//        instance.addDistrictInstance(new DistrictInstance("Личаківський", "Лычаковский", "Lychakivskyi"));
//        instance.addDistrictInstance(new DistrictInstance("Франківський", "Франковский", "Frankivskyi"));
//        instance.addDistrictInstance(new DistrictInstance("Залізничний", "Железнодорожный", "Zaliznychnyi"));
//        instance.addDistrictInstance(new DistrictInstance("Сихівський", "Сиховский", "Sykhivskyi"));

        return newCity;
    }

    private City createLvivTest() {
        City newCity = new City();

        newCity.setName("ЛьвівTest");
//        instance.setNameEng("Lviv");
//        instance.setNameRu("Львов");
        newCity.addDistrict(new District("ШевченківськийTest"));
        newCity.addDistrict(new District("ГалицькийTest"));
        newCity.addDistrict(new District("ЛичаківськийTest"));
        newCity.addDistrict(new District("ФранківськийTest"));
        newCity.addDistrict(new District("ЗалізничнийTest"));
        newCity.addDistrict(new District("СихівськийTest"));

//        newCity.addDistrict(new District("Шевченківський"/*, "Шевченковский", "Shevchenkivskyi"*/));
//        instance.addDistrictInstance(new DistrictInstance("Галицький", "Галицкий", "Halychskyi"));
//        instance.addDistrictInstance(new DistrictInstance("Личаківський", "Лычаковский", "Lychakivskyi"));
//        instance.addDistrictInstance(new DistrictInstance("Франківський", "Франковский", "Frankivskyi"));
//        instance.addDistrictInstance(new DistrictInstance("Залізничний", "Железнодорожный", "Zaliznychnyi"));
//        instance.addDistrictInstance(new DistrictInstance("Сихівський", "Сиховский", "Sykhivskyi"));

        return newCity;
    }
}
