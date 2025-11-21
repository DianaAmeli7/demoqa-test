package pages;

import com.codeborne.selenide.Condition;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import components.CalendarComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class PracticeFormPage {
    private final SelenideElement
    firstNameInput = $("#userName-wrapper").$("input[id*='firstName']"),
    lastNameInput = $("#lastName"),
    userEmailInput = $("#userEmail"),
    genderWrapper = $("#genterWrapper"),
    maleLabel = genderWrapper.$(byText("Male")),
    femaleLabel = genderWrapper.$(byText("Female")),
    otherLabel = genderWrapper.$(byText("Other")),
    maleInput = $("#genterWrapper input[value='Male']"),
    femaleInput = $("#genterWrapper input[value='Female']"),
    otherInput = $("#genterWrapper input[value='Other']"),
    userNumberInput = $("#userNumber-wrapper").$("input[id*='userNumber']"),
    calendarInput = $("#dateOfBirth-wrapper").$("input[id*='dateOfBirth']"),
    subjectsInput = $("#subjectsWrapper").$("input"),
    hobbiesCheckbox = $("#hobbiesWrapper").$("label[for*='hobbies-checkbox-3']"),
    uploadPictureInput = $("#uploadPicture"),
    currentAddress = $("#currentAddress-wrapper").$("textarea"),
    stateCityContainer = $("#stateCity-wrapper"),
    stateDropdown = $("#stateCity-wrapper").$(byText("Select State")),
    cityDropdown = $("#stateCity-wrapper").$(byText("Select City")),
    submitButton = $("#submit"),
    phoneField = $(byId("userNumber")),
    emailInputField = $(byId("userEmail")),
    modalDialog = $(".modal-dialog"),
    modalTitle = $("#example-modal-sizes-title-lg"),
    table = $(".table-responsive");


    CalendarComponent calendarComponent=new CalendarComponent();


    public PracticeFormPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").shouldBe(visible);
        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstNameInput.shouldBe(visible).setValue(value);

        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastNameInput.shouldBe(visible).setValue(value);

        return this;
    }

    public PracticeFormPage setUserEmail(String value) {
        userEmailInput.shouldBe(visible).setValue(value);

        return this;
    }

    public PracticeFormPage setGender(String gender) {
        String normalized = gender.trim().toLowerCase();
        switch (normalized) {
            case "male":
                maleLabel.click();
                maleInput.shouldBe(Condition.checked, Duration.ofSeconds(5));
                break;
            case "female":
                femaleLabel.click();
                femaleInput.shouldBe(Condition.checked, Duration.ofSeconds(5));
                break;
            case "other":
                otherLabel.click();
                otherInput.shouldBe(Condition.checked, Duration.ofSeconds(5));
                break;
            default:
                throw new IllegalArgumentException("Unsupported gender: " + gender);
        }
        return this;
    }


    public PracticeFormPage setUserNumber(String value) {
        userNumberInput.shouldBe(visible).setValue(value);

        return this;
    }

    public PracticeFormPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        new CalendarComponent().setDate(day, month, year);

        return this;
    }

    public PracticeFormPage setSubjects(String value) {
        subjectsInput.shouldBe(visible).setValue(value);
        subjectsInput.pressEnter();
        return this;
    }

    public PracticeFormPage setHobbies(String value) {
        hobbiesCheckbox.shouldBe(visible).click();
        return this;
    }

    public PracticeFormPage uploadPicture(String fileName) {
        uploadPictureInput.uploadFromClasspath(fileName);
        return this;
    }

    public PracticeFormPage setAddress(String value) {
        currentAddress
                .shouldBe(Condition.visible, Duration.ofSeconds(5))
                .setValue(value);

        stateCityContainer.scrollTo();
        return this;
    }

    public PracticeFormPage setState(String state) {
        stateDropdown.click();
        $(byText(state)).shouldBe(visible, Duration.ofSeconds(5)).click();
        return this;
    }

    public PracticeFormPage setCity(String city) {
        cityDropdown.click();
        $(byText(city)).shouldBe(visible, Duration.ofSeconds(5)).click();
        return this;
    }

    public PracticeFormPage submit() {
        submitButton
                .shouldBe(Condition.visible, Duration.ofSeconds(5))
                .scrollIntoView("{block: 'center'}")
                .shouldBe(Condition.interactable, Duration.ofSeconds(3))
                .click();

        return this;

    }

    public PracticeFormPage verifyFormSubmitted(String expectedTitle) {
        modalDialog.shouldBe(Condition.visible, Duration.ofSeconds(3));
        modalTitle.shouldHave(Condition.text(expectedTitle), Duration.ofSeconds(2));
        return this;
    }

    public void verifyTableContent(String... expectedContent) {

        for (String content : expectedContent) {
            table.shouldHave(Condition.text(content), Duration.ofSeconds(1));
        }
    }


    public PracticeFormPage verifyFormNotSubmitted() {
            return this;

        }
    public PracticeFormPage verifyPhoneFieldHasError() {

        Selenide.sleep(1000);

        String borderColor = phoneField.getCssValue("border-color");
        System.out.println("Цвет границы телефона: " + borderColor);


        if (!borderColor.contains("220, 53, 69") && !borderColor.contains("dc3545")) {
            throw new AssertionError("Нет красной границы у поля телефона! Цвет: " + borderColor);
        }

        return this;
    }

    public void verifyEmailFieldIsValid() {
                emailInputField.shouldNotHave(Condition.cssClass("is-invalid"), Duration.ofSeconds(2));

      }
    }

