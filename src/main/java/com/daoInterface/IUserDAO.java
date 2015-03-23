package com.daoInterface;

import javax.ejb.Local;

import com.entities.User;

@Local
public interface IUserDAO {

	public void addUser(String email, String password);

	public User checkUserDetails(String email, String password);
}
