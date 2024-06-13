package com.home;

import org.openqa.selenium.WebDriver;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.login.LoginPage;
import com.logout.LogoutPage;
import com.util.Page;

public class HomePage extends Page<HomePage> {
	/**
	 * @param driver the webdriver
	 */
	public HomePage(WebDriver driver) {
		super(driver, ROUTE);
	}

	@Override
	public HomePage verify_route() {
		return this;
	}

	Actions actions = new Actions(driver);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	private static final String ROUTE = "/";
	
	private static final String DASHBOARD = ".nav-link.active";
	private static final String GREETING_BANNER = ".mysailpoint-greeting-banner__welcome";
	private static final String ADMIN_MENU_BTN = "(//a[@id=\"slpt-nav-admin\"])[1]";
	private static final String CUSTOMERS_DROPDOWN = ".nav-icon.far.fa-user";
	private static final String CUSTOMERS_PAGE = "//a[@href='/Admin/Customer/List']";
	private static final String ADD_NEW_CUST_BTN = "//a[@href='/Admin/Customer/Create']";
	private static final String CUSTOMER_EMAIL_TXTBOX = "//input[@id='Email']";
	private static final String CUSTOMER_PASSWORD_TXTBOX = "//input[@id='Password']";
	private static final String CUSTOMER_FIRST_NAME_TXTBOX = "//input[@id='FirstName']";
	private static final String CUSTOMER_LAST_NAME_TXTBOX = "//input[@id='LastName']";
	private static final String CUSTOMER_GENDER_RADIO_BTN = "//input[@id='Gender_Male']";
	private static final String CUSTOMER_DOB = "//input[@id='DateOfBirth']";
	private static final String CUSTOMER_COMPANY_TXTBOX = "//input[@id='Company']";
	private static final String CUSTOMER_TAX_CHECKBOX = "//input[@id='Company']";
	private static final String CUSTOMER_ADMIN_COMMENT_TXT_BOX = "//*[@id='AdminComment']";
	private static final String CUSTOMER_SAVE_BTN = "//button[@name='save']";
	private static final String CUSTOMER_CREATION_SUCCESS_MSG = ".alert.alert-success.alert-dismissable";
	private static final String MSG_CLOSE_BTN = "//button[@data-dismiss='alert']";
	private static final String CUSTOMER_EDIT_BTN = "//tbody//tr[1]//td[7]";
	
