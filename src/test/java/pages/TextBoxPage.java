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
    public TextBoxPage openPage() {
        open(pageAddress);
        return this;
    }

    public TextBoxPage enterUserName(String value) {
        userNameField.setValue(value);
        return this;
    }

    public TextBoxPage enterUserEmail(String value) {
        userEmailField.setValue(value);
        return this;
    }

    public TextBoxPage enterCurrentAddress(String value) {
        currentAddressField.setValue(value);
        return this;
    }

    public TextBoxPage enterPermanentAddress(String value) {
        permanentAddressField.setValue(value);
        return this;
    }

    public TextBoxPage submitForm() {
        submitButton.click();
        return this;
    }

    public TextBoxPage assertSuccessSubmission(String key, String value) {
        outputResults.$(byId(key)).shouldHave(text(value));
        return this;
    }

    public TextBoxPage assertFailSubmission() {
        outputResults.shouldNotBe(visible);
        return this;
    }
}
