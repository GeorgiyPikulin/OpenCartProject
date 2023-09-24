package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDataDrivenTest extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void test_LoginDDT(String email, String pwd, String exp) {
		logger.info(" Starting TC_003_LoginDataDrivenTest ");

		try {
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			homePage.clickLogin();

			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmail(email);
			loginPage.setPassword(pwd);
			loginPage.clickLogin();

			MyAccountPage myAccountPage = new MyAccountPage(driver);
			boolean targetPage = myAccountPage.isMyAccountPageExists(); // this method is created MyAccountPage

			if (exp.equals("Valid")) {
				if (targetPage) {
					myAccountPage.clickLogout();
					Assert.assertTrue(true);
				} else {
                    Assert.fail();
				}
			}

			if (exp.equals("Invalid")) {
				if (targetPage) {
					MyAccountPage myAccPage = new MyAccountPage(driver);
					myAccPage.clickLogout();
                    Assert.fail();
				} else {
					Assert.assertTrue(true);
				}
			}

		} catch (Exception e) {
			Assert.fail();
		}
		logger.info(" Finished TC_003_LoginDataDrivenTest");
	}
}
