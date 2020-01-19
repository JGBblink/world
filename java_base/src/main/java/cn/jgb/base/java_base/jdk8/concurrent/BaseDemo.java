package cn.jgb.base.java_base.jdk8.concurrent;

import java.util.regex.Pattern;
import java.util.stream.Stream;

public class BaseDemo {


	public static void main(String[] args) {

		String str = "asdbnzx,cnl,asdowe,asdaklskd,";
		String[] split = str.split(",");
		System.out.println(String.join(":",split));
		/*str.chars().forEach(c->{
			System.out.println(c + "->" + String.valueOf((char) c));
		});*/

		// 244889156@qq.com
		Pattern pattern = Pattern.compile(".*@gmail\\.com");
		long count = Stream.of("bob@gmail.com", "alice@hotmail.com")
				.filter(pattern.asPredicate())
				.count();

		System.out.println(count);


		Stream<String> stream = Stream.of("A", "B");

		Stream<String> stringStream = stream.filter(e -> e.length() > 0);
		stringStream.forEach(e->System.out.println());
		stringStream.forEach(e->System.out.println());

	}
}
