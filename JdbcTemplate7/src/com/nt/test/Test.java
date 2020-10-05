package com.nt.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Struct;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nt.dao.EmployeeDao;
import com.nt.dto.Employee;

public class Test {

	public static void main(String[] args) throws Exception {

		
	
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"/com/nt/resources/applicationContext.xml");
		EmployeeDao employeeDao = (EmployeeDao) applicationContext.getBean("empdao");
		// Employee employee = new Employee();
		int eid;
		String ename;
		float esal;
		String eaddr;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println();
			System.out.println("1. ADD EMPLOYEE");
			System.out.println("2. SEARCH EMPLOYEE");
			System.out.println("3. UPDATE EMPLOYEE");
			System.out.println("4. DELETE EMPLOYEE");
			System.out.println("5. EXIT ");
			System.out.println("Enter Your Option : ");
			int option = Integer.parseInt(bufferedReader.readLine());
			switch (option) {
			case 1:
				System.out.println();
				System.out.println("Enter Employee ID :");
				eid = Integer.parseInt(bufferedReader.readLine());
				System.out.println("Enter Employee Name :");
				ename = bufferedReader.readLine();
				System.out.println("Enter Employee Salary : ");
				esal = Float.parseFloat(bufferedReader.readLine());
				System.out.println("Enter Employee Address :");
				eaddr = bufferedReader.readLine();
				Employee employee1 = new Employee();
				employee1.setEid(eid);
				employee1.setEname(ename);
				employee1.setEsal(esal);
				employee1.setEaddr(eaddr);
				String status = employeeDao.addEmployee(employee1);
				System.out.println("--------------------------------------------");
				System.out.println(status);
				System.out.println("----------------------------------------------");
				break;
			case 2:
				System.out.println();
				System.out.println("Enter Employee ID : ");
				eid = Integer.parseInt(bufferedReader.readLine());
				Employee employee = employeeDao.searchEmployee(eid);
				if (employee == null) {
					System.out.println("Employee Not Existed...");

				} else {
					System.out.println("======Employee Details Are : ======");
					System.out.println("Employee Id 					: " + employee.getEid());
					System.out.println("Employee Name 				: " + employee.getEname());
					System.out.println("Emploee Salary 				: " + employee.getEsal());
					System.out.println("Employee Address				: " + employee.getEaddr());

				}

				break;
			case 3:

				System.out.println("Enter Employee ID : ");
				eid = Integer.parseInt(bufferedReader.readLine());
				Employee employee3 = employeeDao.searchEmployee(eid);
				if (employee3 == null) {
					System.out.println("Employee   Not Existed...");

				} else {
					System.out.println("Employee ID 								 : " + employee3.getEid());
					System.out.println("Enter Employee Name[Old Name:" + employee3.getEname() + "]    		 : ");
					ename = bufferedReader.readLine();
					System.out.println("Enter Employee Salary[Old Salary:" + employee3.getEsal() + "]  			 : ");
					esal = Float.parseFloat(bufferedReader.readLine());
					System.out.println("Enter Employee Address[Old Address:" + employee3.getEaddr() + "]    : ");
					eaddr = bufferedReader.readLine();
					Employee employee2 = new Employee();
					employee2.setEid(eid);
					employee2.setEname(ename);
					employee2.setEsal(esal);
					employee2.setEaddr(eaddr);
					String status1 = employeeDao.updateEmployee(employee2);
					System.out.println(status1);

				}

				break;
			case 4:
				System.out.println();
				System.out.println("Enter Employee ID : ");
				eid = Integer.parseInt(bufferedReader.readLine());
				String status2 = employeeDao.deleteEmployee(eid);
				System.out.println(status2);

				break;
			case 5:
				System.out.println("*****Thanks for Using this Application*******");
				System.exit(0);
				break;

			default:
				System.out.println("Enter the Options[1,2,3,4,5] Only");
				break;
			}

		}

		/*
		 * employee.setEid(999); employee.setEname("Siva"); employee.setEsal(1750.0f);
		 * employee.setEaddr("Ryd"); String status=employeeDao.addEmployee(employee);
		 * System.out.println(status);
		 */

		/*
		 * employee=employeeDao.searchEmployee("111"); if (employee==null) {
		 * System.out.println("Employee Not Existed...");
		 * 
		 * } else { System.out.println("=======Employee  Details========");
		 * System.out.println("Employee Id            :"+employee.getEid());
		 * System.out.println("Employee Name     :"+employee.getEname());
		 * System.out.println("Employee  Salary    :"+employee.getEsal());
		 * System.out.println("Employee Address  :"+employee.getEaddr());
		 * 
		 * }
		 */

		/*
		 * String status=employeeDao.deleteEmployee("111"); System.out.println(status);
		 */
		/*
		 * employee.setEid(222); employee.setEname("Virat"); employee.setEsal(0050.0f);
		 * employee.setEaddr("Ben"); String status=employeeDao.updateEmployee(employee);
		 * System.out.println(status);
		 */
	}

}
