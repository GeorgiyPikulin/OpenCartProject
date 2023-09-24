package testBase;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

public class BaseClass {
    public WebDriver driver;
    public Logger logger;
    public ResourceBundle resourceBundle;

    @BeforeClass(groups = {"Master","Sanity","Regression"}) //Step8 groups added
    @Parameters("browser") // getting browser parameter from testng.xml
    public void setup(String browser) {
        resourceBundle = ResourceBundle.getBundle("config"); //Load config.properties file
        logger = LogManager.getLogger(this.getClass());

        switch (browser) {
            case "chrome" -> driver = new ChromeDriver();
            case "edge" -> driver = new EdgeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            case "safari" -> driver = new SafariDriver();
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(resourceBundle.getString("appURL"));
        driver.manage().window().maximize();
    }
    @AfterClass(groups = {"Master","Sanity","Regression"}) //Step8 groups added
    public void tearDown() {
        driver.quit();
    }
    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }
    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }
    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphanumeric(7);
    }
    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (Exception e) {
            e.getMessage();
        }
        return destination;
    }
}
