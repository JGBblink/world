package cn.jgb.cloud.eureka_client.mq.local.filter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class MainTest {

	FilterLink linnk1;

	{
		String disease1 = "痛风急性发作、高尿酸血症、上消化道出血、慢性乙肝、腹部大手术";
		String disease2 = "急性胆囊炎缓解初期、炎症性肠病、急性胰腺炎缓解初期、结肠息肉切除术后、急性胃炎缓解初期、消化性溃疡I期、胃息肉切除术后、疝气术后、胃肠术后1-3d、妇科术后1-3d";
		String disease3 = "急性胆囊炎缓解中期、急性胰腺炎缓解中期、急性胃炎缓解中期、慢性胃炎急性发作期、消化性溃疡恢复II期-III期";
		String disease4 = "糖尿病肾病、肾功能不全、肾功能不全伴并发症、慢性肾炎、肾病综合症、肾炎、肾功能不全";
		String disease5 = "心衰、脑卒中、慢性心功不全（心功III级及以上）、脑出血、脑肿瘤、阑尾手术、肛门疾病手术、肝脏手术、胃肠手术、胆道手术、胆囊手术、慢性胆囊炎、疝气术后恢复期、鼻咽癌、鼻咽部手术、结肠息肉、胃息肉、食管癌、肺癌、气胸、心率不齐、重症肺炎、重症感染伴胸腔积液、消化不良、肝硬化、急性胰腺炎恢复期、急性胆囊炎恢复期、慢性胃炎、消化性溃疡恢复IV期";
		String disease6 = "颈椎病、髋骨折、关节置换、上肢骨折、脊柱疾病、下肢骨折、骨肿瘤、颅脑损伤、肝恶性肿瘤、一般恶性肿瘤、血液恶性肿瘤、妇科恶性肿瘤、泌尿系统恶性肿瘤";
		String disease7 = "高血压、高血压伴并发症、糖尿病、高血脂、糖尿病足、甲亢、高钙血症、血色病、骨质疏松、冠心病伴并发症、冠心病、慢性心功不全（心功I-II级）、心率不齐、心动过速、房颤、室颤、心室肥厚、心肌炎、多囊肾、血液透析、急性肾炎、肾结石、癫痫、帕金森病、白内障、结膜炎、角膜溃疡、糖尿病眼病、疝气、甲乳手术、下肢静脉曲张手术、表皮包块切除术、慢性盆腔炎、子宫肌瘤、子宫脱垂、宫颈糜烂、肾囊肿、泌尿系统结石、前列腺增生、痴呆、慢性支气管炎急性发作、慢性支气管炎、支气管哮喘急性发作期、慢性阻塞性肺病、肺炎、慢性支气管哮喘、支气管扩张、支气管扩张伴并发症、哮喘、胃食管反流性疾病、胃食管反流性疾病伴食管炎、肠易激综合征不伴腹泻、肠易激综合征伴腹泻、痛风稳定期、胸腔积液";
		List<String> filter1 = Arrays.asList(disease1.split("、"));
		List<String> filter2 = Arrays.asList(disease2.split("、"));
		List<String> filter3 = Arrays.asList(disease3.split("、"));
		List<String> filter4 = Arrays.asList(disease4.split("、"));
		List<String> filter5 = Arrays.asList(disease5.split("、"));
		List<String> filter6 = Arrays.asList(disease6.split("、"));
		List<String> filter7 = Arrays.asList(disease7.split("、"));

		// DiseaseStrategy:只匹配疾病
		// DiseaseAgeStrategyGT:疾病 + 年龄 >= 65
		// DiseaseAgeStrategyLT:疾病 + 年龄 < 65
		// DiseaseAgeRiceStrategyLT:疾病 + 年龄 < 65 + 米饭=软米饭
		// DiseaseAgeNotRiceStrategyLT:疾病 + 年龄 < 65 + 米饭=非软米饭
		linnk1 = new ALink(new DiseaseStrategy(filter1,new Result("不可匹配",null,null)));
		FilterLink linnk2 = new ALink(new DiseaseStrategy(filter2,new Result("流食",null,null)));
		FilterLink linnk3 = new ALink(new DiseaseStrategy(filter3,new Result("半流食",null,null)));

		FilterLink linnk4 = new ALink(new DiseaseAgeStrategyGT(filter4,new Result("软食","低蛋白","低蛋白大米")));
		FilterLink linnk5 = new ALink(new DiseaseStrategy(filter5,new Result("软食","普通蛋白","白米饭")));
		FilterLink linnk6 = new ALink(new DiseaseAgeStrategyGT(filter7,new Result("软食","普通蛋白","白米饭")));
		FilterLink linnk7 = new ALink(new DiseaseAgeStrategyGT(filter6,new Result("软食","高蛋白","白米饭")));

		FilterLink linnk8 = new ALink(new DiseaseAgeStrategyLT(filter4,new Result("普食","低蛋白","低蛋白大米")));
		FilterLink linnk9 = new ALink(new DiseaseAgeRiceStrategyLT(filter7,new Result("普食","普通蛋白","白米饭")));
		FilterLink linnk10 = new ALink(new DiseaseAgeNotRiceStrategyLT(filter7,new Result("普食","普通蛋白","杂粮饭")));
		FilterLink linnk11 = new ALink(new DiseaseAgeRiceStrategyLT(filter6,new Result("普食","高蛋白","白米饭")));
		FilterLink linnk12 = new ALink(new DiseaseAgeNotRiceStrategyLT(filter6,new Result("普食","高蛋白","杂粮饭")));

		linnk1.setNext(linnk2);
		linnk2.setNext(linnk3);
		linnk3.setNext(linnk4);
		linnk4.setNext(linnk5);
		linnk5.setNext(linnk6);
		linnk6.setNext(linnk7);
		linnk7.setNext(linnk8);
		linnk8.setNext(linnk9);
		linnk9.setNext(linnk10);
		linnk10.setNext(linnk11);
		linnk11.setNext(linnk12);
	}
	/**
	 * 流程:定制规则-->链接规则-->传递参数-->获取结果
	 * @param args
	 */
    public static void main(String[] args) {

		// --------------------------以上代码主要是控制匹配规则以及筛选优先级-------------
        // --------------------------此处是传入参数,匹配结果------------------------
        Map<String,Object> parame = new HashMap<>(3);
        List<String> disease = new ArrayList<>();
        //disease.add("痛风急性发作");
        //disease.add("急性胆囊炎缓解初期");
        //disease.add("急性胆囊炎中期");
        //disease.add("糖尿病肾病");
        //disease.add("心衰");
        disease.add("颈椎病");
        disease.add("髋骨折");
        disease.add("关节置换");
        //disease.add("高血压");


		// 疾病列表,年龄,是否吃软米饭
		Result result = new Result(null, null, null);
		parame.put("disease",disease);
        parame.put("age",65);
        parame.put("flag",false);

		List<String> filter1 = Arrays.asList("颈椎病、髋骨折、关节置换、上肢骨折、脊柱疾病、下肢骨折、骨肿瘤、颅脑损伤、肝恶性肿瘤、一般恶性肿瘤、血液恶性肿瘤、妇科恶性肿瘤、泌尿系统恶性肿瘤".split("、"));
		List<String> filter2 = new ArrayList<>(Arrays.asList("颈椎病、髋骨折、关节置换".split("、")));
		List<String> collect = filter1.stream().filter(e -> disease.contains(e)).collect(Collectors.toList());
		filter2.removeAll(collect);

		// 匹配出结果
    }

    @RequestMapping("/match")
    public Result match(String disease,int age,boolean soft) {
    	Long begin = System.currentTimeMillis();
		List<String> diseases = new ArrayList<>(Arrays.asList(disease.split(",")));
		Result result = new Result(null, null, null);
		Map<String,Object> parame = new HashMap<>(3);
		parame.put("disease",diseases);
		parame.put("age",age);
		parame.put("flag",soft);

		linnk1.handleRequest(parame, result);
		System.out.println("time==>" + (System.currentTimeMillis() - begin));
		return result;
	}


}
