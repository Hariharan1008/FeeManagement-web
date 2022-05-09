package com.hari.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hari.Dao.RegisterInsertion;
import com.hari.Dao.RegistrationInsertionAdminDao;
import com.hari.logic.TotalFeesFinder;
import com.hari.model.FeesStructure;
import com.hari.model.Registration;
import com.hari.model.Session;

/**
 * Servlet implementation class RegistrationInsertionServlet
 */
@WebServlet("/RegistrationInsertionServlet")
public class RegistrationInsertionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationInsertionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String userName=request.getParameter("username");
		String mobile=request.getParameter("mobile");
		String age=request.getParameter("age");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String year=request.getParameter("year");
		int yearOfStudy=Integer.parseInt(year);
		String branch=request.getParameter("branch");
		String hOrD=request.getParameter("hostelCheck");
		String needBus=request.getParameter("bus");
		Registration registration=new Registration();
		
		registration.setName(name);
		registration.setUserName(userName);
		registration.setUserMobileNumber(mobile);
		int userAge=Integer.parseInt(age);
		registration.setAge(userAge);
		registration.setUserEmail(email);
		registration.setUserPassword(password);
		registration.setYearOfStudy(yearOfStudy);
		registration.setBranch(branch);
		registration.sethOrD(hOrD);
		registration.setNeedBus(needBus);
		PrintWriter out=response.getWriter();
			int userInsert=0;
			try {
				userInsert = RegisterInsertion.insertUser(registration);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String totalFees=null;
			try {
				totalFees = TotalFeesFinder.totalFeeFinder(yearOfStudy,branch,hOrD,needBus);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FeesStructure fee= new FeesStructure();
			fee.setEmail(email);
			fee.setName(name);
			fee.setTotalFees(totalFees);
			String paidFees=Integer.toString(0);
			fee.setFeesPaid(paidFees);
			fee.setFeesPending(totalFees);
			fee.setPayingAmount(paidFees);
			Date date =Date.valueOf(LocalDate.now());
			fee.setPaidOn(date);
			int adminInsert=0;
			try {
			 adminInsert=RegistrationInsertionAdminDao.userInsertionInAdminTable(fee);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(userInsert==1 && adminInsert==1)
			{
				Session.setSessionmobile(mobile);
				Session.setSessionEmail(email);
				out.println("Successfully Inserted");
			}
			else
			{
				out.println("found an error");
			}
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
