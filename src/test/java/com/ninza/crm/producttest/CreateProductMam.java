package com.ninza.crm.producttest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.ninza.crm.baseclass.BaseClass;
import com.ninza.crm.objectrepository.CreateProductsPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.ProductsPage;

public class CreateProductMam extends BaseClass{
	
	@Test
	public void CreateProductWithMandatoryFieldTest() throws IOException, InterruptedException {
	
		
		// Click on product link
		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();			
				
		// click add product button
		ProductsPage pp = new ProductsPage(driver);
		pp.getaddProduct().click();	
		
		int ranNum = ju.getRandomNumber();

		String productName = ef.toReadDataFromExcel("Product", 1, 0) + ranNum;
		String qty = ef.toReadDataFromExcel("Product", 1, 1);
		String price = ef.toReadDataFromExcel("Product", 1, 2);

		
		// Create product
		CreateProductsPage cpp = new CreateProductsPage(driver);
		cpp.getProductName().sendKeys(productName);
		WebElement quantity = cpp.getQuantity();
		quantity.clear();
		quantity.sendKeys(qty);
		WebElement pricepp = cpp.getPrice();
		pricepp.clear();
		pricepp.sendKeys(price);

		WebElement category = driver.findElement(By.name("productCategory"));	
		Select sc = new Select(category);
		sc.selectByValue("Electronics");
		
		
		WebElement vendor = driver.findElement(By.name("vendorId"));
		Select sc1 = new Select(vendor);
		sc1.selectByValue("VID_466");
		Thread.sleep(500);
		
		cpp.getAddProdButton().click();

		// Verify the succesfull message
		WebElement toastMsg = hp.getToastMsg();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));

		String msg = toastMsg.getText();

		if (msg.contains("Successfully Added")) {
			System.out.println("Product created successfully");
		} else {
			System.out.println("Product is not created");
		}
		
		hp.getCloseToastMsgBtn().click();
		
	}



}
