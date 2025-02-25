	package com.tutorialsninjaqa.testcases;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsnijaqa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchResultsPage;
//Adding new comment and test, practice git actions to follow to add and committ the code
public class SearchPageTest extends Base {

	public WebDriver driver;
	
	public SearchPageTest() {
		super();
		
  	 }
	@BeforeMethod
	public void setup() {
		
		driver=intializeWebdriverandOpenApplication(prop.getProperty("browserName"));

		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void VerifySearchwithValidProducts() {
		HomePage hp=new HomePage(driver);
		SearchResultsPage srp=new SearchResultsPage(driver);
		
		hp.enterSearchText(dataProp.getProperty("validProduct"));
		hp.clickSearch();
		Assert.assertTrue(srp.validresultsToDisplay(),"Valid product details are not displayed");
		
		
	}
	
	
	@Test(priority=2)
	public void VerifySearchWithIvalidProduct() {
		HomePage hp=new HomePage(driver);
		SearchResultsPage srp=new SearchResultsPage(driver);
		
		
		hp.enterSearchText(dataProp.getProperty( "invalidProduct"));
		hp.clickSearch();
		String actualSearchMessage= srp.messageeForInvalidSearchResults();
		Assert.assertEquals(actualSearchMessage,dataProp.getProperty( "noProductTextInSearchResults"));
		
		
		
	}
	
	@Test(priority=3)
	public void verifySearchWithoutanyProduct() {
		HomePage hp=new HomePage(driver);
		SearchResultsPage srp=new SearchResultsPage(driver);


		//driver.findElement(By.name("search")).sendKeys("");
		hp.clickSearch();
		String actualSearchMessage= srp.messageeForInvalidSearchResults();
		Assert.assertEquals(actualSearchMessage,dataProp.getProperty( "noProductTextInSearchResults"));

		
	}
	
}
