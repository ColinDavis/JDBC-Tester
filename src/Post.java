public class Post
{/*
	 *JDBC TESTER. not actual class~~~
	 */
	private int ID, UID, rank;
	private String text, date, name="";
	
	/**
	 * This constructor is used solely for the data hard-coded for testing.
	 */
	public Post()
	{
		this.date = "date";
		this.ID = -1;
		this.UID = -1;
		this.text = "text";
		this.rank = -1;
		this.name = "name";
	}
	public Post(int ID, String text, String name, String date,int ranking)
	{
		//userName = name;
		this.ID= ID;
		this.text = text;
		this.name = name;
		this.date = date;
		rank = ranking;
		
	}
	/**
	 * This method is the method to initialize data based on the time it can be attained from the database.
	 */
	public Post(String date, int ID, int UID, String text)
	{
		this.date = date;
		this.ID = ID;
		this.UID=UID;
		this.text=text;
		
		this.rank = -1;
		this.name = "";
	}
	
	public int getID()
		{return ID;}
	public String getText()
		{return text;}
	public int getUID()
		{return UID;}
	public String getName()
		{return name;}
	public String getDate()
		{return date;}
	public int getRank()
		{return rank;}
	
	public void setName(String name)
		{this.name = name;}
	public void setRank(int rank)
		{this.rank=rank;}
}
