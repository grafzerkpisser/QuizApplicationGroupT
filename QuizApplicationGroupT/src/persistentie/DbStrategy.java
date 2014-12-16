package persistentie;

import java.io.IOException;
import java.util.List;

import persistentie.DataHandler.QuizOpdrachtMapper;
import model.Opdracht;
import model.QuizOpdracht;
import model.Quiz;

public interface DbStrategy {
	List<Opdracht> leesOpdrachten() throws NumberFormatException, IllegalArgumentException, IOException;
	List<Quiz> leesQuizzen() throws IOException;
	List<QuizOpdrachtMapper> leesQuizOpdrachten() throws IOException;
	void bewaarOpdrachten(List<Opdracht>opdrachten) throws IOException;
	void bewaarQuizzen(List<Quiz>quizzen) throws IOException;
	void bewaarQuizOpdrachten(List<QuizOpdracht>quizOpdrachten) throws IOException;
}
