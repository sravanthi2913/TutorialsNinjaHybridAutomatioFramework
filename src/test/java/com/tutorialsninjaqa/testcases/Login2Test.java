package com.tutorialsninjaqa.testcases;

import com.tutorialsnijaqa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninjaqa.utils.Utilities;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Login2Test extends Base {
	
	LoginPage lp;
	AccountPage ap;
	//Extending the Base class by creating the constructor
	public Login2Test() {
		super();
	}

	public WebDriver driver;

		@BeforeMethod
		public void setup()
		{
			driver=intializeWebdriverandOpenApplication(prop.getProperty("browserName"));
			
			//Creating the object of HomePageClass
			HomePage hM=new HomePage(driver);
			hM.clickonMyAccount();
			lp = hM.selectLoginOption();
			
		}

	@AfterMethod

	public void tearDown() {
		driver.quit();
	}

	@Test(priority=1,dataProvider="validCredentialssupplier")
	public void verifyLoginwithValidCredentials(String Email, String Password) {
		//Commenting this line as we are optimizing the code by retuning the LoginPage variable in HomePage selectLoginOption method.
		//LoginPage lp=new LoginPage(driver);
		
		lp.enterEmail(Email);
		lp.enterPassword(Password);
		 ap = lp.clickonLogin();
		//Please be aware we removed the hardcoded username and password by passing config properties
		
		/*--Commented to practice PageObjectModel Pattern Design
		 * 
		 * driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("validEmail");
		//driver.findElement(By.id("input-password")).sendKeys(Password);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		*/

		/* checking if the test cases is passed with the following statement*/

		Assert.assertTrue(ap.editYourAccountInformationtext(), "Edit Your Account Information is not Displayed.");

	}
	
/*	//Manually passing the data using hardcoding technique
	@DataProvider(name="validCredentialssupplier")
	public Object[][] supplyData() {
		Object[][] data= {{"sravanthidheer71234@gmail.com","12345"},{"sravanthidheer81234@gmail.com","12345"},{"sravanthidheer91234@gmail.com","12345"}};
		return data;
		
	}*/
	 
	//Passing the data using the data driven approach i.e extracting the data from the excel
	
	@DataProvider(name="validCredentialssupplier")
	public Object[][] supplyData() {
		Object[][] data= Utilities.getTestDatafromExcel("Sheet1");
		return data;
	}

	@Test(priority=2)
	public void verifyLoginwithInValidCredentials() {

		
		lp.enterEmail(Utilities.generateEmailWithTimeStamp());
		lp.enterPassword(dataProp.getProperty("invalidPassword"));
		lp.clickonLogin();

		String actualWarningmessage= lp.retriveemailPasswordNotMatching();
		String expectedWarningmessage=dataProp.getProperty("emailPasswordNoMatchWarning");



		/* checking if the test cases is passed with the following statement*/

		Assert.assertTrue(actualWarningmessage.contains(expectedWarningmessage),"Expected Warning message is not displayed");
	}


	@Test(priority=3)
	public void verifyLoginwithInValidPassword() {


		lp.enterEmail(prop.getProperty("validEmail"));
		lp.enterPassword(dataProp.getProperty("invalidPassword"));
		lp.clickonLogin();
		
		String actualWarningmessage= lp.retriveemailPasswordNotMatching();
		String expectedWarningmessage=dataProp.getProperty("emailPasswordNoMatchWarning");




		/* checking if the test cases is passed with the following statement*/

		Assert.assertTrue(actualWarningmessage.contains(expectedWarningmessage),"Expected Warning message is not displayed");
	}

	@Test(priority=4)
	public void verifyLoginwithEmptyCredentials() {

		

		//driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(" ");
		//driver.findElement(By.id("input-password")).sendKeys("");
		
		lp.clickonLogin()	;

		String actualWarningmessage= lp.retriveemailPasswordNotMatching();
		String expectedWarningmessage=dataProp.getProperty("emailPasswordNoMatchWarning");



		/* checking if the test cases is passed with the following statement*/

		Assert.assertTrue(actualWarningmessage.contains(expectedWarningmessage),"Expected Warning message is not displayed");
	}

	
}
