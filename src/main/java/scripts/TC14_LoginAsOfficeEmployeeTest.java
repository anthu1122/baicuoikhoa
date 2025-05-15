package scripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.DashboardPage;

public class TC14_LoginAsOfficeEmployeeTest extends BaseTest {

    @Test
    public void testLoginSuccess() {

        new LoginPage(driver).login("thutest1234", "thutest1234");
        boolean isUserLoggedIn = new DashboardPage(driver).isUserLoggedIn();

        Assert.assertTrue(isUserLoggedIn, "Login failed. User info not visible");
    }
}
