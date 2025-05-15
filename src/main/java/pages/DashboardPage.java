package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public DashboardPage clickMainMenu(String mainMenuText) {
        By mainMenu = By.xpath("//span[normalize-space(text())='" + mainMenuText + "']");
        wait.until(ExpectedConditions.elementToBeClickable(mainMenu)).click();
        return this;
    }

    public DashboardPage clickSubMenu(String subMenuText) {
        By subMenu = By.xpath("//a[.//p[normalize-space(text())='" + subMenuText + "'] or normalize-space(text())='" + subMenuText + "']");
        wait.until(ExpectedConditions.elementToBeClickable(subMenu)).click();
        return this;
    }

    public DashboardPage goToEmployeePage() {
        return clickMainMenu("Cấu hình").clickSubMenu("Quản lý nhân viên");
    }

    public DashboardPage goToAttendancePage() {
        return clickMainMenu("Đào tạo").clickSubMenu("Điểm danh");
    }

    public boolean isUserLoggedIn() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h2[contains(text(),'System Administrator - admin')]")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
