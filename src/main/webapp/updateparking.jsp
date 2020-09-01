<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="xyz.willz.entities.AdminParking" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	</head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="./resources/css/fontawesome/css/all.css">
<body style="background-color: thistle;">
	
	<% 
	
	
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	
		AdminParking data = (AdminParking)session.getAttribute("dataToBeUpdated");
	%>
	<%@ include file="./navbar.jsp" %>
	<main style="text-align: center; padding: 20px; margin: 5px; margin-top: 3rem; margin-bottom: 7rem">
	
	<h1>Update Info Of this Parking</h1>
	
	<form action="updateparking" method="post" style="margin-top: 15vh;">
		
		  <div class="form-row justify-content-center" style="text-align: center;">
		    <div class="form-group col-md-6" style="max-width: 400px;">
		      <label for="latitude">Latitude</label>
		      <input name="latitude" value=<%= data.getLatitude() %> type="text" class="form-control" id="Latitude" required=true disabled>
		    </div>
		    <div class="form-group col-md-6" style="max-width: 400px;">
		      <label for="Longitude">Longitude</label>
		      <input name="longitude" value=<%= data.getLongitude() %> type="text" class="form-control" id="Longitude" required=true disabled> 
		    </div>
		  </div>

		<div class="form-row justify-content-center" >
		    <div class="form-group col" style="max-width: 265px;">
		      <label for="vacant">Vacant</label>
		      <input name="vacant" value=<%= data.getVacant() %> type="text" class="form-control" id="vacant" value=<%= data.getVacant() %> required=true>
		    </div>
		    <div class="form-group col" style="max-width: 265px;">
		      <label for="occupied">Occupied</label>
		      <input name="occupied" value=<%= data.getOccupied() %> type="text" class="form-control" id="occupied" required=true>
		    </div>
		    <div class="form-group col" style="max-width: 265px;">
		      <label for="total">Total</label>
		      <input name="total" value=<%= data.getTotal() %> type="text" class="form-control" id="total" required=true >
		    </div>
		</div>
		
		<div class="form-group" style="text-align: center;">
	    	<label for="parkingName">Parking Name</label>
	    	<input name="parkingName" value="<%= data.getParkingName() %>" type="text" class="form-control" id="parkingName" placeholder="Parking Name" required="true" style="width: 80vw; max-width: 750px; margin-left: 30%; margin-right: 30%; margin: auto;">
		</div>
		
		<div class="form-group" style="text-align: center;">
	    	<label for="parkingAddress">Address</label>
	    	<input name="address" value="<%= data.getAddress() %>" type="text" class="form-control" id="parkingAddress" placeholder="Address" required="true" style="width: 80vw; max-width: 750px; margin-left: 30%; margin-right: 30%; margin: auto;" disabled>
		</div>
		
		<div class="form-group" style="text-align: center;">
	    	<input name="parkingId" value=<%= data.getId() %> hidden=true type="text" class="form-control" required=true style="max-width: 750px;width:80vw; margin-left: 30%; margin-right: 30%; margin: auto;" >
		</div>
		
		<div class="form-row justify-content-center" style="text-align: center;">
		    <div class="form-group col-md-6" style="max-width: 150px;">
		      <label for="twoWheeler">Two Wheeler</label>
		      <input name="two_wheeler" value=<%= data.getTwo_wheeler() %> type="number" class="form-control" id="twoWheeler" required=true >
		    </div>
		    <div class="form-group col-md-6" style="max-width: 150px;">
		      <label for="fourWheeler">Four Wheeler</label>
		      <input name="four_wheeler" value=<%= data.getFour_wheeler() %> type="number" class="form-control" id="fourWheeler" required=true > 
		    </div>
	   </div>
		
		<button type="submit" class="btn btn-danger" style="width: 150px;">Update</button>
		
	</form>
	
	</main>
	
	<%@ include file="./footer.jsp" %>
	
		<!-- JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
 	
 	<style>
		input{
			text-align: center;
		} 		

 	</style>
	
</body>
</html>