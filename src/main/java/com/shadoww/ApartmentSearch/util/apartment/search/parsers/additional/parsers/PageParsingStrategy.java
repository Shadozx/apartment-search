package com.shadoww.ApartmentSearch.util.apartment.search.parsers.additional.parsers;


import java.io.IOException;
import java.util.List;

public interface PageParsingStrategy {

    List<String> parseHtmlFromPages(String url) throws IOException;
}
