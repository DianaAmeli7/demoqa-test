package components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class OutputComponent {
    private final SelenideElement outputBlock = $("#output");

    public void checkOutputVisible() {
        outputBlock.shouldBe(visible);
    }

    public OutputComponent checkOutputNotVisible() {
        outputBlock.shouldNotBe(visible);
        return this;
    }

    public void checkName(String expectedName) {
        outputBlock.shouldHave(text("Name:" + expectedName));
    }

    public void checkEmail(String expectedEmail) {
        outputBlock.shouldHave(text("Email:" + expectedEmail));
    }

    public void checkCurrentAddress(String expectedAddress) {
        outputBlock.shouldHave(text("Current Address :" + expectedAddress));
    }

    public void checkPermanentAddress(String expectedAddress) {
        outputBlock.shouldHave(text("Permanent Address :" + expectedAddress));
    }

    public void checkAllData(String name, String email, String currentAddress, String permanentAddress) {
        checkOutputVisible();
        if (!name.isEmpty()) checkName(name);
        if (!email.isEmpty()) checkEmail(email);
        if (!currentAddress.isEmpty()) checkCurrentAddress(currentAddress);
        if (!permanentAddress.isEmpty()) checkPermanentAddress(permanentAddress);
    }
}