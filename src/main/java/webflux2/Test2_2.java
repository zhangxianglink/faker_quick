package webflux2;

import java.util.stream.IntStream;

public class Test2_2 {
	
	public static void main(String[] args) {
		int asInt = IntStream.of(1,3,5,2,62,21).parallel().max().getAsInt();
		System.out.println(asInt);
	}
}
