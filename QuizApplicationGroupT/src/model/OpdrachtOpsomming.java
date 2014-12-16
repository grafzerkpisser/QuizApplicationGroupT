package model;

import java.io.Serializable;
import java.util.ArrayList;

import enumerations.*;

public class OpdrachtOpsomming extends Opdracht implements Valideerbaar,
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean inJuisteVolgorde;
	public static final String VALIDEERTEKST = "Typ je antwoorden achter elkaar gescheiden door ;";

	public OpdrachtOpsomming(String vraag, String juisteAntwoord,
			Integer maxAantalPogingen, ArrayList<String> antwoordHints,
			Integer maxAntwoordTijd, Boolean inJuisteVolgorde, Leraar leraar,
			OpdrachtCategorie opdrachtCategorie) {
		super(vraag, juisteAntwoord, maxAantalPogingen, antwoordHints,
				maxAntwoordTijd, leraar, opdrachtCategorie);
		this.inJuisteVolgorde = inJuisteVolgorde;
	}

	public Boolean getInJuisteVolgorde() {
		return inJuisteVolgorde;
	}

	public String getValideerTekst() {
		return VALIDEERTEKST;
	}

	public void setInJuisteVolgorde(Boolean inJuisteVolgorde) {
		this.inJuisteVolgorde = inJuisteVolgorde;
	}

	public boolean valide(String antwoord) {
		String[] parts = antwoord.split(";");
		if (parts.length != this.getJuisteAntwoord().split(";").length)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Opsomming [inJuisteVolgorde=" + inJuisteVolgorde + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((inJuisteVolgorde == null) ? 0 : inJuisteVolgorde.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpdrachtOpsomming other = (OpdrachtOpsomming) obj;
		if (inJuisteVolgorde == null) {
			if (other.inJuisteVolgorde != null)
				return false;
		} else if (!inJuisteVolgorde.equals(other.inJuisteVolgorde))
			return false;
		return true;
	}

}
