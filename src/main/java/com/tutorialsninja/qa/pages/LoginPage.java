package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailAddressField;
	
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginField;
	
	@FindBy(xpath="//div[contains(@class, 'alert alert-danger alert-dismissible')]")
	private WebElement emailPasswordNotMatching;
	
	//Let's create a constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		//Intiliaze the pageFactory class to overcome StaleElementReferenceException
		PageFactory.initElements(driver, this);
		
	}
	
//Lets perform some Actions
	public void enterEmail(String emailText) {
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
		
	}
	
	public AccountPage clickonLogin() {
		loginField.click();
		return new AccountPage(driver);
	}
	
	public String retriveemailPasswordNotMatching() {
		String retrivedtext=emailPasswordNotMatching.getText();
		return retrivedtext;
		
	}
}
