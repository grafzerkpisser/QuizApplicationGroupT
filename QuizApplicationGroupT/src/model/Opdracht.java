package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import model.QuizOpdracht;
import utils.datum.Datum;
import enumerations.*;



public class Opdracht implements Comparable<Opdracht>, Cloneable, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vraag;
	private String juisteAntwoord;
	private Integer maxAantalPogingen;
	private ArrayList<String> antwoordHints;
	private Integer maxAntwoordTijd;
	private Leraar leraar;
	private OpdrachtCategorie opdrachtCategorie;
	private Datum aanmaakDatum;
	private UUID opdrachtId;
	private ArrayList<QuizOpdracht> quizOpdrachten;

	// Getters & Setters
	public void setOpdrachtId(UUID opdrachtId) {
		this.opdrachtId = opdrachtId;
	}

	public UUID getOpdrachtId() {
		return opdrachtId;
	}

	public void setAanmaakDatum(Datum aanmaakDatum) {
		this.aanmaakDatum = aanmaakDatum;
	}

	public void setVraag(String vraag) {
		this.vraag = vraag;
	}

	public void setJuisteAntwoord(String juisteAntwoord) {
		this.juisteAntwoord = juisteAntwoord;
	}

	public void setMaxAantalPogingen(Integer maxAantalPogingen) {
		this.maxAantalPogingen = maxAantalPogingen;
	}

	public void setAntwoordHints(ArrayList<String> antwoordHints) {
		this.antwoordHints = antwoordHints;
	}

	public void setMaxAntwoordTijd(Integer maxAntwoordTijd) {
		this.maxAntwoordTijd = maxAntwoordTijd;
	}

	public void setLeraar(Leraar leraar) {
		this.leraar = leraar;
	}

	public void setOpdrachtCategorie(OpdrachtCategorie opdrachtCategorie) {
		this.opdrachtCategorie = opdrachtCategorie;
	}

	public Datum getAanmaakDatum() {
		return aanmaakDatum;
	}

	public OpdrachtCategorie getOpdrachtCategorie() {
		return opdrachtCategorie;
	}

	public Leraar getLeeraar() {
		return leraar;
	}

	public String getVraag() {
		return vraag;
	}

	public String getJuisteAntwoord() {
		return juisteAntwoord;
	}

	public Integer getMaxaantaPogingen() {
		return maxAantalPogingen;
	}

	public ArrayList<String> getAntwoordHints() {
		return antwoordHints;
	}

	public Integer getMaxAntwoordTijd() {
		return maxAntwoordTijd;
	}
	public ArrayList<QuizOpdracht> getQuizOpdrachten(){
		return quizOpdrachten;
	}

	//Constructor
	public Opdracht(UUID opdrachtId, String vraag, String juisteAntwoord, Integer maxAantalPogingen, ArrayList<String> antwoordHints,
			Integer maxAntwoordTijd, Leraar leeraar, OpdrachtCategorie opdrachtCategorie) {
		this.quizOpdrachten = new ArrayList<QuizOpdracht>();
		this.opdrachtId = opdrachtId;
		this.vraag = vraag;
		this.juisteAntwoord = juisteAntwoord;
		this.maxAantalPogingen = maxAantalPogingen;
		this.antwoordHints = antwoordHints;
		this.maxAntwoordTijd = maxAntwoordTijd;
		this.leraar = leeraar;
		this.opdrachtCategorie = opdrachtCategorie;
		this.aanmaakDatum = new Datum();
	}
	public Opdracht(Opdracht opdracht){
		this.quizOpdrachten = new ArrayList<QuizOpdracht>();
		this.opdrachtId = opdracht.getOpdrachtId();
		this.vraag = opdracht.getVraag();
		this.juisteAntwoord = opdracht.getJuisteAntwoord();
		this.maxAantalPogingen = opdracht.getMaxaantaPogingen();
		this.antwoordHints = opdracht.getAntwoordHints();
		this.maxAntwoordTijd = opdracht.getMaxAntwoordTijd();
		this.leraar = opdracht.leraar;
		this.opdrachtCategorie = opdracht.getOpdrachtCategorie();
		this.aanmaakDatum = opdracht.getAanmaakDatum();
	}
	
	//Methods
	public void voegQuizOpdrachtToe(QuizOpdracht quizOpdracht) {
		quizOpdrachten.add(quizOpdracht);
	}

	public void verwijderQuizOpdracht(QuizOpdracht quizOpdracht) {
		quizOpdrachten.remove(quizOpdracht);
	}

	public String toString() {
		return vraag + "(" + juisteAntwoord + ")";
	}
	
	//Override Methods
		@Override
		public int compareTo(Opdracht o) {
			return o.compareTo(this);
		}
		@Override
		public Opdracht clone() throws CloneNotSupportedException {

			try {
				Opdracht q = (Opdracht) super.clone();
				return q;
			} catch (CloneNotSupportedException e) {
				throw e;
			}
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((aanmaakDatum == null) ? 0 : aanmaakDatum.hashCode());
			result = prime * result
					+ ((antwoordHints == null) ? 0 : antwoordHints.hashCode());
			result = prime
					* result
					+ ((juisteAntwoord == null) ? 0 : juisteAntwoord.hashCode());
			result = prime * result
					+ ((leraar == null) ? 0 : leraar.hashCode());
			result = prime
					* result
					+ ((maxAantalPogingen == null) ? 0 : maxAantalPogingen
							.hashCode());
			result = prime
					* result
					+ ((maxAntwoordTijd == null) ? 0 : maxAntwoordTijd
							.hashCode());
			result = prime
					* result
					+ ((opdrachtCategorie == null) ? 0 : opdrachtCategorie
							.hashCode());
			result = prime * result
					+ ((opdrachtId == null) ? 0 : opdrachtId.hashCode());
			result = prime
					* result
					+ ((quizOpdrachten == null) ? 0 : quizOpdrachten.hashCode());
			result = prime * result + ((vraag == null) ? 0 : vraag.hashCode());
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
			Opdracht other = (Opdracht) obj;
			if (aanmaakDatum == null) {
				if (other.aanmaakDatum != null)
					return false;
			} else if (!aanmaakDatum.equals(other.aanmaakDatum))
				return false;
			if (antwoordHints == null) {
				if (other.antwoordHints != null)
					return false;
			} else if (!antwoordHints.equals(other.antwoordHints))
				return false;
			if (juisteAntwoord == null) {
				if (other.juisteAntwoord != null)
					return false;
			} else if (!juisteAntwoord.equals(other.juisteAntwoord))
				return false;
			if (leraar != other.leraar)
				return false;
			if (maxAantalPogingen == null) {
				if (other.maxAantalPogingen != null)
					return false;
			} else if (!maxAantalPogingen.equals(other.maxAantalPogingen))
				return false;
			if (maxAntwoordTijd == null) {
				if (other.maxAntwoordTijd != null)
					return false;
			} else if (!maxAntwoordTijd.equals(other.maxAntwoordTijd))
				return false;
			if (opdrachtCategorie != other.opdrachtCategorie)
				return false;
			if (opdrachtId == null) {
				if (other.opdrachtId != null)
					return false;
			} else if (!opdrachtId.equals(other.opdrachtId))
				return false;
			if (quizOpdrachten == null) {
				if (other.quizOpdrachten != null)
					return false;
			} else if (!quizOpdrachten.equals(other.quizOpdrachten))
				return false;
			if (vraag == null) {
				if (other.vraag != null)
					return false;
			} else if (!vraag.equals(other.vraag))
				return false;
			return true;
		}
		

}
