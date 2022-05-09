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
import com.hari.Dao.WalletInsertionDao;
import com.hari.model.Wallet;

/**
 * Servlet implementation class WalletInsertionServlet
 */
@WebServlet("/WalletInsertionServlet")
public class WalletInsertionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WalletInsertionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String userMobile=request.getParameter("mobile");
		long mobile=Long.parseLong(userMobile);
		String transactionpin=request.getParameter("tpin");
		int tpin=Integer.parseInt(transactionpin);
		long balance=0;
		Wallet wallet=new Wallet();
		wallet.setName(name);
		wallet.setMobile(mobile);
		wallet.setBalance(balance);
		wallet.setTpin(tpin);
		WalletInsertionDao insert=new WalletInsertionDao();
		int completed=0;
		String result="";
		PrintWriter out=response.getWriter();
		try {
		   completed=insert.walletInsertion(wallet);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(completed==0)
		{
			result="Creation failed";
		}
		else
		{
			result="Success";
		}
		Gson gson=new Gson();
		String json=gson.toJson(result);
		out.println(json);
		System.out.println(json);
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
