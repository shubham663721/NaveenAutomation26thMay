package testCases;

import java.awt.AWTException;
import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AccountSettingsPage;
import pages.LoginPage;
import testBase.TestBase;
import util.ExcelUtility;
//This Test Case is to Verify Account Settings Page
public class AccountSettingsTestCase extends TestBase {
	LoginPage loginPage;
	AccountSettingsPage accountSettingsPage;
	ExcelUtility excelUtility;
	
	
	public AccountSettingsTestCase() {
		super();
	}
	
	//This code will evoke browser
	@BeforeClass()
	public void setup() {
		Initialization();
		loginPage = new LoginPage();
		accountSettingsPage = new AccountSettingsPage();
		excelUtility = new ExcelUtility();
	}
	
	//This Test is to login and Verify title
	@Test(priority=0)
	public void accountSettings() {
		String titleofPage = loginPage.login();
		 Assert.assertEquals(titleofPage, "Dashboard | self CRM","Title is Incorrect");
		String ASPageTitle = accountSettingsPage.selectAccountSettings();
	    Assert.assertEquals(ASPageTitle, "Account Settings", "Account Settings Page title is incorrect");
	   String accountHeader = accountSettingsPage.clickLinkVerifyTitle("Account");
	    Assert.assertEquals(accountHeader, "Account","Header is not Correct");  
	}
	
	//This Data Provider will read data from AccountSettings sheet from Excel file
	@DataProvider
	public Object[][] getCapsuleCRMTestData() throws IOException{
		Object data[][] = ExcelUtility.getTestData("AccountSettings");
		return data;
	}

	//This Test is to verify title of all the Settings
	@Test(priority=1,dataProvider="getCapsuleCRMTestData")
	public void headerValidation(String accountType, String header) {
		  String HeaderActual = accountSettingsPage.clickLinkVerifyTitle(accountType);
		  Assert.assertEquals(HeaderActual, header,"Header is not Correct");
	}
	
	//This Test is to Verify Appearance Settings
	@Test(priority=2)
	public void validateAppearance() throws AWTException, InterruptedException {
		accountSettingsPage.appearance("Appearance");
	}
	
	//This Data Provider will read data from Users sheet from Excel file
	@DataProvider
	public Object[][] getCapsuleCRMUserTestData() throws IOException{
		Object data[][] = ExcelUtility.getTestData("Users");
		return data;
	}
	
	//This Test is to Verify Users Settings
	@Test(priority=3,dataProvider="getCapsuleCRMUserTestData")
	public void validateUseradded(String fName, String lName, String email, String userN) {
	String usernameActual =	accountSettingsPage.users("Users", fName, lName, email, userN);
	Assert.assertEquals(usernameActual, userN);
	}
	
	//This Data Provider will read data from Opportunities sheet from Excel file
	@DataProvider
	public Object[][] getCapsuleCRMOpportunitiesTestData() throws IOException{
		Object data[][] = ExcelUtility.getTestData("Opportunities");
		return data;
	}
	
	//This Test is to Verify opportunities Settings
	@Test(priority=4,dataProvider="getCapsuleCRMOpportunitiesTestData")
    public void vaildateMileStoneAdded(String MileStoneName, String MileStoneDescription, String WinningProbability, String DaysUntilStale) throws InterruptedException {
	boolean mileStoneAdded =	accountSettingsPage.opportunities("Opportunities", MileStoneName, MileStoneDescription, WinningProbability, DaysUntilStale);
	Assert.assertTrue(mileStoneAdded,"MileStone is not added Successfully");
	}
	
	//This Data Provider will read data from Track sheet from Excel file
	@DataProvider
	public Object[][] getCapsuleCRMTrackTestData() throws IOException{
		Object data[][] = ExcelUtility.getTestData("Track");
		return data;
	}
	
	//This Test is to Verify tracks Settings
	@Test(priority=5,dataProvider="getCapsuleCRMTrackTestData")
    public void vaildateTracksAdded(String TrackName, String TagName, String TaskDescription, String catagory, String dueDate) throws InterruptedException {
	boolean trackStatus = accountSettingsPage.tracks("Tracks",TrackName, TagName, TaskDescription, catagory, dueDate);
	Assert.assertTrue(trackStatus, "Track is not added Successfully");
	}
	
	//This Test is to Verify Integration Settings
	@Test(priority=6)
	public void validateIntegrationConfigurationButtons() {
		int configCountis = accountSettingsPage.Integration();
		Assert.assertEquals(configCountis, 10, "Config button count is not matching");
	}

	//This code will close the Browser after execution
	@AfterClass()
	public void tearDown() {
		driver.quit();
	}
}
