package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage{

	//constructor
	public Homepage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="//span[@class='caret']")
	WebElement lnkmyaccount;

	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")	//added from step 5
	WebElement lnkLogin;
	
	//action methods
	public void clickMyAccount() {
		lnkmyaccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}

	
}
