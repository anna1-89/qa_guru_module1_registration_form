package tests;

import org.junit.jupiter.api.Test;

public class TextBoxTests extends TestBase{
    String userName = "Anna Pilugina";
    String userEmail = "anna1_89@mail.ru";
    String currentAddress = "Zarelye";
    String permanentAddress = "Velikiy Novgorod";
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
