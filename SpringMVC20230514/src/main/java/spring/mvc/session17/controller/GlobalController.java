package spring.mvc.session17.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * 在 Spring 中，我們可以使用 @ControllerAdvice 來聲明（宣告）一些全局性的東西
 * @ExceptionHandler 用於定義全局例外處理
 * @ModelAttribute 用於全局資料參數的定義
 * @InitBinder 初始化繫結器，用於資料繫結、設定資料轉換器等...
 * 
 * */
@ControllerAdvice
public class GlobalController {
	
	// 全局例外
	@ExceptionHandler({RuntimeException.class, SQLException.class})
	public String globalException(Exception ex, Model model, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		model.addAttribute("referer", referer);
		model.addAttribute("ex", "@ControllerAdvice 全局例外：" + ex);
		return "session17/error";
	}
	
}
