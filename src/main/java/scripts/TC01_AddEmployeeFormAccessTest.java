package scripts;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.LoginPage;

public class TC01_AddEmployeeFormAccessTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AddEmployeePage addEmployeePage;

    @BeforeClass
    public void setUp() {
        super.setUp();

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        addEmployeePage = new AddEmployeePage(driver);
    }

    @Test
    public void testAddEmployeeButtonIsDisplayed() {

        loginPage.login("testadmin", "test1234");
        dashboardPage.goToEmployeePage();

        boolean isButtonDisplayed = addEmployeePage.isAddEmployeeButtonDisplayed();
        Assert.assertTrue(isButtonDisplayed,
                "The 'Add Employee' button does not appear after accessing the Employee Management page");
    }
}
