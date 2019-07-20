package com.companyName.projectName.module.testCases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.companyName.projectName.module.pages.LandingPage;
import com.companyName.projectName.module.testBase4thMay.TastBase4thMay;

public class TC005_Create_An_Account extends TastBase4thMay {
	
	@BeforeClass
	public static void isSkip() {
		
		if(!isExecutable("TC005_Create_an_Account"))		
			throw new SkipException("Skipping Test case as it's Run Mode is set to N");					
	}
	
	@Test(dataProvider = "Data_Collections")
	public void TC005_Create_an_Account(Hashtable <String, String> htdata) throws InterruptedException {
		
		//launchBrowser("TC001_Create_an_Account5");
	//	launchBrowser();
		//testCaseName = "loginToApplication";
		
		LandingPage lp = new LandingPage(driver); // TestBase class
		
		lp.verifyRedirectedURL();
		lp.signUpToApplication(htdata);
			
	//	closeBrowser();
		
	}
	
}
