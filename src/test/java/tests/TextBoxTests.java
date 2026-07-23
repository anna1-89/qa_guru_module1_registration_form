package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests extends TestBase{

    String userName = "Anna Pilugina";
    String userEmail = "anna1_89@mail.ru";
    String currentAddress = "Zarelye";
    String permanentAddress = "Velikiy Novgorod";

    String userInvalidEmail = "anna1_89mail.ru";

    @Test
    void positiveFillAllFormTest() {
        open("/text-box.html");
        $("#userName").setValue(userName);
        $("#userEmail").setValue(userEmail);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").click();

        $("#output #name").shouldHave(text(userName));
        $("#output #email").shouldHave(text(userEmail));
        $("#output #currentAddress").shouldHave(text(currentAddress));
        $("#output #permanentAddress").shouldHave(text(permanentAddress));
    }

    @Test
    void positiveFillRequiredFormTest() {
        open("/text-box.html");
        $("#userName").setValue(userName);
        $("#userEmail").setValue(userEmail);
        $("#currentAddress").setValue(currentAddress);
        $("#submit").click();

        $("#output #name").shouldHave(text(userName));
        $("#output #email").shouldHave(text(userEmail));
        $("#output #currentAddress").shouldHave(text(currentAddress));
    }

    @Test
    void negativeInvalidEmailTest() {
        open("/text-box.html");
        $("#userName").setValue(userName);
        $("#userEmail").setValue(userInvalidEmail);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").click();

        $("#output").shouldNotBe(visible);

    }

}
