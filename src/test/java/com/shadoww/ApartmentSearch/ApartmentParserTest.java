package com.shadoww.ApartmentSearch;

import com.shadoww.ApartmentSearch.models.Apartment;
import com.shadoww.ApartmentSearch.util.apartment.instances.ApartmentInstance;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


class P {
    protected String s = "P";

    public String method() {
        return s;
    }
}

class C extends P {
    String s = "C";

    @Override
    public String method() {
        s = super.s;
        return s;
    }

}

public class ApartmentParserTest {

    @Test
    public void testOlx() throws IOException, URISyntaxException {
//        String url = "https://www.real-estate.lviv.ua/оренда-квартир/місто-львів/ціна-8000,10000/c-uah";
//
//        String city = ‘Lviv’;


//        boolean b = 0;

        P p = new C();
        System.out.println(p.method());

        System.out.println(p.s);

//        float  a = 5.5;
String x = "x";
x.concat("y");
        System.out.println(x);

//
//        ApartmentParser parser = getRealEstateParser();
////        List<Apartment> apartments = parser.parseApartmentsFromUrl(url);
////
////        for (var ap : apartments) {
////            System.out.println(ap);
////        }
//        Document doc = getDocument(url);
//        System.out.println(parser.parseOnePage(doc.html()));

//        Document page = Jsoup.parse(getJSHtml("https://www.olx.ua/uk/nedvizhimost/kvartiry/dolgosrochnaya-arenda-kvartir/lvov/?currency=UAH&search%5Bfilter_float_price:from%5D=6000&search%5Bfilter_float_price:to%5D=10000"));
//
//        System.out.println(page.select("#mainContent > div.css-1nvt13t > form > div.css-x1jscs > div.css-n9feq4 > span > span"));

//        new LvivApartmentParser().parseRealEstateLviv();


//        new LvivApartmentParser().parseOLX();

//        String url = "https://www.olx.ua/uk/nedvizhimost/kvartiry/dolgosrochnaya-arenda-kvartir/lvov/?currency=UAH&search%5Bfilter_float_price:from%5D=6000&search%5Bfilter_float_price:to%5D=10000";

//        String url = "https://bigl.ua/ua/s3748783-topbooknetua";
//
//        List<String> urls = new ArrayList<>();
//
//        for(int i = 1; i <= 321; i++) {
//            urls.add(url + "/page" + i);
//        }
//
//        System.out.println("Urls are prepared! " + urls.size() + " size");
//
//        List<String> htmls = new ArrayList<>();
//
//        for (var u : urls) {
//
//
//            System.out.println("Url - " + u);
//            Document doc = getDocument(u);
//            doc.removeClass("#base-page > div._1XYhr > div._2vR_m._3Hg65.mYOos > div > div.ek-grid.ek-grid_indent_xs.ek-grid_align_center > div.ek-grid__item.ek-grid__item_width_1-1.ek-grid__item_width_4-5\\@large > div:nth-child(7) > div");
//            htmls.add(doc.html());
//        }
//
//        System.out.println("Html is ready!");
//
//        List<Element> elements = new ArrayList<>();
//
//        String sel = ".ek-grid__item  div > div._2vR_m._3Kc4N._1BGhw > a";
//        for (var h : htmls) {
//            Document doc = Jsoup.parse(h);
//
//            Elements els = doc.select(sel);
//
//            elements.addAll(els.stream().toList());
//        }
//
//        System.out.println("Elements is is here!" + elements.size() + " size");
//
//
//        for (var e : elements) {
//            System.out.println(e.text());
//        }
//

//        new LvivApartmentParser().parseOLXLviv();

//        var difference = findDifference(list);
//        System.out.println("Difference:");
//        difference.forEach(System.out::println);

//        List<Apartment> l1 = new ArrayList<>();
//        l1.add(new Apartment("t1", "a1", "u1", 0));
//        l1.add(new Apartment("t2", "a2", "u2", 0));
//
//        List<Apartment> l2 = new ArrayList<>();
//        l2.add(new Apartment("t1", "a1", "u1", 0));
//        l2.add(new Apartment("t4", "a4", "u4", 0));
//        l2.add(new Apartment("t3", "a3", "u3", 0));
//
//        List<Apartment> difference = new ArrayList<>(l1);
//        difference.removeAll(l2);
//
//        List<Apartment> difference2 = new ArrayList<>(l2);
//        difference2.removeAll(l1);
//
//        difference.addAll(difference2);
//
//        System.out.println("Різниця між списками:");
//        for (Apartment apartment : difference) {
//            System.out.println(apartment);
//        }
//
//        System.out.println("Another:");
//        System.out.println(findDifference(l1,l2, List.of(new Apartment("t1", "a1", "u1", 0), new Apartment("t5", "a2", "u2", 0))));


//        Apartment apartment1 = new Apartment("Володимира Великого вул., Львів, Франківський район", "Володимира Великого вул.", "https://www.real-estate.lviv.ua/4226813-квартира-оренда-львів-франківський-володимира-великого.html", 0);
//        Apartment apartment2 = new Apartment("Володимира Великого вул., Львів, Франківський район", "Володимира Великого вул.", "https://www.real-estate.lviv.ua/4226606-квартира-оренда-львів-франківський-володимира-великого.html", 0);
//
//        System.out.println(apartment1.equals(apartment2));

//        ApartmentParser parser = ApartmentParsers.getOlxParser();

//        new LvivApartmentParser().parseOLXLviv();
//        List<ApartmentInstance> instances = parser.parseOnePage(getHtmlFromUrl(url));

//        System.out.println("Квартири:");
//        instances.forEach(System.out::println);
//
//        System.out.println(instances.size());
//        System.out.println("=============================");
//        String reg = "(?<city>.*?), (?<district>.*?) - (?<date>.*?)$";
//        String text = "Львів, Шевченківський - Сьогодні о 13:26";
//        Matcher m = Pattern.compile(reg).matcher(text);
//
//        if (m.matches()) {
//            System.out.println(m.group("city"));
//            System.out.println(m.group("district"));
//            System.out.println(m.group("date"));
//        }

//        new LvivApartmentParser().parseOLXLviv();
        //        new LvivApartmentParser().parseRealEstateLviv();
//        System.out.println(TimeZone.getDefault());
//
//        TimeZone.setDefault(TimeZone.getDefault());
//        String content = getHtmlFromUrl(url);
//
//        Document doc = Jsoup.parse(content);
//        removeComments(doc);
//        System.out.println(doc.select("#mainContent > div.css-1nvt13t > form > div.css-x1jscs > div.css-n9feq4 > span > span"));
//        Elements elements = doc.select(".listing-grid-container > .css-oukcj3 > .css-1sw7q4x");
//
//        for (var e : elements) {
//            System.out.println(e.select("h6.css-16v5mdi"));
//            System.out.println(e.select("div.css-odp1qd > p"));
//
//            System.out.println("-----------------------------------------\n");
//        }
    }

