package myannotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 解析注解
 * @author Administrator
 *
 */
public class Using {
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class<?> student = Class.forName("myannotation.Student");
		boolean b = student.isAnnotationPresent(EasyAnnotation.class);
		if(b) {
			EasyAnnotation annotation = student.getAnnotation(EasyAnnotation.class);
			System.out.println("TYPE:" + annotation.username());
		}
		
		Method[] methods = student.getMethods();
		for (Method method : methods) {
			boolean c = method.isAnnotationPresent(EasyAnnotation.class);
			if(c) {
				EasyAnnotation annotation = method.getAnnotation(EasyAnnotation.class);
				System.out.println("METHOD1:" + annotation.username());
			}
		}
		
		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			for (Annotation a : annotations) {
				if(a instanceof EasyAnnotation) {
					EasyAnnotation annotation = (EasyAnnotation) a;
					System.out.println("METHOD2:" + annotation.username());
				}
			}
		}
	}

}
