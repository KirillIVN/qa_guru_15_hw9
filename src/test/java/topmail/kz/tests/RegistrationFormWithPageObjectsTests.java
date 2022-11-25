package topmail.kz.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import topmail.kz.helpers.Attach;
import topmail.kz.pages.RegistrationFormPage;

import static io.qameta.allure.Allure.step;


public class RegistrationFormWithPageObjectsTests extends TestData  {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void setUp()  {
        SelenideLogger.addListener("allure", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "100.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        Configuration.browser = "chrome";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";


    }

    @Test
    void fillFormTest() {
        step("Fill form", () -> {
            //test for main page;
            registrationFormPage.openPage()
                    .setFirstName(firstName)
                    .setLastName(secondName)
                    .setEmail(email)
                    .setGender(Gender)
                    .setPhoneNumber(mPhone)
                    .setBirthDate(dayOfBirth, monthOfBirth, yearOfBirth)
                    .setSubject(perSubject1)
                    .setSubject(perSubject2)
                    .setHobbies(hobby1, hobby2)
                    .loadPicture(path)
                    .setCurrAddress(currAddress)
                    .setState(state)
                    .setCity(city)
                    .clickSubmitButton();
        });
        step("Check form date", () -> {
            //test for modal page;
            registrationFormPage.checkResultTableVisible()
                    .checkResult("Student Name", firstName + " " + secondName)
                    .checkResult("Student Email", email)
                    .checkResult("Gender", "Male")
                    .checkResult("Mobile", mPhone)
                    .checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                    .checkResult("Subjects", perSubject1 + "," + " "+ perSubject2)
                    .checkResult("Hobbies", hobby1)
                    .checkResult("Hobbies", hobby2)
                    .checkResult("Picture", path)
                    .checkResult("Address", currAddress)
                    .checkResult("State and City", state)
                    .checkResult("State and City", city);
        });
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshortAs("Last screenshort");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

}

