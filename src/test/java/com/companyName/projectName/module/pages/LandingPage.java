package com.companyName.projectName.module.pages;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.companyName.projectName.module.testBase4thMay.TastBase4thMay;
import com.companyName.projectName.module.utilities.CommonMethods;
import com.relevantcodes.extentreports.LogStatus;

public class LandingPage extends TastBase4thMay {

	WebDriver driver; // Landing page driver -- local Driver

	public LandingPage(WebDriver driver) { // TestBase Driver -- Global Driver
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[text()='crm']")
	WebElement logoText;
	@FindBy(xpath = "//a[@class='login']")
	WebElement signInBtn;
	@FindBy(xpath = "//input[@id='email_create']")
	WebElement emailID;
	@FindBy(xpath = "//button[@id='SubmitCreate']")
	WebElement createAnAccntBtn;
	@FindBy(xpath = "//input[@id='id_gender1']")
	WebElement Mrtitle;
	@FindBy(xpath = "//input[@id='id_gender2']")
	WebElement Mrstitle;
	@FindBy(xpath = "//input[@id='customer_firstname']")
	WebElement first_Name;
	@FindBy(xpath = "//input[@id='customer_lastname']")
	WebElement last_Name;
	@FindBy(xpath = "//input[@id='passwd']")
	WebElement password;
	@FindBy(xpath = "//button[@id='submitAccount']")
	WebElement registerBtn;
	@FindBy(xpath = "//*[@class='alert alert-danger']")
	WebElement errorMsg;

	public void verifyRedirectedURL() {

		String actual_title = driver.getTitle();

		// Verification point in Manual Test case
		Assert.assertEquals(actual_title, "My Store");
		test.log(LogStatus.PASS, "The Title of the page is :-" + actual_title);
		Reporter.log("The Title of the page is :-" + actual_title);
		CommonMethods.takeScreenShot();

	}

	public void signUpToApplication(Hashtable<String, String> htdata) {

		signInBtn.click();
		test.log(LogStatus.PASS, "signIn button is clicked.");
		Reporter.log("signIn button is clicked.");

		emailID.sendKeys(htdata.get("Email_ID"));
		CommonMethods.takeScreenShot();
		test.log(LogStatus.PASS, "Email ID has been entered.");
		Reporter.log("Email ID has been entered.");

		createAnAccntBtn.click();
		test.log(LogStatus.PASS, "create an account button is clicked.");
		Reporter.log("create an account button is clicked.");

		Mrtitle.click();
		test.log(LogStatus.PASS, "Mr. Title is selected.");
		Reporter.log("Mr. Title is selected.");

		first_Name.sendKeys(htdata.get("First_Name"));
		test.log(LogStatus.PASS, "first_Name has been entered.");
		Reporter.log("first_Name has been entered.");

		last_Name.sendKeys(htdata.get("Last_Name"));
		test.log(LogStatus.PASS, "last_Name has been entered.");
		Reporter.log("last_Name has been entered.");

		password.sendKeys(htdata.get("Password"));
		test.log(LogStatus.PASS, "password has been entered.");
		Reporter.log("password has been entered.");
		CommonMethods.takeScreenShot();
	}

	public void registerUser() {

		registerBtn.click();
		test.log(LogStatus.PASS, "Register button is clicked.");
		Reporter.log("Register button is clicked.");

		if (errorMsg.isDisplayed())
		/*test.log(LogStatus.FAIL, "Please enter all required fields");
		Reporter.log("Please enter all required fields");
		CommonMethods.takeScreenShotFail();*/
	
		Assert.fail("Please enter all required fields");

	}
}
