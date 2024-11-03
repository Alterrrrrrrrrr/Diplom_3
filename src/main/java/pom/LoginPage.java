package pom;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

//    private WebDriver driver;
//    private WebDriver yandexDriver;

    // "Email"
    private By emailField = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @name='name']");

    // "Пароль"
    private By passwordField = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @name='Пароль']");

    // "Войти"
    private By enterButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");

    // "Зарегистрироваться"
    private By registerButton = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Зарегистрироваться']");

    // "Восстановить пароль"
    private By recoverPasswordButton = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']");

    // "Вход"
    private By enterHeader = By.xpath(".//h2[text()='Вход']");


    // Заполнение поля "Email"
    public void inputEmailField(String emailFieldValue) {
        driver.findElement(emailField).sendKeys(emailFieldValue);
    }

    // Заполнение поля "Пароль"
    public void inputPasswordField(String passwordFieldValue) {
        driver.findElement(passwordField).sendKeys(passwordFieldValue);
    }

    // Клик по кнопке "Войти"
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

    @Step("Вход под пользователем")
    public void enteringUser(String emailValue, String passwordValue) {
        inputEmailField(emailValue);
        inputPasswordField(passwordValue);
        clickEnterButton();
    }

    @Step("Клик по кнопке регистрации")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Клик по кнопке восстановления пароля")
    public void clickRecoverPasswordButton() {
        driver.findElement(recoverPasswordButton).click();
    }

    @Step("Получение заголовка из блока входа")
    public String getEnterHeadingText() {
        return  driver.findElement(enterHeader).getText();
    }
}