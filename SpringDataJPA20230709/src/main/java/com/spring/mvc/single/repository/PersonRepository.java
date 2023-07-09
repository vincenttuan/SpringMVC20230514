package com.spring.mvc.single.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.mvc.single.entity.Person;

/*
 * 方法命名規則
 * 1. 查詢方法以 find | read | get 開頭
 * 2. 涉及條件查詢時，條件的屬性（首字母大寫）用條件關鍵字（例如：And、Or）連結
 * */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	// 預設會有 CRUD
	// 自訂其他查找方法
	// 1. 根據 name 來取得 Person: Where name = ?
	List<Person> getByName(String name);
	
	// 2. name 是 XX 的開頭以及 id >= 值
	// Where name like ? and id >= ?
	List<Person> getByNameStartingWithAndIdGreaterThenEqual(String name, Long id);
	
	// 3. Where id in (?, ?, ?, ...)
	List<Person> getByIdIn(List<Long> ids);
	
	// 4. Where birth < ?
	List<Person> getByBirthLessThen(Date birth);
	
	// 5. Where birth between ?(含) and ?(含)
	List<Person> getByBirthBetween(Date birthBegin, Date birthEnd);
	
	// 6. 自訂 sql 查詢
	@Query(nativeQuery = true, value = "select id, name, password, birth from person where id >= 1? and id <= 2?")
	List<Person> findPerson(Long beginId, Long endId);
}



