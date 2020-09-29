package com.nt.factory;

import com.nt.dao.StudentDao;
import com.nt.dao.StudentDaoImpl;

public class StudentDaoFactory {
private static StudentDao studentDao;
static
{
	studentDao=new StudentDaoImpl();
}

	public static  StudentDao getStudentDao()
	{
		return studentDao;
	}
}
