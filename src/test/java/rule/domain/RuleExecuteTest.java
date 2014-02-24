package rule.domain;

import java.util.ArrayList;
import java.util.List;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;

public class RuleExecuteTest {
	// 构造执行器
	ExpressRunner runner = new ExpressRunner(false, true);

	// 做一些初始化的操作，添加规则解析的函数
	public void init() throws Exception {
		runner.addOperatorWithAlias("而且", "and", null);
		runner.addFunctionOfClassMethod("userTagJudge",
				Function.class.getName(), "userTagJudge", new String[] {
						UserDO.class.getName(), "int" }, "你不是三星卖家");

		runner.addFunctionOfClassMethod("hasOrderGoods",
				Function.class.getName(), "hasOrderGoods", new String[] {
						UserDO.class.getName(), "long" }, "你没有开通淘宝店铺");

		runner.addMacro("三星卖家", "userTagJudge(userInfo,3)");// 3表示三星卖家的标志位
		runner.addMacro("苹果卖家", "userTagJudge(userInfo,5)");// 5表示苹果卖家的标志位
		runner.addMacro("已经开店", "hasOrderGoods(userInfo,100)");// 100表示旺铺商品的ID
	}

	// 封装执行
	public String hasPermission(UserDO userInfo, String expression)
			throws Exception {
		IExpressContext<String, Object> expressContext = new DefaultContext<String, Object>();
		expressContext.put("userInfo", userInfo);
		List<String> errorInfo = new ArrayList<String>();
		Boolean result = (Boolean) runner.execute(expression, expressContext,
				errorInfo, true, false);
		String resultStr = "";
		if (result.booleanValue() == true) {
			resultStr = "可以订购此商品";
		} else {
			for (int i = 0; i < errorInfo.size(); i++) {
				if (i > 0) {
					resultStr = resultStr + ",";
				}
				resultStr = resultStr + errorInfo.get(i);
			}
			resultStr = resultStr + ",所以不能订购此商品";
		}
		return "亲爱的" + userInfo.getName() + " : " + resultStr;
	}

	public static void main(String[] args) throws Exception {
		RuleExecuteTest exe = new RuleExecuteTest();
		exe.init();
		String str = exe.hasPermission(new UserDO(151, "iamzhong", 8),
				"苹果卖家 and 已经开店");
		System.out.println(str);
	}

}
