package web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.andreev.web.config.SelenideConfig;

import java.util.logging.Logger;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class BaseTest {

    Logger logger = Logger.getLogger("testLogger");

    @BeforeEach
    public void beforeMethod() {
        SelenideConfig.setup();
    }

    @AfterEach
    public void afterMethod() {
        if (hasWebDriverStarted()) {
            closeWebDriver();
        }
    }
}
