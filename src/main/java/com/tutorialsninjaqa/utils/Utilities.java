package com.tutorialsninjaqa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;


public class Utilities {
	//Removing hardcording implicit and explicit values in BAse class by creating them in the Utilities class.
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=5;
	//Making this method as static so the variables can directly accessed with class rather than class instance
	public static String  generateEmailWithTimeStamp() {
		Date date=new Date();	
		String timestamp= date.toString().replace(" ", "_").replace(":", "_");
		return "sravanthidheer71234"+timestamp+"@gmail.com";
	}
	
	
	//Please remember this sheet will actually implements our Data driven approach by extracting data from excel
	public static Object[][] getTestDatafromExcel(String sheetName) {
		
		File file=new File("C:\\Users\\srava\\eclipse-workspace\\TutorialsNinjaProj\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\Practice Sheet.xlsx");
		XSSFWorkbook workBook = null ; 
		
		try {
		//We have to provide the file name and path here
		FileInputStream fisExcel=new FileInputStream(file);
		//Intializing the Workbook
		 workBook=new XSSFWorkbook(fisExcel);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		//Intializing the sheet and passing the sheetName using getSheet method
		XSSFSheet sheet=workBook.getSheet(sheetName);
		
		//now accessing the rows and columns in a sheet.
		int rows=sheet.getLastRowNum();
		int cols=sheet.getRow(0).getLastCellNum();
		
		Object[][] data=new Object[rows][cols];
		
		//iterate throught the excel fileS
		
		for (int i=0;i<rows;i++) {
		
			XSSFRow row = sheet.getRow(i+1);	
			
			for(int j=0;j<cols;j++) {
				
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				case STRING:
					data [i][j]=cell.getStringCellValue();
					break;
					
				case NUMERIC:
					//Please note we are typecasting the expected float value to int value by providing (int), later we can convereted the in to string using Interger.toString()
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					
				/*case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();*/
				}
			}
			
		}
		return data;
		
	}
	
	public static String captureScreenshot(WebDriver driver,String testName) {
		
		//To retereice the driver we have to use the above code
				File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				//Moving the screenshot from source location i.e the above line to to destination path using the following code
				String destinationScreenshotPath=System.getProperty("C:\\Users\\srava\\eclipse-workspace\\TutorialsNinjaProj\\Screenshots"+testName+".png");
				
				//Move the Screenshot from source path to destination Path or folder
				try {
					FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return destinationScreenshotPath;
				
		
	}
}
