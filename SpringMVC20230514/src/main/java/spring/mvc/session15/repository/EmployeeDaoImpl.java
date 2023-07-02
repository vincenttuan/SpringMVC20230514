package spring.mvc.session15.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.mvc.session15.entity.Employee;
import spring.mvc.session15.entity.Job;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private JobDao jobDao;
	
	@Override
	public int add(Employee emp) {
		String sql = SQLUtil.ADD_EMPLOYEE_SQL;
		return jdbcTemplate.update(sql, emp.getEname(), emp.getSalary());
	}

	@Override
	public int update(Employee emp) {
		String sql = SQLUtil.UPT_EMPLOYEE_SQL;
		return jdbcTemplate.update(sql, emp.getEname(), emp.getSalary(), emp.getEid());
	}

	@Override
	public int delete(Integer eid) {
		String sql = SQLUtil.DEL_EMPLOYEE_SQL;
		return jdbcTemplate.update(sql, eid);
	}

	@Override
	public Employee get(Integer eid) {
		String sql = SQLUtil.GET_EMPLOYEE_SQL;
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Employee>(Employee.class), eid);
	}

	@Override
	public int getCount() {
		String sql = SQLUtil.COUNT_EMPLOYEE_SQL;
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public List<Employee> query() {
		String sql = SQLUtil.QUERY_EMPLOYEE_SQL;
		// 自行將每一筆紀錄的 Employee 組合 Job
		RowMapper<Employee> rm = (rs, rowNum) -> {
			Employee employee = new Employee();
			employee.setEid(rs.getInt("eid"));
			employee.setEname(rs.getString("ename"));
			employee.setSalary(rs.getInt("salary"));
			employee.setCreatetime(rs.getTimestamp("createtime"));
			
			// 查詢該筆員工有哪些工作 ?
			List<Job> jobs = jobDao.queryByEid(employee.getEid());
			
			// 組合 Jobs
			employee.setJobs(jobs);
			return employee;
		};
		
		return jdbcTemplate.query(sql, rm);
	}

	@Override
	public List<Employee> queryPage(int pageNo) {
		// offset: 要從哪一筆紀錄開始查詢
		int offset = (pageNo - 1) * LIMIT;
		String sql = SQLUtil.QUERY_PAGE_EMPLOYEE_SQL;
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Employee>(Employee.class), LIMIT, offset);
	}

}
