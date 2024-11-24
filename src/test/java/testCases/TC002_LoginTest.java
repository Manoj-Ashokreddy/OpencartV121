package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountpage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity", "Master"})
	public void testLogin() {
		try {
		logger.info("****Starting TC002_LoginTest *** ");
		
		//Homepage
		
		Homepage hp = new Homepage(driver);
		
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login Page
		LoginPage lp = new LoginPage(driver);
		
		lp.setemail(p.getProperty("email"));
		lp.setpassword(p.getProperty("password"));
		
		lp.clickLogin();
		
		//My account page
		MyAccountpage mp = new MyAccountpage(driver);
		
		boolean targetpage = mp.isMyaccountpageexists();
		
		Assert.assertEquals(targetpage, true,"Login Failed");
	
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("****Finished TC002_LoginTest *** " );
		
	}

}
