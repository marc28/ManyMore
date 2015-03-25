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
		return (Collection<PlayList>) em
				.createQuery("from PlayList p where p.id = :id")
				.setParameter("id", id).getResultList();

	}

	/**
	 * 
	 * select playlist.name, playlist.playlistId, track.name AS TRACK_NAME,
	 * track.album, track.artist from playlist,track, playlist_track where
	 * playlist.playlistId = playlist_track.PLAY_ID AND track.trackID =
	 * playlist_track.TRACK_ID order by playlist.playlistId;
	 * 
	 * "select p.name, p.playlistId, t.name,t.album, t.artist"
				+ " FROM Track t, PlayList p, PlayList.tracks pt"
				+ " where p.playlistId = pt.PLAY_ID"
				+ " AND t.trackID = pt.TRACK_ID ORDER BY p.playlistId"
	 * 
	 */

	@Override
	public Collection<PlayList> getAllPlayListsWithTracks() {
		return (Collection<PlayList>) em.createQuery("select p.name, p.playlistId, t.name,t.album, t.artist"
				+ " FROM PlayList p "
				+ " inner join p.tracks t ORDER BY p.playlistId"
				).getResultList();
	}

}
