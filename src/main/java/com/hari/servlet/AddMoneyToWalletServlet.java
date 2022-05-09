package com.hari.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hari.logic.AddMoneyToWallet;
import com.hari.model.Session;


/**
 * Servlet implementation class AddMoneyToWalletServlet
 */
@WebServlet("/AddMoneyToWalletServlet")
public class AddMoneyToWalletServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMoneyToWalletServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userMobile=Session.getSessionmobile();
		Long mobile=Long.parseLong(userMobile);
		String userAmount=request.getParameter("amount");
		long amount=Long.parseLong(userAmount);
		AddMoneyToWallet add=new AddMoneyToWallet();
		int completed=0;
		try {
			completed = add.addMoney(mobile, amount);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out=response.getWriter();
		if(completed==1)
		{
			out.println("money add Successfully");
			System.out.println("money add Successfully");
		}
		else
		{
			out.println("money not added");
			System.out.println("money not added");
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
