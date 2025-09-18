package programsSentByMam;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.objectrepository.CampaignsPage;
import com.ninza.crm.objectrepository.CreateCampaignPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.SigninPage;

public class CreateCampaignTest {
	
	@Test
	public void createCamapignWithMandataoryFieldsTest() throws EncryptedDocumentException, IOException {
		// Properties file
		// Step 1. Create the object of property file utility
		PropertyFileUtility pf = new PropertyFileUtility();

		// get the values
		String BROWSER = pf.toGetDataFromPropertiesFile("browser");
		String URL = pf.toGetDataFromPropertiesFile("url");
		String USERNAME = pf.toGetDataFromPropertiesFile("username");
		String PASSWORD = pf.toGetDataFromPropertiesFile("password");

		// excel file
		// Step 1. create the object of excel file utility
		ExcelFileUtility ex = new ExcelFileUtility();
		String campaignName = ex.toReadDataFromExcel("Camp", 1, 1);
		String targetSize = ex.toReadDataFromExcel("Camp", 1, 2);

		WebDriver driver = null;

		// launch the browser
		if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		// Navigate and Login in to the application

		SigninPage sp = new SigninPage(driver);
		sp.signInToApp(URL, USERNAME, PASSWORD);

		// Click on campaign link in home page
		HomePage hp = new HomePage(driver);
		hp.getCampaignsLink().click();

		// Click on create campaign
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getCreateCampaign().click();

		// Create campaign with Mandatory fields
		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampaignName().sendKeys(campaignName);
		ccp.getTargetSize().clear();
		ccp.getTargetSize().sendKeys(targetSize);
		ccp.getCreatecampaignBtn().click();

		// Verify the succesfull message
		WebElement toastMsg = hp.getToastMsg();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));

		String msg = toastMsg.getText();

		if (msg.contains("Successfully Added")) {
			System.out.println("Campign created successfully");
		} else {
			System.out.println("Campign is not created");
		}
		hp.getCloseToastMsgBtn().click();
		hp.logout();
		driver.quit();
	}

	@Test
	public void createCampaignWithExpectedCloseDateTest() throws EncryptedDocumentException, IOException {
		// Properties file
		// Step 1. Create the object of property file utility
		PropertyFileUtility pf = new PropertyFileUtility();

		// get the values
		String BROWSER = pf.toGetDataFromPropertiesFile("browser");
		String URL = pf.toGetDataFromPropertiesFile("url");
		String USERNAME = pf.toGetDataFromPropertiesFile("username");
		String PASSWORD = pf.toGetDataFromPropertiesFile("password");

		// excel file
		// Step 1. create the object of excel file utility
		ExcelFileUtility ex = new ExcelFileUtility();
		String campaignName = ex.toReadDataFromExcel("Camp", 1, 1);
		String targetSize = ex.toReadDataFromExcel("Camp", 1, 2);

		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		// Navigate and Login in to the application

		SigninPage sp = new SigninPage(driver);
		sp.signInToApp(URL, USERNAME, PASSWORD);

		// Click on campaign link in home page
		HomePage hp = new HomePage(driver);
		hp.getCampaignsLink().click();

		// Click on create campaign
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getCreateCampaign().click();

		// Create campaign with Mandatory fields
		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampaignName().sendKeys(campaignName);
		ccp.getTargetSize().clear();
		ccp.getTargetSize().sendKeys(targetSize);

		// Get the date after 30 days
		JavaUtility ju = new JavaUtility();
		String expectedDate = ju.togetRequiredDate(30);
		ccp.getExpectedCloseDate().sendKeys(expectedDate);
		ccp.getCreatecampaignBtn().click();

		// Verify the succesfull message
		WebElement toastMsg = hp.getToastMsg();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));

		String msg = toastMsg.getText();

		if (msg.contains("Successfully Added")) {
			System.out.println("Campign created successfully");
		} else {
			System.out.println("Campign is not created");
		}
		hp.getCloseToastMsgBtn().click();
		hp.logout();
		driver.quit();

	}


}
