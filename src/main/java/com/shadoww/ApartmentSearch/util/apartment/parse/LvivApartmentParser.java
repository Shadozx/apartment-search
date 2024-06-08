package com.shadoww.ApartmentSearch.util.apartment.parse;

import com.shadoww.ApartmentSearch.models.Apartment;
import com.shadoww.ApartmentSearch.models.District;
import com.shadoww.ApartmentSearch.util.apartment.factory.ApartmentParsers;
import com.shadoww.ApartmentSearch.util.apartment.instances.ApartmentInstance;
import com.shadoww.ApartmentSearch.util.apartment.instances.CityInstance;
import com.shadoww.ApartmentSearch.util.apartment.instances.DistrictInstance;
import com.shadoww.ApartmentSearch.util.apartment.search.parsers.ApartmentParser;
import com.shadoww.ApartmentSearch.models.City;
import com.shadoww.ApartmentSearch.services.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class LvivApartmentParser {

    private CitiesService citiesService;

    @Autowired
    public LvivApartmentParser(CitiesService citiesService) {


        this.citiesService = citiesService;
    }


    public LvivApartmentParser(){}

    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.MINUTES)
    public void parseRealEstateLviv() {

//        CityInstance instance = createLviv();

        ApartmentParser parser = ApartmentParsers.getRealEstateParser();

        var parsedApartments = parse(parser, "https://www.real-estate.lviv.ua/оренда-квартир/місто-львів/кімнат-2/ціна-8000,10000/c-uah");

        if (parsedApartments.isEmpty()) {
            System.out.println("List of apartments is empty");
            return;
        }
//
//        parsedApartments.stream().map(ApartmentInstance::getApartment).forEach(System.out::println);
        System.out.println(parsedApartments.size());


        showCountApartmentsByDistrict(parsedApartments);

//        List<Apartment> filteredApartments = parsedApartments.stream().filter(a-> a != null && !a.getAddress().equals("") && !a.getUrl().equals("")).toList();


        var apartmentsByDistrict = groupApartmentsByDistrict(parsedApartments);

        Optional<City> foundCity = Optional.empty();//citiesService.findOneByName("Львів");

//        apartmentsByDistrict
//                .forEach((key, value) -> instance.getDistrictInstances()
//                        .stream()
//                        .filter(d -> d.equalWithoutLang(key))
//                        .forEach(d -> d.addApartments(value)));
//



        City city = foundCity.orElseGet(this::createLviv);

        apartmentsByDistrict
                .forEach((key, value) -> city.getDistricts()
                        .stream()
                        .filter(d -> d.getName().equals(key))
                        .forEach(d -> d.addApartments(value)));


//        city.getDistricts()
//                .forEach(d -> instance.getDistrictInstances()
//                        .stream()
//                        .filter(di -> di.equalWithoutLang(d.getName()))
//                        .forEach(di -> d.addApartments(di.getApartments().stream().map(ApartmentInstance::getApartment).toList())));

//        apartmentsByDistrict
//                .entrySet()
//                .stream()
//                .forEach(e->{
//                    city.getDistricts().stream().filter(d->d.eq)
//                });

//        for (var a : parsedApartments) {
//
//            Optional<DistrictInstance>foundD = instance.getDistrictInstances().stream().filter(d->d.equalWithoutLang(a.getDistrictName())).findFirst();
//
//            if (foundD.isPresent()) {
//                var foundDistrict = city.getDistricts().stream().filter(d->d.getName().equals(foundD.get().getNameUa())).findFirst();
//
//                foundDistrict.ifPresent(district -> district.addApartment(a.getApartment()));
//            }
//        }

        showCity(city);

        System.out.println("==============================================\n");

        citiesService.saveCity(city);
    }

    @Scheduled(fixedRate = 25, timeUnit = TimeUnit.MINUTES)
    public void parseOLXLviv() {
        ApartmentParser parser = ApartmentParsers.getOlxParser();

        List<ApartmentInstance> parsedApartments = parser.parseApartmentsFromUrl("https://www.olx.ua/uk/nedvizhimost/kvartiry/dolgosrochnaya-arenda-kvartir/lvov/?currency=UAH&search%5Bfilter_float_price:from%5D=6000&search%5Bfilter_float_price:to%5D=10000");


        List<ApartmentInstance> filteredApartments = new HashSet<>(parsedApartments).stream().toList();

        System.out.println(filteredApartments.size());

        showCountApartmentsByDistrict(parsedApartments);

        var apartmentsByDistrict = groupApartmentsByDistrict(filteredApartments);

        Optional<City> foundCity = Optional.empty();//citiesService.findOneByName("Львів");

        City city = foundCity.orElseGet(this::createLviv);

        apartmentsByDistrict
                .forEach((key, value) -> city.getDistricts()
                        .stream()
                        .filter(d -> d.getName().equals(key))
                        .forEach(d -> d.addApartments(value)));


//        city.getDistricts()
//                .forEach(d -> instance.getDistrictInstances()
//                        .stream()
//                        .filter(di -> di.equalWithoutLang(d.getName()))
//                        .forEach(di -> d.addApartments(di.getApartments().stream().map(ApartmentInstance::getApartment).toList())));

//        apartmentsByDistrict
//                .entrySet()
//                .stream()
//                .forEach(e->{
//                    city.getDistricts().stream().filter(d->d.eq)
//                });

//        for (var a : parsedApartments) {
//
//            Optional<DistrictInstance>foundD = instance.getDistrictInstances().stream().filter(d->d.equalWithoutLang(a.getDistrictName())).findFirst();
//
//            if (foundD.isPresent()) {
//                var foundDistrict = city.getDistricts().stream().filter(d->d.getName().equals(foundD.get().getNameUa())).findFirst();
//
//                foundDistrict.ifPresent(district -> district.addApartment(a.getApartment()));
//            }
//        }

        showCity(city);

        System.out.println("==============================================\n");

        showSameApartments(filteredApartments);

        showCountApartmentsByDistrict(filteredApartments);

        System.out.println("\n=============================================\n");


        System.out.println(filteredApartments.size());

//        citiesService.saveCity(city);
    }

    /**
     *
     */


    private City createLviv() {
        City newCity = new City();

        newCity.setName("Львів");
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

    public List<ApartmentInstance> parse(ApartmentParser parser, String url) {

        return parser.parseApartmentsFromUrl(url);
    }

    private Map<String, List<Apartment>> groupApartmentsByDistrict(List<ApartmentInstance> apartmentInstances) {
        return apartmentInstances
                .stream()
                .collect(Collectors.groupingBy(ApartmentInstance::getDistrictName, Collectors.mapping(ApartmentInstance::getApartment, Collectors.toList())));
    }

    private void showSameApartments(List<ApartmentInstance> apartmentInstances) {

        System.out.println("Підрахунок ідентичний об'явлень");
        apartmentInstances
                .stream()
                .map(ApartmentInstance::getApartment)
                .collect(Collectors.groupingBy(Apartment::getTitle, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .forEach(System.out::println);
    }

    private void showCountApartmentsByDistrict(List<ApartmentInstance> apartmentInstances) {


        System.out.println("\nКількість квартир по районах");
        apartmentInstances
                .stream()
//                .map(ApartmentInstance::getApartment)
                .collect(Collectors.groupingBy(ApartmentInstance::getDistrictName, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .forEach(System.out::println);

        System.out.println("==========================\n");

    }

    private void showCity(City city) {
        System.out.println("City name -" + city.getName());


        city.getDistricts()
                .stream()
                .sorted(Comparator.comparingInt((District d) -> d.getApartments().size()).reversed().thenComparing(District::getName))
                .forEach(d -> {

                    System.out.println("\nРайон - " + d.getName());

                    d.getApartments().forEach(System.out::println);

                    System.out.println("Кількість квартир - " + d.getApartments().size());
                    System.out.println("-----------------------------------------");
                });

        System.out.println("==============================================\n");

    }
}
