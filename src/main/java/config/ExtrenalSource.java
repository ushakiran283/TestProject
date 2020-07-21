package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExtrenalSource {
	
	public String getUsernamePwd() throws IOException{
		File excelfile = new File("TestData.xlsx");
		FileInputStream fis = new FileInputStream(excelfile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Credentials");
        XSSFRow row = sheet.getRow(1);
        XSSFCell cell = row.getCell(0);
        String pwd = cell.getStringCellValue();
        System.out.println("Value from the Excel sheet :"+ pwd);
		fis.close();
        return  pwd;
	}

}
