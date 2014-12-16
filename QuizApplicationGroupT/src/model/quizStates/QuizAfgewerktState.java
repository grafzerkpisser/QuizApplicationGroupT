package model.quizStates;

import java.io.Serializable;

import model.Quiz;
import model.QuizOpdracht;

public class QuizAfgewerktState implements QuizState,Serializable {

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
		for(QuizOpdracht quizOpdracht : quiz.getQuizOpdrachten())
		{
			quizOpdracht.ontKoppelOpdrachtVanQuiz();
		}
		//TODO DELETE
		
	}

	@Override
	public void neemDeelAanQuiz(Quiz quiz) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
