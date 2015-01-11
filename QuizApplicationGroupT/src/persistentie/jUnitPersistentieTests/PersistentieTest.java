package persistentie.jUnitPersistentieTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.OpdrachtMeerkeuze;
import model.Opdracht;
import model.OpdrachtCatalogus;
import model.QuizCatalogus;
import model.QuizOpdracht;
import model.Quiz;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import persistentie.CsvWriterOpdracht;
import persistentie.CsvWriterQuiz;
import persistentie.CsvWriterQuizOpdracht;
import persistentie.DataHandler;
import persistentie.DataHandler.QuizOpdrachtMapper;
import enumerations.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersistentieTest {

	OpdrachtCatalogus opdrachtCatalogus;
	QuizCatalogus quizCatalogus;
	List<Opdracht> opdrachten = new ArrayList<Opdracht>();
	List<Quiz> quizzen = new ArrayList<Quiz>();
	List<QuizOpdracht> quizOpdrachten = new ArrayList<QuizOpdracht>();

	@Before
	public void setUp() throws Exception {
		opdrachtCatalogus = new OpdrachtCatalogus();
		ArrayList<String> antwoordHints = new ArrayList<String>();
		antwoordHints.add("Manneke Pis");
		antwoordHints.add("Atomium");
		ArrayList<String> keuzes = new ArrayList<String>();
		keuzes.add("Brussel");
		keuzes.add("Antwerpen");
		keuzes.add("Gent");
		Opdracht o1 = new Opdracht(UUID.randomUUID(),"Wat is de Hoofdstad van Belgie", "Brussel", 1,
				antwoordHints, 60, Leraar.Bart,
				OpdrachtCategorie.AlgemeneKennis);
		Opdracht o2 = new OpdrachtMeerkeuze(UUID.randomUUID(), "Hoofdstad van Belgie", "Brussel", 1,
				antwoordHints, 60, keuzes, Leraar.Bart,
				OpdrachtCategorie.AlgemeneKennis);
		opdrachtCatalogus.voegOpdrachtToe(o1);
		opdrachtCatalogus.voegOpdrachtToe(o2);

		quizCatalogus = new QuizCatalogus();
		Quiz q1 = new Quiz(UUID.randomUUID(), "Hoofdsteden", Leerjaar.derde, false, false,
				Leraar.Bart);
		Quiz q2 = new Quiz(UUID.randomUUID(), "Hoofdsteden2", Leerjaar.eerste, false, false,
				Leraar.Brecht);
		QuizOpdracht.koppelOpdrachtAanQuiz(q1, o1, 5);
		QuizOpdracht.koppelOpdrachtAanQuiz(q2, o1, 2);
		QuizOpdracht.koppelOpdrachtAanQuiz(q2, o2, 7);
		quizCatalogus.voegQuizToe(q1);
		quizCatalogus.voegQuizToe(q2);
		opdrachten.add(o1);
		opdrachten.add(o2);
		quizzen.add(q1);
		quizzen.add(q2);
		for (Quiz quiz : quizCatalogus.getQuizLijst()) {
			for(QuizOpdracht qo : quiz.getQuizOpdrachten()){
				quizOpdrachten.add(qo);
			}
		}
	}

	@Test
	public void test1_Wegschrijven_opdrachten_Aanvaard() {
		Boolean success = true;
		try {
			CsvWriterOpdracht.bewaar(opdrachten);

		} catch (IOException e) {
			success = false;
		}
		assertTrue(success);
	}

	@Test
	public void test2_Ophalen_opdrachten_Aanvaard() {
		Boolean success = true;
		List<Opdracht> o = new ArrayList<Opdracht>();
		try {
			o = CsvWriterOpdracht.Lees();
		} catch (IllegalArgumentException | IOException e) {
			success = false;
		}
		assertTrue(success && o.size() > 0);
	}
	@Test
	public void test3_Wegschrijven_quizzen_Aanvaard(){
		Boolean success = true;
		try {
			CsvWriterQuiz.bewaar(quizzen);

		} catch (IOException e) {
			success = false;
		}
		assertTrue(success);
	}
	@Test
	public void test4_Ophalen_quizzen_Aanvaard(){
		Boolean success = true;
		List<Quiz> q = new ArrayList<Quiz>();
		try {
			q= CsvWriterQuiz.lees();
		} catch (IllegalArgumentException | IOException e) {
			success = false;
		}
		assertTrue(success && q.size() > 0);
	}
	@Test
	public void test5_Wegschrijven_quizOpdrachten_Aanvaard(){
		Boolean success = true;
		try {
			CsvWriterQuizOpdracht.bewaar(quizOpdrachten);

		} catch (IOException e) {
			success = false;
		}
		assertTrue(success);
	}
	@Test
	public void test6_Ophalen_quizopdrachten_Aanvaard(){
		Boolean success = true;
		List<QuizOpdrachtMapper> qo = new ArrayList<QuizOpdrachtMapper>();
		try {
			qo= CsvWriterQuizOpdracht.lees();
		} catch (IllegalArgumentException | IOException e) {
			success = false;
		}
		assertTrue(success && qo.size() > 0);
	}
	@Test
	public void test7_Mapper_Aanvaard(){
		DataHandler dH = new DataHandler();
		try {
			dH.bewaarCatalogi(opdrachtCatalogus,quizCatalogus,quizOpdrachten);
			dH.vulCatalogi();
		} catch (Exception e) {
			assertFalse(true);
		}
		assertTrue(true);
	}

}
