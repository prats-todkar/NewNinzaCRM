package com.ninza.crm.campaignstest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ninza.crm.baseclass.BaseClass;
import com.ninza.crm.baseclass.BaseClassWithGroups;
import com.ninza.crm.objectrepository.CampaignsPage;
import com.ninza.crm.objectrepository.CreateCampaignPage;
import com.ninza.crm.objectrepository.HomePage;

@Listeners(com.ninza.crm.listenerutility.ListenerImplementationNew.class)
public class CreateCampaignTestWithGroups extends BaseClassWithGroups {
	
	@Test(groups = "smoke")
	public void createCamapignWithMandataoryFieldsTest() throws EncryptedDocumentException, IOException {


		String campaignName = ef.toReadDataFromExcel("Campaigns", 1, 1);
		String targetSize = ef.toReadDataFromExcel("Campaigns", 1, 2);

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
	
	}

	@Test(groups = "regression")
	public void createCampaignWithExpectedCloseDateTest() throws EncryptedDocumentException, IOException {
	

		String campaignName = ef.toReadDataFromExcel("Campaigns", 1, 1);
		String targetSize = ef.toReadDataFromExcel("Campaigns", 1, 2);

		
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

		// get date after 30 days
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
		
	}

 

}
