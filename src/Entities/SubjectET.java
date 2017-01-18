package Entities;

import java.io.Serializable;
import java.util.ArrayList;

public class SubjectET implements Serializable{
	
	private int Sid;
	private String SubjectTitle;
	private int GenreId;
	
	public SubjectET(int Subid,String Title,int Genre)
	{
		super();
		this.Sid=Subid;
		this.SubjectTitle=Title;
		this.GenreId=Genre;
	}
	public int getSid() {
		return Sid;
	}
	public String getSubjectTitle() {
		return SubjectTitle;
	}
	public int getGenreId() {
		return GenreId;
	}
	public void setSid(int sid) {
		Sid = sid;
	}
	public void setSubjectTitle(String subjectTitle) {
		SubjectTitle = subjectTitle;
	}
	public void setGenreId(int genreId) {
		GenreId = genreId;
	}
	
}
