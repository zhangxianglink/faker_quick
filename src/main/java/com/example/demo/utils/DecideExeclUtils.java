package com.example.demo.utils;

public class DecideExeclUtils {
	
	 public static boolean isExcel2003(String filePath)
	    {
	        return filePath.matches("^.+\\.(?i)(xls)$");
	    }

	    public static boolean isExcel2007(String filePath)
	    {
	        return filePath.matches("^.+\\.(?i)(xlsx)$");
	    }

}
