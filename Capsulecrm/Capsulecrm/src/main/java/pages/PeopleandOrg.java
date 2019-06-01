package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import testBase.TestBase;

//This Method is to Verify People and Organization Window
public class PeopleandOrg extends TestBase {
	int k = 1;
	@FindBy(xpath = "//a[@aria-label='People & Organisations']")
	WebElement peoplelogo;
	@FindBy(xpath = "//span[@class='page-header-title']")
	WebElement peoplePageHeader;
	@FindBy(xpath = "//a[contains(text(),'Add Person')]")
	WebElement addPersonbutton;
	@FindBy(xpath = "(//div[contains(label,'Title')]//following::select)[1]")
	WebElement titleDropdown;
	@FindBy(xpath = "//input[@id='party:fnDecorate:fn']")
	WebElement nameFirst;
	@FindBy(xpath = "//input[@id='party:lnDecorate:ln']")
	WebElement nameLast;
	@FindBy(xpath = "//input[@id='party:roleDecorate:jobTitle']")
	WebElement JobTitle;
	@FindBy(xpath = "//input[@id='party:orgDecorate:org']")
	WebElement organization;
	@FindBy(xpath = "//input[@id='party:j_id325:0:phnDecorate:number']")
	WebElement phoneNo;
	@FindBy(xpath = "//input[@id='party:save']")
	WebElement save;

	// Constructor to initialize elements
	public PeopleandOrg() {
		PageFactory.initElements(driver, this);
	}

	// This Method is to open Person and Organization Window and Verify Title
	public String ClickPersonIcon() throws InterruptedException {
		peoplelogo.click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		String titleIs = peoplePageHeader.getText();
		return titleIs;
	}

	// This Method is to Add Person
	public void addPerson(String title, String firstName, String lastName, String jobTitle, String org, String Phone) {
		addPersonbutton.click();
		Select drp = new Select(titleDropdown);
		drp.selectByValue(title);
		nameFirst.sendKeys(firstName);
		nameLast.sendKeys(lastName);
		JobTitle.sendKeys(jobTitle);
		organization.sendKeys(org);
		phoneNo.sendKeys(Phone);
		save.click();
	}

	// This Method is to Verify that Person is added Successfully or not
	public boolean verifyPersonAdded(String fn, String ln) {
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + fn + " " + ln + "')]"));
		if (k <= list.size()) {
			return true;

		} else {
			return false;
		}

	}
}
