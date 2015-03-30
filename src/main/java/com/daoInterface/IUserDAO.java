package com.daoInterface;

import javax.ejb.Local;

import com.entities.User;

@Local
public interface IUserDAO {

	void addUser(String email, String password);

	User checkUserDetails(String email, String password);
	
	User getUserById(int id);
}
