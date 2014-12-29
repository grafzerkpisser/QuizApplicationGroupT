package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DerbyDatabaseHandlerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String DB_URL = "jdbc:derby:/opt/apps/Apache/db-derby-10.11.1.1-bin/bin/QuizAppDB;create=true";
		final String Select_quiz = "select * from quiz";

		try (Connection connection = DriverManager.getConnection(DB_URL);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(Select_quiz)) {
			ResultSetMetaData metaData = resultSet.getMetaData();
			int aantalKolommen = metaData.getColumnCount();
			System.out.printf("Lijst van Quizzen\n");

			for (int i = 1; i <= aantalKolommen; i++) {
				System.out.printf("%-8s\t", metaData.getColumnName(i));
			}
			System.out.println();

			while (resultSet.next()) {
				for (int i = 1; i <= aantalKolommen; i++) {
					System.out.printf("%-8s\t", resultSet.getObject(i));
				}
				System.out.println();
			}
		}

		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

	}
}
