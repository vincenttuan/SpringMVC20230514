package com.spring.mvc.single.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.mvc.single.entity.Person;
import com.spring.mvc.single.repository.PersonRepository;

@Controller
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping("/")
	public String index(@ModelAttribute Person person, Model model, HttpSession httpSession) {
		List<Person> persons = queryPagePersons(httpSession); // personRepository.findAll();
		model.addAttribute("_method", "POST");
		model.addAttribute("persons", persons);
		return "person/index";
	}
	
	@GetMapping("/{id}")
	public String getPersonById(@PathVariable Long id, Model model, HttpSession httpSession) {
		Person person = personRepository.findOne(id);
		List<Person> persons = queryPagePersons(httpSession); // personRepository.findAll();
		model.addAttribute("_method", "PUT");
		model.addAttribute("person", person);
		model.addAttribute("persons", persons);
		return "person/index";
	}
	
	@PostMapping("/")
	public String create(@ModelAttribute Person person) {
		personRepository.save(person);
		return "redirect:/mvc/person/";
	}
	
	@PutMapping("/")
	public String update(@ModelAttribute Person person) {
		// 該筆資料是否存在 ?
		if(personRepository.exists(person.getId())) {
			// 修改
			personRepository.save(person);
		}
		return "redirect:/mvc/person/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteById(@PathVariable long id) {
		personRepository.delete(id);
		return "redirect:/mvc/person/";
	}
	
	// 查詢分頁
	// 範例路徑：/page, /page?no=1, /page?no=5 etc...
	@GetMapping("/page")
	public String page(@RequestParam(name = "no", required = false, defaultValue = "0") Integer no, Model model) {
		int pageNo = no;
		int pageSize = 5;
		// 排序
		Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
		Sort sort = new Sort(order);
		// 分頁
		PageRequest pageRequest = new PageRequest(pageNo, pageSize, sort);
		Page<Person> page = personRepository.findAll(pageRequest);
		List<Person> persons = page.getContent(); 
		model.addAttribute("persons", persons);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("totalPage", page.getTotalPages());
		return "person/page";
	}
	
	// 範例路徑：/page2, /page2?no=1, /page2?no=5 etc...
	@GetMapping("/page2")
	public String page2(@RequestParam(name = "no", required = false, defaultValue = "0") Integer no, HttpSession httpSession) {
		httpSession.setAttribute("pageNo", no);
		// 寫入 session
		return "redirect:/mvc/person/";
	}
	
	private List<Person> queryPagePersons(HttpSession httpSession) {
		int pageNo = 0;
		Object pageObj = httpSession.getAttribute("pageNo");
		if(pageObj instanceof Integer) {
			pageNo = (Integer)pageObj;
		}
		int pageSize = 5;
		// 排序
		Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
		Sort sort = new Sort(order);
		// 分頁
		PageRequest pageRequest = new PageRequest(pageNo, pageSize, sort);
		Page<Person> page = personRepository.findAll(pageRequest);
		List<Person> persons = page.getContent();
		// 寫入 session
		httpSession.setAttribute("totalPage", page.getTotalPages());
		return persons;
	}
	
}
