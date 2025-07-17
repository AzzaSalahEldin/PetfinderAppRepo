package utils;

import config.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;


public class ScreenshotUtil {
    public static File takeScreenshot(String testName) {
        WebDriver driver = DriverManager.getDriver();
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
        File dest = new File(path);

        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }
}
