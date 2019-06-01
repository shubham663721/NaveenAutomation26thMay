package testBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBase {
public static WebDriver driver;
public static Properties prop;

//In Constructor Loading config file which will be used to get input Parameters
public TestBase() {
	try {
		prop = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\config\\Config.properties");
		prop.load(ip);
	} 
	catch (FileNotFoundException e) {
		e.printStackTrace();
	}
		 catch (IOException e) {
			e.printStackTrace();
		}	
}
//This method will be used Initialize Browser
public static void Initialization() {
	String Browser = prop.getProperty("Browser");
	if(Browser.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "\\src\\main\\resources\\chromedriver.exe");
	//Opening Chrome in Incognito Mode
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--Incognito");
		driver = new ChromeDriver(options);
	}
	
	driver.get(prop.getProperty("URL"));
	driver.manage().window().maximize();
	
}
}
