	package com.tutorialsninjaqa.testcases;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsnijaqa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninjaqa.utils.Utilities;

public class RegisterTest extends Base{
	
	public WebDriver driver;
	RegisterPage rp;
	AccountSuccessPage ap; 
	
	public RegisterTest(){
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver=intializeWebdriverandOpenApplication(prop.getProperty("browserName"));
		HomePage hp=new HomePage(driver);
		//clicking on the MyAccount
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		hp.clickonMyAccount();
		rp=hp.selectRegisterOption();
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	
	@Test
	public void verifyRegisteringAnAccountWithMandatoryFields(){
		
		rp.enterfirstNameField(dataProp.getProperty("firstName"));
		rp.enterlastNameField(dataProp.getProperty("lastName"));
		rp.enteremailField(Utilities.generateEmailWithTimeStamp());
		rp.entertelephoneField(dataProp.getProperty("telephone"));
		//rp.enteremailField(prop.getProperty("validEmail"));
		rp.enterpasswordField(prop.getProperty("validPassword"));
		rp.enterpasswordConfirmField(prop.getProperty("validPassword"));
		rp.clicknewsLetterField();;

		rp.selectagreeField();
		ap=rp.clickcontinueField();
		
		String actualSuccessMessage=ap.accountSuccessPageHeading();
		Assert.assertEquals(actualSuccessMessage, "Your Account Has Been Created!");
		
		
}
	
@Test(priority=2)	
public void verifyRegisteringAccountbyProvidingAllFields() {
	
	
	rp.enterfirstNameField(dataProp.getProperty("firstName"));
	rp.enterlastNameField(dataProp.getProperty("lastName"));
	rp.enteremailField(Utilities.generateEmailWithTimeStamp());
	//rp.emailField(prop.getProperty("validEmail"));
	rp.entertelephoneField(dataProp.getProperty("telephone"));

	rp.enterpasswordField(prop.getProperty("validPassword"));
	rp.enterpasswordConfirmField(prop.getProperty("validPassword"));
	rp.clicknewsLetterField();;
	rp.selectagreeField();
	ap=rp.clickcontinueField();
	
	String actualSuccessMessage=ap.accountSuccessPageHeading();
	Assert.assertEquals(actualSuccessMessage, dataProp.getProperty("accountSucessfulCreatedHeading"),"Account creation is unsuccessful");

	
}
@Test(priority=3)
private void verifyRegisteringAccountWithoutfilinganydetails() {
	
	
	rp.clickcontinueField();
	
	
	String actualPrivacyMessageWarning=rp.retrivePrivacyMessageWarning();
	Assert.assertTrue(actualPrivacyMessageWarning.contains(dataProp.getProperty("privacyPolicyWarning"))
			, "Warning Message is not displayed");
	
	String actualFirstNameMessageWarning=rp.retriveFirstNameMessageWarning();
	Assert.assertTrue(actualFirstNameMessageWarning.contains(dataProp.getProperty("firstNameWarning"))
			,"FirstNameMessageWarning is not displayed");

	String actualLastNameMessageWarning=rp.retriveLastNameMessageWarning();
	Assert.assertTrue(actualLastNameMessageWarning.contains(dataProp.getProperty("lastNameWarning"))
			,"Last Name MessageWarning is not displayed");
			
	String actualEmailMessageWarning=rp.retriveEmailMessageWarning();
	Assert.assertTrue(actualEmailMessageWarning.contains(dataProp.getProperty("emailMessageWarning"))
		, "Email MessageWarning is not displayed");
	
	
	String actualTelephoneMessageWarning=rp.retriveTelephoneMessageWarning();
	Assert.assertTrue(actualTelephoneMessageWarning.contains(dataProp.getProperty("telephoneMessageWarning"))
		,"Telephone MessageWarning is not displayed"); 
	
	String actualPasswordMessageWarning=rp.retrivePasswordMessageWarning();
	Assert.assertTrue(actualPasswordMessageWarning.contains(dataProp.getProperty("passwordMessageWarning" ))
		,"Password MessageWarning is not displayed");
	
	
	
					
	
	

	
	
	
	
	
	
}
		
		
	}


