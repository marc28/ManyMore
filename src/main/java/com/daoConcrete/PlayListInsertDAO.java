package com.daoConcrete;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.daoInterface.IPlaylistDAO;
import com.entities.PlayList;

@Local
@Stateless
public class PlayListInsertDAO implements IPlaylistDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public void insertPlayListInformation(Collection<PlayList> playlist) {
		int count = 0;
		Query q = em.createQuery("from PlayList");
		List<PlayList> playlists = q.getResultList();
		for (PlayList p : playlist) {
			System.out.println(p.getPlaylistId() + " -------------- "
					+ p.getName());
			if (!playlists.contains(p)) {
				em.merge(p);
				System.out.println("Inserted: " + (count++));
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<PlayList> returnAllPlaylistsNames() {
		/*return (Collection<PlayList>) em
				.createQuery("from PlayList p where p.id = :id")
				.setParameter("id", id).getResultList();*/
		return (Collection<PlayList>)em.createQuery("from PlayList").getResultList();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<PlayList> getAllPlayListsWithTracks() {
		return (Collection<PlayList>) em.createQuery("select p.name, p.playlistId, t.name,t.album, t.artist"
				+ " FROM PlayList p "
				+ " inner join p.tracks t ORDER BY p.playlistId"
				).getResultList();
	}

	@Override
	public void removePlaylist(int id) {
		PlayList playlist = (PlayList)em.createQuery("from PlayList p where p.id = :id").setParameter("id", id).getSingleResult();
		//System.out.println(playlist.getName());
		em.remove(playlist);
		
	}

	@Override
	public void saveEditPlaylist(int id, String name) {
		PlayList playlist = (PlayList)em.createQuery("from PlayList p where p.id = :id").setParameter("id", id).getSingleResult();
		playlist.setName(name);
		em.merge(playlist);
		System.out.println("Playlist has been added");
	}

	

}
