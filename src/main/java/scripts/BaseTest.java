package scripts;

import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        DriverFactory.setDriver("edge");
        driver = DriverFactory.getDriver();

        driver.manage().window().maximize();
        driver.get("https://olms.codedao.io.vn/login");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("OLMS"));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.tearDown();
    }
}
