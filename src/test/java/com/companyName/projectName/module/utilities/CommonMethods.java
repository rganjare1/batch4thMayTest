package com.companyName.projectName.module.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import com.companyName.projectName.module.testBase4thMay.TastBase4thMay;
import com.relevantcodes.extentreports.LogStatus;

public class CommonMethods extends TastBase4thMay {

	public static void takeScreenShot() {

		String formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date());

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String target = new File(System.getProperty("user.dir")) + "\\src\\test\\resources\\screenShots\\Passed\\"
					+testCaseName+ "_" + formater + ".png";

			File destFile = new File(target);

			// FileUtil.capy

			FileHandler.copy(source, destFile);

			test.log(LogStatus.PASS, test.addScreenCapture(target));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void takeScreenShotFail() {

		String formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date());

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String target = new File(System.getProperty("user.dir")) + "\\src\\test\\resources\\screenShots\\Failed\\"
					+ "_" + formater + ".png";

			File destFile = new File(target);

			// FileUtil.capy

			FileHandler.copy(source, destFile);

			test.log(LogStatus.FAIL, test.addScreenCapture(target));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
