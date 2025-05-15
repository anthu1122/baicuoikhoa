package scripts;

import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.LoginPage;

public class TC10_VerifyRoleDropdownLoadsAutomaticallyTest extends BaseTest {

    @Test
    public void testRoleDropdownLoadsWhenDepartmentIsSelected() {

        new LoginPage(driver).login("testadmin", "test1234");
        new DashboardPage(driver).goToEmployeePage();

        new AddEmployeePage(driver)
                .clickAddEmployeeButton()
                .selectDepartment("Văn phòng")
                .verifyOfficeRolesLoaded();
    }
}