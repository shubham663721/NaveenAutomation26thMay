package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.Cases;
import pages.LoginPage;
import pages.PeopleandOrg;
import testBase.TestBase;
import util.ExcelUtility;
//This Test Case is to Verify Case added successfully or not

public class CaseStatusVerification extends TestBase {
	LoginPage loginPage;
	PeopleandOrg peopleandOrg;
	ExcelUtility excelUtility;
	Cases cases;
	
public CaseStatusVerification() {
	super();
}

//This Method will Initialize Browser
@BeforeMethod()
public void setup() {
	Initialization();
	loginPage = new LoginPage();
	peopleandOrg = new PeopleandOrg();
	excelUtility = new ExcelUtility();
	cases = new Cases();
}

//This Data Provider will read data from AddPerson sheet from Excel file
@DataProvider
public Object[][] getCapsuleCRMTestData() throws IOException{
	Object data[][] = ExcelUtility.getTestData("AddPerson");
	return data;
}

//This Test case is to Verify People and Organization Page and Cases page
@Test(dataProvider="getCapsuleCRMTestData")
public void validateAddPerson(String title, String firstName, String lastName, String jobTitle, String org, String Phone, String CaseName, String TagV) throws InterruptedException {
	String titleofPage = loginPage.login();
	 Assert.assertEquals(titleofPage, "Dashboard | self CRM","Title is Incorrect");
	String peopleTitle = peopleandOrg.ClickPersonIcon();
     Assert.assertEquals(peopleTitle, "People & Organisations");
	peopleandOrg.addPerson(title, firstName, lastName, jobTitle, org, Phone);
    boolean userAdded =	peopleandOrg.verifyPersonAdded(firstName, lastName);
     Assert.assertTrue(userAdded,"user is not Added");
    String casesTitle = cases.ClickCasesIcon();
     Assert.assertEquals(casesTitle,"Cases", "Title is Incorrect");
    String titleCases = cases.AddCase(firstName, lastName, CaseName, TagV);
     Assert.assertEquals(titleCases, CaseName,"Title is not Matching");
     String nameVerifi = cases.nameV();
     Assert.assertEquals(nameVerifi, firstName + " " + lastName);
    String caseS = cases.CaseStatus();
     Assert.assertEquals(caseS, "Open");
     
}

//This method will close the Browser after test case Execution
@AfterMethod()
public void tearDown() {
	driver.quit();
}
	
}
