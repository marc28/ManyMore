package com.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.entities.User;
import com.serviceInterface.IUserService;

@Path("/user")
public class UserREST {
	
	@EJB
	private IUserService service;
	
	@POST
	@Path("/addUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public User addUser(User user){
		//User user = new User(email, password,position);
		return service.addUser(user.getEmail(),user.getPassword());
	}
	
	@POST
	@Path("/auth")
	@Produces(MediaType.APPLICATION_JSON)
	public User loginUser(User user){
		return service.checkUserDetails(user.getEmail(), user.getPassword());
	}

}