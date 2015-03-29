package com.rest;

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
import com.serviceInterface.IPlaylistService;
import com.serviceInterface.ITrackService;

@Path("/playlist")
public class playlistREST {

	@EJB
	private IPlaylistService service;
	
	@EJB
	private ITrackService trackService;

	@GET
	@Path("/playlistnames")
	@Produces(MediaType.APPLICATION_JSON)
	//public Collection<PlayList> forDropdownMenu(@QueryParam("PID") int id) {
	public Collection<PlayList> returnAllPlaylistsNames(){
		return service.returnAllPlaylistsNames();
	}
	
	@GET
	@Path("/namesdropdown")
	@Produces(MediaType.APPLICATION_JSON)
	//public Collection<PlayList> forDropdownMenu(@QueryParam("PID") int id) {
	public Collection<String> getNamesFromPlayListOnly(){
		return service.getNamesFromPlayListOnly();
	}
	
	@GET
	@Path("/tracknamesdropdown")
	@Produces(MediaType.APPLICATION_JSON)
	//public Collection<PlayList> forDropdownMenu(@QueryParam("PID") int id) {
	public Collection<Track> getTrackNamesOnly(){
		return trackService.getTrackNamesOnly();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<PlayList> getAllPlayListsWithTracks(){
		return service.getAllPlayListsWithTracks();
	}
	
	@GET
	@Path("/pid")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removePlaylist(@QueryParam("pid") int id){
		service.removePlaylist(id);
	}
	
	@GET
	@Path("/editandsave")
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveEditPlaylist(@QueryParam("pid") int id,@QueryParam("name") String name){
		service.saveEditPlaylist(id,name);
	}
}
