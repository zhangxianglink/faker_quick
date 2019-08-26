package com.example.demo.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleExcelWrite {
    private static final Logger logger = LoggerFactory.getLogger(SimpleExcelWrite.class);

    public static File exportExcel(String excelType, Map<String, String> headMap, List<Map<String, Object>> resaultMap,
        String exportUrl) throws Exception {
        // 第一步创建workbook
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步创建sheet
        HSSFSheet sheet = wb.createSheet(excelType);
        // 第三步创建行row:添加表头0行
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        // style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //居中

        // 第四步创建表头
        Set<String> set = headMap.keySet();
        Iterator<String> it = set.iterator();
        int index = 0;
        List<String> list = new ArrayList<>();
        while (it.hasNext()) {
            HSSFCell cell = row.createCell(index); // 第一个单元格
            String key = (String) it.next();
            String value = (String) headMap.get(key);
            cell.setCellValue(value);
            list.add(key);
            index++;
        }
        index = 0;
        // 第五步插入数据

        for (int i = 0; i < resaultMap.size(); i++) {
            row = sheet.createRow(i + 1);
            int j = 0;
            for (String string : list) {
                // 顺序写入
                Map<String, Object> data = resaultMap.get(i);
                Set<String> set1 = data.keySet();
                Iterator<String> it1 = set1.iterator();
                while (it1.hasNext()) {
                    String key1 = (String) it1.next();
                    String value1 = (String) data.get(key1);
                    if (string.equalsIgnoreCase(key1)) {
                        row.createCell(j).setCellValue(value1);
                        j++;
                    }
                }
            }
        }
        /*
         * for (int i = 0; i < dataArray.size(); i++) {
         * // 创建行
         * row = sheet.createRow(i + 1);
         * // 创建单元格并且添加数据
         * JsonObject object = dataArray.get(i).getAsJsonObject();
         * int j = 0;
         * for (Entry<String, JsonElement> e : object.entrySet()){
         * System.out.println(e.getValue().toString().replaceAll("\"",""));
         * row.createCell(j).setCellValue(e.getValue().toString().replaceAll("\"",""));
         * j++;
         * }
         * }
         */

        // 第六步将生成excel文件保存到指定路径下
        File parent = new File(exportUrl);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        File outputFile = new File(exportUrl, String.format("export2007_%s.xls", System.currentTimeMillis()));
        try {
            FileOutputStream fout = new FileOutputStream(outputFile);
            wb.write(fout);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Excel文件生成成功:" + outputFile.getAbsolutePath());
        return outputFile;

    }

    public static File exportExcelX(Map<String, String> headMap, List<Map<String, Object>> resaultMap,
        String exportUrl) throws Exception {
        long start = System.currentTimeMillis();
        // 第一步创建workbook
        XSSFWorkbook book = new XSSFWorkbook();
        SXSSFWorkbook wb = new SXSSFWorkbook(book);
        // 第二步创建sheet
        SXSSFSheet sheet = wb.createSheet("系统日志导出");
        sheet.setDefaultRowHeight((short) (2 * 256)); // 设置默认行高，表示2个字符的高度，必须先设置列宽然后设置行高，不然列宽没有效果
        sheet.setDefaultColumnWidth(17);
        // 第三步创建行row:添加表头0行
        SXSSFRow row = sheet.createRow(0);

        // 第四步创建表头
        Set<String> set = headMap.keySet();
        Iterator<String> it = set.iterator();
        int index = 0;
        List<String> list = new ArrayList<>();
        while (it.hasNext()) {
            SXSSFCell cell = row.createCell(index);
            String key = (String) it.next();
            String value = (String) headMap.get(key);
            cell.setCellValue(value);
            list.add(key);
            index++;
        }
        index = 0;
        // 第五步插入数据

        for (int i = 0; i < resaultMap.size(); i++) {
            row = sheet.createRow(i + 1);
            int j = 0;
            for (String string : list) {
                // 顺序写入
                Map<String, Object> data = resaultMap.get(i);
                Set<String> set1 = data.keySet();
                Iterator<String> it1 = set1.iterator();
                while (it1.hasNext()) {
                    String key1 = (String) it1.next();
                    Object value1 = data.get(key1);
                    if (string.equalsIgnoreCase(key1)) {
                        Object value = value1;
                        if (value instanceof BigDecimal) {
                            BigDecimal decimal = (BigDecimal) value;
                            double doubleValue = decimal.doubleValue();
                            row.createCell(j).setCellValue(doubleValue);
                        } else {
                            String str = (String) value;
                            row.createCell(j).setCellValue(str);
                        }
                        j++;
                    }
                }
            }
        }

        // 第六步将生成excel文件保存到指定路径下
        File parent = new File(exportUrl);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        File outputFile = new File(exportUrl, String.format("export"+year+"_%s.xlsx", System.currentTimeMillis()));
        OutputStream fout = new FileOutputStream(outputFile);
        wb.write(fout);
        fout.close();
        wb.close();
        logger.info("导出成功，目标存放在{}", outputFile.getAbsolutePath());
        long end = System.currentTimeMillis();
        logger.info("导出完成，共耗时{}", end - start);
        return outputFile;

    }

}
