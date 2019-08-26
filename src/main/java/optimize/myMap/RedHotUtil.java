package optimize.myMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RedHotUtil {

    public static void main(String[] args) {
        int a = 900 ;
        int b = 900 /10 ;
        int c = 900 - 150 - b -b;
        List<Integer> list = getList(c, 6);
        Collections.sort(list);
        list.stream().map(e -> e + b).forEach(System.out::println);
        // 一一行
        Random random = new Random();
        int d =  random.nextInt(c) + b;
        System.out.println("高度: "+(d+ 150));
        System.out.println("底部: "+d);
    }

    private static List<Integer> getList(Integer width, Integer numbers){
        ArrayList<Integer> list = new ArrayList<>();
        Integer spots = numbers ;
        Integer amount = width;
        list.add(amount);
        Random random = new Random();
        for (int i = 0; i < numbers -1; i++) {
            int i1 = random.nextInt(amount / spots * 2 - 50) + 50;
            amount -= i1;
            spots--;
            list.add(amount);
        }
        return list;
    }
}
