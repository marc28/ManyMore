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
	private int playlistId;
	private String name;
	
	@ManyToMany
	@JoinTable(name ="playlist_track",
			joinColumns={@JoinColumn(name="PLAY_ID", referencedColumnName="playlistId")},
			 inverseJoinColumns={@JoinColumn(name="TRACK_ID", referencedColumnName="trackID")})
	private List<Track> tracks = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "user_pid")
	@JsonBackReference
	private User userPlayList;
	
	//Method to add Tracks to PlayList
	public void addTrackToPlayList(Track t){
		tracks.add(t);
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
	public User getUserPlayList() {
		return userPlayList;
	}

	public void setUserPlayList(User userPlayList) {
		this.userPlayList = userPlayList;
	}


	
	
}
