import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pom.MainPage;
import pom.LoginPage;
import pom.RegistrationPage;
import core.UserAPI;

import static org.hamcrest.CoreMatchers.is;

public class RegisterTest extends BaseTest {

    Faker faker = new Faker();
    private UserAPI userAPI;
    StringBuilder token;
    private String email = faker.internet().emailAddress();
    private String password = faker.internet().password();
    private String name = faker.name().firstName();


    @Before
    public void setup() {

        RestAssured.baseURI = MainPage.URL;
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successfulRegistrationTest() {
        userAPI = new UserAPI();

        String expectedText = "Вход";

        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickEnterAccountButton();

        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.clickRegisterButton();

        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.registerUser(name, email, password);

        Response responseLogin = userAPI.login(email, password);

        token = new StringBuilder(responseLogin.then().extract().path("accessToken").toString());
        token.delete(0,7);

        String actualText = objLoginPage.getEnterHeadingText();

        MatcherAssert.assertThat("Ошибка регистрации", expectedText, is(actualText));
    }

    @Test
    @DisplayName("Некорректный пароль")
    public void invalidPasswordTest() {
        String expectedText = "Некорректный пароль";
        String invalidPassword = "123";

        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickEnterAccountButton();

        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.clickRegisterButton();

        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.registerUser(name, email, invalidPassword);

        String actualText = objRegistrationPage.getInvalidPasswordText();

        MatcherAssert.assertThat("Некорректный пароль не получен", expectedText, is(actualText));
    }

    @After
    public void tearDown() {
        if(token != null) {
            userAPI.delete(String.valueOf(token));
        }
        driver.quit();
    }
}