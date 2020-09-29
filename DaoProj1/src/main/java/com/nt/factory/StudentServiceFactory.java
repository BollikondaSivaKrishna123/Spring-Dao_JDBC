package com.nt.factory;

import com.nt.service.StudentService;
import com.nt.service.StudentServiceImpl;

public class StudentServiceFactory {
private static StudentService studentService;
static {
	studentService=new StudentServiceImpl();
}
	
public static StudentService getStudentService() {
	return studentService;
}
}
