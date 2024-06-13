package com.excel.read;
import java.io.FileInputStream;
import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel {
	
	public static int i;
	public static int j;
	
	
	public static void main (String [] args) throws Exception{
		
		readData();
		readCountryData();
	}
	
	public static int readData() throws Exception
	{
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\resources\\UserIdTestDataGPC.xlsx");
		//Creating a workbook
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);
		int numericValue = (int) sheet.getRow(0).getCell(0).getNumericCellValue();
		return numericValue;
		
	}
	
	public static String[][] readCountryData() throws Exception
	{
		XSSFWorkbook workbook = new XSSFWorkbook(System.getProperty("user.dir")+"\\resources\\CountryList.xlsx");
	    XSSFSheet workSheet = workbook.getSheetAt(0);

	    int noOfRows = workSheet.getLastRowNum() + 1;
	    int noOfColumns = workSheet.getRow(0).getLastCellNum();
	    String[][] expectedCountryList = new String[noOfRows][noOfColumns];

	    for ( i = workSheet.getFirstRowNum(); i < workSheet.getLastRowNum() + 1; i++) {
	        Row row = workSheet.getRow(i);
	        for (j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
	            Cell cell = row.getCell(j);
	            expectedCountryList[i][j] = cell.getStringCellValue();
	            //System.out.println(expectedCountryList[i][j]);
	        }
	    }
		return expectedCountryList;
	}
	
	
}
