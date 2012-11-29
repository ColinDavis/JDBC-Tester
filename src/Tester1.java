import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.mysql.jdbc.Driver;

public class Tester1 
{
	public static void main(String[] args) 
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			new com.mysql.jdbc.Driver();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://sqlserver.mhscs.org:3600/MHS_Android_News?user=java_client&password=rRcNmJsHFfD7XEjn");
			String connectionUrl = "jdbc:mysql://sqlserver.mhscs.org:3600";
			String connectionUser = "java_client";
			String connectionPassword = "rRcNmJsHFfD7XEjn";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Updates");
			while (rs.next())
			{
				
				int updateID = rs.getInt("Update_ID");
				String updateText = rs.getString("Update_Text");
				String updateUID = rs.getString("Update_UID");		//Access userName at a later date
				String updateDate = rs.getString("Update_Date");	
				
			//	 * Use ArrayList<Post> to store Posts; parameters: (updateID, updateText, updateUID, updateDate, TextBox)
			//	 * Use ArrayList<TextBox> to store TextBoxes with corresponding Posts 
				 
				System.out.println(updateID);
				System.out.println(updateText);
				System.out.println(updateUID);
				System.out.println(updateDate);
				
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
}
