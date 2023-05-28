package spring.mvc.session10.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session10.service.LottoService;

@Controller
@RequestMapping("/lotto")
public class LottoController {
	
	@Autowired
	private LottoService lottoService;
	
	// 首頁
	@GetMapping("/")
	public String index(Model model) {
		List<Set<Integer>> lottos = lottoService.queryAll();
		model.addAttribute("lottos", lottos);
		return "session10/lotto";
	}
	
	// 新增一筆 lotto
	@GetMapping("/add")
	public String add(Model model) {
		lottoService.add();
		return "redirect:./"; // 重導至 "/" 首頁
	}
}
