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

import spring.mvc.session17.entity.Author;
import spring.mvc.session17.entity.Book;

@Controller
@RequestMapping("/session17/book_author")
public class BookAndAuthorController {
	private List<Book> books = new ArrayList<>();
	private List<Author> authors = new ArrayList<>();
	
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("books", books);
		model.addAttribute("authors", authors);
		return "session17/book_author";
	}
	
	@PostMapping("/")
	public String add(@ModelAttribute("a") Author author, @ModelAttribute("b") Book book) {
		books.add(book);
		authors.add(author);
		
		return "redirect:./";
	}
	
}
 