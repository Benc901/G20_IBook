package Entities;

import java.io.Serializable;

/**
 * Class of FileEvent entity that have fields about file data and data of book
 * with this class we can transform book file from client to server and from server to client 
 *
 */
public class FileEvent implements Serializable {
	
	public FileEvent() {
	}
	
	private static final long serialVersionUID = 1L;

	private String destinationDirectory;
	private String sourceDirectory;
	private String filename;
	private long fileSize;
	private byte[] fileData;
	private String status;
	private int bookid;
	
	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public String getDestinationDirectory() {
	return destinationDirectory;
	}
	
	public void setDestinationDirectory(String destinationDirectory) {
	this.destinationDirectory = destinationDirectory;
	}
	
	public String getSourceDirectory() {
	return sourceDirectory;
	}
	
	public void setSourceDirectory(String sourceDirectory) {
	this.sourceDirectory = sourceDirectory;
	}
	
	public String getFilename() {
	return filename;
	}
	
	public void setFilename(String filename) {
	this.filename = filename;
	}
	
	public long getFileSize() {
	return fileSize;
	}
	
	public void setFileSize(long fileSize) {
	this.fileSize = fileSize;
	}
	
	public String getStatus() {
	return status;
	}
	
	public void setStatus(String status) {
	this.status = status;
	}
	
	public byte[] getFileData() {
	return fileData;
	}
	public void setFileData(byte[] fileData) {
	this.fileData = fileData;
	}
}
