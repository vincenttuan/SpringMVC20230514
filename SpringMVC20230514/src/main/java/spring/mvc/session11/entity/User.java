package spring.mvc.session11.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
	private String name; // 姓名
	private Integer age; // 年齡
	
	// 為了要滿足前端HTML支援的日期格式(yyyy-MM-dd)
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 設定請求的日期格式
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") // 設定回應的日期格式
	private Date birth; // 生日
	
	private String education; // 教育程度
	private String sex; // 性別
	private String[] interest; // 興趣
	private String resume; // 履歷
	
	public User() {
		
	}

	public User(String name, String birthStr, String education, String sex, String[] interest, String resume) {
		this.name = name;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.birth = sdf.parse(birthStr);
			
			// 根據 birthStr 計算現在年齡
			LocalDate birth = LocalDate.parse(birthStr);
			LocalDate today = LocalDate.now();
			// 利用 Period 計算二個 LocalDate 之間的差距
			Period period = Period.between(birth, today);
			// 計算出年齡
			int yearsOld = period.getYears();
			// 將年齡覆值給 this.age
			this.age = yearsOld;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.education = education;
		this.sex = sex;
		this.interest = interest;
		this.resume = resume;
	}

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

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String[] getInterest() {
		return interest;
	}

	public void setInterest(String[] interest) {
		this.interest = interest;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", birth=" + birth + ", education=" + education + ", sex=" + sex
				+ ", interest=" + Arrays.toString(interest) + ", resume=" + resume + "]";
	}
	
	
}
