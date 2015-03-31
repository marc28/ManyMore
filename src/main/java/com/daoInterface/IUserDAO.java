package com.daoInterface;

import javax.ejb.Local;

import com.entities.User;

@Local
public interface IUserDAO {

	User addUser(String email, String password);

	User checkUserDetails(String email, String password);
	
	User getUserEmail(String email);
}
