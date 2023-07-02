package spring.mvc.session15.repository;

import java.util.List;

import spring.mvc.session15.entity.Job;

public interface JobDao {
	// 每頁筆數
	int LIMIT = 5;
	
	// 新增單筆
	int add(Job job);
	
	// 修改單筆
	int update(Job job);
	
	// 刪除單筆
	int delete(Integer jid);
	
	// 單筆查詢
	Job get(Integer jid);
	
	// 單筆查詢-目前所有資料筆數
	int getCount();
	
	// 多筆查詢-不分頁
	List<Job> query();
	
	// 多筆查詢-分頁
	List<Job> queryPage(int pageNo);
	
	// 多筆查詢-根據 eid 查詢工作
	List<Job> queryByEid(Integer eid);
}
