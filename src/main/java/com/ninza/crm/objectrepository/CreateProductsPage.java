package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProductsPage {
	
	WebDriver driver; 
	 
	 public CreateProductsPage(WebDriver driver) 
	 { 
		 
	  this.driver=driver; 
	  PageFactory.initElements(driver,this); 
	   
	 } 
	 
	 @FindBy(name = "productId") 
	 private WebElement productId; 
	 
	 @FindBy(name = "productName") 
	 private WebElement productName; 
	 
	 @FindBy(name = "productCategory") 
	 private WebElement prodCategory; 
	 
	 @FindBy(name = "quantity") 
	 private WebElement quantity; 
	 
	 @FindBy(name = "price") 
	 private WebElement price; 
	 
	 @FindBy(name = "vendorId") 
	 private WebElement vendorId; 
	  
	 @FindBy(xpath="//button[text()='Add']") 
	 private WebElement addProdButton; 
	 
	 public WebElement getAddProdButton() { 
	  return addProdButton; 
	 } 
	 
	 public WebElement getProductId() { 
	  return productId; 
	 } 
	 
	 public WebElement getProductName() { 
	  return productName; 
	 } 
	 
	 public WebElement getProdCategory() { 
	  return prodCategory; 
	 } 
	 
	 public WebElement getQuantity() { 
	  return quantity; 
	 } 
	 
	 public WebElement getPrice() { 
	  return price; 
	 } 
	 
	 public WebElement getVendorId() { 
	  return vendorId; 
	 }

}
