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

	@Override
	public void removePlaylist(String id) {
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
	public Collection<PlayList> getAllPlayListsWithTracks(int id) {
		return dao.getAllPlayListsWithTracks(id);
	}

	@Override
	public void saveEditPlaylist(String id, String name) {
		dao.saveEditPlaylist(id,name);
		
	}

	@Override
	public Collection<String> getNamesFromPlayListOnly(int id) {
		return dao.getNamesFromPlayListOnly(id);
	}

	@Override
	public void insertTrackIntoPlayList(int id,String trackName, String playlistName) {
		dao.insertTrackIntoPlayList(id,trackName, playlistName);
		
	}

	@Override
	public void removeTrackFromPlaylist(int id, String trackName, String playlistName) {
		dao.removeTrackFromPlaylist(id,trackName,playlistName);
		
	}

	

	

}
