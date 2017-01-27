package Entities;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * class of genre entity with all the genre details by the database genre table
 *
 */
public class GenreET implements Serializable{
	private int Gid;
	private String GenreTitle;
	private ArrayList<SubjectET> SubjectList;
	
	public GenreET(int id,String Title)
	{
		super();
		this.Gid=id;
		this.GenreTitle=Title;
		SubjectList = new ArrayList<SubjectET>();
	}
	public GenreET()
	{
		
	}
	public int getGid() {
		return Gid;
	}
	public String getGenreTitle() {
		return GenreTitle;
	}
	public ArrayList<SubjectET> getSubjectList() {
		return SubjectList;
	}
	public void setGid(int gid) {
		Gid = gid;
	}
	public void setGenreTitle(String genreTitle) {
		GenreTitle = genreTitle;
	}
	public void setSubjectList(ArrayList<SubjectET> subjectList) {
		SubjectList = subjectList;
	}
	public void SetSubject(int Subid,String Title,int Genre)
	{
		SubjectList.add(new SubjectET(Subid, Title, Genre));
	}
}
