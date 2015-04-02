package com.rest;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.entities.PlayList;
import com.entities.Track;
import com.entities.User;
import com.serviceInterface.IPlaylistService;
import com.serviceInterface.ITrackService;
import com.serviceInterface.IUserService;

@Path("/playlist")
public class playlistREST {

	@EJB
	private IPlaylistService service;

	@EJB
	private ITrackService trackService;

	@EJB
	private IUserService userService;

	@GET
	@Path("/playlistnames")
	@Produces(MediaType.APPLICATION_JSON)
	// public Collection<PlayList> forDropdownMenu(@QueryParam("PID") int id) {
	public Collection<PlayList> returnAllPlaylistsNames(
			@QueryParam("libid") String userEmail) {
		if (userEmail != null) {
			User u = userService.getUserEmail(userEmail);
			if (u != null) {
				return service.returnAllPlaylistsNames(u.getLibraryid());
			}
		}
		Collection<PlayList> nothingFound = new ArrayList<PlayList>();
		return nothingFound; // return an empty list
	}

	@GET
	@Path("/insertingTrackToPlaylist")
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertTrackIntoPlayList(
			@QueryParam("trackname") String trackName,
			@QueryParam("playlist") String playlistName) {
		System.out.println("GAANNNNNJJJJJAAAAAA");
		service.insertTrackIntoPlayList(trackName, playlistName);
	}

	@GET
	@Path("/removeTrackFromPlaylist")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeTrackFromPlaylist(@QueryParam("trackname") String trackName,
			@QueryParam("playlist") String playlistName) {

		service.removeTrackFromPlaylist(trackName, playlistName);
	}

	@GET
	@Path("/namesdropdown")
	@Produces(MediaType.APPLICATION_JSON)
	// public Collection<PlayList> forDropdownMenu(@QueryParam("PID") int id) {
	public Collection<String> getNamesFromPlayListOnly(
			@QueryParam("libid") String userEmail) {
		if (userEmail != null) {
			User u = userService.getUserEmail(userEmail);
			if (u != null) {
				return service.getNamesFromPlayListOnly(u.getLibraryid());
			}
		}
		Collection<String> nothingFound = new ArrayList<String>();
		return nothingFound; // return an empty list
	}

	@GET
	@Path("/tracknamesdropdown")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Track> getTrackNamesOnly(
			@QueryParam("libid") String userEmail,
			@QueryParam("title") String title) {
		if (userEmail != null) {
			User u = userService.getUserEmail(userEmail);
			if (u != null) {
				return trackService.getTrackNamesOnly(u.getLibraryid(),title);
			}
		}
		Collection<Track> nothingFound = new ArrayList<Track>();
		return nothingFound; // return an empty list
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<PlayList> getAllPlayListsWithTracks(
			@QueryParam("libid") String userEmail) {
		if (userEmail != null) {
			User u = userService.getUserEmail(userEmail);
			if (u != null) {
				return service.getAllPlayListsWithTracks(u.getLibraryid());
			}
		}
		Collection<PlayList> nothingFound = new ArrayList<PlayList>();
		return nothingFound; // return an empty list
	}

	@GET
	@Path("/pid")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removePlaylist(@QueryParam("pid") String id) {
		service.removePlaylist(id);
	}

	@GET
	@Path("/editandsave")
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveEditPlaylist(@QueryParam("pid") String id,
			@QueryParam("name") String name) {
		service.saveEditPlaylist(id, name);
	}
}
