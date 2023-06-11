package spring.mvc.session13.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session13.entity.Person;

@Controller
@RequestMapping("/session13/person")
public class PersonController {
	
	private List<Person> people = new CopyOnWriteArrayList<>();
	
	@GetMapping("/")
	public String index(Model model, @ModelAttribute Person person) {
		model.addAttribute("people", people);
		return "session13/person";
	}
	
	@PostMapping("/")
	public String add(Model model, @ModelAttribute @Valid Person person, BindingResult result) {
		if(result.hasErrors()) { // 若有檢驗到錯誤
			model.addAttribute("people", people);
			return "session13/person"; // 自動將錯誤訊息資料也一併給 view
		}
		// 進行資料新增
		people.add(person);
		return "redirect:./";
	}
	
	
	
}
