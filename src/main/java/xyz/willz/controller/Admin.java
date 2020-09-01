package xyz.willz.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.willz.dao.AdminParkingDao;


@WebServlet("/admin")
public class Admin extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In Admin");
		HttpSession session = request.getSession();
		
		try {
			AdminParkingDao parkingDetails = new AdminParkingDao();
			if(!parkingDetails.isEverythingOk()) {
				RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
				rd.forward(request, response);
				return;
			}
			int id = (int) session.getAttribute("id");
			session.setAttribute("parkingDetails", parkingDetails.fetchData(id));
			RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
			rd.forward(request, response);
			return;
		} catch(Exception e) {
			System.out.println("Exception in admin: " + e);
		}
		response.sendRedirect("login");
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
