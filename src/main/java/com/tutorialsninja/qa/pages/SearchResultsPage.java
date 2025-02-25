package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {
	
	WebDriver driver;
	
	@FindBy(linkText="HP LP3065")
	private WebElement resultsToDisplay ;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement invalidresultMessageToDisplay;
	
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	} 
	
	//To PErform Actions
	
	public boolean validresultsToDisplay() { 
		boolean displayStatus=resultsToDisplay.isDisplayed();
		return displayStatus;
		
	}
	
	public String messageeForInvalidSearchResults() {
		
		String invalidProductDisplaymessage=invalidresultMessageToDisplay.getText();
		return invalidProductDisplaymessage;
		
		
	}

}
