package tests;

import org.junit.jupiter.api.Test;

import static data.TestData.*;

public class RegistrationFormTestsWithFaker extends TestBase {

    @Test
    void positiveFillAllFormTest() {
        registrationFormPage
                .openPage()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterUserEmail(email)
                .chooseGender(gender)
                .enterUserNumber(number)
                .setDateOfBirth(birthDate, monthBirthDate, yearBirthDate)
                .selectSubject(subject)
                .selectHobby(hobby)
                .uploadPicture(fileName)
                .enterCurrentAddress(address)
                .selectStateAndCity(state, city)
                .submitForm()

                .assertSuccessSubmission()
                .checkSubmittedData("Student Name", firstName + " " + lastName)
                .checkSubmittedData("Student Email", email)
                .checkSubmittedData("Gender", gender)
                .checkSubmittedData("Mobile", number)
                .checkSubmittedData("Date of Birth", birthDate + " " +  monthBirthDate.substring(0,3) + " " + yearBirthDate)
                .checkSubmittedData("Subjects", subject)
                .checkSubmittedData("Hobbies", hobby)
                .checkSubmittedData("Picture", fileName)
                .checkSubmittedData("Address", address)
                .checkSubmittedData("State and City", state + " " + city);
    }

    @Test
    void positiveFillRequiredFormTest() {
        registrationFormPage
                .openPage()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .chooseGender(gender)
                .enterUserNumber(number)
                .submitForm()

                .assertSuccessSubmission()
                .checkSubmittedData("Student Name", firstName + " " + lastName)
                .checkSubmittedData("Student Email", email)
                .checkSubmittedData("Gender", gender)
                .checkSubmittedData("Mobile", number)
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
                .submitForm()

                .assertFailSubmission();
    }

    @Test
    void negativeNotAllRequiredFistNameTest() {
        registrationFormPage
                .openPage()
                .enterLastName(lastName)
                .chooseGender(gender)
                .enterUserNumber(number)
                .submitForm()

                .assertFailSubmission();
    }

    @Test
    void negativeNotAllRequiredLastNameTest() {
        registrationFormPage
                .openPage()
                .enterLastName(firstName)
                .chooseGender(gender)
                .enterUserNumber(number)
                .submitForm()

                .assertFailSubmission();
    }

    @Test
    void negativeNotAllRequiredGenderTest() {
        registrationFormPage
                .openPage()
                .enterLastName(firstName)
                .enterLastName(lastName)
                .enterUserNumber(number)
                .submitForm()

                .assertFailSubmission();
    }

    @Test
    void negativeNotAllRequiredUserNumberTest() {
        registrationFormPage
                .openPage()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .chooseGender(gender)
                .submitForm()

                .assertFailSubmission();
    }

    @Test
    void negativeInvalidUserNumberTest() {
        registrationFormPage
                .openPage()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .chooseGender(gender)
                .enterUserNumber(invalidNumber)
                .submitForm()

                .assertFailSubmission();
    }

}
