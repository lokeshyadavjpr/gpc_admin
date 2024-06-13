package com.admin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.*;

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
import com.util.Page;
import com.excel.read.*;
import com.login.LoginPage;

public class AdminPage extends Page<AdminPage> {

	/**
	 * @param driver the webdriver
	 */
	public AdminPage(WebDriver driver) {
		super(driver, ROUTE);
	}

	@Override
	public AdminPage verify_route() {
		return this;
	}

	public static String authCode = "";
	public static String userId = "";
	public String managerName = "";
	public String company = "";
	public String actualManagerField = "";
	public String expectedManagerField = "";
	public String cnField = "";
	public String actualCNField = "";
	public String expectedCNField = "";
	public String actualdistinguishedName = "";
	public String expectedDistinguishedName = "";
	public String actualAccountID = "";
	public String expectedAccountID = "";
	public String actualUserPrincipalName = "";
	public String expectedUserPrincipalName = "";
	public SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd");
	private ReadDataFromExcel readData = PageFactory.initElements(driver, ReadDataFromExcel.class);;
	Actions actions = new Actions(driver);
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	private static final String ROUTE = "/";
	private static final String AUTH_STATIC_CODE_TXTBOX = ".control input";
	private static final String AUTHORIZATION_CODE = ".box>p";
	private static final String TOTP_CODE_TXTBOX = "//input[@id='totpCode']";
	private static final String TOTP_VERIFY_BTN = "//button/span[text()='Verify']";
	private static final String DASHBOARD = ".nav-link.active";
	private static final String GREETING_BANNER = ".mysailpoint-greeting-banner__welcome";
	private static final String ADMIN_MENU_BTN = "(//a[@id=\"slpt-nav-admin\"])[1]";
	private static final String AUTHENTICATION_SECURITY_QUES_RADIO_BTN = "//*[@id='slpt-dbx-strongauthn-chooser-panel-id-kba-bodyEl']//input";
	private static final String AUTHENTICATION_CONTINUE_BTN = "//*[@id='slpt-dbx-strongauthn-chooser-panel-id-btn']";
	private static final String AUTHENTICATION_SECURITY_ANSWERS_TXT_BOX = ".x-form-item-input-row>td>div+input";
	private static final String ADMIN_AUTHENTICATE_BTN = "//a[@id='slpt-strongauthn-enterkba-btn']";
	private static final String IDENTITIES_DROPDOWN_MENU_BTN = "//a[@id='slpt-nav-admin-identities']/ancestor::li";
	private static final String IDENTITIES_DROPDOWN_LIST = "//a[@id='slpt-nav-admiral-identities-list']";  ////span[contains(text(),'Identity List')]
	private static final String SEARCH_IDENTITIES_SEARCH_BAR = ".slpt-search-bar-input__input.slpt-field-focus";
	private static final String SEARCH_IDENTITIES_LOADER = "//div[@class='loading-mask ng-tns-c2437564756-70 ng-trigger ng-trigger-backdropState ng-star-inserted']";
	private static final String SEARCH_BAR_CANCEL_BTN = "//slpt-icon-times-circle/ancestor::button";
	private static final String EXPORT_BTN = "//*[@id='pendo-identity-list-export']/button";
	private static final String QUICK_FILTER_ALL = "//button[@aria-label='All']/span";
	private static final String LIST_COLUMN_CHOOSER = "//button[@aria-label='Columns']";
	private static final String TOGGLE_CARDS_TABLE = ".toggle-group>slpt-toggle-button>button>span";
	private static final String COLUMNS_LIST = ".menu.checkbox-list>li";
	private static final String COLUMNS_HEADER_NAMES = ".slpt-data-grid-column-header__name>span";
	private static final String RESET_COLUMNS_BTN = ".slpt-dropdown__menu>div>div>span";
	private static final String OVERVIEW_DETAILS_DATA_LIST = ".identity-attributes-overview_properties .identity-attributes--attribute-wrapper .attribute-component__value>span";
	private static final String LIFECYCLE_STATE = "//*[text()=' Lifecycle State ']/..//span";
	private static final String DETAILS_COLUMN1_DATA_LIST = ".identity-attributes-container.ng-star-inserted .attribute-component__key--uppercase";  //.slpt-user-details-attributes-grid>div:nth-child(3)>div>table>tbody>tr>td:nth-child(2n-1)>div
	private static final String DETAILS_COLUMN2_DATA_LIST = ".identity-attributes-container.ng-star-inserted .attribute-component__value>span";  //.slpt-user-details-attributes-grid>div:nth-child(3)>div>table>tbody>tr>td:nth-child(3n-1)>div
	private static final String DETAILS_COLUMN_NAME = ".slpt-link-anchor .ng-star-inserted>span";
	private static final String IDENTITY_LIST_EMPTY = ".ng-star-inserted .empty-identities-list-container__message";
	private static final String USER_DETAILS_IDENTITY_ICON = ".slpt-identity-icon";
	private static final String USER_DETAILS_IDENTITY_NAME = ".identity-attributes-overview_name>span";
	private static final String USER_DETAILS_BACK_BTN = ".slpt-btn__left-icon.ng-star-inserted";
	private static final String USER_DETAILS_ACTION_BTN = "//span[@class='slpt-btn-label ng-star-inserted']";
	private static final String SET_LIFECYCLE_STATUS_LINK="//span[normalize-space()='Set Lifecycle State']";
	private static final String SET_LIFECYCLE_STATUS_INACTIVE="//span[normalize-space()='inactive']//span[@id='lifecycleStatesRadiolabel']";
	private static final String SET_LIFECYCLE_STATUS_DELETED="//span[normalize-space()='deleted']//span[@id='lifecycleStatesRadiolabel']";
	private static final String SET_LIFECYCLE_STATUS_SAVE_BTN="//button[@aria-label='Save']";
	private static final String SET_LIFECYCLE_STATUS_CLOSE_BTN="\"//slpt-icon[@aria-label='close']//slpt-icon-close[@class='ng-star-inserted']//*[name()='svg']\";";
	private static final String ACTIVE_DIR_STATUS="//div[span[contains(text(),'Active Directory')]]/following::div[1]/following::div[1]/app-account-status/div/slpt-badge/span/div";
	private static final String TRY_NEW_EXPERIENCE_BTN = "//*[text()='Try New Experience']/ancestor::a";
	private static final String USER_DETAIL_DISPLAY_NAME = "(//*[text()=' displayName ']/..//dd//span)[2]";
	private static final String USER_DETAIL_PAGE_MENU_BUTTONS = ".slpt-list-item-label";
	private static final String USER_DETAIL_EMAIL="//dt[normalize-space()='Workday Email']/following::dd[1]/div/span";
	private static final String USER_DETAIL_REHIRE="//dt[normalize-space()='Rehire']/following::dd[1]/div/span";
	private static final String USER_MENU_ELEMENTS = "//*[@class='slpt-clickable-cell-text']";
	private static final String USER_MENU_ACCOUNTS_TAB_LINK = ".slpt-link-anchor:nth-child(2n-1)"; //
	private static final String ACCOUNTS_TAB_NAME_FIELD_VALUES = "//*[@col-id='name']//span";
	private static final String ACCOUNTS_TAB_SOURCE_NAME_FIELD_VALUES = "//*[@col-id='source.displayableName']//span"; ////*[@col-id='sourceName']//span
	private static final String ACCOUNTS_TAB_STATUS_FIELD_VALUES = "//*[@col-id='status']//span//div";
	private static final String USER_MENU_ACTIVE_DIRECTORY_LINK = "(//*[contains(text(),'Active Directory')])[1]"; // //body//td[contains(text(),'Active Directory')]  //body//td[text()='Active Directory']
	private static final String USER_MENU_IDENTITY_ACTIVITY_TXT = "//span[@id='slpt-useractivitypanel_header_hd-textEl']";
	private static final String USER_MENU_WORK_REASSIGNMENT_ACTIONS_BTN = "//app-identity-details-actions-dropdown//button";
	private static final String ACCOUNTS_TAB_STATUS_COLUMN = ".slpt-statusbadge-column .with-out-spinner";
	private static final String DETAILS_CLOSE_BTN = "//button[@aria-label='Exit to Identity List']";
	private static final String ACCOUNTS_AD_COLUMN1_DATA_LIST = "(//*[@class='slpt-collapsible-card__container'])[3]//*[@class='attribute-component__key']"; //.slpt-user-account-attributes-grid .x-fit-item>table>tbody>tr>td:nth-child(2n-1)
	private static final String ACCOUNTS_AD_COLUMN2_DATA_LIST = "(//*[@class='slpt-collapsible-card__container'])[3]//div[1][@class='attribute-component__value ng-star-inserted' or @class='attribute-component__value--multi-value ng-star-inserted']/span"; //(//*[@class='slpt-collapsible-card__container'])[3]//*[@class='attribute-component__value ng-star-inserted']/span
//	private static final String ACCOUNTS_AD1_COLUMN1_DATA_LIST = "(//*[@class='slpt-collapsible-card__container'])[3]//*[@class='attribute-component__key']"; //.slpt-user-account-attributes-grid .x-fit-item>table>tbody>tr>td:nth-child(2n-1)
//	private static final String ACCOUNTS_AD1_COLUMN2_DATA_LIST = "(//*[@class='slpt-collapsible-card__container'])[3]//div[1][@class='attribute-component__value ng-star-inserted' or @class='attribute-component__value--multi-value ng-star-inserted']/span"; //.slpt-user-account-attributes-grid .x-fit-item>table>tbody>tr>td:nth-child(3n-1)
//	private static final String ACCOUNTS_WD_COLUMN1_DATA_LIST = "(//*[@class='slpt-collapsible-card__container'])[3]//*[@class='attribute-component__key']"; //.slpt-user-account-attributes-grid .x-fit-item>table>tbody>tr>td:nth-child(2n-1)
//	private static final String ACCOUNTS_WD_COLUMN2_DATA_LIST = "(//*[@class='slpt-collapsible-card__container'])[3]//*[@class='attribute-component__value ng-star-inserted']/span"; //.slpt-user-account-attributes-grid .x-fit-item>table>tbody>tr>td:nth-child(3n-1)
	private static final String LIFECYCLE_STATE_CHANGE_OPTION = "//*[@id='slpt-user-details-panel-table-lifecycle-actions-btnWrap']";
	private static final String LIFECYCLE_STATE_ITEMS = ".x-menu-item";
	private static final String ATTRIBUTE_SINGLE_MULTIPLE_TOGGLE_BUTTON = ".toggle-button";
	private static final String COLUMN_BUTTON="//button[@aria-label='Columns']";
	private static final String AD_TERMINATION_DATE="//dt[text()[normalize-space()='extensionAttribute1']]/following::dd[1]";
	private static final String WD_UPN_ATRRIBUTE="//dt[text()[normalize-space()='USERPRINCIPALNAME__CUSTID']]/following::dd[1]";
	private static final String WD_EMAIL_ATRRIBUTE="//dt[text()[normalize-space()='EMAIL_ADDRESS_WORK']]/following::dd[1]";
	private static final String AD_UPN_ATRRIBUTE="//dt[text()[normalize-space()='userPrincipalName']]/following::dd[1]";
	
