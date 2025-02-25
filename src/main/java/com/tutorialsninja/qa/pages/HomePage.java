package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;

	//The entire classes in this package are creating/implementing the PageObjectModel design pattern and the class that implemetns the
	//POM is PageFactory.So basically we are removing the locators and categorizing them in a location(here its HomePage)
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropDown;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	WebElement registerOption;
	
	//Locators of Search functionality that are accesible in HomePage.
	
	@FindBy(name="search")
	private WebElement searchText;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchClick;
	
	public  HomePage(WebDriver driver) {
		this.driver=driver;
		
		//To overcome the staleElementReferenceException the selenuim provided PageFactory design pattern provides a method called init method
		//Here this is referred to as HomePage.class we can write it as this.
		
		PageFactory.initElements(driver, this);//bascially this statement once called it will call the webElements and the associated locators that overcome the expected exception
		
		
	}
	
	
	// Next set we have to perform some actions like below
	
	public void clickonMyAccount() {
		myAccountDropDown.click();
	}
	
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
		
	}
	
	public RegisterPage selectRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public void enterSearchText(String searchTexttoPass) {
		searchText.sendKeys(searchTexttoPass);
	}
	
	public void clickSearch() {
		searchClick.click();
		
	}
	
}
