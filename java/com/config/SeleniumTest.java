package com.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.login.LoginPage;
import com.logout.LogoutPage;
//import com.report.ScreenshotListener;
import com.report.ScreenshotListener;

/**
 * Base test for a selenium test.
 * <p>
 * Performs test setup, property loading, web driver configuration, url targets.
 * Also contains convenience methods to assist in writing cleaner tests.
 */
public class SeleniumTest {

	private static final String PARAMETER_PROPERTY_FILE_DIR = "resources/parameters/";
	private static final String PARAMETER_PROPERTY_FILE_EXTENSION = ".params.properties";
	private static final String SELENIUM_HOST_URL_KEY = "selenium.host.url";
	private static final String SELENIUM_BROWSER_NAME_KEY = "selenium.browser.name";
	private static final String OCTOPUS_URL = "octopus_url";
	private static final String BROWSERSTACK_RUN = "browserstack.run";
	private static final String BROWSER_STACK_BROWSER_NAME = "browserStack_browser_name";
	
	private static final String TARGET_URL_KEY = "target.url";
//	private static final String NAVBAR_USER_ID = "navbar-user-a";
	private static final String BROWSER_STACK_USERNAME = "browserStack_username";
	private static final String BROWSER_STACK_AUTOMATE_KEY = "browserStack_accessKey";
	private static final String LOGIN_NAME="login.username";
	private static final String LOGIN_PASSWORD="login.password";
	
	public static WebDriver driver = null;
	public static String seleniumServerURL = "http://localhost:4444/wd/hub"; // default
	private static String seleniumBrowserName = "chrome"; // default
	protected static String targetBaseURL = "http://localhost:3000"; // default
	private static String browserstackRun = "false"; // default
	public static String browserstackUsername = ""; // default
	public static String browserstackAutomateKey = ""; // default
	public static String sessionID = "";
	public static String username = ""; // default
	public static String password = ""; // default
	public static String browserStackBrowserName = ""; // default
	public static String buildName = "UIAutomation";
	public static String URL = "https://" + browserstackUsername + ":" + browserstackAutomateKey
			+ "@hub-cloud.browserstack.com/wd/hub";
	public static String gitCommitId = "test";
	private LoginPage loginPage;

