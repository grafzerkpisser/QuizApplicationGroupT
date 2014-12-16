package utils.datumScratch;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("serial")
public class Datum implements Comparable<Datum>, Serializable {
	private int dag;
	private int maand;
	private int jaar;

	private final int aantalDagenPerGewoonJaar = 365;
	private final int aantalDagenPerSchrikkelJaar = 366;

	@SuppressWarnings("deprecation")
	public Datum() {
		Date sysDatum = new Date();

		dag = sysDatum.getDate();
		maand = sysDatum.getMonth();
		jaar = sysDatum.getYear();
	}

	public Datum(Datum datum) {
		dag = datum.dag;
		maand = datum.maand;
		jaar = datum.jaar;
	}

	public Datum(int dag, int maand, int jaar) throws IllegalArgumentException {
		setDatum(dag, maand, jaar);
	}

	public Datum(String datum) throws IllegalArgumentException {
		if (datum.length() != 10) {
			throw new IllegalArgumentException("Datum moet ingegeven worden in het formaat \"DD/MM/JJJJ\".");
		}

		String subJaar = datum.substring(6, 10);
		int berekendJaar;

		try {
			berekendJaar = Integer.parseInt(subJaar);
			if (berekendJaar <= 0 || berekendJaar > 9999) {
				throw new IllegalArgumentException("Jaar moet liggen tussen 1 en 9999.");
			}
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Jaar moet numeriek zijn en liggen tussen 1 en 9999.");
		}

		String subMaand = datum.substring(3, 5);
		int berekendeMaand;

		try {
			berekendeMaand = Integer.parseInt(subMaand);

			if (berekendeMaand <= 0 || berekendeMaand > 12) {
				throw new IllegalArgumentException("Maand moet liggen tussen 1 en 12.");
			}
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Maand moet numeriek zijn en liggen tussen 1 en 12.");
		}

		String subDag = datum.substring(0, 2);
		int berekendeDag;
		int maxDagen = getAantalDagenInMaand(berekendeMaand, isSchrikkeljaar(berekendJaar));

		try {
			berekendeDag = Integer.parseInt(subDag);
			if (berekendeDag <= 0 || berekendeDag > maxDagen) {
				throw new IllegalArgumentException(String.format("Dag moet liggen tussen 1 en %s.", maxDagen));
			}
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException(String.format("Dag moet numeriek zijn en liggen tussen 1 en %s.", maxDagen));
		}

		this.dag = berekendeDag;
		this.maand = berekendeMaand;
		this.jaar = berekendJaar;
	}



	@Override
	public int compareTo(Datum d) {
		return verschilInDagen(d);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aantalDagenPerGewoonJaar;
		result = prime * result + aantalDagenPerSchrikkelJaar;
		result = prime * result + dag;
		result = prime * result + jaar;
		result = prime * result + maand;
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
		Datum other = (Datum) obj;
		if (aantalDagenPerGewoonJaar != other.aantalDagenPerGewoonJaar)
			return false;
		if (aantalDagenPerSchrikkelJaar != other.aantalDagenPerSchrikkelJaar)
			return false;
		if (dag != other.dag)
			return false;
		if (jaar != other.jaar)
			return false;
		if (maand != other.maand)
			return false;
		return true;
	}

	public boolean kleinerDan(Datum d) {
		return verschilInDagen(d) < 0;
	}

	public Boolean setDatum(int dag, int maand, int jaar) throws IllegalArgumentException {
		if (jaar <= 0 || jaar > 9999) {
			throw new IllegalArgumentException("Jaar moet liggen tussen 1 en 9999.");
		}

		if (maand <= 0 || maand > 12) {
			throw new IllegalArgumentException("Maand moet liggen tussen 1 en 12.");
		}

		int maxDagen = getAantalDagenInMaand(maand, isSchrikkeljaar(jaar));
		if (dag <= 0 || dag > maxDagen) {
			throw new IllegalArgumentException(String.format("Dag moet liggen tussen 1 en %s.", maxDagen));
		}

		this.dag = dag;
		this.maand = maand;
		this.jaar = jaar;
		return true;
	}

