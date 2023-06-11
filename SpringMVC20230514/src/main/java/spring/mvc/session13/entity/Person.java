package spring.mvc.session13.entity;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Person {
	
	@NotEmpty(message = "{person.name.empty}")
	@Size(min = 2, max = 50, message = "{person.name.size}")
	private String name; // 姓名
	
	@NotNull(message = "{person.age.null}")
	@Range(min = 18, max = 99, message = "{person.age.range}")
	private Integer age; // 年齡
	
	@NotNull(message = "{person.member.null}")
	private Boolean member; // 是否是會員
	
	@NotNull(message = "{person.birth.null}")
	@Past(message = "{person.birth.past}")
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 將網頁表單傳來的日期格式轉成 Date 物件
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") // Date 物件轉日期字串(當前端欲收到 json 字串的時候)
	private Date birth; // 生日

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getMember() {
		return member;
	}

	public void setMember(Boolean member) {
		this.member = member;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", member=" + member + ", birth=" + birth + "]";
	}
	
	
}
