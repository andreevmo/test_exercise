package ru.andreev.web.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$;
import static java.util.Objects.requireNonNull;

public class HiTechPage {

    public final static String ADDRESS_HI_TECH = "https://hi-tech.mail.ru/";
    public final static String TITLE_PAGE_HI_TECH = "Новости высоких технологий, обзоры смартфонов, " +
            "гаджетов и бытовой техники - Hi-Tech Mail.ru";

    public String getAttributeAltByHiTechImg() {
        List<SelenideElement> img = $$(By.cssSelector("img.pm-logo__link__pic"))
                .should(sizeGreaterThan(0));
        return img.stream()
                .filter(element -> requireNonNull(element.getAttribute("src")).contains("hi-tech"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Element с картинкой содержащей 'hi-tech' не найден."))
                .getAttribute("alt");
    }
}
