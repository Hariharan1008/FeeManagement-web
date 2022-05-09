package com.hari.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hari.logic.RegisterValidator;
import com.hari.model.Registration;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String userName=request.getParameter("userName");
		String mobile=request.getParameter("mobile");
		String age=request.getParameter("age");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		Registration registration=new Registration();
		
		registration.setName(name);
		registration.setUserName(userName);
		registration.setUserMobileNumber(mobile);
		int userAge=Integer.parseInt(age);
		registration.setAge(userAge);
		registration.setUserEmail(email);
		registration.setUserPassword(password);
		PrintWriter out=response.getWriter();
		String message;
		try
		{
			int result=RegisterValidator.validatingRegistration(registration);
			if(result==1)
			{
				message="successfull";
			}
			else
			{
				message="failure";
			}
			
		}
		catch(Exception e)
		{
			message=e.getMessage();
		}
		Gson gson=new Gson();
		String json=gson.toJson(message);
		System.out.println(json);
		out.println(json);
		out.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
