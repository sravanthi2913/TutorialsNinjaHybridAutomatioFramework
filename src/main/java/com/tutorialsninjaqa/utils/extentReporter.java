package com.tutorialsninjaqa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class  extentReporter	 {
	public static ExtentReports generateExtentReport() {
		
		ExtentReports ex=new ExtentReports();
		
		//Invoking the spark reporter and setting the created file path in test-output folder
		File extentReportFile = new File(System.getProperty("user.dir") + "/test-output/ExtentReports/extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		//Accessing the spark reporter methods
		sparkReporter.config().setTheme(Theme.DARK);
		
		sparkReporter.config().setReportName("Automation Test Results");
		sparkReporter.config().setDocumentTitle("Tutorials Ninja Automation Project Report");
		
		sparkReporter.config().setTimeStampFormat("dd/MM/YY hh:mm:ss");
		
		//Now we have to attach the extent reporter to the spark reporter
		
		ex.attachReporter(sparkReporter);
		
		//To Display few more details like Application URL, USername, Password etc we follow the following way
		
		Properties configProp=new Properties();
		File configPropFile=new File("C:\\Users\\srava\\eclipse-workspace\\TutorialsNinjaProj\\src\\main\\java\\com\\tutorials\\qa\\config\\config.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(configPropFile);
			configProp.load(fis);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		///These 4 lines actually helps to put the extra information in our extent report
		ex.setSystemInfo("Application URL",configProp.getProperty("url"));
		ex.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
		ex.setSystemInfo("Valid Email", configProp.getProperty("validEmail"));
		ex.setSystemInfo("valid Password", configProp.getProperty("validPassword"));

		//In the following section we are going include different System specification using the mentioned code.This enable 
		//the user to have more details in the extent report 
		
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("user.name"));
		System.out.println(System.getProperty("java.version"));
		
		return ex;

		
		
		
		
		
	
	
	}

}
