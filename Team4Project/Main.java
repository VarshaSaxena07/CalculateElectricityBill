package Team4Project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Main {

		public static void main(String[] args) throws ClassNotFoundException, SQLException, InvalidTypeException, IdNotFoundException {
			
			Scanner sc = new Scanner(System.in);
			String c="";
			
			System.out.println("1.Admin");
			System.out.println("2.Consumer");
			System.out.println("Enter your choice");
			int ch = sc.nextInt();
			switch(ch)
			{
			case 1:
				admin();
				break;
			case 2:
			    consumer();
			    break;
			}
			
		}

		private static void admin() throws ClassNotFoundException, SQLException {
			Scanner sc = new Scanner(System.in);
				System.out.println("-----Hello Admin !.----");
				System.out.println("Please enter your username :");
				String username=sc.next();
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
						viewAllBills();
						break;
					case 2:
						viewBillWithCityAndArea();
						break;
					case 3:
						viewBillWithYearAndMonth();
						break;
					
					}
				}		
		}

		

		private static void consumer() throws ClassNotFoundException, SQLException, InvalidTypeException, IdNotFoundException {
			Scanner sc = new Scanner(System.in);
			String c="";
			do {
				System.out.println("-----Hello welcome to the electricity bill calculator..----");
				System.out.println("1.New Consumer");
				System.out.println("2.Existing Consumer");
				System.out.println("3.Exit");
				System.out.println("Enter your choice");
				int ch = sc.nextInt();
				switch(ch)
				{
				case 1:
				    newConsumer();
				    break;
				case 2:
					existingConsumer();					
					break;
				case 3:
					break;
				default:
					System.out.println("Wrong Choice");		
				}		
			
				System.out.println("Do you want to continue(Y/N)");
				c=sc.next();
			}while(c.equals('Y'));

			
		}

		private static void existingConsumer() throws ClassNotFoundException, SQLException, IdNotFoundException {
			Connection con=MyConnection.myConnection();
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Your Consumer ID:");
			int cid=sc.nextInt();
			String sqlSelect="select * from user where id= cid";
			if(sqlSelect.equals(null))
				throw new IdNotFoundException();
			else {
				System.out.println("Please Enter Electricity Units:");
				int units=sc.nextInt();
				Statement st=con.createStatement();
				ResultSet rs = st.executeQuery(sqlSelect);
				CallableStatement callable = con.prepareCall("{call calculateBill(?,?) }");
				callable.setInt(1, rs.getInt(1));
				callable.setInt(2, units);
				callable.executeUpdate();				
			}			
		}

		private static boolean newConsumer() throws ClassNotFoundException, SQLException, InvalidTypeException {
			Connection con=MyConnection.myConnection();
			String sqlInsert = "insert into consumer values(?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sqlInsert);
			Scanner sc=new Scanner(System.in);
			int cid=0,flag=0;
			String cname="",city="",area="",type="";
			try {				
			System.out.println("Enter Consumer Id :");
			cid=sc.nextInt();
			System.out.println("Enter Consumer Name :");
			cname =sc.next();
			System.out.println("Enter City :");
			city=sc.next();
			System.out.println("Enter Area :");
			area =sc.next();
			System.out.println("Enter Type(Domestic/Commercial) :");
			type =sc.next();
			if(!type.equals("Domestic")||!type.equals("Commercial"))
				throw new InvalidTypeException();
		} catch (Exception e) {
			System.out.println("Invalid Input ! "+e);
			flag=1;
		}
			if(flag==0)
			{
			pst.setInt(1, cid);
			pst.setString(2, cname);
			pst.setString(3, city);
			pst.setString(4, area);
			pst.setString(5, type);
			pst.execute();
			return true;
			}
			else
			return false;
				
		}
		private static void viewBillWithYearAndMonth() throws ClassNotFoundException, SQLException {
			Connection con=MyConnection.myConnection();
			String sqlSelect="select * from viewBillWithYearAndMonth";
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(sqlSelect);
			while(rs.next())
				System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4));
			
		}

		private static void viewBillWithCityAndArea() throws ClassNotFoundException, SQLException {
			Connection con=MyConnection.myConnection();
			String sqlSelect="select * from viewBillWithCityAndArea";
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(sqlSelect);
			while(rs.next())
				System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4));
			
		}
			

		private static void viewAllBills() throws ClassNotFoundException, SQLException {
			Connection con=MyConnection.myConnection();
			String sqlSelect="select * from viewAllBill";
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(sqlSelect);
			while(rs.next())
				System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4));
			
		}

		private static boolean validate(String username, String password) throws ClassNotFoundException, SQLException {
			Connection con=MyConnection.myConnection();
			String sqlSelect="select * from admin where uname= username and pass=password";
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(sqlSelect);
			if(rs!=null)
				return true;
			else
			return false;
		}

}
