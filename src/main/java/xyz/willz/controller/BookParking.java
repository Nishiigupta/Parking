package xyz.willz.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import xyz.willz.dao.BookingDao;
import xyz.willz.entities.AdminParking;
import xyz.willz.entities.BookingInfo;

@WebServlet("/bookparking")
public class BookParking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		try {
			final Integer id = Integer.parseInt(request.getParameter("id"));
			final AdminParking selectedParking = ((ArrayList<AdminParking>)session.getAttribute("parkings")).get(id);
			
			session.setAttribute("selectedParking", selectedParking);
			final RequestDispatcher rd = request.getRequestDispatcher("booking.jsp");
			rd.forward(request, response);
			return;
			
		} catch(Exception e) {
			System.out.println("Exception: " + e);
		}
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		final BookingInfo bookingInfo = new BookingInfo(request, (AdminParking) session.getAttribute("selectedParking"));
		
		try{
			System.out.println("Post in book Parking");
			// Check Validity
			if(bookingInfo.valid()) {
				System.out.println("Booking Information is valid");
				// check availability
				final BookingDao bookingDao = new BookingDao();
				boolean available = bookingDao.validate(bookingInfo);
				if(available) {
					session.setAttribute("bookinginfo", bookingInfo);
					System.out.println(bookingInfo);
					
					RequestDispatcher rd = request.getRequestDispatcher("confirmation.jsp");
					rd.forward(request, response);
					return;
				} else {
					session.setAttribute("message", "No Vacant Spots");
				}
			} else {
				session.setAttribute("message", "Invalid Details");
			}
			
		}catch(Exception e) {
			System.out.println("Exception in booking");
		}
		
		response.sendRedirect("index.jsp");
	}

}
