package com.dataprovider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class AdminPageDataProvider {

	
	public static Object[][] readData(int sheetno) throws Exception {
		Object[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(
					System.getProperty("user.dir") + "\\resources\\UserIdTestDataNAPACAN.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheetAt(sheetno);
			int totalNoOfRows = sheet.getLastRowNum() - sheet.getFirstRowNum();
			int totalNoOfCol = sheet.getRow(totalNoOfRows).getLastCellNum();
			//System.out.println(totalNoOfRows);
			//System.out.println(totalNoOfCol);
		//	System.out.println("First Row Num" + sheet.getFirstRowNum());
			//System.out.println("Last Row Num" + sheet.getLastRowNum());
			// int totalNoOfCols = sheet.getFirstRowNum();
			// int totalNoOfRows = sh.getRows();

			arrayExcelData = new Object[totalNoOfRows + 1][totalNoOfCol];
			// DataFormatter formatter = new DataFormatter();
			for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
					//System.out.println("First Cell Num" + row.getFirstCellNum());
					//System.out.println("last Cell Num" + row.getLastCellNum());
					Cell cell = row.getCell(j);
					//System.out.println("Cell data" + row.getCell(j));
					// arrayExcelData[i][j] = cell.setCellType(Cell CELL_TYPE_NUMERIC);
					arrayExcelData[i][j] = (int) cell.getNumericCellValue();
					//System.out.println("Excel Value:" + arrayExcelData[i][j]);
					// System.out.println(expectedCountryList[i][j]);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}

		return arrayExcelData;
	}
	
	@DataProvider
	public Object[][] validate_admin_page_active_directory_data_to_workday_data_TestData() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	@DataProvider
	public Object[][] validate_admin_page_hire_date_is_within_14_days_TestData() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	@DataProvider
	public Object[][] validate_admin_page_offer_rescinded_no_show_TestData() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	@DataProvider
	public Object[][] validate_admin_page_start_date_changed_from_within_14_days_to_today() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	@DataProvider
	public Object[][] validate_admin_page_workday_writeback_of_email_upn() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	
	
	@DataProvider
	public Object[][] validate_admin_page_rehire_start_in_past() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	@DataProvider
	public Object[][] validate_admin_page_rehire_start_in_14_days_or_less() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	
	@DataProvider
	public Object[][] validate_admin_page_rehire_date_is_within_14_days() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	
	@DataProvider
	public Object[][] validate_admin_page_leaver_last_day_of_work_within_90_days() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(6);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	@DataProvider
	public Object[][] validate_admin_page_leaver_last_day_of_work_more_than_90_days() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(7);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	@DataProvider
	public Object[][] validate_admin_page_user_lifecycle_state() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(8);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	@DataProvider
	public Object[][] validate_admin_page_new_hire_started_in_past() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(9);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	@DataProvider
	public Object[][] validate_admin_page_rehire_started_after_90_days_in_past() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	@DataProvider
	public Object[][] validate_admin_page_leaver_last_day_of_past_for_an_elevated_privilleged_account() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(11);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	
	@DataProvider
	public Object[][] validate_admin_page_profile_management_attribute_mapping() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	@DataProvider
	public Object[][] validate_admin_page_profile_management_employee_contractor_with_same_name() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(12);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	@DataProvider
	public Object[][] validate_admin_page_profile_management_employee_contractor_with_2_existing_user_having_same_name() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(13);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	
	
	@DataProvider
	public Object[][] validate_admin_page_profile_management_employee_contractor_with_same_name_from_different_BU() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(14);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	@DataProvider
	public Object[][] validate_admin_page_workday_writeback_for_motion_user() {
		Object[][] arrayObject = null;
		try {
			arrayObject = readData(15);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	
}
