package pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.text;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import components.CalendarComponent;
import components.ResultTableComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {
    private final SelenideElement
            firstNameInput = $("#userName-wrapper").$("input[id*='firstName']"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber-wrapper").$("input[id*='userNumber']"),
            calendarInput = $("#dateOfBirth-wrapper").$("input[id*='dateOfBirth']"),
            subjectsInput = $("#subjectsWrapper").$("input"),
            hobbiesCheckbox = $("#hobbiesWrapper").$("label[for*='hobbies-checkbox-3']"),
            uploadPictureInput = $("#uploadPicture"),
            currentAddress = $("#currentAddress-wrapper").$("textarea"),
            stateDropdown = $("#stateCity-wrapper").$(byText("Select State")),
            cityDropdown = $("#stateCity-wrapper").$(byText("Select City")),
            submitButton = $("#submit");


    CalendarComponent calendarComponent=new CalendarComponent();
    ResultTableComponent resultTable = new ResultTableComponent();


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

    public PracticeFormPage setGender() {
        genderWrapper.$(byText("Male")).shouldBe(visible).click();
        $("#genterWrapper input[value='Male']").shouldBe(Condition.checked, Duration.ofSeconds(10));
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
        currentAddress.shouldBe(visible).setValue(value);
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

    public void submit() {

        $("#submit").scrollIntoView(true);


        Selenide.sleep(300);

        executeJavaScript("arguments[0].click();", $("#submit"));


        Selenide.sleep(500);

    }



    public void verifySuccess() {
        $(".modal-content").shouldBe(visible, Duration.ofSeconds(10));
        $$(".table-responsive td").get(1).shouldHave(text("Ivan Egorov"));
        $$(".table-responsive td").get(3).shouldHave(text("Ivan@egorov.com"));
        $$(".table-responsive td").get(5).shouldHave(text("Male"));
        $$(".table-responsive td").get(7).shouldHave(text("Biology"));
        $$(".table-responsive td").get(9).shouldHave(text("Music"));
        $$(".table-responsive td").get(11).shouldHave(text("test-image.jpg"));
        $$(".table-responsive td").get(13).shouldHave(text("Moscow"));
        $$(".table-responsive td").get(15).shouldHave(text("NCR Delhi"));
    }

    public PracticeFormPage checkResult(String fieldName, String expectedValue) {
        $("table-responsive").$(byText(fieldName))
                .shouldHave(text(expectedValue));
        return this;
    }


    public void verifyMinimalDataSuccess() {

        $(".modal-content").shouldBe(visible, Duration.ofSeconds(10));

        $(".table-responsive").shouldHave(
                text("Test User"),      // Student Name
                text("test@example.com"), // Email
                text("Male"),           // Gender
                text("1234567890")      // Mobile
        );

        $(".table-responsive").shouldNotHave(
                text("Moscow"),         // Current Address
                text("Biology"),        // Subjects
                text("Music")           // Hobbies
        );
    }
    public ResultTableComponent getResultTable() {
        return new ResultTableComponent();
    }
}
