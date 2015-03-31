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
	public Collection<Track> getAllTracks(int id) {
		Query query = em.createQuery("from Track t where t.userTrack.libID = :id").setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public void insertTrackInformation(Collection<Track> tracks) {
		int count = 0;
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

	@Override
	public void removeTrack(int id) {
		Track removetrack = (Track) em.createQuery("from Track t where t.id = :id").setParameter("id",id).getSingleResult();
		System.out.println(removetrack.getName());
		em.remove(removetrack);
		
	}

	@Override
	public void saveEditTrack(int id,String name,String artist,String album) {
		
		Track track = (Track) em.createQuery("from Track t where t.id = :id").setParameter("id",id).getSingleResult();
		track.setName(name);
		track.setArtist(artist);
		track.setAlbum(album);
		System.out.println(name);
		em.merge(track);
		System.out.println("name has been changed!!");
		
	}

	@Override
	public Collection<Track> getTrackNamesOnly(int id) {
		return (Collection<Track>)em.createQuery("from Track t where t.userTrack.libID = :id").setParameter("id", id).getResultList();
	}
}
