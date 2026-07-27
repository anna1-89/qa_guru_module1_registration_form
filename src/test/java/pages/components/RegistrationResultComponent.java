package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationResultComponent {
    //Elements
    private final SelenideElement resultComponent = $("#resultModal");
    private final SelenideElement resultTable = $("#resultBody");
    private final SelenideElement resultComponentTitle = $("#example-modal-sizes-title-lg");

    public static String textSuccessRegistration = "Thanks for submitting the form";

    //Actions
    public RegistrationResultComponent checkSuccessSubmission() {
        resultComponent.shouldBe(visible);
        resultComponentTitle.shouldHave(text(textSuccessRegistration));
        return this;
    }

    public RegistrationResultComponent checkData(String key, String value) {
        assertEquals(resultTable.$(byText(key)).sibling(0).text(), value);
        return this;
    }
}
