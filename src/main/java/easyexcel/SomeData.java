package easyexcel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.IndexedColors;

import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.TableStyle;

public class SomeData {
	
	public static List<List<String>> getHead(){
		List<List<String>> head = new ArrayList<List<String>>();
	    List<String> headCoulumn1 = new ArrayList<String>();
	    List<String> headCoulumn2 = new ArrayList<String>();
	    List<String> headCoulumn3 = new ArrayList<String>();
	    List<String> headCoulumn4 = new ArrayList<String>();
	    List<String> headCoulumn5 = new ArrayList<String>();

	    headCoulumn1.add("第一列");headCoulumn1.add("第一列");headCoulumn1.add("第一列");
	    headCoulumn2.add("第一列");headCoulumn2.add("第一列");headCoulumn2.add("第一列");

	    headCoulumn3.add("第二列");headCoulumn3.add("第二列");headCoulumn3.add("第二列");
	    headCoulumn4.add("第三列");headCoulumn4.add("第三列2");headCoulumn4.add("第三列2");
	    headCoulumn5.add("第一列");headCoulumn5.add("第3列");headCoulumn5.add("第4列");

	    head.add(headCoulumn1);
	    head.add(headCoulumn2);
	    head.add(headCoulumn3);
	    head.add(headCoulumn4);
	    head.add(headCoulumn5);
	    return head;
	}
	
	public static List<List<Object>> getData(){
		List<List<Object>> data = new ArrayList<List<Object>>();
		for (int i = 0; i < 20; i++) {
			ArrayList<Object> list = new ArrayList<>();
			list.add("zhang");
			list.add(String.valueOf(154645872642l));
			list.add(Long.valueOf("215"+i));
			list.add("金王吧");
			list.add("我的博客");
			data.add(list);
		}
		return data;
	}
	
	public static TableStyle getTableStyle() {
		TableStyle tableStyle = new TableStyle();
		//表头
		Font font = new Font();
		font.setBold(true);
		font.setFontHeightInPoints((short)12);
		font.setFontName("宋体");
		tableStyle.setTableHeadFont(font);
		tableStyle.setTableHeadBackGroundColor(IndexedColors.YELLOW);
		//表格主体
		Font font2 = new Font();
		font2.setBold(false);
		font.setFontHeightInPoints((short)12);
//		font.setFontName("黑体");
		tableStyle.setTableContentFont(font2);
		tableStyle.setTableContentBackGroundColor(IndexedColors.GREEN);
		
		return tableStyle;
	}

}
