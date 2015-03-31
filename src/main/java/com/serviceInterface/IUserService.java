package com.serviceInterface;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.entities.User;

@Local
@Stateless
@WebService
public interface IUserService {

	User addUser(String email, String password);
	User checkUserDetails(String email, String password);
	public User getUserEmail(String email);
}
