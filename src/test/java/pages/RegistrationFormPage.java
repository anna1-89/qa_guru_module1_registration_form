package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.RegistrationResultComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationFormPage {
    //Elements
    private final String pageAddress = "/automation-practice-form.html";
    private final SelenideElement banner = $("#fixedban").$(byText("×"));
    private final SelenideElement firstNameField = $("#firstName");
    private final SelenideElement lastNameField = $("#lastName");
    private final SelenideElement userEmailField = $("#userEmail");
    private final SelenideElement genderContainer = $("#genterWrapper");
    private final SelenideElement userNumberField = $("#userNumber");
    private final SelenideElement calendarComponent = $(".datepicker-field");
    private final SelenideElement subjectSelect = $("#subjectsInput");
    private final SelenideElement hobbySelect = $("#hobbiesWrapper");
    private final SelenideElement pictureSelect = $("#uploadPicture");
    private final SelenideElement currentAddressField = $("#currentAddress");
    private final SelenideElement stateCityContainer =  $("#stateCity-wrapper");
    private final SelenideElement stateField = $("#state");
    private final SelenideElement cityField = $("#city");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement errorLabel = $("#formError");

    public static String textNotSuccessRegistration = "Please fill required fields and enter a valid 10-digit mobile number.";

    CalendarComponent calendar = new CalendarComponent();
    RegistrationResultComponent registrationResultComponent = new RegistrationResultComponent();

    //Actions
    @Step ("Открываем страницу регистрации")
    public RegistrationFormPage openPage() {
        open(pageAddress);
        return this;
    }

    @Step ("Закрываем всплывающие окна")
    public RegistrationFormPage closeBanner() {
        banner.click();
        return this;
    }

    @Step ("Вводим имя")
    public RegistrationFormPage enterFirstName(String value) {
        firstNameField.setValue(value);
        return this;
    }

    @Step ("Вводим фамилию")
    public RegistrationFormPage enterLastName(String value) {
        lastNameField.setValue(value);
        return this;
    }

    @Step ("Вводим email")
    public RegistrationFormPage enterUserEmail(String value) {
        userEmailField.setValue(value);
        return this;
    }

    @Step ("Выбираем пол")
    public RegistrationFormPage chooseGender(String value) {
        genderContainer.$(byText(value)).click();
        return this;
    }

    @Step ("Вводим номер телефона")
    public RegistrationFormPage enterUserNumber(String value) {
        userNumberField.setValue(value);
        return this;
    }

    @Step ("Выбираем дату рождения")
    public RegistrationFormPage setDateOfBirth(String day, String month, String year) {
        calendarComponent.click();
        calendar.setDate(day, month, year);
        return this;
    }

    @Step ("Выбираем предмет")
    public RegistrationFormPage selectSubject(String value) {
        subjectSelect.setValue(value).pressEnter();
        return this;
    }

    @Step ("Выбираем хобби")
    public RegistrationFormPage selectHobby(String value) {
        hobbySelect.$(byText(value)).click();
        return this;
    }

    @Step ("Загружаем изображение")
    public RegistrationFormPage uploadPicture(String value) {
        pictureSelect.uploadFromClasspath(value);
        return this;
    }

    @Step ("Вводим текущий адрес")
    public RegistrationFormPage enterCurrentAddress(String value) {
        currentAddressField.setValue(value);
        return this;
    }

    public RegistrationFormPage selectState(String value) {
        stateField.scrollTo().click();
        stateCityContainer.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage selectCity(String value) {
        cityField.click();
        stateCityContainer.$(byText(value)).click();
        return this;
    }

    @Step ("Выбираем штат и город")
    public RegistrationFormPage selectStateAndCity(String state, String city) {
        selectState(state);
        selectCity(city);
        return this;
    }

    @Step ("Нажимаем кнопку submit")
    public RegistrationFormPage submitForm() {
        submitButton.scrollTo().click();
        return this;
    }

    @Step ("Проверяем успешность регистрации")
    public RegistrationFormPage assertSuccessSubmission() {
        registrationResultComponent.checkSuccessSubmission();
        return this;
    }

    @Step ("Проверяем зарегистрированные данные")
    public RegistrationFormPage checkSubmittedData(String key, String value) {
        registrationResultComponent.checkData(key, value);
        return this;
    }

    @Step ("Проверяем неуспешность регистрации")
    public RegistrationFormPage assertFailSubmission() {
        errorLabel.shouldBe(visible);
        errorLabel.shouldHave(text(textNotSuccessRegistration));
        return this;
    }
}
