package spring.mvc.session15.entity;

import javax.validation.constraints.Size;

public class Job {
	
	// 資料表的主鍵(PK)
	private Integer jid;
	
	@Size(min = 2, max = 50, message = "{job.jname.size}")
	private String jname;
	
	// 資料表的外鍵(FK)
	private Integer eid;
	
	// 手動加入: 一個工作會對應到一個員工
	private Employee employee;

	public Integer getJid() {
		return jid;
	}

	public void setJid(Integer jid) {
		this.jid = jid;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Job [jid=" + jid + ", jname=" + jname + ", eid=" + eid + ", employee=" + employee + "]";
	}
	
	
}
