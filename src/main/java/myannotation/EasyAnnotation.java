package myannotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE,ElementType.METHOD})
public @interface EasyAnnotation {

	/**
	 * 如果注解本身没有注解类型元素，那么在使用注解的时候可以省略()，直接写为：@注解名，它和标准语法@注解名()等效！
	 * 如果注解本本身只有一个注解类型元素，而且命名为value，那么在使用注解的时候可以直接使用：@注解名(注解值)，其等效于：@注解名(value = 注解值)
	 * @return
	 */
	public String value();

	String username();
	
	int age() default 18;
}
