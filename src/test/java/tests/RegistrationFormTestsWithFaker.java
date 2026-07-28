package tests;

import data.TestDataWithFaker;
import org.junit.jupiter.api.Test;

import static data.TestDataWithFaker.*;

public class RegistrationFormTestsWithFaker extends TestBase {

    TestDataWithFaker testData = new TestDataWithFaker();

    @Test
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
                .checkSubmittedData("Picture", fileName)
                .checkSubmittedData("Address", testData.address)
                .checkSubmittedData("State and City", testData.state + " " + testData.city);
    }

    @Test
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
    void negativeEmptyFormTest() {
        registrationFormPage
                .openPage()
                .closeBanner()
                .submitForm()

                .assertFailSubmission();
    }

    @Test
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
