package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationFormPage {
    //Elements
    private final String pageAddress = "/automation-practice-form.html";
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
    private final SelenideElement resultComponent = $("#resultModal");
    private final SelenideElement resultTable = $("#resultBody");
    private final SelenideElement resultComponentTitle = $("#example-modal-sizes-title-lg");
    private final SelenideElement errorLabel = $("#formError");

    public static String textSuccessRegistration = "Thanks for submitting the form";
    public static String textNotSuccessRegistration = "Please fill required fields and enter a valid 10-digit mobile number.";

    CalendarComponent calendar = new CalendarComponent();

    //Actions
    public RegistrationFormPage openPage() {
        open(pageAddress);
        $("#fixedban").$(byText("×")).click();
        return this;
    }

    public RegistrationFormPage enterFirstName(String value) {
        firstNameField.setValue(value);
        return this;
    }

    public RegistrationFormPage enterLastName(String value) {
        lastNameField.setValue(value);
        return this;
    }

    public RegistrationFormPage enterUserEmail(String value) {
        userEmailField.setValue(value);
        return this;
    }

    public RegistrationFormPage chooseGender(String value) {
        genderContainer.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage enterUserNumber(String value) {
        userNumberField.setValue(value);
        return this;
    }

    public RegistrationFormPage setDateOfBirth(String day, String month, String year) {
        calendarComponent.click();
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationFormPage selectSubject(String value) {
        subjectSelect.setValue(value).pressEnter();
        return this;
    }

    public RegistrationFormPage selectHobby(String value) {
        hobbySelect.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage uploadPicture(String value) {
        pictureSelect.uploadFromClasspath(value);
        return this;
    }

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

    public RegistrationFormPage selectStateAndCity(String state, String city) {
        selectState(state);
        selectCity(city);
        return this;
    }

    public RegistrationFormPage submitForm() {
        submitButton.scrollTo().click();
        return this;
    }

    public RegistrationFormPage assertSuccessSubmission() {
        resultComponent.shouldBe(visible);
        resultComponentTitle.shouldHave(text(textSuccessRegistration));
        return this;
    }

    public RegistrationFormPage checkSubmittedData(String key, String value) {
        assertEquals(resultTable.$(byText(key)).sibling(0).text(), value);
        return this;
    }

    public RegistrationFormPage assertFailSubmission() {
        errorLabel.shouldBe(visible);
        errorLabel.shouldHave(text(textNotSuccessRegistration));
        return this;
    }
}
