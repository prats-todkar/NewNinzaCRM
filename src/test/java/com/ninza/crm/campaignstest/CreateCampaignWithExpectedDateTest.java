package com.ninza.crm.campaignstest;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.JsonFileUtility;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;

public class CreateCampaignWithExpectedDateTest {

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {
		
		JsonFileUtility js = new JsonFileUtility();
		ExcelFileUtility ef = new ExcelFileUtility();
		WebDriverUtility wd = new WebDriverUtility();
		JavaUtility ju = new JavaUtility();
		
		String BROWSER = js.toGetDataFromJsonFile("Browser");
		String URL = js.toGetDataFromJsonFile("Url");
	    String USERNAME = js.toGetDataFromJsonFile("Username");	
	    String PASSWORD = js.toGetDataFromJsonFile("Password");	
						
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
	
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Click on campaign link
		driver.findElement(By.linkText("Campaigns")).click();
						
	   //Create campaign with Mandatory fields
	   driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
	   
	   String campaignName = ef.toReadDataFromExcel("Campaigns", 1, 1);
	   String targSize = ef.toReadDataFromExcel("Campaigns", 1, 2);
	   
	   
	   driver.findElement(By.name("campaignName")).sendKeys(campaignName);
	   
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

}
