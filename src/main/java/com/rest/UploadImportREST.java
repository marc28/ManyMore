package com.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.entities.PlayList;
import com.entities.Track;
import com.readInExcel.FileUploadForm;
import com.readInExcel.ReadInXML;
import com.serviceInterface.IPlaylistService;
import com.serviceInterface.ITrackService;

@Path("/files")
public class UploadImportREST {
	

	@EJB
	private ITrackService service;
	
	@EJB
	private IPlaylistService service2;

	
	@POST
	@Path("/upload")
	@Consumes(MediaType.APPLICATION_XML)
	public void uploadFile(@MultipartForm FileUploadForm form) {
		String filename = "C:/Users/marc/Documents/tracks.xml";
		if (form == null)
			filename = "null.txt";
		try {
			writeFile(form.getData(), filename);
		} catch (IOException e) {
			e.printStackTrace();

		}
		System.out.println("File Created");

	}
	
	private void writeFile(byte[] content, String fileName) throws IOException {
		File file = new File(fileName);
		if (file.exists() == false) {
			file.createNewFile();
		}
		FileOutputStream fop = new FileOutputStream(file);
		fop.write(content);
		fop.flush();
		fop.close();

	}
	
	@POST
	@Path("/import")
	public void importXML(){
		ReadInXML rin = new ReadInXML("C:/Users/marc/Documents/itunes.xml");
		
		
		//Put in the tracks
		Collection<Track> tracks = rin.getAllTrackInoframtion();
		service.insertTrackInformation(tracks);
		//Put in the play lists
		
		Collection<PlayList> playlists = rin.getPlayListInformation();
		service2.insertPlayListInformation(playlists);
		
		
	}

}

