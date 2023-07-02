package spring.mvc.session15.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session15.entity.Employee;
import spring.mvc.session15.entity.Job;
import spring.mvc.session15.repository.EmployeeDao;
import spring.mvc.session15.repository.JobDao;

@Controller
@RequestMapping("/session15/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private JobDao jobDao;
	
	@GetMapping("/")
	public String index(@ModelAttribute Employee employee, Model model, HttpSession session) {
		model.addAttribute("_method", "POST");
		setBaseModelAttribute(model, session);
		return "session15/employee";
	}
	
	// 新增 Employee
	@PostMapping("/")
	public String add(@ModelAttribute @Valid Employee employee, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "POST");
			setBaseModelAttribute(model, session);
			return "session15/employee";
		}
		// 新增資料
		employeeDao.add(employee);
		
		// 判斷使用者是否在分頁狀態
		String sessionNum = session.getAttribute("num") + "";
		if(sessionNum.length() > 0) {
			// 設定目前最大的 num
			session.setAttribute("num", getPageCount());
		}
		return "redirect:./";
	}
	
	// 取得總頁數
	private int getPageCount() {
		int count = employeeDao.getCount();
		int limit = EmployeeDao.LIMIT;
		int pageCount = (int)(Math.ceil((double)count/limit));
		return pageCount;
	}
		
	// 設定 base model attr value
	private void setBaseModelAttribute(Model model, HttpSession session) {
		// 取得目前使用者所在的分頁
		String sessionNum = session.getAttribute("num") + "";
		// 目前總頁數
		int pageCount = getPageCount();
		if(sessionNum.length() > 0 && !sessionNum.equals("null")) {
			int num = Integer.parseInt(sessionNum);
			// 判斷 session 中的 num 是否大於實際總頁數
			if(num > pageCount) {
				num = pageCount;
				session.setAttribute("num", num);
			}
			model.addAttribute("employees", employeeDao.queryPage(num));
		} else {
			model.addAttribute("employees", employeeDao.query());
		}
		model.addAttribute("jobs", jobDao.query());
		model.addAttribute("pageCount", pageCount);
	}
	
}
