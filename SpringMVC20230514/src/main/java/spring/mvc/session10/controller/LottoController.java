package spring.mvc.session10.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String add(Model model, RedirectAttributes attr) {
		Set<Integer> lotto = lottoService.add();
		attr.addAttribute("lotto", lotto); // 放在網址列？參數中
		//attr.addFlashAttribute("lotto", lotto); // 放在暫存的/一次性的 session 屬性中
		return "redirect:./"; // 重導至 "/" 首頁
	}
	
	// 修改 lotto
	@GetMapping("/update/{index}")
	public String update(@PathVariable("index") int index) {
		lottoService.update(index);
		return "redirect:../"; // 重導至 "/" 首頁
	}
	
	// 刪除 lotto
	@GetMapping("/delete/{index}")
	public String delete(@PathVariable("index") int index) {
		lottoService.delete(index);
		return "redirect:../"; // 重導至 "/" 首頁
	}
}
