package com.readInExcel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.entities.Library;
import com.entities.PlayList;
import com.entities.Track;
import com.entities.User;

public class ReadInXML {

	SAXBuilder builder;
	File xmlFile;
	int lidId;

	public ReadInXML(String fileName, int libId) {
		builder = new SAXBuilder();
		xmlFile = new File(fileName);
		this.lidId = libId;
	}
	
	public String getLibraryPersistenceId(){
		String libraryId = "";
		Document document;
		try {
			document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement(); // 'plist'
			Element firstDict = rootNode.getChild("dict"); // dict
			List<Element> listFirstDicts = firstDict.getChildren("string");
			libraryId = listFirstDicts.get(listFirstDicts.size()-1).getText();
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return libraryId;
	}

	public ArrayList<Track> getAllTrackInoframtion() {
		Document document;
		Track track = new Track();
		Library lib = new Library();
		lib.setLibID(lidId);
		ArrayList<Track> tracks = new ArrayList<Track>();
		try {
			document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement(); // 'plist'
			Element firstDict = rootNode.getChild("dict"); // dict
			// Getting the secondDict!!
			Element secondDict = firstDict.getChild("dict");

			// Getting all the 'dict' children of secondDict
			List<Element> listSecondDicts = secondDict.getChildren("dict");
			for (int m = 0; m < listSecondDicts.size(); m++) {
				track.setUserTrack(lib);
				int listSize = listSecondDicts.get(m).getChildren().size();
				List<Element> trialFirst = listSecondDicts.get(m).getChildren();
				for (int i = 0; i < listSize - 1; i += 2) {
					Element node = (Element) trialFirst.get(i);
					Element nodeInsert = (Element) trialFirst.get(i + 1);
					if(node.getText().equals("Persistent ID")){
						track.setTrackPersisId(nodeInsert.getText());
					}
					else if (node.getText().equals("Track ID"))
						track.setTrackID(Integer.parseInt(nodeInsert.getText()));
					else if (node.getText().equals("Name"))
						track.setName(nodeInsert.getText());
					else if (node.getText().equals("Artist"))
						track.setArtist(nodeInsert.getText());
					else if (node.getText().equals("Album"))
						track.setAlbum(nodeInsert.getText());
					else if (node.getText().equals("Genre"))
						track.setGenre(nodeInsert.getText());
					else if (node.getText().equals("Year"))
						track.setYear(nodeInsert.getText());
				}
				
				tracks.add(track);
				track = new Track();
			}

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tracks;
	}

	public ArrayList<PlayList> getPlayListInformation() {
		ArrayList<PlayList> playlists = new ArrayList<PlayList>();
		ArrayList<Track> trackList = getAllTrackInoframtion();
		PlayList playlist = new PlayList();
		Document document;
		Library user = new Library();
		user.setLibID(lidId);
	

		try {
			document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement(); // 'plist'
			Element firstDict = rootNode.getChild("dict"); // dict
			// Getting the play list!!
			Element arrayPlayListNode = firstDict.getChildren().get(firstDict.getChildren().size() - 1);
			List<Element> arrayPlayListChildren = arrayPlayListNode.getChildren("dict");

			for (int j = 0; j < arrayPlayListChildren.size(); j++) {

				List<Element> trialFirst = arrayPlayListChildren.get(j)
						.getChildren();

				// Here we start to create the play list
				for (int i = 0; i < trialFirst.size(); i += 2) {
					Element node = (Element) trialFirst.get(i);
					Element nodeInsert = (Element) trialFirst.get(i + 1);
					if (node.getText().equals("Name")) {
						playlist.setName(nodeInsert.getText()); // working
					} else if (node.getText().equals("Playlist ID"))
						playlist.setPlaylistId(Integer.parseInt(nodeInsert.getText())); // working
					else if (node.getText().equals("Playlist Persistent ID"))
						playlist.setPlaylistPersistenceId(nodeInsert.getText()+j);
				}
				Element arrayNode = trialFirst.get(trialFirst.size() - 1);
				List<Element> arrayNodeChildren = arrayNode.getChildren();
				for(int m = 0 ; m <arrayNodeChildren.size();m++){
					Track t = new Track();
					List<Element> trackIdAndIntegerNode = arrayNodeChildren.get(m).getChildren();
					Element nodeInsert = (Element) trackIdAndIntegerNode.get(1);
					int trackId = Integer.parseInt(nodeInsert.getText()); //getting the id 
					for(Track titer: trackList){
						if(titer.getTrackID() == trackId){
							t = titer;
						}
					}
					playlist.addTrackToPlayList(t);
					
				}
				
				playlist.setUserPlayList(user);
				playlists.add(playlist);
				playlist = new PlayList();

			}

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(playlists.get(0).g);
		return playlists;
	}

}

/*
 * for (int m = 0; m < arrayNodeChildren.size(); m++) { track = new Track();
 * List<Element> trackIdAndIntegerNode = arrayNodeChildren
 * .get(m).getChildren(); Element nodeInsert = (Element)
 * trackIdAndIntegerNode.get(1); int trackId =
 * Integer.parseInt(nodeInsert.getText()); // getting // the // id
 * track.setTrackID(trackId);
 * 
 * trackList.add(track); // track.setPlaylist(playlist); //if i remove, i get
 * null // playlist_ids in track!! }
 */