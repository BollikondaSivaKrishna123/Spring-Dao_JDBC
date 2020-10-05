package com.nt.dao;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.nt.dto.Employee;

import com.nt.maper.RowMaperDao;

public class EmployeeDaoImpl extends NamedParameterJdbcDaoSupport implements EmployeeDao {

	/*
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
*/
	private DataSource ds;
	public void setDs(DataSource ds) {
		this.ds = ds;
		
	}
	@Override
	public String addEmployee(Employee employee) {
		String status = null;
		String query = "select * from  employee where eid=:eid";
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("eid",employee.getEid());
				//DaoSupport Classes provide Respective XXXTemplate Object.
				//To get Template Objects, we Can  use getXXXTemplate()
				//public void XXXTemplate getXXXTemplate
		
		List<Employee> list = getNamedParameterJdbcTemplate().query(query,map,

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
			Map<String,Object> map2=new HashMap<String, Object>();
			map2.put("eid",employee.getEid());
			map2.put("ename",employee.getEname());
			map2.put("esal",employee.getEsal());
			map2.put("eaddr",employee.getEaddr());
			int rowcount =getNamedParameterJdbcTemplate().update(query1,map2);
			
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
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("eid",eid);
		//DaoSupport Classes provide Respective XXXTemplate Object.
		//To get Template Objects, we Can  use getXXXTemplate()
		//public void XXXTemplate getXXXTemplate()
		
		
		List<Employee> list =getNamedParameterJdbcTemplate().query(query3,map,new RowMaperDao());
		
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
		
		String query = "update employee set ename=:ename,esal=:esal,eaddr=:eaddr where eid=:eid";
	
		Map<String,Object> map3=new HashMap<String, Object>();
		map3.put("eid",employee.getEid());
		map3.put("ename",employee.getEname());
		map3.put("esal",employee.getEsal());
		map3.put("eaddr",employee.getEaddr());
		int rowCount =getNamedParameterJdbcTemplate().update(query,map3);
		
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
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("eid",eid);
			int rowcount =getNamedParameterJdbcTemplate().update(query,map);
			if (rowcount == 1) {
				status = "Employee Deleted";

			} else {
				status = "Employee Deletion Failure..";
			}
		}

		return status;
	}

}
