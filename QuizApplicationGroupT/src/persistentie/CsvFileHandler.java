package persistentie;

import java.io.IOException;
import java.util.List;

import model.Opdracht;
import model.Quiz;
import model.QuizOpdracht;
import persistentie.DataHandler.QuizOpdrachtMapper;

public class CsvFileHandler implements DbStrategy {

	@Override
	public List<Opdracht> leesOpdrachten() throws NumberFormatException,
			IllegalArgumentException, IOException {
		return CsvWriterOpdracht.Lees();
	}

	@Override
	public List<Quiz> leesQuizzen() throws IOException {
		return CsvWriterQuiz.lees();
	}

	@Override
	public List<QuizOpdrachtMapper> leesQuizOpdrachten() throws IOException {
		return CsvWriterQuizOpdracht.lees();
	}

	@Override
	public void bewaarOpdrachten(List<Opdracht> opdrachten) throws IOException {
		CsvWriterOpdracht.bewaar(opdrachten);
	}

	@Override
	public void bewaarQuizzen(List<Quiz> quizzen) throws IOException {
		CsvWriterQuiz.bewaar(quizzen);
	}

	@Override
	public void bewaarQuizOpdrachten(List<QuizOpdracht> quizOpdrachten)
			throws IOException {
		CsvWriterQuizOpdracht.bewaar(quizOpdrachten);
	}
	@Override
	public String toString(){
		return "File Handler";
	}
}
