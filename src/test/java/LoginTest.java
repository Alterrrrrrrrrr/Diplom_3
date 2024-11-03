import core.UserAPI;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.github.javafaker.Faker;
import pom.*;

import static org.hamcrest.CoreMatchers.is;

public class LoginTest extends BaseTest {

    Faker faker = new Faker();
    private UserAPI userAPI;
    StringBuilder token;
    private String email = faker.internet().emailAddress();
    private String password = faker.internet().password();
    private String name = faker.name().firstName();

    @Before
    public void setup() {

        RestAssured.baseURI = MainPage.URL;

        userAPI = new UserAPI();
        Response responseCreate = userAPI.create(email, password, name);
        token = new StringBuilder(responseCreate.then().extract().path("accessToken").toString());
        token.delete(0,7);
    }

    @Test
    @DisplayName("Логин с главной страницы")
    public void loginMainPageTest() {
        String expectedText = "Оформить заказ";

        MainPage mainPage = new MainPage(driver);
        mainPage.clickEnterAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enteringUser(email, password);

        String actualText = mainPage.getArrangeOrderText();

        MatcherAssert.assertThat("На главной не появляется кнопка Оформить заказ", expectedText, is(actualText));
    }

    @Test
    @DisplayName("Логин через личный кабинет")
    public void loginPersonalAccountTest() {
        String expectedText = "Оформить заказ";

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enteringUser(email, password);

        String actualText = mainPage.getArrangeOrderText();

        MatcherAssert.assertThat("На главной не появляется кнопка Оформить заказ", expectedText, is(actualText));
    }

    @Test
    @DisplayName("Логин через форму регистрации")
    public void loginRegisterFormTest() {
        String expectedText = "Оформить заказ";

        MainPage mainPage = new MainPage(driver);
        mainPage.clickEnterAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickEnterButton();

        loginPage.enteringUser(email, password);

        String actualText = mainPage.getArrangeOrderText();

        MatcherAssert.assertThat("На главной не появляется кнопка Оформить заказ", expectedText, is(actualText));
    }

    @Test
    @DisplayName("Логин через восстановление пароля")
    public void loginForgotPasswordFormTest() {
        String expectedText = "Оформить заказ";

        MainPage mainPage = new MainPage(driver);
        mainPage.clickEnterAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRecoverPasswordButton();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickEnterButton();

        loginPage.enteringUser(email, password);

        String actualText = mainPage.getArrangeOrderText();

        MatcherAssert.assertThat("На главной не появляется кнопка Оформить заказ", expectedText, is(actualText));
    }


    @After
    public void tearDown() {
        if (token != null) {
            userAPI.delete(String.valueOf(token));
        }
        driver.quit();
    }
}