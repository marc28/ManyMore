package com.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class PlayList {
	@Id
	private String playlistPersistenceId;
	private int playlistId;
	private String name;
	
	@ManyToMany
	@JoinTable(name ="playlist_track",
			joinColumns={@JoinColumn(name="PLAY_ID", referencedColumnName="playlistPersistenceId")},
			 inverseJoinColumns={@JoinColumn(name="TRACK_ID", referencedColumnName="trackPersisId")})
	private List<Track> tracks = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "lib_pid")
	@JsonBackReference
	private Library userPlayList;
	
	//Method to add Tracks to PlayList
	public void addTrackToPlayList(Track t){
		tracks.add(t);
	}
	
	

	public String getPlaylistPersistenceId() {
		return playlistPersistenceId;
	}



	public void setPlaylistPersistenceId(String playlistPersistenceId) {
		this.playlistPersistenceId = playlistPersistenceId;
	}



	public int getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	@XmlTransient
	public Library getUserPlayList() {
		return userPlayList;
	}

	public void setUserPlayList(Library userPlayList) {
		this.userPlayList = userPlayList;
	}


	
	
}
