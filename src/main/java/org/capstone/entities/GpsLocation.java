package org.capstone.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gps_location")
public class GpsLocation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_gps")
	private int id;
	
	@Column ( name ="latitude")
	private double latitude;
	
	@Column (name="longitude")
	private double longitude;
	
	@Column ( name="timestamp")
	private Timestamp time;
	
	@Column ( name = "address")
	private String address;
	
	@ManyToOne
	@JoinColumn (name = "user_id")
	User user;
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public Timestamp getTime() {
		return this.time;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

}
