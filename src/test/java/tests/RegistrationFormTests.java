package tests;

import data.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegistrationFormTests extends TestBase {

    TestData testData = new TestData();

    @Test
    @DisplayName("Успешная регистрация при заполнении всех полей")
    void positiveFillAllFormTest() {
        registrationFormPage
                .openPage()
                .closeBanner()
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
                .selectStateAndCity(testData.state, testData.city)
                .submitForm()

                .assertSuccessSubmission()
                .checkSubmittedData("Student Name", testData.firstName + " " + testData.lastName)
                .checkSubmittedData("Student Email", testData.email)
                .checkSubmittedData("Gender", testData.gender)
                .checkSubmittedData("Mobile", testData.number)
                .checkSubmittedData("Date of Birth", testData.birthDate + " " +  testData.monthBirthDate.substring(0,3) + " " + testData.yearBirthDate)
                .checkSubmittedData("Subjects", testData.subject)
                .checkSubmittedData("Hobbies", testData.hobby)
                .checkSubmittedData("Picture", testData.fileName)
                .checkSubmittedData("Address", testData.address)
                .checkSubmittedData("State and City", testData.state + " " + testData.city);
    }

    @Test
    @DisplayName("Успешная регистрация при заполнении только обязательных полей")
    void positiveFillRequiredFormTest() {
        registrationFormPage
                .openPage()
                .closeBanner()
                .enterFirstName(testData.firstName)
                .enterLastName(testData.lastName)
                .chooseGender(testData.gender)
                .enterUserNumber(testData.number)
                .submitForm()

                .assertSuccessSubmission()
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
    }

    @Test
    @DisplayName("Неуспешная регистрация при пустой форме")
    void negativeEmptyFormTest() {
        registrationFormPage
                .openPage()
                .closeBanner()
                .submitForm()

                .assertFailSubmission();
    }

    @Test
    @DisplayName("Неуспешная регистрация при незаполненном имени")
    void negativeNotAllRequiredFistNameTest() {
        registrationFormPage
                .openPage()
                .closeBanner()
                .enterLastName(testData.lastName)
                .chooseGender(testData.gender)
                .enterUserNumber(testData.number)
                .submitForm()

                .assertFailSubmission();
    }

    @Test
    @DisplayName("Неуспешная регистрация при незаполненной фамилии")
    void negativeNotAllRequiredLastNameTest() {
        registrationFormPage
                .openPage()
                .closeBanner()
                .enterLastName(testData.firstName)
                .chooseGender(testData.gender)
                .enterUserNumber(testData.number)
                .submitForm()

                .assertFailSubmission();
    }

    @Test
    @DisplayName("Неуспешная регистрация при невыбранном поле")
    void negativeNotAllRequiredGenderTest() {
        registrationFormPage
                .openPage()
                .closeBanner()
                .enterLastName(testData.firstName)
                .enterLastName(testData.lastName)
                .enterUserNumber(testData.number)
                .submitForm()

                .assertFailSubmission();
    }

    @Test
    @DisplayName("Неуспешная регистрация при незаполненном номера телефона")
    void negativeNotAllRequiredUserNumberTest() {
        registrationFormPage
                .openPage()
                .closeBanner()
                .enterFirstName(testData.firstName)
                .enterLastName(testData.lastName)
                .chooseGender(testData.gender)
                .submitForm()

                .assertFailSubmission();
    }

    @Test
    @DisplayName("Неуспешная регистрация при некорректном номере телефона")
    void negativeInvalidUserNumberTest() {
        registrationFormPage
                .openPage()
                .closeBanner()
                .enterFirstName(testData.firstName)
                .enterLastName(testData.lastName)
                .chooseGender(testData.gender)
                .enterUserNumber(testData.invalidNumber)
                .submitForm()

                .assertFailSubmission();
    }

}
