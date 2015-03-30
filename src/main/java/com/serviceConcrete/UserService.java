package com.serviceConcrete;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.daoInterface.IUserDAO;
import com.entities.User;
import com.serviceInterface.IUserService;

@Stateless
@Local
public class UserService implements IUserService{

	@EJB
	private IUserDAO dao;
	
	@Override
	public void addUser(String email, String password) {
		dao.addUser(email, password);
	}

	@Override
	public User checkUserDetails(String email, String password) {
		return dao.checkUserDetails(email,password);
	}

	@Override
	public User getUserEmail(String email) {
		return dao.getUserEmail(email);
	}

}