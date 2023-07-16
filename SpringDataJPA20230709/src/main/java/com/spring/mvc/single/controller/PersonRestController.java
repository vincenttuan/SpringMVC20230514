package com.spring.mvc.single.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mvc.single.entity.Person;
import com.spring.mvc.single.repository.PersonRepository;

// 建立前後端分離 Controller
@RestController // 在每個方法中自動加上 @ResponseBody
@RequestMapping("/rest/person")
public class PersonRestController {
	
	@Autowired
	private PersonRepository personRepository;
	
	// 單筆查詢
	@GetMapping("/{id}")
	public Person get(@PathVariable("id") long id) {
		return personRepository.findOne(id);
	}
	
	// 多筆查詢
	@GetMapping("/")
	public List<Person> queryAll() {
		return personRepository.findAll();
	}
	
	// 新增
	@PostMapping("/")
	public String create(@RequestBody Person person) {
		personRepository.save(person);
		return "Add OK";
	}
	
	// 修改
	@PutMapping("/")
	public String update(@RequestBody Person person) {
		personRepository.save(person);
		return "Update OK";
	}
	
	// 刪除
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") long id) {
		personRepository.delete(id);
		return "Delete " + id + " OK";
	}
	
}
