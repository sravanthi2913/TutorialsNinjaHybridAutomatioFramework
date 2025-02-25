package com.tutorialsnijaqa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninjaqa.utils.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	

	//This piece of code will load the config properties file to the method
	public  Base() {
		 prop=new Properties();
		 dataProp=new Properties();
		//File file1=new File(System.getProperty("C:\\Users\\srava\\eclipse-workspace\\TutorialsNinjaProj\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties"));
		
		try {
			FileInputStream fis=new FileInputStream("C:\\Users\\srava\\eclipse-workspace\\TutorialsNinjaProj\\src\\main\\java\\com\\tutorials\\qa\\config\\config.properties");
			prop.load(fis);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			FileInputStream fis1=new FileInputStream("C:\\Users\\srava\\eclipse-workspace\\TutorialsNinjaProj\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
			dataProp.load(fis1);;
		}catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	public WebDriver intializeWebdriverandOpenApplication(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
			
				}else
				if (browserName.equalsIgnoreCase("Safari")) {
					driver=new SafariDriver();
					
				}else
					if(browserName.equalsIgnoreCase("FireFox")){
					driver=new FirefoxDriver();
					
				}
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));	
		
		//Removed the hardcoded URL link and included the parameter of properties file
		driver.get(prop.getProperty("url"));

		return driver;
	}
}
