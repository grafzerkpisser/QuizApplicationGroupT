package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import enumerations.Leraar;
import enumerations.OpdrachtCategorie;

public class OpdrachtMeerkeuze extends Opdracht implements Serializable, Valideerbaar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> keuzes;
	public ArrayList<String> getKeuzes()
	{
		return keuzes;
	}
	public static final String VALIDEERTEKST = "Geen geldige keuze";
	public OpdrachtMeerkeuze(UUID id, String vraag, String juisteAntwoord, Integer maxAantalPogingen, ArrayList<String> antwoordHints,
			Integer maxAntwoordTijd, ArrayList<String> keuzes, 
			Leraar leeraar, OpdrachtCategorie opdrachtCategorie) {
		super(id, vraag, juisteAntwoord, maxAantalPogingen, antwoordHints, maxAntwoordTijd, 
				leeraar,opdrachtCategorie);
		this.keuzes = keuzes;
	}

	
	public void voegKeuzeToe(String keuze)
	{
		keuzes.add(keuze);
	}
	public void verwijderKeuze(Integer index)
	{
		@SuppressWarnings("unused")
		boolean b = (keuzes.remove(index.intValue()) != null);
		
	}
	public boolean valide(String antwoord) {
		for (String s : keuzes) {
			if(s==antwoord)
				return true;
		}
		return false;
	}
	public String getValideerTekst() {
		return VALIDEERTEKST;
	}
	@Override
	public String toString() {
		return super.toString() + "Meerkeuze [keuzes=" + keuzes + "]";
	}

}
