package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AddEmployeePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By addEmployeeButton = By.xpath("//button[contains(., 'Thêm nhân viên')]");
    private final By addEmployeeForm = By.xpath("//h2[text()='Thêm nhân viên']");
    private final By firstNameField = By.xpath("(//input[@type='text'])[1]");
    private final By lastNameField = By.xpath("(//input[@type='text'])[2]");
    private final By usernameField = By.xpath("(//input[@type='text'])[7]");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By phoneField = By.xpath("(//input[@type='text'])[8]");
    private final By confirmButton = By.xpath("//button[normalize-space(text())='Xác nhận']");
    private final By roleDropdown = By.xpath("//div[@role='combobox' and contains(@class, 'MuiSelect-multiple')]");
    private final By backdrop = By.cssSelector(".MuiBackdrop-root");
    private final By usernameInOfficeExistWarning = By.xpath("//p[text()='serviceError.staffExisted']");
    private final By usernameInAcademicExistWarning = By.xpath("//p[contains(text(), 'serviceError.teacherExisted')]");

    private final By departmentRadioOffice = By.xpath("//label[.//input[@value='OFFICE']]");
    private final By departmentRadioAcademic = By.xpath("//label[.//input[@value='ACADEMIC']]");

    private final By roleOptionCustomerService = By.xpath("//li[text()='Chăm sóc khách hàng']");
    private final By roleOptionConsultant = By.xpath("//li[text()='Tư vấn viên']");
    private final By roleOptionTeacherVN = By.xpath("//li[text()='Giáo viên Việt Nam']");

    public AddEmployeePage clickAddEmployeeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeButton)).click();
        return this;
    }

    public AddEmployeePage waitForFormVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addEmployeeForm));
        return this;
    }

    public boolean isAddEmployeeButtonDisplayed() {
        return isElementVisible(addEmployeeButton);
    }

    public AddEmployeePage createEmployee
            (String first, String last, String dept, String user, String pass, String phone, String role) {
        this.clickAddEmployeeButton()
                .waitForFormVisible();
        this.fillRequiredFields(first, last, dept, user, pass, phone);
        this.selectRole(role);
        this.clickConfirm();
        return this;
    }

    public AddEmployeePage fillRequiredFields
            (String first, String last, String dept, String user, String pass, String phone) {
        fillInput(firstNameField, first);
        fillInput(lastNameField, last);
        fillInput(usernameField, user);
        fillInput(passwordField, pass);
        driver.findElement(passwordField).sendKeys(Keys.TAB);
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField));
        fillInput(phoneField, phone);
        selectDepartment(dept);
        return this;
    }

    public AddEmployeePage selectDepartment(String dept) {
        if (dept.equalsIgnoreCase("Văn phòng")) {
            clickIfVisible(departmentRadioOffice);
        } else if (dept.equalsIgnoreCase("Đào tạo")) {
            clickIfVisible(departmentRadioAcademic);
        }
        return this;
    }

    public AddEmployeePage selectRole(String role) {
        clickIfVisible(roleDropdown);
        if (role.equalsIgnoreCase("Chăm sóc khách hàng")) {
            clickIfVisible(roleOptionCustomerService);
        } else if (role.equalsIgnoreCase("Tư vấn viên")) {
            clickIfVisible(roleOptionConsultant);
        } else if (role.equalsIgnoreCase("Giáo viên Việt Nam")) {
            clickIfVisible(roleOptionTeacherVN);
        }
        return this;
    }

    public AddEmployeePage clickConfirm() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(backdrop));
            WebElement confirm = wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirm);
        } catch (Exception e) {
            System.out.println("Error when clicking confirm: " + e.getMessage());
        }
        return this;
    }

    public boolean isAddEmployeeFormClosed() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(addEmployeeForm));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public AddEmployeePage verifyOfficeRolesLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(roleDropdown));

            boolean hasCustomerService = isElementVisible(roleOptionCustomerService);
            boolean hasConsultant = isElementVisible(roleOptionConsultant);

            Assert.assertTrue(hasCustomerService && hasConsultant,
                    "The dropdown does not have the roles 'Customer Service' and 'Consultant'");
        } catch (Exception e) {
            Assert.fail("Error when validating roles in the dropdown: " + e.getMessage());
        }

        return this;
    }

    public boolean isUsernameExistWarningVisibleOffice() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInOfficeExistWarning));
            return isElementVisible(usernameInOfficeExistWarning);
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isUsernameExistWarningVisibleAcademic() {
        return isElementVisible(usernameInAcademicExistWarning);
    }

    private void fillInput(By locator, String value) {
        WebElement field = driver.findElement(locator);
        field.clear();
        field.sendKeys(value);
    }

    private void clickIfVisible(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private boolean isElementVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
