package com.amazon.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadingXML {
	
	public static ArrayList<Object[]> getDataFromExcel() throws EncryptedDocumentException, InvalidFormatException, IOException{
		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		Workbook EmpDataBook = null;
		DataFormatter formatter = new DataFormatter();
		File file =    new File("C:/Users/Santosh/workspace/AmazonOreder/src/main/java/com/amazon/qa/util/OrderDetails.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		EmpDataBook = WorkbookFactory.create(inputStream);
		Sheet EmpDataSheet = EmpDataBook.getSheet("Sheet1");
		int Last_row = EmpDataSheet.getLastRowNum();
		int Last_column = EmpDataSheet.getRow(0).getLastCellNum();
		Object ob[] = new Object[Last_column];

		for(int i=1;i<=Last_row;i++){
			for(int j=0;j<ob.length;j++){
				ob[j] = formatter.formatCellValue(EmpDataSheet.getRow(i).getCell(j));
			}
			myData.add(ob);
		}
		return myData;
	}
}

