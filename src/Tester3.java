import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tester3 {
    
	
	
    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://MySQL55.marlborough.int:3306/MHS_News";
        String user = "Android_Client";
        String password = "bA55nAFA";

        try {
            
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement("SELECT * FROM Updates");
            rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println("ID: "+rs.getInt(2));		//i.e 1= first ever, 2 is second ever, etc. (1,3,4 happens)
                System.out.println("User: "+rs.getInt(3));		//identifier for person
                System.out.println("Text: "+rs.getString(4));
                System.out.println("Date: "+convertDate(rs.getString(1)));
                System.out.println();
            }
            
            pst = con.prepareStatement("SELECT * FROM Users");
            rs = pst.executeQuery();
            
            while(rs.next()) {
            	System.out.println(rs.getString(2));
           }


        } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Tester3.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Tester3.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
    private static String convertDate(String date)
    {
    	//Date: 2012-12-20 09:53:03.0
    	//      YYYY-MM-DD HH:MM:SS.x
    	//		0123-56-89 12:45:78
    	//		HH:MM, MM/DD/YYYY
    	//		12345, 56/89/0123
    	char[] cDate = date.toCharArray();
    	date = "";
    	
    	if(cDate[11] !='0')
    		date+= cDate[11];
    	date+= cDate[12];
    	date+= cDate[13];
    	date+= cDate[14];
    	date+= cDate[15];
    	date += ", ";
    	if(cDate[5] !='0')
    		date+= cDate[5];
    	date+= cDate[6];
    	date+= "/";
    	if(cDate[8] !='0')
    		date+= cDate[8];
    	date+= cDate[9];
    	date+= "/";
    	date+= cDate[0];
    	date+= cDate[1];
    	date+= cDate[2];
    	date+= cDate[3];
    	return date;
    }
}

class testPerson
{
	private int ID;
	private String name;
	
	public testPerson(int ID, String name)
	{
		this.ID = ID;
		this.name=name;
	}
	
	public String getName()
		{return name;}
	public int getID()
		{return ID;}
}