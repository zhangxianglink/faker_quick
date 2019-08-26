package com.example.demo;


import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.dao.BoyDao;
import com.example.demo.dao.TclassMapper;
import com.example.demo.pojo.Boy;
import com.example.demo.pojo.Tclass;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BoyMapperTest {
	
	@Autowired
	private BoyDao boyDao;
	
	@Test
	public void showww(){
		//获取纳秒
	HashMap<Integer, Integer> hashMap = new HashMap<>();
		for (int i = 0; i < 3; i++) {
			float permillage = getPermillage();
    		int x = (int) (permillage * (230 -150) +150);
    		permillage = getPermillage();
    		int y = (int) (permillage * (999 -150) +150);
    		hashMap.put(x, y);
		}
		System.out.println(hashMap.toString());
		String a = "xxxxxddddggg";
		String substring = a.substring(6, a.length());
		System.out.println(substring);
	}
	
	public static float getPermillage(){
		int nano = LocalTime.now().getNano();
		System.out.println("纳秒:"+ nano);
		int i=(int)(Math.random()*900)+100;
		float permillage = i / 1000f;
		System.out.println("千分比: " + permillage);
		return permillage;
	}
	@Test
	public void sho(){
		UUID randomUUID = UUID.randomUUID();
		System.out.println(randomUUID.toString());
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMMyyyyHHmmss-SSS");
		String formatStr =formatter.format(new Date());
		formatStr = formatStr.substring(formatStr.length()-3);
		System.out.println(formatStr);
		int parseInt = Integer.parseInt(formatStr);
		float permillage = parseInt / 1000f;
		System.out.println(permillage);
	}
	
	
	public static void main(String[] args) {
		Boy b1 = new Boy(1, 1, "b1");
		Boy b2 = new Boy(2, 1, "b2");
		Boy b3 = new Boy(3, 2, "b3");
		Boy b4 = new Boy(2, 2, "b4");
		Boy b5 = new Boy(1, 3, "b5");
		List<Boy> list = Arrays.asList(b1,b2,b3,b4,b5);
		Map<String,Boy> map = Maps.uniqueIndex(list, Boy::getbName);
		map.keySet().stream().forEach(e -> System.out.println(map.get(e).toString()));
		System.out.println("jdk8-----------------------");
		Map<Integer, Boy> collect = list.stream().collect(Collectors.toMap(Boy::getbId, Function.identity(), (v1,v2) -> v2));
		collect.keySet().stream().forEach(e -> System.out.println(collect.get(e).toString()));
	}
	
	@Test
	public void addOne(){
		Boy boy = new Boy();
		boy.setbHeight(110);
		boy.setbName("猴子");
		int insertBoy = boyDao.insertBoy(boy);
		System.out.println(boy.getbId() + " : " +insertBoy);
		// annotation method
//		int addBoy = boyDao.addBoy(boy);
	}
	
	@Test
	public void deleteOne(){
		int deleteOneById = boyDao.deleteOneById(8);
		Assert.assertNotNull("xml", deleteOneById);
		int deleteOneById2 = boyDao.deleteOneById2(9);
		Assert.assertNotNull("annotation:", deleteOneById2);
	}
	
	@Test
	public void selectOne(){
		Boy selectOneById = boyDao.selectOneById(1);
		Boy a = boyDao.selectOneById2(1);
	    System.out.println(selectOneById.toString());
	    // 注意返回字段的映射匹配
	    System.out.println(a.toString());
	}
	
	@Test
	public void updateOne(){
		Boy boy = new Boy();
		boy.setbId(10);
		boy.setbHeight(124);
		boy.setbName("jojo");
		boyDao.updateUser1(boy);
		//annotation
		boyDao.updateUser2(132, "kafka", 7);
	}
	
	@Test
	public void find(){
		Boy boy = new Boy();
		List<Boy> findBoy = boyDao.findBoy(boy);
		findBoy.forEach(e -> System.out.println(e));
	}

	@Test
	public void find2(){
		Boy boy = new Boy();
//		boy.setBHeight(169);
		Boy findBoy2 = boyDao.findBoy2(boy);
		System.out.println(findBoy2);
	}
	
	@Test
	public void find3(){
		List<Integer> list = Arrays.asList(1,2,3);
		List<Boy> list2 = boyDao.selectById(list);
		list2.forEach(e -> System.out.println(e));
	}
	
	@Autowired
	private TclassMapper mapper;
	
	@Test
	public void show(){
//		Tclass class1 = mapper.getClass(1);
//		System.out.println(class1);
		Tclass getcc = mapper.getcc(2);
		System.out.println(getcc);
	}
	
	@Test
	public void newBoy(){	
		Multimap<String, Integer> multimap = ArrayListMultimap.create();
		multimap.put("营业证书",12);
		multimap.put("营业证书",24);
		multimap.put("药品批件",33);
		multimap.put("生产证书",4);
		multimap.put("生产证书",2);
		Map<String, Collection<Integer>> asMap = multimap.asMap();
		for(String value : asMap.keySet()){
			Collection<Integer> collection = asMap.get(value);
			if(collection.size() > 1){
			System.out.println(value + " : " + collection.toString());
			}
		}
	}
}

















