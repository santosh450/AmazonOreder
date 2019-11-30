package com.amazon.qa.util;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteXML {
	
	public static void WriteDataToExcel(String sheetName,String[] dataToWrite) throws Exception {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");  
	    Date date = new Date();  
	    String s2 = formatter.format(date);  
		String s = "C://Users//Santosh//workspace//AmazonOreder//src//main//java//com//amazon//qa//util//DeliveryDetails"+s2+".xlsx";
		FileOutputStream fileOut = new FileOutputStream(s);
		Workbook wb = new XSSFWorkbook();
		Sheet sheet1 = wb.createSheet(sheetName);
		for(int i=0;i<dataToWrite.length;i++){
		sheet1.createRow(i).createCell(0).setCellValue(dataToWrite[i]);
		}
		wb.write(fileOut);
		fileOut.close();
	}
}

