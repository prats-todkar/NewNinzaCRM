package com.ninza.crm.producttest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;

public class CreateProductWithMandatoryFieldsTest {

	public static void main(String[] args) throws InterruptedException, IOException {


		PropertyFileUtility pf = new PropertyFileUtility();
		ExcelFileUtility ef = new ExcelFileUtility();
		WebDriverUtility wd = new WebDriverUtility();
		JavaUtility ju = new JavaUtility();
		
		String browserName = pf.toGetDataFromPropertiesFile("browser");
		String urlLink = pf.toGetDataFromPropertiesFile("url");
		String userName = pf.toGetDataFromPropertiesFile("username");
		String passWord = pf.toGetDataFromPropertiesFile("password");
		
		WebDriver driver = null;
		
		//launch the browser
		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equals("edge")){
			driver = new EdgeDriver();
		}
	
		//Maximize the window
		driver.manage().window().maximize();
				
	    //implicit wait
	    wd.waitforpageToLoad(driver);
	    
		//Navigate to ninza CRM
		driver.get(urlLink);	
		
		//Login into the ninza CRM
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("inputPassword")).sendKeys(passWord);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
		
        //Click on product link
		driver.findElement(By.linkText("Products")).click();
		
		//click add product button
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		
		int ranNum = ju.getRandomNumber();
		
		String productName = ef.toReadDataFromExcel("Product", 1, 0)+ranNum;
		driver.findElement(By.name("productName")).sendKeys(productName);
		
		WebElement category = driver.findElement(By.name("productCategory"));	
		Select sc = new Select(category);
		sc.selectByValue("Electronics");
		Thread.sleep(1000);
		
		
        String quantity = ef.toReadDataFromExcel("Product", 1, 1);
		WebElement Quantity = driver.findElement(By.name("quantity"));
		Quantity.click();
		Quantity.clear();	
		Quantity.sendKeys(quantity);
		
		String price = ef.toReadDataFromExcel("Product", 1, 2);	
		WebElement Price= driver.findElement(By.name("price"));
		Price.click();
		Price.clear();
		Price.sendKeys(price);
		Thread.sleep(1000);
		
        WebElement vendor = driver.findElement(By.name("vendorId"));
		Select sc1 = new Select(vendor);
		sc1.selectByValue("VID_001");
		Thread.sleep(2000);
		
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		
		
        WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));

        wd.waitforVisibilityOfElement(driver, toastMsg);
		
		String msg = toastMsg.getText();
		System.out.println("Toast Message : "+msg);
	
		driver.close();	
	}

}
