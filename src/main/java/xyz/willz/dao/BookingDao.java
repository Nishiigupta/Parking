package xyz.willz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import xyz.willz.entities.BookingInfo;

public class BookingDao {
	private final String url;
	private final String uName;
	private final String pWord;
	private final String driverName;
	private boolean isEverythingOk = true;
	private Connection con;
	
	public BookingDao() {
		this.url = "jdbc:mysql://localhost:3306/parking";
		this.uName = "root";
		this.pWord = "Bittu.22";
		this.driverName = "com.mysql.cj.jdbc.Driver"; 
		
		
		try {
			
			Class.forName(this.driverName);
			con = DriverManager.getConnection(url, uName, pWord);
			
		} catch(Exception e) {
			this.isEverythingOk = false;
		}
		
	}
	
	public boolean makeBooking(BookingInfo bookingInfo) {
		if(!this.isEverythingOk) return false;
		
		final String sql = "INSERT INTO bookings(parking_id, buyer_id, vehicle_type, arrival_time, departure_time, arrival_date, departure_date) values (?, ?, ?, ?, ?, ?, ?)";
		try {
			final PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, bookingInfo.getParkingId());
			st.setInt(2, bookingInfo.getBuyerId());
			st.setString(3, bookingInfo.getVehicleType());
			st.setString(4, bookingInfo.getArrivalTime());
			st.setString(5, bookingInfo.getDepartureTime());
			st.setString(6, bookingInfo.getArrivalDate());
			st.setString(7, bookingInfo.getDepartureDate());
			
			st.execute();
			
			this.incrementParking(bookingInfo.getParkingId());
			bookingInfo.setVacant(bookingInfo.getVacant()-1);
			return true; 
		}catch(Exception e) {
			System.out.println("Exception while saving: " + e);
		}
		return false;
	}
	
	
	private void incrementParking(int id) {
		final String sql = "UPDATE parkings SET vacant = parkings.vacant - 1, occupied = parkings.occupied + 1 where id = ?";
		try {
			System.out.println("going to update vacancy's ");
			final PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			st.execute();
			System.out.println("Updated vacancy");
		} catch(Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	
	public boolean validate(BookingInfo bookingInfo) {
		if(!this.isEverythingOk) return false;
		
		// Check in the database whether it is available or not
		final String sql = "SELECT vacant FROM parkings WHERE id = ?";
		try {
			final PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, bookingInfo.getParkingId());
			
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				bookingInfo.setVacant(rs.getInt("vacant"));
				if(bookingInfo.getVacant() > 0) { 
					System.out.println("Valid Booking dao layer");
					return true;
				}
			}
			
		} catch(Exception e) {
			System.out.println("in exception " + e.getMessage());
		}
		System.out.println("Not Vacant for booking dao layer");
		return false;
	}
	
	
	private final String CHECK = "select id, parking_Id from bookings where departure_time < time(now()) && departure_date <= date(now())";
	private final String UPDATEVACANT = "UPDATE parkings SET vacant = vacant+1, occupied = occupied-1 where id = ?";
	private final String CLEAR = "DELETE FROM bookings WHERE id = ?";
	public void checkAndClear() {
		
		if(this.isEverythingOk == false) {
			return;
		}
		this.check();
		
	}
	
	private void check() {
		try {
			final PreparedStatement statement = this.con.prepareStatement(CHECK);
			ResultSet results = statement.executeQuery();
			
			if(!results.next()) {
				return ;
			} 
		
			final ArrayList<Integer> allParkingsId = new ArrayList<>();
			final ArrayList<Integer> allBookingsId = new ArrayList<>();
			do { 
				allBookingsId.add(results.getInt("id"));
				allParkingsId.add(results.getInt("parking_id"));
			} while(results.next());
			
			System.out.println("All parking Ids' are: " + allParkingsId);
			this.updateVacantParkings(allParkingsId);
			
			System.out.println("All booking Ids' are: " + allBookingsId);
			this.clear(allBookingsId);
		}catch(Exception e) {
			System.out.println("Exception: " + e);
		}
		
	}
	
	private void updateVacantParkings(ArrayList<Integer> allParkingsId) {
		
		try {
			for(int id: allParkingsId) {
				final PreparedStatement statement = this.con.prepareStatement(UPDATEVACANT);
				statement.setInt(1, id);
				System.out.println("statement: " + statement);
				statement.execute();
			}
			
		} catch(Exception e) {
			System.out.println("Exception: " + e);
		}
		
	}
	
	private void clear(ArrayList<Integer> allBookingsId) {
		try {
			
			for(int id: allBookingsId) {
				final PreparedStatement statement = this.con.prepareStatement(CLEAR);
				statement.setInt(1, id);
				System.out.println("statement: " + statement);
				statement.execute();
			}
			
		} catch(Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	
	public boolean isEverythingOk() {
		return this.isEverythingOk;
	}
}
