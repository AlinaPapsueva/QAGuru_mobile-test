package androidstudio.lesson22.tests;

import androidstudio.lesson22.drivers.BrowserstackMobileDriver;
import androidstudio.lesson22.drivers.LocalMobileDriver;
import androidstudio.lesson22.helpers.Attach;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = LocalMobileDriver.class.getName();
        Configuration.browserSize = null;

        String deviceHost = System.getProperty("deviceHost");
        switch (deviceHost) {
            case "android_brwstack":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            case "android_emulator":
                Configuration.browser = LocalMobileDriver.class.getName();
                break;
        }
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        String deviceHost = System.getProperty("deviceHost");
        String sessionId = Selenide.sessionId().toString();

        Attach.pageSource();
        closeWebDriver();

        if (deviceHost.equals("android_brwstack")) {
            Attach.addVideo(sessionId);;
        }
    }

}