package spring.mvc.session11.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session11.entity.User;

@Controller
@RequestMapping("/session11/user")
public class UserController {
	
	private List<User> users = new CopyOnWriteArrayList<>();
	// 預設資料
	{	
		users.add(new User("Vincent", "2010-05-01", "大學", "男", new String[] {"飛控"}, "Test1"));
		users.add(new User("Mary", "2012-06-03", "高中", "女", new String[] {"爬山","看書"}, "Test2"));
		users.add(new User("Helen", "2009-08-05", "研究所", "女", new String[] {"爬山"}, "Test3"));
		users.add(new User("Jack", "2012-09-07", "大學", "男", new String[] {"爬山", "打球"}, "Test4"));
		users.add(new User("Rose", "2011-12-09", "高中", "女", null, "Test5"));
	}
	
	
}