	private static final String DETAILS_LOADING="//div[contains(@class,'loading-mask ng-tns')]";
	
	
	
	
	@FindBys({ @FindBy(css = GREETING_BANNER) })
	private WebElement greetingBanner;
	@FindBys({ @FindBy(css = DASHBOARD) })
	private WebElement dashboardBtn;
	@FindBys({ @FindBy(xpath = ADMIN_MENU_BTN) })
	private WebElement adminMenuBtn;
	@FindBys({ @FindBy(xpath = AUTHENTICATION_SECURITY_QUES_RADIO_BTN) })
	private WebElement adminSecurityQuestion;
	@FindBys({ @FindBy(xpath = AUTHENTICATION_CONTINUE_BTN) })
	private WebElement authenticationContinue;
	@FindBys({ @FindBy(css = AUTHENTICATION_SECURITY_ANSWERS_TXT_BOX) })
	private List<WebElement> authenticationSecurityAnswers;
	@FindBys({ @FindBy(xpath = ADMIN_AUTHENTICATE_BTN) })
	private WebElement adminAuthenticateBtn;
	@FindBys({ @FindBy(xpath = IDENTITIES_DROPDOWN_MENU_BTN) })
	private WebElement identitiesDropdownMenu;
	@FindBys({ @FindBy(xpath = IDENTITIES_DROPDOWN_LIST) })
	private List<WebElement> identitiesDropdownList;
	@FindBys({ @FindBy(css = SEARCH_IDENTITIES_SEARCH_BAR) })
	private WebElement identitiesSearchBar;
	@FindBys({ @FindBy(xpath = SEARCH_IDENTITIES_LOADER) })
	private WebElement identitiesSearchLoader;
	@FindBys({ @FindBy(css = SEARCH_BAR_CANCEL_BTN) })
	private WebElement searchCancelBtn;
	@FindBys({ @FindBy(xpath = EXPORT_BTN) })
	private WebElement exportBtn;
	@FindBys({ @FindBy(xpath = QUICK_FILTER_ALL) })
	private WebElement quickFilterAll;
	@FindBys({ @FindBy(xpath = LIST_COLUMN_CHOOSER) })
	private WebElement listColumnChooser;
	@FindBys({ @FindBy(css = TOGGLE_CARDS_TABLE) })
	private List<WebElement> toggleBtn;
	@FindBys({ @FindBy(css = COLUMNS_LIST) })
	private List<WebElement> columnsList;
	@FindBys({ @FindBy(css = COLUMNS_HEADER_NAMES) })
	private List<WebElement> columnsName;
	@FindBys({ @FindBy(css = RESET_COLUMNS_BTN) })
	private WebElement resetColumnsBtn;
	@FindBys({ @FindBy(css = OVERVIEW_DETAILS_DATA_LIST) })
	private List<WebElement> overviewDetailsDataList;
	@FindBys({ @FindBy(xpath = LIFECYCLE_STATE) })
	private WebElement lifeCycleState;
	@FindBys({ @FindBy(css = DETAILS_COLUMN1_DATA_LIST) })
	private List<WebElement> detailsDataList1;
	@FindBys({ @FindBy(css = DETAILS_COLUMN2_DATA_LIST) })
	private List<WebElement> detailsDataList2;
	@FindBys({ @FindBy(css = DETAILS_COLUMN_NAME) })
	private List<WebElement> detailsColumnName;
	@FindBys({ @FindBy(css = IDENTITY_LIST_EMPTY) })
	private WebElement identityListEmptyMsg;
	@FindBys({ @FindBy(xpath = USER_DETAIL_DISPLAY_NAME) })
	private WebElement detailsDisplayName;
	@FindBys({ @FindBy(css = USER_DETAILS_BACK_BTN) })
	private List<WebElement> userDetailsBackBtn;
	
	@FindBy(xpath = USER_DETAILS_ACTION_BTN)
	private WebElement userDetailsActionBtn;
	
	@FindBy(xpath = SET_LIFECYCLE_STATUS_LINK)
	private WebElement setLifeCycleStatusLink;	
	
	@FindBy(xpath = SET_LIFECYCLE_STATUS_INACTIVE)
	private WebElement setLifeCycleStatusInactive;	
	
	@FindBy(xpath = SET_LIFECYCLE_STATUS_DELETED)
	private WebElement setLifeCycleStatusDeleted;	

	
	@FindBy(xpath = SET_LIFECYCLE_STATUS_SAVE_BTN)
	private WebElement setLifeCycleStatusSaveBtn;
	
	@FindBy(xpath = SET_LIFECYCLE_STATUS_CLOSE_BTN)
	private WebElement setLifeCycleStatusCloseBtn;
	
	@FindBy(xpath = ACTIVE_DIR_STATUS)
	private WebElement activeDirStatus;
	
	@FindBys({ @FindBy(css = USER_DETAILS_IDENTITY_ICON) })
	private WebElement userDetailsIdentityIcon;
	@FindBys({ @FindBy(css = USER_DETAILS_IDENTITY_NAME) })
	private WebElement userDetailsIdentityName;
	
	@FindBy(xpath = USER_DETAIL_EMAIL)
	private WebElement userDetailEmail;
	
	@FindBy(xpath = USER_DETAIL_REHIRE)
	private WebElement userDetailRehire;	
	
	@FindBys({ @FindBy(xpath = TRY_NEW_EXPERIENCE_BTN) })
	private WebElement tryNewExperienceBtn;
	@FindBys({ @FindBy(css = USER_DETAIL_PAGE_MENU_BUTTONS) })
	private List<WebElement> userDetailsMenuButtons;
	@FindBys({ @FindBy(xpath = USER_MENU_ELEMENTS) })
	private List<WebElement> userDetailsMenuElements;
	@FindBys({ @FindBy(css = USER_MENU_ACCOUNTS_TAB_LINK) })
	private List<WebElement> accountsTabLink;
	@FindBys({ @FindBy(xpath = ACCOUNTS_TAB_NAME_FIELD_VALUES) })
	private List<WebElement> accountsNameFieldValues;
	@FindBys({ @FindBy(xpath = ACCOUNTS_TAB_SOURCE_NAME_FIELD_VALUES) })
	private List<WebElement> accountsSourceNameFieldValues;
	@FindBys({ @FindBy(xpath = ACCOUNTS_TAB_STATUS_FIELD_VALUES) })
	private List<WebElement> accountsStatusFieldValues;
	@FindBys({ @FindBy(xpath = USER_MENU_ACTIVE_DIRECTORY_LINK) })
	private WebElement activeDirectoryLink;
	@FindBys({ @FindBy(xpath = USER_MENU_IDENTITY_ACTIVITY_TXT) })
	private WebElement userMenuIdentityActivityTxt;
	@FindBys({ @FindBy(xpath = USER_MENU_WORK_REASSIGNMENT_ACTIONS_BTN) })
	private WebElement reassignmentActionsDropdown;
	@FindBys({ @FindBy(xpath = DETAILS_CLOSE_BTN) })
	private WebElement deatilsCloseBtn;
	@FindBys({ @FindBy(xpath = ACCOUNTS_AD_COLUMN1_DATA_LIST) })
	private List<WebElement> accountsADColumn1DataList;
	@FindBys({ @FindBy(xpath = ACCOUNTS_AD_COLUMN2_DATA_LIST) })
	private List<WebElement> accountsADColumn2DataList;
	
	@FindBys({ @FindBy(xpath = AD_TERMINATION_DATE)})
	private List<WebElement> activeDirTerminationDate;
	
	@FindBys({ @FindBy(xpath = WD_UPN_ATRRIBUTE)})
	private List<WebElement> workdayUPNAttribute;
	
	@FindBys({ @FindBy(xpath = WD_EMAIL_ATRRIBUTE)})
	private List<WebElement> workdayEmailAttribute;
	
	
	@FindBys({ @FindBy(xpath = AD_UPN_ATRRIBUTE)})
	private List<WebElement> activeDirUPNAttribute;
	

	
	

//	@FindBys({ @FindBy(xpath = ACCOUNTS_AD1_COLUMN1_DATA_LIST) })
//	private List<WebElement> accountsAD1Column1DataList;
//	@FindBys({ @FindBy(xpath = ACCOUNTS_AD1_COLUMN2_DATA_LIST) })
//	private List<WebElement> accountsAD1Column2DataList;
//	
//	
//	@FindBys({ @FindBy(xpath = ACCOUNTS_WD_COLUMN1_DATA_LIST) })
//	private List<WebElement> accountsWDColumn1DataList;
//	@FindBys({ @FindBy(xpath = ACCOUNTS_WD_COLUMN2_DATA_LIST) })
//	private List<WebElement> accountsWDColumn2DataList;
	
	
	@FindBys({ @FindBy(css = ACCOUNTS_TAB_STATUS_COLUMN) })
	private List<WebElement> accountsStatusData;
	@FindBy(css = AUTH_STATIC_CODE_TXTBOX)
	private List<WebElement> authStaticCode;
	@FindBy(css = AUTHORIZATION_CODE)
	private WebElement authorizationCode;
	@FindBys({ @FindBy(xpath = TOTP_CODE_TXTBOX) })
	private WebElement totpCodeTxtbox;
	@FindBys({ @FindBy(xpath = TOTP_VERIFY_BTN) })
	private WebElement totpVerifyBtn;
	@FindBys({ @FindBy(xpath = LIFECYCLE_STATE_CHANGE_OPTION) })
	private WebElement lifecycleStateChange;
	@FindBys({ @FindBy(css = LIFECYCLE_STATE_ITEMS) })
	private List<WebElement> lifecycleStateItems;
	@FindBys({ @FindBy(css = ATTRIBUTE_SINGLE_MULTIPLE_TOGGLE_BUTTON) })
	private List<WebElement> attributeToggleBtn;
	@FindBys({ @FindBy(xpath = COLUMN_BUTTON) })
	private WebElement columnButton;



	
	
	
	
	
	
	
	

	/*
	 * public AdminPage validate_authorization_code() { String loginPage =
	 * driver.getWindowHandle(); ((JavascriptExecutor)
	 * driver).executeScript("window.open('https://totp.danhersam.com/')");
	 * Set<String> authPage = driver.getWindowHandles();
	 * wait_for_disappearance_of_modal(); for (String window : authPage) { if
	 * (!window.equals(loginPage)) { driver.switchTo().window(window);
	 * wait_for_disappearance_of_modal_short_wait(); authStaticCode.get(0).clear();
	 * authStaticCode.get(0).sendKeys("SVAIWPAVGCV4SQPL"); //Stage=JPTYMUZCRRTZXPQE,
	 * Dev=SVAIWPAVGCV4SQPL wait_for_disappearance_of_modal(); authCode =
	 * authorizationCode.getText(); driver.close(); } }
	 * driver.switchTo().window(loginPage);
	 * //wait_for_disappearance_of_modal_short_wait();
	 * totpCodeTxtbox.sendKeys(authCode); totpVerifyBtn.click();
	 * wait_for_disappearance_of_modal(); wait_for_disappearance_of_modal(); return
	 * this; }
	 */

	public AdminPage validate_greeting_banner() {
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		wait_for_appearance_of_css(GREETING_BANNER, 50);
		Assert.assertEquals(greetingBanner.getText(), "Welcome");
		return this;
	}

	public AdminPage validate_admin_menu_btn() {
		Assert.assertTrue(adminMenuBtn.isDisplayed());
		wait_for_disappearance_of_modal();
		return this;
	}

	public AdminPage click_on_admin_menu_btn() {
		adminMenuBtn.click();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		return this;
	}

	public AdminPage validate_admin_strong_authentication() {
		adminSecurityQuestion.click();
		authenticationContinue.click();
		authenticationSecurityAnswers.get(0).sendKeys("12345678");
		authenticationSecurityAnswers.get(1).sendKeys("12345678");
		wait_for_disappearance_of_modal_short_wait();
		adminAuthenticateBtn.click();
		wait_for_disappearance_of_modal();
		return this;
	}

	public AdminPage validate_identities_dropdown_menu() {
		Assert.assertTrue(identitiesDropdownMenu.isDisplayed());
		identitiesDropdownMenu.click();
		return this;
	}

	public AdminPage validate_identities_dropdown_list() {
		Assert.assertEquals(identitiesDropdownList.get(0).getText(), "Identities");
		identitiesDropdownList.get(0).click();
		wait_for_disappearance_of_modal();
		return this;
	}

	public AdminPage validate_identity_list_page_elements() {
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		Assert.assertTrue(identitiesSearchBar.isDisplayed());
		identitiesSearchBar.sendKeys("");
		Assert.assertTrue(exportBtn.isDisplayed());
		Assert.assertTrue(quickFilterAll.isDisplayed());
		Assert.assertTrue(listColumnChooser.isDisplayed());
		Assert.assertEquals(toggleBtn.get(0).getText(), "Cards");
		Assert.assertEquals(toggleBtn.get(1).getText(), "Table");
		wait_for_disappearance_of_modal();
		return this;
	}

