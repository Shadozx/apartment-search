package com.shadoww.ApartmentSearch.util.apartment.search.parsers.additional.parsers;

import com.shadoww.ApartmentSearch.util.apartment.search.parsers.additional.LinkProvider;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Setter
public class ServerGeneratedPageParser implements PageParsingStrategy {

    private LinkProvider provider;


    private PageLoader pageLoader;

    @Override
    public List<String> parseHtmlFromPages(String url) throws IOException {

        List<String> links = createLinks(url);

        // завантажуємо html сторінки з посилань

        if (links != null) {
            // отримуємо html код з сторінки
            List<String> allhtml = new ArrayList<>();


            for (var l : links) {
                allhtml.add(pageLoader.loadPage(l));
            }
            return allhtml;
        }
        return null;
    }

    private List<String> createLinks(String url) throws IOException {

        String html = pageLoader.loadPage(url);
        if (html != null) {
            Document onePage = Jsoup.parse(html);

            // отримуємо список посилань з для парсингу
            return provider.getPageLinks(url, onePage);
        }
        return null;
    }
}
