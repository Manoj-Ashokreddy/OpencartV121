package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountpage extends BasePage{
	
	//constructor
	
	public MyAccountpage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement heading;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logout;
	
	//Action method
	public boolean isMyaccountpageexists() {
		try{
			return heading.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clicklogout() {
		logout.click();
	}

}
