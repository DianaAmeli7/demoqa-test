package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class TextBoxTest {
    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = 10000;
    }

    @Test
    void successfulTextBoxTestWithAllFields() {
        textBoxPage.openPage()
                .setFullName("Sveta Lomakina")
                .setEmail("lomakina24@mail.ru")
                .setCurrentAddress("г.Оренбург")
                .setPermanentAddress("г.Москва")
                .submit()
                .getOutput()
                .checkAllData(
                        "Sveta Lomakina",
                        "lomakina24@mail.ru",
                        "г.Оренбург",
                        "г.Москва"
                );
    }

    @Test
    void textBoxTestWithMinimumData() {
        textBoxPage.openPage()
                .setFullName("Sveta Lomakina")
                .setEmail("lomakina24@mail.ru")
                .submit()
                .getOutput()
                .checkAllData(
                        "Sveta Lomakina",
                        "lomakina24@mail.ru",
                        "",
                        ""
                );
    }

    @Test
    void textBoxNegativeTestWithInvalidEmail() {
        textBoxPage.openPage()
                .setFullName("Sveta Lomakina")
                .setEmail("invalid-email")
                .setCurrentAddress("г.Оренбург")
                .submit()
                .getOutput()
                .checkOutputVisible();
    }
}