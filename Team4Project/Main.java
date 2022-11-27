package Team4Project;
import java.sql.SQLException;
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
				Admin.admin();
				break;
			case 2:
			    Consumer.consumer();
			    break;
			}
			
		}


		

		

		
		

		

}
