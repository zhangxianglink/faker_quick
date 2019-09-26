package webflux3_stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;

import com.example.demo.pojo.Boy;

public class StreamDemo5 {
	
	public static void main(String[] args) {
		List<Boy> list = Arrays.asList(
				new Boy(1,170,"一号"),
				new Boy(1,172,"2号"),
				new Boy(3,173,"3号"),
				new Boy(2,174,"4号"),
				new Boy(3,178,"5号"),
				new Boy(7,179,"6号"),
				new Boy(7,181,"7号"),
				new Boy(7,185,"8号"),
				new Boy(7,190,"9号")
				);
		//聚合数据
		List<String> collect = list.stream().map(Boy::getbName).collect(Collectors.toList());
		System.out.println(collect);
		//指定具体类型
		TreeSet<String> set = list.stream().map(Boy::getbName).collect(Collectors.toCollection(TreeSet::new));
		//统计信息
		IntSummaryStatistics collect2 = list.stream().collect(Collectors.summarizingInt(Boy::getbId));
		System.out.println(collect2.toString());
		//分块
		Map<Boolean, List<Boy>> map = list.stream().collect(Collectors.partitioningBy(b -> b.getbHeight() > 180));
		MapUtils.verbosePrint(System.out, "身高分组", map);
		//分组
		Map<Integer, List<Boy>> collect3 = list.stream().collect(Collectors.groupingBy(Boy::getbId));
		Map<Integer, Long> collect4 = list.stream().collect(Collectors.groupingBy(Boy::getbId,Collectors.counting()));
	}

}
