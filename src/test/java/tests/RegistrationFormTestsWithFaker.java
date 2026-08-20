package tests;

import data.TestDataWithFaker;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static data.TestDataWithFaker.*;

@Disabled
public class RegistrationFormTestsWithFaker extends TestBase {

    TestDataWithFaker testData = new TestDataWithFaker();

    @Test
    void positiveFillAllFormTest() {
        registrationFormPage
                .openPage()
                .closeBanner()
                .enterFirstName(testData.firstNameT)
                .enterLastName(testData.lastNameT)
                .enterUserEmail(testData.emailT)
                .chooseGender(testData.genderT)
                .enterUserNumber(testData.numberT)
                .setDateOfBirth(testData.birthDateT, testData.monthBirthDateT, testData.yearBirthDateT)
                .selectSubject(testData.subjectT)
                .selectHobby(testData.hobbyT)
                .uploadPicture(testData.fileNameT)
                .enterCurrentAddress(testData.addressT)
                .selectStateAndCity(testData.stateT, testData.cityT)
                .submitForm()

                .assertSuccessSubmission()
                .checkSubmittedData("Student Name", testData.firstNameT + " " + testData.lastNameT)
                .checkSubmittedData("Student Email", testData.emailT)
                .checkSubmittedData("Gender", testData.genderT)
                .checkSubmittedData("Mobile", testData.numberT)
                .checkSubmittedData("Date of Birth", testData.birthDateT + " " +  testData.monthBirthDateT.substring(0,3) + " " + testData.yearBirthDateT)
                .checkSubmittedData("Subjects", testData.subjectT)
                .checkSubmittedData("Hobbies", testData.hobbyT)
                .checkSubmittedData("Picture", fileNameT)
                .checkSubmittedData("Address", testData.addressT)
                .checkSubmittedData("State and City", testData.stateT + " " + testData.cityT);
    }

    @Test
    void positiveFillRequiredFormTest() {
        registrationFormPage
                .openPage()
                .closeBanner()
                .enterFirstName(testData.firstNameT)
                .enterLastName(testData.lastNameT)
                .chooseGender(testData.genderT)
                .enterUserNumber(testData.numberT)
                .submitForm()

                .assertSuccessSubmission()
                .checkSubmittedData("Student Name", testData.firstNameT + " " + testData.lastNameT)
                .checkSubmittedData("Student Email", "-")
                .checkSubmittedData("Gender", testData.genderT)
                .checkSubmittedData("Mobile", testData.numberT)
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
                .enterLastName(testData.lastNameT)
                .chooseGender(testData.genderT)
                .enterUserNumber(testData.numberT)
                .submitForm()

                .assertFailSubmission();
    }

    @Test
    void negativeNotAllRequiredLastNameTest() {
        registrationFormPage
                .openPage()
                .closeBanner()
                .enterLastName(testData.firstNameT)
                .chooseGender(testData.genderT)
                .enterUserNumber(testData.numberT)
                .submitForm()

                .assertFailSubmission();
    }

    @Test
    void negativeNotAllRequiredGenderTest() {
        registrationFormPage
                .openPage()
                .closeBanner()
                .enterLastName(testData.firstNameT)
                .enterLastName(testData.lastNameT)
                .enterUserNumber(testData.numberT)
                .submitForm()

                .assertFailSubmission();
    }

    @Test
    void negativeNotAllRequiredUserNumberTest() {
        registrationFormPage
                .openPage()
                .closeBanner()
                .enterFirstName(testData.firstNameT)
                .enterLastName(testData.lastNameT)
                .chooseGender(testData.genderT)
                .submitForm()

                .assertFailSubmission();
    }

    @Test
    void negativeInvalidUserNumberTest() {
        registrationFormPage
                .openPage()
                .closeBanner()
                .enterFirstName(testData.firstNameT)
                .enterLastName(testData.lastNameT)
                .chooseGender(testData.genderT)
                .enterUserNumber(testData.invalidNumberT)
                .submitForm()

                .assertFailSubmission();
    }

}
