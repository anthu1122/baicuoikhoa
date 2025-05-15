package scripts;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.DashboardPage;
import pages.AddEmployeePage;

@Listeners(listeners.TestListener.class)
public class TC09_VerifyPasswordWithPersonalInfoWarningTest extends BaseTest {

    @DataProvider(name = "personalInfoPasswords")
    public Object[][] providePersonalInfoPasswords() {
        return new Object[][]{
                // 1. Password = Họ tên
                {"thutest", "thutest"},
                // 2. Password = SĐT
                {"0876543200", "0876543200"},
                // 3. Password = Username
                {"thutestpwdpersonalinfo", "thutestpwdpersonalinfo"}
        };
    }

    @Test(dataProvider = "personalInfoPasswords")
    public void testPasswordWithPersonalInfoIsBlocked(String username, String password) {

        new LoginPage(driver).login("testadmin", "test1234");
        new DashboardPage(driver).goToEmployeePage();

        AddEmployeePage addEmployeePage = new AddEmployeePage(driver)
                .clickAddEmployeeButton()
                .fillRequiredFields("Thư", "Test", "Văn phòng", username, password, "0123456678");

        addEmployeePage.selectRole("Chăm sóc khách hàng");
        addEmployeePage.clickConfirm();

        boolean isFormClosed = addEmployeePage.isAddEmployeeFormClosed();

        Assert.assertFalse(isFormClosed,
                "The form is closed with a password containing personal information: " + password);
    }
}