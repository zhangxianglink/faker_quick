package com.example.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import easyexcel.ExcelUtil;



public class ImportExcelTest {
	
	
	

	@Test
	public void show() throws Exception{
		Path path = Paths.get("C:/Users/cuihang/Desktop/山西.xlsx");
		File file = path.toFile();
		boolean excel2003 = ExcelUtil.isExcel2003("C:/Users/cuihang/Desktop/山西.xlsx");
		Workbook wb = null;
		if(excel2003){
			   wb = new HSSFWorkbook(new FileInputStream(file));
		}else{
			 wb = new XSSFWorkbook(new FileInputStream(file));
		}
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(0);
		for (Cell cell : row) {
			System.out.println(cell.getStringCellValue());
		}
		int lastRowNum = sheet.getLastRowNum();
		System.out.println("行:" + lastRowNum);
		int count = 0;
		for (int i = 1; i <= lastRowNum; i++) {
			Row row2 = sheet.getRow(i);
			Cell cell = row2.getCell(2);
			Cell cell9 = row2.getCell(11);
			String sinopharmId = cell.getStringCellValue();
			sinopharmId = sinopharmId.replaceAll("Temp", "");
			String mnfctId = cell9.getStringCellValue();
			// 进行比较
			Map<String, Object> map = null ;
//			reviewDao.getMnfctByTempSinopharmId(sinopharmId);
			String dbMnfctId = ClobUtils.convertClobToString(map.get("FIELD_VALUE"));
		    int recordId = Integer.parseInt(map.get("ID").toString());
			if(!mnfctId.equals(dbMnfctId)){
				//进行更改
//				reviewDao.updateReviewDrugsMnfctId(mnfctId, recordId);
				count ++ ;
			}
			Cell createCell = row2.createCell(13);
			createCell.setCellValue(dbMnfctId);
		}
		File file2 = new File("C:/Users/cuihang/Desktop/山西2.xlsx");
		FileOutputStream outputStream = new FileOutputStream(file2);
		wb.write(outputStream);
		outputStream.flush();
		outputStream.close();
		System.out.println("count :" + count);
	}
	
	 
}
