package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// gebaseerd op het Singleton design pattern vermits er slechts één connectie wordt gemaakt(= 1 instantie)

public class DerbyDBConnection {
	public static DerbyDBConnection instance = new DerbyDBConnection();
	public static final String DB_URL = "jdbc:derby:/opt/apps/Apache/db-derby-10.11.1.1-bin/bin/QuizAppDB;create=true";
	public static final String user = "";
	public static final String password = "";
	public static final String driverClass = "com.mysql.jdbc.Driver";

	// private constructor

	public DerbyDBConnection() {
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL);
		}

		catch (SQLException sqlException) {
			System.out.println("Kan geen verbinding maken met database");
			// sqlException.printStackTrace();
		}

		return connection;
	}

	public static Connection getConnection() {
		return instance.createConnection();
	}
}
