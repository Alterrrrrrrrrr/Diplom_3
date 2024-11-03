import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pom.MainPage;
import pom.LoginPage;
import pom.AccountPage;
import core.UserAPI;

import static org.hamcrest.CoreMatchers.is;

public class PersonalAccountTest extends BaseTest {

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
    @DisplayName("Переход в личный кабинет")
    public void goToPersonalAccountTest() {
        String expectedText = "В этом разделе вы можете изменить свои персональные данные";

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enteringUser(email, password);

        mainPage.clickPersonalAccountButton();

        AccountPage accountPage = new AccountPage(driver);
        String actualText = accountPage.getPersonalAccountText();

        MatcherAssert.assertThat("Ошибка перехода в личный кабинет", expectedText, is(actualText));
    }

    @Test
    @DisplayName("Переход в конструктор из личного кабинета")
    public void switchingFromPersonalAccountClickConstructorButtonTest() {
        String expectedText = "Соберите бургер";

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enteringUser(email, password);

        mainPage.clickPersonalAccountButton();

        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickConstructorButton();

        String actualText = mainPage.getCollectBurgerText();

        MatcherAssert.assertThat("Ошибка перехода в конструктор", expectedText, is(actualText));
    }

    @Test
    @DisplayName("Клик по логотипу из личного кабинета")
    public void switchingFromPersonalAccountClickLogoTest() {
        String expectedText = "Оформить заказ";

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.enteringUser(email, password);

        mainPage.clickPersonalAccountButton();

        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickLogoButton();

        String actualText = mainPage.getArrangeOrderText();

        MatcherAssert.assertThat("Ошибка перехода на главную страницу", expectedText, is(actualText));
    }

    @Test
    @DisplayName("Выход из личного кабинета")
    public void clickExitButtonTest() {
        String expectedText = "Вход";

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enteringUser(email, password);

        mainPage.clickPersonalAccountButton();

        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickExitButton();

        String actualText = loginPage.getEnterHeadingText();

        MatcherAssert.assertThat("Ошибка выхода из личного кабинета", expectedText, is(actualText));
    }

    @After
    public void tearDown() {
        if (token != null) {
            userAPI.delete(String.valueOf(token));
        }
        driver.quit();
    }
}