package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.TestBase;
//This Class is to Verify Login Page
public class LoginPage extends TestBase {

	@FindBy(xpath="//input[@id='login:usernameDecorate:username']")
	WebElement userName;
	@FindBy(xpath="//input[@id='login:passwordDecorate:password']")
	WebElement password;
	@FindBy(xpath="//input[@id='login:login']")
	WebElement loginButton;
	
	//Constructor to initialize elements
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//This Method is to Login into Application and Verify Title
	public String login() {
		userName.sendKeys(prop.getProperty("userName"));
		password.sendKeys(prop.getProperty("password"));
		loginButton.click();
	String title = driver.getTitle();
	return title;
	}
	
}
