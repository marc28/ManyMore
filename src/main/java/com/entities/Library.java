package com.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Library {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int libID;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user; //Join user one to one
	
	@OneToMany(mappedBy="userTrack",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Collection<Track> tracks = new ArrayList<>();
	
	@OneToMany(mappedBy="userPlayList",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Collection<PlayList> playlist = new ArrayList<>();
	
	
	public int getLibID() {
		return libID;
	}

	public void setLibID(int libID) {
		this.libID = libID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection<Track> getTracks() {
		return tracks;
	}

	public void setTracks(Collection<Track> tracks2) {
		this.tracks = tracks2;
	}

	public Collection<PlayList> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Collection<PlayList> playlist) {
		this.playlist = playlist;
	}
	
	
	
}
