package com.shadoww.ApartmentSearch.util.apartment.factory;

import com.shadoww.ApartmentSearch.util.apartment.search.builders.ApartmentParserBuilder;
import com.shadoww.ApartmentSearch.util.apartment.instances.ApartmentInstance;
import com.shadoww.ApartmentSearch.util.apartment.search.parsers.ApartmentParser;
import com.shadoww.ApartmentSearch.util.apartment.search.parsers.additional.parsers.PageLoader;
import com.shadoww.ApartmentSearch.util.apartment.search.parsers.additional.parsers.ServerGeneratedPageParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApartmentParsers {

    public static ApartmentParser getRealEstateParser() {

        PageLoader pageLoader = url -> getDocument(url).html();

        ServerGeneratedPageParser serverGeneratedPageParser = new ServerGeneratedPageParser();
        serverGeneratedPageParser.setProvider(((url, main) -> {

            String strNumber = main.select("#search_result > ul.list__pagination-custom.pagination.justify-content-center > li.last.page-item > a").last().text();

            List<String> allUrls = new ArrayList<>(List.of(url));
            int amount = Integer.parseInt(strNumber);

            for (int n = 2; n <= amount; n++) allUrls.add(url + "/p-" + n);


            return allUrls;
        }));

        serverGeneratedPageParser.setPageLoader(pageLoader);
        try {
            return new ApartmentParserBuilder()
                    .advertisementSelector("#search_result > div.row.row-dense.col-dense-right.mt-2 > div")
                    .strategyParsing(serverGeneratedPageParser)
                    .baseUrl("https://www.real-estate.lviv.ua/")
                    .convectAdvertisementToApartment(el -> {

                        Elements title = el.select("p.object-listing-address");
                        Elements url = el.select("div.wrapper-obj-listing-item.row-dense-25px > a");

                        if (!title.isEmpty() && !url.isEmpty()) {
                            ApartmentInstance instance = new ApartmentInstance();

                            String reg = "^(?<street>.*?), ((?<building>.*?), )?(?<city>.*?), (?<district>.*?)$";
                            String district = null;

                            String street = null;

                            var m = Pattern.compile(reg).matcher(title.text());

                            if (m.matches()) {

                                String building = m.group("building");
                                district = m.group("district").replace("район","").trim();
                                street = m.group("street") + (building == null ? "" : ", " + building);
                            }
                            instance.district(district);
                            instance.createApartment(title.first().text(), street, url.first().absUrl("href"));

//                            return new Apartment(title.first().text(), title.first().text().split(" вул., ")[0], url.first().absUrl("href"), 0);
                            return instance;
                        }
//                        else System.out.println("Advertisement is empty");
                        return null;
                    })
                    .build();
        } catch (Exception e) {
            System.out.println("Exception message: " + e.getMessage());
        }
        return null;
    }


    public static ApartmentParser getOlxParser() {
        PageLoader pageLoader = url -> {

            OkHttpClient client =  new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS) // Таймаут на з'єднання
                    .readTimeout(10, TimeUnit.SECONDS)    // Таймаут на читання відповіді
                    .writeTimeout(10, TimeUnit.SECONDS)   // Таймаут на запис відправленого запиту
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    return response.body().string();
                } else {
                    System.out.println("Request was not successful. Response code: " + response.code());
                }
            }

            return null;
        };

        ServerGeneratedPageParser serverGeneratedPageParser = new ServerGeneratedPageParser();
        serverGeneratedPageParser.setProvider(((url, main) -> {

            String strNumber = Objects.requireNonNull(main.select("#mainContent > div.css-1nvt13t>form section.css-j8u5qq li >a").last()).text();

            List<String> allUrls = new ArrayList<>(List.of(url));
            int amount = Integer.parseInt(strNumber);

            for (int n = 2; n <= amount; n++) allUrls.add(url + "&page=" + n);
            return allUrls;
        }));

        serverGeneratedPageParser.setPageLoader(pageLoader);
        try {
            return new ApartmentParserBuilder()
                    .advertisementSelector(".listing-grid-container > .css-oukcj3 > .css-1sw7q4x")
                    .strategyParsing(serverGeneratedPageParser)
                    .baseUrl("https://olx.ua/")
                    .convectAdvertisementToApartment(el -> {
                        removeComments(el);

                        Elements title = el.select("h6.css-16v5mdi");

                        Elements urlApart = el.select("a.css-rc5s2u");

                        Elements district = el.select("p.css-1a4brun.er34gjf0");
//                    System.out.println(title);
                        if (!title.isEmpty() && !urlApart.isEmpty() && !district.isEmpty()) {

                            ApartmentInstance instance = new ApartmentInstance();

                            String reg = "(?<city>.*?), (?<district>.*?) - (?<date>.*?)$";

//                            System.out.println(district.first().text());
                            Matcher m = Pattern.compile(reg).matcher(district.first().text());

                            if(m.matches()) {
                                instance.district(m.group("district"));
                                instance.createApartment(title.text(), el.select(".css-veheph.er34gjf0").text(), urlApart.first().absUrl("href"));
//                            return new Apartment(title.text(), el.select(".css-veheph.er34gjf0").text(), urlApart.first().absUrl("href"), 0);
                                return instance;
                            }
                        }
                        return null;
                    })
                    .build();

        } catch (Exception e) {
            System.out.println("Exception message: " + e.getMessage());
        }

        return null;
    }

    public static Document getDocument(String url) throws IOException {
        return Jsoup
                .connect(url)
                .header("Accept-Encoding", "gzip, deflate")
//                    .userAgent("Mozilla")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
                .maxBodySize(0)
                .timeout(600000)
                .get();
    }

    private static void removeComments(Node node) {
        for (int i = 0; i < node.childNodes().size(); ) {
            Node child = node.childNode(i);

            if (child instanceof Comment) {
                // Видалити коментар
                child.remove();
            } else {
                // Рекурсивно викликати для дочірніх елементів
                removeComments(child);
                i++;
            }
        }
    }

}
