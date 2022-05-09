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
import com.hari.Dao.FindTicketUsingIdDao;
import com.hari.model.Tickets;

/**
 * Servlet implementation class FindTicketUsingIdServlet
 */
@WebServlet("/FindTicketUsingIdServlet")
public class FindTicketUsingIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindTicketUsingIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ticketId=request.getParameter("ticketId");
		System.out.println(ticketId);
		PrintWriter out=response.getWriter();
		
		try {
			Tickets ticket=FindTicketUsingIdDao.usingTicketId(Integer.parseInt(ticketId));
			Gson gson=new Gson();
			String json=gson.toJson(ticket);
			System.out.println(json);
			out.println(json);
			out.flush();
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
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
