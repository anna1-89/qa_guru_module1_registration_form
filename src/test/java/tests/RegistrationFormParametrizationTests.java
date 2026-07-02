package tests;

import data.Gender;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationFormParametrizationTests extends TestBase {

    @BeforeEach
    void setUp() {
        open("/automation-practice-form.html");
        $("#fixedban").$(byText("×")).click();
    }


    @CsvFileSource(resources = "/test_data/registrationShouldBeSuccessForAllFilledFields.csv")
    @ParameterizedTest(name = "Регистация при всех полях упешна для {0} {1} {2} {3}")
    @Tags({
            @Tag("SMOKE"),
            @Tag("POSITIVE")
        }
    )
    @DisplayName("Регистрация при заполнении всех полей формы")
     void registrationShouldBeSuccessForAllFilledFieldsTest(
             String firstName, String lastName, String email, String gender, String number, String monthBirthDate, String yearBirthDate, String birthDate,
             String subject, String hobby, String address, String state, String city) {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(number);
        $(".datepicker-field").click();
        $(".react-datepicker__month-select").selectOption(monthBirthDate);
        $(".react-datepicker__year-select").selectOption(yearBirthDate);
        $(".react-datepicker__month").$(byText(birthDate)).click();
        $("#subjectsInput").setValue(subject).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#uploadPicture").uploadFromClasspath("01.jpg");
        $("#currentAddress").setValue(address);
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#submit").scrollTo().click();

        $("#resultModal").shouldBe(visible);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $("#resultBody").$(byText("Student Name")).sibling(0).shouldHave(text(firstName + " " + lastName));
        $("#resultBody").$(byText("Student Email")).sibling(0).shouldHave(text(email));
        $("#resultBody").$(byText("Gender")).sibling(0).shouldHave(text(gender));
        $("#resultBody").$(byText("Mobile")).sibling(0).shouldHave(text(number));
        $("#resultBody").$(byText("Date of Birth")).sibling(0).shouldHave(text(birthDate + " " +  monthBirthDate.substring(0,3) + " " + yearBirthDate));
        $("#resultBody").$(byText("Subjects")).sibling(0).shouldHave(text(subject));
        $("#resultBody").$(byText("Hobbies")).sibling(0).shouldHave(text(hobby));
        $("#resultBody").$(byText("Picture")).sibling(0).shouldHave(text("01.jpg"));
        $("#resultBody").$(byText("Address")).sibling(0).shouldHave(text(address));
        $("#resultBody").$(byText("State and City")).sibling(0).shouldHave(text(state + " " + city));
    }

    @CsvSource(value = {
            "Anna, Pilugina, Female, 1234567891",
            "Lev, Ivanov, Male, 9876543219"
    })
    @ParameterizedTest(name = "Регистация при обязательных полях упешна для {0} {1} {2} {3}")
    @Tags({
            @Tag("SMOKE"),
            @Tag("POSITIVE")
    }
    )
    @DisplayName("Регистрация при заполнении обязательных полей формы")
    void registrationShouldBeSuccessForRequiredFieldsTest(String firstName, String lastName, String gender, String userNumber) {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        $("#submit").scrollTo().click();

        $("#resultModal").shouldBe(visible);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $("#resultBody").$(byText("Student Name")).sibling(0).shouldHave(text(firstName + " " + lastName));
        $("#resultBody").$(byText("Student Email")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("Gender")).sibling(0).shouldHave(text(gender));
        $("#resultBody").$(byText("Mobile")).sibling(0).shouldHave(text(userNumber));
        $("#resultBody").$(byText("Date of Birth")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("Subjects")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("Hobbies")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("Picture")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("Address")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("State and City")).sibling(0).shouldHave(text("-"));
    }

    @EnumSource(Gender.class)
    @ParameterizedTest(name="Регистрация доступна для пола {0}")
    @Tags({
            @Tag("SMOKE"),
            @Tag("POSITIVE")
    }
    )
    @DisplayName("Регистрация возможна для обоих полов")
    void genderShouldBeDisplayedCorrectly(Gender gender) {
        $("#genterWrapper").$(byText(gender.name())).exists();
    }

    static Stream<Arguments> registrationSuccessfullForAllGenders(){
        return Stream.of(
                Arguments.of("Anna", "Pilugina", "Female", "1234567891"),
                Arguments.of("Lev", "Ivanov", "Male", "9876543219")
        );
    }

    @MethodSource
    @ParameterizedTest(name="Регистрация проходит для всех полов")
    @Tags({
            @Tag("SMOKE"),
            @Tag("POSITIVE")
    }
    )
    //List<String> expectedData
    @DisplayName("Регистрация проходит для всех полов")
    void registrationSuccessfullForAllGenders(String firstName, String lastName, String gender, String userNumber) {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        $("#submit").scrollTo().click();

        $("#resultModal").shouldBe(visible);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $("#resultBody").$(byText("Student Name")).sibling(0).shouldHave(text(firstName + " " + lastName));
        $("#resultBody").$(byText("Gender")).sibling(0).shouldHave(text(gender));
        $("#resultBody").$(byText("Mobile")).sibling(0).shouldHave(text(userNumber));
    }

    @Test
    @Tags({
            @Tag("SMOKE"),
            @Tag("NEGATIVE")
    }
    )
    void negativeEmptyFormTest() {
        $("#submit").scrollTo().click();

        $("#formError").shouldBe(visible);
        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    @Tag("NEGATIVE")
    void negativeNotAllRequiredFistNameTest() {
        $("#lastName").setValue("Pilugina");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("123456789");
        $("#submit").scrollTo().click();

        $("#formError").shouldBe(visible);
        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Disabled("Доработка функционала")
    @Test
    @Tag("NEGATIVE")
    void negativeNotAllRequiredLastNameTest() {
        $("#firstName").setValue("Anna");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("123456789");
        $("#submit").scrollTo().click();

        $("#formError").shouldBe(visible);
        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    @Tag("NEGATIVE")
    void negativeNotAllRequiredGenderTest() {
        $("#firstName").setValue("Anna");
        $("#lastName").setValue("Pilugina");
        $("#userNumber").setValue("123456789");
        $("#submit").scrollTo().click();

        $("#formError").shouldBe(visible);
        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    @Tag("NEGATIVE")
    @DisplayName("Ошибки регистрации при пустом номере телефона")
    void registrationShouldNotBeSuccessForEmptyUserNumberTest() {
        $("#firstName").setValue("Anna");
        $("#lastName").setValue("Pilugina");
        $("#genterWrapper").$(byText("Female")).click();
        $("#submit").scrollTo().click();

        $("#formError").shouldBe(visible);
        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @ValueSource(strings = {
            "12345678", "1", "ab12345678"
    })
    @ParameterizedTest(name = "Проверка ошибки регистрации при невалидной номере телефона: {0}")
    @Tags({
            @Tag("SMOKE"),
            @Tag("NEGATIVE")
    }
    )
    @DisplayName("Ошибки регистрации при невалидном номере телефона")
    void registrationShouldNotBeSuccessForInvalidUserNumberTest(String userNumber) {
        $("#firstName").setValue("Anna");
        $("#lastName").setValue("Pilugina");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue(userNumber);
        $("#submit").scrollTo().click();

        $("#formError").shouldBe(visible);
        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

}
