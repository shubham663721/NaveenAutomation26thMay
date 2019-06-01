package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import testBase.TestBase;

public class ExcelUtility extends TestBase{
	//this method is used to read data from Excel, here as per SheetName it reads data from Excel for Particular Sheet
	public static Object[][] getTestData(String sheetName) throws IOException {
	File file = new File(System.getProperty("user.dir")+ "\\src\\main\\resources\\InputData.xlsx");
	
	FileInputStream is = new FileInputStream(file);
	
	XSSFWorkbook wb = new XSSFWorkbook(is);
	
	XSSFSheet sheet = wb.getSheet(sheetName);
	
	Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	
	for(int i=0; i<sheet.getLastRowNum();i++) {
		
		for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
			
			data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
		}
	}
	wb.close();
	
	return data;
	}
	
}
