package com.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@Column
	private int libraryid;
	private String email;
	private String password;
	
	@OneToMany(mappedBy="userTrack",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Track> tracks = new ArrayList<>();
	
	@OneToMany(mappedBy="userPlayList",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<PlayList> playlist = new ArrayList<>();

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

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	public List<PlayList> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(List<PlayList> playlist) {
		this.playlist = playlist;
	}


	
	
	
}
