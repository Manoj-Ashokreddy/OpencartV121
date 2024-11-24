package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	//constructor
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	//Locators
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txt_firstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txt_lastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txt_number;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txt_confirmpassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chk_terms;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btn_continue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msg_confmsg;
	
	
	//Actions
	public void setfirstName(String fname) {
		txt_firstname.sendKeys(fname);
	}
	
	public void setlastName(String lname) {
		txt_lastname.sendKeys(lname);
	}
	
	public void setemail(String email) {
		txt_email.sendKeys(email);
	}
	
	public void setnumber(String num) {
		txt_number.sendKeys(num);
	}
	public void setpassword(String pwd) {
		txt_password.sendKeys(pwd);
	}
	
	public void confpassword(String pwd) {
		txt_confirmpassword.sendKeys(pwd);
	}
	
	public void privacypolicy() {
		chk_terms.click();
	}
	
	public void submit() {
		btn_continue.click();
	}
	
	public String confirmationmgs() {
		try {
			return msg_confmsg.getText();
		}catch(Exception e) {
			return(e.getLocalizedMessage());
		}
	}
}
