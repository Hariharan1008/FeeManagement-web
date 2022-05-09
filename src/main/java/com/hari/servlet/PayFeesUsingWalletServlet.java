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
import com.hari.logic.SubtractMoneyFromWallet;
import com.hari.logic.UpdateFeesAfterUsingWallet;
import com.hari.model.Session;

/**
 * Servlet implementation class PayFeesUsingWalletServlet
 */
@WebServlet("/PayFeesUsingWalletServlet")
public class PayFeesUsingWalletServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayFeesUsingWalletServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userAmount=request.getParameter("amount");
		long amount=Long.parseLong(userAmount);
		System.out.println(amount);
		PrintWriter out=response.getWriter();
		PayFeesUsingWallet pay=new PayFeesUsingWallet();
		int amountValid=0;
		try {
			amountValid = pay.amountVerifier(amount);
			System.out.println(amountValid);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(amountValid==0)
		{
			//wallet.setAmount(amount);
			
			UpdateFeesAfterUsingWallet updatefees=new UpdateFeesAfterUsingWallet();
			try {
				updatefees.adminTableUpdation(amount);
				System.out.println(updatefees);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SubtractMoneyFromWallet subtract=new SubtractMoneyFromWallet();
			long mobile=Long.parseLong(Session.getSessionmobile());		
		    try {
				subtract.walletUpdatiorAfterPayment( mobile, amount);
				System.out.println(mobile+" "+amount);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    String message="success";
		    Gson gson=new Gson();
		    String json=gson.toJson(message);
		    System.out.println(json);
		    out.println(json);
		    out.flush();
		}
		else
		{
			String message="Insufficient funds";
			Gson gson=new Gson();
		    String json=gson.toJson(message);
		    System.out.println(json);
		    out.println(json);
		    out.flush();
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
