package com.readInExcel;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class FileUploadForm {

public FileUploadForm(){
		
	}
	
	private byte[] data;
	public byte[] getData() {
		// TODO Auto-generated method stub
		return data;
	}

	@FormParam("uploadedFile")
	@PartType("application/octect-stream")
	public void setData(byte[] data){
		this.data=data;
	}
	
	
}