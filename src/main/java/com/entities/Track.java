package com.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


@Entity
public class Track {

	@Id
	@Column
	private int trackID;
	private String name = "Unknown Track Name";
	private String artist = "Unknown artist";
	private String album = "Unknown album";
	private String genre = "Unknown Genre";
	private String year = "Unknown year";
	
	@ManyToMany(mappedBy = "tracks",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<PlayList> playlists = new ArrayList<PlayList>();
	
	@ManyToOne
	@JoinColumn(name = "lib_tid")
	@JsonBackReference
	private Library userTrack;
	
	
	public int getTrackID() {
		return trackID;
	}

	public void setTrackID(int trackID) {
		this.trackID = trackID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@JsonIgnore
	public List<PlayList> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<PlayList> playlists) {
		this.playlists = playlists;
	}

	@XmlTransient
	public Library getUserTrack() {
		return userTrack;
	}

	public void setUserTrack(Library userTrack) {
		this.userTrack = userTrack;
	}

	
	
	
	
}
