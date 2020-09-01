
package xyz.willz.entities;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class AdminParking {
	final private Integer id;
	private String parkingName;
	private String address;
	final private String latitude;
	final private String longitude;
	private Integer total, vacant, occupied;
	private Integer two_wheeler, four_wheeler;
	
	public AdminParking(HttpServletRequest request) {
		if(request.getParameter("parkingId") != null) {
			this.id = Integer.parseInt(request.getParameter("id"));
		} else {
			this.id = -1;
		}
		
		this.parkingName = request.getParameter("parkingName");
		this.address = request.getParameter("address");
		this.latitude = request.getParameter("latitude");
		this.longitude = request.getParameter("longitude");
		this.total = Integer.parseInt(request.getParameter("total"));
		this.vacant = Integer.parseInt(request.getParameter("vacant"));
		this.occupied = Integer.parseInt(request.getParameter("occupied"));
		this.two_wheeler = Integer.parseInt(request.getParameter("two_wheeler"));
		this.four_wheeler = Integer.parseInt(request.getParameter("four_wheeler"));
	}

//	public AdminParking(final Integer id, final String parkingName, final String latitude, final String longitude, final Integer totalParking, final Integer vacantParking, final Integer occupied) {
//		this.id = id;
//		this.parkingName = parkingName;
//		this.latitude = latitude;
//		this.longitude = longitude;
//		this.total = totalParking;
//		this.vacant = vacantParking;
//		this.occupied = occupied;
//	}
	public AdminParking(HashMap<String, Object> hmap) {
		this.id = (int)hmap.get("id");
		this.parkingName = (String)hmap.get("parkingName");
		this.address = (String)hmap.get("address");
		this.latitude = (String)hmap.get("latitude");
		this.longitude = (String)hmap.get("longitude");
		this.total = (int)hmap.get("total");
		this.vacant = (int)hmap.get("vacant");
		this.occupied = (int)hmap.get("occupied");
		this.two_wheeler = (int)hmap.get("two_wheeler");
		this.four_wheeler = (int)hmap.get("four_wheeler");
	}
	
	@Override
	public String toString() {
		return "AdminParking [id=" + id + ", parkingName=" + parkingName + ", address=" + address + ", latitude="
				+ latitude + ", longitude=" + longitude + ", total=" + total + ", vacant=" + vacant + ", occupied="
				+ occupied + ", two_wheeler=" + two_wheeler + ", four_wheeler=" + four_wheeler + "]";
	}

	public Integer getTwo_wheeler() {
		return two_wheeler;
	}

	public void setTwo_wheeler(Integer two_wheeler) {
		this.two_wheeler = two_wheeler;
	}

	public Integer getFour_wheeler() {
		return four_wheeler;
	}

	public void setFour_wheeler(Integer four_wheeler) {
		this.four_wheeler = four_wheeler;
	}

	public Integer getId() {
		return id;
	}
	public String getParkingName() {
		return parkingName;
	}
	public String getLatitude() {
		return latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setParkingName(final String parkingName) {
		this.parkingName = parkingName;
	}
	public void setTotal(Integer totalParking) {
		this.total = totalParking;
	}

	public Integer getVacant() {
		return vacant;
	}

	public void setVacant(Integer vacantParking) {
		this.vacant = vacantParking;
	}

	public Integer getOccupied() {
		return occupied;
	}

	public void setOccupied(Integer occupied) {
		this.occupied = occupied;
	}
	public Integer getTotal() {
		return total;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

	
}
