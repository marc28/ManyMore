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

	@Override
	public Collection<PlayList> returnAllPlaylistsNames(int id) {
		return(Collection<PlayList>)em.createQuery("from PlayList p where p.id = :id")
		.setParameter("id",id).getResultList();

	}

}
