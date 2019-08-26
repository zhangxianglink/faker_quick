package com.example.demo.lambda;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import org.apache.tomcat.jni.Time;
import org.junit.Test;
import org.springframework.util.Assert;

/*
 * 所有的java.time队形都是不可变的
 * 一个瞬间instant是时间线上的一个点
 * 在java事件中每天都是86400秒
 * 持续时间Duration是两个瞬间之间的时间
 * LocalDateTime没有时区信息
 * TemporalAdjuster的方法可以处理常用的日历计算.
 * ZonedDateTime是指定时区中的某个时间点
 * 使用DateTimeForMatter来格式化和解析时间,日期
 */
public class NewDate1 {

	/*
	 * 时间线上的的原点: 1970.1.1的午夜
	 */
	@Test
	public void TimeLIne1() {
		// 当前时间点
		Instant now = Instant.now();
		// 计算一段时间
		for (int i = 0; i < 1000000; i++) {
			Assert.notNull(i, "空");
		}
		Instant end = Instant.now();
		Duration between = Duration.between(now, end);
		long millis = between.toMillis();
		System.out.println(millis);
	}

	/*
	 * 本地日期
	 */
	@Test
	public void TlocalDate() {
		// LocalDate 带有年,月,日 2019-02-25
		LocalDate now = LocalDate.now();
		// 改进:可以使用1代表一月份
		LocalDate of = LocalDate.of(2018, Month.AUGUST, 30);
		// 添加时间
		now.plusDays(1);
		now.plusMonths(1);
		now.plusYears(1);
		System.out.println(now.plusWeeks(1));
		// 减去时间
		of.minusDays(1);
		// 添加减去一段日期Period 或者 Duration
		Period period = Period.between(of, now);
		of.minus(period);
		of.plus(period);
		// 修改年月日,返回新的对象
		of.withDayOfMonth(1);
		of.withDayOfYear(1);
		of.withYear(1);
		of.withMonth(1);
		// get大致如上
		// 获取时间段
		Period until = of.until(now);
		int days = until.getDays();
		long until2 = of.until(now, ChronoUnit.DAYS);
		System.out.println(days + "=" + until2);
		// 比较日期
		boolean before = now.isBefore(of);
		boolean after = now.isAfter(of);
		// 判断是否是闰年
		LocalDate leap = LocalDate.of(2008, 1, 1);
		boolean leapYear = leap.isLeapYear();
		System.out.println(leapYear);
		// 常用
		DayOfWeek.FRIDAY.plus(1);
		Year.isLeap(2019);
		YearMonth.now();
		MonthDay.now();
	}

	
	/**
	 * 日期校准器,TemporalAjusters 提供静态方法进行校正
	 */
	private TemporalAdjuster NEXT_workDAY = TemporalAdjusters.ofDateAdjuster(w -> {
		LocalDate result = w;
//		do {
			result = result.plusDays(7);
//		} while (result.getDayOfWeek().getValue() >= 6);
		return result;
	});

	@Test
	public void testTemporalAdjusters() {
		// 计算接下来的第一个周6
		TemporalAdjuster adjuster = TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY);
		LocalDate tuesday = LocalDate.now().with(adjuster);
		System.out.println(tuesday);
		// 指定工作日的前一周或者后一周
		LocalDate of = LocalDate.of(2019, 3, 3);
		LocalDate with = of.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
		System.out.println(with.toString());
		// 某月中的第n个工作日
		LocalDate with2 = of.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.MONDAY));
		System.out.println(with2);
		// 某月中最后一个工作日
		TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY);
		/*
		 * 还有诸如新年第一天 ,最后一天 $下一年第一天 本月第一天/最后 (指定工作日)$ 下个月第一天
		 */
		LocalDate now = LocalDate.now();
		LocalDate with3 = now.with(NEXT_workDAY);
		System.err.println(with3);
	}
	
	/*
	 * 本地时间 LocalTime
	 * 格式化与解析
	 */
	@Test
	public void TestTime(){
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now.toString());
		String format = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(now);
		//格式化
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm:ss");
		String format2 = pattern.format(now);
		System.out.println(format2);
		
	}
	
	@Test
	public void StringToLocalDate(){
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMdd");
		String date = "20190901";
		LocalDate parse = LocalDate.parse(date, ofPattern);
		LocalDate minusDays = parse.minusDays(1);
		String format = ofPattern.format(minusDays);
		System.out.println(format);
	}

	/**
	 * 获取程序员节日时间 ,每年的第256天
	 */
	@Test
	public void coderDay(){
		TemporalAdjuster lastDayOfYear = TemporalAdjusters.lastDayOfYear();
		LocalDate date = LocalDate.now().with(lastDayOfYear);
		LocalDate result = null;
		if (date.isLeapYear()) {
			result = date.minusDays(110);
		}else{
		    result = date.minusDays(109);
		}
		System.out.println(result.getDayOfYear());
	}
	
	/**
	 * 获取某个月的日历
	 */
	@Test
	public void cal(){
		//设置年,月,日
		LocalDate day = LocalDate.of(2019, 2, 23);
		//获得第一天,最后一天
		LocalDate first = day.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate last = day.with(TemporalAdjusters.lastDayOfMonth());
		int rows = last.getDayOfMonth() / 7 + 1 ;
		for (int i = 0; i < rows; i++) {
			
			System.out.println();
		}
	}
	
	
	
	
	
}
