package xyz.willz.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.willz.dao.AdminParkingDao;
import xyz.willz.entities.AdminParking;

@WebServlet("/updateparking")
public class UpdateParkingInfo extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Integer id = Integer.valueOf((String)request.getAttribute("id"));
		try{
			Integer id = Integer.valueOf(request.getParameter("id"));
			HttpSession session = request.getSession();
			
			List<AdminParking> allData = (List<AdminParking>)session.getAttribute("parkingDetails");
			System.out.println("Data: "+allData);
			AdminParking data = allData.get(id);
			
			session.setAttribute("dataToBeUpdated", data);
			System.out.println("preprocesses " + id);
			
			RequestDispatcher rd = request.getRequestDispatcher("updateparking.jsp");
			rd.forward(request, response);
			return;
			
		} catch(Exception e) {
			System.out.println("Error in updateParking");
			response.sendRedirect("admin"); 
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("About to update Data");
		try {
			final Integer parkingId = Integer.parseInt(request.getParameter("parkingId"));
			final String parkingName = request.getParameter("parkingName");
			final Integer vacant = Integer.valueOf(request.getParameter("vacant"));
			final Integer occupied = Integer.valueOf(request.getParameter("occupied"));
			final Integer total = Integer.valueOf(request.getParameter("total"));
			final Integer two_wheeler = Integer.parseInt(request.getParameter("two_wheeler"));
			final Integer four_wheeler = Integer.parseInt(request.getParameter("four_wheeler"));
			
			AdminParkingDao adminParkingDao = new AdminParkingDao();
			
			adminParkingDao.update(parkingId, parkingName, vacant, occupied, total, two_wheeler, four_wheeler);

			System.out.println("All Updated");
		} catch(Exception e) {
			System.out.println("Something Wrong");
		}
		
		System.out.println("Updated Data and redirecting now");
		response.sendRedirect("admin");
	}
}
