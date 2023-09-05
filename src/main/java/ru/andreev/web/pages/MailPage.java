package ru.andreev.web.pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.allMatch;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MailPage {

    public static final String ADDRESS_MAIL_RU = "https://mail.ru/";
    private final String aXpathByTestId = "//a[@data-testid='%s']";
    private final String allXpathByTestId = "//*[@data-testid='%s']";
    private final Map<String, String> ratesIdElements = Map.of(
            "USD", "rates-item-usd",
            "EUR", "rates-item-eur",
            "OIL", "rates-item-oil"
    );

    private final Map<String, String> tabIdElements = Stream.of(new String[][]{
            {"Новости", "news-tabs-tab-item-main"},
            {"Спецоперация", "news-tabs-tab-item-ukraine2022"},
            {"Спорт", "news-tabs-tab-item-sport"},
            {"Леди", "news-tabs-tab-item-lady"},
            {"Авто", "news-tabs-tab-item-auto"},
            {"Кино", "news-tabs-tab-item-cinema"},
            {"Hi-Tech", "news-tabs-tab-item-hitech"},
            {"Игры", "news-tabs-tab-item-games"},
            {"Дети", "news-tabs-tab-item-deti"},
            {"Здоровье", "news-tabs-tab-item-health"},
            {"Дом", "news-tabs-tab-item-dom"},
            {"Питомцы", "news-tabs-tab-item-pets"}
    }).collect(Collectors.toMap(p -> p[0], p -> p[1]));

    public MailPage open() {
        Selenide.open(ADDRESS_MAIL_RU);
        return this;
    }

    public String getRates(String curr) {
        return $(By.xpath(String.format(aXpathByTestId, ratesIdElements.get(curr))))
                .should(visible)
                .getText();
    }

    public MailPage goToTab(String tab) {
        $(By.xpath(String.format(aXpathByTestId, tabIdElements.get(tab))))
                .should(visible)
                .click();
        return this;
    }

    public List<String> getNewsTitle() {
        int countTitle = $$(By.xpath(String.format(allXpathByTestId, "news-item-title")))
                .should(sizeGreaterThan(0))
                .should(allMatch("Не все элементы отображены на странице.", WebElement::isDisplayed)).size();
        List<String> newsTitle = new ArrayList<>();
        for (int i = 0; i < countTitle; i++) {
            newsTitle.add($$(By.xpath(String.format(allXpathByTestId, "news-item-title"))).get(i).getText());
        }
        return newsTitle;
    }
}
