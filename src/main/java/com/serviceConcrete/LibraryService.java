package com.serviceConcrete;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebService;

import com.daoInterface.ILibraryDAO;
import com.entities.Library;
import com.serviceInterface.ILibraryService;

@Local (ILibraryService.class)
@WebService(endpointInterface="com.serviceInterface.ILibraryService")
@Stateless
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class LibraryService implements ILibraryService{
	
	@EJB
	ILibraryDAO dao;

	@Override
	public void insertLibrary(Library library) {
		dao.insertLibrary(library);
	}

	public ILibraryDAO getDao() {
		return dao;
	}

	public void setDao(ILibraryDAO dao) {
		this.dao = dao;
	}
	
	

}
