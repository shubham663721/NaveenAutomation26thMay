    
package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.TestBase;
//This Class is to Verify Cases
public class Cases extends TestBase {

	@FindBy(xpath="//a[@aria-label='Cases']")
	WebElement Cases;
	@FindBy(xpath="//span[@class='page-header-title']")
	WebElement casesPageHeader;
	@FindBy(xpath="//a[contains(text(),'Add Case')]")
	WebElement addCaseButton;
	@FindBy(xpath="//input[@id='partySearch']")
	WebElement caseRelatedto;
	@FindBy(xpath="//div[@class='searchresult']/ul/li[1]")
	WebElement firstSuggestion;
	@FindBy(xpath="//input[@id='caseNameDecorate:name']")
	WebElement caseName;
	@FindBy(xpath="//span[@class='ui-combobox']/input")
	WebElement tag;
	@FindBy(xpath="//input[@id='tagsDecorate:j_id191']")
	WebElement Addtag;
	@FindBy(xpath="//input[@id='save']")
    WebElement save;
	@FindBy(xpath="//div[@class='entity-details-title']")
    WebElement caseTitle;
	@FindBy(xpath="//div[@class='kase-summary-status float-left']/span")
    WebElement caseStatus;
	@FindBy(xpath="//a[@class='ember-view']")
    WebElement nameVerification;
	// Constructor to initialize elements
	public Cases() {
		PageFactory.initElements(driver, this);
		
	}
	
	//This Method is to Open Case Window
	public String ClickCasesIcon() throws InterruptedException {
		Cases.click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	    String titleIs = casesPageHeader.getText();
	   return titleIs;
	}
	
	//This Method is Add Case
	public String AddCase(String fn, String ln, String caseN, String tagValue) throws InterruptedException {
		addCaseButton.click();
		caseRelatedto.sendKeys(fn + " " + ln);
		//Thread.sleep(2000);
		firstSuggestion.click();
		caseName.sendKeys(caseN);
		//Thread.sleep(2000);
		tag.sendKeys(tagValue);
		Addtag.click();
		save.click();
	String CaseTitle =	caseTitle.getText();
	return CaseTitle;
	}
	
   	//This Method is to Verify Name
	public String nameV() {
		String name = nameVerification.getText();
			return name;
		}
	//This Method is verify Case Status
	public String CaseStatus() {
	String caseS =	caseStatus.getText();
	return caseS;
	}
	
}
