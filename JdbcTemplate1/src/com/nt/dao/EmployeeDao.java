package com.nt.dao;

import com.nt.dto.Employee;

public interface EmployeeDao {
public String addEmployee(Employee employee);
public Employee searchEmployee(String eid);
public String updateEmployee(Employee employee);
public String deleteEmployee(String eid);

}
