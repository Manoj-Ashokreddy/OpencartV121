package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.Homepage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	
	@Test(groups={"Regression","Master"})
	public void testAccountRegistration() {
		try {
		logger.info("***** Starting TC001_AccountRegistrationTest **** ");
		Homepage hp = new Homepage(driver);
		
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount......");
		hp.clickRegister();
		logger.info("Clicked on Regsiter......");
		
		AccountRegistrationPage ap = new AccountRegistrationPage(driver);
		
		logger.info("Filling all the fields......");
		
		ap.setfirstName(randomstring().toUpperCase());
		ap.setlastName(randomstring().toUpperCase());
		ap.setemail(randomstring()+"@gmail.com");
		ap.setnumber(randomnumber());
		
		String pwd = randomalphanumeric();
		
		ap.setpassword(pwd);
		ap.confpassword(pwd);
		
		
		ap.privacypolicy();
		ap.submit();
		
		logger.info("Validating the message......");
		
		String msg = ap.confirmationmgs();
		
		if(msg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		
		else {
			logger.error("Test Failed...");
			logger.debug("Debug Logs....");
			Assert.assertTrue(false);
		}
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("***** Finished TC001_AccountRegistrationTest **** ");
		
	}
}
