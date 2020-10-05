package com.nt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.nt.dto.Employee;
import com.nt.maper.RowMaperDao;

public class EmployeeDaoImpl implements EmployeeDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public String addEmployee(Employee employee) {
		String status = null;
		List<Employee> list = jdbcTemplate.query("select * from employee where eid=" + employee.getEid(),
				
				//Lambda Expression Java8 Feature
				(ResultSet rs, int arg1) -> {
			Employee employee1=new Employee();
			employee1.setEid(rs.getInt("eid"));
			employee1.setEname(rs.getString("ename"));
			employee1.setEsal(rs.getFloat("esal"));
			employee1.setEaddr(rs.getString("eaddr"));
			return employee1;
		});
		if (list.isEmpty() == true) {
			int rowcount = jdbcTemplate.update("insert into employee values(" + employee.getEid() + ",'"
					+ employee.getEname() + "'," + employee.getEsal() + ",'" + employee.getEaddr() + "')");
			if (rowcount == 1) {
				status = "Employee Insertion Success.. ";

			}

			else {
				status = "Employee Insertion Failure";
			}

		} else {
			status = "Employee Already Existed...";

		}
		return status;
	}

	@Override
	public Employee searchEmployee(int eid) {
		Employee employee = null;
		List<Employee> list = jdbcTemplate.query("select * from employee where eid=" + eid, new RowMaperDao());
		if (list.isEmpty() == true) {
			employee = null;
		} else {
			employee = list.get(0);

		}

		return employee;
	}

	@Override
	public String updateEmployee(Employee employee) {
	String status=null;
	List<Employee> employee1=jdbcTemplate.query("select * from employee where eid="+employee.getEid(), new RowMaperDao());
	
		int rowCount=jdbcTemplate.update("update employee set ename='"+employee.getEname()+"',esal="+employee.getEsal()+",  eaddr='"+employee.getEaddr()+"' where eid="+employee.getEid());
	if (rowCount==1) {
		status="Employee Updation Success..";
		
	}
	else {
		status="Employee Updation Failure";
	}
	
	
	
		return status;
	}

	@Override
	public String deleteEmployee(int eid) {
		String status = null;
		Employee employee = searchEmployee(eid);
		if (employee == null) {
			status = "Employee Not Existed";

		} else {
			int rowcount = jdbcTemplate.update("delete from employee where eid=" + eid);
			if (rowcount == 1) {
				status = "Employee Deleted";

			} else {
				status = "Employee Deletion Failure..";
			}
		}

		return status;
	}

}
