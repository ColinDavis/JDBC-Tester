import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.*;


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
		 
		 System.out.print(post.getName());
		 System.out.println(": " + post.getUID());
		 System.out.println(post.getRank());
		 System.out.println(post.getText());
		 System.out.println(post.getDate());
		 
		 
		 
		 
	}
	
	public static void test()
	{
		Gson gson = new Gson();
		String json;
		//try {
			//json = call("http://dev.mhsnews.org/json_db.php");
		json = "{\"0\":\"2013-01-02 09:47:04\",\"Update_Date\":\"2013-01-02 09:47:04\",\"1\":\"31\",\"Update_ID\":\"31\",\"2\":\"2\",\"Update_UID\":\"2\",\"3\":\"Test\",\"Update_Text\":\"Test\"}";
		  System.out.println(json.toCharArray().length);
		json += "{\"0\":\"2013-01-02 09:46:19\",\"Update_Date\":\"2013-01-02 09:46:19\",\"1\":\"30\",\"Update_ID\":\"30\",\"2\":\"0\",\"Update_UID\":\"0\",\"3\":\"jkl;sdasdafjklsadfjkl;\",\"Update_Text\":\"jkl;sdasdafjklsadfjkl;\"}";
			
		  System.out.println(json.toCharArray().length);
			//LOOP:
				//While there is a line in JSON up to a certain line (test:4)
			post = gson.fromJson(json, Post.class);
		
		//} catch (IOException e) {e.printStackTrace();}
		
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