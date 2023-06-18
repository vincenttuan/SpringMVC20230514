package spring.mvc.session15.repository;

import java.util.List;

import spring.mvc.session15.entity.Employee;

public interface EmployeeDao {
	// 每頁筆數
	int LIMIT = 5;
	
	// 新增單筆
	int add(Employee emp);
	
	// 修改單筆
	int update(Employee emp);
	
	// 刪除單筆
	int delete(Integer eid);
	
	// 單筆查詢
	Employee get(Integer eid);
	
	// 單筆查詢-目前所有資料筆數
	int getCount();
	
	// 多筆查詢-不分頁
	List<Employee> query();
	
	// 多筆查詢-分頁
	List<Employee> queryPage(int pageNo);
}
