package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Locale;

public class TextBoxTestsWithFaker extends TestBase{
    Faker faker = new Faker();
    Faker fakerRu = new Faker(new Locale("ru"));
    String userName = fakerRu.name().fullName();
    String userEmail = faker.internet().emailAddress();
    String currentAddress = fakerRu.address().fullAddress();
    String permanentAddress = fakerRu.address().fullAddress();
    String userInvalidEmail = "anna1_89mail.ru";

    @Test
    void positiveFillAllFormTest() {

        textBoxPage
                .openPage()
                .enterUserName(userName)
                .enterUserEmail(userEmail)
                .enterCurrentAddress(currentAddress)
                .enterPermanentAddress(permanentAddress)
                .submitForm()

                .assertSuccessSubmission("name", userName)
                .assertSuccessSubmission("email", userEmail)
                .assertSuccessSubmission("currentAddress", currentAddress)
                .assertSuccessSubmission("permanentAddress", permanentAddress);
    }

    @Test
    void positiveFillRequiredFormTest() {
        textBoxPage
                .openPage()
                .enterUserName(userName)
                .enterUserEmail(userEmail)
                .enterCurrentAddress(currentAddress)
                .submitForm()

                .assertSuccessSubmission("name", userName)
                .assertSuccessSubmission("email", userEmail)
                .assertSuccessSubmission("currentAddress", currentAddress);
    }

    @Test
    void negativeInvalidEmailTest() {
        textBoxPage
                .openPage()
                .enterUserName(userName)
                .enterUserEmail(userInvalidEmail)
                .enterCurrentAddress(currentAddress)
                .enterPermanentAddress(permanentAddress)
                .submitForm()

                .assertFailSubmission();
    }

}
