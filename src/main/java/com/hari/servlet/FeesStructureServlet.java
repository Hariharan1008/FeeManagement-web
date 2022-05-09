package com.hari.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hari.Dao.FeesStructureDao;
import com.hari.model.FeesStructure;

/**
 * Servlet implementation class FeesStructureServlet
 */
@WebServlet("/FeesStructureServlet")
public class FeesStructureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeesStructureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userYear=request.getParameter("year");
		String branch=request.getParameter("branch");
		System.out.println(userYear+""+branch);
		int year=Integer.parseInt(userYear);
		PrintWriter out=response.getWriter();
		try {
			FeesStructure fees=FeesStructureDao.getFeesStructure(year,branch);
			//System.out.println(fees.getFirstSemesterFees()+""+fees.getSecondSemesterFees()+""+fees.getHotelFees()+""+fees.getTransportFees());
			
			Gson gson=new Gson();
			String json=gson.toJson(fees);
			System.out.println(json);
		    out.println(json);
		    out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
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
