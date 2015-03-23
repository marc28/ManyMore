package com.testing;

import java.util.ArrayList;

import com.entities.PlayList;
import com.entities.Track;
import com.readInExcel.ReadInXML;

public class Driver {
	public static void main(String[] args) {
		ReadInXML ri = new ReadInXML("itunes.xml");
		ArrayList<PlayList> playlist = ri.getPlayListInformation();
		for(PlayList p : playlist){
			System.out.println(p.getName());
			System.out.println(p.getPlaylistId());
			System.out.println("Tracks: -------- ");
			/*for(Track t : p.getTracks()){
				System.out.println(t.getName());
				//System.out.println(t.getAlbum());
			}*/
			System.out.println();
		}
		
		
		
	}
}
