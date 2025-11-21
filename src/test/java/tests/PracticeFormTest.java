package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;



public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.headless = false;
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = 15000;
    }

    private static final String FULL_FIRST_NAME = "Ivan";
    private static final String FULL_LAST_NAME = "Egorov";
    private static final String FULL_EMAIL = "ivan.egorov@example.com";
    private static final String FULL_GENDER = "Male";
    private static final String FULL_PHONE = "7910535102";
    private static final String FULL_DAY = "07";
    private static final String FULL_MONTH = "November";
    private static final String FULL_YEAR = "2001";
    private static final String FULL_SUBJECT = "Biology";
    private static final String FULL_HOBBY = "Music";
    private static final String FULL_PICTURE = "test-image.jpg";
    private static final String FULL_ADDRESS = "Moscow";
    private static final String FULL_STATE = "NCR";
    private static final String FULL_CITY = "Delhi";
    private static final String FULL_STUDENT_NAME = FULL_FIRST_NAME + " " + FULL_LAST_NAME;
    private static final String FULL_DATE = FULL_DAY + " " + FULL_MONTH + "," + FULL_YEAR;
    private static final String FULL_STATE_CITY = FULL_STATE + " " + FULL_CITY;
    private static final String MODAL_TITLE = "Thanks for submitting the form";

    @Test
    void successfulPracticeFormTest() {
        new PracticeFormPage()
                .openPage()
                .setFirstName(FULL_FIRST_NAME)
                .setLastName(FULL_LAST_NAME)
                .setUserEmail(FULL_EMAIL)
                .setGender(FULL_GENDER)
                .setUserNumber(FULL_PHONE)
                .setDateOfBirth(FULL_DAY, FULL_MONTH, FULL_YEAR)
                .setSubjects(FULL_SUBJECT)
                .setHobbies(FULL_HOBBY)
                .uploadPicture(FULL_PICTURE)
                .setAddress(FULL_ADDRESS)
                .setState(FULL_STATE)
                .setCity(FULL_CITY)
                .submit();

        new PracticeFormPage()
                .verifyFormSubmitted(MODAL_TITLE)
                .verifyTableContent(
                        "Student Name", FULL_STUDENT_NAME,
                        "Student Email", FULL_EMAIL,
                        "Gender", FULL_GENDER,
                        "Mobile", FULL_PHONE,
                        "Date of Birth", FULL_DATE,
                        "Subjects", FULL_SUBJECT,
                        "Hobbies", FULL_HOBBY,
                        "Picture", FULL_PICTURE,
                        "Address", FULL_ADDRESS,
                        "State and City", FULL_STATE_CITY
                );
    }

    private static final String MIN_FIRST_NAME = "Test";
    private static final String MIN_LAST_NAME = "User";
    private static final String MIN_EMAIL = "test@example.com";
    private static final String MIN_GENDER = "Other";
    private static final String MIN_PHONE = "1234567890"; // 10 цифр - корректный формат

    private static final String MODAL_TITLE_TEXT = "Thanks for submitting the form";
    private static final String TABLE_HEADER_NAME = "Student Name";
    private static final String TABLE_HEADER_EMAIL = "Student Email";
    private static final String TABLE_HEADER_GENDER = "Gender";
    private static final String TABLE_HEADER_MOBILE = "Mobile";
    private static final String EXPECTED_NAME = MIN_FIRST_NAME + " " + MIN_LAST_NAME;
    private static final String EXPECTED_EMAIL = MIN_EMAIL;
    private static final String EXPECTED_GENDER = MIN_GENDER;
    private static final String EXPECTED_MOBILE = MIN_PHONE;

    @Test
    void minimalRequiredDataTest() {
        new PracticeFormPage()
                .openPage()
                .setFirstName(MIN_FIRST_NAME)
                .setLastName(MIN_LAST_NAME)
                .setUserEmail(MIN_EMAIL)
                .setGender(MIN_GENDER)
                .setUserNumber(MIN_PHONE)
                .submit()
                .verifyFormSubmitted(MODAL_TITLE_TEXT)
                .verifyTableContent(
                        TABLE_HEADER_NAME, EXPECTED_NAME,
                        TABLE_HEADER_EMAIL, EXPECTED_EMAIL,
                        TABLE_HEADER_GENDER, EXPECTED_GENDER,
                        TABLE_HEADER_MOBILE, EXPECTED_MOBILE
                );
    }

        private static final String FIRST_NAME = "Ivan";
        private static final String LAST_NAME = "Egorov";
        private static final String VALID_EMAIL = "egorov@example.com";
        private static final String GENDER = "Male";
        private static final String INVALID_PHONE = "123";
       @Test
         void negativeValidationTest() {
            new PracticeFormPage()
                    .openPage()
                    .setFirstName(FIRST_NAME)
                    .setLastName(LAST_NAME)
                    .setUserEmail(VALID_EMAIL)
                    .setGender(GENDER)
                    .setUserNumber(INVALID_PHONE)
                    .submit()
                    .verifyFormNotSubmitted()
                    .verifyPhoneFieldHasError()
                    .verifyEmailFieldIsValid();
        }
    }


