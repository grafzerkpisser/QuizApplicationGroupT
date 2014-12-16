package model.quizStates;

import model.QuizOpdracht;
import model.Quiz;

public interface QuizState {
	void verwijderQuizOpdracht(Quiz quiz, QuizOpdracht quizOpdracht) throws Exception;
	void voegQuizOpdrachtToe(Quiz quiz, QuizOpdracht quizOpdracht)throws Exception;
	void verwijderQuiz(Quiz quiz)throws Exception;
	void neemDeelAanQuiz(Quiz quiz)throws Exception;
}
