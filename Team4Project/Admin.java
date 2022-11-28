package Team4Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Admin {

	public static void admin() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		String c="";
			System.out.println("-----Hello Admin !.----");
			System.out.println("Please enter your username :");
			String username=sc.next();
			System.out.println("Please enter your password :");
			String password=sc.next();
			boolean valid=validate(username,password);
			if(valid)
			{
				System.out.println("You have sucessfully logged in !");
				do {
				System.out.println("1.View Bills of all the consumers");
				System.out.println("2.View Bills of the consumers for specific city and area ");
				System.out.println("3.View Bills of the consumer for specific year and month");
				System.out.println("Enter your choice");
				int ch = sc.nextInt();
				switch(ch)
				{
				case 1:
					viewAllBills();
					break;
				case 2:
					viewBillWithCityAndArea();
					break;
				case 3:
					viewBillWithYearAndMonth();
					break;
				default:
					System.out.println("Wrong Choice");		
				}		
			
				System.out.println("Do you want to continue(Y/N)");
				c=sc.next();
			}while(c.equals("Y"));	
		}
			else
				System.out.println("Invalid Credentials !!");
	}
	
		private static void viewBillWithYearAndMonth() throws ClassNotFoundException, SQLException {
			Connection con=MyConnection.myConnection();
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter year:");
			int year = sc.nextInt();
			System.out.println("Enter month:");
			int month = sc.nextInt();
			
			String sqlInsert = "select * from viewAllBills where year = ? and month = ?";
			PreparedStatement pst = con.prepareStatement(sqlInsert);
	     	pst.setInt(1,year);
	     	pst.setInt(2, month);
	     	ResultSet rs = pst.executeQuery();
//	     	if(rs==null)
//				System.out.println("No records are available with this year and month");
	     	while(rs.next())
				System.out.println(rs.getInt(1)+" | "+rs.getString(2));	
		}	
		
		private static void viewBillWithCityAndArea() throws ClassNotFoundException, SQLException {
			Connection con=MyConnection.myConnection();
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the name of the city :");
			String city = sc.next();
			System.out.println("Enter the name of the area :");
			String area = sc.next();
			
			String sqlInsert = "select * from viewAllBills where city = ? and area = ?";
			PreparedStatement pst = con.prepareStatement(sqlInsert);
	     	pst.setString(1,city);
	     	pst.setString(2, area);
	     	ResultSet rs = pst.executeQuery();
//	     	if(rs==null)
//				System.out.println("No records are available with this city and area");
	     	while(rs.next())
				System.out.println(rs.getInt(1)+" | "+rs.getString(2));	
		}	
			
		private static void viewAllBills() throws ClassNotFoundException, SQLException {
			Connection con=MyConnection.myConnection();
			String sqlSelect="select * from viewAllBills";
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(sqlSelect);
			System.out.println("Consumer ID | year | month | unitsConsumed | totalAmount");
			while(rs.next())
				System.out.println(rs.getInt(1)+" | "+rs.getString(2)+" | "+rs.getString(3)+" | "+rs.getString(4)+" | "+rs.getString(5));	
		}	

		private static boolean validate(String username, String password) throws ClassNotFoundException, SQLException {
			Connection con=MyConnection.myConnection();
			String sqlSelect="select * from admin where adminname= ? and password= ? ";
			PreparedStatement pst = con.prepareStatement(sqlSelect);
			pst.setString(1,username);
			pst.setString(2,password);
			pst.execute();
			Statement st=con.createStatement();
			ResultSet rs = pst.executeQuery();
			if(rs!=null)
				return true;
			else
			return false;
		}
}