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
}
