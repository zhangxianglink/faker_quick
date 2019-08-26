package com.example.demo.utils;

import java.sql.SQLException;

import oracle.sql.CLOB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ClobUtils {

    private static final Logger logger = LoggerFactory.getLogger(ClobUtils.class);

    public static String convertClobToString(Object object) {
        if (object instanceof CLOB) {
            try {
                int size = (int) ((CLOB) object).length();
                return ((CLOB) object).getSubString(1, size);
            } catch (SQLException e) {
                logger.error("serialize CLOB to String failure, message = {}", e.getMessage());
                throw new RuntimeException();
            }
        } else {
            return String.valueOf(object);
        }
    }
}
