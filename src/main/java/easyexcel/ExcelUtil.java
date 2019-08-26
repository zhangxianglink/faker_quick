package easyexcel;


import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;



public class ExcelUtil {
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
//            throw new ExcelImportFailureException();
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
}