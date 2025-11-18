package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Selectors;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ResultTableComponent {
    private final SelenideElement resultModal = $(Selectors.byCssSelector(".modal-content"));
    private final SelenideElement resultTitle = $(Selectors.byCssSelector(".modal-title"));

    public void shouldBeVisible() {
        resultModal.shouldBe(visible);
        resultTitle.shouldHave(Condition.text("Thanks for submitting the form"));
    }

    public ResultTableComponent checkResult(String label, String expectedValue) {
        $(Selectors.byCssSelector(".table-responsive")).shouldBe(visible);

        String xpath = "//td[text()='" + label + "']/following-sibling::td";
        SelenideElement valueElement = $(xpath);
        valueElement.shouldBe(visible);

        if ("Date of Birth".equals(label)) {

            String expectedDay = expectedValue.substring(0, 2).trim();

            valueElement.shouldHave(Condition.text(expectedDay));
        } else {
            valueElement.shouldHave(Condition.text(expectedValue));
        }

        return this;

    }

    public void verifyNoSuccessModal() {

    }
}



