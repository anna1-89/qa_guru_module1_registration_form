package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import pages.RegistrationFormPage;
import pages.TextBoxPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://qa-guru.github.io/one-page-form";

        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }
}
