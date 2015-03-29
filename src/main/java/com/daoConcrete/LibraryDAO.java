package com.daoConcrete;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.daoInterface.ILibraryDAO;
import com.entities.Library;
import com.entities.Track;

@Local
@Stateless
public class LibraryDAO implements ILibraryDAO{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void insertLibrary(Library library) {
		Query query = em.createQuery("from Library");
		List<Library> libList = query.getResultList();

		if (!libList.contains(library)) // .contains is handy for a list
			em.merge(library);
		
	}

}
