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
	public Collection<Track> getAllTracks() {
		// TODO Auto-generated method stub
		return dao.getAllTracks();
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

}