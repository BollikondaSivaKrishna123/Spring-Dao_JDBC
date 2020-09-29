package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nt.dto.Student;
import com.nt.factory.ConnectionFactory;

public class StudentDaoImpl implements StudentDao {

	Connection con=ConnectionFactory.getConnection();
	@Override
	public String add(Student std) {
		String status=null;
		try {
			PreparedStatement pStatement=con.prepareStatement("select * from student where sid=?");
			pStatement.setString(1,std.getSid());
			ResultSet r=pStatement.executeQuery();
			boolean b=r.next();
			if (b==true) {
				status="existed";
				
			}
			else {
				PreparedStatement preparedStatement=con.prepareStatement("insert into student values(?,?,?)");
				preparedStatement.setString(1, std.getSid());
				preparedStatement.setString(2, std.getSname());
				preparedStatement.setString(3,std.getSaddr());
				
				int rowcount=preparedStatement.executeUpdate();
				if (rowcount==1) {
					status="success";
					
					
				}
				else {
					status="failure";
				}
				
			
			}
			
		} catch (Exception e) {
			status="failure";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Student search(String sid) {
		Student student=null;
	try {
		
		PreparedStatement pStatement=con.prepareStatement("select * from student where sid=?");
		pStatement.setString(1,sid);
		ResultSet rSet=pStatement.executeQuery();
		boolean b=rSet.next();
		if (b==true) {
			student=new Student();
			student.setSid(rSet.getString(1));
			student.setSname(rSet.getString(2));
			student.setSaddr(rSet.getString(3));
			return student;
		}
		else {
			student=null;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
		return student;
	}

	@Override
	public String delete(String sid) {
		String status=null;
	try {
		PreparedStatement pStatement=con.prepareStatement("select * from student where sid=?");
		pStatement.setString(1,sid);
		ResultSet r=pStatement.executeQuery();
		boolean b=r.next();
		if (b==true) {
		
		PreparedStatement preparedStatement=con.prepareStatement("delete from student where sid=? ");
		preparedStatement.setString(1,sid);
		int rowcount=preparedStatement.executeUpdate();
		if (rowcount==1) {
			status="success";
			
		}
		else {
			status="failure";
		}
		}
		else {
			status="notexisted";
		}
		
	} catch (Exception e) {
	e.printStackTrace();
	}
		return  status;
	}

}