    public ApartmentInstance create(String title, String address, String url, String des) {
        ApartmentInstance instance = new ApartmentInstance();
        instance.district(des);
        instance.createApartment(title, address, url);

        return instance;
    }

    /*public List<Apartment> getOlxApartments(String url, int amount) {
        List<String> urls = new ArrayList<>();
        for (int i = 1; i <= amount; i++) urls.add(url + "&page=" + i);

        List<String> allhtml = getHtmlFromPages(urls);

        System.out.println(Jsoup.parse(allhtml.get(0)).select("#mainContent > div.css-1nvt13t > form > div.css-x1jscs > div.css-n9feq4 > span > span"));


        ApartmentParser parser = ApartmentParsers.getOlxParser();

        return allhtml
                .stream()
                .flatMap(html->parser.parseOnePage(html)
                        .stream()).toList();*/
//        for (var html : allhtml) {
//
//            parser.parseOnePage(html);
//        }
//    }

    private Map<String, List<Apartment>> groupApartmentsByDistrict(List<ApartmentInstance> apartmentInstances) {
        return apartmentInstances
                .stream()
                .collect(Collectors.groupingBy(ApartmentInstance::getDistrictName,
                        Collectors.mapping(ApartmentInstance::getApartment, Collectors.toList())));
    }

    public List<String> getHtmlFromPages(List<String> urls) {

        return urls
                .stream()
                .map(u -> {
                    try {
                        return getHtmlFromUrl(u);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(h -> Jsoup.parse(h).html())
                .toList();
    }

    public static List<Apartment> findDifference(List<List<Apartment>> apartmentLists) {
        return apartmentLists
                .stream()
                .flatMap(List::stream) // Об'єднати всі квартири в один потік
                .collect(Collectors.groupingBy(apartment -> apartment, Collectors.counting())) // Підрахунок кількості кожної квартири
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1) // Відфільтрувати квартири, які є унікальними
                .map(Map.Entry::getKey) // Повернути квартири як результат
                .collect(Collectors.toList());
    }

    private WebDriver getDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Включаємо хедлес-режим
        options.addArguments("--disable-gpu"); // Вимикаємо GPU, що допомагає уникнути проблем

        // Створення хедлес-браузера Chrome
        return new ChromeDriver(options);
    }

    public String getHtmlFromUrl(String url) throws IOException {
        OkHttpClient client =  new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
//                .header("Date", new Date().toString())
                .build();

        try (Response response = client.newCall(request).execute()) {
//            String dateHeader = response.header("Date");
//
//            System.out.println(dateHeader);
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                System.out.println("Request was not successful. Response code: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

//    private String getJSHtml(String url) {
//        WebDriver driver = getDriver();
//        driver.get(url);
//
//        // Використовуємо JavaScriptExecutor для отримання HTML-коду
//        if (driver instanceof JavascriptExecutor jsExecutor) {
//
//            // Отримуємо HTML-код
//            return (String) jsExecutor.executeScript("return document.documentElement.outerHTML;");
//        }
//
//        // Закриваємо драйвер
//        driver.quit();
//
//        return null;
//    }

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
    // Рекурсивна функція для видалення коментарів з усіх елементів
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
