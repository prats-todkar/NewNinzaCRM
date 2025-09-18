package com.ninza.crm.objectrepository;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SigninPage {
	
	WebDriver driver;
	
	public SigninPage(WebDriver driver) {               //constructor
		
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	
	
	@FindBy(id="username")
	WebElement userName;

	@FindBy(id="inputPassword")
	WebElement passWord;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement signIn;
	
	public WebElement getUserName() {
		return userName;
	}

	public WebElement getPassWord() {
		return passWord;
	}

	public WebElement getSignIn() {
		return signIn;
	}

	public void signInToApp(String url, String un, String pwd) {
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(url);
		getUserName().sendKeys(un);
		getPassWord().sendKeys(pwd);
		signIn.click();
	}

}