	public String getDatumInAmerikaansFormaat() {
		return String.format("%s/%s/%s", this.jaar, this.maand, this.dag);
	}

	public String getDatumInEuropeesFormaat() {
		return String.format("%s/%s/%s", this.dag, this.maand, this.jaar);
	}

	@Override
	public String toString() {
		return String.format("%s %s %s", this.dag, getMaandWaarde(), this.jaar);
	}

	public int verschilInDagen(Datum d) {
		return getDatumInDagen(d) - getDatumInDagen(this);
	}

	public int verschilInMaanden(Datum d) {
		int verschilInJaar = d.jaar - this.jaar;
		int verschilInDag = d.dag - this.dag;
		int verschilInMaand = d.maand - this.maand;
		// TODO review this part
		if (verschilInJaar == 0) {
			if (verschilInDag > 0) {
				verschilInMaand += 1;
			}
			if (verschilInDag < 0) {
				verschilInMaand -= 1;
			}
		} else {
			verschilInMaand += verschilInJaar * 12;

			if (verschilInJaar < 0 && verschilInDag > 0) {
				verschilInMaand += 1;
			} else if (verschilInJaar > 0 && verschilInDag < 0) {
				verschilInMaand -= 1;
			}
		}

		if (verschilInMaand < 0) {
			verschilInMaand *= -1;
		}

		return verschilInMaand;
	}

	public int verschilInJaren(Datum d) {
		int verschilJaren = d.jaar - this.jaar;
		int verschilDagen = getAantalDagenVoorDagEnMaand(d) - getAantalDagenVoorDagEnMaand(this);

		if (verschilJaren < 0) {
			verschilJaren *= -1;

			if (verschilDagen > 0) {
				verschilJaren -= 1;
			}
		} else if (verschilDagen < 0) {
			verschilJaren -= 1;
		}

		return verschilJaren;
	}

	public void veranderDatum(int aantalDagen) throws IllegalArgumentException {
		Datum datum = veranderDatumInNieuwObject(aantalDagen);

		this.dag = datum.dag;
		this.maand = datum.maand;
		this.jaar = datum.jaar;
	}

	public Datum veranderDatumInNieuwObject(int aantalDagen) throws IllegalArgumentException {
		int dagen = getDatumInDagen(this);
		int maxDagen = getDatumInDagen(new Datum(31, 12, 9999)) - dagen;
		int nieuweDatum = dagen + aantalDagen;
		int month = 1;

		if (nieuweDatum < 1 || aantalDagen > maxDagen) {
			throw new IllegalArgumentException(String.format("Aantal dagen moet groter zijn dan -%s en kleiner dan %s", dagen,
					maxDagen));
		}
		int aantalDagen4Jaar = (3 * aantalDagenPerGewoonJaar) + aantalDagenPerSchrikkelJaar;
		int aantalDagen400Jaar = (aantalDagen4Jaar * 100) - 3;
		int aantalDagen100Jaar = (aantalDagen4Jaar * 25) - 1;

		int datum400Jaar = nieuweDatum / aantalDagen400Jaar;
		int rest400Jaar = nieuweDatum % aantalDagen400Jaar;
		int datum100Jaar = rest400Jaar / aantalDagen100Jaar;
		int rest100Jaar = rest400Jaar % aantalDagen100Jaar;
		int datum4Jaar = rest100Jaar / aantalDagen4Jaar;
		int rest4Jaar = rest100Jaar % aantalDagen4Jaar;
		int datumJaar = rest4Jaar / aantalDagenPerGewoonJaar;
		int restDagen = rest4Jaar % aantalDagenPerGewoonJaar;
		// Het 4e jaar van een periode van 4 jaar is een schrikkeljaar. We weten dus dat dit geen volledig
		// schrikkeljaar kan zijn.

		int jaar = (datum400Jaar * 400) + (datum100Jaar * 100) + (datum4Jaar * 4) + datumJaar;

		if (restDagen == 0) {
			aantalDagen = 31;
			month = 12;
			jaar--;
		} else {
			boolean monthFound = false;

			while (monthFound == false) {
				int aantalDagenInMaand = getAantalDagenInMaand(month, datumJaar == 3);

				if (aantalDagenInMaand > restDagen) {
					monthFound = true;
				} else {
					restDagen -= aantalDagenInMaand;
					month++;
				}
			}
		}

		return new Datum(restDagen, month, jaar);
	}

