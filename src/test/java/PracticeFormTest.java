import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 10000;
    }

    @Test
    public void fillAllFieldsInForm() {
        open("/automation-practice-form");

        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Egorov");
        $("#userEmail").setValue("Ivan@egorov.com");
        $("label[for='gender-radio-1']").click();
        $("#userNumber").setValue("7910535102");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("2001");
        $$(".react-datepicker__day").findBy(text("7")).click();

        $("#subjectsInput").setValue("Biology").pressEnter();
        $("label[for='hobbies-checkbox-3']").click();
        $("#uploadPicture").uploadFromClasspath("test-image.jpg");
        $("#currentAddress").setValue("Moscow");

        $("#state").scrollIntoView(true).click();
        $("#react-select-3-option-0").click();

        $("#city").scrollIntoView(true).click();
        $("#react-select-4-option-0").click();

        $("#submit").click();

        $(".table").shouldHave(
                text("Student Name"), text("Ivan Egorov"),
                text("Student Email"), text("Ivan@egorov.com"),
                text("Gender"), text("Male"),
                text("Mobile"), text("7910535102"),
                text("Date of Birth"), text("07 November,2001"),
                text("Subjects"), text("Biology"),
                text("Hobbies"), text("Music"),
                text("Picture"), text("test-image.jpg"),
                text("Address"), text("Moscow"),
                text("State and City"), text("NCR Delhi")
        );
     }
}