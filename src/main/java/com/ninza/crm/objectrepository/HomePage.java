package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ninza.crm.generic.webdriverutility.WebDriverUtility;

public class HomePage {
	
    WebDriver driver;
	WebDriverUtility wlib = new WebDriverUtility();

	public HomePage(WebDriver driver) {               //constructor
		
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignsLink;
	
	@FindBy(linkText="Contacts")
	private WebElement contactsLink;
	
	@FindBy(linkText="Leads")
	private WebElement leadsLink;
	
	@FindBy(linkText="Opportunities")
	private WebElement opportunitiesLink;
	
	@FindBy(linkText="Products")
	private WebElement productsLink;

	@FindBy(linkText="Quotes")
	private WebElement quotesLink;
	
	@FindBy(linkText="Purchase Order")
	private WebElement purchaseOrderLink;
	
	@FindBy(linkText="Sales Order")
	private WebElement salesOrderLink;
	
	@FindBy(linkText="Invoice")
	private WebElement invoiceLink;
	
	@FindBy(linkText="Admin Console")
	private WebElement adminConsoleLink;
	
	@FindBy(xpath="//span[text()='Create Campaign']") 
	private WebElement createcampaignBtn; 
	
	@FindBy(xpath = "//div[@role='alert']")
	private WebElement toastMsg;
	
	@FindBy(xpath = "//button[@aria-label='close']")
	private WebElement closeToastMsgBtn;
	
	@FindBy(className = "user-icon")
	private WebElement userIcon;
	
	@FindBy(xpath = "//div[text()='Logout ']")
	private WebElement logoutBtn;

	public WebElement getCampaignsLink() {
		return campaignsLink;
	}

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getLeadsLink() {
		return leadsLink;
	}

	public WebElement getOpportunitiesLink() {
		return opportunitiesLink;
	}

	public WebElement getProductsLink() {
		return productsLink;
	}

	public WebElement getQuotesLink() {
		return quotesLink;
	}

	public WebElement getPurchaseOrderLink() {
		return purchaseOrderLink;
	}

	public WebElement getSalesOrderLink() {
		return salesOrderLink;
	}

	public WebElement getInvoiceLink() {
		return invoiceLink;
	}

	public WebElement getAdminConsoleLink() {
		return adminConsoleLink;
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebDriverUtility getWlib() {
		return wlib;
	}

	public WebElement getCreatecampaignBtn() {
		return createcampaignBtn;
	}

	public WebElement getToastMsg() {
		return toastMsg;
	}

	public WebElement getCloseToastMsgBtn() {
		return closeToastMsgBtn;
	}

	public WebElement getUserIcon() {
		return userIcon;
	}

	public WebElement getLogoutBtn() {
		return logoutBtn;
	}
	
	
	public void logout() {
		
		wlib.mouseHoverOnWebelement(driver, userIcon);
		wlib.ClickOnWebelement(driver, logoutBtn);
		//driver.quit(); 
	}
	

}
