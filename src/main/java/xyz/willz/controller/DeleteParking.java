package xyz.willz.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import xyz.willz.dao.LoginDao;
import xyz.willz.dao.ParkingsDao;


@WebServlet("/deleteparking")
public class DeleteParking extends HttpServlet {
    

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Integer id = Integer.parseInt(req.getParameter("id"));
		if(id!=null) {
			
			final ParkingsDao parkingDao = new ParkingsDao();
			parkingDao.deleteParking(id);
			
		}
		
		resp.sendRedirect("admin");
		return;
		
	}

}
