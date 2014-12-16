package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Quiz;

public class QuizCatalogus implements Iterable<QuizCatalogus>,
		Comparable<QuizCatalogus>, Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private List<Quiz> quizLijst;

	public List<Quiz> getQuizLijst(){
		return quizLijst;
	}
	public QuizCatalogus() {
		quizLijst = new ArrayList<Quiz>();
	}

	public void voegQuizToe(Quiz q) throws Exception {
		if(quizLijst.stream().filter(x -> x.getOnderwerp().equals(q.getOnderwerp())).findFirst().isPresent())
		{
		 throw new Exception("Quiz is reeds toegevoegd");	
		}
		else
		{
		quizLijst.add(q);
		}
	}

	public void verwijderQuiz(Quiz q) throws Exception {
		quizLijst.remove(q);
	}

	public void verwijderQuiz(int index) {
		quizLijst.remove(index);
	}
	
	public void UpdateQuiz(Quiz quiz ) throws Exception{
		for(int x = 0; x < this.GetAantalQuizzes();x=x+1){
			if(this.getQuizLijst().get(x).getQuizId().equals(quiz.getQuizId())){
				this.getQuizLijst().get(x).setLeerjaar(quiz.getLeerjaar());
				this.getQuizLijst().get(x).setOnderwerp(quiz.getOnderwerp());
				this.getQuizLijst().get(x).setLeraar(quiz.getLeraar());
				this.getQuizLijst().get(x).setQuizStatus(quiz.getQuizStatus());
				for(QuizOpdracht qo : quiz.getQuizOpdrachten()){
					if(! this.getQuizLijst().get(x).getQuizOpdrachten().stream()
							.filter(y -> y.getOpdracht().getOpdrachtId()
									.equals(qo.getOpdracht().getOpdrachtId())).findFirst().isPresent()){
						this.getQuizLijst().get(x).voegQuizOpdrachtToe(qo);
					}
				}
				break;
			}
		}
	}

	public Object getWaarde(int rij, int kol) {
		Object terug = null;
		if (rij < this.quizLijst.size()) {
			if (kol < 3) {
				terug = kol == 0 ? quizLijst.get(rij).getOnderwerp()
						: kol == 1 ? quizLijst.get(rij).getLeerjaar()
								: quizLijst.get(rij).getQuizStatus();
			}
		}
		return terug;
	}

	public int GetAantalQuizzes() {
		return quizLijst.size();
	}

	public Quiz get(int index) {
		return this.quizLijst.get(index);
	}

	@Override
	public QuizCatalogus clone() throws CloneNotSupportedException {

		QuizCatalogus q_c = new QuizCatalogus();
		q_c.quizLijst = this.quizLijst;
		return q_c;
	}

	@Override
	public String toString() {
		String quizen = "";
		for (Quiz q : quizLijst) {
			quizen += q.toString() + "/n";
		}
		return quizen;
	}

	@Override
	public int compareTo(QuizCatalogus q) {
		return q.compareTo(this);
	}

	@Override
	public Iterator<QuizCatalogus> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((quizLijst == null) ? 0 : quizLijst.hashCode());
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
		QuizCatalogus other = (QuizCatalogus) obj;
		if (quizLijst == null) {
			if (other.quizLijst != null)
				return false;
		} else if (!quizLijst.equals(other.quizLijst))
			return false;
		return true;
	}

}
