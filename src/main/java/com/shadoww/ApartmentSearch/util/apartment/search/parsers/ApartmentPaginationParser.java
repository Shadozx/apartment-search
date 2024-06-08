package com.shadoww.ApartmentSearch.util.apartment.search.parsers;

import com.shadoww.ApartmentSearch.models.Apartment;
import com.shadoww.ApartmentSearch.util.apartment.search.parsers.additional.ApartmentConverter;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ApartmentPaginationParser implements ApartmentTTParser {

    private WebDriver driver; //getDriver();
    private String apartmentSelector;

    private ApartmentConverter apartmentConverter;
//    private String districtSelector;
//    private String citySelector;

    public ApartmentPaginationParser(String apartmentSelector) {
        this.apartmentSelector = apartmentSelector;
    }

    public List<Apartment> parseApartments(String url) {
        try {
            driver.get(url);
            List<Apartment> allApartments = new ArrayList<>();


            List<WebElement> elements = driver.findElements(By.cssSelector(apartmentSelector));


//            System.out.println(driver.findElement(By.cssSelector("#mainContent > div.css-lxw5r3 > form > div.css-x1jscs > div.css-n9feq4 > span > span")).getText());
            // враховує тільки варіант якщо тільки одна сторінка а треба щоб вміло по всім сайтам
//            return elements == null ? new ArrayList<>() : elements.stream().map(this::getApartment).toList();
            return null;
        }finally {
            driver.quit();
        }
    }

//    @Override
//    public Apartment getApartment(WebElement el) {
//        return apartmentConverter.parseApartmentFromAdvertisement(el);
//    }


}


/**
 *
 * Є сайти по сторінкові що переходячи на наступну сторінку, а що прогортаючи вони прогружаючись - такі види сайтів з ких можна отримати інформацію
 * кожен сайт має будову сторінки по html а значить треба визначити що треба взяти з сайту
 * сайт треба після того коли визначено що треба взяти треба спарсити треба визначити як спарсити
 * як визначити чи ця інформація вже є базі даних? якщо немає то як добавляти? а якщо є частково то як додати частину? (треба якась фільтрація даних наприклад)
 *
 * */


/**
 *  Сторінковий парсер
 *  1. отримує силку
 *  2. проскановуємо всі сторінки з результатами і збираємо html контент з сторінок(залежить від типу парсера)
 *  3. запускаємо цикл який пробігається по html контенту з кожної спаршеної сторінки і заданим селектором отримуємо оголошення
 *  4. заданим методом обробки оголошення перетворюємо його в обєкт квартири і віддаємо
 *  5. збираємо з циклу список обєктів квартир і віддаємо його як результат роботи
 *
 * */