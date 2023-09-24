package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

    @Test(groups= {"Regression","Master"})
    void test_Account_Registration() {
        logger.debug("application logs......");
        logger.info("***  Starting TC_001_AccountRegistrationTest ***");
        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            logger.info("Clicked on My account link");
            homePage.clickRegister();
            logger.info("Clicked on register link");

            AccountRegistrationPage acctRegPage = new AccountRegistrationPage(driver);
            logger.info("Providing customer data");
            acctRegPage.setFirstName(randomString().toUpperCase());
            acctRegPage.setLastName(randomString().toUpperCase());
            acctRegPage.setEmail(randomString()+"@gmail.com");
            acctRegPage.setTelephone(randomNumber());
            String password = randomAlphaNumeric();
            acctRegPage.setPassword(password);
            acctRegPage.confirmPassword(password);

            acctRegPage.clickPrivacyPolicy();
            acctRegPage.clickContinue();
            logger.info("Clicked on continue");
            Thread.sleep(5000);

            String confirmMsg = acctRegPage.getConfirmationMsg();
            logger.info("Validating expected message");
            Assert.assertEquals(confirmMsg,"Your Account Has Been Created!","Did not get expected msg");

        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("***  Finished TC_001_AccountRegistrationTest ***");
    }
}
