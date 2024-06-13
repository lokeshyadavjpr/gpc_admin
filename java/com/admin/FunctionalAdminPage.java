package com.admin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.config.SeleniumTest;
import com.dataprovider.AdminPageDataProvider;
import com.util.ScreenRecorderUtil;

public class FunctionalAdminPage extends SeleniumTest{

	private AdminPage adminPage;
	private long startTime;
	private long stopTime;
	private long elapsedTime;
	public String pageName = "Admin Module";
	
	@BeforeClass(alwaysRun=true)
	public void beforeClass() throws Exception{
		ScreenRecorderUtil.startRecord("AdminPage");
		
		System.out.println("========== Inside Admin Page ==========");
		adminPage = PageFactory.initElements(driver, AdminPage.class);
		startTime = System.currentTimeMillis();
		adminPage
		
			//.validate_authorization_code()
			.validate_greeting_banner()
			.validate_admin_menu_btn()
			.click_on_admin_menu_btn()
			//.validate_admin_strong_authentication()
			.validate_identities_dropdown_menu()
			.validate_identities_dropdown_list();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}	
		
	@Test(priority = 1, groups={"test","smoke","preProdSanity","prodSanity","regression1"}, description = "Validate Admin Menu button on Home Page")
	public void validate_admin_menu_btn(){
		System.out.println("Inside_validate_admin_menu_btn");
		startTime = System.currentTimeMillis();
		
		adminPage
			.validate_admin_menu_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(enabled = false, priority = 2, groups={"smoke","preProdSanity","prodSanity","regression1"}, description = "Validate Admin Page Identities menu")
	public void validate_admin_page_identities_menu(){
		System.out.println("Inside_validate_admin_page_identities_menu");
		startTime = System.currentTimeMillis();
		
		adminPage
			.validate_identities_dropdown_menu()
			.validate_identities_dropdown_list();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(priority = 3, groups={"smoke","preProdSanity","prodSanity","regression1"}, description = "Validate Admin Page Identity List Page")
	public void validate_admin_page_identity_list_page(){
		System.out.println("Inside_validate_admin_page_identity_list_page");
		startTime = System.currentTimeMillis();
		
		adminPage
			.validate_identity_list_page_elements();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(priority = 4, groups={"smoke","preProdSanity","prodSanity","regression1"}, description = "Validate Admin Page Columns Header")
	public void validate_admin_page_columns_header_names(){
		System.out.println("Inside_validate_admin_page_columns_header_names");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_identity_list_page_elements()
			.validate_column_list()
			.validate_full_columns_name()
			.validate_reset_columns_name()
			.validate_reset_columns_header_name();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(priority = 5, groups={"smoke","preProdSanity","prodSanity","regression1"}, description = "Validate Admin Page Identity List page toggle btn")
	public void validate_admin_page_identity_list_toggle_btn(){
		System.out.println("Inside_validate_admin_page_identity_list_toggle_btn");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_toggle_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(priority = 6, groups={"smoke","preProdSanity","prodSanity","regression1"}, description = "Validate User details page Menu buttons")
	public void validate_admin_page_identity_list_user_page_menu_buttons(){
		System.out.println("Inside_validate_admin_page_identity_list_user_page_menu_buttons");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity()
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_user_details_page_menu_button()
			.click_on_user_data_close_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	/*@Test(enabled = false, priority = 7, groups={"smoke","regression","preProdSanity","prodSanity"}, description = "Validate User details page Menu details")
	public void validate_admin_page_identity_list_user_page_menu_details(){
		System.out.println("Inside_validate_admin_page_identity_list_user_page_menu_details");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity()
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_user_details_page_menu_button()
			.validate_accounts_menu_user_details_page()
			.validate_application_menu_user_details_page()
			.validate_roles_menu_user_details_page()
			.validate_activity_menu_user_details_page()
			.validate_work_reassignment_menu_user_details_page()
			.click_on_user_data_close_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	*/
	@Test(priority = 8, groups={"smoke","preProdSanity","prodSanity","regression1"}, description = "Validate Admin Page Search and Verify an identity")
	public void validate_admin_page_search_identity_of_user_one(){
		System.out.println("Inside_validate_admin_page_search_identity_of_user_one");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity()
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_display_name_to_user_name()
			.click_on_user_data_close_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(priority = 9, groups={"smoke","preProdSanity","prodSanity","regression1"}, description = "Validate Admin Page Global IdentityNow Username to userid")
	public void validate_admin_page_global_username_to_userid(){
		System.out.println("Inside_validate_admin_page_global_username_to_userid");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity()
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_global_username_to_userid()
			.click_on_user_data_close_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(priority = 10, dataProvider = "validate_admin_page_user_lifecycle_state",dataProviderClass = AdminPageDataProvider.class,  groups={"smoke","preProdSanity","prodSanity","regression1"}, description = "Validate Admin Page Lifecycle State")
	public void validate_admin_page_user_lifecycle_state(int user_id){
		System.out.println("Inside_validate_admin_page_user_lifecycle_state");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity(user_id)
			.validate_identity_result_name()
			.click_on_user_data_name()
			//.validate_attribute_multiple_view_toggle_btn()
			.validate_userdetails_lifecycle_state()
			.click_on_user_data_close_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(priority = 11, groups={"preProdSanity","prodSanity","regression1"}, description = "Validate User details validation")
	public void validate_admin_page_identity_list_user_details_data(){
		System.out.println("Inside_validate_admin_page_identity_list_user_details_data");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity()
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_details_data()
			.click_on_user_data_close_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(priority = 12, groups={"preProdSanity","prodSanity","regression1"}, description = "Validate User details --> Accounts tab --> Active Directory Data")
	public void validate_admin_page_identity_list_active_directory_data(){
		System.out.println("Inside_validate_admin_page_identity_list_active_directory_data");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity()
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_accounts_tab_active_directory_data()
			.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(priority = 13, groups={"preProdSanity","prodSanity","regression2"}, description = "Validate User details --> Accounts tab --> Workday Data")
	public void validate_admin_page_identity_list_workday_data(){
		System.out.println("Inside_validate_admin_page_identity_list_workday_data");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity()
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_accounts_tab_workday_data()
			.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	/*
	@Test(priority = 14, groups={"preProdSanity","prodSanity","regression2"}, description = "Validate Dev GPC Global Username")
	public void validate_admin_page_user_details_gpc_username(){
		System.out.println("Inside_validate_admin_page_user_details_gpc_username");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity()
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_gpc_global_username()
			.click_on_user_data_close_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}*/
	  
	@Test(priority = 15, groups={"preProdSanity","prodSanity","regression2"}, description = "Validate compare User details data to workday data")
	public void validate_admin_page_user_details_data_to_workday_data(){
		System.out.println("Inside_validate_admin_page_user_details_data_to_workday_data");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity()
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_details_data_to_workday_data()
			.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(dataProvider = "validate_admin_page_active_directory_data_to_workday_data_TestData",dataProviderClass = AdminPageDataProvider.class, priority = 16, groups={"preProdSanity","prodSanity","regression2"}, description = "Validate compare Active Directory data to workday data")
	public void validate_admin_page_active_directory_data_to_workday_data(int user_id){
		System.out.println("Inside_validate_admin_page_active_directory_data_to_workday_data");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity(user_id)
			.validate_identity_result_name()
			.click_on_user_data_name()
			//.validate_attribute_multiple_view_toggle_btn()
			.validate_active_directory_data_to_workday_data()
			.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
		
	@Test(priority = 17, dataProvider = "validate_admin_page_hire_date_is_within_14_days_TestData",dataProviderClass = AdminPageDataProvider.class, groups={"preProdSanity","prodSanity","regression2"}, description = "Validate Hire Date is within 14 Days")
	public void validate_admin_page_hire_date_is_within_14_days(int user_id){
		System.out.println("Inside_validate_admin_page_hire_date_is_within_14_days");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity(user_id)
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_details_data_to_workday_data()
			.click_on_user_data_back_btn()
			.click_on_user_data_name()
			//.validate_attribute_multiple_view_toggle_btn()
			.validate_active_directory_data_to_workday_data()
			.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(priority = 18, groups={"preProdSanity","prodSanity","regression2"}, description = "Validate admin page hire date is more than 14 days in future")
	public void validate_admin_page_hire_date_is_more_than_14_days_in_future(){
		System.out.println("Inside_admin_page_hire_date_is_more_than_14_days_in_future");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_user_hire_date_more_than_14()  	// validate_search_a_prehire_identity()
			.validate_user_should_not_searchable()				// validate_prehire_identity_result()
			.clear_identity_search_bar_data();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	/*@Test(priority = 19, dataProvider = "validate_admin_page_offer_rescinded_no_show_TestData",dataProviderClass = AdminPageDataProvider.class, groups={"regression","preProdSanity","prodSanity","regression2"}, description = "Validate compare Active Directory data to workday data")
	public void validate_admin_page_offer_rescinded_no_show(int user_id){
		System.out.println("Inside_validate_admin_page_active_directory_data_to_workday_data");
		startTime = System.currentTimeMillis();
		
		adminPage

		.validate_search_an_identity(user_id)
		.validate_identity_result_name()
		.click_on_user_data_name()
		.validate_attribute_multiple_view_toggle_btn()
		.validate_user_details_rescinded_lifecycle_state()
		.click_on_user_data_close_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}*/
	
	@Test(priority = 20, dataProvider = "validate_admin_page_start_date_changed_from_within_14_days_to_today",dataProviderClass = AdminPageDataProvider.class, groups={"preProdSanity","prodSanity","regression2"}, description = "Validate Start Date changed from within 14 days to today")
	public void validate_admin_page_start_date_changed_from_within_14_days_to_today(int user_id){
		System.out.println("Inside_validate_admin_page_start_date_changed_from_within_14_days_to_today");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity(user_id)
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_accounts_tab_active_directory_data()
			.click_on_user_data_back_btn()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_accounts_tab_workday_data()
			.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	//dataProvider = "readData", int userid
	@Test(priority = 21, groups={"preProdSanity","prodSanity","regression2"}, description = "Validate new hire with same name")
	public void validate_admin_page_new_hire_with_same_name(){
		System.out.println("Inside_validate_admin_page_new_hire_with_same_name");
		startTime = System.currentTimeMillis();
		adminPage

			.validate_search_identity_with_same_name_hired()
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_new_hire_with_same_name()	
			.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
		
	@Test(priority = 22, groups={"preProdSanity","prodSanity","regression2"}, description = "Validate ( Leaver ) Urgent Termination")
	public void validate_admin_page_leaver_urgent_termination(){
		System.out.println("Inside_validate_admin_page_leaver_urgent_termination");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_leaver_urgent_termination()
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_user_details_active_lifecycle_state()
			.change_lifecycle_state_to_inactive()
			.validate_user_details_inactive_lifecycle_state()
			.click_on_accounts_tab()
			.validate_active_directory_is_disabled1()
//			//.validate_ad_disabled_for_urgent_termination()
//			.click_on_details_tab()
//			.change_lifecycle_state_to_active()
			.click_on_user_data_close_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(priority = 23, dataProvider = "validate_admin_page_leaver_last_day_of_work_within_90_days" ,dataProviderClass = AdminPageDataProvider.class, groups={"preProdSanity","prodSanity","regression2"}, description = "Validate ( Leaver ) Last day of work is within 90 Days ( = Yesterday)")
	public void validate_admin_page_leaver_last_day_of_work_within_90_days(int user_id){
		System.out.println("Inside_validate_admin_page_leaver_last_day_of_work_within_90_days");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity(user_id)  //any inactive user
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_user_details_inactive_lifecycle_state()
			.click_on_accounts_tab()
			.validate_active_directory_is_disabled1()
//			.validate_active_directory_is_disabled()
//			.validate_ad_is_deleted()
//			.click_on_details_tab()
//			.change_lifecycle_state_to_active()
			.click_on_user_data_close_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(priority = 24, dataProvider = "validate_admin_page_leaver_last_day_of_work_more_than_90_days" ,dataProviderClass = AdminPageDataProvider.class, groups={"preProdSanity","prodSanity","regression2"}, description = "Validate ( Leaver ) Last day of work is more than 90 Days ( = Yesterday)")
	public void validate_admin_page_leaver_last_day_of_work_more_than_90_days(int user_id){
		System.out.println("Inside_validate_admin_page_leaver_last_day_of_work_more_than_90_days");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity(user_id) //------AD account shouldn't be there --- Deleted state
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_user_details_deleted_lifecycle_state()
			.click_on_accounts_tab()
			.change_lifecycle_state_to_inactive()
			.change_lifecycle_state_to_deleted()
			.click_on_user_data_close_btn()
			.click_on_user_data_name()
			.click_on_accounts_tab()
			.validate_ad_is_deleted()
			.click_on_user_data_close_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(priority = 25, groups={"preProdSanity","prodSanity","regression2"}, description = "Validate ( Leaver ) Last day of work is today")
	public void validate_admin_page_leaver_last_day_of_work_today(){
		System.out.println("Inside_validate_admin_page_leaver_last_day_of_work_today");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_leaver_last_day_of_work_today()
			.validate_identity_result_name()
			.click_on_user_data_name()
			//.validate_attribute_multiple_view_toggle_btn()
			.click_on_accounts_tab()
			.click_on_active_directory_link()
			//.validate_termination_date_in_AD()   //only for GPC
			
			//.validate_user_details_active_lifecycle_state()
			//.change_lifecycle_state_to_inactive()
			//.validate_user_details_inactive_lifecycle_state()
			//.click_on_accounts_tab()
			//.validate_active_directory_is_disabled()
//			//.validate_ad_disabled_for_urgent_termination()
//			.click_on_details_tab()
//			.change_lifecycle_state_to_active()
			//.click_on_user_data_close_btn();
			.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	
	@Test(dataProvider = "validate_admin_page_new_hire_started_in_past",dataProviderClass = AdminPageDataProvider.class, priority = 26, groups={"preProdSanity","prodSanity","regression2"}, description = "Validate New Hire Started in Past")
	public void validate_admin_page_new_hire_started_in_past(int user_id){
		System.out.println("Inside_validate_admin_page_new_hire_started_in_past");
		startTime = System.currentTimeMillis();
		
		adminPage
			.validate_search_an_identity(user_id)
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_active_directory_data_to_workday_data()
			.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	//=========================================
	
	@Test(priority = 27, dataProvider = "validate_admin_page_rehire_start_in_past",dataProviderClass = AdminPageDataProvider.class, groups={"preProdSanity","prodSanity","regression3"}, description = "Validate ( Rehire - Prehire) Start in Past")
	public void validate_admin_page_rehire_start_in_past(int user_id){
		System.out.println("Inside_validate_admin_page_rehire_start_in_past");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity(user_id)        //we can take any active rehire ID
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_user_details_rehire_lifecycle_state()
			.validate_details_data_to_workday_data()
			.click_on_user_data_back_btn()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_active_directory_data_to_workday_data()
			.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(priority = 28, dataProvider = "validate_admin_page_rehire_start_in_14_days_or_less",dataProviderClass = AdminPageDataProvider.class, groups={"preProdSanity","prodSanity","regression3"}, description = "Validate ( Rehire ) Start in 14 days or less")
	public void validate_admin_page_rehire_start_in_14_days_or_less(int user_id){
		System.out.println("Inside_validate_admin_page_rehire_start_in_14_days_or_less");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity(user_id)     //it should be prehire
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_user_details_prehire_lifecycle_state()
			.validate_details_data_to_workday_data()
			.click_on_user_data_back_btn()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_active_directory_data_to_workday_data()
			.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}

	
	@Test(dataProvider = "validate_admin_page_rehire_started_after_90_days_in_past",dataProviderClass = AdminPageDataProvider.class, priority = 29, groups={"preProdSanity","prodSanity","regression3"}, description = "Validate Rehire Started after 90 days in past")
	public void validate_admin_page_rehire_started_after_90_days_in_past(int user_id){
		System.out.println("Inside_validate_admin_page_rehire_started_in_past");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity(user_id)        //we can take any active rehire ID
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_user_details_rehire_lifecycle_state()
			.validate_rehire_is_one_under_details()
			.click_on_user_data_close_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	// 30 is duplicate
	/*@Test(priority = 30, dataProvider = "validate_admin_page_rehire_date_is_within_14_days",dataProviderClass = AdminPageDataProvider.class, groups={"preProdSanity","prodSanity","regression3"}, description = "Validate rehire Date is within 14 Days")
	public void validate_admin_page_rehire_date_is_within_14_days(int user_id){
		System.out.println("Inside_validate_admin_page_rehire_date_is_within_14_days");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity(user_id)
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_details_data_to_workday_data()
			.click_on_user_data_back_btn()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_active_directory_data_to_workday_data()
			.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}*/
	
	
	/*
	@Test(priority = 31, dataProvider = "validate_admin_page_leaver_last_day_of_past_for_an_elevated_privilleged_account" ,dataProviderClass = AdminPageDataProvider.class, groups={"preProdSanity","prodSanity","regression3"}, description = "validate admin_page leaver last day of past for an elevated privileged account")
	public void validate_admin_page_leaver_last_day_of_past_for_an_elevated_privilleged_account(int user_id){
		System.out.println("Inside_validate_admin_page_leaver_last_day_of_past_for_an_elevated_privileged_account");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity(user_id) //------AD account shouldn't be there --- Deleted state
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_user_details_deleted_lifecycle_state()
			.click_on_accounts_tab()
			.validate_ad_is_deleted()
			.click_on_user_data_close_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	*/
	// 32 is duplicate of 16
	/*
	@Test(priority = 32, dataProvider = "validate_admin_page_profile_management_attribute_mapping" ,dataProviderClass = AdminPageDataProvider.class, groups={"preProdSanity","prodSanity","regression3"}, description = "validate admin page profile management attribute mapping")
	public void validate_admin_page_profile_management_attribute_mapping(int user_id){
		System.out.println("Inside_validate_admin_page_profile_management_attribute_mapping");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity(user_id) 
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_active_directory_data_to_workday_data()
			.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(priority = 33, dataProvider = "validate_admin_page_profile_management_employee_contractor_with_same_name" ,dataProviderClass = AdminPageDataProvider.class, groups={"preProdSanity","prodSanity","regression3"}, description = "admin page profile management employee contractor with same name")
	public void validate_admin_page_profile_management_employee_contractor_with_same_name(int user_id){
		System.out.println("Inside_validate_admin_page_profile_management_employee_contractor_with_same_name");
		startTime = System.currentTimeMillis();
		
		adminPage

			.validate_search_an_identity(user_id) 
			.validate_identity_result_name()
			.click_on_user_data_name()
			.validate_attribute_multiple_view_toggle_btn()
			.validate_new_hire_with_same_name()	
			.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}*/
	
	@Test(priority = 34, dataProvider = "validate_admin_page_profile_management_employee_contractor_with_2_existing_user_having_same_name" ,dataProviderClass = AdminPageDataProvider.class, groups={"preProdSanity","prodSanity","regression3"}, description = "profile management employee contractor with 2 existing user having same name")
	public void validate_admin_page_profile_management_employee_contractor_with_2_existing_user_having_same_name(int user_id){
		System.out.println("Inside_validate_admin_page_profile_management_employee_contractor_with_2_existing_user_having_same_name");
		startTime = System.currentTimeMillis();
		
		adminPage

		.validate_search_an_identity(user_id) 
		.validate_identity_result_name()
		.click_on_user_data_name()
		.validate_attribute_multiple_view_toggle_btn()
		.validate_new_hire_with_same_name()	
		.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@Test(priority = 35, dataProvider = "validate_admin_page_profile_management_employee_contractor_with_same_name_from_different_BU" ,dataProviderClass = AdminPageDataProvider.class, groups={"preProdSanity","prodSanity","regression3"}, description = "validate admin page profile management employee contractor with same name from different BU")
	public void validate_admin_page_profile_management_employee_contractor_with_same_name_from_different_BU(int user_id){
		System.out.println("Inside_validate_admin_page_profile_management_employee_contractor_with_same_name_from_different_BU");
		startTime = System.currentTimeMillis();
		
		adminPage

		.validate_search_an_identity(user_id) 
		.validate_identity_result_name()
		.click_on_user_data_name()
		//.validate_attribute_multiple_view_toggle_btn()
		.validate_emp_with_same_name_different_BU()	
		.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	
	@Test(priority = 36, dataProvider = "validate_admin_page_workday_writeback_of_email_upn" ,dataProviderClass = AdminPageDataProvider.class, groups={"preProdSanity","prodSanity","regression3","failed"}, description = "validate admin page workday writeback of email and upn")
	public void validate_admin_page_workday_writeback_of_email_upn(int user_id){
		System.out.println("Inside_validate_admin_page_workday_writeback_of_email_upn");
		startTime = System.currentTimeMillis();
		
		adminPage

		.validate_search_an_identity(user_id) 
		.validate_identity_result_name()
		.click_on_user_data_name()
		//.validate_attribute_multiple_view_toggle_btn()
		.validate_upn_under_workday_and_ad()
		.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	
	@Test(priority = 37, dataProvider = "validate_admin_page_workday_writeback_for_motion_user" ,dataProviderClass = AdminPageDataProvider.class, groups={"preProdSanity","prodSanity","regression3","failed"}, description = "validate admin page workday writeback for motion user")
	public void validate_admin_page_workday_writeback_for_motion_user(int user_id){
		System.out.println("Inside_validate_admin_page_workday_writeback_for_motion_user");
		startTime = System.currentTimeMillis();
		
		adminPage

		.validate_search_an_identity(user_id) 
		.validate_identity_result_name()
		.click_on_user_data_name()
		//.validate_attribute_multiple_view_toggle_btn()
		.validate_upn_under_workday()  //UPN should not be write back to workday
		.click_on_user_data_back_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	
	@AfterClass(alwaysRun=true)
	public void afterClass() throws Exception{
		ScreenRecorderUtil.stopRecord();
	}
	
}
