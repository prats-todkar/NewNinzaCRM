package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	
WebDriver driver;

public ProductsPage(WebDriver driver) {               //constructor
		
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath = "//span[text() = 'Add Product']")
	private WebElement addProduct;

	public WebElement getaddProduct() {
		return addProduct;
	}
	

}
