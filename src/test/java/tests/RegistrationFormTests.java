package tests;

import data.TestData;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

@Story("Registration form")
public class RegistrationFormTests extends TestBase {

    TestData testData = new TestData();

    @Test
    @DisplayName("Успешная регистрация при заполнении всех полей")
    void positiveFillAllFormTest() {
        step("Открыть форму регистрации", () ->
                registrationFormPage.openPage());
        step("Закрыть баннер", () ->
                registrationFormPage.closeBanner());
        step("Заполнить форму регистрации", () -> {
            registrationFormPage
                .enterFirstName(testData.firstName)
                .enterLastName(testData.lastName)
                .enterUserEmail(testData.email)
                .chooseGender(testData.gender)
                .enterUserNumber(testData.number)
                .setDateOfBirth(testData.birthDate, testData.monthBirthDate, testData.yearBirthDate)
                .selectSubject(testData.subject)
                .selectHobby(testData.hobby)
                .uploadPicture(testData.fileName)
                .enterCurrentAddress(testData.address)
                .selectStateAndCity(testData.state, testData.city);
        });
        step("Нажать Submit", () ->
                registrationFormPage.submitForm());
        step("Проверить успешность регистрации", () -> {
            step("Проверить, что регистрация прошла успешно", () ->
                registrationFormPage.assertSuccessSubmission());
            step("Проверить корректность зарегистрированных данных", () -> {
                registrationFormPage
                        .checkSubmittedData("Student Name", testData.firstName + " " + testData.lastName)
                        .checkSubmittedData("Student Email", testData.email)
                        .checkSubmittedData("Gender", testData.gender)
                        .checkSubmittedData("Mobile", testData.number)
                        .checkSubmittedData("Date of Birth", testData.birthDate + " " + testData.monthBirthDate.substring(0, 3) + " " + testData.yearBirthDate)
                        .checkSubmittedData("Subjects", testData.subject)
                        .checkSubmittedData("Hobbies", testData.hobby)
                        .checkSubmittedData("Picture", testData.fileName)
                        .checkSubmittedData("Address", testData.address)
                        .checkSubmittedData("State and City", testData.state + " " + testData.city);
            });
        });
    };

    @Test
    @DisplayName("Успешная регистрация при заполнении только обязательных полей")
    void positiveFillRequiredFormTest() {
        step("Открыть форму регистрации", () ->
                registrationFormPage.openPage());
        step("Закрыть баннер", () ->
                registrationFormPage.closeBanner());
        step("Заполнить форму регистрации", () -> {
            registrationFormPage
                    .enterFirstName(testData.firstName)
                    .enterLastName(testData.lastName)
                    .chooseGender(testData.gender)
                    .enterUserNumber(testData.number);
        });
        step("Нажать Submit", () ->
                registrationFormPage.submitForm());
        step("Проверить успешность регистрации", () -> {
            step("Проверить, что регистрация прошла успешно", () ->
                    registrationFormPage.assertSuccessSubmission());
            step("Проверить корректность зарегистрированных данных", () -> {
                registrationFormPage
                        .checkSubmittedData("Student Name", testData.firstName + " " + testData.lastName)
                        .checkSubmittedData("Student Email", "-")
                        .checkSubmittedData("Gender", testData.gender)
                        .checkSubmittedData("Mobile", testData.number)
                        .checkSubmittedData("Date of Birth", "-")
                        .checkSubmittedData("Subjects", "-")
                        .checkSubmittedData("Hobbies", "-")
                        .checkSubmittedData("Picture", "-")
                        .checkSubmittedData("Address", "-")
                        .checkSubmittedData("State and City", "-");
            });
        });
    }

    @Test
    @DisplayName("Пример проваленного теста с не успешной регистрацией")
    void failedFillRequiredFormTest() {
        step("Открыть форму регистрации", () ->
                registrationFormPage.openPage());
        step("Закрыть баннер", () ->
                registrationFormPage.closeBanner());
        step("Заполнить форму регистрации", () -> {
            registrationFormPage
                    .enterFirstName(testData.firstName)
                    .enterLastName(testData.lastName)
                    .chooseGender(testData.gender)
                    .enterUserNumber(testData.number);
        });
        step("Нажать Submit", () ->
                registrationFormPage.submitForm());
        step("Проверить успешность регистрации", () -> {
            step("Проверить, что регистрация прошла успешно", () ->
                    registrationFormPage.assertSuccessSubmission());
            step("Проверить корректность зарегистрированных данных", () -> {
                registrationFormPage
                        .checkSubmittedData("Student Name", testData.firstName + " " + testData.lastName)
                        .checkSubmittedData("Student Email", "-")
                        .checkSubmittedData("Gender", testData.gender)
                        .checkSubmittedData("Mobile", "-")
                        .checkSubmittedData("Date of Birth", "-")
                        .checkSubmittedData("Subjects", "-")
                        .checkSubmittedData("Hobbies", "-")
                        .checkSubmittedData("Picture", "-")
                        .checkSubmittedData("Address", "-")
                        .checkSubmittedData("State and City", "-");
            });
        });
    }

