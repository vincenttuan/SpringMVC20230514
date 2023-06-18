package spring.mvc.session15.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.mvc.session15.entity.Job;

@Repository
public class JobDaoImpl implements JobDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int add(Job job) {
		String sql = SQLUtil.ADD_JOB_SQL;
		return jdbcTemplate.update(sql, job.getJname(), job.getEid());
	}

	@Override
	public int update(Job job) {
		String sql = SQLUtil.UPT_JOB_SQL;
		return jdbcTemplate.update(sql, job.getJname(), job.getEid(), job.getJid());
	}

	@Override
	public int delete(Integer jid) {
		String sql = SQLUtil.DEL_JOB_SQL;
		return jdbcTemplate.update(sql, jid);
	}

	@Override
	public Job get(Integer jid) {
		String sql = SQLUtil.GET_JOB_SQL;
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Job>(Job.class), jid);
	}

	@Override
	public int getCount() {
		String sql = SQLUtil.COUNT_JOB_SQL;
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public List<Job> query() {
		String sql = SQLUtil.QUERY_JOB_SQL; // 不分頁查詢
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Job>(Job.class));
	}

	@Override
	public List<Job> queryPage(int pageNo) {
		// offset: 要從哪一筆紀錄開始查詢
		int offset = (pageNo - 1) * LIMIT;
		String sql = SQLUtil.QUERY_PAGE_JOB_SQL;
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Job>(Job.class), LIMIT, offset);
	}
	
	
}
