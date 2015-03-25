package com.daoInterface;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.entities.Track;

@Local
@Stateless
public interface ITrackDAO {
	
	public void addTrackToDataBase(Track track);

	public Collection<Track> getAllTracks();

	public void insertTrackInformation(Collection<Track> tracks);
	
	public void removeTrack(int id);
}