	public AdminPage validate_attribute_multiple_view_toggle_btn() {
		wait_for_disappearance_of_modal_long_wait();
		//wait_for_disappearance_of_modal();
		wait_for_disappearance_Of_xpath(DETAILS_LOADING, 30);
		attributeToggleBtn.get(1).click();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public AdminPage validate_column_list() {
		
		scroll_to_element(columnButton);
		listColumnChooser.click();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		columnsList.get(1).click();
		columnsList.get(4).click();
		columnsList.get(5).click();
		listColumnChooser.click();
		wait_for_disappearance_of_modal();
		return this;
	}

	public AdminPage validate_full_columns_name() {
		Assert.assertEquals(columnsName.get(1).getText(), "Name");
		Assert.assertEquals(columnsName.get(2).getText(), "Email");
		Assert.assertEquals(columnsName.get(3).getText(), "Account Name");
		Assert.assertEquals(columnsName.get(4).getText(), "Username");
		Assert.assertEquals(columnsName.get(5).getText(), "Lifecycle State");
		Assert.assertEquals(columnsName.get(6).getText(), "Identity State");
		Assert.assertEquals(columnsName.get(7).getText(), "Status");
		Assert.assertEquals(columnsName.get(8).getText(), "Actions");
		for (int i = 0; i < columnsName.size(); i++) {
			System.out.println(columnsName.get(i).getText());
		}
		return this;
	}

	public AdminPage validate_reset_columns_name() {
		listColumnChooser.click();
		wait_for_disappearance_of_modal();
		resetColumnsBtn.click();
		wait_for_disappearance_of_modal();
		return this;
	}

	public AdminPage validate_reset_columns_header_name() {
		Assert.assertEquals(columnsName.get(1).getText(), "Name");
		Assert.assertEquals(columnsName.get(2).getText(), "Email");
		Assert.assertEquals(columnsName.get(3).getText(), "Username");
		Assert.assertEquals(columnsName.get(4).getText(), "Lifecycle State");
		Assert.assertEquals(columnsName.get(5).getText(), "Actions");
		listColumnChooser.click();
		return this;
	}

	public AdminPage validate_toggle_btn() {
		toggleBtn.get(0).click();
		wait_for_disappearance_of_modal();
		toggleBtn.get(1).click();
		return this;
	}

	public AdminPage validate_search_an_identity(int user_id) {
		wait_for_disappearance_of_modal_short_wait();
		Assert.assertTrue(identitiesSearchBar.isDisplayed());
		try {
			//userId = Integer.toString(ReadDataFromExcel.readData());
			userId = Integer.toString(user_id);
			identitiesSearchBar.clear();
			//System.out.println("Value ::;:::::: " + user_id);
			identitiesSearchBar.sendKeys(userId); // userId 522328 522935
			// wait_for_disappearance_of_modal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public AdminPage validate_search_an_identity() {
		Assert.assertTrue(identitiesSearchBar.isDisplayed());
		
		identitiesSearchBar.sendKeys("595788"); // userId 522328 522935 595712 - update in validate_global_username_to_userid() also
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public AdminPage validate_global_username_to_userid() {
		wait_for_disappearance_of_modal();
		//Assert.assertEquals(overviewDetailsDataList.get(1).getText(), userId);
		//System.out.println(detailsDataList2.get(4).getText());
		System.out.println("Expected global user: "+detailsDataList2.get(6).getText());
		Assert.assertEquals(detailsDataList2.get(6).getText(), "595788");
		return this;
	}
	
	public AdminPage validate_search_identity_hire_date_within_14days() {
		Assert.assertTrue(identitiesSearchBar.isDisplayed());
		
			identitiesSearchBar.sendKeys("595710"); // userId 522328 522935
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public AdminPage validate_search_identity_start_date_chage_within_14days_to_today() {
		Assert.assertTrue(identitiesSearchBar.isDisplayed());
		
			identitiesSearchBar.sendKeys("524806"); // userId 522328 522935
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public AdminPage validate_search_user_hire_date_more_than_14() { //validate_search_a_prehire_identity()
		
		Assert.assertTrue(identitiesSearchBar.isDisplayed());
		try {
			//userId = Integer.toString(ReadDataFromExcel.readData());
			identitiesSearchBar.clear();
			identitiesSearchBar.sendKeys("595787"); // userId 522328 522935 595084 
			// wait_for_disappearance_of_modal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public AdminPage validate_search_an_offer_rescinded_identity() {
		Assert.assertTrue(identitiesSearchBar.isDisplayed());
			identitiesSearchBar.clear();
			identitiesSearchBar.sendKeys("525834"); 
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public AdminPage validate_search_rehire_start_in_14_days_or_less_identity() {
		Assert.assertTrue(identitiesSearchBar.isDisplayed());
		try {
			//userId = Integer.toString(ReadDataFromExcel.readData());
			identitiesSearchBar.clear();
			identitiesSearchBar.sendKeys("401784"); // userId 522328 522935
			// wait_for_disappearance_of_modal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		return this;
	}
	

	public AdminPage validate_search_leaver_urgent_termination() {
		Assert.assertTrue(identitiesSearchBar.isDisplayed());
		try {
			//userId = Integer.toString(ReadDataFromExcel.readData());
			identitiesSearchBar.clear();
			identitiesSearchBar.sendKeys("595789"); // userId 522328 522935 595005 
			// wait_for_disappearance_of_modal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public AdminPage validate_search_leaver_last_day_of_work_today() {
		Assert.assertTrue(identitiesSearchBar.isDisplayed());
		try {
			//userId = Integer.toString(ReadDataFromExcel.readData());
			identitiesSearchBar.clear();
			identitiesSearchBar.sendKeys("595793"); // userId 522328 522935 595005 
			// wait_for_disappearance_of_modal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	
	
	public AdminPage validate_search_leaver_last_day_is_within_90_days() {
		Assert.assertTrue(identitiesSearchBar.isDisplayed());
		identitiesSearchBar.clear();
		identitiesSearchBar.sendKeys("525836"); // userId 522328 522935
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public AdminPage validate_search_leaver_last_day_is_more_than_90_days() {
		Assert.assertTrue(identitiesSearchBar.isDisplayed());
		identitiesSearchBar.clear();
		identitiesSearchBar.sendKeys("519948"); // userId 522328 522935
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public AdminPage validate_search_identity_with_same_name_hired() {
		Assert.assertTrue(identitiesSearchBar.isDisplayed());
		identitiesSearchBar.clear();
		identitiesSearchBar.sendKeys("458806"); // userId 522328 522935
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public AdminPage validate_search_rehire_start_in_past() {
		Assert.assertTrue(identitiesSearchBar.isDisplayed());
		try {
			//userId = Integer.toString(ReadDataFromExcel.readData());
			identitiesSearchBar.clear();
			identitiesSearchBar.sendKeys("522942"); // userId 522328 522935
			// wait_for_disappearance_of_modal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		return this;
	}

	public AdminPage validate_identity_result_name() {
		//wait_for_appearance_of_css(DETAILS_COLUMN_NAME, 20);
		Assert.assertTrue(detailsColumnName.get(0).isDisplayed());
		return this;
	}
	
	public AdminPage validate_user_should_not_searchable() {   // validate_prehire_identity_result()
		Assert.assertEquals(identityListEmptyMsg.getText(), "We couldn't find anything that matches your query. Please try again.");
		System.out.println(identityListEmptyMsg.getText());
		return this;
	}

	public AdminPage validate_identity_user_page_elements() {
		Assert.assertTrue(userDetailsBackBtn.get(0).isDisplayed());
		Assert.assertTrue(userDetailsIdentityIcon.isDisplayed());
		Assert.assertTrue(userDetailsIdentityName.isDisplayed());
		Assert.assertTrue(tryNewExperienceBtn.isDisplayed());
		return this;
	}

	public AdminPage click_on_user_data_name() {
		wait_for_appearance_of_css(DETAILS_COLUMN_NAME, 20); 
		detailsColumnName.get(0).click();
		wait_for_disappearance_of_modal(); // added 01/05/24
		wait_for_appearance_of_xpath(LIFECYCLE_STATE, 50);
		wait_for_disappearance_of_modal();
		//wait_for_disappearance_of_modal();
		return this;
	}

	public AdminPage validate_gpc_global_username() {
		wait_for_disappearance_of_modal_long_wait();
		//Assert.assertEquals(overviewDetailsDataList.get(1).getText(), userId);
		return this;
	}

	public AdminPage validate_user_details_page_menu_button() {
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		Assert.assertEquals(userDetailsMenuButtons.get(0).getText(), "Details");
		Assert.assertEquals(userDetailsMenuButtons.get(1).getText(), "Access");
		Assert.assertEquals(userDetailsMenuButtons.get(2).getText(), "Accounts");
		Assert.assertEquals(userDetailsMenuButtons.get(3).getText(), "Work Reassignment");
		Assert.assertEquals(userDetailsMenuButtons.get(4).getText(), "Events");
		//Assert.assertEquals(userDetailsMenuButtons.get(5).getText(), "Work Reassignment");
		return this;
	}

	public AdminPage validate_accounts_menu_user_details_page() {
		userDetailsMenuButtons.get(2).click();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		Assert.assertEquals(userDetailsMenuElements.get(0).getText(), "Active Directory");
		Assert.assertEquals(userDetailsMenuElements.get(1).getText(), "DEV GPC Global IdentityNow");
		Assert.assertEquals(userDetailsMenuElements.get(2).getText(), "Workday");
		return this;
	}

	public AdminPage validate_application_menu_user_details_page() {
		userDetailsMenuButtons.get(2).click();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		Assert.assertEquals(userDetailsMenuElements.get(3).getText(), "Active Directory");
		return this;
	}

	public AdminPage validate_roles_menu_user_details_page() {
		userDetailsMenuButtons.get(3).click();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		Assert.assertEquals(userDetailsMenuElements.get(4).getText(), "GenPt domain basic access");
		return this;
	}

	public AdminPage validate_activity_menu_user_details_page() {
		userDetailsMenuButtons.get(4).click();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		Assert.assertEquals(userMenuIdentityActivityTxt.getText(), "Identity Activity");
		return this;
	}

	public AdminPage validate_work_reassignment_menu_user_details_page() {
		userDetailsMenuButtons.get(5).click();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		Assert.assertTrue(reassignmentActionsDropdown.isDisplayed());
		reassignmentActionsDropdown.click();
		deatilsCloseBtn.click();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public AdminPage change_lifecycle_state_to_inactive() {
		userDetailsActionBtn.click();
		wait_for_disappearance_of_modal_short_wait();
		setLifeCycleStatusLink.click();
		wait_for_disappearance_of_modal_short_wait();
		wait_for_clickable_of_xpath(SET_LIFECYCLE_STATUS_INACTIVE,30);
		setLifeCycleStatusInactive.click();
		wait_for_disappearance_of_modal_short_wait();
		setLifeCycleStatusSaveBtn.click();
		wait_for_disappearance_of_modal_long_wait();
		wait_for_disappearance_of_modal_short_wait();
		driver.navigate().refresh();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public AdminPage change_lifecycle_state_to_deleted() {
		wait_for_disappearance_of_modal_short_wait();
		wait_for_disappearance_of_modal_short_wait();
		wait_for_clickable_of_xpath(USER_DETAILS_ACTION_BTN,30);
		userDetailsActionBtn.click();
		wait_for_disappearance_of_modal_short_wait();
		setLifeCycleStatusLink.click();
		wait_for_disappearance_of_modal_short_wait();
		wait_for_clickable_of_xpath(SET_LIFECYCLE_STATUS_DELETED,30);
		setLifeCycleStatusDeleted.click();
		wait_for_disappearance_of_modal_long_wait();
		wait_for_disappearance_of_modal_short_wait();
		setLifeCycleStatusSaveBtn.click();
		wait_for_disappearance_of_modal();
		driver.navigate().refresh();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public AdminPage change_lifecycle_state_to_active() {
		lifecycleStateChange.click();
		lifecycleStateItems.get(0).click();
		wait_for_disappearance_of_modal();
		driver.navigate().refresh();		
		return this;
	}
	
	public AdminPage validate_user_details_inactive_lifecycle_state() {
		wait_for_disappearance_of_modal();
		String lifecycleState = lifeCycleState.getText();
		String[] extractedState = lifecycleState.split(" ");
		String firstSubString = extractedState[0];
		Assert.assertEquals(firstSubString, "inactive");
		return this;
	}

	public AdminPage click_on_workday_link() {
		wait_for_disappearance_of_modal();
		List<String> list1 = new ArrayList<>();
		for (WebElement dataList1 : accountsNameFieldValues) {
			list1.add(dataList1.getText());
		}
		
		List<String> list2 = new ArrayList<>();
		for (WebElement dataList2 : accountsSourceNameFieldValues) {
			list2.add(dataList2.getText());
		}
		
		for(int i=0; i<list2.size(); i++)
		{
			//System.out.println(list2.get(i));
			if(list2.get(i).contains("Workday"))
			{
				System.out.println("=============");
				System.out.println(list1.get(i));
				accountsNameFieldValues.get(i).click();
				break;
			}
		}
		
		return this;
	}
	
	public AdminPage click_on_active_directory_link() {
		wait_for_disappearance_of_modal();
		List<String> list1 = new ArrayList<>();
		for (WebElement dataList1 : accountsNameFieldValues) {
			list1.add(dataList1.getText());
		}
		
		List<String> list2 = new ArrayList<>();
		for (WebElement dataList2 : accountsSourceNameFieldValues) {
			list2.add(dataList2.getText());
		}
		
		boolean apac=false;
		for(int i=0; i<list2.size(); i++)
		{
			//System.out.println(list2.get(i));
			if(list2.get(i).contains("APAC Industrial"))
			{
				System.out.println("=============");
				System.out.println(list1.get(i));
				accountsNameFieldValues.get(i).click();
				apac=true;
				break;
				
			} 
		}
		System.out.println("apac ="+apac);
		
		if(apac==false){		
			for(int j=0; j<list2.size(); j++)
			{
			
			if (list2.get(j).contains("Active Directory"))
			{
				System.out.println("=============");
				System.out.println(list1.get(j));
				accountsNameFieldValues.get(j).click();
				break;
			}
			
			}
		}	
		return this;
	}
	
	public AdminPage validate_active_directory_is_disabled() {
		wait_for_disappearance_of_modal();
		List<String> list1 = new ArrayList<>();
		for (WebElement dataList1 : accountsStatusFieldValues) { 
			list1.add(dataList1.getText());
		}
		
		List<String> list2 = new ArrayList<>();
		for (WebElement dataList2 : accountsSourceNameFieldValues) {
			list2.add(dataList2.getText());
		}
		
		for(int i=0; i<list2.size()-1; i++)
		{
			//System.out.println(list2.get(i));
			if(list2.get(i).contains("Active Directory"))
			{
//				System.out.println("=============");
				System.out.println(list1.get(i));
				Assert.assertEquals(accountsStatusFieldValues.get(i).getText(), "Disabled");
				//accountsNameFieldValues.get(i).click();
				break;
			}
		}
		return this;
	}
	
	
	public AdminPage validate_active_directory_is_disabled1(){
		driver.navigate().refresh();
		wait_for_disappearance_of_modal();
		wait_for_appearance_of_xpath(ACTIVE_DIR_STATUS,30);
		System.out.println("Active Directory status:"+activeDirStatus.getText());
		Assert.assertEquals(activeDirStatus.getText(), "Disabled");
		
		return this;
	}
	
	public AdminPage validate_user_details_prehire_lifecycle_state() {
		wait_for_disappearance_of_modal_long_wait();
		List<String> list1 = new ArrayList<>();
		for (WebElement dataList1 : detailsDataList1) {
			list1.add(dataList1.getText());
			scroll_to_element(dataList1);
		}

		List<String> list2 = new ArrayList<>();
		for (WebElement dataList2 : detailsDataList2) {
			list2.add(dataList2.getText());
			scroll_to_element(dataList2);
		}

		String[][] arr = new String[list1.size()][2];
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j <= 1; j++) {
				if (j == 0) {
					arr[i][j] = list1.get(i);
				} else {
					arr[i][j] = list2.get(i);

				}
			}
		}

		String lifecycleState = lifeCycleState.getText();
		String[] extractedState = lifecycleState.split(" ");
		String firstSubString = extractedState[0];
		Assert.assertEquals(firstSubString, "prehire");   //prehire
		if (firstSubString.equalsIgnoreCase("prehire")) {
			System.out.println("State is Pehire");
			for (int p = 0; p < arr.length; p++) {
				for (int q = 0; q < 2; q++) {

					if ("Rehire".equals(arr[p][q])) {
						String userDetailValue = arr[p][q + 1];
						int rehire = Integer.parseInt(userDetailValue);
						if (rehire == 1) {
							System.out.println("Lifecycle State : Prehire | Prehire is equal to 1.");
						}
					}
				}

			}
		}
		return this;
	}
	
	public AdminPage validate_user_details_active_lifecycle_state() {
		wait_for_disappearance_of_modal_long_wait();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date StartDate = null;
		Date EndDate = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		Calendar calendar = Calendar.getInstance();

		Date currentDate = null;

		List<String> list1 = new ArrayList<>();
		for (WebElement dataList1 : detailsDataList1) {
			list1.add(dataList1.getText());
			//scroll_to_element(dataList1);
		}

		List<String> list2 = new ArrayList<>();
		for (WebElement dataList2 : detailsDataList2) {
			list2.add(dataList2.getText());
			//scroll_to_element(dataList2);
		}

		String[][] arr = new String[list1.size()][2];
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j <= 1; j++) {
				if (j == 0) {
					arr[i][j] = list1.get(i);
				} else {
					arr[i][j] = list2.get(i);

				}
			}
		}

		String lifecycleState = lifeCycleState.getText();
		String[] extractedState = lifecycleState.split(" ");
		String firstSubString = extractedState[0];
		Assert.assertEquals(firstSubString, "active");
		if (firstSubString.equalsIgnoreCase("Active")) {
			System.out.println("State is Active");
			for (int p = 0; p < arr.length; p++) {
				for (int q = 0; q < 2; q++) {

					if ("Start Date".equals(arr[p][q])) {
						String userDetailValue = arr[p][q + 1];
						try {
							StartDate = (Date) sdf.parse(userDetailValue);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							currentDate = (Date) sdf.parse(dtf.format(now));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (StartDate.before(currentDate)) {
							System.out.println("Lifecycle State : Active | Start Date is less than Today's Date.");
						}
					}
				}

			}
		}
		return this;
	}
	
	public AdminPage validate_user_details_deleted_lifecycle_state() {
		wait_for_disappearance_of_modal_long_wait();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date EndDate = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		Calendar calendar = Calendar.getInstance();

		Date currentDate = null;

		List<String> list1 = new ArrayList<>();
		for (WebElement dataList1 : detailsDataList1) {
			list1.add(dataList1.getText());
			//scroll_to_element(dataList1);
		}

		List<String> list2 = new ArrayList<>();
		for (WebElement dataList2 : detailsDataList2) {
			list2.add(dataList2.getText());
			//scroll_to_element(dataList2);
		}

		String[][] arr = new String[list1.size()][2];
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j <= 1; j++) {
				if (j == 0) {
					arr[i][j] = list1.get(i);
				} else {
					arr[i][j] = list2.get(i);

				}
			}
		}

		for (int p = 0; p < arr.length; p++) {
			for (int q = 0; q < 2; q++) {

				if ("End Date".equals(arr[p][q])) {
					String userDetailValue = arr[p][q + 1];
					try {
						EndDate = (Date) sdf.parse(userDetailValue);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						currentDate = (Date) sdf.parse(dtf.format(now));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					calendar.setTime(EndDate);
					calendar.add(Calendar.DAY_OF_MONTH, 90);
					Date updatedDate = calendar.getTime();

					if (updatedDate.before(currentDate)) {
						System.out.println("Lifecycle State : Deleted | End Date + 90 is less than Today's Date");
					}
				}
			}

		}
	
		return this;
	}
	
	
	public AdminPage validate_user_details_rehire_lifecycle_state() {
		wait_for_disappearance_of_modal_long_wait();
		List<String> list1 = new ArrayList<>();
		for (WebElement dataList1 : detailsDataList1) {
			list1.add(dataList1.getText());
			scroll_to_element(dataList1);
		}

		List<String> list2 = new ArrayList<>();
		for (WebElement dataList2 : detailsDataList2) {
			list2.add(dataList2.getText());
			scroll_to_element(dataList2);
		}

		String[][] arr = new String[list1.size()][2];
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j <= 1; j++) {
				if (j == 0) {
					arr[i][j] = list1.get(i);
				} else {
					arr[i][j] = list2.get(i);

				}
			}
		}

		String lifecycleState = lifeCycleState.getText();
		String[] extractedState = lifecycleState.split(" ");
		String firstSubString = extractedState[0];
		Assert.assertEquals(firstSubString, "rehire");
		if (firstSubString.equalsIgnoreCase("rehire")) {
			System.out.println("State is Rehire");
			for (int p = 0; p < arr.length; p++) {
				for (int q = 0; q < 2; q++) {

					if ("Rehire".equals(arr[p][q])) {
						String userDetailValue = arr[p][q + 1];
						int rehire = Integer.parseInt(userDetailValue);
						if (rehire == 1) {
							System.out.println("Lifecycle State : Rehire | Rehire is equal to 1.");
						}
					}
				}

			}
		}
		return this;
	}
	
	public AdminPage validate_user_details_rescinded_lifecycle_state() {
		wait_for_disappearance_of_modal_long_wait();

		List<String> list1 = new ArrayList<>();
		for (WebElement dataList1 : detailsDataList1) {
			list1.add(dataList1.getText());
			scroll_to_element(dataList1);
		}

		List<String> list2 = new ArrayList<>();
		for (WebElement dataList2 : detailsDataList2) {
			list2.add(dataList2.getText());
			scroll_to_element(dataList2);
		}

		String[][] arr = new String[list1.size()][2];
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j <= 1; j++) {
				if (j == 0) {
					arr[i][j] = list1.get(i);
				} else {
					arr[i][j] = list2.get(i);

				}
			}
		}

		String lifecycleState = lifeCycleState.getText();
		String[] extractedState = lifecycleState.split(" ");
		String firstSubString = extractedState[0];
		Assert.assertEquals(firstSubString, "rescinded");
		 if (firstSubString.equalsIgnoreCase("Rescinded")) {
			System.out.println("State is Rescinded");
			for (int p = 0; p < arr.length; p++) {
				for (int q = 0; q < 2; q++) {

					if ("Rescinded".equals(arr[p][q])) {
						String userDetailValue = arr[p][q + 1];
						int rehire = Integer.parseInt(userDetailValue);
						if (rehire == 0) {
							System.out.println("Lifecycle State : Rescinded ");
						}
					}
				}

			}
		}
		return this;
	}
	


	public AdminPage validate_userdetails_lifecycle_state() {
		wait_for_disappearance_of_modal_long_wait();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date StartDate = null;
		Date EndDate = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		Calendar calendar = Calendar.getInstance();

		Date currentDate = null;

		List<String> list1 = new ArrayList<>();
		for (WebElement dataList1 : detailsDataList1) {
			list1.add(dataList1.getText());
			scroll_to_element(dataList1);
		}

		List<String> list2 = new ArrayList<>();
		for (WebElement dataList2 : detailsDataList2) {
			list2.add(dataList2.getText());
			scroll_to_element(dataList2);
		}

		String[][] arr = new String[list1.size()][2];
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j <= 1; j++) {
				if (j == 0) {
					arr[i][j] = list1.get(i);
				} else {
					arr[i][j] = list2.get(i);

				}
			}
		}

		String lifecycleState = lifeCycleState.getText();
		String[] extractedState = lifecycleState.split(" ");
		String firstSubString = extractedState[0];
		// Assert.assertEquals(firstSubString, "Active");
		if (firstSubString.equalsIgnoreCase("Active")) {
			System.out.println("State is Active");
			for (int p = 0; p < arr.length; p++) {
				for (int q = 0; q < 2; q++) {

					if ("Start Date".equals(arr[p][q])) {
						String userDetailValue = arr[p][q + 1];
						try {
							StartDate = (Date) sdf.parse(userDetailValue);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							currentDate = (Date) sdf.parse(dtf.format(now));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (StartDate.before(currentDate)) {
							System.out.println("Lifecycle State : Active | Start Date is less than Today's Date.");
						}
					}
				}

			}
		}

		else if (firstSubString.equalsIgnoreCase("rehire")) {
			System.out.println("State is Rehire");
			for (int p = 0; p < arr.length; p++) {
				for (int q = 0; q < 2; q++) {

					if ("Rehire".equals(arr[p][q])) {
						String userDetailValue = arr[p][q + 1];
						int rehire = Integer.parseInt(userDetailValue);
						if (rehire == 1) {
							System.out.println("Lifecycle State : Rehire | Rehire is equal to 1.");
						}
					}
				}

			}
		}
		
		else if (firstSubString.equalsIgnoreCase("rescinded")) {
			System.out.println("State is Rescinded");
			for (int p = 0; p < arr.length; p++) {
				for (int q = 0; q < 2; q++) {

					if ("Rescinded".equals(arr[p][q])) {
						String userDetailValue = arr[p][q + 1];
						int rehire = Integer.parseInt(userDetailValue);
						if (rehire == 0) {
							System.out.println("Lifecycle State : Rescinded ");
						}
					}
				}

			}
		}

		else if (firstSubString.equalsIgnoreCase("inactive")) {

			System.out.println("State is Inactive");
			for (int p = 0; p < arr.length; p++) {
				for (int q = 0; q < 2; q++) {

					if ("End Date".equals(arr[p][q])) {
						String userDetailValue = arr[p][q + 1];
						try {
							EndDate = (Date) sdf.parse(userDetailValue);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							currentDate = (Date) sdf.parse(dtf.format(now));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						calendar.setTime(currentDate);
						calendar.add(Calendar.DAY_OF_MONTH, -90);
						Date updatedDate = calendar.getTime();

						// String inactiveDays = ZonedDateTime.parse(currentDate,
						// dtf).plusDays(90).format(DateTimeFormatter.ISO_LOCAL_DATE);
//						String inactiveDays = OffsetDateTime.parse(currentDate, dtf).plusDays(90).format(DateTimeFormatter.ISO_LOCAL_DATE);
						/// Date inactiveDay = null;
//						try {
//							inactiveDay = (Date) sdf.parse(inactiveDays);
//						} catch (ParseException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						if (EndDate.before(currentDate)) {
							if (EndDate.after(updatedDate)) {
								System.out.println(
										"Lifecycle State : Inactive | End Date is less than Today's Date and within 90 Days");
							}
						}
					}
				}

			}
		}

		else if (firstSubString.equalsIgnoreCase("prehire")) {
			System.out.println("State is Prehire");
			for (int p = 0; p < arr.length; p++) {
				for (int q = 0; q < 2; q++) {

					if ("Start Date".equals(arr[p][q])) {
						String userDetailValue = arr[p][q + 1];
						try {
							StartDate = (Date) sdf.parse(userDetailValue);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							currentDate = (Date) sdf.parse(dtf.format(now));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (StartDate.after(currentDate)) {
							System.out.println("Lifecycle State : Prehire | Start Date is greater than Today's Date.");
						}
					}
				}

			}
		}

		else if (firstSubString.equalsIgnoreCase("deleted")) {

			System.out.println("State is Deleted");
			for (int p = 0; p < arr.length; p++) {
				for (int q = 0; q < 2; q++) {

					if ("End Date".equals(arr[p][q])) {
						String userDetailValue = arr[p][q + 1];
						try {
							EndDate = (Date) sdf.parse(userDetailValue);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							currentDate = (Date) sdf.parse(dtf.format(now));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						calendar.setTime(EndDate);
						calendar.add(Calendar.DAY_OF_MONTH, 90);
						Date updatedDate = calendar.getTime();

						if (updatedDate.before(currentDate)) {
							System.out.println("Lifecycle State : Deleted | End Date + 90 is less than Today's Date");
						}
					}
				}

			}
		}

		return this;
	}

	public AdminPage validate_display_name_to_user_name() {
		wait_for_disappearance_of_modal();
		Assert.assertTrue(detailsDataList2.get(0).isDisplayed());
		Assert.assertEquals(detailsDataList2.get(0).getText(), userDetailsIdentityName.getText());
		return this;
	}



	public AdminPage click_on_user_data_back_btn() {
		userDetailsBackBtn.get(0).click();
		wait_for_appearance_of_xpath(DETAILS_CLOSE_BTN, 30);
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		deatilsCloseBtn.click();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		identitiesSearchBar.clear();
		return this;
	}
	
	public AdminPage click_on_user_data_close_btn() {
		scroll_to_element(deatilsCloseBtn);
		deatilsCloseBtn.click();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		identitiesSearchBar.clear();
		return this;
	}

	public AdminPage clear_identity_search_bar_data()
	{
		identitiesSearchBar.clear();
		return this;
	}
	
	public AdminPage validate_details_data() {
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
//		ArrayList<String> [][] list = new ArrayList[30][30];
//		list[0][0] = new ArrayList<>();
//		
//		for(int i =0 ; i <30 ; i++) {
//			for(int j = 0 ; j<=1 ; j++) {
//				if(j ==0) {
//					list.add(list1.get())
//				}
//			}
//		}
//		
//		
		List<String> list1 = new ArrayList<>();
		for (WebElement dataList1 : detailsDataList1) {
			list1.add(dataList1.getText());
			scroll_to_element(dataList1);
		}
		// System.out.println(list1.get(0));
		List<String> list2 = new ArrayList<>();
		for (WebElement dataList2 : detailsDataList2) {
			list2.add(dataList2.getText());
			scroll_to_element(dataList2);
		}
		String[][] arr = new String[list1.size()][list1.size()];
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j <= 1; j++) {
				if (j == 0) {
					arr[i][j] = list1.get(i);
					// System.out.println(arr[i][j]);
				} else {
					arr[i][j] = list2.get(i);
					// System.out.println(arr[i][j]);
				}
			}
		}

//		for(int i = 0 ; i<arr.length ; i++) {
//			for(int j = 0 ; j<=1; j++) {
//				System.out.println(arr[i][j]);
//			}
//		}

//		List<String> list1 = new ArrayList<>();
//		for(WebElement dataList1 : detailsDataList1)
//		{
//			list1.add(dataList1.getText());
//			scroll_to_element(dataList1);
//			System.out.println(dataList1.getText());		
//		}
//		System.out.println("================================================");
//		List<String> list2 = new ArrayList<>();
//		for(WebElement dataList2 : detailsDataList2)
//		{
//			list2.add(dataList2.getText());
//			scroll_to_element(dataList2);
//			System.out.println(dataList2.getText());		
//		}

		return this;
	}

	public AdminPage validate_accounts_tab_active_directory_data() {
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		Assert.assertEquals(userDetailsMenuButtons.get(2).getText(), "Accounts");
		userDetailsMenuButtons.get(2).click();
		wait_for_disappearance_of_modal();
		accountsTabLink.get(2).click();
		wait_for_disappearance_of_modal();
		List<String> list1 = new ArrayList<>();
		for (WebElement dataList1 : accountsADColumn1DataList) {
			list1.add(dataList1.getText());
			scroll_to_element(dataList1);
		}
		// System.out.println(list1.get(0));
		List<String> list2 = new ArrayList<>();
		for (WebElement dataList2 : accountsADColumn2DataList) {
			list2.add(dataList2.getText());
			scroll_to_element(dataList2);
		}
		String[][] arr = new String[list1.size()][list1.size()];
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j <= 1; j++) {
				if (j == 0) {
					arr[i][j] = list1.get(i);
					// System.out.println(arr[i][j]);
				} else {
					arr[i][j] = list2.get(i);
					// System.out.println(arr[i][j]);
				}
			}
		}

		return this;
	}

	public AdminPage validate_accounts_tab_workday_data() {
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		userDetailsMenuButtons.get(2).click();
		wait_for_disappearance_of_modal();
		//userDetailsMenuElements.get(2).click();
		click_on_workday_link();
		//accountsTabLink.get(0).click();
		wait_for_disappearance_of_modal();
		List<String> list1 = new ArrayList<>();
		for (WebElement dataList1 : accountsADColumn1DataList) {
			list1.add(dataList1.getText());
			scroll_to_element(dataList1);
		}
		// System.out.println(list1.get(0));
		List<String> list2 = new ArrayList<>();
		for (WebElement dataList2 : accountsADColumn2DataList) {
			list2.add(dataList2.getText());
			scroll_to_element(dataList2);
		}
		String[][] arr = new String[list1.size()][list1.size()];
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j <= 1; j++) {
				if (j == 0) {
					arr[i][j] = list1.get(i);
					// System.out.println(arr[i][j]);
				} else {
					arr[i][j] = list2.get(i);
					// System.out.println(arr[i][j]);
				}
			}
		}

		return this;
	}

	public AdminPage validate_details_data_to_workday_data() {
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal_long_wait();
		// wait_for_disappearance_of_modal_long_wait();
		List<String> list1 = new ArrayList<>();
		for (WebElement dataList1 : detailsDataList1) {
			list1.add(dataList1.getText());
			scroll_to_element(dataList1);
		}
		//System.out.println(list1.size());
		// System.out.println(list1.get(0));
		List<String> list2 = new ArrayList<>();
		for (WebElement dataList2 : detailsDataList2) {
			list2.add(dataList2.getText());
			scroll_to_element(dataList2);
		}
		//System.out.println(list2.size());
		String[][] arr = new String[list1.size()][2];
		//System.out.println(arr.length);
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j <= 1; j++) {
				if (j == 0) {
					arr[i][j] = list1.get(i);
					//System.out.println(arr[i][j]);
				} else {
					arr[i][j] = list2.get(i);
					//System.out.println(arr[i][j]);
				}
			}
		}
	
		//scroll_to_element(userDetailsIdentityIcon);
		userDetailsMenuButtons.get(2).click();
		wait_for_disappearance_of_modal();
		//userDetailsMenuElements.get(2).click();
		click_on_workday_link();
		//accountsTabLink.get(0).click();
		wait_for_disappearance_of_modal();
		List<String> wdlist1 = new ArrayList<>();
		for (WebElement dataList1 : accountsADColumn1DataList) {
			wdlist1.add(dataList1.getText());
			scroll_to_element(dataList1);
		}
		//System.out.println(wdlist1.size());
		// System.out.println(list1.get(0));
		List<String> wdlist2 = new ArrayList<>();
		for (WebElement dataList2 : accountsADColumn2DataList) {
			wdlist2.add(dataList2.getText());
			scroll_to_element(dataList2);
		}
		//System.out.println(wdlist2.size());
		String[][] wdArr = new String[wdlist1.size()][2];
		System.out.println(wdArr.length);
		for (int i = 0; i < wdlist1.size(); i++) {
			for (int j = 0; j <= 1; j++) {
				if (j == 0) {
					wdArr[i][j] = wdlist1.get(i);
					//System.out.println(wdArr[i][j]);
				} else {
					wdArr[i][j] = wdlist2.get(i);
					//System.out.println(wdArr[i][j]);
				}
			}
		}

		/*
		 * System.out.println("Size of Arr array " + arr.length);
		 * System.out.println("Size of Workday array " + wdArr.length);
		 * System.out.println("Size of list 1 " + list1.size());
		 * System.out.println("Size of workday list " + wdlist1.size());
		 */
		String[][] expectedDataArray = new String[18][2];
		expectedDataArray[0][0] = "BUSINESS UNIT"; 
		expectedDataArray[0][1] = "Business_Unit__c";
		expectedDataArray[1][0] = "Business Unit Code";
		expectedDataArray[1][1] = "Business_Unit_code__c";
		expectedDataArray[2][0] = "City";
		expectedDataArray[2][1] = "CITY";
		expectedDataArray[3][0] = "Cost Center";
		expectedDataArray[3][1] = "COST_CENTER";
		expectedDataArray[4][0] = "Country";
		expectedDataArray[4][1] = "COUNTRY";
		expectedDataArray[5][0] = "Department";
		expectedDataArray[5][1] = "DEPARTMENT";
		expectedDataArray[6][0] = "Preferred First Name";
		expectedDataArray[6][1] = "PREFERRED_FIRST_NAME";
		expectedDataArray[7][0] = "Preferred Last Name";
		expectedDataArray[7][1] = "PREFERRED_LAST_NAME";
		expectedDataArray[8][0] = "Worker Type";
		expectedDataArray[8][1] = "WORKER_TYPE__c";
		expectedDataArray[9][0] = "Contract End Date";
		expectedDataArray[9][1] = "CONTRACT_END_DATE";
		expectedDataArray[10][0] = "Employee Number";
		expectedDataArray[10][1] = "FILENUMBER";
		expectedDataArray[11][0] = "Employee Type";
		expectedDataArray[11][1] = "EMPLOYEE_TYPE";
		expectedDataArray[12][0] = "End Date";
		expectedDataArray[12][1] = "TERMINATION_DATE";
		expectedDataArray[13][0] = "Contract End Date";
		expectedDataArray[13][1] = "TERMINATION_DATE";
		expectedDataArray[14][0] = "Rehire";
		expectedDataArray[14][1] = "REHIRE";
		expectedDataArray[15][0] = "Cost Center ID";
		expectedDataArray[15][1] = "COST_CENTER_REFERENCE_ID";
		expectedDataArray[16][0] = "Start Date";
		expectedDataArray[16][1] = "HIREDATE";
		expectedDataArray[17][0] = "GENPT AD Company";
		expectedDataArray[17][1] = "COMPANY_NAME";

		/*
		 * for(int i = 0 ; i<arr.length; i++) { for(int j = 0 ; j<=1; j++) {
		 * if(arr[i][j].equalsIgnoreCase(expectedDataArray[0][0]) ) { // &&
		 * expectedDataArray[0][1].equalsIgnoreCase(wdArr[i][j]))
		 * System.out.println(arr[i][j]); System.out.println("=================");
		 * //System.out.println(wdArr[i][j]);
		 * 
		 * } } }
		 */
//		String detailValue="";
//		String workDayvalue="";
//		for(int i = 0 ; i< expectedDataArray.length-3 ; i++) {
//			
//				//expectedDataArray[i][j];
//				
//				for(int p = 0 ; p< arr.length ; p++) {
//						
//						if( expectedDataArray[i][0].equalsIgnoreCase(arr[p][0])) {
//							detailValue= arr[p][1];
//							
//							for(int k =0 ; k<wdArr.length ; k++) {
//		
//									if(expectedDataArray[i][1].equalsIgnoreCase(wdArr[k][0])) {
//										workDayvalue= wdArr[k][1];	
//										
//										 if(detailValue.equalsIgnoreCase(workDayvalue)) {
//											 System.out.println("Match Found");
//										 }
//										 else {
//											 System.out.println("No Match Found");
//										 }
//									}
//									
//									
//							
//							}
//							
//						}
//					
//				}
//			
//		}

		int count1 = 0, count2 = 0;
		for (int i = 0; i < expectedDataArray.length - 3; i++) {

			for (int p = 0; p < arr.length; p++) {

				if (expectedDataArray[i][0].equalsIgnoreCase(arr[p][0])) {
					count1++;
					String userDetailValue = arr[p][1];
					System.out.println(count1++ + ". " + "User Details    | " + arr[p][0] + " : " + userDetailValue);
					count1--;
					if (userDetailValue.equals("--")) {
						System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");
						break;
					} else {
						for (int k = 0; k < wdArr.length; k++) {

							if (expectedDataArray[i][1].equalsIgnoreCase(wdArr[k][0])) {

								count2++;
								String workDayValue = wdArr[k][1];
								System.out.println("   WorkDay Details | " + wdArr[k][0] + " : " + workDayValue);
								if (userDetailValue.equalsIgnoreCase(workDayValue)) {
									System.out.println(" ~Match Found~ ");

								}

								else {
									System.out.println(" ===== Value did not match ===== ");
								}
								// System.out.println("i :"+i+ "and j :" +j);
								// System.out.println("k :"+k+ "and l :" +l);
							}

						}

					}
				}

			}

		}

		for (int p = 0; p < arr.length; p++) {
			for (int q = 0; q < 2; q++) {

				if (expectedDataArray[15][0].equalsIgnoreCase(arr[p][q])) {
					count1++;
					String userDetailValue = arr[p][q + 1];
					System.out.println(count1++ + ". " + "User Details    | " + arr[p][q] + " : " + userDetailValue);
					count1--;
					if (userDetailValue.equals("") || userDetailValue.equals(" ")) {
						System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");
						break;
					} else {
						for (int k = 0; k < wdArr.length; k++) {
							for (int l = 0; l < 2; l++) {

								if (expectedDataArray[15][1].equalsIgnoreCase(wdArr[k][l])) {
									count2++;
									String workDayValue = wdArr[k][l + 1];
									System.out.println("   WorkDay Details | " + wdArr[k][l] + " : " + workDayValue);

									Pattern pattern = Pattern.compile(userDetailValue);
									Matcher matcher = pattern.matcher(workDayValue);

									boolean found = false;
									while (matcher.find()) {
										// System.out.println("I found the text "+matcher.group()+" starting at index
										// "+matcher.start()+" and ending at index "+matcher.end());
										System.out.println(" ~Match Found~ ");
										found = true;
									}
									if (!found) {
										System.out.println("No match found.");
									}
								}

							}
						}
					}
				}
			}
		}

		for (int p = 0; p < arr.length; p++) {
			for (int q = 0; q < 2; q++) {

				if (expectedDataArray[16][0].equalsIgnoreCase(arr[p][q])) {
					count1++;
					String userDetailValue = arr[p][q + 1];
					System.out.println(count1++ + ". " + "User Details    | " + arr[p][q] + " : " + userDetailValue);
					count1--;
					if (userDetailValue.equals("") || userDetailValue.equals(" ")) {
						System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");
						break;
					} else {
						for (int k = 0; k < wdArr.length; k++) {
							for (int l = 0; l < 2; l++) {

								if (expectedDataArray[16][1].equalsIgnoreCase(wdArr[k][l])) {
									count2++;
									String workDayValue = wdArr[k][l + 1];
									System.out.println("   WorkDay Details | " + wdArr[k][l] + " : " + workDayValue);

									SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
									Date StartDate = null;
									try {
										StartDate = (Date) sdfo.parse(userDetailValue);
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									Date HIREDATE = null;
									try {
										HIREDATE = (Date) sdf.parse(workDayValue);
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									// System.out.println("StartDate: " + sdfo.format(StartDate));
									// System.out.println("HIREDATE: " + sdf.format(HIREDATE));

									if (StartDate.equals(HIREDATE)) {
										System.out.println(" ~Match Found~ ");
									}

								}
							}
						}

					}
				}
			}
		}

		for (int p = 0; p < arr.length; p++) {
			for (int q = 0; q < 2; q++) {

				if (expectedDataArray[17][0].equalsIgnoreCase(arr[p][q])) {
					count1++;
					String userDetailValue = arr[p][q + 1];
					System.out.println(count1++ + ". " + "User Details    | " + arr[p][q] + " : " + userDetailValue);
					count1--;
					if (userDetailValue.equals("") || userDetailValue.equals(" ")) {
						System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");
						break;
					} else {
						for (int k = 0; k < wdArr.length; k++) {
							for (int l = 0; l < 2; l++) {

								if (expectedDataArray[17][1].equalsIgnoreCase(wdArr[k][l])) {
									count2++;
									String workDayValue = wdArr[k][l + 1];
									System.out.println("   WorkDay Details | " + wdArr[k][l] + " : " + workDayValue);

									String s3 = "";
									Pattern p1 = Pattern.compile("\\b[a-zA-Z]");
									Matcher m = p1.matcher(workDayValue);

									while (m.find()) {
										s3 = s3 + m.group();

									}
									if (userDetailValue.equals(s3)) {
										System.out.println(" ~Match Found~ ");
									}

									// System.out.println(s3);

								}
							}
						}
					}
				}
			}
		}

		return this;
	}

	public AdminPage click_on_details_tab() {
		userDetailsMenuButtons.get(0).click();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public AdminPage click_on_accounts_tab() {
		userDetailsMenuButtons.get(2).click();
		wait_for_disappearance_of_modal();
		return this;
	}
	
	public AdminPage validate_ad_disabled_for_urgent_termination() {
//		Assert.assertEquals(accountsStatusData.get(3).getText(), "Disabled");
//		wait_for_disappearance_of_modal();
		
		
		return this;
	}
	
	public AdminPage validate_ad_is_deleted() {
//		boolean adLink = activeDirectoryLink.isDisplayed();
//		Assert.assertFalse(activeDirectoryLink.isDisplayed());
			
		boolean flag = true;
		for(int i=0; i<accountsSourceNameFieldValues.size();i++)
		{
			if(accountsSourceNameFieldValues.get(i).getText().contains("Active Directory")) {
				flag= false;

			}
		}
		
		
		if(flag== false)
		
			System.out.println("Active Directory is present");
		else
		System.out.println("Active Directory is not present");
		return this;
	}

	public AdminPage validate_active_directory_data_to_workday_data() {
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		userDetailsMenuButtons.get(2).click();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		click_on_active_directory_link();
		//userDetailsMenuElements.get(0).click();
		//click_on_workday_link();
		//accountsTabLink.get(0).click();
		wait_for_disappearance_of_modal();
		List<String> adlist1 = new ArrayList<>();
		for (WebElement dataList1 : accountsADColumn1DataList) {
			adlist1.add(dataList1.getText());
			//System.out.println(adlist1.size());
			scroll_to_element(dataList1);
		}
		// System.out.println(list1.get(0));
		List<String> adlist2 = new ArrayList<>();
		for (WebElement dataList2 : accountsADColumn2DataList) {
			adlist2.add(dataList2.getText());
			//System.out.println(adlist2.size());
			scroll_to_element(dataList2);
		}
		String[][] adArr = new String[adlist1.size()][2];
		for (int i = 0; i < adlist2.size(); i++) {
			for (int j = 0; j <= 1; j++) {
				if (j == 0) {
					adArr[i][j] = adlist1.get(i);
					System.out.println("Attribute name"+adArr[i][j]);

				} else {
					adArr[i][j] = adlist2.get(i);
					System.out.println("VAlue"+adArr[i][j]);

				}
			}
		}

		//scroll_to_element(userDetailsIdentityIcon);
		//userDetailsBackBtn.get(1).click();
		//wait_for_disappearance_of_modal();
		userDetailsBackBtn.get(0).click();
		//userDetailsMenuButtons.get(1).click();
		//wait_for_disappearance_of_modal();
		//userDetailsMenuElements.get(2).click();
		wait_for_appearance_of_css(USER_MENU_ACCOUNTS_TAB_LINK, 30);
		//accountsTabLink.get(2).click();
		click_on_workday_link();
		wait_for_disappearance_of_modal();
		List<String> wdlist1 = new ArrayList<>();
		for (WebElement dataList1 : accountsADColumn1DataList) {
			wdlist1.add(dataList1.getText());
			scroll_to_element(dataList1);
		}
		// System.out.println(list1.get(0));
		List<String> wdlist2 = new ArrayList<>();
		for (WebElement dataList2 : accountsADColumn2DataList) {
			wdlist2.add(dataList2.getText());
			scroll_to_element(dataList2);
		}
		String[][] wdArr = new String[wdlist1.size()][2];
		for (int i = 0; i < wdlist1.size(); i++) {
			for (int j = 0; j <= 1; j++) {
				if (j == 0) {
					wdArr[i][j] = wdlist1.get(i);
					// System.out.println(arr[i][j]);
				} else {
					wdArr[i][j] = wdlist2.get(i);
					// System.out.println(arr[i][j]);
				}
			}
		}

		String[][] expectedDataArray = new String[14][2];
		expectedDataArray[0][0] = "sAMAccountName";
		expectedDataArray[0][1] = "FILENUMBER";
		expectedDataArray[1][0] = "department";
		expectedDataArray[1][1] = "COST_CENTER";
		expectedDataArray[2][0] = "extensionAttribute5";
		expectedDataArray[2][1] = "WORKER_TYPE__c";
		expectedDataArray[3][0] = "givenName";
		expectedDataArray[3][1] = "PREFERRED_FIRST_NAME";
		expectedDataArray[4][0] = "postalCode";
		expectedDataArray[4][1] = "POSTAL_CODE";
		expectedDataArray[5][0] = "sn";
		expectedDataArray[5][1] = "PREFERRED_LAST_NAME";
		expectedDataArray[6][0] = "employeeID";
		expectedDataArray[6][1] = "FILENUMBER";
		expectedDataArray[7][0] = "employeeType";
		expectedDataArray[7][1] = "EMPLOYEE_TYPE";
		expectedDataArray[8][0] = "cn";
		expectedDataArray[8][1] = "WORKER_NAME";
		expectedDataArray[9][0] = "userPrincipalName";
		expectedDataArray[9][1] = "";
		expectedDataArray[10][0] = "extensionAttribute10";
		expectedDataArray[10][1] = "JOBCODE";
		expectedDataArray[11][0] = "company";
		expectedDataArray[11][1] = "COMPANY_NAME";
		expectedDataArray[12][0] = "employeeID";
		expectedDataArray[12][1] = "";
		expectedDataArray[13][0] = "co";
		expectedDataArray[13][1] = "COUNTRY";

		/*
		 * for(int i = 0 ; i<arr.length; i++) { for(int j = 0 ; j<=1; j++) {
		 * if(arr[i][j].equalsIgnoreCase(expectedDataArray[0][0]) ) { // &&
		 * expectedDataArray[0][1].equalsIgnoreCase(wdArr[i][j]))
		 * System.out.println(arr[i][j]); System.out.println("=================");
		 * //System.out.println(wdArr[i][j]);
		 * 
		 * } } }
		 */
		int count1 = 0, count2 = 0;
		for (int i = 0; i < expectedDataArray.length - 7; i++) {
			

				for (int p = 0; p < adArr.length; p++) {
					

						if (expectedDataArray[i][0].equalsIgnoreCase(adArr[p][0])) {
							count1++;
							String userDetailValue = adArr[p][ 1];
							System.out.println(
									count1++ + ". " + "Active Directory  | " + adArr[p][0] + " : " + userDetailValue);
							count1--;
							if (userDetailValue.equals("--")) {
								System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");
								break;
							} 
							else{
							
							if(userDetailValue.equals("") || userDetailValue.equals(" ")) {
								System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");
								break;
							}
							else {
							for (int k = 0; k < wdArr.length; k++) {
								 

									if (expectedDataArray[i][1].equalsIgnoreCase(wdArr[k][0])) {
										count2++;
										String workDayValue = wdArr[k][1];
										System.out.println(
												"   WorkDay Details   | " + wdArr[k][0] + " : " + workDayValue);
										if (userDetailValue.equalsIgnoreCase(workDayValue)) {
											System.out.println(" ~Match Found~ ");

										}

										else {
											System.out.println(" ===== Value did not match ===== ");
										}
									}
								}
							}
						  }
						}
					
				}
			
		}

		for (int p = 0; p < adArr.length; p++) {
			

				if (expectedDataArray[7][0].equals(adArr[p][0])) {
					count1++;
					String userDetailValue = adArr[p][1];
					System.out
							.println(count1++ + ". " + "Active Directory  | " + adArr[p][0] + " : " + userDetailValue);
					count1--;
					if(userDetailValue.equals("") || userDetailValue.equals(" ")) {
						System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");
						break;
					}
					else {
					boolean userDetailValue_flag = false;
					if(userDetailValue.equals("Salaried") || userDetailValue.equals("Salary")) {
						userDetailValue_flag = true;
					}
					for (int k = 0; k < wdArr.length; k++) {
						

							if (expectedDataArray[7][1].equals(wdArr[k][0])) {
								count2++;
								String workDayValue = wdArr[k][1];
								System.out.println("   WorkDay Details   | " + wdArr[k][0] + " : " + workDayValue);
								
								boolean workDayValue_flag = false;
								if(workDayValue.equals("Salaried") || workDayValue.equals("Salary")) {
									workDayValue_flag = true;
								}
								
								if (userDetailValue_flag == true && workDayValue_flag == true) {
									System.out.println(" ~Match Found~ ");
								}
								else {
									System.out.println("No match found.");
								}

							}
						
					}
					}
				}
			
		}
		
		for (int p = 0; p < adArr.length; p++) {
			

				if (expectedDataArray[8][0].equals(adArr[p][0])) {
					count1++;
					String userDetailValue = adArr[p][ 1];
					System.out
							.println(count1++ + ". " + "Active Directory  | " + adArr[p][0] + " : " + userDetailValue);
					count1--;
					if(userDetailValue.equals("") || userDetailValue.equals(" ")) {
						System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");
						break;
					}
					else {
					for (int k = 0; k < wdArr.length; k++) {
						

							if (expectedDataArray[8][1].equals(wdArr[k][0])) {
								count2++;
								String workDayValue = wdArr[k][1];
								System.out.println("   WorkDay Details   | " + wdArr[k][0] + " : " + workDayValue);
								int temp = 0;
								for (int i = 0; i < userDetailValue.length(); i++) {
									if ((userDetailValue.charAt(i)) == (workDayValue.charAt(temp))) {
										temp++;
										if (temp == workDayValue.length()) {
											System.out.println(" ~Match Found~ ");
											Assert.assertEquals(temp, workDayValue.length());
											break;
										}
//								     else{
//								        	System.out.println("No match found.");
//								     }
									} else {
										temp = 0;
									}

								}

							}
						}
					}
					
				}
			
		}

		for (int p = 0; p < adArr.length; p++) {
			

				if (expectedDataArray[10][0].equals(adArr[p][0])) {
					count1++;
					String userDetailValue = adArr[p][ 1];
					System.out
							.println(count1++ + ". " + "Active Directory  | " + adArr[p][0] + " : " + userDetailValue);
					count1--;
					if(userDetailValue.equals("") || userDetailValue.equals(" ")) {
						System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");
						break;
					}
					else {
					for (int k = 0; k < wdArr.length; k++) {
						

							if (expectedDataArray[10][1].equals(wdArr[k][0])) {
								count2++;
								String workDayValue = wdArr[k][1];
								String extractedValue = "";
								for (int i = 0; i < 3; i++) {
									extractedValue = extractedValue + workDayValue.charAt(i);
								}
								System.out.println("   WorkDay Details   | " + wdArr[k][0] + " (" + wdArr[k][0]
										+ "=<Company>+JC)" + " : " + extractedValue + "JC");

								Pattern pattern = Pattern.compile(userDetailValue);
								Matcher matcher = pattern.matcher(extractedValue + "JC");

								boolean found = false;
								while (matcher.find()) {
									// System.out.println("I found the text "+matcher.group()+" starting at index
									// "+matcher.start()+" and ending at index "+matcher.end());
									System.out.println(" ~Match Found~ ");
									found = true;
								}
								if (!found) {
									System.out.println("No match found.");
								}
							}
						}
						
					}
			}
			}
		

		for (int k = 0; k < wdArr.length; k++) {
			
				if (wdArr[k][0].equalsIgnoreCase("MANAGER_NAME") || wdArr[k][0].equalsIgnoreCase("manager")) {
					managerName = wdArr[k][1];
				}

			}
		

		for (int k = 0; k < adArr.length; k++) {
			

				if (adArr[k][0].equalsIgnoreCase("company")|| adArr[k][0].equalsIgnoreCase("COMPANY_NAME")) {
					company = adArr[k][1];
				}

				if (adArr[k][0].equals("cn")) {
					cnField = adArr[k][ 1];
				}

				if (adArr[k][0].equals("distinguishedName") || adArr[k][0].equals("DN")) {
					actualdistinguishedName = adArr[k][ 1];
				}

				if (adArr[k][0].equals("manager")) {
					actualManagerField = adArr[k][ 1];
				}

			}
		
		count1++;
		System.out.println(count1++ + ". " + "Actual Manager    : " + actualManagerField);
		
		expectedManagerField = "CN=" + managerName + ",OU=" + company + ",OU=USER,DC=iga,DC=TEST";
		
		System.out.println("    Expected Manager  : " + expectedManagerField);
		
		// Assert.assertEquals(actualManagerField, expectedManagerField);
		if(actualManagerField.equals("") || actualManagerField.equals(" ")) {
			System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");

		}

		else if (actualManagerField.equals("--")) {
			System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");
		} 
		
		else {
			int i =3;
			String extractedManagerName="";
			while( actualManagerField.charAt(i) != ',' ) {
				extractedManagerName=extractedManagerName+actualManagerField.charAt(i);
				i++;
			
			}
			//System.out.println(extractedManagerName);
			
			int temp = 0;
			for (int j = 0; j < extractedManagerName.length(); j++) {
				if ((extractedManagerName.charAt(j)) == (managerName.charAt(temp))) {
					temp++;
					if (temp == managerName.length()) {
						System.out.println(" ~Match Found~ ");
						Assert.assertEquals(temp, managerName.length());
						break;
					}
				} else {
					temp = 0;
				}

			//System.out.println(" ~Match Found~");
		}
		
		
		expectedDistinguishedName = "CN=" + cnField + ",OU=" + company + ",OU=USER,DC=GENPT,DC=TEST";   //for dev - ,OU=USER,DC=iga,DC=dev   for stage - ,OU=USER,DC=GENPT,DC=TEST
		System.out.println(count1++ + ". " + "Actual Distinguished Name    : " + actualdistinguishedName);
		System.out.println("    Expected Distinguished Name : " + expectedDistinguishedName);
		// Assert.assertEquals(actualdistinguishedName, expectedDistinguishedName);
		if(actualdistinguishedName.equals("") || actualdistinguishedName.equals(" ")) {
			System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");

		}
		
		else if (actualdistinguishedName.equals(expectedDistinguishedName)) {
			System.out.println(" ~Match Found~");
		}
		
		else if (!actualdistinguishedName.equalsIgnoreCase(expectedDistinguishedName)) {
			System.out.println("===== Value did not match =====");
		}

		for (int p = 0; p < adArr.length; p++) {
			

				if (expectedDataArray[11][0].equals(adArr[p][0])) {
					String userDetailValue = adArr[p][ 1];
					System.out
							.println(count1++ + ". " + "Active Directory  | " + adArr[p][0] + " : " + userDetailValue);
					count1--;
					if(userDetailValue.equals("") || userDetailValue.equals(" ")) {
						System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");
						break;
					}
					else {
					for (int k = 0; k < wdArr.length; k++) {
						

							if (expectedDataArray[11][1].equals(wdArr[k][0])) {
								count2++;
								String workDayValue = wdArr[k][ 1];
								System.out.println("   WorkDay Details  | " + wdArr[k][0] + " : " + workDayValue);

								String s3 = "";
								Pattern p1 = Pattern.compile("\\b[a-zA-Z]");
								Matcher m = p1.matcher(workDayValue);

								while (m.find()) {
									s3 = s3 + m.group();

								}
								if (userDetailValue.equals(s3)) {
									//System.out.println(s3);
									//System.out.println(workDayValue);
									System.out.println(" ~Match Found~ ");
									
								}
							}
						}
					}
					}
				}
			}
		

		for (int p = 0; p < adArr.length; p++) {
			

				if (expectedDataArray[12][0].equals(adArr[p][0])) {
					String userDetailValue = adArr[p][1];
					System.out
							.println(count1++ + ". " + "Active Directory  | " + adArr[p][0] + " : " + userDetailValue);
					count1--;

					if (userDetailValue.equals(" ") || userDetailValue.equals("")) {
						System.out.println("~~~~~~~~Value is null~~~~~~~~~");
						//expectedUserPrincipalName = " ";
					}
					
					else {
						expectedUserPrincipalName = userDetailValue + "@GENPT-LAB.NET";

					}
				}

				if (expectedDataArray[9][0].equals(adArr[p][0])) {
					count1++;
					actualUserPrincipalName = adArr[p][1];
				}
			}

		
		if (actualUserPrincipalName.equalsIgnoreCase(expectedUserPrincipalName)) {
			System.out.println("   Active Directory  | " + "User Principal Name : " + actualUserPrincipalName);
			System.out.println(" ~Match Found~ ");
		} else {
			System.out.println(" ===== Value did not match ===== ");
		}

		String country[][] = null;
		try {
			country = ReadDataFromExcel.readCountryData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int p = 0; p < adArr.length; p++) {
			

				if (expectedDataArray[13][0].equals(adArr[p][0])) {
					count1++;
					String userDetailValue = adArr[p][1];
					System.out
							.println(count1++ + ". " + "Active Directory  | " + adArr[p][0] + " : " + userDetailValue);
					count1--;
					if(userDetailValue.equals("") || userDetailValue.equals(" ")) {
						System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");
						break;
					}
					else {
					for (int k = 0; k < wdArr.length; k++) {
				

							if (expectedDataArray[13][1].equals(wdArr[k][0])) {
								count2++;
								String workDayValue = wdArr[k][1];
								System.out.println("   WorkDay Details   | " + wdArr[k][0] + " : " + workDayValue);
								for (int r = 0; r < country.length; r++) {
									for (int s = 0; s < 2; s++) {

										if (country[r][s].equalsIgnoreCase(userDetailValue)) {
											if (country[r][s + 1].equalsIgnoreCase(workDayValue)) {
												System.out.println(" ~Match Found~ ");
												break;
											} else {
												System.out.println(" ===== Value did not match ===== ");
												break;
											}
										}
									}
								}

							}
						}
					}
					}
				}
			
		
		
		return this;
	}
	
	public AdminPage validate_new_hire_with_same_name() {
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		
		List<String> list1 = new ArrayList<>();
		for (WebElement dataList1 : detailsDataList1) {
			list1.add(dataList1.getText());
			scroll_to_element(dataList1);
		}
		// System.out.println(list1.get(0));
		List<String> list2 = new ArrayList<>();
		for (WebElement dataList2 : detailsDataList2) {
			list2.add(dataList2.getText());
			scroll_to_element(dataList2);
		}
		String[][] arr = new String[list1.size()][2];
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j <= 1; j++) {
				if (j == 0) {
					arr[i][j] = list1.get(i);
					// System.out.println(arr[i][j]);
				} else {
					arr[i][j] = list2.get(i);
					// System.out.println(arr[i][j]);
				}
			}
		}

		
		userDetailsMenuButtons.get(2).click();
		wait_for_disappearance_of_modal();
		//userDetailsMenuElements.get(0).click();
		//accountsTabLink.get(2).click();
		click_on_active_directory_link();
		wait_for_disappearance_of_modal();
		List<String> adlist1 = new ArrayList<>();
		for (WebElement dataList1 : accountsADColumn1DataList) {
			adlist1.add(dataList1.getText());
			scroll_to_element(dataList1);
		}
		// System.out.println(list1.get(0));
		List<String> adlist2 = new ArrayList<>();
		for (WebElement dataList2 : accountsADColumn2DataList) {
			adlist2.add(dataList2.getText());
			scroll_to_element(dataList2);
		}
		String[][] adArr = new String[adlist1.size()][2];
		for (int i = 0; i < adlist1.size(); i++) {
			for (int j = 0; j <= 1; j++) {
				if (j == 0) {
					adArr[i][j] = adlist1.get(i);

				} else {
					adArr[i][j] = adlist2.get(i);

				}
			}
		}
		
		int count1 = 0;
		
		for (int k = 0; k < arr.length; k++) {
			for (int l = 0; l < 2; l++) {

				if (arr[k][l].equals("DN") || arr[k][l].equals("distinguishedName")) {
					actualdistinguishedName = arr[k][l + 1];
				}
			}
		}
		
		for (int k = 0; k < adArr.length; k++) {
			for (int l = 0; l < 2; l++) {

				if (adArr[k][l].equals("company")) {
					company = adArr[k][l + 1];
				}

				if (adArr[k][l].equals("cn")) {
					cnField = adArr[k][l + 1];
				}

				if (adArr[k][l].equals("distinguishedName") || adArr[k][l].equals("DN")) {
					actualdistinguishedName = adArr[k][l + 1];
				}

				if (adArr[k][l].equals("Account ID")) {
					actualAccountID = adArr[k][l + 1];
				}

			}
		}
		
		expectedDistinguishedName = "CN=" + cnField + ",OU=" + company + ",OU=USER,DC=GENPT,DC=TEST";   //for dev - ,OU=USER,DC=iga,DC=dev   for stage - ,OU=USER,DC=GENPT,DC=TEST
		System.out.println(++count1 + ". " + "Actual Distinguished Name    : " + actualdistinguishedName);
		System.out.println("   Expected Distinguished Name : " + expectedDistinguishedName);
		// Assert.assertEquals(actualdistinguishedName, expectedDistinguishedName);
		if(actualdistinguishedName.equals("") || actualdistinguishedName.equals(" ")) {
			System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");

		}
		
		else if (actualdistinguishedName.equals(expectedDistinguishedName)) {
			System.out.println(" ~Match Found~");
		}
		
		else if (!actualdistinguishedName.equalsIgnoreCase(expectedDistinguishedName)) {
			System.out.println("===== Value did not match =====");
		}
		
		expectedAccountID = "CN=" + cnField + ",OU=" + company + ",OU=USER,DC=GENPT,DC=TEST";   //for dev - ,OU=USER,DC=iga,DC=dev   for stage - ,OU=USER,DC=GENPT,DC=TEST
		System.out.println(++count1 + ". " + "Actual Account ID   : " + actualAccountID);
		System.out.println("   Expected Account ID : " + expectedAccountID);	
		// Assert.assertEquals(actualdistinguishedName, expectedDistinguishedName);
		if(actualAccountID.equals("") || actualAccountID.equals(" ")) {
			System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");

		}
		
		else if (actualAccountID.equals(expectedAccountID)) {
			System.out.println(" ~Match Found~");
		}
		
		else if (!actualAccountID.equalsIgnoreCase(expectedAccountID)) {
			System.out.println("===== Value did not match =====");
		}
		
		actualCNField = cnField;
		expectedCNField = detailsDisplayName.getText();  // userDetailsIdentityName
		
		if(actualCNField.matches(".*\\d")) {
			System.out.println("New Actual cn : " + actualCNField);
			System.out.println("New Expected cn : " + expectedCNField);
			System.out.println(" ~ New Match Found~ ");
		}else {
			//Assert.assertNotEquals(expectedCNField, actualCNField);
			System.out.println(" ~ User has same cn ~ ");
		}
		
		int temp = 0;
		for (int i = 0; i < actualCNField.length(); i++) {
			if ((actualCNField.charAt(i)) == (expectedCNField.charAt(temp))) {
				temp++;
				if (temp == expectedCNField.length()) {
					System.out.println(++count1 + ". " + "Actual cn : " + actualCNField);
					System.out.println("   Expected cn : " + expectedCNField);	
					System.out.println(" ~Match Found~ ");
					Assert.assertEquals(temp, expectedCNField.length());
					break;
				}
//		     else{
//		        	System.out.println("No match found.");
//		     }
			} else {
				temp = 0;
			}

		}
		return this;
	}
	
	public AdminPage validate_emp_with_same_name_different_BU() {
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		
		List<String> list1 = new ArrayList<>();
		for (WebElement dataList1 : detailsDataList1) {
			list1.add(dataList1.getText());
			scroll_to_element(dataList1);
		}
		// System.out.println(list1.get(0));
		List<String> list2 = new ArrayList<>();
		for (WebElement dataList2 : detailsDataList2) {
			list2.add(dataList2.getText());
			scroll_to_element(dataList2);
		}
		String[][] arr = new String[list1.size()][2];
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j <= 1; j++) {
				if (j == 0) {
					arr[i][j] = list1.get(i);
					// System.out.println(arr[i][j]);
				} else {
					arr[i][j] = list2.get(i);
					// System.out.println(arr[i][j]);
				}
			}
		}

		
		userDetailsMenuButtons.get(2).click();
		wait_for_disappearance_of_modal();
		//userDetailsMenuElements.get(0).click();
		//accountsTabLink.get(2).click();
		click_on_active_directory_link();
		wait_for_disappearance_of_modal();
		List<String> adlist1 = new ArrayList<>();
		for (WebElement dataList1 : accountsADColumn1DataList) {
			adlist1.add(dataList1.getText());
			scroll_to_element(dataList1);
		}
		// System.out.println(list1.get(0));
		List<String> adlist2 = new ArrayList<>();
		for (WebElement dataList2 : accountsADColumn2DataList) {
			adlist2.add(dataList2.getText());
			scroll_to_element(dataList2);
		}
		String[][] adArr = new String[adlist1.size()][2];
		for (int i = 0; i < adlist1.size(); i++) {
			for (int j = 0; j <= 1; j++) {
				if (j == 0) {
					adArr[i][j] = adlist1.get(i);

				} else {
					adArr[i][j] = adlist2.get(i);

				}
			}
		}
		
		int count1 = 0;
		
		for (int k = 0; k < arr.length; k++) {
			for (int l = 0; l < 2; l++) {

				if (arr[k][l].equals("DN") || arr[k][l].equals("distinguishedName")) {
					actualdistinguishedName = arr[k][l + 1];
				}
			}
		}
		
		for (int k = 0; k < adArr.length; k++) {
			for (int l = 0; l < 2; l++) {

				if (adArr[k][l].equals("company")) {
					company = adArr[k][l + 1];
				}

				if (adArr[k][l].equals("cn")) {
					cnField = adArr[k][l + 1];
				}

				if (adArr[k][l].equals("distinguishedName") || adArr[k][l].equals("DN")) {
					actualdistinguishedName = adArr[k][l + 1];
				}

				if (adArr[k][l].equals("Account ID")) {
					actualAccountID = adArr[k][l + 1];
				}

			}
		}
		
		expectedDistinguishedName = "CN=" + cnField + ",OU=" + company + ",OU=USER,DC=GENPT,DC=TEST";   //for dev - ,OU=USER,DC=iga,DC=dev   for stage - ,OU=USER,DC=GENPT,DC=TEST
		System.out.println(++count1 + ". " + "Actual Distinguished Name    : " + actualdistinguishedName);
		System.out.println("   Expected Distinguished Name : " + expectedDistinguishedName);
		// Assert.assertEquals(actualdistinguishedName, expectedDistinguishedName);
		if(actualdistinguishedName.equals("") || actualdistinguishedName.equals(" ")) {
			System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");

		}
		
		else if (actualdistinguishedName.equals(expectedDistinguishedName)) {
			System.out.println(" ~Match Found~");
		}
		
		else if (!actualdistinguishedName.equalsIgnoreCase(expectedDistinguishedName)) {
			System.out.println("===== Value did not match =====");
		}
		
		expectedAccountID = "CN=" + cnField + ",OU=" + company + ",OU=USER,DC=GENPT,DC=TEST";   //for dev - ,OU=USER,DC=iga,DC=dev   for stage - ,OU=USER,DC=GENPT,DC=TEST
		System.out.println(++count1 + ". " + "Actual Account ID   : " + actualAccountID);
		System.out.println("   Expected Account ID : " + expectedAccountID);	
		// Assert.assertEquals(actualdistinguishedName, expectedDistinguishedName);
		if(actualAccountID.equals("") || actualAccountID.equals(" ")) {
			System.out.print("~~~~~~~~Value is null~~~~~~~~~\n");

		}
		
		else if (actualAccountID.equals(expectedAccountID)) {
			System.out.println(" ~Match Found~");
		}
		
		else if (!actualAccountID.equalsIgnoreCase(expectedAccountID)) {
			System.out.println("===== Value did not match =====");
		}
		
		actualCNField = cnField;
		expectedCNField = detailsDisplayName.getText();  // userDetailsIdentityName
		
		Assert.assertEquals(expectedCNField,actualCNField);
		System.out.println("Actual cn : " + actualCNField);
		System.out.println("Expected cn : " + expectedCNField);	
		System.out.println(" ~Match Found~ ");

		
		return this;
	}

	public AdminPage validate_termination_date_in_AD() {
		wait_for_disappearance_of_modal();
		wait_for_appearance_of_xpath(AD_TERMINATION_DATE,15);
		String terminationDate=activeDirTerminationDate.get(1).getText();
		
		try {
		Assert.assertTrue(terminationDate.matches("Termination Date:\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$"));
		
			System.out.println("Termination Date " + terminationDate);
			System.out.println(" ~ New Match Found~ ");
		}catch (Exception e) {
			//Assert.assertNotEquals(expectedCNField, actualCNField);
			click_on_user_data_back_btn();
		}
		return this;
	}
	
	public AdminPage validate_upn_under_workday_and_ad() {
		//Click on accounts tab

		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_Of_xpath(DETAILS_LOADING, 30);
		//Check email in details tab
		wait_for_appearance_of_xpath(USER_DETAIL_EMAIL,30);
		String userDetailsEmail=userDetailEmail.getText();		
		userDetailsMenuButtons.get(2).click();
		wait_for_disappearance_of_modal();
		//Click on workday link
		click_on_workday_link();
		//accountsTabLink.get(0).click();
		wait_for_disappearance_of_modal();
		wait_for_appearance_of_xpath(WD_UPN_ATRRIBUTE,30);
		String workdayEmail=workdayEmailAttribute.get(1).getText();
		String workdayUPN=workdayUPNAttribute.get(1).getText();
		userDetailsBackBtn.get(0).click();
		click_on_active_directory_link();
		wait_for_disappearance_of_modal();
		wait_for_appearance_of_xpath(AD_UPN_ATRRIBUTE,30);
		String activeDirUPN=activeDirUPNAttribute.get(0).getText();
		
		try {
			Assert.assertEquals(userDetailsEmail,workdayEmail);
			
				System.out.println("Email is " + workdayEmail);
				System.out.println(" ~ Email write back is correct~ ");
			}catch (Exception e) {
				//Assert.assertNotEquals(expectedCNField, actualCNField);
				click_on_user_data_back_btn();
			}
		
		try {
			Assert.assertEquals(workdayUPN,activeDirUPN);
			
				System.out.println("UPN is " + activeDirUPN);
				System.out.println(" ~ UPN write back is correct~ ");
			}catch (Exception e) {
				//Assert.assertNotEquals(expectedCNField, actualCNField);
				click_on_user_data_back_btn();
			}	
		
		return this;
	}
	
	
	public AdminPage validate_upn_under_workday() {
		//Click on accounts tab

		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_Of_xpath(DETAILS_LOADING, 30);

		userDetailsMenuButtons.get(2).click();
		wait_for_disappearance_of_modal();
		//Click on workday link
		click_on_workday_link();
		//accountsTabLink.get(0).click();
		wait_for_disappearance_of_modal();
		wait_for_appearance_of_xpath(WD_UPN_ATRRIBUTE,30);
		String expectedUPN="--";
		String workdayUPN=workdayUPNAttribute.get(1).getText();
		
		try {
			Assert.assertEquals(expectedUPN,workdayUPN);
			
				System.out.println("Workday UPN is " + workdayUPN);
				System.out.println(" ~ UPN not write backed to workday ~ ");
			}catch (Exception e) {
				//Assert.assertNotEquals(expectedCNField, actualCNField);
				click_on_user_data_back_btn();
			}	
		
		return this;
	}
	
	
	public AdminPage validate_rehire_is_one_under_details() {
		wait_for_disappearance_of_modal();
		wait_for_disappearance_of_modal();
		wait_for_disappearance_Of_xpath(DETAILS_LOADING, 30);
		//Check email in details tab
		wait_for_appearance_of_xpath(USER_DETAIL_REHIRE,30);
		String expectedRehire="1";
		String actualRehire=userDetailRehire.getText();
		try {
			Assert.assertEquals(expectedRehire,actualRehire);
			
				System.out.println("User Details Rehire " + actualRehire);
				System.out.println(" ~ Rehire equals to 1 under user details ~ ");
			}catch (Exception e) {
				//Assert.assertNotEquals(expectedCNField, actualCNField);
				click_on_user_data_close_btn();
			}	
		
		
		return this;
		
	}
}
