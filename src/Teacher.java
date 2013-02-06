import java.util.ArrayList;
//JSON TEST VERSION

/*
TEACHER CLASS
HAS: Type/Status, Department(s), Name, Courses (Course ID + Section ID), Course Names, (Database ID?)
 */
public class Teacher{
	private String Name;
	private int Rank;
	private int UID;
	
	/**
	 * 
	 */
	public Teacher(String Name, int UID, int Rank)
	{
		this.Name = Name;
		this.UID = -1;
		this.Rank = Rank;
	}
	public Teacher(int UID)
	{
		this.UID = UID;
		Rank = -1;
		Name = "Name_error";
	}
	public int getRank()
		{return Rank;}
	public String getName()
		{return Name;}
	public int getUID()
		{return UID;}
	
	public void set(Teacher teacher)
	{
		this.Rank = teacher.getRank();
		this.Name = teacher.getName();
		this.UID = teacher.getUID();
	}
}
