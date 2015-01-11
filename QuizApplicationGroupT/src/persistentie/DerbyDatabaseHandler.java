/**
 *
 */
package persistentie;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import enumerations.*;
import model.Opdracht;
import model.OpdrachtCatalogus;
import model.Quiz;
import model.QuizOpdracht;
import utils.datum.*;
import persistentie.DataHandler.QuizOpdrachtMapper;

/**
 * @author java
 *
 */
public class DerbyDatabaseHandler implements DbStrategy {

	
	@Override
	public List<Opdracht> leesOpdrachten() throws NumberFormatException, IllegalArgumentException, IOException {
			
		final String DB_URL = "jdbc:derby:/opt/apps/Apache/db-derby-10.11.1.1-bin/bin/QuizAppDB;create=true";
		final String Select_opdrachten = "select * from opdrachten";
		String vraag = "";
		String juisteAntwoord = "";
		int maxAantalPogingen = 1;
		ArrayList<String> antwoordHints = new ArrayList<String>();
		;
		int maxAntwoordTijd = 1;
		UUID id = null;
		Leraar leraar = null;
		OpdrachtCategorie opdrachtCat = null;
		List<Opdracht> opdrachtLijst = new ArrayList<Opdracht>();
		Datum d = new Datum();

		try (Connection connection = DriverManager.getConnection(DB_URL);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(Select_opdrachten)) {
			ResultSetMetaData metaData = resultSet.getMetaData();
			int aantalKolommen = metaData.getColumnCount();
			System.out.printf("Lijst van Opdrachten\n");

			for (int i = 1; i <= aantalKolommen; i++) {
				System.out.printf("%-8s\t", metaData.getColumnName(i));
			}
			System.out.println();
			while (resultSet.next()) {

				for (int i = 1; i <= aantalKolommen; i++) {
					System.out.printf("%-8s\t", resultSet.getObject(i));					
					id = UUID.fromString(resultSet.getString("OPDRACHTID"));
					vraag = resultSet.getString("VRAAG");
					juisteAntwoord = resultSet.getString("JUISTEANTWOORD");
					maxAantalPogingen = resultSet.getInt("AANTALPOGINGEN");
					antwoordHints = new ArrayList<String>(Arrays.asList(resultSet.getString("ANTWOORDHINTS").split("\t")));
					maxAntwoordTijd = resultSet.getInt("ANTWOORDTIJD");
					leraar = Leraar.valueOf(resultSet.getString("LERAAR"));
					
					// door ontbreken Categorie in DB default categorie voor alles
					opdrachtCat = OpdrachtCategorie.valueOf("AlgemeneKennis");

				}
				Opdracht o = new Opdracht(id, vraag, juisteAntwoord, maxAantalPogingen, antwoordHints, maxAntwoordTijd, leraar,
						opdrachtCat);
				//o.setAanmaakDatum(d);
				opdrachtLijst.add(o);

				System.out.println();
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return opdrachtLijst;
		

	}

	@SuppressWarnings("null")
	@Override
	public List<Quiz> leesQuizzen() throws IOException, SQLException {
		final String DB_URL = "jdbc:derby:/opt/apps/Apache/db-derby-10.11.1.1-bin/bin/QuizAppDB;create=true";
		final String Select_quiz = "select * from quiz";
		String ow = "";
		String leraar = "";
		String leerjaar = "";
		Boolean isTest = false;
		Boolean isUniekeDeeln = false;
		UUID id = null;

		List<Quiz> quizLijst = new ArrayList<Quiz>();

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
			// List<Object> quizStrings = new ArrayList<Object>();
			while (resultSet.next()) {

				for (int i = 1; i <= aantalKolommen; i++) {
					System.out.printf("%-8s\t", resultSet.getObject(i));
					id = UUID.fromString(resultSet.getString("QUIZID"));
					ow = resultSet.getString("ONDERWERP");
					leerjaar = resultSet.getString("LEERJAAR");
					leraar = resultSet.getString("LERAAR");
					isTest = resultSet.getBoolean("ISTEST");
					isUniekeDeeln = resultSet.getBoolean("ISUNIEKEDEELNAME");

				}
				Quiz a = new Quiz(id, ow, Leerjaar.valueOf(leerjaar), isTest, isUniekeDeeln, Leraar.valueOf(leraar));
				quizLijst.add(a);
				System.out.println();

			}

		}
		return quizLijst;
		
	}

	@Override
	public List<QuizOpdrachtMapper> leesQuizOpdrachten() throws IOException {

		final String DB_URL = "jdbc:derby:/opt/apps/Apache/db-derby-10.11.1.1-bin/bin/QuizAppDB;create=true";
		final String Select_quizOpdrachten = "select * from quizopdrachten";
		List<QuizOpdrachtMapper> quizOpdrachtenLijst = new ArrayList<QuizOpdrachtMapper>();
		int score = 0;
		UUID opdrachtID = null;
		UUID quizID = null;

		try (Connection connection = DriverManager.getConnection(DB_URL);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(Select_quizOpdrachten)) {
			ResultSetMetaData metaData = resultSet.getMetaData();
			int aantalKolommen = metaData.getColumnCount();
			System.out.printf("Lijst van QuizOpdrachten\n");

			for (int i = 1; i <= aantalKolommen; i++) {
				System.out.printf("%-8s\t", metaData.getColumnName(i));
			}
			System.out.println();

			while (resultSet.next()) {

				for (int i = 1; i <= aantalKolommen; i++) {
					System.out.printf("%-8s\t", resultSet.getObject(i));
					score = resultSet.getInt("SCORE");
					opdrachtID = UUID.fromString(resultSet.getString("OPDRACHTID"));
					quizID = UUID.fromString(resultSet.getString("QUIZID"));
				}
				QuizOpdrachtMapper qOmapper = new QuizOpdrachtMapper(opdrachtID, quizID, score);
				quizOpdrachtenLijst.add(qOmapper);
				System.out.println();
				
			}

		} catch (SQLException e) {

			e.printStackTrace();
			
		}
		return quizOpdrachtenLijst;
		
		
	}

	@Override
	public void bewaarOpdrachten(List<Opdracht> opdrachten) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void bewaarQuizzen(List<Quiz> quizzen) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void bewaarQuizOpdrachten(List<QuizOpdracht> quizOpdrachten) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "Database";
	}

}
