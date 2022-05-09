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
import com.hari.Dao.TransactionDetailsUsingIdDao;
import com.hari.model.Transaction;

/**
 * Servlet implementation class TransactionDetailsUsingIdServlet
 */
@WebServlet("/TransactionDetailsUsingIdServlet")
public class TransactionDetailsUsingIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionDetailsUsingIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Id=request.getParameter("transactionId");
		int transactionId=Integer.parseInt(Id);
		try {
			PrintWriter out=response.getWriter();
			Transaction transaction=TransactionDetailsUsingIdDao.usingTransactionId(transactionId);
			Gson gson=new Gson();
			String json=gson.toJson(transaction);
			out.println(json);
			out.flush();
		} catch (ClassNotFoundException | SQLException e) {
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
