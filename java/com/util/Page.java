package com.util;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlMatches;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.login.LoginPage;
import com.config.SeleniumTest;

/**
 * Base page object for ui pages.
 * <p>
 * A page object represents a page and includes hooks to its IDs, elements,
 * fields and more importantly implements all functionality that can be
 * performed on a page.
 * 
 * @param <T> Page object type. Example: _LoginPage
 */
public abstract class Page<T> {


	protected String URL_PATH;
	protected WebDriver driver;
	private long startTime;
	private long stopTime;
	private long elapsedTime;
	
	private static final String LOADER = ".ui.active.centered.inline.loader";
	

	/**
	 * @param driver the webdriver
	 * @param route  a partial route for the page. Example '/login'
	 */
	public Page(WebDriver driver, String route) {
		this.driver = driver;
		this.URL_PATH = route;
	}

	/**
	 * A helper method that waits for timeOutInSeconds seconds for the passed-in ID to disappear.
	 * Useful for handling modal dismissal - Give it the ID of the modal and it will
	 * wait until it's gone before trying to do anything else.
	 * 
	 * @param id The ID of the element to wait to go away
	 */
	protected void wait_for_disappearance_of(String id,int timeOutInSeconds) {
		wait_for_disappearance_Of(id, timeOutInSeconds);
	}

	/**
	 * A method which should be used to verify that the driver is really on a given
	 * route.
	 * <p>
	 * The route URL and page contents should be checked here.
	 * 
	 * @return The page object representing the result of the route.
	 */
	public abstract T verify_route();

	public LoginPage go_to_logout() {
		report_step(generate_step_descriptor());
		driver.get(build_qualified_url("/logout"));
		return PageFactory.initElements(driver, LoginPage.class).verify_route();
	}

	/**
	 * A method which can be used to navigate to the page.
	 * <p>
	 * If navigation fails, an exception will be thrown.
	 * 
	 * @return The page object representing the result of the route.
	 * @throws FileNotFoundException 
	 */
	public T navigate_to_route(String pageName, int timer, boolean checkLoader) throws FileNotFoundException {
		startTime = System.currentTimeMillis();
		report_step(generate_step_descriptor() + ", route: " + URL_PATH);
	//	driver.get(build_qualified_url(URL_PATH));
		if (checkLoader)
		wait_for_page_loader(timer);
		//wait_for_disappearance_of_modal();
	//	waitForPageLoad();
		stopTime = System.currentTimeMillis();
	    elapsedTime = stopTime - startTime;
		System.out.println("####### Current URL::" + driver.getCurrentUrl());
		long seconds = (elapsedTime / 1000) % 60;
		try (PrintWriter out = new PrintWriter("module_load_time_data.txt")) {
		    out.println("Total time taken for module: "+pageName+ " is -"+seconds+" seconds");
		}
		return verify_route();
	}

