package components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ResultTable {
    private final SelenideElement
            modal = $(".modal-dialog"),
            title = $("#example-modal-sizes-title-lg"),
            table = $(".table");

    public ResultTable checkModalAppears() {
        modal.should(appear);
        title.shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    public ResultTable checkModalDoesNotAppear() {
        modal.shouldNot(appear);
        return this;
    }

    public void checkFieldValue(String fieldName, String expectedValue) {
        table.shouldHave(text(fieldName), text(expectedValue));
    }

    public ResultTable checkAllFields(String[][] expectedData) {
        for (String[] field : expectedData) {
            checkFieldValue(field[0], field[1]);
        }
        return this;
    }
}
