package com.shadoww.ApartmentSearch.util.apartment.search.parsers;

import com.shadoww.ApartmentSearch.util.apartment.instances.ApartmentInstance;
import com.shadoww.ApartmentSearch.util.apartment.search.parsers.additional.ApartmentConverter;
import com.shadoww.ApartmentSearch.util.apartment.search.parsers.additional.parsers.PageParsingStrategy;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;


@Setter
//@Getter
public class ApartmentParser {

    private PageParsingStrategy parsingStrategy;

    private String advertisementSelector;

    private String baseUrl;


    private ApartmentConverter apartmentConverter;

    public List<ApartmentInstance> parseApartmentsFromUrl(String url) {

        // отримуємо html контент з посилання

        System.out.println("Start parsing url - " + url);

        if (baseUrl == null) {
            URI uri = URI.create(url);
            baseUrl = uri.getScheme() + "://" + uri.getHost() + "/";
        }

        try {
            List<String> allhtml = parsingStrategy.parseHtmlFromPages(url);

            System.out.println("Html was successful got from pages");

            return allhtml
                    .stream()
                        .flatMap(html -> parseOnePage(html).stream())
                        .filter(Objects::nonNull)
                    .toList();
        } catch (IOException e) {
            System.out.println("There are problems with parsing apartments. Error message:" + e.getMessage());

            return List.of();
        }

    }

    public List<ApartmentInstance> parseOnePage(String html) {
        Document page = Jsoup.parse(html);

        if (baseUrl != null) {
            page.setBaseUri(baseUrl);
        }


//        try {
//            Files.write(Path.of("text.html"), page.select(advertisementSelector).html().getBytes());
//        }catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
        Elements elements = page.select(advertisementSelector);


//        System.out.println("URL:");
//
//        System.out.println(elements.size());

        return elements.stream()
                .map(ap -> {
                    var aps = apartmentConverter.parseApartmentFromAdvertisement(ap);

//                    System.out.println(aps);
                    return aps;
                })
                .toList();
    }
}

/**
 * Сторінковий парсер
 * 1. отримує силку
 * 2. проскановуємо всі сторінки з результатами і збираємо html контент з сторінок(залежить від типу парсера)
 * 3. запускаємо цикл який пробігається по html контенту з кожної спаршеної сторінки і заданим селектором отримуємо оголошення
 * 4. заданим методом обробки оголошення перетворюємо його в обєкт квартири і віддаємо
 * 5. збираємо з циклу список обєктів квартир і віддаємо його як результат роботи
 */