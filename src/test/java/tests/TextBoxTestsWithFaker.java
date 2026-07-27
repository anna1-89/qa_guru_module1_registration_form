package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static utils.RandomUtils.*;

public class TextBoxTestsWithFaker extends TestBase{

    @Test
    void positiveFillAllFormTest() {
        Faker faker = new Faker();
        Faker fakerRu = new Faker(new Locale("ru"));
        String userName = fakerRu.name().fullName();
        String userEmail = faker.internet().emailAddress();
        String currentAddress = fakerRu.address().fullAddress();
        String permanentAddress = fakerRu.address().fullAddress();
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
        String userName = getRandomString(10);
        String userEmail = getRandomEmail();
        String currentAddress = getRandomString(100);
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
        String userName = getRandomString(10);
        String userInvalidEmail = getRandomInvalidEmail();
        String currentAddress = getRandomString(100);
        String permanentAddress = getRandomString(90);;
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