	private String getMaandWaarde() {
		String maandWaarde = "";

		switch (this.maand) {
		case 1:
			maandWaarde = "januari";
			break;
		case 2:
			maandWaarde = "februari";
			break;
		case 3:
			maandWaarde = "maart";
			break;
		case 4:
			maandWaarde = "april";
			break;
		case 5:
			maandWaarde = "mei";
			break;
		case 6:
			maandWaarde = "juni";
			break;
		case 7:
			maandWaarde = "juli";
			break;
		case 8:
			maandWaarde = "augustus";
			break;
		case 9:
			maandWaarde = "september";
			break;
		case 10:
			maandWaarde = "oktober";
			break;
		case 11:
			maandWaarde = "november";
			break;
		case 12:
			maandWaarde = "december";
			break;
		}

		return maandWaarde;
	}

	private int getDatumInDagen(Datum datum) {

		/*
		 * Door de minimumwaarde van datum (01/01/0001) gelijk te stellen aan 1, weten we dat telkens het 4e jaar een
		 * schrikkeljaar is (tenzij deelbaar door 100). Doordat de laatste restwaarde verkregen wordt door een modulus
		 * 4, betekent dit dus dat we uitsluitend met een restwaarde van 0 in een schrikkeljaar kunnen zitten.
		 */

		int aantalDagen = 0;

		int aantal400Jaar = datum.jaar / 400;
		int rest400 = datum.jaar % 400;
		int aantal100Jaar = rest400 / 100;
		int rest100 = rest400 % 100;
		int aantal4Jaar = rest100 / 4;
		int rest4 = rest100 % 4;

		int aantalDagen4Jaar = (3 * aantalDagenPerGewoonJaar) + aantalDagenPerSchrikkelJaar;

		aantalDagen = (aantal400Jaar * ((aantalDagen4Jaar * 100) - 3)) + (aantal100Jaar * ((aantalDagen4Jaar * 25) - 1))
				+ aantal4Jaar * aantalDagen4Jaar;

		switch (rest4) {
		case 0:
			// Met bovenstaande formule werd een schrikkeljaar teveel aangerekend. Het begonnen jaar mag namelijk (nog)
			// niet meetellen.
			aantalDagen -= aantalDagenPerSchrikkelJaar;
			break;
		case 2:
		case 3:
			// - 1 omdat het begonnen jaar geen volledige 365 dagen duurt.
			aantalDagen += (rest4 - 1) * aantalDagenPerGewoonJaar;
			break;
		}

		aantalDagen += getAantalDagenVoorDagEnMaand(datum, rest4 == 0);

		return aantalDagen;
	}

	private int getAantalDagenVoorDagEnMaand(Datum datum) {
		return getAantalDagenVoorDagEnMaand(datum, isSchrikkeljaar(datum.jaar));
	}

	private int getAantalDagenVoorDagEnMaand(Datum datum, Boolean schrikkelJaar) {
		int aantalDagen = 0;

		if (datum.maand > 1) {
			for (int i = 1; i < datum.maand; i++) {
				aantalDagen += getAantalDagenInMaand(i, schrikkelJaar);
			}
		}

		aantalDagen += datum.dag;

		return aantalDagen;
	}

	private Boolean isSchrikkeljaar(int jaar) {
		return ((jaar % 4 == 0 && jaar % 100 != 0) || jaar % 400 == 0);
	}

	private int getAantalDagenInMaand(int maand, Boolean schrikkelJaar) {
		int aantalDagen = 31;

		switch (maand) {
		case 4:
		case 6:
		case 9:
		case 11:
			aantalDagen = 30;
			break;
		case 2:
			aantalDagen = schrikkelJaar ? 29 : 28;
			break;
		}

		return aantalDagen;
	}
}
