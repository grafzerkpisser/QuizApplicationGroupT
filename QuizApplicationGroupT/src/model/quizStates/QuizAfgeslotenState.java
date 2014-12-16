package model.quizStates;

import java.io.Serializable;

import model.Quiz;
import model.QuizOpdracht;

public class QuizAfgeslotenState implements QuizState, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void verwijderQuizOpdracht(Quiz quiz, QuizOpdracht quizOpdracht)
			throws Exception {
		throw new Exception(String.format("QuizOpdracht %s kan niet verwijderd worden uit Quiz %s.", quizOpdracht, quiz));
		
	}

	@Override
	public void voegQuizOpdrachtToe(Quiz quiz, QuizOpdracht quizOpdracht)
			throws Exception {
		throw new Exception(String.format("QuizOpdracht %s kan niet toegevoegd worden aan Quiz %s.", quizOpdracht, quiz));
		
	}

	@Override
	public void verwijderQuiz(Quiz quiz) throws Exception {
		throw new Exception(String.format("Quiz %s kan niet verwijderd worden.", quiz));
		
	}

	@Override
	public void neemDeelAanQuiz(Quiz quiz) throws Exception {
		throw new Exception(String.format("Deelname aan quiz %s is niet meer mogelijk.", quiz));
		
	}

}
