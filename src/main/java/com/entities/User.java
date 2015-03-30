package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name="userId")
	private int libraryid;
	private String email;
	private String password;
	
/*	@OneToOne(fetch=FetchType.LAZY,mappedBy="user")
	private Library library; //join user To Library
*/	
	

	public int getLibraryid() {
		return libraryid;
	}

	public void setLibraryid(int libraryid) {
		this.libraryid = libraryid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
