package com.daoConcrete;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.daoInterface.IUserDAO;
import com.entities.User;

@Local
@Stateless
public class UserDAO implements IUserDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User addUser(String email, String password) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		Query q = em.createQuery("from User u where u.email = :email").setParameter("email", email);
		List<User> userList = q.getResultList();
		boolean same = false;
		for(User i : userList){
			if(i.getEmail().equals(user.getEmail())){
				same = true;
			}
		}
		if(!same){
			em.persist(user);
			return user;
		}	
		return null;
	}

	@Override
	public User checkUserDetails(String email, String password) {
		Query query = em.createQuery("from User u where u.email = :email");
		query.setParameter("email", email);
		List<User> returnedUser = query.getResultList();
		if (returnedUser.size() > 0	&& validatePassword(returnedUser.get(0), password))
			return returnedUser.get(0);
		return null;
	}

	private boolean validatePassword(User user, String password) {
		return user.getPassword().equals(password);
	}

	
	@Override
	public User getUserEmail(String email) {
		Query q = em.createQuery("from User u where u.email = :email").setParameter("email", email);
		User userRetruned = null;
		try {
			userRetruned = (User) q.getSingleResult();
		} catch (NoResultException nre) {
			//Nothing to handle really!!
		}
		if (userRetruned != null) {
			User u = em.find(User.class, userRetruned.getLibraryid());
			return u;
		}
		return userRetruned;
	}

}
