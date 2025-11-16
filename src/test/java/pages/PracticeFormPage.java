package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class PracticeFormPage {

    private final SelenideElement firstNameInput = $("#FirstName"),
            lastNameInput = $("#LastName"),
            userEmailInput = $("UserEmail"),
            genderWrapper =$("#genderWrapper"),
            userNumberInput =$("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            hobbiesCheckbox = $("label[for='hobbies-checkbox-3']"),
            uploadPicture = $("#uploadPicture"),
            currentAddress = $("#currentAddress"),
            stateDropdown = $("#state"),
            cityDropdown = $("#city"),
            submitButton = $("#submit");


    public PracticeFormPage openPage() {
        open("/automation-practice-form");

        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public PracticeFormPage setUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public PracticeFormPage setGenderWrapper(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public PracticeFormPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public PracticeFormPage setDateOfBirth(String day, String month, String year ) {
        $("#dateOfBirthInput").click();


        return this;
    }

    public PracticeFormPage setSubjects(String subject) {
    subjectsInput.setValue(subject).pressEnter();

    return this;
   }

    public PracticeFormPage setHobbies(String Music) {
        hobbiesCheckbox.click();
        return this;
    }

    public PracticeFormPage uploadFile(String fileName) {
        uploadPicture.uploadFromClasspath(fileName);
        return this;
    }

    public PracticeFormPage setAddress(String address) {
        currentAddress.setValue(address);
        return this;
    }

    public PracticeFormPage setState(String state) {
        stateDropdown.scrollIntoView(true).click();
        $(String.format("#react-select-3-option-%s", state)).click();
        return this;
    }

    public PracticeFormPage setCity(String city) {
        cityDropdown.scrollIntoView(true).click();
        $(String.format("#react-select-4-option-%s", city)).click();
        return this;
    }

    public void submit() {
        submitButton.click();
    }

    public PracticeFormPage checkResult(String fieldName, String expectedValue) {
        $("table-responsive").$(byText(fieldName))
        .shouldHave(text(expectedValue));
        return this;
    }
}