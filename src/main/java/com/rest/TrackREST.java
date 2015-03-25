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
	
	/*@POST
	@Consumes(MediaType.APPLICATION_XML)
	public void addTrack(Track track){
		service.addTrackToDataBase(track);
	}*/
	
	

}
