package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationFormTests extends TestBase {

    @Test
    void positiveFillAllFormTest() {
        open("/automation-practice-form.html");
        $("#fixedban").$(byText("×")).click();

        $("#firstName").setValue("Anna");
        $("#lastName").setValue("Pilugina");
        $("#userEmail").setValue("anna1_89@mail.ru");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("1234567891");
        $(".datepicker-field").click();
        $(".react-datepicker__month-select").$(byText("January")).click();
        $(".react-datepicker__year-select").$(byText("1989")).click();
        $(".react-datepicker__month").$(byText("27")).click();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("01.jpg");
        $("#currentAddress").setValue("Velikiy Novgorod");
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();
        $("#submit").scrollTo().click();

        $("#resultModal").shouldBe(visible);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $("#resultBody").$(byText("Student Name")).sibling(0).shouldHave(text("Anna Pilugina"));
        $("#resultBody").$(byText("Student Email")).sibling(0).shouldHave(text("anna1_89@mail.ru"));
        $("#resultBody").$(byText("Gender")).sibling(0).shouldHave(text("Female"));
        $("#resultBody").$(byText("Mobile")).sibling(0).shouldHave(text("1234567891"));
        $("#resultBody").$(byText("Date of Birth")).sibling(0).shouldHave(text("27 Jan 1989"));
        $("#resultBody").$(byText("Subjects")).sibling(0).shouldHave(text("Computer Science"));
        $("#resultBody").$(byText("Hobbies")).sibling(0).shouldHave(text("Reading"));
        $("#resultBody").$(byText("Picture")).sibling(0).shouldHave(text("01.jpg"));
        $("#resultBody").$(byText("Address")).sibling(0).shouldHave(text("Velikiy Novgorod"));
        $("#resultBody").$(byText("State and City")).sibling(0).shouldHave(text("Haryana Karnal"));
    }

    @Test
    void positiveFillRequiredFormTest() {
        open("/automation-practice-form.html");
        $("#fixedban").$(byText("×")).click();

        $("#firstName").setValue("Anna");
        $("#lastName").setValue("Pilugina");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("1234567891");
        $("#submit").scrollTo().click();

        $("#resultModal").shouldBe(visible);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $("#resultBody").$(byText("Student Name")).sibling(0).shouldHave(text("Anna Pilugina"));
        $("#resultBody").$(byText("Student Email")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("Gender")).sibling(0).shouldHave(text("Female"));
        $("#resultBody").$(byText("Mobile")).sibling(0).shouldHave(text("1234567891"));
        $("#resultBody").$(byText("Date of Birth")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("Subjects")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("Hobbies")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("Picture")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("Address")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("State and City")).sibling(0).shouldHave(text("-"));
    }

    @Test
    void negativeEmptyFormTest() {
        open("/automation-practice-form.html");
        $("#fixedban").$(byText("×")).click();

        $("#submit").scrollTo().click();

        $("#formError").shouldBe(visible);
        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    void negativeNotAllRequiredFistNameTest() {
        open("/automation-practice-form.html");
        $("#fixedban").$(byText("×")).click();

        $("#lastName").setValue("Pilugina");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("123456789");
        $("#submit").scrollTo().click();

        $("#formError").shouldBe(visible);
        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    void negativeNotAllRequiredLastNameTest() {
        open("/automation-practice-form.html");
        $("#fixedban").$(byText("×")).click();

        $("#firstName").setValue("Anna");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("123456789");
        $("#submit").scrollTo().click();

        $("#formError").shouldBe(visible);
        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    void negativeNotAllRequiredGenderTest() {
        open("/automation-practice-form.html");
        $("#fixedban").$(byText("×")).click();

        $("#firstName").setValue("Anna");
        $("#lastName").setValue("Pilugina");
        $("#userNumber").setValue("123456789");
        $("#submit").scrollTo().click();

        $("#formError").shouldBe(visible);
        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    void negativeNotAllRequiredUserNumberTest() {
        open("/automation-practice-form.html");
        $("#fixedban").$(byText("×")).click();

        $("#firstName").setValue("Anna");
        $("#lastName").setValue("Pilugina");
        $("#genterWrapper").$(byText("Female")).click();
        $("#submit").scrollTo().click();

        $("#formError").shouldBe(visible);
        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    void negativeInvalidUserNumberTest() {
        open("/automation-practice-form.html");
        $("#fixedban").$(byText("×")).click();

        $("#firstName").setValue("Anna");
        $("#lastName").setValue("Pilugina");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("123456789");
        $("#submit").scrollTo().click();

        $("#formError").shouldBe(visible);
        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

}
