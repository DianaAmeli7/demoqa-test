package tests;

import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.Selenide;
import components.ResultTableComponent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;


public class PracticeFormTest {
    private final PracticeFormPage practiceFormPage = new PracticeFormPage();
    private final ResultTableComponent resultTable = new ResultTableComponent();


    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.headless = false;
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = 15000;
    }

    @Test
    void successfulPracticeFormTest() {
        new PracticeFormPage()
                .openPage()
                .setFirstName("Ivan")
                .setLastName("Egorov")
                .setUserEmail("Ivan@egorov.com")
                .setGender()
                .setUserNumber("7910535102")
                .setDateOfBirth("07", "November", "2001")
                .setSubjects("Biology")
                .setHobbies("Music")
                .uploadPicture("test-image.jpg")
                .setAddress("Moscow")
                .setState("NCR")
                .setCity("Delhi")
                .submit();

    }

    @Test
    void minimalRequiredDataTest() {
        PracticeFormPage form = new PracticeFormPage();

        form    .openPage()
                .setFirstName("Test")
                .setLastName("User")
                .setUserEmail("test@example.com")
                .setGender()
                .setUserNumber("1234567890");

        form.submit();
        form.verifyMinimalDataSuccess();
    }

    @Test
    void negativeValidationTest() {
        new PracticeFormPage()
                .openPage()
                .setFirstName("Ivan")
                .setLastName("Egorov")
                .setUserEmail("invalid-email")
                .setGender()
                .setUserNumber("123")
                .submit();

        new PracticeFormPage().getResultTable()
                .verifyNoSuccessModal();
    }
}