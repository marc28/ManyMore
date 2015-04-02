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

import com.entities.Track;
import com.entities.User;
import com.serviceInterface.ITrackService;
import com.serviceInterface.IUserService;

@Path("/tracks")
public class TrackREST {
	@EJB
	private ITrackService service;

	@EJB
	private IUserService userService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Track> hello(@QueryParam("libid") String userEmail) {
		if (userEmail != null) {
			User u = userService.getUserEmail(userEmail);
			if (u != null) {
				return service.getAllTracks(u.getLibraryid());
			}
		}
		Collection<Track> nothingFound = new ArrayList<Track>();
		return nothingFound; // return an empty list
	}

	@GET
	@Path("/tid")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeTrack(@QueryParam("tid") String id) {
		service.removeTrack(id);
	}

	@GET
	@Path("/editandsave")
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveEditTrack(@QueryParam("tid") String id,
			@QueryParam("name") String name,
			@QueryParam("artist") String artist,
			@QueryParam("album") String album) {
		service.saveEditTrack(id, name, artist, album);
	}

}
