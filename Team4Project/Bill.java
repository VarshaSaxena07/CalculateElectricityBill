package Team4Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bill {
	public static void viewBillWithYearAndMonth() throws ClassNotFoundException, SQLException {
		Connection con=MyConnection.myConnection();
		String sqlSelect="select * from viewBillWithYearAndMonth";
		Statement st=con.createStatement();
		ResultSet rs = st.executeQuery(sqlSelect);
		while(rs.next())
			System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4));
		
	}

	public static void viewBillWithCityAndArea() throws ClassNotFoundException, SQLException {
		Connection con=MyConnection.myConnection();
		String sqlSelect="select * from viewBillWithCityAndArea";
		Statement st=con.createStatement();
		ResultSet rs = st.executeQuery(sqlSelect);
		while(rs.next())
			System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4));
		
	}		

	public static void viewAllBills() throws ClassNotFoundException, SQLException {
		Connection con=MyConnection.myConnection();
		String sqlSelect="select * from viewAllBill";
		Statement st=con.createStatement();
		ResultSet rs = st.executeQuery(sqlSelect);
		while(rs.next())
			System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4));
		
	}

}
