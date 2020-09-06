package xyz.willz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xyz.willz.entities.AdminParking;

public class ParkingsDao {
	private final String url;
	private final String uName;
	private final String pWord;
	private final String driverName;
	private boolean isEverythingOk = true;

	private Connection con;
	
	public boolean deleteParking(final Integer id) {
		if(!isEverythingOk) return false;
		final String sql = "DELETE FROM parkings WHERE id = ?";
		
		try {
			final PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			statement.execute();
			
		}catch(Exception e) {
			System.out.println("Couldn't delete the parking" + e);
			return false;
		}
		return true;
		
	}
	
	public List<AdminParking> findParkings(final String address) {
		List<AdminParking> parkings = new ArrayList<>();
		if(isEverythingOk == false) {
			return parkings;
		}
		System.out.println("Going to fetch Parkings");
		final String sql = "SELECT * FROM parkings WHERE address LIKE ?"; 
		try {
			
			PreparedStatement st = this.con.prepareStatement(sql);
			st.setString(1, "%" +address + "%");
			
			final ResultSet resultSet = st.executeQuery();
			System.out.println("fetched Parkings");
			while(resultSet.next()) {
				HashMap<String, Object> hmap = new HashMap<>();
				
				hmap.put("id", resultSet.getObject("id"));
				hmap.put("parkingName", resultSet.getObject("parkingName"));
				hmap.put("address", resultSet.getObject("address"));
				hmap.put("latitude", resultSet.getObject("latitude"));
				hmap.put("longitude", resultSet.getObject("longitude"));
				hmap.put("total", resultSet.getObject("total"));
				hmap.put("vacant", resultSet.getObject("vacant"));
				hmap.put("occupied", resultSet.getObject("occupied"));
				hmap.put("two_wheeler", resultSet.getObject("two_wheeler"));
				hmap.put("four_wheeler", resultSet.getObject("four_wheeler"));
				
				AdminParking adminParkingObj = new AdminParking(hmap);
				parkings.add(adminParkingObj);
			}
			
		} catch(Exception e) {
			System.out.println("Exception in parkings Dao: " + e);
		}
		return parkings;
	}
	
	public ParkingsDao() {
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
}
