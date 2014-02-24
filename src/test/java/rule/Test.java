package rule;

import com.ql.util.express.ExpressRunner;

public class Test {
	public static void main(String[] args) throws Exception{
		
		String express = "10 * 10 + 1 + 2 * 3 + 5 * 2";
		ExpressRunner runner = new ExpressRunner(false,true);
		Object r = runner.execute(express,null, null, true,true);
		System.out.println("表达式计算：" + express + " = " + r);

	}

}
