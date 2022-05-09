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


import com.google.gson.Gson;
import com.hari.Dao.FindWalletBalanceDao;
import com.hari.Dao.FindWalletUserUsingMobile;
import com.hari.Dao.UpdateWalletDao;
import com.hari.logic.UpdateFundTransaction;
import com.hari.model.FundTransfer;
import com.hari.model.Session;
/**
 * Servlet implementation class FundTransferServlet
 */
@WebServlet("/FundTransferServlet")
public class FundTransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundTransferServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String serverReq=request.getParameter("req");
		int req=Integer.parseInt(serverReq);
		
		System.out.println(req);
		if(req==1)
		{
		     String recMobile=request.getParameter("recmobile");
		     long receiverMobile=Long.parseLong(recMobile);
		     FindWalletUserUsingMobile findName=new FindWalletUserUsingMobile();
		     String userName=null;
		     PrintWriter out=response.getWriter();
		     try 
		     {
			        userName = findName.findUser(receiverMobile);
		     } 
		     catch (ClassNotFoundException e) 
		     {
			       e.printStackTrace();
		     }  
		     catch (SQLException e)
		     {
			      e.printStackTrace();
		      }		     
		      String user="";
		      if(userName!=null)
		      {
			     user=userName;
		      }
		      else
		      {
		    	  user="no user found";
		      }
		      Gson gson=new Gson();
			  String json=gson.toJson(user);
			  out.println(json);
			  System.out.println(json);
			  out.flush();
		    	  
		}
		else if(req==2)
		{
			PrintWriter out=response.getWriter();
			String recMobile=request.getParameter("recmobile");
		    long receiverMobile=Long.parseLong(recMobile);
		    long senderMobile=Long.parseLong(Session.getSessionmobile());
		    String userAmount=request.getParameter("amount");
		    long amount=Long.parseLong(userAmount);
		    long senderBalance=0;
		    long receiverBalance=0;
		    FindWalletBalanceDao findBalance=new FindWalletBalanceDao();
		    String message="";
			  try 
			  {
				  senderBalance=findBalance.findWalletBalance(senderMobile);
			  } 
			  catch (ClassNotFoundException e) 
			  {
				
				e.printStackTrace();
			  } 
			  catch (SQLException e) 
			  {
				
				e.printStackTrace();
			  }
			  try 
			  {
				receiverBalance=findBalance.findWalletBalance(receiverMobile);
			  } 
			  catch (ClassNotFoundException e)
			  {
				
				e.printStackTrace();
			  }  
			  catch (SQLException e) 
			  {
				e.printStackTrace();
			  }
			if(amount<=senderBalance)
			{
				FundTransfer transfer=new FundTransfer();
				transfer.setAmount(amount);
				transfer.setSenderMobile(senderMobile);
				transfer.setReceiverMobile(receiverMobile);
				transfer.setSenderBalance(senderBalance);
				transfer.setReceiverBalance(receiverBalance);
				UpdateFundTransaction fund=new UpdateFundTransaction();
				try 
				{
					fund.updateWallet(transfer);
					UpdateWalletDao points=new UpdateWalletDao();
					points.updateWalletPoints(senderMobile,(int)amount);
					message="completed";
				} 
				catch (ClassNotFoundException e)
				{
					
					e.printStackTrace();
				}
				catch (SQLException e)
				{
					
					e.printStackTrace();
				}
						
			}
			else
			{
				message="Insufficient funds";
			}
			Gson gson=new Gson();
			String json=gson.toJson(message);
			out.println(json);
			System.out.println(json);
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
