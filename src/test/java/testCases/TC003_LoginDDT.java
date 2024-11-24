package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountpage;
import testBase.BaseClass;
import utilities.DataProviders;

/*Data is valid -- login success -- testpass --logout
 * 					login failed -- test fail
 * 
 * Data is Invalid --login success -- testfail -- logout
 * 						login failed -- testpass
 * 
 */

public class TC003_LoginDDT extends BaseClass
{
	@Test(dataProvider="LoginData", dataProviderClass = DataProviders.class,groups="Datadriven")	//getting data provider from different class
	public void verify_loginDDT(String email, String pwd, String exp) {
		
		logger.info("****Starting TC003_LoginDDT *****");
		try {
		// Homepage
		Homepage hp = new Homepage(driver);
		
		hp.clickMyAccount();
		hp.clickLogin();

		// Login Page
		LoginPage lp = new LoginPage(driver);
		
		lp.setemail(email);
		lp.setpassword(pwd);

		lp.clickLogin();

		// My account page
		MyAccountpage mp = new MyAccountpage(driver);

		boolean targetpage = mp.isMyaccountpageexists();
		
		if(exp.equalsIgnoreCase("VALID")) {
			
			if(targetpage==true) {
				mp.clicklogout();
				Assert.assertTrue(true);
				
			}
			else 
			{
				Assert.assertTrue(false);
			}
		}
			
			if(exp.equalsIgnoreCase("INVALID")) {
				if(targetpage==true) {
					
					mp.clicklogout();
					Assert.assertTrue(false);
					
			}
			
				else {
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		finally {
			logger.info("****Finished TC003_LoginDDT *****");
		}
	}
}
