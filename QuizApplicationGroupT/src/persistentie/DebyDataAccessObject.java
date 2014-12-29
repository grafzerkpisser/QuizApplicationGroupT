/**
 *
 */
package persistentie;

import java.io.IOException;
import java.util.List;

import model.Opdracht;
import model.Quiz;
import model.QuizOpdracht;
import persistentie.DataHandler.QuizOpdrachtMapper;

/**
 * @author java
 *
 */
public class DebyDataAccessObject implements DbStrategy {

	@Override
	public List<Opdracht> leesOpdrachten() throws NumberFormatException, IllegalArgumentException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Quiz> leesQuizzen() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuizOpdrachtMapper> leesQuizOpdrachten() throws IOException {
		// TODO Auto-generated method stub
		return null;
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

}
