package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.quizStates.*;
import model.QuizOpdracht;
import utils.datum.Datum;
import enumerations.*;

public class Quiz implements Comparable<Quiz>, Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UUID quizId;
	private String onderwerp;
	private Leerjaar leerjaar;
	private Boolean isTest;
	private Boolean isUniekeDeelname;
	private QuizStatus quizStatus;
	private Leraar leraar;
	private Datum registratieDatum;
	private List<QuizOpdracht> quizOpdrachten;
	private model.quizStates.QuizState quizState;

	// constructor met parameters
	public Quiz(String onderwerp, Leerjaar leerjaar, Boolean isTest,
			Boolean isUniekeDeelname, Leraar leraar) {
		quizOpdrachten = new ArrayList<QuizOpdracht>();
		this.quizId = UUID.randomUUID();
		this.onderwerp = onderwerp;
		this.leerjaar = leerjaar;
		this.isTest = isTest;
		this.isUniekeDeelname = isUniekeDeelname;
		this.leraar = leraar;
		registratieDatum = new Datum();
		this.setQuizStatus(QuizStatus.IN_CONSTRUCTIE);
	}

	// Getters 
	public List<QuizOpdracht> getQuizOpdrachten(){
		return quizOpdrachten;
	}
	public Leraar getLeraar() {
		return this.leraar;
	}

	public void setLeraar(Leraar leraar) {
		this.leraar = leraar;
	}

	public UUID getQuizId() {
		return quizId;
	}

	public String getOnderwerp() {
		return onderwerp;
	}

	public Leerjaar getLeerjaar() {
		return leerjaar;
	}

	public Boolean getIsTest() {
		return isTest;
	}

	public Boolean getIsUniekeDeelname() {
		return isUniekeDeelname;
	}

	public QuizStatus getQuizStatus() {
		return quizStatus;
	}

	public String getCleanOnderwerp() {
		String regex = "\\s*(\\b(de|een|het|met|van|in)\\b|,|\\.|\\?|\\!|;|\\:)\\s*";

		String nieuwOnderwerp = this.onderwerp.replaceAll(regex, "");
		return nieuwOnderwerp;
	}

	// Setters
	public void setOnderwerp(String onderwerp) {
		this.onderwerp = onderwerp;
	}

	public void setLeerjaar(Leerjaar leerjaar) {
		this.leerjaar = leerjaar;
	}

	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

	public void setQuizId(UUID quizId) {
		this.quizId = quizId;
	}

	public void setIsUniekeDeelname(Boolean isUniekeDeelname) {
		this.isUniekeDeelname = isUniekeDeelname;
	}

	public void setQuizStatus(QuizStatus quizStatus) {
		this.quizStatus = quizStatus;
		switch (quizStatus) {
		case IN_CONSTRUCTIE:
			quizState = new QuizInConstructionState();
			break;
		case AFGESLOTEN:
			quizState = new QuizAfgeslotenState();
			break;
		case AFGEWERKT:
			quizState = new QuizAfgewerktState();
			break;
		case LAATSTE_KANS:
			quizState = new QuizLaatsteKansState();
			break;
		case OPENGESTELD:
			quizState = new QuizAfgeslotenState();
			break;
		}
	}

	public ArrayList<Opdracht> getOpdrachten() {
		ArrayList<Opdracht> opdrachten = new ArrayList<Opdracht>();
		for (QuizOpdracht quizOpdracht : quizOpdrachten) {
			opdrachten.add(quizOpdracht.getOpdracht());
		}
		return opdrachten;
	}

	public QuizOpdracht getOpdracht(int volgnr) {
		return quizOpdrachten.get(volgnr - 1);
	}

	
	public void verwijderQuizOpdracht(QuizOpdracht quizOpdracht) throws Exception {
		this.quizState.verwijderQuizOpdracht(this, quizOpdracht);
	}

	public void voegQuizOpdrachtToe(QuizOpdracht quizOpdracht) throws Exception {
		this.quizState.voegQuizOpdrachtToe(this, quizOpdracht);
	}
	
	public void verwijderQuiz() throws Exception
	{
		this.quizState.verwijderQuiz(this);
	}
	public Opdracht get(int index) {
		return this.getQuizOpdrachten().get(index).getOpdracht();
	}
	public Integer getAantalOpdrachten(){
		return this.getQuizOpdrachten().size();
	}
	public QuizOpdracht getQuizOpdracht(Integer row){
		return this.quizOpdrachten.get(row);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isTest == null) ? 0 : isTest.hashCode());
		result = prime
				* result
				+ ((isUniekeDeelname == null) ? 0 : isUniekeDeelname.hashCode());
		result = prime * result
				+ ((leerjaar == null) ? 0 : leerjaar.hashCode());
		result = prime * result + ((leraar == null) ? 0 : leraar.hashCode());
		result = prime * result
				+ ((onderwerp == null) ? 0 : onderwerp.hashCode());
		result = prime * result + ((quizId == null) ? 0 : quizId.hashCode());
		result = prime * result
				+ ((quizOpdrachten == null) ? 0 : quizOpdrachten.hashCode());
		result = prime * result
				+ ((quizStatus == null) ? 0 : quizStatus.hashCode());
		result = prime
				* result
				+ ((registratieDatum == null) ? 0 : registratieDatum.hashCode());
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
		Quiz other = (Quiz) obj;
		if (isTest == null) {
			if (other.isTest != null)
				return false;
		} else if (!isTest.equals(other.isTest))
			return false;
		if (isUniekeDeelname == null) {
			if (other.isUniekeDeelname != null)
				return false;
		} else if (!isUniekeDeelname.equals(other.isUniekeDeelname))
			return false;
		if (leerjaar != other.leerjaar)
			return false;
		if (leraar != other.leraar)
			return false;
		if (onderwerp == null) {
			if (other.onderwerp != null)
				return false;
		} else if (!onderwerp.equals(other.onderwerp))
			return false;
		if (quizId == null) {
			if (other.quizId != null)
				return false;
		} else if (!quizId.equals(other.quizId))
			return false;
		if (quizOpdrachten == null) {
			if (other.quizOpdrachten != null)
				return false;
		} else if (!quizOpdrachten.equals(other.quizOpdrachten))
			return false;
		if (quizStatus != other.quizStatus)
			return false;
		if (registratieDatum == null) {
			if (other.registratieDatum != null)
				return false;
		} else if (!registratieDatum.equals(other.registratieDatum))
			return false;
		return true;
	}

	@Override
	public int compareTo(Quiz o) {
		if (o.onderwerp.equalsIgnoreCase(this.onderwerp)) {
			return 0;
		} else {
			String inputNieuwOnderwerp = o.getCleanOnderwerp();

			if (getCleanOnderwerp().replace(" ", "").equalsIgnoreCase(inputNieuwOnderwerp.replace(" ", ""))) {
				return 0;
			}
		}
		return this.registratieDatum.verschilInDagen(o.registratieDatum);
	}
	@Override
	public String toString() {
		return String.format("%s, %s", this.onderwerp, this.registratieDatum);
	}
	
	@Override
	public Quiz clone() throws CloneNotSupportedException {

		try {
			Quiz q = (Quiz) super.clone();
			return q;
		} catch (CloneNotSupportedException e) {
			throw e;
		}
	}
	

}
