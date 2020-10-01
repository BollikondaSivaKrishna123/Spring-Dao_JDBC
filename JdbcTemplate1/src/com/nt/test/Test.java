package com.nt.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nt.dao.EmployeeDao;
import com.nt.dto.Employee;

public class Test {

	public static void main(String[] args) {
	ApplicationContext applicationContext=new ClassPathXmlApplicationContext("/com/nt/resources/applicationContext.xml");
	EmployeeDao employeeDao=(EmployeeDao)applicationContext.getBean("empdao");
	Employee employee=new Employee();
	
	employee.setEid(444);
	employee.setEname("Siva");
	employee.setEsal(1750.0f);
	employee.setEaddr("Ryd");
	String status=employeeDao.addEmployee(employee);
	System.out.println(status);
	
	
	
	/*
	employee=employeeDao.searchEmployee("111");
	if (employee==null) {
		System.out.println("Employee Not Existed...");
		
	}
	else {
		System.out.println("=======Employee  Details========");
		System.out.println("Employee Id            :"+employee.getEid());
		System.out.println("Employee Name     :"+employee.getEname());
		System.out.println("Employee  Salary    :"+employee.getEsal());
		System.out.println("Employee Address  :"+employee.getEaddr());
		
	}
	*/
	
	
	
	/*
	String status=employeeDao.deleteEmployee("111");
	System.out.println(status);
	*/
	/*
	employee.setEid(222);
	employee.setEname("Virat");
	employee.setEsal(0050.0f);
	employee.setEaddr("Ben");
	String status=employeeDao.updateEmployee(employee);
	System.out.println(status);
	*/
	}


}
