package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    //Elements
    private final SelenideElement monthData = $(".react-datepicker__month-select");
    private final SelenideElement yearData = $(".react-datepicker__year-select");
    private final SelenideElement dateData = $(".react-datepicker__month");

    //Actions
    public void setDate(String day, String month, String year) {
        monthData.$(byText(month)).click();
        yearData.$(byText(year)).click();
        dateData.$(byText(day)).click();
    }

}
