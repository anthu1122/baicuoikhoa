package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class ScreenshotHelper {

    public static void captureScreenshot(WebDriver driver, String testName) {
        if (driver != null) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshot, new File("screenshots/" + testName + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("WebDriver is null, cannot capture screenshot for " + testName);
        }
    }
}
