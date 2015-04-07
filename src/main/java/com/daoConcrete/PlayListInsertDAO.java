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
import com.entities.Track;

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
	public Collection<PlayList> returnAllPlaylistsNames(int id) {
	
		return (Collection<PlayList>)em.createQuery("from PlayList p where p.userPlayList.libID = :id").setParameter("id", id).getResultList();
	}
	

	@Override
	public Collection<String> getNamesFromPlayListOnly(int id) {
		// TODO Auto-generated method stub
		return (Collection<String>)em.createQuery("select p.name from PlayList p where p.userPlayList.libID = :id").setParameter("id", id).getResultList();
	}
	

	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<PlayList> getAllPlayListsWithTracks(int id) {
		return (Collection<PlayList>) em.createQuery("select p.name, t.name,t.album, t.artist"
				+ " FROM PlayList p "
				+ " inner join p.tracks t WHERE p.userPlayList.libID = :id ORDER BY p.playlistId"
				).setParameter("id", id).getResultList();
	}

	@Override
	public void removePlaylist(String id) {
		PlayList playlist = (PlayList)em.createQuery("from PlayList p where p.id = :id").setParameter("id", id).getSingleResult();
		//System.out.println(playlist.getName());
		em.remove(playlist);
		
	}

	@Override
	public void saveEditPlaylist(String id, String name) {
		PlayList playlist = (PlayList)em.createQuery("from PlayList p where p.id = :id").setParameter("id", id).getSingleResult();
		playlist.setName(name);
		em.merge(playlist);
		System.out.println("Playlist has been added");
	}

	@Override
	public void insertTrackIntoPlayList(int id,String trackName, String playlistName) {
		List<Track> tracks = (List<Track>)em.createQuery("from Track t where t.userTrack.libID = :id AND t.name LIKE :trackName").setParameter("id", id).setParameter("trackName", "%"+ trackName +"%").getResultList();
		//System.out.println("TRACK SIZE: " + tracks.size());
		List<PlayList> playlist = (List<PlayList>)em.createQuery("from PlayList p where p.userPlayList.libID = :id AND p.name LIKE :playlistName").setParameter("id", id).setParameter("playlistName","%"+ playlistName +"%").getResultList();
		//System.out.println("PLAYLIST IZE: " + playlist.size());
		if(tracks.size()>0 && playlist.size()>0){
			PlayList p = playlist.get(0);//.add(tracks.get(0));
			Track t = tracks.get(0);
			p.getTracks().add(t);
			em.merge(p);
		}else{
			System.out.println("NNNOOOOOPPPPEEEE");
		}
	}

	@Override
	public void removeTrackFromPlaylist(int id,String trackName, String playlistName) {
		List<Track> tracks = (List<Track>)em.createQuery("from Track t where t.userTrack.libID = :id AND t.name LIKE :trackName").setParameter("id", id).setParameter("trackName", "%"+ trackName +"%").getResultList();
		List<PlayList> playlist = (List<PlayList>)em.createQuery("from PlayList p where p.userPlayList.libID = :id AND p.name LIKE :playlistName").setParameter("id", id).setParameter("playlistName","%"+ playlistName +"%").getResultList();
		if(tracks.size()>0 && playlist.size()>0){
			
			PlayList p = playlist.get(0);//.add(tracks.get(0));
			Track t = tracks.get(0);
			p.getTracks().remove(t);
			em.merge(p);
		}else{
			System.out.println("Problem removing");
		}
		
	}


	

}
