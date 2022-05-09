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
import com.hari.Dao.WalletLoginValidationDao;

/**
 * Servlet implementation class WalletLoginVerification
 */
@WebServlet("/WalletLoginVerification")
public class WalletLoginVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WalletLoginVerification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mobile=request.getParameter("mobile");
		String transpin=request.getParameter("tpin");
		int tpin=Integer.parseInt(transpin);
		System.out.println(mobile);
		System.out.println(tpin);
		long userMobile=Long.parseLong(mobile);
		WalletLoginValidationDao login=new WalletLoginValidationDao();
		String message=null;
		PrintWriter out=response.getWriter();
		try {
			int valid=login.validateWalletlogin(userMobile,tpin);
			if(valid==1)
			{
				message="passed";
			}
			else
			{
				message="failed";
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
