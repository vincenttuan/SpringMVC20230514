package spring.mvc.session17.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session17.entity.Division;

@Controller
@RequestMapping("/session17/division")
public class DivisionController {
	
	@GetMapping("/")
	public String index(@ModelAttribute Division division) {
		return "session17/division";
	}
	
	@PostMapping("/")
	public String operation(@ModelAttribute Division division) {
		int result = division.getX() / division.getY();
		division.setResult(result);
		return "session17/division";
	}
	
	// 捕獲使用者輸入資料格式不正確的例外：BindException
	// 數學錯誤例外：ArithmeticException
	@ExceptionHandler({BindException.class, ArithmeticException.class})
	public String catchException(Exception ex, Model model, HttpServletRequest request) {
		// 那一頁發生問題 ?
		String referer = request.getHeader("Referer");
		model.addAttribute("referer", referer);
		switch (ex.getClass().getSimpleName()) {
			case "BindException":
				model.addAttribute("ex", "使用者輸入資料格式不正確");
				break;
			case "ArithmeticException":
				model.addAttribute("ex", "數學錯誤例外:分母不可為 0");
				break;
		}
		//model.addAttribute("ex", ex.getClass().getSimpleName() + ", " + ex.getMessage());
		return "session17/error";
	}
	
	
}
