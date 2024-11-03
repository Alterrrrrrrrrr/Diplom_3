package pom;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
//    private WebDriver driver;
//    private WebDriver yandexDriver;
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    // "Соберите бургер"
    private By collectBurger = By.xpath(".//h1[@class='text text_type_main-large mb-5 mt-10' and text()='Соберите бургер']");

    // "Личный кабинет"
    private By personalAccountButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");

    // "Войти в аккаунт"
    private By enterAccountButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");

    // "Булки"
    private By bunsButton = By.xpath(".//span[@class='text text_type_main-default' and text()='Булки']");

    // "Соусы"
    private By saucesButton = By.xpath(".//span[@class='text text_type_main-default' and text()='Соусы']");

    // "Начинки"
    private By fillingsButton = By.xpath(".//span[@class='text text_type_main-default' and text()='Начинки']");

    // "Оформить заказ"
    private final By arrangeOrderButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");

    // "Булки"
    private By bunsSectionText = By.xpath(".//h2[@class='text text_type_main-medium mb-6 mt-10' and text()='Булки']");

    // "Соусы"
    private By saucesSectionText = By.xpath(".//h2[@class='text text_type_main-medium mb-6 mt-10' and text()='Соусы']");

    // "Соусы"
    private By toppingsSectionText = By.xpath(".//h2[@class='text text_type_main-medium mb-6 mt-10' and text()='Начинки']");


    @Step("Клик на кнопку личного кабинета")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Клик на кнопку входа в аккаунт")
    public void clickEnterAccountButton() {
        driver.findElement(enterAccountButton).click();
    }

    @Step("Клик на кнопку булок")
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    @Step("Клик на кнопку соусов")
    public void clickSaucesButton() {
        driver.findElement(saucesButton).click();
    }

    @Step("Клик на кнопку начинок")
    public void clickToppingsButton() {
        driver.findElement(fillingsButton).click();
    }

    @Step("Клик на текст соберите бургер")
    public String getCollectBurgerText() {
        return driver.findElement(collectBurger).getText();
    }

    @Step("Получение текста кнопки оформления заказа")
    public String getArrangeOrderText() {
        return driver.findElement(arrangeOrderButton).getText();
    }

    @Step("Получение текста раздела булок")
    public String getBunsSectionText() {
        return driver.findElement(bunsSectionText).getText();
    }

    @Step("Получение текста раздела соусов")
    public String getSaucesSectionText() {
        return driver.findElement(saucesSectionText).getText();
    }

    @Step("Получение текста раздела начинок")
    public String getToppingsSectionText() {
        return driver.findElement(toppingsSectionText).getText();
    }
}