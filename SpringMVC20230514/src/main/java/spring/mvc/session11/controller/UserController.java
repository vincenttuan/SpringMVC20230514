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
		
		users.add(new User("Vincent", "2010-05-01" ));
	}
	
	
}
