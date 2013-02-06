import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.google.gson.Gson;

public class GSON_UID_Tester 
{
	/**
	 * teacherList1 is representative of the list of Posts we attain from JSON. These 'pseudo-Teachers' only have a UID,
	 * which is the case for the information about the Teacher we have from the Post. teacherList is the list of Teachers
	 * that we attain from JSON, which will be used to set names/ranks to Posts based off the UIDs of the posting Teachers.
	 */
	private static ArrayList<Teacher> teacherList1,teacherList;
	
	public static void main(String[] args) 
	{
		teacherList1 = new ArrayList<Teacher>();
		for(int i=0; i<5; i++)
			teacherList1.add(new Teacher(i+1));
		
		teacherList = new ArrayList<Teacher>();
		
		try {
			 System.out.println(getJSON("http://dev.mhsnews.org/json_db/users.php"));
			 getTeachers();
		} catch (IOException e) {e.printStackTrace();	}
		
		//--------------------------------------------------------------------------------\\
		
		System.out.println("No Longer JUST json section");
		//set the rest of the Teacher data when I only have the UID to work with, and the list of Teachers
		for(int i=0; i<teacherList1.size(); i++)
			for(int j=0; j<teacherList.size(); j++)
				if ( teacherList1.get(i).getUID() == teacherList.get(j).getUID() )
				{
					teacherList1.get(i).set(teacherList.get(j));
					System.out.println("UID: "+teacherList1.get(i).getUID());
					System.out.println("Name: "+teacherList1.get(i).getName());
					System.out.println("Rank: "+teacherList1.get(i).getRank());
					System.out.println();
				}
	}
	
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
		
		//------------TEST AREA-------------\\
	/**	for(int i=0; i<teacherList.size(); i++)
		{
			System.out.println("UID: "+teacherList.get(i).getUID());
			System.out.println("Name: "+teacherList.get(i).getName());
			System.out.println("Rank: "+teacherList.get(i).getRank());
			System.out.println();
		}*/
		
		
		} catch (IOException e) {e.printStackTrace();}
		
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