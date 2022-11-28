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
			System.out.println("-----Hello Admin !.----");
			System.out.println("Please enter your username :");
			String username=sc.next();
			System.out.println("Please enter your password :");
			String password=sc.next();
			boolean valid=validate(username,password);
			if(valid)
			{
				System.out.println("You have sucessfully logged in !");
				System.out.println("1.View Bills of all the consumers");
				System.out.println("2.View Bills of the consumers for specific city and area ");
				System.out.println("3.View Bills of the consumer for specific year and month");
				System.out.println("Enter your choice");
				int ch = sc.nextInt();
				switch(ch)
				{
				case 1:
					Bill.viewAllBills();
					break;
				case 2:
					Bill.viewBillWithCityAndArea();
					break;
				case 3:
					Bill.viewBillWithYearAndMonth();
					break;
				}
			}
			else {
				System.out.println("Invalid Credentials");
			}
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
