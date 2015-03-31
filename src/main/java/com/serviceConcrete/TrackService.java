package com.serviceConcrete;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebService;

import com.daoInterface.ITrackDAO;
import com.entities.Track;
import com.serviceInterface.ITrackService;

@Local (ITrackService.class)
@WebService(endpointInterface="com.serviceInterface.ITrackService")
@Stateless
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class TrackService implements ITrackService{
	
	@EJB
	ITrackDAO dao;
	
	@Override
	public void addTrackToDataBase(Track track) {
		dao.addTrackToDataBase(track);
	}
	
	@Override
	public Collection<Track> getAllTracks(int id) {
		// TODO Auto-generated method stub
		return dao.getAllTracks(id);
	}

	public ITrackDAO getDao() {
		return dao;
	}

	public void setDao(ITrackDAO dao) {
		this.dao = dao;
	}

	@Override
	public void insertTrackInformation(Collection<Track> tracks) {
		dao.insertTrackInformation(tracks);
		
	}

	/**
	 * Method to delete Track
	 */
	@Override
	public void removeTrack(int id) {
		dao.removeTrack(id);		
	}

	@Override
	public void saveEditTrack(int id,String name,String artist,String album) {
		dao.saveEditTrack(id,name,artist,album);
		
	}

	@Override
	public Collection<Track> getTrackNamesOnly(int id, String title) {
		return dao.getTrackNamesOnly(id,title);
	}

}
