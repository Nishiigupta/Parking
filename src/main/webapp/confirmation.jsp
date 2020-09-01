<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="xyz.willz.entities.BookingInfo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirmation</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="./resources/css/fontawesome/css/all.css">
    <link rel="stylesheet" href="./resources/css/confirmation.css">
</head>
<body>
	<%@ include file="./navbar.jsp" %>

	<%
	
	
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");

		BookingInfo bookingInfo = (BookingInfo)session.getAttribute("bookinginfo");
	%>
	<div class="heading">
	  <h1>Booking Available</h1>
	</div>
	<main>
	<!-- The Booking Form Container -->
	  
	  <form action="bookingsuccess" method="post" class="confirmationForm diabled">
	  	
	  	<div class="form-group ParkingNameContainer" id="ParkingNameContainer">
	      <input name="parkingName" value="<%= bookingInfo.getParkingName() %>" type="text" id="ParkingName" class="form-control my-3 py-3 mx-auto" readonly/>
		</div>
		
		<div class="form-group ParkingAddressContainer" id="ParkingAddress">
	      <input name="address" value="<%= bookingInfo.getParkingaddress() %>" type="text" id="ParkingAddress" class="form-control my-3 py-3 mx-auto" readonly/>
		</div>
		
		<div class="row px-3 py-3">
		    <div class="col">
		      <label for="Arrival mx-auto">Arrival</label>
		      <select name="arrivalDate" class="form-control" id="ArrivalDate" disabled >
		      	<option value="<%= bookingInfo.getArrivalDate() %>"><%= bookingInfo.getArrivalDate() %></option>	      
		      </select>
		      <select name="arrivalTime" class="form-control " id="ArrivalTime" disabled >
		      	<option value="<%= bookingInfo.getArrivalTime() %>"><%= bookingInfo.getArrivalTime() %></option>
		      </select>
		    </div>
		    <div class="col">
		      <label for="Departure">Departure</label>
		      <select name="departureDate" class="form-control" id="DepartureDate" disabled>
		      	<option value="<%= bookingInfo.getDepartureDate() %>"><%= bookingInfo.getDepartureDate() %></option>
		      </select>
		      <select name="departureTime" class="form-control" id="DepartureTime" disabled>
		      	<option value="<%= bookingInfo.getDepartureTime() %>"><%= bookingInfo.getDepartureTime() %></option>
		      </select>
		    </div>	
		</div>
		<div>
			<input type="radio" name="vehicleType" value="<%= bookingInfo.getVehicleType() %>" checked > <%= bookingInfo.getVehicleType() %> </input>
	  	</div>
	  	
	  	<button type="submit" class="btn btn-success w-75 my-3">Pay</button>
	  	
	  </form>
	<!-- End Of Booking Form -->
	</main>	
	

	<%@ include file="./footer.jsp" %>
	
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	
	<script src="./resources/js/booking.js" type="text/javascript"></script>
</body>
</html>