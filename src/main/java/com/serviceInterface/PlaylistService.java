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
	public Collection<PlayList> returnAllPlaylistsNames() {
		return dao.returnAllPlaylistsNames();
	}

	@Override
	public void removePlaylist(int id) {
		 dao.removePlaylist(id);
	}
	
	
	//Getters and setters
	public IPlaylistDAO getDao() {
		return dao;
	}

	public void setDao(IPlaylistDAO dao) {
		this.dao = dao;
	}

	@Override
	public Collection<PlayList> getAllPlayListsWithTracks() {
		return dao.getAllPlayListsWithTracks();
	}

	@Override
	public void saveEditPlaylist(int id, String name) {
		dao.saveEditPlaylist(id,name);
		
	}

	

	

}