	@FindBys({ @FindBy(css = GREETING_BANNER) })
	private WebElement greetingBanner;
	@FindBys({ @FindBy(css = DASHBOARD) })
	private WebElement dashboardBtn;
	@FindBys({ @FindBy(xpath = ADMIN_MENU_BTN) })
	private WebElement adminMenuBtn;
	@FindBys({ @FindBy(css = CUSTOMERS_DROPDOWN) })
	private WebElement customersDropdown;
	@FindBys({ @FindBy(xpath = CUSTOMERS_PAGE) })
	private WebElement customersPage;
	@FindBys({ @FindBy(xpath = ADD_NEW_CUST_BTN) })
	private WebElement addNewBtn;
	@FindBys({ @FindBy(xpath = CUSTOMER_EMAIL_TXTBOX) })
	private WebElement customerEmail;
	@FindBys({ @FindBy(xpath = CUSTOMER_PASSWORD_TXTBOX) })
	private WebElement customerPassword;
	@FindBys({ @FindBy(xpath = CUSTOMER_FIRST_NAME_TXTBOX) })
	private WebElement customerFirstName;
	@FindBys({ @FindBy(xpath = CUSTOMER_LAST_NAME_TXTBOX) })
	private WebElement customerLastName;
	@FindBys({ @FindBy(xpath = CUSTOMER_GENDER_RADIO_BTN) })
	private WebElement customerGender;
	@FindBys({ @FindBy(xpath = CUSTOMER_DOB) })
	private WebElement customerDOB;
	@FindBys({ @FindBy(xpath = CUSTOMER_COMPANY_TXTBOX) })
	private WebElement customerCompany;
	@FindBys({ @FindBy(xpath = CUSTOMER_TAX_CHECKBOX) })
	private WebElement customerTaxCheckbox;
	@FindBys({ @FindBy(xpath = CUSTOMER_ADMIN_COMMENT_TXT_BOX) })
	private WebElement customerAdminComment;
	@FindBys({ @FindBy(xpath = CUSTOMER_SAVE_BTN) })
	private WebElement customerSaveBtn;
	@FindBys({ @FindBy(css = CUSTOMER_CREATION_SUCCESS_MSG) })
	private WebElement customerCreateSuccessMsg;
	@FindBys({ @FindBy(xpath = MSG_CLOSE_BTN) })
	private WebElement msgCloseBtn;
	@FindBys({ @FindBy(xpath = CUSTOMER_EDIT_BTN) })
	private WebElement customerEditBtn;
	
	
	
	
	public String getSaltString() {
        String SALTCHARS = "abcdefghijklmnopqrstvwxyz0123456789";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
	
	public HomePage validate_greeting_banner()
	{
		Assert.assertEquals(greetingBanner.getText(), "Welcome");
		return this;
	}
	
	public HomePage validate_admin_menu_btn()
	{
		Assert.assertTrue(adminMenuBtn.isDisplayed());
		return this;
	}
	
	
	public HomePage click_on_customers_link()
	{
		customersDropdown.click();
		customersPage.click();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public HomePage click_on_add_new_customer()
	{
		Assert.assertTrue(addNewBtn.isDisplayed());
		addNewBtn.click();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public HomePage enter_customer_details()
	{
		Assert.assertTrue(customerEmail.isDisplayed());
		customerEmail.sendKeys(getSaltString()+"@gmail.com");
		Assert.assertTrue(customerPassword.isDisplayed());
		customerPassword.sendKeys("Abcd@12345");
		Assert.assertTrue(customerFirstName.isDisplayed());
		customerFirstName.sendKeys("Johnathan");
		Assert.assertTrue(customerLastName.isDisplayed());
		customerLastName.sendKeys("Carter");
		Assert.assertTrue(customerGender.isDisplayed());
		customerGender.click();
		Assert.assertTrue(customerDOB.isDisplayed());
		customerDOB.sendKeys("11/1/1988");
		Assert.assertTrue(customerCompany.isDisplayed());
		customerCompany.sendKeys("Amazon.in");
		Assert.assertTrue(customerTaxCheckbox.isDisplayed());
		customerTaxCheckbox.click();
		Assert.assertTrue(customerAdminComment.isDisplayed());
		customerAdminComment.sendKeys("Automation Test");
		
		return this;
	}
	
	public HomePage click_on_save_customer_btn()
	{
		Assert.assertTrue(customerSaveBtn.isDisplayed());
		customerSaveBtn.click();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public HomePage validate_customer_success_msg()
	{
		Assert.assertTrue(customerCreateSuccessMsg.isDisplayed());
		msgCloseBtn.click();
		return this;
	}
	
	public HomePage click_on_dashboard_page()
	{
		dashboardBtn.click();
		return this;
	}
	
	public HomePage click_on_edit_customer_btn()
	{
		Assert.assertTrue(customerEditBtn.isDisplayed());
		customerEditBtn.click();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public HomePage update_customer_last_name()
	{
		Assert.assertTrue(customerLastName.isDisplayed());
		customerLastName.clear();
		customerLastName.sendKeys("Smith");
		return this;
	}
	
	public LoginPage perform_logout() throws Exception {
		String pageName="Logout page";
		LogoutPage logoutPage = PageFactory.initElements(driver, LogoutPage.class);
		driver.manage().deleteAllCookies();
		logoutPage.navigate_to_route(pageName,10,false);
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
}
