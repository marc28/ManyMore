package com.serviceInterface;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.daoInterface.IPlaylistDAO;
import com.entities.PlayList;

@Local 
@WebService
@Stateless
public class PlaylistService implements IPlaylistService{
	@EJB
	private IPlaylistDAO dao;
	
	
	@Override
	public void insertPlayListInformation(Collection<PlayList> playlists) {
		dao.insertPlayListInformation(playlists);
		
	}

	@Override
	public Collection<PlayList> returnAllPlaylistsNames(int id) {
		return dao.returnAllPlaylistsNames(id);
	}

	public IPlaylistDAO getDao() {
		return dao;
	}

	public void setDao(IPlaylistDAO dao) {
		this.dao = dao;
	}

	

}
