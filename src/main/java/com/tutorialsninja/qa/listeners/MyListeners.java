package com.tutorialsninja.qa.listeners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.tutorialsninjaqa.utils.Utilities;
import com.tutorialsninjaqa.utils.extentReporter;

public class MyListeners implements ITestListener {
	ExtentReports ex;
	ExtentTest extentTest;
	@Override
	public void onStart(ITestContext context) {
		 ex=extentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		//The below code will actually helps to extract the testCaseName using getName method
	//	String testResult=result.getName();
		 extentTest = ex.createTest(result.getName());
		extentTest.log(Status.PASS,result.getName()+"Test started executing");
		
		//We are commenting the below line because we are not printing anymore in the console rather we are printing in the extent report
		//System.out.println(testResult+ "Test started excuting");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//String testResult=result.getName();
		 extentTest = ex.createTest(result.getName());
		 extentTest.log(Status.PASS,result.getName()+"Test started executing");
		//System.out.println(testResult+ "Test executed succesfully");
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//String testResult=result.getName();
		//
		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		//Optimizing the code
		String destinationScreenshotPath= Utilities.captureScreenshot(driver, result.getName());		
		//Adding ScreenShot to the extent report 
		 extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		 extentTest.log(Status.INFO,result.getThrowable());
		 extentTest.log(Status.FAIL, result.getName()+ "Test case execution failed");
		 
		
		//System.out.println(testResult+ "Test case execution failed");
		//System.out.println(result.getThrowable());
	  	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testResult=result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testResult+ "Test case execution is skipped");
		//System.out.println(testResult+ "Test case execution is skipped");
		
	}

	@Override
	public void onFinish(ITestContext context) {
	
		//System.out.println( "Test case execution completed");
		
		//Auto Launching the report file
		
		ex.flush();
	
	}

}

