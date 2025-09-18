package com.ninza.crm.campaignstest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;

public class CreateCampaignWithMandatoryFieldsTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		PropertyFileUtility pf = new PropertyFileUtility();
		ExcelFileUtility ef = new ExcelFileUtility();
		WebDriverUtility wd = new WebDriverUtility();
		
		// get values from properties file
		String BROWSER = pf.toGetDataFromPropertiesFile("browser");
		String URL = pf.toGetDataFromPropertiesFile("url");
		String USERNAME = pf.toGetDataFromPropertiesFile("username");
		String PASSWORD = pf.toGetDataFromPropertiesFile("password");
		
		//excel file
		String campaignName = ef.toReadDataFromExcel("Campaigns", 1, 1);
		String targSize = ef.toReadDataFromExcel("Campaigns", 1, 2);
		
		
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
		
		
		
		//create campaign 
		driver.findElement(By.linkText("Campaigns"));	
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		
		
		//create campaign with mandatory fields
		driver.findElement(By.name("campaignName")).sendKeys(campaignName);
		
		WebElement targetSize = driver.findElement(By.name("targetSize"));
		targetSize.clear();
		targetSize.sendKeys(targSize);
		
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
