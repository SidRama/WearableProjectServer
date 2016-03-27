package org.capstone.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@Column(name = "password")
	private String password;
	
	@Column (name = "phonenumber")
	private String phonenumber;
	
	@OneToMany(mappedBy="user")
	private List <Emergency> emergencyContacts;
	
	@OneToMany(mappedBy="user")
	private List <GpsLocation> locations;
	
	public User() {
		
	}
	
	public User(int id,  String password, String phonenumber) {
		super();
		this.id = id;
		
		this.password = password;
		this.phonenumber = phonenumber;
		
	}
	
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhonenumber() {
		return this.phonenumber;
	}
	
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public List<Emergency> getEmergencyContacts() {
		return this.emergencyContacts;
	}
	
	public void setEmergencyContacts(List<Emergency> emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}
	
	public List <GpsLocation> getLocations() {
		return this.locations;
	}
	
	public void setLocations(List <GpsLocation> locations) {
		this.locations = locations;
	}
	
}
	