	private static Properties loadPropertyFile(InputStream input) {
		if (input == null)
			throw new IllegalArgumentException("Must have a valid file to load properties from.");

		Properties properties = new Properties();
		try {
			// load a properties file
			properties.load(input);
		} catch (IOException ex) {
			throw new RuntimeException("Unable to load properties file!", ex);
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}

	/**
	 * Base url of the selenium server. Example: http://localhost:4444/wd/hub
	 * 
	 * @return the selenium server url
	 */
	public static String getSeleniumServerURL() {
		return seleniumServerURL;
	}

	/**
	 * Base url of the application. Example: http://localhost:3000/
	 * 
	 * @return the base url string
	 */
	public static String getTargetBaseURL() {
		return targetBaseURL;
	}

	/**
	 * Load all the parameters in the parameters directory into system properties.
	 */
	@BeforeSuite(alwaysRun = true)
	public void load_parameters() {
		loadClasspathPropertyFiles(PARAMETER_PROPERTY_FILE_DIR, PARAMETER_PROPERTY_FILE_EXTENSION);
	}

	/**
	 * Load system properties.
	 */
	@BeforeSuite(alwaysRun = true)
	public void load_config_properties() {

		loadClasspathPropertyFiles(PARAMETER_PROPERTY_FILE_DIR, PARAMETER_PROPERTY_FILE_EXTENSION);
		if (System.getProperty(SELENIUM_HOST_URL_KEY) != null)
			seleniumServerURL = System.getProperty(SELENIUM_HOST_URL_KEY);

		if (System.getProperty(SELENIUM_BROWSER_NAME_KEY) != null)
			seleniumBrowserName = System.getProperty(SELENIUM_BROWSER_NAME_KEY);

		if (System.getProperty(OCTOPUS_URL) != null) {
			targetBaseURL = System.getProperty(OCTOPUS_URL);
		} 

		if (System.getProperty(TARGET_URL_KEY) != null) {
			targetBaseURL = System.getProperty(TARGET_URL_KEY);
		}
		
		if (System.getProperty(LOGIN_NAME) != null) {
			username = System.getProperty(LOGIN_NAME);
		}
		
		if (System.getProperty(LOGIN_PASSWORD) != null) {
			password = System.getProperty(LOGIN_PASSWORD);
		}

//		if (System.getProperty(OCTOPUS_BROWSERSTACK_RUN) != null) {
//			browserstackRun = System.getProperty(OCTOPUS_BROWSERSTACK_RUN);
//		} 
		if (System.getProperty(BROWSERSTACK_RUN) != null) {
			browserstackRun = System.getProperty(BROWSERSTACK_RUN);
		}

		if (System.getProperty(BROWSER_STACK_USERNAME) != null)
			browserstackUsername = System.getProperty(BROWSER_STACK_USERNAME);

		if (System.getProperty(BROWSER_STACK_AUTOMATE_KEY) != null)
			browserstackAutomateKey = System.getProperty(BROWSER_STACK_AUTOMATE_KEY);


		if (System.getProperty(BROWSER_STACK_BROWSER_NAME) != null)
			browserStackBrowserName = System.getProperty(BROWSER_STACK_BROWSER_NAME);

		URL = "https://" + browserstackUsername + ":" + browserstackAutomateKey + "@hub-cloud.browserstack.com/wd/hub";

	}

	@SuppressWarnings("unused")
	@BeforeSuite(alwaysRun = true, timeOut = 250000, dependsOnMethods = { "load_parameters", "load_config_properties" })
	public void open_web_driver() throws MalformedURLException, InterruptedException, FileNotFoundException {
		versionInformation();
		URL host;
		try {
			host = new URL(seleniumServerURL);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		if (browserstackRun.equals("false")) {
			try {
				String osName = System.getProperty("os.name");
				if (osName.contains("Windows")) {
						intilizeBrowserForWindows();
				} else if (osName.contains("Linux")) {
					intilizeBrowserForLinux();
				}

				else if (osName.contains("Mac")) {
					intilizeBrowserForMac();
				}

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	            //driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
	            //driver.manage().timeouts().setScriptTimeout(3L, TimeUnit.SECONDS);

				driver.manage().window().maximize();
				driver.get(targetBaseURL);
			} catch (WebDriverException e) {
				System.out.println(e.getMessage());
			} catch (Throwable t) {
				// Selenium puts blank lines in its exceptions. Remove them and
				// rethrow.
				throw new RuntimeException(t.getMessage().replaceAll("(?m)^[ \t]*\r?\n", ""));
			}
		} else {
			intilizeBrowserStackConfigs();

		}
		
		login_into_application();

	}

	private void login_into_application() {
		loginPage = PageFactory.initElements(driver, LoginPage.class);
													
			loginPage
				.enter_username(username)
				.enter_password(password)
				.press_login_button();
				
	}

	private void intilizeBrowserForWindows() {
		if (seleniumBrowserName.equalsIgnoreCase("firefox")) {
			File file = new File("drivers\\geckodriver.exe");
			System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
		} else if (seleniumBrowserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.addArguments("disable-infobars");
			options.addArguments("-incognito");
			options.addArguments("start-maximized");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-gpu");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-browser-side-navigation");
			options.addArguments("--disable-dev-shm-usage");
			// options.setCapability("requireWindowFocus", true);
			File file = new File("drivers/chromedriver-windows.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			driver = new ChromeDriver(options);

		} else if (seleniumBrowserName.equalsIgnoreCase("IE")) {
			File file = new File("drivers/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requireWindowFocus", true);
			driver = new InternetExplorerDriver(capabilities);
		} else if (seleniumBrowserName.equalsIgnoreCase("edge")) {
			File file = new File("drivers/MicrosoftWebDriver.exe");
			System.setProperty("webdriver.edge.driver", file.getAbsolutePath());
			DesiredCapabilities capabilitiesEdge = DesiredCapabilities.edge();
			driver = new EdgeDriver(capabilitiesEdge);

		}

	}

	private void intilizeBrowserForLinux() {
		if (seleniumBrowserName.equalsIgnoreCase("firefox")) {

			File file = new File("drivers/geckodriver-linux");
			System.setProperty("webdriver.firefox.marionette", file.getAbsolutePath());
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
		} else if (seleniumBrowserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.addArguments("disable-infobars");
			options.addArguments("-incognito");
			// options.setCapability("requireWindowFocus", true);
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-gpu");
			File file = new File("drivers/chromedriver-linux");
			System.out.println("Chrome Driver path: " + file.getAbsolutePath());
			 //System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
			driver = new ChromeDriver(options);
		}

	}

	private void intilizeBrowserForMac() {
		if (seleniumBrowserName.equalsIgnoreCase("firefox")) {
			File file = new File("drivers/geckodriver-linux");
			System.setProperty("webdriver.firefox.marionette", file.getAbsolutePath());
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
		} else if (seleniumBrowserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.addArguments("disable-infobars");
			options.addArguments("-incognito");
			// options.setCapability("requireWindowFocus", true);
			options.addArguments("--no-sandbox");
			File file = new File("drivers/chromedriver");
			System.out.println("Chrome Driver path: " + file.getAbsolutePath());
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			driver = new ChromeDriver(options);
		}
	}

	private void intilizeBrowserStackConfigs() throws MalformedURLException {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	//	String browserName = System.getProperty("browserStackBrowserName");
		String browserVersion = System.getProperty("browserStack.browser.version");
		String OS_Name = System.getProperty("browserStack.os.name");
		String osVersion = System.getProperty("browserStack.os.versions");
		String resolution = System.getProperty("browserStack.resolution");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("project", "MC_DIGITAL_UI_Automation");
		capabilities.setCapability("name", "UI_Automation_" + timeStamp);
		capabilities.setCapability("browserstack.local", false);
		capabilities.setCapability("browserstack.debug", true);
		capabilities.setCapability("browserstack.console", "verbose");
		capabilities.setCapability("browserstack.networkLogs", true);
		capabilities.setCapability("browserstack.video", true);
		capabilities.setCapability("resolution", resolution);
		capabilities.setCapability("browser", browserStackBrowserName);
//		capabilities.setCapability("browser_version", browserVersion);
		capabilities.setCapability("os", OS_Name);
		capabilities.setCapability("os_version", osVersion);
		capabilities.setCapability("build", buildName);
		driver = new RemoteWebDriver(new URL(URL), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(targetBaseURL);
		driver.navigate().to(targetBaseURL);;
//		driver.navigate().refresh();
		((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
		sessionID = ((RemoteWebDriver) driver).getSessionId().toString();
	}

	/**
	 * Attempts to close browser always after execution all the test cases Method
	 * call to mark test cycle in browser stack as pass/fail
	 * 
	 * @throws Exception
	 * @throws URISyntaxException
	 * @throws UnsupportedEncodingException
	 */
	@AfterSuite(alwaysRun = true)
	public void close_web_driver() throws UnsupportedEncodingException, URISyntaxException, Exception {
		if (browserstackRun.equals("true"))
			setTestStatusOnBrowserStack(ScreenshotListener.testStatus);
		if (driver != null)
			driver.quit();
	}

	/**
	 * @param status
	 * @throws URISyntaxException
	 * @throws UnsupportedEncodingException
	 * @throws IOException                  Method to mark the test cycle in browser
	 *                                      stack as passed or failed after test
	 *                                      completion.
	 * 
	 */
	public static void setTestStatusOnBrowserStack(String status)
			throws URISyntaxException, UnsupportedEncodingException, IOException {

		// Rest api call to change the status of test cycle as pass/fail

		URI uri = new URI("https://" + browserstackUsername + ":" + browserstackAutomateKey
				+ "@api.browserstack.com/automate/sessions/" + sessionID + ".json");
		HttpPut putRequest = new HttpPut(uri);
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add((new BasicNameValuePair("status", status)));
		nameValuePairs.add((new BasicNameValuePair("reason", "")));
		putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		HttpClientBuilder.create().build().execute(putRequest);
	}

	/**
	 * Attempts to perform a logout. Will function even if no user is logged in.
	 * @throws FileNotFoundException 
	 */
	protected LoginPage perform_logout() throws FileNotFoundException {
		String pageName="Logout page";
		LogoutPage logoutPage = PageFactory.initElements(driver, LogoutPage.class);
		driver.manage().deleteAllCookies();
		logoutPage.navigate_to_route(pageName,10,false);
		return PageFactory.initElements(driver, LoginPage.class);
	}

	/**
	 * Skips the test if it has not yet been implemented.
	 */
	protected void skip_not_implemented() {
		throw new SkipException("Skipping test. Not yet implemented.");
	}

	/**
	 * Loads all properties files in the given resource directory with the given
	 * file extension.
	 * 
	 * @param resourceFileDir       The resource directory in which to load all
	 *                              files from.
	 * @param resourceFileExtension The extension of the file to load.
	 */
	private final void loadClasspathPropertyFiles(final String resourceFileDir, final String resourceFileExtension) {
		String path = getClass().getClassLoader().getResource("parameters/").getPath();

		// Get a list of property files with the given extension.
		File[] propertiesFiles = new File(path).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(resourceFileExtension);
			}
		});

		// For each file, open it and load each property in the file into the
		// system properties.
		for (File file : propertiesFiles) {
			try {
				final Properties properties = loadPropertyFile(new FileInputStream(file));

				for (String key : properties.stringPropertyNames()) {
					System.setProperty(key, properties.getProperty(key));
				}
			} catch (FileNotFoundException e) {
				throw new IllegalArgumentException("Unable to find property file.", e);
			}
		}
	}

	@SuppressWarnings("static-access")
	public void versionInformation() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		Object obj = new JsonParser().parse(new FileReader("resources/git.properties"));
		JsonObject jsonObject = (JsonObject) obj;
		String gitCommitId = jsonObject.get("git.commit.id").toString().trim().replace("\"", "");
		this.gitCommitId = gitCommitId;
	}

}
