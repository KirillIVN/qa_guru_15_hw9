package topmail.kz.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import topmail.kz.pages.components.CalendarComponent;
import topmail.kz.pages.components.ResultsTableComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {

    private CalendarComponent calendarComponent = new CalendarComponent();
    private ResultsTableComponent resultsTableComponent = new ResultsTableComponent();

    private  SelenideElement
            firstNameInput = $x("//*[@id='firstName']"),
            lustNameInput = $x("//*[@id='lastName']"),
            emailInput = $x("//*[@id='userEmail']"),
            mobilePhoneInput = $x("//*[@id='userNumber']"),
            currentAddressInput = $x("//*[@id='currentAddress']"),
            buttonClick = $x("//*[@id='submit']"),
            loadPicture = $x("//*[@id='uploadPicture']");

    private final static String TITLE_TEXT = "Practice Form";

    @Step("Open main page")
    //open main page;
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        $(".main-header").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#RightSide_Advertisement').remove()");

        return this;
    }
    @Step("Fill First name")
    //input First name
    public RegistrationFormPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }
    @Step("Fill Last name")
    //input Last name
    public RegistrationFormPage setLastName(String value) {
        lustNameInput.setValue(value);

        return this;
    }
    @Step("Fill E-mail")
    //input Email name
    public RegistrationFormPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }
    @Step("Fill Gender")
    //set Gender
    public RegistrationFormPage setGender(String gender) {
        $x("//*[@id='genterWrapper']//label[text() ='"+gender+"']").click();

        return this;
    }
    @Step("Fill Phone number")
    //input phone number
    public RegistrationFormPage setPhoneNumber(String value) {
        mobilePhoneInput.setValue(value);

        return this;
    }
    @Step("Fill Date of Birth")
    //input date of Birth
    public RegistrationFormPage setBirthDate(String day, String month, String year) {
        $x("//*[@id='dateOfBirth']/div[1]/div").click();
        calendarComponent.setDate(day, month, year);

        return this;
    }
    @Step("Fill Subjects")
    //input subjects
    public RegistrationFormPage setSubject(String value) {
        $x("//*[@id='subjectsInput']").setValue(value);
        $x("//*[@id='subjectsInput']").setValue(value).pressEnter();

        return this;
    }
    @Step("Fill Hobbies")
    //set hobbies
    public RegistrationFormPage setHobbies(String hobby1, String hobby2) {
        $x("//*[@class='col-md-9 col-sm-12']//label[text() = '"+ hobby1 +"']").click();
        $x("//*[@class='col-md-9 col-sm-12']//label[text() = '"+ hobby2 +"']").click();

        return this;
    }
    @Step("Load picture")
    //load picture
    public RegistrationFormPage loadPicture(String path) {
        loadPicture.uploadFromClasspath(path);

        return this;
    }
    @Step("Fill Current address")
    //input current address
    public RegistrationFormPage setCurrAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }
    @Step("Fill State")
    //set state
    public RegistrationFormPage setState(String state) {
        $x("//*[@id='react-select-3-input']").setValue(state).pressEnter();

        return this;
    }
    @Step("Fill City")
    //set city
    public RegistrationFormPage setCity(String city) {
        $x("//*[@id='react-select-4-input']").setValue(city).pressEnter();

        return this;
    }
    //click submit button
    public RegistrationFormPage clickSubmitButton() {
        buttonClick.click();

        return this;
    }
    //check visible of modal window
    public RegistrationFormPage checkResultTableVisible() {
        resultsTableComponent.checkModal();

        return this;
    }
    @Step("Check results of filling")
    //check results
    public RegistrationFormPage checkResult(String key, String value) {
        resultsTableComponent.checkResult(key, value);

        return this;
    }
 }