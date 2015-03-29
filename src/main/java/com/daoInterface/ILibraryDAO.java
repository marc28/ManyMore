package com.daoInterface;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.entities.Library;

@Local
@Stateless
public interface ILibraryDAO {
	public void insertLibrary(Library library);
}
