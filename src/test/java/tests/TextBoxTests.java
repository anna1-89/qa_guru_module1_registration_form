package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests extends TestBase{

    @Test
    void positiveFillAllFormTest() {
        open("https://qa-guru.github.io/one-page-form/text-box.html");
        $("#userName").setValue("Anna Pilugina");
        $("#userEmail").setValue("anna1_89@mail.ru");
        $("#currentAddress").setValue("Zarelye");
        $("#permanentAddress").setValue("Velikiy Novgorod");
        $("#submit").click();

        $("#output #name").shouldHave(text("Anna Pilugina"));
        $("#output #email").shouldHave(text("anna1_89@mail.ru"));
        $("#output #currentAddress").shouldHave(text("Zarelye"));
        $("#output #permanentAddress").shouldHave(text("Velikiy Novgorod"));
    }

    @Test
    void positiveFillRequiredFormTest() {
        open("https://qa-guru.github.io/one-page-form/text-box.html");
        $("#userName").setValue("Anna Pilugina");
        $("#userEmail").setValue("anna1_89@mail.ru");
        $("#currentAddress").setValue("Zarelye");
        $("#submit").click();

        $("#output #name").shouldHave(text("Anna Pilugina"));
        $("#output #email").shouldHave(text("anna1_89@mail.ru"));
        $("#output #currentAddress").shouldHave(text("Zarelye"));
    }

    @Test
    void negativeInvalidEmailTest() {
        open("https://qa-guru.github.io/one-page-form/text-box.html");
        $("#userName").setValue("Anna Pilugina");
        $("#userEmail").setValue("anna1");
        $("#currentAddress").setValue("Zarelye");
        $("#permanentAddress").setValue("Velikiy Novgorod");
        $("#submit").click();

        $("#output").shouldNotBe(visible);

    }

}
