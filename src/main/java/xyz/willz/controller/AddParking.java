package xyz.willz.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.willz.dao.AdminParkingDao;
import xyz.willz.entities.AdminParking;

@WebServlet("/addparking")
public class AddParking extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("addparking.jsp");
		rd.forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getParameter("parkingName") == null || request.getParameter("latitude") == null || request.getParameter("longitude") == null) {
				response.sendRedirect("addparking");
				return;
			}
			final AdminParking newParking = new AdminParking(request);
			
			final Integer adminId = (Integer)request.getSession().getAttribute("id");
			
			final AdminParkingDao newParkingDao = new AdminParkingDao();
			final boolean status = newParkingDao.add(newParking, adminId);
			
			
			
		} catch(Exception e) {
			System.out.println("Exception in adding parking: " + e);
		}
		response.sendRedirect("admin");
	}
}
