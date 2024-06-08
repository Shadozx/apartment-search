package com.shadoww.ApartmentSearch.util.apartment.search.builders;

import com.shadoww.ApartmentSearch.util.apartment.search.parsers.additional.parsers.PageParsingStrategy;
import com.shadoww.ApartmentSearch.util.apartment.search.parsers.ApartmentParser;
import com.shadoww.ApartmentSearch.util.apartment.search.parsers.additional.ApartmentConverter;

public class ApartmentParserBuilder {

    private PageParsingStrategy parsingStrategy;

    private ApartmentConverter converter;

    private String advertisementSelector;

    private String baseUrl;

    public ApartmentParserBuilder strategyParsing(PageParsingStrategy parsingStrategy) {
        this.parsingStrategy = parsingStrategy;

        return this;
    }

    public ApartmentParserBuilder advertisementSelector(String advertisementSelector) {
        this.advertisementSelector = advertisementSelector;

        return this;
    }

    public ApartmentParserBuilder convectAdvertisementToApartment(ApartmentConverter converter) {

        this.converter = converter;

        return this;
    }

    public ApartmentParserBuilder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;

        return this;
    }

    public ApartmentParser build() throws Exception {

        ApartmentParser parser = new ApartmentParser();

        if (converter == null || parsingStrategy == null || advertisementSelector == null) throw new Exception("Element cannot be null!");
        parser.setApartmentConverter(converter);
        parser.setParsingStrategy(parsingStrategy);
        parser.setAdvertisementSelector(advertisementSelector);

        if (baseUrl != null) parser.setBaseUrl(baseUrl);

        return parser;
    }
}
