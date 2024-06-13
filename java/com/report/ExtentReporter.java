package com.report;

import static com.config.SeleniumTest.gitCommitId;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.config.SeleniumTest;

/**
 * Generates the extent report html.
 */
public class ExtentReporter implements IReporter {
	protected static final String EXTENT_CONFIG_FILE = "extent-config.xml";
	protected static final String BASE_PAGES_PACKAGE = "com.demo.pages";

	ExtentSparkReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		String reportFile = null;
		reportFile = System.getProperty("user.dir") + "/target" + File.separator + gitCommitId+".html";
		htmlReporter = new ExtentSparkReporter(reportFile);
		//new stuff
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("GPC Regression Report");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		try {
			htmlReporter.loadXMLConfig(new File(getClass().getClassLoader().getResource(EXTENT_CONFIG_FILE).getFile()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("OS Version", System.getProperty("browserStack.os.versions"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("Server Under Test", SeleniumTest.getTargetBaseURL());
		extent.setSystemInfo("Browser Under Test",System.getProperty("selenium.browser.name"));
		extent.setSystemInfo("Selenium Server", SeleniumTest.seleniumServerURL);

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				try {
					buildTestNodes(context.getPassedTests(), Status.PASS);
					buildTestNodes(context.getFailedTests(), Status.FAIL);
					buildTestNodes(context.getSkippedTests(), Status.SKIP);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

		Reporter.log("Automation Report: " + reportFile, true);
		extent.flush();
	}

	private void buildTestNodes(IResultMap tests, Status status) throws IOException {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				String description = result.getMethod().getDescription().replace("\n", "<br>");
				test = extent.createTest(result.getTestClass().getRealClass().getSimpleName() + " -> "
						+ result.getMethod().getMethodName(), description);

				List<String> testngReporterLogs = Reporter.getOutput(result);
				for (String testngLog : testngReporterLogs)
					test.log(Status.INFO, testngLog);

				// Tag as functional/integration/longerintegration.
				for (String group : result.getMethod().getGroups())
					test.assignAuthor(group);

				// Tag the page being tested using the package name.

				String instanceName = result.getInstanceName();
				instanceName = instanceName.replace(BASE_PAGES_PACKAGE, "");
				String[] pageCategory = instanceName.split("\\.");

				// Assign base package category to test
				if (pageCategory != null && pageCategory.length > 0) {
					test.assignCategory(pageCategory[0]);

					// Assign sub package (1 level down) to test
					if (pageCategory.length > 1)
						test.assignCategory(pageCategory[1]);
				}

				// Attach screenshot if one exists.
				

				if (result.getStatus() == ITestResult.FAILURE) {
					test.log(Status.FAIL, result.getName());
					test.log(Status.FAIL, result.getThrowable());
					test.fail("Screen Shot : " + test.addScreenCaptureFromPath(result.getMethod().getMethodName() +".png"));
					System.out.println("############## FAIL Test SnapShot Attached to Report #####################################");
				} else if (result.getStatus() == ITestResult.SUCCESS) {
					test.log(Status.PASS, result.getName());
					test.pass("Screen Shot : " + test.addScreenCaptureFromPath(result.getMethod().getMethodName() +".png"));
				} else if (result.getStatus() == ITestResult.SKIP) {
					test.skip("Test Case : " + result.getName() + " has been skipped");
					test.skip("Screen Shot : " + test.addScreenCaptureFromPath(result.getMethod().getMethodName() + "skip" +".png"));
                    System.out.println("############## SKIP Test SnapShot Attached to Report #####################################");
				}

				if (result.getThrowable() != null) {
					String throwableMessageSimple = result.getThrowable().getMessage();
					if (throwableMessageSimple != null && !throwableMessageSimple.isEmpty()
							&& throwableMessageSimple.indexOf('\n') > 0) {
						throwableMessageSimple = throwableMessageSimple.substring(0,
								throwableMessageSimple.indexOf('\n'));
						test.log(status, throwableMessageSimple + "<br>Exception: ");
					}
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}

				extent.flush();
			}
		}
	}

}
