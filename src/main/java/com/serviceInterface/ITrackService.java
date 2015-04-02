package com.serviceInterface;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.entities.Track;

@Local
@WebService
@Stateless
public interface ITrackService {

	public void addTrackToDataBase(Track track);
	public Collection<Track> getAllTracks(int id);
	public void insertTrackInformation(Collection<Track> tracks);
	public void removeTrack(String id);
	public void saveEditTrack(String id,String name,String artist,String album);
	Collection<Track>getTrackNamesOnly(int id, String title);
}
