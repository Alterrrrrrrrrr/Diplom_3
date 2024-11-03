package pom;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    // "Войти"
    private By enterButton = By.xpath(".//a[@class='Auth_link__1fOlj']");

    @Step("Войти на странице забытого пароля")
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }
}