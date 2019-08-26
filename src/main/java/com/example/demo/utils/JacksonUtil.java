package com.example.demo.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

/**
 * Created by byang059 on 1/9/18.
 */
public class JacksonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String bean2Json(Object obj) throws IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
        mapper.writeValue(gen, obj);
        gen.close();
        return sw.toString();
    }

    public static <T> T json2Bean(Object map, Class<T> objClass) {
        try {
            String jsonStr = mapper.writeValueAsString(map);
            return mapper.readValue(jsonStr, objClass);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T json2Bean(Map map, Class<T> objClass)
            throws IOException {
        String jsonStr = mapper.writeValueAsString(map);
        return mapper.readValue(jsonStr, objClass);
    }

    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) {
        if (map == null) {
            return null;
        }
        try {
            T obj = beanClass.newInstance();
            BeanUtils.populate(obj, map);
            return obj;
        } catch (Exception e) {
        	  throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static Map<?, ?> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new BeanMap(obj);
    }
}
