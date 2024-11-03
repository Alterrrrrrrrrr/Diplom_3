import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import pom.MainPage;

import static org.hamcrest.CoreMatchers.is;

public class ConstructorTest extends BaseTest {


    @Test
    @DisplayName("Проверка текста булок")
    public void bunButtonTest() {
        String expectedText = "Булки";

        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesButton();
        mainPage.clickBunsButton();

        String actualText = mainPage.getBunsSectionText();

        MatcherAssert.assertThat("Название раздела Булки", expectedText, is(actualText));
    }

    @Test
    @DisplayName("Проверка текста соусов")
    public void bunSaucesTest() {
        String expectedText = "Соусы";

        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesButton();

        String actualText = mainPage.getSaucesSectionText();

        MatcherAssert.assertThat("Название раздела Соусы", expectedText, is(actualText));
    }

    @Test
    @DisplayName("Проверка текста начинок")
    public void bunToppingsTest() {
        String expectedText = "Начинки";

        MainPage mainPage = new MainPage(driver);

        mainPage.clickToppingsButton();

        String actualText = mainPage.getToppingsSectionText();

        MatcherAssert.assertThat("Название раздела Начинки", expectedText, is(actualText));
    }
}