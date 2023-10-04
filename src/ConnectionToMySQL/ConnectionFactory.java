package ConnectionToMySQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
 
	private static final String url = "jdbc:mysql://localhost:3306/registerdb";
	private static final String user = "root";
	private static final String password = "Gor!l@146200";
	
	private static Connection conn;
	
	public static Connection getConnectionToMySQL() {
		
		try {
			if(conn==null) {
				conn = DriverManager.getConnection(url, user, password);
				return conn;
			}else {
				return conn;
			}			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
}
