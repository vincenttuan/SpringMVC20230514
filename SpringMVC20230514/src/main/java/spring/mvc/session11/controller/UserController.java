package spring.mvc.session11.controller;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

import spring.mvc.session11.entity.User;

@Controller
@RequestMapping("/session11/user")
public class UserController {
	
	private List<User> users = new CopyOnWriteArrayList<>();
	private List<String> educationList = Arrays.asList("小學", "國中", "高中", "大學", "研究所");
	
	// 預設資料
	{	
		users.add(new User("Vincent", "2010-05-01", "大學", "男", new String[] {"飛控"}, "Test1"));
		users.add(new User("Mary", "2012-06-03", "高中", "女", new String[] {"爬山","看書"}, "Test2"));
		users.add(new User("Helen", "2009-08-05", "研究所", "女", new String[] {"爬山"}, "Test3"));
		users.add(new User("Jack", "2012-09-07", "大學", "男", new String[] {"爬山", "打球"}, "Test4"));
		users.add(new User("Rose", "2011-12-09", "高中", "女", null, "Test5"));
	}
	
	// 首頁
	@GetMapping("/")
	public String index(Model model, @ModelAttribute User user) {
		model.addAttribute("_method", "POST");
		model.addAttribute("submitButtonName", "新增");
		model.addAttribute("users", users);
		model.addAttribute("educationList", educationList);
		return "session11/user";
	}
	
	// 單筆查詢
	// 範例：/SpringMVC20230514/mvc/session11/user/1?action=update
	// 範例：/SpringMVC20230514/mvc/session11/user/2?action=delete
	@GetMapping("/{index}")
	public String get(@PathVariable("index") int index, 
					  @RequestParam(value = "action", required = true) String action,
					  Model model) {
		
		User user = users.get(index);
		model.addAttribute("user", user);
		model.addAttribute("index", index);
		model.addAttribute("users", users);
		model.addAttribute("educationList", educationList);
		
		switch (action) {
			case "update":
				model.addAttribute("_method", "PUT");
				model.addAttribute("submitButtonName", "修改");
				break;
			case "delete":
				model.addAttribute("_method", "DELETE");
				model.addAttribute("submitButtonName", "刪除");
				break;
		}
		
		return "session11/user";
	}
	
	// 新增
	@PostMapping("/")
	public String add(@ModelAttribute User user) {
		users.add(user);
		return "redirect:./";
	}
	
	// 修改
	@PutMapping("/{index}")
	public String update(@PathVariable("index") int index, @ModelAttribute User user) {
		users.set(index, user);
		return "redirect:./";
	}
	
	// 刪除
	@DeleteMapping("/{index}")
	public String delete(@PathVariable("index") int index) {
		users.remove(index);
		return "redirect:./";
	}
	
}
