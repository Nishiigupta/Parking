package xyz.willz.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.willz.dao.BookingDao;
import xyz.willz.entities.BookingInfo;

@WebServlet("/bookingsuccess")
public class BookingSuccess extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Hello");
		BookingDao bookingDao = new BookingDao();
		
		try {
			HttpSession session = req.getSession();
			BookingInfo bookingInfo = (BookingInfo)session.getAttribute("bookinginfo");
			if(bookingDao.isEverythingOk()) {
				boolean success = bookingDao.makeBooking(bookingInfo);
				if(success) {
//					
					RequestDispatcher rd = req.getRequestDispatcher("getReceipt");
					rd.forward(req, resp);
					
					return;
				}
			}
		} catch(Exception e) {
			System.out.println("Exception in success booking: " + e);	
		}
		
		resp.sendRedirect("/Parking");
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/Parking");
	}

}
