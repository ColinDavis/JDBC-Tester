

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;



public class GSON_Tester 
{
	private static Post post;

	public static void main(String[] args) 
	{
		post =new Post();
		
		try {
			 System.out.println(call("http://dev.mhsnews.org/json_db.php"));
			 test();
		} catch (IOException e) {e.printStackTrace();	}

	}
	
	public static void test()
	{
		Gson gson = new Gson();
		String json;
		try {
			json = call("http://dev.mhsnews.org/json_db.php");
	
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
				j=i+1;
				
				 System.out.print(post.getName());
				 System.out.println(": " + post.getUID());
				 System.out.println(post.getID());
				 System.out.println(post.getText());
				 System.out.println(post.getDate());
				 System.out.println();
				 
				 
				 //2/1/13 - This works fine. GSON_Tester parses all of the json into individual Posts.
				 //			Next Step: add Posts to Post ArrayList
			}
		}
			 // post = gson.fromJson(json, Post.class);
		
		} catch (IOException e) {e.printStackTrace();}
		
	}
	public static String call(String url) throws IOException {  
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
/**
 * A Line from JSON:
 {"0":"2013-01-02 09:47:04","Update_Date":"2013-01-02 09:47:04","1":"31","Update_ID":"31","2":"2","Update_UID":"2","3":"Test","Update_Text":"Test"}

 * Sorted Line:
 {
 "0":"2013-01-02 09:47:04",
 "Update_Date":"2013-01-02 09:47:04",
 "1":"31",
 "Update_ID":"31",
 "2":"2",
 "Update_UID":"2",
 "3":"Test",
 "Update_Text":"Test"
 }

 */