package org.capstone.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emergency")
public class Emergency implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="emergency_id")
	private int id;
	
	@Column (name = "emergency_contact")
	private String contact;
	
	@ManyToOne
	@JoinColumn (name = "user_id")
	User user;
	
	public int getId() {
		return this.id;
	}
	
	public Emergency(String contact, User user) {
		this.contact = contact;
		this.user = user;
	}
	
	public Emergency() {
		
	}
	
	public void setEmergencyContact(String contact) {
		this.contact = contact;
	}
	
	public String getEmergencyContact(){
		return this.contact;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
