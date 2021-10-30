package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @Test
    void fillFormTest() {
        String firstName = "John";
        String lastName = "Malkovich";
        String userEmail = "Malkovich@mail.ru";
        String userNumber = "3332222332";
        String month = "December";
        String year = "1953";
        String day = "9";
        String subjectsInput = "Maths";
        String hobby = "Music";
        String currentAddress = "Cambridge, Massachusetts, USA";
        String state = "Haryana";
        String city = "Panipat";

        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("[for='gender-radio-1']").click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--009").click();
        $("#submit").scrollTo();
        $("#subjectsInput").setValue(subjectsInput).pressEnter();
        $(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("image.jpeg");
        $("#currentAddress").setValue(currentAddress);
        $("#state").scrollTo().click();
        $("#state").find(byText(state)).click();
        $("#city").click();
        $("#city").$(byText(city)).click();
        $("#submit").click();

        $(".modal-content").shouldHave(
                text("Student name"), text(firstName + " " + lastName),
                text("Student Email"), text(userEmail),
                text("Gender"), text("Male"),
                text("Mobile"), text(userNumber),
                text("Date of Birth"), text(day + " " + month + "," + year),
                text("Subjects"), text(subjectsInput),
                text("Hobbies"), text(hobby),
                text("Picture"), text("image.jpeg"),
                text("Address"), text(currentAddress),
                text("State and City"), text(state + " " + city));
    }
}