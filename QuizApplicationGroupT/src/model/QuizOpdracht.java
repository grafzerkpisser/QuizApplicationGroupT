package model;

import java.io.Serializable;


public class QuizOpdracht implements Comparable<QuizOpdracht>, Cloneable,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Quiz quiz;
	private Opdracht opdracht;
	private int maxScore;

	public QuizOpdracht(Quiz quiz, Opdracht opdracht, int maxScore) {
		this.quiz = quiz;
		this.opdracht = opdracht;
		this.maxScore = maxScore;
	}

	public static void koppelOpdrachtAanQuiz(Quiz quiz, Opdracht opdracht,
			int maxScore) throws Exception {
		QuizOpdracht quizOpdracht = new QuizOpdracht(quiz, opdracht, maxScore);
		quiz.voegQuizOpdrachtToe(quizOpdracht);
		opdracht.voegQuizOpdrachtToe(quizOpdracht);
	}

	public void ontKoppelOpdrachtVanQuiz() throws Exception {
		quiz.verwijderQuizOpdracht(this);
		opdracht.verwijderQuizOpdracht(this);
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public Opdracht getOpdracht() {
		return opdracht;
	}

	public Integer getMaxScore() {
		return maxScore;
	}
	
	@Override
	public int compareTo(QuizOpdracht eenQuizOpdracht) {
		return eenQuizOpdracht.compareTo(this);
	}

	@Override
	public QuizOpdracht clone() throws CloneNotSupportedException {

		try {
			QuizOpdracht q = (QuizOpdracht) super.clone();
			return q;
		} catch (CloneNotSupportedException e) {
			throw e;
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maxScore;
		result = prime * result
				+ ((opdracht == null) ? 0 : opdracht.hashCode());
		result = prime * result + ((quiz == null) ? 0 : quiz.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuizOpdracht other = (QuizOpdracht) obj;
		if (maxScore != other.maxScore)
			return false;
		if (opdracht == null) {
			if (other.opdracht != null)
				return false;
		} else if (!opdracht.equals(other.opdracht))
			return false;
		if (quiz == null) {
			if (other.quiz != null)
				return false;
		} else if (!quiz.equals(other.quiz))
			return false;
		return true;
	}

}
