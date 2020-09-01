<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="xyz.willz.entities.AdminParking"%>
<%@page import="java.util.*, java.text.SimpleDateFormat, java.text.DateFormat" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="./resources/css/fontawesome/css/all.css">
    <link rel="stylesheet" href="./resources/css/booking.css">
</head>
<body>
	<%@ include file="./navbar.jsp" %>
	<main>
	
	  <div class="heading">
	  	<h1>Book Parking</h1>
	  </div>
	  
	  <% 
	  
	  
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");

	  
	  	final AdminParking selectedParking = (AdminParking)session.getAttribute("selectedParking");
	  %>	
	
			<!-- The Booking Form Container -->
	  <form action="bookparking" method="post" class="confirmationForm">
	  	
	  	<div class="form-group ParkingNameContainer" id="ParkingNameContainer">
	      <input name="parkingName" value="<%= selectedParking.getParkingName() %>" type="text" id="ParkingName" class="form-control my-3 py-3 mx-auto" readonly/>
		</div>
		
		<div class="form-group ParkingAddressContainer" id="ParkingAddress">
	      <input name="address" value="<%= selectedParking.getAddress() %>" type="text" id="ParkingAddress" class="form-control my-3 py-3 mx-auto" readonly/>
		</div>
		
		<div class="row px-3 py-3">
		    <div class="col">
		      <label for="Arrival mx-auto">Arrival</label>
		      <select name="arrivalDate" class="form-control" id="ArrivalDate">
		      	<% 
		      		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd, EEE");
		    		SimpleDateFormat sdfvalue = new SimpleDateFormat("yyyy:MM:dd");
		      		Calendar calendar = Calendar.getInstance();
		    		for(int i = 0; i < 7; i++) {
		    			%> <option value="<%= sdfvalue.format(calendar.getTime()) %>"><%= sdf.format(calendar.getTime()) %></option> <%
		    			calendar.add(Calendar.DATE, 1);
		    		}
		    	%>	      
		      </select>
		      <select name="arrivalTime" class="form-control " id="ArrivalTime">
		      	<% 
		      		sdf = new SimpleDateFormat("HH:mm");
		      		calendar = Calendar.getInstance();
		      		calendar.add(Calendar.HOUR, 2);
		    		for(int i = 0; i < 24; i++) {
		    			%> <option value="<%= sdf.format(calendar.getTime()) %>"><%= sdf.format(calendar.getTime()) %></option> <%
		    			calendar.add(Calendar.HOUR, 1);
		    		}
		    	%>
		      </select>
		    </div>
		    <div class="col">
		      <label for="Departure">Departure</label>
		      <select name="departureDate" class="form-control" id="DepartureDate">
		      	<% 
		      		sdf = new SimpleDateFormat("yyyy MMM dd, EEE");
		      		calendar = Calendar.getInstance();
		    		for(int i = 0; i < 7; i++) {
		    			%> <option value="<%= sdfvalue.format(calendar.getTime()) %>"><%= sdf.format(calendar.getTime()) %></option> <%
		    			calendar.add(Calendar.DATE, 1);
		    		}
		    	%>
		      </select>
		      <select name="departureTime" class="form-control" id="DepartureTime">
		      	<% 
		      		sdf = new SimpleDateFormat("HH:mm");
		      		calendar = Calendar.getInstance();
		      		calendar.add(Calendar.HOUR, 2);
		    		for(int i = 0; i < 24; i++) {
		    			calendar.add(Calendar.HOUR, 1);
		    			%> <option value="<%= sdf.format(calendar.getTime()) %>"><%= sdf.format(calendar.getTime()) %></option> <%
		    		}
		    	%>
		      </select>
		    </div>	
		</div>
		
		<input type="radio" name="vehicleType" value="two_wheeler" checked > Two Wheeler </input>  
        <input type="radio" name="vehicleType" value="four_wheeler"> Four Wheeler </input>
	  		
	  	<button type="submit" class="btn btn-primary w-75 my-3">Confirm Booking</button>
	  	
	  </form>
	<!-- End Of Booking Form -->
	
	</main>
	
	<%@ include file="./footer.jsp" %>
	
		<!-- JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</body>
</html>