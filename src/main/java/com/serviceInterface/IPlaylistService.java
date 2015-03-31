package com.serviceInterface;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.entities.PlayList;

@Stateless
@Local
@WebService
public interface IPlaylistService {

	public void insertPlayListInformation(Collection<PlayList> playlists);
	Collection<PlayList> returnAllPlaylistsNames(int id);
	public Collection<PlayList> getAllPlayListsWithTracks(int id);
	public void removePlaylist(int id);
	public void saveEditPlaylist(int id, String name);
	Collection<String>getNamesFromPlayListOnly(int id);
	void insertTrackIntoPlayList(String trackName, String playlistName);
	public void removeTrackFromPlaylist(String trackName, String playlistName);
}
