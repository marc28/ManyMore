package com.serviceInterface;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.entities.User;

@Local
@Stateless
@WebService
public interface IUserService {

	public void addUser(String email, String password);

	public User checkUserDetails(String email, String password);
}
