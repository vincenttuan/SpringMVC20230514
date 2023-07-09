package com.spring.mvc.single.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "person")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 50, name = "name", nullable = false, unique = true)
	private String name;
	
	@Column(length = 50, name = "name", nullable = false)
	private String password;
	
	@Column
	@Temporal(TemporalType.DATE) // 存入資料表的格式
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 給 HTML Form 表單呈現的格式
	@JsonFormat(pattern = "yyyy-NN-dd") // 給 Json 字串呈現的格式
	private Date birth;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", password=" + password + ", birth=" + birth + "]";
	}
	
	
	
	
}
