package xyz.willz.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

public class BookingInfo {
	
	private Integer id; 
	private String arrivalDate;
	private String departureDate;
	private String arrivalTime;
	private String departureTime;
	private final String parkingName;
	private final String parkingaddress;
	private String vehicleType;
	private Integer parkingId;
	private Integer buyerId;
	private boolean isAvailable = true;
	private Integer vacant;
	
	
	public BookingInfo(final HttpServletRequest request, final AdminParking selectedParking) {
		
		this.parkingName = selectedParking.getParkingName();
		this.parkingaddress = selectedParking.getAddress();
		try {
			this.buyerId = (Integer)request.getSession().getAttribute("id");
			this.parkingId = selectedParking.getId();
			this.setArrivalDate(request.getParameter("arrivalDate"));
			this.setDepartureDate(request.getParameter("departureDate"));
			this.setArrivalTime(request.getParameter("arrivalTime"));
			this.setDepartureTime(request.getParameter("departureTime"));
			this.vehicleType = request.getParameter("vehicleType");
			
		} catch(Exception e) {
			System.out.println("Exception in BookingInfo : " + e);
			this.isAvailable = false;
		}

		System.out.println("Booking Info instance succesfully created");
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public Integer getParkingId() {
		return parkingId;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public boolean valid() {
		if(this.isAvailable == false) {
			return false;
		}
		
		final Calendar calendar = Calendar.getInstance();
		final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy:MM:dd");
		final SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
		
		try {System.out.println("Valid date");
			if(sdfDate.format(calendar.getTime()).compareTo(this.arrivalDate) > 0 || sdfDate.format(sdfDate.parse(this.arrivalDate)).compareTo(this.departureDate) > 0) {
				System.out.println("Not Valid");
				this.isAvailable = false;
				return false;
			}System.out.println("Valid time");
			if(sdfTime.format(sdfTime.parse(this.arrivalTime)).compareTo(this.departureTime) >= 0 && sdfDate.format(sdfDate.parse(this.arrivalDate)).compareTo(this.departureDate) >= 0) {
				System.out.println("Not valid");
				this.isAvailable = false;
				return false;
			}
		} catch(Exception e) {
			System.out.println("Exception in validating bookingInfo: " + e);
			this.isAvailable = false;
			return false;
		}
		
		return true;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}


	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}


	public String getDepartureDate() {
		return departureDate;
	}


	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}


	public String getArrivalTime() {
		return arrivalTime;
	}


	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


	public String getDepartureTime() {
		return departureTime;
	}


	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}


	public String getVehicleType() {
		return vehicleType;
	}


	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}


	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}


	public void setVacant(Integer vacant) {
		this.vacant = vacant;
	}


	public boolean isAvailable() {
		return isAvailable;
	}

	public Integer getVacant() {
		return vacant;
	}


	public String getParkingName() {
		return parkingName;
	}


	public String getParkingaddress() {
		return parkingaddress;
	}


	@Override
	public String toString() {
		return "BookingInfo [arrivalDate=" + arrivalDate + ", departureDate=" + departureDate + ", arrivalTime="
				+ arrivalTime + ", departureTime=" + departureTime + ", parkingName=" + parkingName
				+ ", parkingaddress=" + parkingaddress + ", vehicleType=" + vehicleType + ", isAvailable=" + isAvailable
				+ ", vacant=" + vacant + "]";
	}
	
	
}
