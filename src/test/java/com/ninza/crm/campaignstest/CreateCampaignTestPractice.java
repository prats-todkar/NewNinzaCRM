package com.ninza.crm.campaignstest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.JsonFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;
import com.ninza.crm.objectrepository.CampaignsPage;
import com.ninza.crm.objectrepository.CreateCampaignPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.SigninPage;

public class CreateCampaignTestPractice {
	
	@Test
	public void CreateCampaignWithMandatoryFields() throws IOException, InterruptedException {
		
	
		PropertyFileUtility pf = new PropertyFileUtility();
		
	    WebDriverUtility wd = new WebDriverUtility();	
		
		
		// get values from properties file
		String BROWSER = pf.toGetDataFromPropertiesFile("browser");
		String URL = pf.toGetDataFromPropertiesFile("url");
		String USERNAME = pf.toGetDataFromPropertiesFile("username");
		String PASSWORD = pf.toGetDataFromPropertiesFile("password");
		
		//excel file
		
		ExcelFileUtility ef = new ExcelFileUtility();
		
		String campaignName = ef.toReadDataFromExcel("Campaigns", 1, 1);
		String targSize = ef.toReadDataFromExcel("Campaigns", 1, 2);
		
		
        WebDriver driver = null;    //  used in before class
		
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}else{
			driver = new FirefoxDriver();
		}
			
		// this values are from properties files for log in
		
	    
		SigninPage sp = new SigninPage(driver);
		sp.signInToApp(URL,USERNAME,PASSWORD);
		
		//create campaign 
		//driver.findElement(By.linkText("Campaigns"));	

		HomePage hp = new HomePage(driver);
		hp.getCampaignsLink().click();
		
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getCreateCampaign().click();
		
		
		//create campaign with mandatory fields
		CreateCampaignPage ccp = new CreateCampaignPage(driver);

		ccp.getCampaignName().sendKeys(campaignName);
		
		ccp.getTargetSize().clear();
		ccp.getTargetSize().sendKeys(targSize);
		
		Thread.sleep(2000);
		
		ccp.getCreatecampaignBtn().click();
		
		WebElement toastMsg = hp.getToastMsg();
		
		wd.waitforVisibilityOfElement(driver, toastMsg);
		
		String msg = toastMsg.getText();
		System.out.println("Toast Message : "+msg);
		
		if(msg.contains(campaignName)) {
			System.out.println("Campaign created succeesfully");
		}
		else {
			System.out.println("Campaign not created");
		}

		hp.getCloseToastMsgBtn().click();
		hp.logout();
		driver.quit();
	}

	    @Test
        public void CreateCampaignWithExpectedDateTest() throws IOException, ParseException, InterruptedException {
		
		JsonFileUtility js = new JsonFileUtility();
		ExcelFileUtility ex = new ExcelFileUtility();
		WebDriverUtility wd = new WebDriverUtility();
		JavaUtility ju = new JavaUtility();
		
		String BROWSER = js.toGetDataFromJsonFile("Browser");
		String URL = js.toGetDataFromJsonFile("Url");
	    String USERNAME = js.toGetDataFromJsonFile("Username");	
	    String PASSWORD = js.toGetDataFromJsonFile("Password");	
						
	    // excel file
	 	// Step 1. create the object of excel file utility
	 	
		String campaignName = ex.toReadDataFromExcel("Camp", 1, 1);
		String targSize = ex.toReadDataFromExcel("Camp", 1, 2);
	 		
        WebDriver driver = null;
        
        if (BROWSER.equals("chrome")) {
        	
		    driver = new ChromeDriver();
	    
        } else if (BROWSER.equals("edge")) {
		
        	driver = new EdgeDriver();
	    }
	    else if (BROWSER.equals("firefox")) {
		
	    	driver = new FirefoxDriver();
	    }
			   
		driver.manage().window().maximize();
		
		wd.waitforpageToLoad(driver);

		SigninPage sp = new SigninPage(driver);
		sp.signInToApp(URL,USERNAME,PASSWORD);
		
		
		HomePage hp = new HomePage(driver);
		hp.getCampaignsLink().click();
		
						
	   //Create campaign with Mandatory fields
		CreateCampaignPage ccp = new CreateCampaignPage(driver);

		ccp.getCampaignName().sendKeys(campaignName);
		
		
		ccp.getTargetSize().clear();
		ccp.getTargetSize().sendKeys(targSize);
		
		Thread.sleep(2000);
		
		ccp.getCreatecampaignBtn().click();
	    WebElement targetSize = driver.findElement(By.name("targetSize"));
	    targetSize.clear();
	    targetSize.sendKeys(targSize);
	   
	   ju.getCurrentDate();
	   String expectedDate = ju.togetRequiredDate(30);
				
	   driver.findElement(By.name("expectedCloseDate")).sendKeys(expectedDate);
				
	   driver.findElement(By.xpath("//button[@type='submit']")).click();
				
				
	   //Verify the succesfull message
       WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
				
	   wd.waitforVisibilityOfElement(driver, toastMsg);
			
	   String msg = toastMsg.getText();
       System.out.println(msg);
		
       Thread.sleep(1000);
       driver.close();

	   }
	    
	   @Test
	   public void CreateCampaignWithCampaignStatus() throws EncryptedDocumentException, IOException {
		   
			PropertyFileUtility pf = new PropertyFileUtility();
			ExcelFileUtility ef = new ExcelFileUtility();
			WebDriverUtility wd = new WebDriverUtility();
			
			String BROWSER = pf.toGetDataFromPropertiesFile("browser");
			String URL = pf.toGetDataFromPropertiesFile("url");
			String USERNAME = pf.toGetDataFromPropertiesFile("username");
			String PASSWORD = pf.toGetDataFromPropertiesFile("password");
			
	        WebDriver driver = null;
			
			if(BROWSER.equals("chrome")) {
				driver = new ChromeDriver();
			}else if(BROWSER.equals("edge")) {
				driver = new EdgeDriver();
			}else{
				driver = new FirefoxDriver();
			}
			
			driver.manage().window().maximize();

			wd.waitforpageToLoad(driver);

			driver.get(URL);
		
			// this values are from properties files for log in
			
		    driver.findElement(By.id("username")).sendKeys(USERNAME);	
			driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			String campaignName = ef.toReadDataFromExcel("Campaigns", 1, 1);
			String targSize = ef.toReadDataFromExcel("Campaigns", 1, 2);
			String status = ef.toReadDataFromExcel("Campaign", 1, 3);
			
			//create campaign 
			driver.findElement(By.linkText("Campaigns"));	
			driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
			
			driver.findElement(By.name("campaignName")).sendKeys(campaignName);
			
			WebElement targetSize = driver.findElement(By.name("targetSize"));
			targetSize.clear();
			targetSize.sendKeys(targSize);
			
			//enter campaign status
			driver.findElement(By.name("campaignStatus")).sendKeys(status);
			
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
			
			wd.waitforVisibilityOfElement(driver, toastMsg);
			
			String msg = toastMsg.getText();
			System.out.println("Toast Message : "+msg);
			
			if(msg.contains(campaignName)) {
				System.out.println("Campaign created succeesfully");
			}
			else {
				System.out.println("Campaign not created");
			}
			
	        WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
			
			wd.mouseHoverOnWebelement(driver, icon);
	        
			WebElement logout = driver.findElement(By.xpath("//div[text()='Logout']"));	
			
			wd.ClickOnWebelement(driver, logout);
			
			driver.close();

	   }

}
