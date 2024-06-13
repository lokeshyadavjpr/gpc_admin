package com.home;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.config.SeleniumTest;
import com.util.ScreenRecorderUtil;

public class FunctionalHomePage extends SeleniumTest {

	private HomePage homePage;
	private long startTime;
	private long stopTime;
	private long elapsedTime;
	public String pageName = "Home Module";
	
	@BeforeClass(alwaysRun=true)
	public void beforeClass() throws Exception{
		ScreenRecorderUtil.startRecord("HomePage");
		
		System.out.println("========== Inside Home Page ==========");
		homePage = PageFactory.initElements(driver, HomePage.class);
		startTime = System.currentTimeMillis();
		homePage
			.validate_greeting_banner()
			.validate_admin_menu_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}	
		
	@Test(priority = 1, groups={"regression","preProdSanity","prodSanity"}, description = "Validate Admin Menu button on Home Page")
	public void validate_admin_btn(){
		System.out.println("Inside_validate_admin_btn");
		startTime = System.currentTimeMillis();
		homePage
			.validate_admin_menu_btn();
		
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);
	}
	
	@AfterClass(alwaysRun=true)
	public void afterClass() throws Exception{
		ScreenRecorderUtil.stopRecord();
	}

}