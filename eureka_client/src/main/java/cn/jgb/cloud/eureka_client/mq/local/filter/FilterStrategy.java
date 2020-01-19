package cn.jgb.cloud.eureka_client.mq.local.filter;

import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 匹配算法策略
 */
public abstract class FilterStrategy {
    List<String> filter;
	Result resultRule;

	// 返回结果
	protected String typeResult = null;
	protected String proteinResult = null;
	protected String riceResult = null;

    FilterStrategy(List filter,Result resultRule) {
        this.filter = filter;
		this.resultRule = resultRule;
    }

    public Result getResult() {
    	return new Result(typeResult,proteinResult,riceResult);
	}

    abstract boolean filter(Map<String,Object> parame,Result result);

	/**
	 * 是否有疾病
	 * @param disease
	 * @return
	 */
	protected boolean contains(List<String> disease) {
		List<String> collect = filter.stream().filter(e -> disease.contains(e)).collect(Collectors.toList());
		return !collect.isEmpty();
    }

	/**
	 * 移除相同疾病
	 * @param disease
	 */
	protected void remove(List<String> disease) {
		List<String> collect = filter.stream().filter(e -> disease.contains(e)).collect(Collectors.toList());
		System.out.println("移除前:" + disease);
		disease.removeAll(collect);
		System.out.println("移除后==>" + disease);
		System.out.println("-----------------------");
	}
}

/**
 * 只匹配疾病
 */
class DiseaseStrategy extends FilterStrategy{

    DiseaseStrategy(List filter, Result resultRule) {
        super(filter,resultRule);
    }

    @Override
    public boolean filter(Map<String,Object> parame,Result result) {
        List<String> disease = (List<String>) parame.get("disease");
        if(contains(disease)) {
        	remove(disease);
        	if(StringUtils.isEmpty(result.getType())) {
				result.setType(resultRule.getType());
			}else {
				if(Rule.greaterType(resultRule.getType(),result.getType())) {
					result.setType(resultRule.getType());
				}
			}
        }

        return disease.isEmpty();
    }
}

/**
 * 疾病 + 年龄 >= 65
 */
class DiseaseAgeStrategyGT extends FilterStrategy{

    DiseaseAgeStrategyGT(List filter, Result resultRule) {
		super(filter,resultRule);
    }

    @Override
    public boolean filter(Map<String,Object> parame,Result result) {
        List<String> disease = (List<String>) parame.get("disease");
        Integer age = (Integer) parame.get("age");
        if(contains(disease)) {
            if(age >= 65) {
				remove(disease);
            	// 类型
				if(StringUtils.isEmpty(result.getType())) {
					result.setType(resultRule.getType());
				}else {
					if(Rule.greaterType(resultRule.getType(),result.getType())) {
						result.setType(resultRule.getType());
					}
				}

				// 蛋白质
				if(StringUtils.isEmpty(result.getProtein())) {
					result.setProtein(resultRule.getProtein());
				}else {
					if(Rule.greaterProtein(resultRule.getProtein(),result.getProtein())) {
						result.setProtein(resultRule.getProtein());
					}
				}

				// 米饭类型
				if(StringUtils.isEmpty(result.getRice())) {
					result.setRice(resultRule.getRice());
				}
            }
        }
		return disease.isEmpty();
    }
}

/**
 * 疾病 + 年龄 < 65
 */
class DiseaseAgeStrategyLT extends FilterStrategy{

    DiseaseAgeStrategyLT(List filter, Result resultRule) {
		super(filter,resultRule);
    }

    @Override
    public boolean filter(Map<String,Object> parame,Result result) {
        List<String> disease = (List<String>) parame.get("disease");
        Integer age = (Integer) parame.get("age");
        if(contains(disease)) {
            if(age < 65) {
				remove(disease);
				// 类型
				if(StringUtils.isEmpty(result.getType())) {
					result.setType(resultRule.getType());
				}else {
					if(Rule.greaterType(resultRule.getType(),result.getType())) {
						result.setType(resultRule.getType());
					}
				}

				// 蛋白质
				if(StringUtils.isEmpty(result.getProtein())) {
					result.setProtein(resultRule.getProtein());
				}else {
					if(Rule.greaterProtein(resultRule.getProtein(),result.getProtein())) {
						result.setProtein(resultRule.getProtein());
					}
				}

				// 米饭类型
				if(StringUtils.isEmpty(result.getRice())) {
					result.setRice(resultRule.getRice());
				}
            }
        }
		return disease.isEmpty();
    }
}

/**
 * 疾病 + 年龄 < 65 + 米饭=软米饭
 */
class DiseaseAgeRiceStrategyLT extends FilterStrategy{

    DiseaseAgeRiceStrategyLT(List filter, Result resultRule) {
		super(filter,resultRule);
    }

    @Override
    public boolean filter(Map<String,Object> parame,Result result) {
        List<String> disease = (List<String>) parame.get("disease");
        Integer age = (Integer) parame.get("age");
        Boolean flag = (Boolean) parame.get("flag");
        if(contains(disease)) {
            if(age < 65 && flag) {
				remove(disease);
				// 类型
				if(StringUtils.isEmpty(result.getType())) {
					result.setType(resultRule.getType());
				}else {
					if(Rule.greaterType(resultRule.getType(),result.getType())) {
						result.setType(resultRule.getType());
					}
				}

				// 蛋白质
				if(StringUtils.isEmpty(result.getProtein())) {
					result.setProtein(resultRule.getProtein());
				}else {
					if(Rule.greaterProtein(resultRule.getProtein(),result.getProtein())) {
						result.setProtein(resultRule.getProtein());
					}
				}

				// 米饭类型
				if(StringUtils.isEmpty(result.getRice())) {
					result.setRice(resultRule.getRice());
				}
            }
        }
		return disease.isEmpty();
    }
}

/**
 * 疾病 + 年龄 < 65 + 米饭=非软米饭
 */
class DiseaseAgeNotRiceStrategyLT extends FilterStrategy{

    DiseaseAgeNotRiceStrategyLT(List filter, Result resultRule) {
		super(filter,resultRule);
    }

    @Override
    public boolean filter(Map<String,Object> parame,Result result) {
        List<String> disease = (List<String>) parame.get("disease");
        Integer age = (Integer) parame.get("age");
        Boolean flag = (Boolean) parame.get("flag");
        if(contains(disease)) {
            if(age < 65 && !flag) {
				remove(disease);
				// 类型
				if(StringUtils.isEmpty(result.getType())) {
					result.setType(resultRule.getType());
				}else {
					if(Rule.greaterType(resultRule.getType(),result.getType())) {
						result.setType(resultRule.getType());
					}
				}

				// 蛋白质
				if(StringUtils.isEmpty(result.getProtein())) {
					result.setProtein(resultRule.getProtein());
				}else {
					if(Rule.greaterProtein(resultRule.getProtein(),result.getProtein())) {
						result.setProtein(resultRule.getProtein());
					}
				}

				// 米饭类型
				if(StringUtils.isEmpty(result.getRice())) {
					result.setRice(resultRule.getRice());
				}
            }
        }
		return disease.isEmpty();
    }


}

