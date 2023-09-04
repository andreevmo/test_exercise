package web;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.andreev.web.pages.HiTechPage;
import ru.andreev.web.pages.MailPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.switchTo;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.andreev.web.pages.HiTechPage.ADDRESS_HI_TECH;
import static ru.andreev.web.pages.HiTechPage.TITLE_PAGE_HI_TECH;

public class MailTest extends BaseTest {

    private MailPage mailPage;
    private HiTechPage hiTech;

    @BeforeEach
    void setup() {
        mailPage = new MailPage();
        hiTech = new HiTechPage();
    }

    @Test
    void testMail() {
        String rates = mailPage.open()
                .getRates("USD");
        logger.info(rates);

        List<String> newsTitle = mailPage.goToTab("Hi-Tech")
                .getNewsTitle();
        logger.info(newsTitle.toString());

        mailPage.goToTab("Hi-Tech");

        switchTo().window(TITLE_PAGE_HI_TECH);

        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertThat(currentUrl).isEqualTo(ADDRESS_HI_TECH);


        String altValue = hiTech.getAttributeAltByHiTechImg();
        logger.info(altValue);
    }
}
