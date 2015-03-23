package com.rest;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.entities.PlayList;
import com.serviceInterface.IPlaylistService;

@Path("/playlist")
public class playlistREST {

	@EJB
	private IPlaylistService service;

	@GET
	@Path("/pid")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<PlayList> forDropdownMenu(@QueryParam("PID") int id) {
		return service.returnAllPlaylistsNames(id);
	}

}