	/**
	 * A helper method that waits for the passed-in ID to disappear. Useful for
	 * handling modal dismissal - Give it the ID of the modal and it will wait until
	 * it's gone before trying to do anything else.
	 * 
	 * @param id               The ID of the element to wait to go away
	 * @param timeOutInSeconds The number of seconds to wait, at most
	 */
	protected void wait_for_disappearance_Of(String css, int timeOutInSeconds) {
		WebDriverWait wait;
			wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(css)));

	}
	
	protected void wait_for_disappearance_Of_short_wait(String css, int timeOutInSeconds) {
		WebDriverWait wait;
			wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(css)));

	}
	
	protected void wait_for_disappearance_Of_xpath(String xpath, int timeOutInSeconds) {
		WebDriverWait wait;
			wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
	}

	/**
	 * A helper method that waits for the passed-in ID to appear. Useful for
	 * handling the cases when user has to wait till the loading of an element.
	 * 
	 * @param name             The ID of the element to get loaded
	 * @param timeOutInSeconds The number of seconds to wait, at most
	 */
	protected void wait_for_appearance_of_name(String name,int timeOutInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));

	}
	
	protected void wait_upto_queue_field_clickable()
	{
		WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.className(" css-1cycwts-control")));

	}

	protected void wait_for_appearance_of_xpath(String xpathExpression,int timeOutInSeconds) {
		WebDriverWait wait;
			wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
	}

	protected void wait_for_appearance_of_css(String className,int timeOutInSeconds) {
		WebDriverWait wait;
			wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(className)));
	}
	
	protected void wait_for_clickable_of_xpath(String xpathExpression,int timeOutInSeconds) {
		WebDriverWait wait;
			wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
	}

	/**
	 * A helper method that waits 5 seconds for the passed-in ID to appear. Useful
	 * for handling the cases when user has to wait till the loading of an element.
	 * 
	 * @param id The ID of the element to get loaded
	 */
	protected void wait_for_appearance_of(String id,int timeOutInSeconds) {
		WebDriverWait wait;
			wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}
	
	protected void wait_for_presence_of(String id,int timeOutInSeconds) {
		WebDriverWait wait;
			wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
	}
	

	protected void wait_for_disappearance_of_modal() {
		// It takes about a second for modals to dismiss.
		try {
			Thread.sleep(4000);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
	}

	protected void wait_for_disappearance_of_modal_long_wait() {
		// It takes about a second for modals to dismiss.
		try {
			Thread.sleep(15000);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
	}
	
	protected void wait_for_disappearance_of_modal_short_wait() {
		// It takes about a second for modals to dismiss.
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
	}
	
	public void waitForPageLoad() {
		  untilJqueryIsDone(driver, 150L);

	}

	private static void untilJqueryIsDone(WebDriver driver, Long timeoutInSeconds) {
		until(driver, (d) -> {
			Boolean isJqueryCallDone = (Boolean) ((JavascriptExecutor) driver).executeScript("return window.active==0");
			System.out.println(isJqueryCallDone);
			if (!isJqueryCallDone) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			return isJqueryCallDone;
		}, timeoutInSeconds);
	}

	private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		webDriverWait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
		try {
			webDriverWait.until(waitCondition);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * A convenience method to return a {@link WebDriverWait} with the driver set.
	 * <p>
	 * Equivalent to (new WebDriverWait(driver, timeOutInSeconds))...
	 * </p>
	 * 
	 * @param timeOutInSeconds the time before the driver throws an exception
	 * @return the element
	 * @see WebDriverWait
	 */
	protected WebDriverWait webDriverWait(long timeOutInSeconds) {
		return new WebDriverWait(driver, timeOutInSeconds);
	}

	protected void wait_for_url(final String url, final String message) {
		try {
			webDriverWait(10).withMessage(message).until(urlMatches(url + "(\\?.+)*$"));
		} catch (TimeoutException e) {
			throw new TimeoutException(message + "Actual URL: " + driver.getCurrentUrl() + ", expected URL : " + url,
					e);
		}
	}

	/**
	 * Reports the given string as a test step to the test reporter. Appears in
	 * generated reports.
	 * 
	 * @param step a string describing the step being performed
	 */
	protected void report_step(String step) {
		Reporter.log(step);
	}

	/**
	 * Generates a descriptor based on the method calling.
	 * <p>
	 * Example: If called from the enter_username method in _LoginPage, this would
	 * return '_LoginPage -> enter_username'
	 * 
	 * @return the method name descriptor
	 */
	protected String generate_step_descriptor(Object... parameter) {
		StackTraceElement callee = Thread.currentThread().getStackTrace()[2];
		String calleeClassName = callee.getClassName();
		calleeClassName = calleeClassName.substring(calleeClassName.lastIndexOf('.') + 1); // Remove
																							// the
																							// package.
		return calleeClassName + " -> " + callee.getMethodName();
	}

	/**
	 * Returns the fully qualified URL for a route. Based on configured base url.
	 * Example: /login --> http://localhost:3000/login
	 * 
	 * @param partialRoute the partial route for the page
	 * @return the fully qualified URL with the protocol, host, port... appended
	 *         with the partial route.
	 */
	protected String build_qualified_url(String partialRoute) {
		return SeleniumTest.getTargetBaseURL().replaceAll("\\/$", "") + partialRoute;
	}

	public boolean isClickable(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void pressTabKey() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB).build().perform();
	}
	
	public void pressEscapeKey() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
	}
	
	public void pressEnterKey() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}
	
	public void pageRefresh() {
		driver.navigate().refresh();
		wait_for_disappearance_of_modal();
	}
	
	public void scroll_to_element(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		//wait_for_disappearance_Of(LOADER, 300);
		//wait_for_disappearance_of_modal();
	}
	
	public void scroll_to_element_quick(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
	}
	
	public void scroll_up() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.PAGE_UP).build().perform();       //Page Up
        System.out.println("Scroll up perfomed");
	}
	
	public void scroll_bottom() {
		Actions act = new Actions(driver);
        act.sendKeys(Keys.PAGE_DOWN).build().perform(); //Page Down
        System.out.println("Scroll down perfomed");
	}
	
	public void scroll_to_element_using_text(String text) {
		WebElement ele = driver.findElement(By.xpath("//*[text()='"+text+"']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		wait_for_disappearance_Of(LOADER, 300);
	}
	
	public void clearWebField(WebElement element){
	    while(!element.getAttribute("value").equals("")){
	        element.sendKeys(Keys.BACK_SPACE);
	    }
	}
	
	public void wait_for_page_loader(int timer) {
		wait_for_disappearance_Of(LOADER, timer);
	}

}

