package com.ninza.crm.leadtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.ninza.crm.generic.fileutility.PropertyFileUtility;


public class CreateLeadTest {
	
	public void CreateLeadWithMandatoryFields() throws IOException, InterruptedException {
		
		PropertyFileUtility pf = new PropertyFileUtility();
		
		String browserName = pf.toGetDataFromPropertiesFile("browser");
		String urlLink = pf.toGetDataFromPropertiesFile("url");
		String userName = pf.toGetDataFromPropertiesFile("username");
		String passWord = pf.toGetDataFromPropertiesFile("password");
		
        WebDriver driver = null;
		
		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equals("edge")){
			driver = new EdgeDriver();
		}
	
		driver.get(urlLink);		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("username")).sendKeys(userName);
		
		driver.findElement(By.id("inputPassword")).sendKeys(passWord);
		
        driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		driver.findElement(By.linkText("Leads")).click();
		
		driver.findElement(By.xpath("//span[text()='Create Lead']")).click();
		
	    Thread.sleep(2000);
	}

}
