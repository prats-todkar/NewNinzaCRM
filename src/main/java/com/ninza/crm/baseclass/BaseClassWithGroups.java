package com.ninza.crm.baseclass;

import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.SigninPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseClassWithGroups {

	public WebDriver driver = null;
	public PropertyFileUtility pf = new PropertyFileUtility();
	public ExcelFileUtility ef=new ExcelFileUtility();
	public JavaUtility ju=new JavaUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	
	public static WebDriver sdriver = null;
	
	@BeforeMethod(groups={"smoke","regression"})
	public void beforeMethod() throws IOException {
		System.out.println("Login");
		String URL = pf.toGetDataFromPropertiesFile("url");
		String USERNAME = pf.toGetDataFromPropertiesFile("username");
		String PASSWORD = pf.toGetDataFromPropertiesFile("password");
		SigninPage sp = new SigninPage(driver);
		sp.signInToApp(URL,USERNAME,PASSWORD);
	}

	@AfterMethod(groups={"smoke","regression"})
	public void afterMethod() {
		System.out.println("Logout");
		HomePage hp=new HomePage(driver);
		hp.logout();
	}

//	@Parameters("Browser")
	@BeforeClass(groups={"smoke","regression"})
	public void beforeClass() throws IOException, InterruptedException {
		
		System.out.println("Launch the browser");
		String BROWSER = pf.toGetDataFromPropertiesFile("browser");

		ChromeOptions settings = new ChromeOptions();		
		Map<String, Object> prefs = new HashMap<>(); 
		prefs.put("profile.password_manager_leak_detection", false); 
		settings.setExperimentalOption("prefs", prefs);	
		
		if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver(settings);
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		

	}

	@AfterClass(groups={"smoke","regression"})
	public void afterClass() {
		System.out.println("Close the browser");
		driver.quit();
	}

	@BeforeTest(groups={"smoke","regression"})
	public void BeforeTest() {
		System.out.println("Pre-codiotions for parallel executions");
	}

	@AfterTest(groups={"smoke","regression"})
	public void afterTest() {
		System.out.println("Post-codiotions for parallel executions");
	}

	@BeforeSuite(groups={"smoke","regression"})
	public void beforeSuite() {
		System.out.println("Connect to Database");
	}

	@AfterSuite(groups={"smoke","regression"})
	public void afterSuite() {
		System.out.println("Disconnect to Database");
	}


}
