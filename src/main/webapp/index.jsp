<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="xyz.willz.entities.AdminParking"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>index</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="./resources/css/fontawesome/css/all.css">
    <link rel="stylesheet" href="./resources/css/index.css">
</head>
<body>
	<%
	

	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	
		if(session.getAttribute("admin") != null) {
			response.sendRedirect("admin");
			return;
		}
	%>

	<%@ include file="./navbar.jsp" %>
	
	<main class="py-3 my-3" style="text-align: center; min-height: 50vh;">
		
		<h1>Find Parking</h1>
		
			<!-- The SearchParking Form Container -->
		<form action="findparkings" method="get" class="BookingForm py-3 my-3">
		    <!-- Warning Message Container -->
			<% 
				try{
					String message = (String) session.getAttribute("message");
					System.out.println(message);
					if(message != null && !message.isBlank()) { %>
						<div class="alert alert-danger" role="alert">
						  <%= message %>
						</div>
					<%}
					session.removeAttribute("message");
				} catch(Exception e) {}
			 %>
			
			<!-- End of Warning Container -->
			
		    <div class="form-group EnterLocationContainer" id="EnterLocationContainer">
		      <input name="address" type="text" id="EnterLocation" class="EnterLocation form-control my-3 py-3 mx-auto" placeholder="Enter Location" /> 
		    </div>
		  
		  <button type="submit" class="btn btn-info w-75">Find Parking</button>
		
		</form>
		<!-- End Of SearchParking Form -->
		
		<% if(session.getAttribute("parkings") != null){ 
			final List<AdminParking> parkings = (ArrayList<AdminParking>)session.getAttribute("parkings");
			if(parkings.size() > 0) {%>	
		<!-- Display all Parking's Details -->
		
		<table class="table table-striped table-hover py-3 my-3">
		<caption>List of Parking</caption>
        <thead>
          <tr>
            <th scope="col">SI.</th>
            <th scope="col">Parking Name</th>
            <th scope="col">Address</th>
            <th scope="col">Two Wheeler</th>
            <th scope="col">Four Wheeler</th>
          </tr>
        </thead>
        <tbody>
        <% int index = 0;
        for(AdminParking items: parkings) {%>
          <tr>
            <th scope="row"><%= index+1 %></th>
            <td><%= items.getParkingName() %></td>
            <td><%= items.getAddress() %></td>
            <td><%= items.getTwo_wheeler() %></td>
            <td><%= items.getFour_wheeler() %></td>
            <td><a href="bookparking?id=<%= index++ %>" style="color: white;"><button type="button" class="btn btn-success mx-3">Book</button></a></td>
          </tr>
        <% } %>
        </tbody>
        
      </table>
		<% } %>
		<!-- Finished Displaying all Parking's Details -->
	<% } %>
	
	</main>
	
	<%@ include file="./footer.jsp" %>
		<!-- JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</body>
</html>