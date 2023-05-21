package spring.mvc.session08.controller;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session08.entity.User;

@Controller
@RequestMapping(value = "/hello")
public class HelloController {
	
	/*
	 * 1. 取得 Welcome 字串的服務
	 * 路徑：/mvc/hello/welcome
	 * */
	@RequestMapping(value = "/welcome")
	@ResponseBody
	public String welcome() {
		return "Welcome Spring MVC!";
	}
	
	/*
	 * 2. ?帶參數 @RequestParam
	 * 路徑：/mvc/hello/sayhi?name=John&age=18
	 * 限制：name 的參數是一定要有的(預設)
	 *      age 的參數是不一定要有的, 初始值 = 0
	 * */
	@RequestMapping(value = "/sayhi")
	@ResponseBody
	public String sayHi(@RequestParam(value = "name", required = true) String name,
						@RequestParam(value = "age", required = false, defaultValue = "0") Integer age) {
		String data = String.format("Hi %s, %d", name, age);
		return data;
	}
	
	/*
	 * 3. Lab 練習
	 * 路徑: /mvc/hello/bmi?h=170.0&w=60.0
	 * 請設計一個 bmi 方法執行上述路徑後會得到 bmi = 20.76
	 * */
	@RequestMapping("/bmi") // @RequestMapping(value = "/bmi")
	@ResponseBody
	public String bmi(@RequestParam("h") Double h, @RequestParam("w") Double w) {
		double bmiValue = w / Math.pow(h/100, 2);
		return String.format("bmi = %.2f", bmiValue);
	}
	
	/*
	 * 4. 同名多筆資料參數
	 * 路徑：/mvc/hello/age?age=18&age=19&age=24
	 * 計算平均年齡
	 * */
	@RequestMapping("/age")
	@ResponseBody
	public String getAvgOfAge(@RequestParam("age") List<Integer> ages) {
		double avg = ages.stream()
						 .mapToInt(age -> age.intValue())  // 將 Integer 轉 int
						 //.mapToInt(Integer::intValue)  // 將 Integer 轉 int
						 .average() // 取得平均物件
						 .getAsDouble(); // 得到平均的 double 值
		return String.format("avg = %.1f", avg);
	}
	
	/*
	 * 5. Lab 練習: 得到多筆 score 資料
	 * 路徑：/mvc/hello/exam?score=80&score=100&score=50
	 * 請自行設計一個方法，此方法可以印出最高分、最低分、平均與總分
	 * */
	@RequestMapping(value = "/exam",
					produces = {"text/plain;charset=utf-8"})
	@ResponseBody
	public String getExamInfo(@RequestParam("score") List<Integer> scores) {
		IntSummaryStatistics stat = scores.stream()
										  .mapToInt(Integer::intValue)
										  .summaryStatistics();
		
		return String.format("最高分：%d 最低分：%d 平均：%.1f 總分：%d", 
				stat.getMax(), stat.getMin(), stat.getAverage(), stat.getSum());
	}
	
	/* 6. 得到多筆資料轉 Map
	 * 路徑：/mvc/hello/person?name=John&score=90&age=18&pass=true
	 * 路徑：/mvc/hello/person?name=Mary&score=40&age=17&pass=false
	 * */
	@RequestMapping("/person")
	@ResponseBody
	public String getPerson(@RequestParam Map<String, String> personMap) {
		return personMap.toString();
	}
	
	/* 
	 * 7. 得到多筆資料自動 Mapping 到指定物件
	 * 路徑：/mvc/hello/user?name=John&score=90&age=18&pass=true
	 * 路徑：/mvc/hello/user?name=Mary&score=40&age=17&pass=false
	 * */
	@RequestMapping("/user")
	@ResponseBody
	public String getUser(User user) {
		return user.toString();
	}
	
	/* 
	 * 8. 路徑參數 @PathVariable
	 * 路徑：/mvc/hello/javaexam/75
	 * 路徑：/mvc/hello/javaexam/45
	 * */
	@RequestMapping(value = "/javaexam/{score}")
	@ResponseBody
	public String getJavaExam(@PathVariable("score") Integer score) {
		return String.format("Java: %d %s", score, (score>=60)?"pass":"fail");
	}
	
	/*
	 * 9. Lab 練習：@RequestParam 與 @PathVariable 混用練習
	 * 路徑：/mvc/hello/calc/add?x=30&y=20  -> Result：50
	 * 路徑：/mvc/hello/calc/sub?x=30&y=20  -> Result：10
	 * 路徑：/mvc/hello/calc/sub?y=20       -> Result：-20
	 * 路徑：/mvc/hello/calc/add            -> Result：0
	 * 路徑：/mvc/hello/calc/sub            -> Result：0
	 * 請設計一個方法可以滿足上面路徑的請求
	 * */
	@RequestMapping(value = "/calc/{exp}")
	@ResponseBody
	public String calc(@PathVariable("exp") String exp,
						@RequestParam(value = "x", required = false, defaultValue = "0") Integer x,
						@RequestParam(value = "y", required = false, defaultValue = "0") Integer y) {
		int result = 0;
		switch (exp) {
			case "add":
				result = x + y;
				break;
			case "sub":
				result = x - y;
				break;
			}
		return String.format("Result: %d", result);
	}
	
	/*
	 * 10. @PathVariable (萬用字元: 任意多字(*)、任意一字(?))
	 * 路徑：/mvc/hello/any/aaabbc/java8
	 * 路徑：/mvc/hello/any/d/java9
	 * */
	@RequestMapping(value = "/any/*/java?")
	@ResponseBody
	public String any() {
		return "Any";
	}
	
	/*
	 * 11. @PathVariable (正則表示式)
	 * 路徑：/mvc/hello/student/vincent-18
	 * 路徑：/mvc/hello/student/anita-19
	 * */
	@RequestMapping(value = "/student/{name:[a-z]+}-{age:\\d+}")
	@ResponseBody
	public String student(@PathVariable("name") String name,
						  @PathVariable("age") Integer age) {
		return name + " : " + age;
	}
}






