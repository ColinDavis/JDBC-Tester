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
 conn = DriverManager.getConnection("jdbc:mysql://web.8bitben.com:3306/mhscsorg_wp?user=java_testr&password=mhsatics");
			String connectionUrl = "jdbc:mysql://web.8bitben.com:3306/mhscsorg_wp";
			String connectionUser = "java_test";
			String connectionPassword = "mhsatics";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Updates");
			while (rs.next()) {
				String updateID = rs.getString("Update_ID");
				String updateText = rs.getString("Update_Text");
				String updateUID = rs.getString("Update_UID");
				String updateDate = rs.getString("Update_Date");
				System.out.println("Update ID: " + updateID + ", updateUID: " + updateUID
						+ ", Update Text: " + updateText + ", Update Date: " + updateDate);
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
