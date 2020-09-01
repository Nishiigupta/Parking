package xyz.willz.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import xyz.willz.entities.BookingInfo;

@WebServlet("/getReceipt")
public class PDFGenerator extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final HttpSession session = request.getSession();
		try {
			System.out.println("About to Generate");
			final BookingInfo bookingInfo = (BookingInfo)session.getAttribute("bookinginfo");
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
			Document doc = new Document(pdfDoc, PageSize.A6);
			
			// Heading
			final String heading = "Booking Success";
			final Paragraph headingPara = new Paragraph(heading);
			final Div headingDiv = new Div();
			headingDiv.add(headingPara);
			headingDiv.setBold();
			headingDiv.setMarginLeft(50);
			headingDiv.setUnderline();
			headingDiv.setMarginBottom(20);
			doc.add(headingDiv);
			
			// Parking Name
			final String parkingName = "Parking Name:- " + bookingInfo.getParkingName();
			final Paragraph parkingPara = new Paragraph(parkingName);
			final Div nameDiv = new Div();
			nameDiv.add(parkingPara);
			doc.add(nameDiv);
			
			// Parking Address
			final Paragraph addressPara = new Paragraph("Address:- " + bookingInfo.getParkingaddress());
			final Div addressDiv = new Div();
			addressDiv.add(addressPara);
			doc.add(addressDiv);
			
			// duration
			float [] pointColumnWidths = {120F, 120F};   
			Table table = new Table(pointColumnWidths);    
  
			// Adding cells to the table       
			table.addCell(new Cell().add("Arrive Date"));       
			table.addCell(new Cell().add("Depart Date"));       
			table.addCell(new Cell().add(bookingInfo.getArrivalDate()));       
			table.addCell(new Cell().add(bookingInfo.getDepartureDate()));       
			table.setMarginTop(20);
			table.setMarginBottom(5);
			doc.add(table);
			
			table = new Table(pointColumnWidths);
			table.addCell(new Cell().add("Arrive Time"));       
			table.addCell(new Cell().add("Depart Time"));       
			table.addCell(new Cell().add(bookingInfo.getArrivalTime()));       
			table.addCell(new Cell().add(bookingInfo.getDepartureTime())); 
			table.setMarginTop(5);
			table.setMarginBottom(20);
	        doc.add(table);
	        
	        // vehicle type
	        String vehicleType;
	        if(bookingInfo.getVehicleType().equals("two_wheeler")) {
	        	vehicleType = "Two Wheeler";
	        } else {
	        	vehicleType = "Four Wheeler";
	        }
	        Paragraph vehicle = new Paragraph(vehicleType);
	        doc.add(vehicle);
	        
	        // Total
	        Paragraph price = new Paragraph("Total:  Rs " + "90");
	        price.setMarginLeft(150);
	        doc.add(price);
	        
	        doc.close();
	        
	        // setting some response headers
	        response.setHeader("Expires", "0");
	        response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
	        response.setHeader("Pragma", "public");
	        // setting the content type
	        response.setContentType("application/pdf");
	        // the contentlength
	        response.setContentLength(baos.size());
	        	        
	        // write ByteArrayOutputStream to the ServletOutputStream
	        OutputStream os = response.getOutputStream();
	        baos.writeTo(os);
	        os.flush();
	        os.close();
	        session.removeAttribute("bookinginfo");
			session.removeAttribute("selectedParking");
			System.out.println("End generation");
		} catch(Exception e) {
			System.out.println("Exception: " + e);
			session.removeAttribute("bookinginfo");
			session.removeAttribute("selectedParking");
			response.sendRedirect("/Parking");
			return;
		}
	}

}
