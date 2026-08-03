package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.RegistrationFormPage;
import pages.TextBoxPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://qa-guru.github.io/one-page-form";

        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 60000;

    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }
}
