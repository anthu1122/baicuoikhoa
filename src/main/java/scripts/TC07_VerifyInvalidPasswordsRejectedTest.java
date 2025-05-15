package scripts;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.LoginPage;

import java.util.UUID;

@Listeners(listeners.TestListener.class)
public class TC07_VerifyInvalidPasswordsRejectedTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AddEmployeePage addEmployeePage;

    @BeforeMethod
    public void setUp() {
        super.setUp();

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        addEmployeePage = new AddEmployeePage(driver);

        loginPage.login("testadmin", "test1234");
        dashboardPage.goToEmployeePage();
    }

    @DataProvider(name = "invalidPasswords")
    public Object[][] invalidPasswordData() {
        return new Object[][]{
                {"123"}, {"abc 123"}, {"pw "}
        };
    }

    @Test(dataProvider = "invalidPasswords")
    public void testInvalidPasswordIsRejected(String password) {
        String randomUsername = "thutestinvalidpwd_" + UUID.randomUUID().toString().substring(0, 8);

        addEmployeePage.clickAddEmployeeButton()
                .waitForFormVisible()
                .fillRequiredFields
                        ("Thư", "Test", "Văn phòng", randomUsername, password, "0123455678")
                .selectRole("Chăm sóc khách hàng")
                .clickConfirm();

        boolean formClosed = addEmployeePage.isAddEmployeeFormClosed();

        Assert.assertFalse(formClosed, "Employee was created with invalid password: " + password);
    }
}
