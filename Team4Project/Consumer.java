package Team4Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Consumer {
	public  static void consumer() throws ClassNotFoundException, SQLException, InvalidTypeException, IdNotFoundException {
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
	public static void existingConsumer() throws ClassNotFoundException, SQLException, IdNotFoundException {
		Connection con=MyConnection.myConnection();
		double totalAmt=0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Your Consumer ID:");
		int cid=sc.nextInt();
		String sqlSelect="select * from user where id= cid";
		if(sqlSelect.equals(null))
			throw new IdNotFoundException();
		else {
			System.out.println("Please Enter Electricity Units:");
			int units=sc.nextInt();
			System.out.println("Please Enter Year:");
			int year=sc.nextInt();
			System.out.println("Please Enter Month:");
			int month=sc.nextInt();
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(sqlSelect);
			String type= rs.getString(5);
			if(type.equals("C"))
				totalAmt=units*4;
			if(type.equals("D"))
				totalAmt=units*2;
			String sqlInsert = "insert into electricityBill values(?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sqlInsert);
			pst.setInt(1, cid);
			pst.setInt(2, year);
			pst.setInt(3, month);
			pst.setDouble(4, units);
			pst.setDouble(5, totalAmt);
			pst.execute();
			
		}			
	}

	public static boolean newConsumer() throws ClassNotFoundException, SQLException, InvalidTypeException {
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
}
