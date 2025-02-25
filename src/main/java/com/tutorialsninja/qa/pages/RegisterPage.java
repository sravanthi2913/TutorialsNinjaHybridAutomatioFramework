package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement latstNameField;
	
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField ;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
 	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(xpath="//input[@value='0']")
	private WebElement newsLetterField;
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement agreeField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueField;
	
	//Lets mention the errors and warnings locators here
	
	@FindBy(xpath="//div[contains(@class, 'alert alert-danger alert-dismissible')]")
	private WebElement PrivacyMessageWarning ;
	
	@FindBy(xpath="//input[@id='input-firstname']//following-sibling::div")
	private WebElement FirstNameMessageWarning ;
	
	@FindBy(xpath="//input[@id='input-lastname']//following-sibling::div")
	private WebElement LastNameMessageWarning ;
	
	@FindBy(xpath="//input[@id='input-email']//following-sibling::div")
	private WebElement EmailMessageWarning ;

	
	@FindBy(xpath="//input[@id='input-telephone']//following-sibling::div")
	private WebElement TelephoneMessageWarning;
	
	@FindBy(xpath="//input[@id='input-password']//following-sibling::div")

	private WebElement PasswordMessageWarning  ;


	
//The following is A CONSTRUCTOR THE REGISTER PAGE CLASS TO INITIATE PAGEFACTORY CLASS
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		//Intiliaze the pageFactory class to overcome StaleElementReferenceException
		PageFactory.initElements(driver, this);
		
	}
	
	//lets perform some actions
	
	public void enterfirstNameField(String firstName) {
		firstNameField.sendKeys(firstName);
	}
	
	public void enterlastNameField(String lastName) {
		latstNameField.sendKeys(lastName);
	}
	public void enteremailField(String email) {
		emailField.sendKeys(email);
	}
	public void entertelephoneField(String telephone) {
		telephoneField.sendKeys(telephone);
		
	}
	
	public void enterpasswordField(String password) {
		passwordField.sendKeys(password);
	}
	public void enterpasswordConfirmField(String passwordConfirm) {
		passwordConfirmField.sendKeys(passwordConfirm);
		
	}
	
	public void clicknewsLetterField() {
		newsLetterField.click();
	}
	public void selectagreeField( ) {
		agreeField.click();
		
	}
	public AccountSuccessPage clickcontinueField() {
		continueField.click();
		return new AccountSuccessPage(driver);
		
	}
	
public String retrivePrivacyMessageWarning() {
	String privacyWarningtext=PrivacyMessageWarning.getText();
	return privacyWarningtext;
	
}

public String retriveFirstNameMessageWarning() {
	String firstNameWarningtext=FirstNameMessageWarning.getText();
	return firstNameWarningtext;
}


public String retriveLastNameMessageWarning() {
	String lastNameWarningtext=LastNameMessageWarning.getText();
	return lastNameWarningtext;
}

public String retriveEmailMessageWarning() {
	String EmailMessageWarningtext=EmailMessageWarning.getText();
	return EmailMessageWarningtext;
}

public String retriveTelephoneMessageWarning() {
	String TelephoneMessageWarningtext=TelephoneMessageWarning.getText();
	return TelephoneMessageWarningtext;
}


public String retrivePasswordMessageWarning() {
	String PasswordMessageWarningtext=PasswordMessageWarning.getText();
	return PasswordMessageWarningtext;
}

}
