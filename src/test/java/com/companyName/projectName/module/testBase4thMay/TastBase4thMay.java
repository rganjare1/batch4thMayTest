package com.companyName.projectName.module.testBase4thMay;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import com.AutomationWorldByRahul.SeleniumTraining.DataCollection;
import com.AutomationWorldByRahul.SeleniumTraining.ExcelReader;
import com.companyName.projectName.module.utilities.Constants;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TastBase4thMay {
	
	// Config, OR load
	// Reports
	// driver
	// launch URL
	// close Browser
	// generate the reports
	
	public static Properties config;
	public static Properties OR;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public static ExcelReader excel;
	
	public static WebDriver driver; /// Global Driver addrss
	
	public static String testCaseName, skip;
	
	public static Hashtable <String, String> testCaseRunMode = new Hashtable<String, String>();
	
	@BeforeSuite // Before start of Test Suite- 500 Test Cases in regression suite
	public static void loadFilesAndsetUpReports() throws IOException {
		
		config = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + Constants.ConfigFile_Path);
		config.load(fis);
		System.out.println("********* Config file has been loaded ***********");
		Reporter.log("********* Config file has been loaded ***********");
		
		OR = new Properties();
		FileInputStream fis1 = new FileInputStream(System.getProperty("user.dir") + Constants.ORFile_Path);
		OR.load(fis1);
		System.out.println("********* OR file has been loaded ***********");
		Reporter.log("********* OR file has been loaded ***********");
				
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		extent = new ExtentReports(System.getProperty("user.dir")+ Constants.ExecutionReport_Path +timeStamp+".html");
		
		excel =	new ExcelReader(Constants.MasterSheet_Path);
		
		loadHashTable(testCaseRunMode, Constants.TestCase_SheetName, Constants.Col_TestCaseName, Constants.Col_RunMode);
		
	}
	
	//@BeforeMethod  // Before Starting of Each Test case
	public static void launchBrowser() {
		test = extent.startTest(testCaseName);
			
		if (config.getProperty("Browser").equals("Chrome")) {				
		System.setProperty("webdriver.chrome.driver", Constants.ChromeDriver_Path);
			driver = new ChromeDriver();
			System.out.println("Address of the Driver:- " +driver);
			test.log(LogStatus.PASS,"******* Chrome Driver has been launched ********");
			Reporter.log("******* Chrome Driver has been launched ********");
			
		} else if (config.getProperty("Browser").equals("FF")) {
			System.setProperty("webdriver.gecko.driver", Constants.Geckodriver_Path);
			driver = new FirefoxDriver();
			test.log(LogStatus.PASS ,"******* Firefox Driver has been launched ********");
			Reporter.log("******* Firefox Driver has been launched ********");
			
		} else if (config.getProperty("Browser").equals("IE")) {
			System.setProperty("webdriver.ie.driver", Constants.IEDriver_Path);
			driver = new InternetExplorerDriver();
		    test.log(LogStatus.PASS ,"******* IE Driver has beenlaunched ***********");
		    Reporter.log("******* IE Driver has been launched ********");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		
		driver.get(config.getProperty("ApplicationURL"));
		test.log(LogStatus.PASS,"User is redirested to Application URL:- " + config.getProperty("ApplicationURL"));
		Reporter.log("User is redirested to Application URL:- " + config.getProperty("ApplicationURL"));
		
		test.log(LogStatus.PASS,"Starting execution of Test Case:- "+testCaseName);
		Reporter.log("Starting execution of Test Case:- "+testCaseName);
	}
	
	// @AfterMethod
	public static void closeBrowser(){
		//Thread.sleep(2000);
		driver.quit();
			
	//	test.log(LogStatus.PASS ,"*** Script execution is completed and Driver has been closed ***");
	//	Reporter.log("*** Script execution is completed and Driver has been closed ***");
	//	extent.endTest(test);	
	}
	
	@AfterSuite(alwaysRun = true)
	public static void writeIntoExtentReport() throws InterruptedException {		
		extent.endTest(test);
		extent.flush();
		
	}
	
	public static void loadHashTable(Hashtable<String, String> htdata, String SheetName, String KeyCol, String valueCol){
		
		int row = excel.getRowCount(SheetName);
		
		for(int i= 2; i<=row; i++) {
			
			String key = excel.getCellData(SheetName, KeyCol, i);
			
			String val = excel.getCellData(SheetName, valueCol, i);
			
			htdata.put(key, val);		
		}
		
		System.out.println(htdata);	
	}

   public static boolean isExecutable(String TC_name) {
	   testCaseName = TC_name;
		if(testCaseRunMode.get(TC_name).equalsIgnoreCase("Y")) {
			skip="No" ;
			return true;	
			} else {
	    	skip="Yes" ;
		   return false;
	    }
	}
   
   @DataProvider 	
	public static Object[][] Data_Collections() {
		
		DataCollection dc = new DataCollection(excel, Constants.TestData_SheetName, testCaseName);
		
		return dc.dataArray();
		// Hash Table		
	}	
	
}
