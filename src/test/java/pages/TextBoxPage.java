package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.visible;

public class TextBoxPage {
    //Elements
    private final String pageAddress = "/text-box.html";
    private final SelenideElement userNameField = $("#userName");
    private final SelenideElement userEmailField = $("#userEmail");
    private final SelenideElement currentAddressField = $("#currentAddress");
    private final SelenideElement permanentAddressField = $("#permanentAddress");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement outputResults = $("#output");

    //Actions
    public void openPage() {
        open(pageAddress);
    }

    public void enterUserName(String value) {
        userNameField.setValue(value);
    }

    public void enterUserEmail(String value) {
        userEmailField.setValue(value);
    }

    public void enterCurrentAddress(String value) {
        currentAddressField.setValue(value);
    }

    public void enterPermanentAddress(String value) {
        permanentAddressField.setValue(value);
    }

    public void submitForm() {
        submitButton.click();
    }

    public void assertSuccessSubmission(String key, String value) {
        outputResults.$(byId(key)).shouldHave(text(value));
    }

    public void assertFailSubmission() {
        outputResults.shouldNotBe(visible);
    }
}
