package Team4Project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	static Connection myConnection() throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	String url="jdbc:mysql://localhost:3306/WileyDb";
	Connection con = DriverManager.getConnection(url,"root","root");
	return con;
	}

}