    @Test
    @DisplayName("Не успешная регистрация при пустой форме")
    void negativeEmptyFormTest() {
        step("Открыть форму регистрации", () ->
                registrationFormPage.openPage());
        step("Закрыть баннер", () ->
                registrationFormPage.closeBanner());
        step("Нажать Submit", () ->
                registrationFormPage.submitForm());
        step("Проверить не успешность регистрации", () ->
                registrationFormPage.assertFailSubmission());
    }

    @Test
    @DisplayName("Не успешная регистрация при незаполненном имени")
    void negativeNotAllRequiredFistNameTest() {
        step("Открыть форму регистрации", () ->
                registrationFormPage.openPage());
        step("Закрыть баннер", () ->
                registrationFormPage.closeBanner());
        step("Заполнить форму регистрации", () -> {
            registrationFormPage
                    .enterLastName(testData.lastName)
                    .chooseGender(testData.gender)
                    .enterUserNumber(testData.number);
        });
        step("Нажать Submit", () ->
                registrationFormPage.submitForm());
        step("Проверить не успешность регистрации", () ->
                registrationFormPage.assertFailSubmission());
    }

    @Test
    @DisplayName("Не успешная регистрация при незаполненной фамилии")
    void negativeNotAllRequiredLastNameTest() {
        step("Открыть форму регистрации", () ->
                registrationFormPage.openPage());
        step("Закрыть баннер", () ->
                registrationFormPage.closeBanner());
        step("Заполнить форму регистрации", () -> {
            registrationFormPage
                    .enterLastName(testData.firstName)
                    .chooseGender(testData.gender)
                    .enterUserNumber(testData.number);
        });
        step("Нажать Submit", () ->
                registrationFormPage.submitForm());
        step("Проверить не успешность регистрации", () ->
                registrationFormPage.assertFailSubmission());
    }

    @Test
    @DisplayName("Не успешная регистрация при невыбранном поле")
    void negativeNotAllRequiredGenderTest() {
        step("Открыть форму регистрации", () ->
                registrationFormPage.openPage());
        step("Закрыть баннер", () ->
                registrationFormPage.closeBanner());
        step("Заполнить форму регистрации", () -> {
            registrationFormPage
                    .enterLastName(testData.firstName)
                    .enterLastName(testData.lastName)
                    .enterUserNumber(testData.number);
        });
        step("Нажать Submit", () ->
                registrationFormPage.submitForm());
        step("Проверить не успешность регистрации", () ->
                registrationFormPage.assertFailSubmission());
    }

    @Test
    @DisplayName("Не успешная регистрация при незаполненном номера телефона")
    void negativeNotAllRequiredUserNumberTest() {
        step("Открыть форму регистрации", () ->
                registrationFormPage.openPage());
        step("Закрыть баннер", () ->
                registrationFormPage.closeBanner());
        step("Заполнить форму регистрации", () -> {
            registrationFormPage
                    .enterLastName(testData.firstName)
                    .enterLastName(testData.lastName)
                    .enterUserNumber(testData.gender);
        });
        step("Нажать Submit", () ->
                registrationFormPage.submitForm());
        step("Проверить не успешность регистрации", () ->
                registrationFormPage.assertFailSubmission());
    }

    @Test
    @DisplayName("Не успешная регистрация при некорректном номере телефона")
    void negativeInvalidUserNumberTest() {
        step("Открыть форму регистрации", () ->
                registrationFormPage.openPage());
        step("Закрыть баннер", () ->
                registrationFormPage.closeBanner());
        step("Заполнить форму регистрации", () -> {
            registrationFormPage
                    .enterLastName(testData.firstName)
                    .enterLastName(testData.lastName)
                    .enterUserNumber(testData.gender)
                    .enterUserNumber(testData.invalidNumber);
        });
        step("Нажать Submit", () ->
                registrationFormPage.submitForm());
        step("Проверить не успешность регистрации", () ->
                registrationFormPage.assertFailSubmission());
    }

}
