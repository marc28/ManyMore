package com.rest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.entities.Library;
import com.entities.PlayList;
import com.entities.Track;
import com.entities.User;
import com.readInExcel.FileUploadForm;
import com.readInExcel.ReadInXML;
import com.serviceInterface.ILibraryService;
import com.serviceInterface.IPlaylistService;
import com.serviceInterface.ITrackService;
import com.serviceInterface.IUserService;

@Path("/files")
public class UploadImportREST {
	

	@EJB
	private ITrackService service;
	
	@EJB
	private IPlaylistService service2;

	@EJB
	private ILibraryService libService;
	
	@EJB
	private IUserService userService;
	
	
	
	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	public void uploadFile(@MultipartForm FileUploadForm form) {
		String filename = "C:/Users/marc/Documents/tracks.xml";
		System.out.println("1");
		if (form == null)
			filename = "null.xml";
		try {
			System.out.println("2");
			writeFile(form.getData(), filename);
		} catch (IOException e) {
			e.printStackTrace();

		}
		System.out.println("3");
		System.out.println("File Created");

	}
	
	private void writeFile(byte[] content, String fileName) throws IOException {
		System.out.println("4");
		File file = new File(fileName);
		if (file.exists() == false) {
			System.out.println("5");
			file.createNewFile();
		}
		/*FileOutputStream fop = new FileOutputStream(file);
		fop.write(content);
		fop.flush();
		fop.close();
*/
		char[] buffer = new char[content.length];
		System.out.println("6");
		 for(int i = 0; i < buffer.length; i++) {
		  buffer[i] = (char)content[i];
		 }

		
		System.out.println("Here");
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
		out.write(buffer);
		out.flush();
		out.close();
		
	}
	
	@POST
	@Path("/import")
	public void importXML(@QueryParam("useremail") String email){
		System.out.println("EMAIL::::::::::::::: " + email);
		User u = userService.getUserEmail(email);
		Library l = new Library();
		l.setLibID(u.getLibraryid());
		l.setUser(u);
		libService.insertLibrary(l);
		//Put in the tracks
		ReadInXML rin = new ReadInXML("C:/Users/marc/Documents/tracks.xml",l.getLibID());
		Collection<Track> tracks = rin.getAllTrackInoframtion();
		service.insertTrackInformation(tracks);
		//Put in the play lists
		
		Collection<PlayList> playlists = rin.getPlayListInformation();
		service2.insertPlayListInformation(playlists);
		
		
	}

}