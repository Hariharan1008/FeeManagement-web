package com.hari.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hari.Dao.TotalAndPaidFeesFinderDao;
import com.hari.logic.TotalAndPaidFeesFinderForFees;
import com.hari.model.FeesStructure;

/**
 * Servlet implementation class PayFeesValidatorServlet
 */
@WebServlet("/PayFeesValidatorServlet")
public class PayFeesValidatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayFeesValidatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email=request.getParameter("email");
		String amount=request.getParameter("amount");
		System.out.println(email+""+amount);
		int valid=0;
		String message=null;
		PrintWriter out=response.getWriter();
		try {
			 valid=TotalAndPaidFeesFinderForFees.feeValidator(email, Integer.parseInt(amount));
			if(valid==0)
			{
				FeesStructure fee=TotalAndPaidFeesFinderDao.totalFeesFinder(email);
				message="please pay only "+fee.getFeesPending();
			}
			else if(valid==1)
			{
				message="Success";
			}
			Gson gson =new Gson();
			String json=gson.toJson(message);
			out.println(json);
			out.flush();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
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
