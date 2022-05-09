package com.hari.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hari.logic.PayFeesUsingWallet;
import com.hari.model.Wallet;
/**
 * Servlet implementation class FindWalletBalanceAndFeesServlet
 */
@WebServlet("/FindWalletBalanceAndFeesServlet")
public class FindWalletBalanceAndFeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindWalletBalanceAndFeesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email=request.getParameter("email");
		String userMobile=request.getParameter("mobile");
		long mobile=Long.parseLong(userMobile);
		PayFeesUsingWallet pay=new PayFeesUsingWallet();
		PrintWriter out=response.getWriter();
		
		try {
			Wallet wallet=pay.findFeesAndWalletBalance(email, mobile);
			Gson gson=new Gson();
			String json=gson.toJson(wallet);
			System.out.println(json);
			out.println(json);
			out.flush();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
