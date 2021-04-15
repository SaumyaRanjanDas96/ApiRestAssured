package com.datadrivedntesting.restapi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class XlUtil {
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static Workbook wb;
	public static Sheet ws;
	public static Row row ;
	public static Cell cell;
	
	public static int getRowCount(String xlfile, String xlsheet) throws EncryptedDocumentException, IOException{
		fi=new FileInputStream(xlfile);
		wb=WorkbookFactory.create(fi);
		 ws = wb.getSheet(xlsheet);
		 int rowcount = ws.getLastRowNum();
		 wb.close();
		 fi.close();
		 return rowcount;
		 
		
	}
public static	int getCellCount(String xlfile, String xlsheet, int rownum) throws EncryptedDocumentException, IOException{
		fi=new FileInputStream(xlfile);
		wb=WorkbookFactory.create(fi);
		 ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		 int cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
		
		
	}
public static String getCellData(String xlfile, String xlsheet, int rownum, int colnum) throws EncryptedDocumentException, IOException{
	fi=new FileInputStream(xlfile);
	wb=WorkbookFactory.create(fi);
      ws = wb.getSheet(xlsheet);
	row = ws.getRow(rownum);
	cell = row.getCell(colnum);
	String data = cell.getStringCellValue();
	wb.close();
	fi.close();
	return data;
}
void setCellValue(String xlfile, String xlsheet, int rownum, int colnum) throws EncryptedDocumentException, IOException{
	fi=new FileInputStream(xlfile);
	wb=WorkbookFactory.create(fi);
	 ws = wb.getSheet(xlsheet);
	row = ws.getRow(rownum);
	 cell = row.getCell(colnum);
	 String data = cell.getStringCellValue();
	fo=new FileOutputStream(xlfile);
	wb.write(fo);
	wb.close();
	fi.close();
	fo.close();
	
	
	
	
}
}
