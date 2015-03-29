package com.serviceInterface;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.entities.Library;

@Local
@WebService
@Stateless
public interface ILibraryService {
	
	public void insertLibrary(Library library);

}
