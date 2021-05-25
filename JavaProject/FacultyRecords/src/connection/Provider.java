package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Provider {
	static Connection cn;
	public static Connection getConn() throws SQLException{
		cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","FINAL","1234");
		return cn;
	}
		static{
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				System.out.println("Connection Done");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
