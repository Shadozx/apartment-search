package com.shadoww.ApartmentSearch.util.apartment.search.parsers.additional;

import com.shadoww.ApartmentSearch.util.apartment.instances.ApartmentInstance;
import org.jsoup.nodes.Element;

@FunctionalInterface
public interface ApartmentConverter {
    ApartmentInstance parseApartmentFromAdvertisement(Element element);
}
