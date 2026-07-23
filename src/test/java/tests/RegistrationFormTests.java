package tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static data.TestData.*;

public class RegistrationFormTests extends TestBase {

    @Test
    void positiveFillAllFormTest() {
        open("/automation-practice-form.html");
        $("#fixedban").$(byText("×")).click();
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(number);
        $(".datepicker-field").click();
        $(".react-datepicker__month-select").$(byText(monthBirthDate)).click();
        $(".react-datepicker__year-select").$(byText(yearBirthDate)).click();
        $(".react-datepicker__month").$(byText(birthDate)).click();
        $("#subjectsInput").setValue(subject).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#uploadPicture").uploadFromClasspath(fileName);
        $("#currentAddress").setValue(address);
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#submit").scrollTo().click();

        $("#resultModal").shouldBe(visible);
        $("#example-modal-sizes-title-lg").shouldHave(text(textSuccessRegistration));
        $("#resultBody").$(byText("Student Name")).sibling(0).shouldHave(text(firstName + " " + lastName));
        $("#resultBody").$(byText("Student Email")).sibling(0).shouldHave(text(email));
        $("#resultBody").$(byText("Gender")).sibling(0).shouldHave(text(gender));
        $("#resultBody").$(byText("Mobile")).sibling(0).shouldHave(text(number));
        $("#resultBody").$(byText("Date of Birth")).sibling(0).shouldHave(text(birthDate + " " +  monthBirthDate.substring(0,3) + " " + yearBirthDate));
        $("#resultBody").$(byText("Subjects")).sibling(0).shouldHave(text(subject));
        $("#resultBody").$(byText("Hobbies")).sibling(0).shouldHave(text(hobby));
        $("#resultBody").$(byText("Picture")).sibling(0).shouldHave(text(fileName));
        $("#resultBody").$(byText("Address")).sibling(0).shouldHave(text(address));
        $("#resultBody").$(byText("State and City")).sibling(0).shouldHave(text(state + " " + city));
    }

    @Test
    void positiveFillRequiredFormTest() {
        open("/automation-practice-form.html");
        $("#fixedban").$(byText("×")).click();

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(number);
        $("#submit").scrollTo().click();

        $("#resultModal").shouldBe(visible);
        $("#example-modal-sizes-title-lg").shouldHave(text(textSuccessRegistration));
        $("#resultBody").$(byText("Student Name")).sibling(0).shouldHave(text(firstName + " " + lastName));
        $("#resultBody").$(byText("Student Email")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("Gender")).sibling(0).shouldHave(text(gender));
        $("#resultBody").$(byText("Mobile")).sibling(0).shouldHave(text(number));
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
        $("#formError").shouldHave(text(textNotSuccessRegistration));
    }

    @Test
    void negativeNotAllRequiredFistNameTest() {
        open("/automation-practice-form.html");
        $("#fixedban").$(byText("×")).click();

        $("#lastName").setValue(lastName);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(number);
        $("#submit").scrollTo().click();

        $("#formError").shouldBe(visible);
        $("#formError").shouldHave(text(textNotSuccessRegistration));
    }

    @Test
    void negativeNotAllRequiredLastNameTest() {
        open("/automation-practice-form.html");
        $("#fixedban").$(byText("×")).click();

        $("#firstName").setValue(firstName);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(number);
        $("#submit").scrollTo().click();

        $("#formError").shouldBe(visible);
        $("#formError").shouldHave(text(textNotSuccessRegistration));
    }

    @Test
    void negativeNotAllRequiredGenderTest() {
        open("/automation-practice-form.html");
        $("#fixedban").$(byText("×")).click();

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userNumber").setValue(number);
        $("#submit").scrollTo().click();

        $("#formError").shouldBe(visible);
        $("#formError").shouldHave(text(textNotSuccessRegistration));
    }

    @Test
    void negativeNotAllRequiredUserNumberTest() {
        open("/automation-practice-form.html");
        $("#fixedban").$(byText("×")).click();

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#genterWrapper").$(byText(gender)).click();
        $("#submit").scrollTo().click();

        $("#formError").shouldBe(visible);
        $("#formError").shouldHave(text(textNotSuccessRegistration));
    }

    @Test
    void negativeInvalidUserNumberTest() {
        open("/automation-practice-form.html");
        $("#fixedban").$(byText("×")).click();

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(invalidNumber);
        $("#submit").scrollTo().click();

        $("#formError").shouldBe(visible);
        $("#formError").shouldHave(text(textNotSuccessRegistration));
    }

}
