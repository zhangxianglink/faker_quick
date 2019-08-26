package com.example.demo.utils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.ELState;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;



public class ExcelUtil  {
	public static SXSSFWorkbook getWorkBook() {
		return new SXSSFWorkbook(new XSSFWorkbook());
	}
	
	
	 /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public static boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            return false;
        }
        return true;
    }

    public static boolean isExcel2003(MultipartFile importFile) {
        String fileName = importFile.getOriginalFilename();// 获取文件名
        boolean isExcel = validateExcel(fileName);
        if (!isExcel) {
            throw new RuntimeException();
        }
        boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
        if (ExcelUtil.isExcel2007(fileName)) {
            isExcel2003 = false;
        }
        return isExcel2003;
    }

    /**
     * @param importFile
     * @param isExcel2003
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbook(MultipartFile importFile) {
        Workbook wb = null;
        try {

            if (isExcel2003(importFile)) {
                // 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(importFile.getInputStream());
            } else {
                // 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(importFile.getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }


    /**
     * 判断表格中值的类型并且返回一个String类型的值
     *
     * @param cell
     * @return
     */
    public static String getValue(Cell cell) {
        String result = "";
        if (cell == null) {
            return result;
        }
        if (cell.getCellType() == CellType.BOOLEAN) {
            result = String.valueOf(cell.getBooleanCellValue()).trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            if (isCellDateFormatted(cell)) {
                result = DateUtil.format(cell.getDateCellValue(), "MM/dd/YYYY");
            } else {
                //用于转化为日期格式
                result = String.valueOf(cell.getNumericCellValue()).trim();
            }
        } else {
            result = String.valueOf(cell.getStringCellValue()).trim();

        }

        if (!StringUtils.isEmpty(result) && result.endsWith(".0")) {
            result = result.replace(".0", "");
        }

        return result;
    }

    // @描述：是否是2003的excel，返回true是2003   
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007   
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    public static boolean isCellDateFormatted(Cell cell) {
        boolean cellDateFormatted;
        try {
            cellDateFormatted = HSSFDateUtil.isCellDateFormatted(cell);
        }catch (Exception e){
            cellDateFormatted = false;
        }
        return cellDateFormatted;
    }
    
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile("^1[3|4|5|6|7|8|9][0-9]\\d{8}$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
    

    public static boolean checkIdNumber(String idnumber) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");
            Matcher matcher = regex.matcher(idnumber);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
    
    
    public static boolean checkPassword(String password) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile("^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])).{6,16}$");
            Matcher matcher = regex.matcher(password);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}