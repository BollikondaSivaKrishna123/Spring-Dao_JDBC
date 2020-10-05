package com.nt.dao;
import java.sql.ResultSet;
import java.util.List;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.nt.dto.Employee;

import com.nt.maper.RowMaperDao;

public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {

	/*
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
*/
	@Override
	public String addEmployee(Employee employee) {
		String status = null;
		String query = "select * from  employee where eid=?";
				//DaoSupport Classes provide Respective XXXTemplate Object.
				//To get Template Objects, we Can  use getXXXTemplate()
				//public void XXXTemplate getXXXTemplate
		
		List<Employee> list = getJdbcTemplate().query(query,new Object[] {employee.getEid()},

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
			String query1 = "insert into employee values(?,?,?,?)";
			

			int rowcount =getJdbcTemplate().update(query1,new Object[] {employee.getEid(),employee.getEname(),employee.getEsal(),employee.getEaddr()});
			
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
		String query3 = "select * from employee where eid="+eid;
		
		//DaoSupport Classes provide Respective XXXTemplate Object.
		//To get Template Objects, we Can  use getXXXTemplate()
		//public void XXXTemplate getXXXTemplate()
		
		
		List<Employee> list =getJdbcTemplate().query(query3, new RowMaperDao());
		
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
		
		String query = "update employee set ename=?,esal=?,eaddr=? where eid=?";
	
		int rowCount = getJdbcTemplate().update(query,new Object[] {employee.getEname(),employee.getEsal(),employee.getEaddr(),employee.getEid()});
		
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
			String query = "delete from employee where eid=?";
			int rowcount =getJdbcTemplate().update(query,new Object[]{eid});
			if (rowcount == 1) {
				status = "Employee Deleted";

			} else {
				status = "Employee Deletion Failure..";
			}
		}

		return status;
	}

}
