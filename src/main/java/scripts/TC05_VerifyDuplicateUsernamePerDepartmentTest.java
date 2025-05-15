package scripts;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.DashboardPage;
import pages.AddEmployeePage;

@Listeners(listeners.TestListener.class)
public class TC05_VerifyDuplicateUsernamePerDepartmentTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AddEmployeePage addEmployeePage;

    @BeforeMethod
    public void setUpTest() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        addEmployeePage = new AddEmployeePage(driver);

        loginPage.login("testadmin", "test1234");
        dashboardPage.goToEmployeePage();
    }

    @DataProvider(name = "duplicateUsernameCases")
    public Object[][] provideUsernameCases() {
        return new Object[][]{
                {"thutestofficeduplicate", "Văn phòng", "Chăm sóc khách hàng", false},
                {"thutestacademicduplicate", "Đào tạo", "Giáo viên Việt Nam", false},
                {"thutestofficeduplicate", "Đào tạo", "Giáo viên Việt Nam", true}
        };
    }

    @Test(dataProvider = "duplicateUsernameCases")
    public void testDuplicateUsernamePerDepartment(String username, String department, String role, boolean shouldSucceed) {
        addEmployeePage.createEmployee
                ("Thư", "Test", department, username, "Thutest123@!", "0123445678", role);

        boolean isFormClosed = addEmployeePage.isAddEmployeeFormClosed();

        if (shouldSucceed) {
            Assert.assertTrue(isFormClosed, "The employee is not created successfully as expected");
        } else {
            Assert.assertFalse(isFormClosed, "The form is closed, but a duplicate username should be rejected");

            if ("Văn phòng".equals(department)) {
                Assert.assertTrue(addEmployeePage.isUsernameExistWarningVisibleOffice(),
                        "Error message is not displayed when the username is duplicated in Office Deparment");
            } else if ("Đào tạo".equals(department)) {
                Assert.assertTrue(addEmployeePage.isUsernameExistWarningVisibleAcademic(),
                        "Error message is not displayed when the username is duplicated in Academic Deparment");
            }
        }
    }
}
