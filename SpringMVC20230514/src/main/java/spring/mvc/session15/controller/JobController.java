package spring.mvc.session15.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	// 首頁
	@GetMapping("/")
	public String index(@ModelAttribute Job job, Model model, HttpSession session)  {
		model.addAttribute("_method", "POST");
		setBaseModelAttribute(model, session);
		return "session15/job";
	}
	
	// 分頁查詢
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
	
	// 單筆查詢
	@GetMapping("/{jid}")
	public String get(@PathVariable("jid") Integer jid, Model model, HttpSession session) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("job", jobDao.get(jid));
		setBaseModelAttribute(model, session);
		return "session15/job";
	}
	
	// 新增 Job
	@PostMapping("/")
	public String add(@ModelAttribute @Valid Job job, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "POST");
			setBaseModelAttribute(model, session);
			return "session15/job";
		}
		// 新增資料
		jobDao.add(job);
		
		// 判斷使用者是否在分頁狀態
		String sessionNum = session.getAttribute("num") + "";
		if(sessionNum.length() > 0) {
			// 設定目前最大的 num
			session.setAttribute("num", getPageCount());
		}
		return "redirect:./";
	}
	
	// 修改 Job
	@PutMapping("/")
	public String update(@ModelAttribute @Valid Job job, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "PUT");
			setBaseModelAttribute(model, session);
			return "session15/job";
		}
		// 修改
		jobDao.update(job);
		return "redirect:./";
	}
	
	// 刪除 Job
	@DeleteMapping("/")
	public String delete(Job job, HttpSession session) {
		Integer jid = job.getJid(); 
		jobDao.delete(jid);
		
		try {
			int num = Integer.parseInt(session.getAttribute("num") + "");
			int pageCount = getPageCount();
			if(num > pageCount) {
				session.setAttribute("num", pageCount);
			}
		} catch (Exception e) {
			
		}
		
		return "redirect:./";
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
		// 目前總頁數
		int pageCount = getPageCount();
		if(sessionNum.length() > 0 && !sessionNum.equals("null")) {
			int num = Integer.parseInt(sessionNum);
			// 判斷 session 中的 num 是否大於實際總頁數
			if(num > pageCount) {
				num = pageCount;
				session.setAttribute("num", num);
			}
			model.addAttribute("jobs", jobDao.queryPage(num));
		} else {
			model.addAttribute("jobs", jobDao.query());
		}
		model.addAttribute("employees", employeeDao.query());
		model.addAttribute("pageCount", pageCount);
	}
}
