package xyz.willz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import xyz.willz.security.Password;


public class LoginDao {
	
	private final String url;
	private final String uName;
	private final String pWord;
	private final String driverName;
	private boolean isEverythingOk = true;
	private Connection con;
	
	public LoginDao() {
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
	
	public int valid(String username, String password, String buyer_seller) {
		if(isEverythingOk == false) {
			return -1;
		}
		
		String sql = "select * from " + buyer_seller + " where username=? and password=?";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, username);
			
			final Password passwordObj = new Password();
			final String hashedPassword = passwordObj.getSecurePassword(password);
			
			st.setString(2, hashedPassword);
			
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				int id = (int)rs.getObject("id");
				return id;
			} 
		} catch(Exception e) {
			System.out.println("in exception " + e.getMessage());
		}
		
		return -1;
	}
}
