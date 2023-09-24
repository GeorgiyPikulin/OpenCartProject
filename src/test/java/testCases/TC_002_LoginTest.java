package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass
{
    @Test(groups= {"Sanity","Master"})
    public void test_Login() {
        logger.info("Starting TC_002_LoginTest");
        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            homePage.clickLogin();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(resourceBundle.getString("email")); // valid email, get it from properties file
            loginPage.setPassword(resourceBundle.getString("password")); // valid password, get it from properties file
            loginPage.clickLogin();

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageExists();
            Assert.assertTrue(targetPage);
        }
        catch(Exception e) {
            Assert.fail();
        }
        logger.info("Finished TC_002_LoginTest");
    }
}


