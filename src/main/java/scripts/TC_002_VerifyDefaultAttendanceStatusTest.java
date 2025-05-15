package scripts;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AttendancePage;
import pages.DashboardPage;
import pages.LoginPage;
import listeners.TestListener;

@Listeners(TestListener.class)
public class TC_002_VerifyDefaultAttendanceStatusTest extends BaseTest {

    @Test
    public void testDefaultAttendanceStatusIsNotYetMarked() {
        try {

            new LoginPage(driver).login("testadmin", "test1234");
            new DashboardPage(driver).goToAttendancePage();

            AttendancePage attendancePage = new AttendancePage(driver);

            Assert.assertTrue(attendancePage.isElementDisplayed(attendancePage.getStatusColumnHeader()),
                    "The column header 'Status' is not displayed");

            Assert.assertTrue(attendancePage.isElementDisplayed(attendancePage.getFirstStudentStatus()),
                    "The student status is not 'Not Marked as Present'");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
}
