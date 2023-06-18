package spring.mvc.session15.controller;

import javax.servlet.http.HttpSession;

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
	
	@GetMapping("/")
	public String index(@ModelAttribute Job job, Model model, HttpSession session)  {
		model.addAttribute("_method", "POST");
		setBaseModelAttribute(model, session);
		return "session15/job";
	}
	
	@GetMapping("/page/{pageNo}")
	public String page(@PathVariable("pageNo") int pageNo, @ModelAttribute Job job, Model model, HttpSession session) {
		if(pageNo < 0) {
			session.setAttribute("num", ""); // 將 session 內容清空
			return "redirect:../";
		}
		session.setAttribute("num", pageNo); // 存入目前使用者所在 page
		model.addAttribute("_method", "POST");
		setBaseModelAttribute(model, session);
		return "session15/job";
	}
	
	@GetMapping("/{jid}")
	public String get(@PathVariable("jid") Integer jid, Model model, HttpSession session) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("job", jobDao.get(jid));
		setBaseModelAttribute(model, session);
		return "session15/job";
	}
	
	// 取得總頁數
	private int getPageCount() {
		int count = jobDao.getCount();
		int limit = JobDao.LIMIT;
		int pageCount = (int)(Math.ceil((double)count/limit));
		return pageCount;
	}
	
	// 設定 base model attr value
	private void setBaseModelAttribute(Model model, HttpSession session) {
		// 取得目前使用者所在的分頁
		String sessionNum = session.getAttribute("num") + "";
		if(sessionNum.length() > 0 && !sessionNum.equals("null")) {
			int num = Integer.parseInt(sessionNum);
			model.addAttribute("jobs", jobDao.queryPage(num));
		} else {
			model.addAttribute("jobs", jobDao.query());
		}
		model.addAttribute("employees", employeeDao.query());
		model.addAttribute("pageCount", getPageCount());
	}
}
