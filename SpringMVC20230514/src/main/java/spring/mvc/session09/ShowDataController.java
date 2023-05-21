package spring.mvc.session09;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/showdata")
public class ShowDataController {
	
	// case1: 用網頁來顯示現在時間
	@RequestMapping("/case1")
	@ResponseBody
	public String case1() {
		return new Date() + "";
	}
	
}
