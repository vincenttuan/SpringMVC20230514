package spring.mvc.session09;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/showdata")
public class ShowDataController {
	
	// case1: 用網頁來顯示現在時間
	@RequestMapping("/case1")
	public ModelAndView case1() {
		String data = new Date() + ""; // model: 資料
		//String view = "/WEB-INF/views/session09/clock.jsp"; // view: 資料渲染處
		String view = "session09/clock"; // view: 資料渲染處
		//------------------------------------------
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", data); // 配置資料
		mav.setViewName(view);// 配置 view
		return mav;
	}
	
	// case2: 用網頁來顯示現在時間 (簡化寫法)
	//@RequestMapping(value = "/case2", method = RequestMethod.GET)
	@GetMapping("/case2")
	public String case2(Model model) { // 得到 Model 物件
		String data = new Date() + ""; 
		model.addAttribute("data", data); // 配置 model 的資料
		return "session09/clock"; // 設定 view 的 name
	}
	
	
}
