package com.nt.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dto.Student;
import com.nt.factory.StudentServiceFactory;
import com.nt.service.StudentService;
@WebServlet("*.do")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher requestDispatcher=null;
		String reqString=request.getRequestURI();
		StudentService service=StudentServiceFactory.getStudentService();
		//System.out.println(reqString);
		if (reqString.endsWith("/addform.do")) {
			Student student=new Student();
			String sid=request.getParameter("sid");
			String sname=request.getParameter("sname");
			String saddr =request.getParameter("saddr");
			student.setSid(sid);
			student.setSname(sname);
			student.setSaddr(saddr);
			String status=service.addStudent(student);
			if (status.equals("success")) {
				requestDispatcher=request.getRequestDispatcher("success.html");
				requestDispatcher.forward(request, response);
				
				
			}
			if(status.equals("failure")){
				requestDispatcher=request.getRequestDispatcher("failure.html");
				requestDispatcher.forward(request, response);
			}
			
		if (status.equals("existed")) {
			requestDispatcher=request.getRequestDispatcher("existed.html");
			requestDispatcher.forward(request, response);
			}
			
			
		}
		if (reqString.endsWith("/searchform.do")) {
			String sid=request.getParameter("sid");
			Student std=service.searchStudent(sid);
			if (std==null) {
				requestDispatcher=request.getRequestDispatcher("notexisted.html");
				requestDispatcher.forward(request, response);
				
			}
			else {
				
			request.setAttribute("std", std);
			requestDispatcher=request.getRequestDispatcher("display.jsp");
			requestDispatcher.forward(request, response);
			
			}
			
			
		}
		if (reqString.endsWith("deleteform.do")) {
				String sid=request.getParameter("sid");
				String status=service.deleteStudent(sid);
				if (status.equals("success")) {
					requestDispatcher=request.getRequestDispatcher("success.html");
					requestDispatcher.forward(request, response);
					
				}
				if (status.equals("failure")) {
					
					requestDispatcher=request.getRequestDispatcher("failure.html");
					requestDispatcher.forward(request, response);
				}
if (status.equals("notexisted")) {
					
					requestDispatcher=request.getRequestDispatcher("notexisted.html");
					requestDispatcher.forward(request, response);
				}
			
		}
		
	}

}
