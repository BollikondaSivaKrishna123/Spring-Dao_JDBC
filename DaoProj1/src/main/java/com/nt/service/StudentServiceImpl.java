package com.nt.service;

import com.nt.dao.StudentDao;
import com.nt.dto.Student;
import com.nt.factory.StudentDaoFactory;

import sun.print.resources.serviceui;

public class StudentServiceImpl implements StudentService {

	StudentDao studentdao=StudentDaoFactory.getStudentDao();
	@Override
	public String addStudent(Student std) {
		String status=studentdao.add(std);
		return status;
	}

	@Override
	public Student searchStudent(String sid) {
		Student student=studentdao.search(sid);
		return student;
	}

	@Override
	public String deleteStudent(String sid) {
	String status=studentdao.delete(sid);
		return status;
	}

}
