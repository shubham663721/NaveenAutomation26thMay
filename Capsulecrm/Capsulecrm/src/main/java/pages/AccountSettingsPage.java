package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.TestBase;

//This Class is for all the Setting links of Account Settings
public class AccountSettingsPage extends TestBase {

	@FindBy(xpath = "//div[@class='menu-select-selected-option ember-view nav-bar-item nav-bar-account-button']")
	WebElement Accountsdropdown;
	@FindBy(xpath = "//a[contains(text(),'Account Settings')]")
	WebElement AccountSettings;
	@FindBy(xpath = "//span[@class='settings-content-menu-title']")
	WebElement AccountSettingsPageTitle;
	@FindBy(xpath = "//*[@class='settings-page-header']")
	WebElement settingHeader;
	@FindBy(xpath = "//header[@class='page-box-header']")
	WebElement Header;
	@FindBy(xpath = "//input[@id='appearance:uploadDecorate:logoImage']")
	WebElement chooseFile;
	@FindBy(xpath = "//input[@id='register:firstnameDecorate:firstName']")
	WebElement firstName;
	@FindBy(xpath = "//input[@id='register:lastNameDecorate:lastName']")
	WebElement lastName;
	@FindBy(xpath = "//input[@id='register:emailDecorate:email']")
	WebElement email;
	@FindBy(xpath = "//input[@id='register:usernameDecorate:username']")
	WebElement userName;
	@FindBy(xpath = "//input[@id='register:save']")
	WebElement inviteUser;
	@FindBy(xpath = "//a[contains(text(),'Add new User')]")
	WebElement addNewUser;
	@FindBy(xpath = "//button[contains(text(),'Add new Milestone')]")
	WebElement addNewMileStone;
	@FindBy(xpath = "//input[@class='form-input-text milestone-modal-name']")
	WebElement mileStoneName;
	@FindBy(xpath = "//*[@class='form-input-text milestone-modal-description']")
	WebElement mileStoneDescr;
	@FindBy(xpath = "//*[@class='form-input-text milestone-modal-probability']")
	WebElement probabilityofWinning;
	@FindBy(xpath = "//*[@class='form-input-text milestone-modal-days-until-stale']")
	WebElement daysUntilStale;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveButton;
	@FindBy(xpath = "//a[contains(text(),'Add new Track')]")
	WebElement addNewTrack;
	@FindBy(xpath = "//*[@id='j_id123:trackDescriptionDecorate:trackDescription']")
	WebElement trackName;
	@FindBy(xpath = "//*[@id='j_id123:trackTagDecorate:trackTag']")
	WebElement tag;
	@FindBy(xpath = "//*[@id='j_id123:taskLines:0:taskDescriptionDecorate:taskDescription']")
	WebElement taskDesc;
	@FindBy(xpath = "//*[@class='ui-selectmenu-button ui-widget ui-state-default ui-corner-all']")
	WebElement catagoryDropDown;
	@FindBy(xpath = "//*[@id='j_id123:taskLines:0:taskDaysAfterDecorate:taskDaysAfter']")
	WebElement due;
	@FindBy(xpath = "//*[contains(text(),'Save')]")
	WebElement saveTrack;
	
	// Constructor to initialize elements
	public AccountSettingsPage() {
		PageFactory.initElements(driver, this);
	}
	
	// This Method is to Select Account Settings
	public String selectAccountSettings() {
		Accountsdropdown.click();
		AccountSettings.click();
		String accountSettingPageTitle = AccountSettingsPageTitle.getText();
		return accountSettingPageTitle;
	}
	
	// This Method is to Click each setting in Account Settings and Verify Title of
	// that Setting
	public String clickLinkVerifyTitle(String link) {
		String settingTitle;
		driver.findElement(By.xpath("//li[@class]/a[contains(text(),'" + link + "')]")).click();
		if (link.equalsIgnoreCase("Invoices")) {
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			settingTitle = Header.getText();
		} else if (link.equalsIgnoreCase("Opportunities")) {
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			settingTitle = Header.getText();
		} else {
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			settingTitle = settingHeader.getText();
		}
		return settingTitle;
	}
	
	// This Method is to Verify Appearance Setting, Using Robot class here to upload
	// file
	public void appearance(String setting) throws AWTException, InterruptedException {
		driver.findElement(By.xpath("//li[@class]/a[contains(text(),'" + setting + "')]")).click();
		chooseFile.click();
		StringSelection strSel = new StringSelection(
				System.getProperty("user.dir") + "\\src\\main\\resources\\OpenButton.PNG");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSel, null);
		// using Robot class for File upload
		Thread.sleep(3000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	// This Method is to Verify Users Setting
	public String users(String User, String FirstName, String LastName, String Email, String UserName) {
		driver.findElement(By.xpath("//li[@class]/a[contains(text(),'" + User + "')]")).click();
		addNewUser.click();
		firstName.sendKeys(FirstName);
		lastName.sendKeys(LastName);
		email.sendKeys(Email);
		userName.sendKeys(UserName);
		inviteUser.click();
		String userNameActual = driver.findElement(By.xpath(
				"//tr[contains(@class,'row')]//a[contains(text(),'" + FirstName + " " + LastName + "')]/following::td"))
				.getText();
		return userNameActual;
	}
	
	// This Method is to Verify Opportunities Setting
	public boolean opportunities(String tab, String MileN, String MileStoneDes, String winProbability, String daysStale)
			throws InterruptedException {
		driver.findElement(By.xpath("//li[@class]/a[contains(text(),'" + tab + "')]")).click();
		addNewMileStone.click();
		mileStoneName.sendKeys(MileN);
		mileStoneDescr.sendKeys(MileStoneDes);
		probabilityofWinning.sendKeys(winProbability);
		daysUntilStale.sendKeys(daysStale);
		saveButton.click();
		Thread.sleep(2000);
		driver.navigate().refresh();
		List<WebElement> mileStoneNames = driver.findElements(By.xpath("//tr[@class='milestone-item']/td[1]/button"));

		for (WebElement i : mileStoneNames) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", i);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			String MSName = i.getText();
			if (MSName.equalsIgnoreCase(MileN)) {
				return true;
			}

		}
		return false;
	}

	//This Method is used to Verify Tracks Setting
	public boolean tracks(String tab1, String trackN, String tagN, String taskD, String catagory, String dueDays)
			throws InterruptedException {
		driver.findElement(By.xpath("//li[@class]/a[contains(text(),'" + tab1 + "')]")).click();
		addNewTrack.click();
		trackName.sendKeys(trackN);
		tag.sendKeys(tagN);
		taskDesc.sendKeys(taskD);
		due.sendKeys(dueDays);
		saveTrack.click();
		boolean status = driver.findElement(By.xpath("//a[contains(text(),'" + trackN + "')]")).isDisplayed();
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	// This Method is used to Verify Integration Setting
	public int Integration() {
		driver.findElement(By.xpath("//li[@class]/a[contains(text(),'Integrations')]")).click();
		List<WebElement> configCount = driver.findElements(By.xpath("//a[contains(text(),'Configure')]"));
		return configCount.size();
	}
}
