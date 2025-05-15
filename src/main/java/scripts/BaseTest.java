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
    protected WebDriverWait wait;  // Khai báo WebDriverWait

    @BeforeMethod
    public void setUp() {
        DriverFactory.setDriver("edge");  // Chọn trình duyệt muốn sử dụng, ví dụ 'edge', 'chrome', 'firefox'
        driver = DriverFactory.getDriver();

        driver.manage().window().maximize();
        driver.get("https://olms.codedao.io.vn/login");

        // Khởi tạo WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Đảm bảo trang đã tải hoàn toàn
        wait.until(ExpectedConditions.titleContains("OLMS"));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.tearDown();
    }
}
