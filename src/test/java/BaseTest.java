import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.A;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pom.MainPage;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class BaseTest {

    public WebDriver driver;


    @Before
    public void setUpDriver() {
        String driverType = System.getenv("WEB_DRIVER");
        driver = getDriver(driverType == null ? "chrome" : driverType);
        driver.manage().timeouts().implicitlyWait(Duration.of(3, SECONDS));
        driver.get(MainPage.URL);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }



    protected WebDriver getDriver(String driverType) {
        switch (driverType) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "firefox":
                WebDriverManager.firefoxdriver().setup();;
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Driver type is not supported");
        }
    }
}
