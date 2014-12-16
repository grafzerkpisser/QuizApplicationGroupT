package model.quizStates;

import java.io.Serializable;
import model.Quiz;
import model.QuizOpdracht;

public class QuizInConstructionState implements QuizState, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void verwijderQuizOpdracht(Quiz quiz, QuizOpdracht quizOpdracht)
			throws Exception {
		quiz.getQuizOpdrachten().remove(quizOpdracht);
	}

	@Override
	public void voegQuizOpdrachtToe(Quiz quiz, QuizOpdracht quizOpdracht)
			throws Exception {
		quiz.getQuizOpdrachten().add(quizOpdracht);
	}

	@Override
	public void verwijderQuiz(Quiz quiz) throws Exception {
		Integer j = quiz.getQuizOpdrachten().size();
		for(int i = 0; i < j; i=i+1)
		{
			quiz.getQuizOpdracht(i).ontKoppelOpdrachtVanQuiz();
		}
	
		
		
	}

	@Override
	public void neemDeelAanQuiz(Quiz quiz) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
