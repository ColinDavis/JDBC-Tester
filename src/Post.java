

public class Post
{/*
	 *JDBC TESTER. not actual class~~~
	 */
	private int Update_ID,Update_UID,rank;
	
	private String Update_Text,Update_Date,name;
	
	
	/**
	 * This constructor is used solely for the data hard-coded for testing.
	 */
	public Post()
	{
		this.Update_Date = "date";
		this.Update_ID = -1;
		this.Update_UID = -1;
		this.Update_Text = "Update_Text";
		this.rank = -1;
		this.name = "name";
	}
	public Post(int Update_ID, String Update_Text, String name, String date,int ranking)
	{
		//userName = name;
		this.Update_ID= Update_ID;
		this.Update_Text = Update_Text;
		this.name = name;
		this.Update_Date = date;
		rank = ranking;
		
	}
	/**
	 * This method is the method to initialize data based on the time it can be attained from the database.
	 */
	public Post(String Update_Date, int Update_ID, int Update_UID, String Update_Text)
	{
		this.Update_Date = Update_Date;
		this.Update_ID = Update_ID;
		this.Update_UID=Update_UID;
		this.Update_Text=Update_Text;
		
		this.rank = -1;
		this.name = "";
	}
	
	public int getID()
		{return Update_ID;}
	public String getText()
		{return Update_Text;}
	public int getUID()
		{return Update_UID;}
	public String getName()
		{return name;}
	public String getDate()
		{return Update_Date;}
	public int getRank()
		{return rank;}
	

	public void setTeacher(Teacher teacher)
	{
		this.rank = teacher.getRank();
		this.name = teacher.getName();
	}
}
