package easyexcel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;

public class Using {

	private static final Logger logger = LoggerFactory.getLogger(Using.class);

	/*
	 * 简易版,依赖定好的数据模型
	 */
	@Test
	public void writeExcel() {
		try (FileOutputStream out = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\easyexcel\\first.xlsx");) {
			ExcelWriter writer = EasyExcelFactory.getWriter(out);
			// sheetNo , headLineMun:表头占几行 , 数据模型
			Sheet sheet = new Sheet(1, 0, WriteModel.class);
			sheet.setSheetName("第一页");
			ArrayList<WriteModel> list = new ArrayList<>();
			for (int i = 0; i < 7; i++) {
				String str = "男";
				if (i % 2 == 0) {
					str = "女";
				}
				WriteModel build = WriteModel.builder().name("王麻子" + i).age(10 + i).gendr(str).build();
				list.add(build);
			}
			writer.write(list, sheet);
			writer.finish();
		} catch (Exception e) {
			logger.error("文件生成出现异常");
		}
	}

	/**
	 * 动态生成模型
	 */
	@Test
	public void writeExcel2() {
		try (FileOutputStream out = new FileOutputStream(
				"C:\\Users\\Administrator\\Desktop\\easyexcel\\second.xlsx");) {
			ExcelWriter writer = EasyExcelFactory.getWriter(out);
			Sheet sheet = new Sheet(1, 0);
			sheet.setSheetName("第一页");
			// 自定义表头
			Table table = new Table(1);
//			table.setTableStyle(SomeData.getTableStyle());
			table.setHead(SomeData.getHead());
			writer.write1(SomeData.getData(), sheet, table);
			// 合并单元格 下标从0开始, 单元格也是如此
//			writer.merge(5, 6, 0, 4);
			writer.finish();
		} catch (Exception e) {
			logger.error("文件生成出现异常");
		}
	}

	@GetMapping("/a.htm")
	public void cooperation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName = new String(("UserInfo " + new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getBytes(),
				"UTF-8");
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("multipart/form-data");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
		ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
		Sheet sheet1 = new Sheet(1, 0);
		sheet1.setSheetName("第一个sheet");
//	        writer.write0(getListString(), sheet1);
		writer.finish();
		out.flush();
	}

	/**
	 * 无java模型读取,excel解析的每行结果以List<String>返回 在ExcelListener获取解析结果
	 */
	@Test
	public void readExcel() {
		try (InputStream in = new FileInputStream(
				"C:\\\\Users\\\\Administrator\\\\Desktop\\\\easyexcel\\\\first.xlsx");) {
			ExcelListener listener = new ExcelListener();
			ExcelReader reader = new ExcelReader(in, ExcelTypeEnum.XLSX, listener);
			reader.read();
		} catch (Exception e) {
			logger.error("读取失败");
		}
	}

	/**
	 * 读取时剔除了表头
	 */
	@Test
	public void readExcel2() {
		try (InputStream in = new FileInputStream(
				"C:\\\\Users\\\\Administrator\\\\Desktop\\\\easyexcel\\\\first.xlsx");) {
			ExcelListener listener = new ExcelListener();
			ExcelReader reader = new ExcelReader(in, ExcelTypeEnum.XLSX, listener);
			Sheet sheet = new Sheet(1, 1, WriteModel.class);
			reader.read(sheet);
		} catch (Exception e) {
			logger.error("读取失败");
		}
	}
}
