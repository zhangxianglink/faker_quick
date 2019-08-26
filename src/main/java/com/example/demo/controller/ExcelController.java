package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.pojo.GKCode;
import com.example.demo.utils.DecideExeclUtils;

public class ExcelController {
	
	
	public static void main(String[] args) {
		File file = new File("C:/Users/cuihang/Desktop/enterprise/sc.xls");
		Workbook workbook = null;
		ArrayList<GKCode> arrayList = new ArrayList<>();
		try{
			if(DecideExeclUtils.isExcel2007(file.getPath())){
				workbook = new XSSFWorkbook(new FileInputStream(file));
			}else {
				workbook = new HSSFWorkbook(new FileInputStream(file));
            }
		}catch (Exception e) {
			e.printStackTrace();
		}
		 Sheet sheet = workbook.getSheetAt(0);//获取第一张表
		 int lastRowNum = sheet.getLastRowNum();
//		 System.out.println(lastRowNum);
		 c:for(int i=1; i< lastRowNum + 1; i++){
			 Row row = sheet.getRow(i);//获取每行数据,从0开始
			 String b = row.getCell(2).getStringCellValue();
			 if( b==null){
				 continue c;
			 }
			 GKCode gkCode = new GKCode();
			
			 gkCode.setCode2(b);
			 arrayList.add(gkCode);
		 }
		 try
	        {
			 workbook.close();
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
		 for (GKCode gk : arrayList) {
			System.out.println(gk.getCode2());
		}
	}
}
