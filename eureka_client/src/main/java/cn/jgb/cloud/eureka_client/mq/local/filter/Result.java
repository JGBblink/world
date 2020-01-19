package cn.jgb.cloud.eureka_client.mq.local.filter;

/**
 * 餐型匹配结果
 */
public class Result {
	private String type;
	private String protein;
	private String rice;

	public Result(String type, String protein, String rice) {
		this.type = type;
		this.protein = protein;
		this.rice = rice;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProtein() {
		return protein;
	}

	public void setProtein(String protein) {
		this.protein = protein;
	}

	public String getRice() {
		return rice;
	}

	public void setRice(String rice) {
		this.rice = rice;
	}

	@Override
	public String toString() {
		return "Result{" +
				"餐型='" + type + '\'' +
				", 蛋白类型='" + protein + '\'' +
				", 米饭='" + rice + '\'' +
				'}';
	}
}
