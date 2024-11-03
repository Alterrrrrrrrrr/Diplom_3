package pom;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }


    // "Конструктор"
    private By constructorButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Конструктор']");

    // Логотип
    private By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    // "Выход"
    private By exitButton = By.xpath(".//button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive']");

    // "В этом разделе вы можете изменить свои персональные данные"
    private By personalAccountText = By.xpath(".//p[@class='Account_text__fZAIn text text_type_main-default']");


    @Step("Клик по кнопке 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Клик по логотипу")
    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }

    @Step("Клик по кнопке 'Выход'")
    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }

    @Step("Получение данных со страницы личного кабинета")
    public String getPersonalAccountText() {
        return  driver.findElement(personalAccountText).getText();
    }

}