package com.nt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.nt.dto.Employee;
import com.nt.maper.RowMaperDao;

public class EmployeeDaoImpl implements EmployeeDao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public String addEmployee(Employee employee) {
		String status = null;
		String query = "select * from  employee where eid=:eid";
		// Map<String, Object> map=new HashMap<String, Object>();
		// map.put("eid", employee.getEid());

		SqlParameterSource mapSqlParameterSource = new MapSqlParameterSource().addValue("eid", employee.getEid());

		List<Employee> list = namedParameterJdbcTemplate.query(query, mapSqlParameterSource,

				// Lambda Expression Java8 Feature
				(ResultSet rs, int arg1) -> {
					Employee employee1 = new Employee();
					employee1.setEid(rs.getInt("eid"));
					employee1.setEname(rs.getString("ename"));
					employee1.setEsal(rs.getFloat("esal"));
					employee1.setEaddr(rs.getString("eaddr"));
					return employee1;
				});
		if (list.isEmpty() == true) {
			String query1 = "insert into employee values(:eid,:ename,:esal,:eaddr)";
			SqlParameterSource mapSqlParameterSource2 = new MapSqlParameterSource().addValue("eid", employee.getEid())
					.addValue("ename", employee.getEname()).addValue("esal", employee.getEsal())
					.addValue("eaddr", employee.getEaddr());

			int rowcount = namedParameterJdbcTemplate.update(query1, mapSqlParameterSource2);
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
		String query3 = "select * from employee where eid=:eid";
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("eid", eid);
		SqlParameterSource mapSqlParameterSource = new MapSqlParameterSource().addValue("eid",eid);

		List<Employee> list = namedParameterJdbcTemplate.query(query3, mapSqlParameterSource, new RowMaperDao());
		if (list.isEmpty() == true) {
			employee = null;
		} else {
			employee = list.get(0);

		}

		return employee;
	}

	@Override
	public String updateEmployee(Employee employee) {
		String status = null;
		// List<Employee> employee1=jdbcTemplate.query("select * from employee where
		// eid=?",new Object[] {employee.getEid()}, new RowMaperDao());

		String query = "update employee set ename=:ename,esal=:esal,eaddr=:eaddr where eid=:eid";
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("ename", employee.getEname());
//		map.put("esal", employee.getEsal());
//		map.put("eaddr", employee.getEaddr());
//		map.put("eid", employee.getEid());

		SqlParameterSource mapSqlParameterSource = new MapSqlParameterSource().addValue("eid", employee.getEid())
				.addValue("ename", employee.getEname()).addValue("esal", employee.getEsal())
				.addValue("eaddr", employee.getEaddr());

		int rowCount = namedParameterJdbcTemplate.update(query, mapSqlParameterSource);
		if (rowCount == 1) {
			status = "Employee Updation Success..";

		} else {
			status = "Employee Updation Failure";
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
			String query = "delete from employee where eid=:eid";
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("eid", eid);
			SqlParameterSource mapSqlParameterSource = new MapSqlParameterSource().addValue("eid", employee.getEid());

			int rowcount = namedParameterJdbcTemplate.update(query, mapSqlParameterSource);
			if (rowcount == 1) {
				status = "Employee Deleted";

			} else {
				status = "Employee Deletion Failure..";
			}
		}

		return status;
	}

}
