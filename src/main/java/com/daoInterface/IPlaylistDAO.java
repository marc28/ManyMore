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
	void removePlaylist(int id);
	void saveEditPlaylist(int id, String name);
	Collection<String>getNamesFromPlayListOnly(int id);
	void insertTrackIntoPlayList(String trackName, String playlistName);
	void removeTrackFromPlaylist(String trackName, String playlistName);
}
