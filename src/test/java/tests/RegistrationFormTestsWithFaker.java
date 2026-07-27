package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static utils.RandomUtils.*;

public class RegistrationFormTestsWithFaker extends TestBase {
    public static String firstName;
    public static String lastName;
    public static String email;
    public static String gender;
    public static String number;
    public static String invalidNumber;
    public static String monthBirthDate;
    public static String yearBirthDate;
    public static String birthDate;
    public static String subject = "Computer Science";
    public static String hobby = "Reading";
    public static String fileName = "01.jpg";
    public static String address;
    public static String state = "Haryana";
    public static String city = "Karnal";

    @BeforeEach
    void prepareRandomData() {
        Faker faker = new Faker();
        Faker fakerRu = new Faker(new Locale("ru"));
        firstName = fakerRu.name().firstName();
        lastName = fakerRu.name().lastName();
        email = faker.internet().emailAddress();
        gender = getRandomGender();
        number = String.valueOf(faker.number().randomNumber(10,true));
        invalidNumber = String.valueOf(faker.number().randomNumber(9,false));;
        monthBirthDate = getRandomMonthOfBirth();
        yearBirthDate = String.valueOf(getRandomInt(1989, 2010));
        birthDate = String.valueOf(faker.number().numberBetween(10,30));
        address = fakerRu.address().fullAddress();
    }

    @Test
    void positiveFillAllFormTest() {
        registrationFormPage
                .openPage()
                .closeBanner()
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
                .closeBanner()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .chooseGender(gender)
                .enterUserNumber(number)
                .submitForm()

                .assertSuccessSubmission()
                .checkSubmittedData("Student Name", firstName + " " + lastName)
                .checkSubmittedData("Student Email", "-")
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
                .closeBanner()
                .submitForm()

                .assertFailSubmission();
    }

    @Test
    void negativeNotAllRequiredFistNameTest() {
        registrationFormPage
                .openPage()
                .closeBanner()
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
                .closeBanner()
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
                .closeBanner()
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
                .closeBanner()
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
                .closeBanner()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .chooseGender(gender)
                .enterUserNumber(invalidNumber)
                .submitForm()

                .assertFailSubmission();
    }

}
