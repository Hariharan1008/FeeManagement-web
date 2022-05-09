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
import com.hari.Dao.TotalFeesFinderUsingYearAndBranch;
import com.hari.model.FeesStructure;

/**
 * Servlet implementation class TotalFeesFinderServlet
 */
@WebServlet("/TotalFeesFinderServlet")
public class TotalFeesFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalFeesFinderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String yearOfStudy=request.getParameter("year");
		int year=Integer.parseInt(yearOfStudy);
		String branch=request.getParameter("branch");
		PrintWriter out=response.getWriter();
		try {
			FeesStructure fee=TotalFeesFinderUsingYearAndBranch.findTotalFees(year, branch);
			
			Gson gson=new Gson();
			String json=gson.toJson(fee);
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
