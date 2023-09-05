package ru.andreev.web.config;

import static com.codeborne.selenide.Configuration.*;

public abstract class SelenideConfig {

    public static void setup() {
        browser = "chrome";
        browserSize = "1920x1080";
        timeout = 30 * 1000;
        pageLoadTimeout = 30 * 1000;
        downloadsFolder = "src/test/resources/downloads";
        fastSetValue = true;
        pageLoadStrategy = "eager";
    }
}
