package spring.mvc.session17.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session17.entity.Master;
import spring.mvc.session17.entity.Product;

@Controller
@RequestMapping("/session17/product_master")
public class ProductAndMasterController {
	private List<Product> products = new ArrayList<>();
	private List<Master> masters = new ArrayList<>();
	
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("products", products);
		model.addAttribute("masters", masters);
		return "session17/product_master";
	}
	
	@PostMapping("/")
	public String add(@ModelAttribute("a") Product product, @ModelAttribute("b") Master master) {
		products.add(product);
		masters.add(master);
		
		return "redirect:./";
	}
	
}
 