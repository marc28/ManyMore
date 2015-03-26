package com.rest;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.entities.Track;
import com.serviceInterface.ITrackService;

@Path("/tracks")
public class TrackREST {
	@EJB
	private ITrackService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Track> hello(){
		return service.getAllTracks();
	}
	
	@GET
	@Path("/tid")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeTrack(@QueryParam("tid") int id){
		service.removeTrack(id);
	}
	
	@GET
	@Path("/editandsave")
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveEditTrack(@QueryParam("tid") int id, @QueryParam("name") String name,
			@QueryParam("artist") String artist, @QueryParam("album") String album){
		service.saveEditTrack(id,name,artist,album);
	}
	
	

}
