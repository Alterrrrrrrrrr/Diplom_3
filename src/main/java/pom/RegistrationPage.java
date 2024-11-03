package pom;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }


    // "Имя"
    private By nameRegistrationField = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @type='text']");

    // "Email"
    private By emailRegistrationField = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @type='text' and @value='']");

    // "Пароль"
    private By passwordRegistrationField = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @type='password']");

    // "Зарегистрироваться"
    private By registerButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");

    // "Войти"
    private By enterButton = By.xpath(".//a[@class='Auth_link__1fOlj']");

    // "Некорректный пароль"
    private By wrongPasswordError = By.xpath(".//p[@class='input__error text_type_main-default']");


    // метод заполнения имени
    public void inputName(String nameValue) {
        driver.findElement(nameRegistrationField).sendKeys(nameValue);
    }

    // метод заполнения почты
    public void inputEmail(String emailValue) {
        driver.findElement(emailRegistrationField).sendKeys(emailValue);
    }

    // метод заполнения пароля
    public void inputPassword(String passwordValue) {
        driver.findElement(passwordRegistrationField).sendKeys(passwordValue);
    }

    // метод клика на кнопку "Зарегистрироваться"
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Метод клика на кнопку входа")
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

    @Step("Метод получения сообщения о неправильном пароле")
    public String getInvalidPasswordText() {
        return driver.findElement(wrongPasswordError).getText();
    }

    @Step("Метод регистрации пользователя")
    public void registerUser(String nameValue, String emailValue, String passwordValue) {
        inputName(nameValue);
        inputEmail(emailValue);
        inputPassword(passwordValue);
        clickRegisterButton();
    }
}