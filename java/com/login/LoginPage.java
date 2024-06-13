package com.login;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.admin.AdminPage;
import com.logout.LogoutPage;
import com.util.Page;

public class LoginPage extends Page<LoginPage>{
	
	// ROUTE
	private static final String ROUTE = "/";
	public static String authCode = "";

	// Element IDs
	Actions actions = new Actions(driver);
	private static final String USERNAME = "//input[@id='username']";
	private static final String PASSWORD = "//input[@id='password']";
	private static final String LOGIN_BUTTON = "//button[@data-testid='sign-in-button']";
	private static final String LOGOUT_BUTTON = "//a[@href='/logout']";
	private static final String AUTH_STATIC_CODE_TXTBOX = ".control input";
	private static final String AUTHORIZATION_CODE = ".box>p"; //a[@id='clipboard-button']
	private static final String TOTP_CODE_TXTBOX = "//input[@id='totpCode']";
	private static final String TOTP_VERIFY_BTN = "//button/span[text()='Verify']";
	
	
	// Form Elements
		@FindBy(xpath = USERNAME)
		private WebElement userNameLogin;
		@FindBy(xpath = PASSWORD)
		private WebElement passwordLogin;
		@FindBy(xpath = LOGIN_BUTTON)
		private WebElement submitLogin;
		@FindBy(xpath = LOGOUT_BUTTON)
		private WebElement logOutButton;
		@FindBy(css = AUTH_STATIC_CODE_TXTBOX)
		private List<WebElement> authStaticCode;
		@FindBy(css = AUTHORIZATION_CODE)
		private WebElement authorizationCode;
		@FindBys({ @FindBy(xpath = TOTP_CODE_TXTBOX) })
		private WebElement totpCodeTxtbox;
		@FindBys({ @FindBy(xpath = TOTP_VERIFY_BTN) })
		private WebElement totpVerifyBtn;
		

			public LoginPage(WebDriver driver) {
				super(driver, ROUTE);
			}
			/**
			 * Logout the current user (if one is logged in) and returns to the login
			 * page.
			 * 
			 * @return a fresh Login Page
			 */
			public LoginPage goto_fresh_login_page() {

				report_step(generate_step_descriptor());
				LogoutPage logoutPage = PageFactory.initElements(driver, LogoutPage.class);
				// waitForLoadCompletion();
				return logoutPage.navigate_to_logout_and_expect_return_to_login();
			}

			/**
			 * Enter text into the username field
			 * 
			 * @param username
			 *            the string to enter into the password field
			 * @return this Login Page
			 */
			public LoginPage zoom_out()
			{
				Robot robot = null;
				try {
					robot = new Robot();
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int i = 0; i < 1; i++) {
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_SUBTRACT);
					robot.keyRelease(KeyEvent.VK_SUBTRACT);
					robot.keyRelease(KeyEvent.VK_CONTROL);
				}
				return this;
			}

			public LoginPage enter_username(String userName) {
				zoom_out();
				report_step(generate_step_descriptor() + ", userName: '"
						+ userName + "'");
				userNameLogin.sendKeys(userName);
				return this;

			}
			
			public LoginPage validate_presence_of_login_Screen() {
				wait_for_disappearance_of_modal();
				Assert.assertTrue(userNameLogin.isDisplayed());
				return this;

			}
			
			public LoginOutcome press_login_button() {
				report_step(generate_step_descriptor());
				submitLogin.click();
				wait_for_disappearance_of_modal_short_wait();
				validate_authorization_code();
				return new LoginOutcome();
			}
			
			public LoginPage validate_authorization_code() {
				String loginPage = driver.getWindowHandle();
				((JavascriptExecutor) driver).executeScript("window.open('https://totp.danhersam.com/')");
				Set<String> authPage = driver.getWindowHandles();
				wait_for_disappearance_of_modal();
				for (String window : authPage) {
					if (!window.equals(loginPage)) {
						driver.switchTo().window(window);
						wait_for_disappearance_of_modal_short_wait();
						authStaticCode.get(0).clear();
						authStaticCode.get(0).clear();
						authStaticCode.get(0).sendKeys("JPTYMUZCRRTZXPQE"); //Stage=JPTYMUZCRRTZXPQE, Dev=SVAIWPAVGCV4SQPL
						wait_for_disappearance_of_modal_short_wait();
						authCode = authorizationCode.getText();
						driver.close();
					}
				}
				driver.switchTo().window(loginPage);
				//wait_for_disappearance_of_modal_short_wait();
				totpCodeTxtbox.sendKeys(authCode);
				totpVerifyBtn.click();
				wait_for_disappearance_of_modal();
				wait_for_disappearance_of_modal();
				return this;
			}

			public LoginPage enter_password(String password) {
				report_step(generate_step_descriptor() + ", password: '"
						+ password + "'");
				passwordLogin.sendKeys(password);
				return this;

			}
			
			
		  /**
			 * Checks the browser is on the correct URL and validates some page elements
			 * exist.
			 * 
			 * @return this Login Page
			 *//* */
			@Override
			public LoginPage verify_route() {
				report_step(generate_step_descriptor());
				wait_for_url(ROUTE, "not on the Login page");
				// waitForAppearanceOf(USERNAME_FIELD_ID, 10);
				// Check some elements on the page to ensure render.
				
				return this;
			}

			/**
			 * Possible outcomes of pressing the login button.
			 */
			public class LoginOutcome {

				/**
				 * 1) Ensures no invalid credentials error message is displayed. 2)
				 * Verifies navigation to dashboard page took place.
				 * 
				 * @return the dashboard page object that we have navigated to.
				 */
				@SuppressWarnings("synthetic-access")
				public LoginOutcome expect_valid_login() {
					report_step(generate_step_descriptor()); // Wait to ensure bad
															// credentials
															// message does not appear.
					waitForPageLoad();
					wait_for_disappearance_of_modal();
					wait_for_disappearance_of_modal();
					// waitForAppearanceOfClass(LOGOUT_BUTTON, 5);
					// Ensure we navigated to the dashboard page.
					// assertTrue(logOutButton.isDisplayed());
					return this;
				}

				public LoginOutcome validate_error_message() {
					// TODO GUI not supporting this feature right now.
					return new LoginOutcome();
				}

				/**
				 * 1) Ensures we are still on the login page.
				 * <p>
				 * Note: Do whatever validation (error message, required fields...) you
				 * need after this.
				 * 
				 * @return the login page object
				 */
				@SuppressWarnings("synthetic-access")
				public LoginPage expect_no_login() {
					report_step(generate_step_descriptor());
					return LoginPage.this.verify_route(); // Ensure we are still on the
															// login page.
				}
			}
		}

