package com.report;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.config.SeleniumTest;

/**
 * Creates a screenshot on failed test.
 */
public class ScreenshotListener implements ITestListener {

	public final static String screenshotPath = System.getProperty("user.dir") + "/target/";
	public static String testStatus = "passed";

	public static String screenShotNameFromMethod(String methodName) {
		return methodName.trim();
	}

	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {
			final Object currentClass = result.getInstance();
			@SuppressWarnings("static-access")
			final WebDriver driver = ((SeleniumTest) currentClass).driver;
			final String methodName = screenShotNameFromMethod(result.getName());

			final File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			if (screenshot != null)
				try {
					FileUtils.copyFile(screenshot, new File(screenshotPath + methodName + ".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//			System.out.println("***Placed screen shot in:  file:///" + screenshotPath + methodName + ".png   ***");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		try {
			testStatus = "failed";
			final Object currentClass = result.getInstance();
			@SuppressWarnings("static-access")
			final WebDriver driver = ((SeleniumTest) currentClass).driver;
			final String methodName = screenShotNameFromMethod(result.getName());

			final File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			if (screenshot != null)
				FileUtils.copyFile(screenshot, new File(screenshotPath + methodName + "skip" + ".png"));
			System.out.println("***Placed screen shot in:  file:///" + screenshotPath + methodName + "skip" + ".png   ***");
		} catch (Exception e) {
			testStatus = "failed";
			System.out.println("################# Unable to take snapshot from ############" + screenshotPath);
		} // Unable to take screenshot.
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			testStatus = "failed";
			final Object currentClass = result.getInstance();
			@SuppressWarnings("static-access")
			final WebDriver driver = ((SeleniumTest) currentClass).driver;
			final String methodName = screenShotNameFromMethod(result.getName());

			final File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			if (screenshot != null)
				FileUtils.copyFile(screenshot, new File(screenshotPath + methodName + ".png"));
			System.out.println("***Placed screen shot in:  file:///" + screenshotPath + methodName + ".png   ***");
			driver.navigate().refresh();
		} catch (Exception e) {
			testStatus = "failed";
			System.out.println("################# Unable to take snapshot from ############" + screenshotPath);
		} // Unable to take screenshot.
	}

}

