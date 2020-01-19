package cn.jgb.cloud.eureka_client.mq.local.filter;

import java.util.Arrays;
import java.util.List;

/**
 * 多疾病筛选结果排序规则
 */
public class Rule {

	static List<String> typeRule = Arrays.asList("普食","软食","半流食","流食","不可匹配");
	static List<String> proteinRule = Arrays.asList("普通蛋白","高蛋白","低蛋白");

	/**
	 * 对餐型进行优先级比较
	 * @param A
	 * @param B
	 * @return
	 */
	public static boolean greaterType(String A,String B) {
		int a = typeRule.indexOf(A);
		int b = typeRule.indexOf(B);
		return a > b;
	}

	/**
	 * 对蛋白质进行优先级比较
	 * @param A
	 * @param B
	 * @return
	 */
	public static boolean greaterProtein(String A,String B) {
		int a = proteinRule.indexOf(A);
		int b = proteinRule.indexOf(B);
		return a > b;
	}
}
