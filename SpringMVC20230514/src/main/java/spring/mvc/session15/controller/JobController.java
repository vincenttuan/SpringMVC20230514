package spring.mvc.session15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session15.entity.Job;
import spring.mvc.session15.repository.EmployeeDao;
import spring.mvc.session15.repository.JobDao;

@Controller
@RequestMapping("/session15/job")
public class JobController {
	
	@Autowired
	private JobDao jobDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	// 取得總頁數
	private int getPageCount() {
		int count = jobDao.getCount();
		int limit = JobDao.LIMIT;
		int pageCount = (int)(Math.ceil((double)count/limit));
		return pageCount;
	}
	
	@GetMapping("/")
	public String index(@ModelAttribute Job job, Model model)  {
		model.addAttribute("_method", "POST");
		
		model.addAttribute("employees", employeeDao.query());
		model.addAttribute("jobs", jobDao.query());
		model.addAttribute("pageCount", getPageCount());
		return "session15/job";
	}
	
	@GetMapping("/page/{pageNo}")
	public String page(@PathVariable("pageNo") int pageNo, @ModelAttribute Job job, Model model) {
		if(pageNo < 0) {
			return "redirect:../";
		}
		model.addAttribute("_method", "POST");
		
		model.addAttribute("employees", employeeDao.query());
		model.addAttribute("jobs", jobDao.queryPage(pageNo));
		model.addAttribute("pageCount", getPageCount());
		return "session15/job";
	}
	
	@GetMapping("/{jid}")
	public String get(@PathVariable("jid") Integer jid, Model model) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("job", jobDao.get(jid));
		
		model.addAttribute("employees", employeeDao.query());
		model.addAttribute("jobs", jobDao.query());
		model.addAttribute("pageCount", getPageCount());
		return "session15/job";
	}
	
	
}
