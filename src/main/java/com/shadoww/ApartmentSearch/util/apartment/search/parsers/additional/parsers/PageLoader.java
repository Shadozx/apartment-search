package com.shadoww.ApartmentSearch.util.apartment.search.parsers.additional.parsers;


import java.io.IOException;

@FunctionalInterface
public interface PageLoader {

    String loadPage(String url) throws IOException;
}
