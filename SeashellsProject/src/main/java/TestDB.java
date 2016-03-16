import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDB {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		try {

			final String dbUrl = "jdbc:mysql://www.db4free.net:3306/seashell?useSSL=false";
			final String user = "seashelladmin";
			final String pass = "appdirect";
			final String dbClass = "com.mysql.jdbc.Driver";

			Class.forName(dbClass).newInstance();
			System.out.println("driver loaded"); // THIS IS BEING RETURNED

			// Connection
			Connection connect = DriverManager.getConnection(dbUrl, user, pass);
			System.out.println("connected"); 
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
