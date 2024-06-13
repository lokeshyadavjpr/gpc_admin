package com.logout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.login.LoginPage;
import com.util.Page;

public class LogoutPage extends Page<LogoutPage> {

	// URL
	public static final String ROUTE = "/logout";

	private static final String LOGOUT_ICON = "//a[@href='/logout']";

	// Form Elements
	@FindBy(xpath = LOGOUT_ICON)
	private WebElement logoutIcon;
	public LogoutPage(WebDriver driver) {
		super(driver, ROUTE);
	}

	public LogoutPage click_logout() {
		wait_for_appearance_of_css(LOGOUT_ICON,60);
		logoutIcon.click();
		return this;
	}

	/**
	 * This navigates to /logout
	 * <p>
	 * If the user is logged in, the user should be logged out and redirected to
	 * login. If the user is not yet logged in, this should simply redirect to
	 * login.
	 * <p>
	 * Under no circumstance should the user be logged in after hitting this
	 * route!
	 */
	public LoginPage navigate_to_logout_and_expect_return_to_login() {
		report_step(generate_step_descriptor());

		// logoutIcon.click();
		// TODO AssertPendig
		// Ensure we were redirected to the login page.
		return PageFactory.initElements(driver, LoginPage.class);
	}

	@Override
	public LogoutPage verify_route() {
		report_step(generate_step_descriptor());

		// NOTE! The logout page should redirect to the login page so we
		// actually want to verify the login route here!
		// PageFactory.initElements(driver, _LoginPage.class).verify_route();
		return this;
	}
}

