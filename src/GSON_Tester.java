

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.google.gson.Gson;



public class GSON_Tester 
{
	private static Post post;
	private static ArrayList<Post> postList;
	private static ArrayList<Teacher> teacherList;

	public static void main(String[] args) 
	{
		post =new Post();
		teacherList = new ArrayList<Teacher>();
		postList = new ArrayList<Post>();
		
		try {
			 System.out.println(getJSON("http://dev.mhsnews.org/json_db/updates.php"));
			 test();
			 getTeachers();
			 setTeachers();
		} catch (IOException e) {e.printStackTrace();	}

		for(int i=0; i<postList.size(); i++)
		{
			System.out.print(postList.get(i).getName());
			System.out.println(": " + postList.get(i).getUID());
			System.out.println(postList.get(i).getID());
			System.out.println(postList.get(i).getText());
			System.out.println(postList.get(i).getDate());
			System.out.println();
		}
	}
	
	public static void test()
	{
		Gson gson = new Gson();
		String json;
		try {
			json = getJSON("http://dev.mhsnews.org/json_db/updates.php");
	
		char[] cson = json.toCharArray();
		  System.out.println(json.toCharArray().length);
		  System.out.println();
			//LOOP:
				//While there is a line in JSON up to a certain line (test:4)
		  int a = 0, j=0;
		for (int i=1; i<cson.length; i++)
		{
			if (cson[i] == '{' && a==0)
			{
				j=i;
			}
			
			
			if (cson[i] == '}')
			{
				a++;
				String sson = "";
				for(int k=j; k<=i; k++)
					sson+= cson[k];
				post = gson.fromJson(sson,Post.class);
				postList.add(post);
				j=i+1;
				

				 
				 
				 //2/1/13 - This works fine. GSON_Tester parses all of the json into individual Posts.
				 //			Next Step: add Posts to Post ArrayList
			}
		}
			 // post = gson.fromJson(json, Post.class);
		
		} catch (IOException e) {e.printStackTrace();}
		
	}
	
	/**
	 * This method fills the ArrayList @teacherList with every teacher on the database
	 */
	public static void getTeachers()
	{
		Gson gson = new Gson();
		String json;
		try {
			json = getJSON("http://dev.mhsnews.org/json_db/users.php");
	
		char[] cson = json.toCharArray();
		  System.out.println(json.toCharArray().length);
		  System.out.println();
			//LOOP:
				//While there is a line in JSON up to a certain line (test:4)
		  int a = 0, j=0;
		for (int i=1; i<cson.length; i++)
		{
			if (cson[i] == '{' && a==0)
				j=i;
			
			if (cson[i] == '}')
			{
				String sson = "";
				for(int k=j; k<=i; k++)
					sson+= cson[k];
				teacherList.add(gson.fromJson(sson,Teacher.class));
				j=i+1;
				a++;
			}
		}
		} catch (IOException e) {e.printStackTrace();}
		
	}
	
	/**
	 * This method is filling the gaps of postList with the Teachers (name/rank/UID) that each Post lacks
	 */
	public static void setTeachers()
	{
		for(int i=0; i<postList.size(); i++)
			for(int j=0; j<teacherList.size(); j++)
				if ( postList.get(i).getUID() == teacherList.get(j).getUID() )
				{
					postList.get(i).setTeacher(teacherList.get(j));
					break;
				}
	}
	
	public static String getJSON(String url) throws IOException {  
		BufferedReader bis = null;  
		InputStream is = null;  
		 
		try {  
			URLConnection connection = new URL(url).openConnection(); 
			is = connection.getInputStream();  
				// warning of UTF-8 data  
			bis = new BufferedReader(new InputStreamReader(is, "UTF-8"));  
			String line = null;  
			StringBuffer result = new StringBuffer();  
	
			while ((line = bis.readLine()) != null) {  
				result.append(line);  
			}  
			return result.toString();  
		}
		
		finally {  
			if (bis != null) {  
				try {  
					bis.close();  
				}
				catch (IOException e) {  
					e.printStackTrace();  
				}  
			} 
			
			if (is != null) {  
				try {  
					is.close();  
				}
				catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
		}  
	}
}