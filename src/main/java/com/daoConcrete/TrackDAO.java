package com.daoConcrete;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.daoInterface.ITrackDAO;
import com.entities.Track;

@Local
@Stateless
public class TrackDAO implements ITrackDAO{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void addTrackToDataBase(Track track) {

		Query query = em.createQuery("from Track");
		List<Track> trackList = query.getResultList();

		if (!trackList.contains(track)) // .contains is handy for a list
			em.merge(track);

	}
	
	@Override
	public Collection<Track> getAllTracks() {
		Query query = em.createQuery("from Track");
		return query.getResultList();
	}

	@Override
	public void insertTrackInformation(Collection<Track> tracks) {
		int count = 0;
		System.out.println("£££££££££££££££££££££: " + tracks.size());
		Query q = em.createQuery("from Track");
		List<Track> trackList = q.getResultList();
		for(Track t : tracks){
			/*em.persist(t.deepCopy());*/
			if(!trackList.contains(t)){
			em.merge(t);
			System.out.println("Inserted: " + (count++));
			}
		}
		
	}
}
