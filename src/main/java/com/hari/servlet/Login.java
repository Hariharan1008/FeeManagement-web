package com.hari.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hari.Dao.LoginValidationUsingDatabase;
import com.hari.model.Session;

/**
 * Servlet implementation class RegistersServer
 */
@WebServlet("/RegistersServer")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String valid=null;
		
		try {
			valid=LoginValidationUsingDatabase.loginValidator(email,password);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		String message=null;
		if(valid.length()==10)
		{
			Session.setSessionEmail(email);
			Session.setSessionmobile(valid);
			message="Welcome";
			//out.println( email);
		}
		else if(valid=="fail")
		{
			message= "no records found";
			//out.println( email);
		}
		else
		{
			message ="invalid credentials";
		}
		Gson gson=new Gson();
		String json=gson.toJson(message);
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
