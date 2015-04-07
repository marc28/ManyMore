package com.daoInterface;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.entities.PlayList;


@Local
@Stateless
public interface IPlaylistDAO {

	void insertPlayListInformation(Collection<PlayList> playlist);
	Collection<PlayList> returnAllPlaylistsNames(int id);
	Collection<PlayList> getAllPlayListsWithTracks(int id);
	void removePlaylist(String id);
	void saveEditPlaylist(String id, String name);
	Collection<String>getNamesFromPlayListOnly(int id);
	void insertTrackIntoPlayList(int id,String trackName, String playlistName);
	void removeTrackFromPlaylist(int id, String trackName, String playlistName);
}
