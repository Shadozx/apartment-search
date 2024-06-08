package com.shadoww.ApartmentSearch.util.apartment.search.parsers.additional;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public interface LinkProvider {
    List<String> getPageLinks(String Url, Document main) throws IOException;

}
