package com.nt.maper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.nt.dto.Employee;

public class RowMaperDao implements RowMapper<Employee> {
	@Override
	public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
		Employee employee=new Employee();
		employee.setEid(rs.getInt("eid"));
		employee.setEname(rs.getString("ename"));
		employee.setEsal(rs.getFloat("esal"));
		employee.setEaddr(rs.getString("eaddr"));
		return employee;
	}
	

}
