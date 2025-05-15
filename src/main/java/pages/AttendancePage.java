package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AttendancePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By headerRow = By.xpath("//tr[contains(@class, 'MuiTableRow-head')]");
    private By statusColumnHeader = By.xpath("//th[.//span[text()='Trạng thái']]");
    private By firstStudentStatus = By.xpath("//tr[1]//td[5]//span[text()='Chưa điểm danh']");

    public AttendancePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isElementDisplayed(By elementLocator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        return element.isDisplayed();
    }

    public By getHeaderRow() {
        return headerRow;
    }

    public By getStatusColumnHeader() {
        return statusColumnHeader;
    }

    public By getFirstStudentStatus() {
        return firstStudentStatus;
    }
}
