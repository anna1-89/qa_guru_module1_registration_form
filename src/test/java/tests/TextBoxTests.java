package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class TextBoxTests extends TestBase{
    String userName = "Anna Pilugina";
    String userEmail = "anna1_89@mail.ru";
    String currentAddress = "Zarelye";
    String permanentAddress = "Velikiy Novgorod";
    String userInvalidEmail = "anna1_89mail.ru";

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void positiveFillAllFormTest() {
        textBoxPage.openPage();
        textBoxPage.enterUserName(userName);
        textBoxPage.enterUserEmail(userEmail);
        textBoxPage.enterCurrentAddress(currentAddress);
        textBoxPage.enterPermanentAddress(permanentAddress);
        textBoxPage.submitForm();

        textBoxPage.assertSuccessSubmission("name", userName);
        textBoxPage.assertSuccessSubmission("email", userEmail);
        textBoxPage.assertSuccessSubmission("currentAddress", currentAddress);
        textBoxPage.assertSuccessSubmission("permanentAddress", permanentAddress);
    }

    @Test
    void positiveFillRequiredFormTest() {
        textBoxPage.openPage();
        textBoxPage.enterUserName(userName);
        textBoxPage.enterUserEmail(userEmail);
        textBoxPage.enterCurrentAddress(currentAddress);
        textBoxPage.submitForm();

        textBoxPage.assertSuccessSubmission("name", userName);
        textBoxPage.assertSuccessSubmission("email", userEmail);
        textBoxPage.assertSuccessSubmission("currentAddress", currentAddress);
    }

    @Test
    void negativeInvalidEmailTest() {
        textBoxPage.openPage();
        textBoxPage.enterUserName(userName);
        textBoxPage.enterUserEmail(userInvalidEmail);
        textBoxPage.enterCurrentAddress(currentAddress);
        textBoxPage.enterPermanentAddress(permanentAddress);
        textBoxPage.submitForm();

        textBoxPage.assertFailSubmission();

    }

}
