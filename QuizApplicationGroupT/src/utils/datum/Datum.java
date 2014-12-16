package utils.datum;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class Datum implements Comparable<Datum>, Serializable {

	//Properties
	private GregorianCalendar gregDatum;
	//Getters & Setters
	public GregorianCalendar getDatum() {
		return gregDatum;
	}
	public String getDatumInEuropeesFormaat() {
		SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
		return sdf.format(gregDatum.getTime());
	}
	public String getDatumInAmerikaansFormaat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
		return sdf.format(gregDatum.getTime());
	}
	public boolean setDatum(int jaar, int maand, int dag) {
		try {
			this.setDatum(new GregorianCalendar(jaar, maand, dag));
		} catch (IllegalArgumentException ex) {
			return false;
		}
		return true;
	}
	
	//Constructors
	public Datum() {
		gregDatum = (GregorianCalendar) GregorianCalendar.getInstance();
	}

	public Datum(int dag, int maand, int jaar) throws IllegalArgumentException {
		try {
			Calendar cal = GregorianCalendar.getInstance();
			cal.setLenient(false);
			cal.set(jaar, maand - 1, dag);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			//With setLenient the calendar will only accept real dates.
			//example: 32/02/2014 will not be accepted. 
			// The setLenient function will only be checked by get methods.
			cal.getTime();
			gregDatum = (GregorianCalendar)cal;
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Vul een correcte jaar, maand en dag in.");
		}
	}

	public Datum(String d) throws IllegalArgumentException {
		try {
			String[] parts = d.split("/");
			if(parts.length != 3)
				throw new IllegalArgumentException("Vul een correcte jaar, maand en dag in, in het volgende formaat: dd/mm/yyyy");
			Calendar cal = GregorianCalendar.getInstance();
			cal.setLenient(false);
			cal.set(Integer.parseInt(parts[2]), Integer.parseInt(parts[1]) - 1,
					Integer.parseInt(parts[0]));
			cal.set(Calendar.HOUR_OF_DAY, 0);
			
			cal.getTime();
			gregDatum = (GregorianCalendar) cal;
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Vul een correcte jaar, maand en dag in, in het volgende formaat: dd/mm/yyyy");
		}
	}

	public Datum(Datum d) {
		this.setDatum(d.getDatum());
	}

	//Methods


	private LocalDate getLocalDate(Datum d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d.getDatum().getTime());
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return LocalDate.of(year, month + 1, day);
	}

	public int verschilInJaren(Datum d) {
		int result = Period.between(getLocalDate(d), getLocalDate(this)).getYears();
		return result > 0 ? result : result * -1;
	}

	public int verschilInMaanden(Datum d) {
		int result = Period.between(getLocalDate(d), getLocalDate(this)).getMonths() + (Period.between(getLocalDate(d), getLocalDate(this)).getYears() *12) ;
		return result > 0 ? result : result * -1;
	}

	public int verschilInDagen(Datum d) {

		Date d1 = this.gregDatum.getTime();
		Date d2 = d.getDatum().getTime();
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	public void veranderDatum(int daysToAdd) {
		this.gregDatum.add(GregorianCalendar.DAY_OF_MONTH, daysToAdd);
	}

	public Datum veranderDatumInNieuwObject(int daysToAdd) {
		Datum d1 = this;
		d1.gregDatum.add(GregorianCalendar.DAY_OF_MONTH, daysToAdd);
		return d1;
	}
	public boolean kleinerDan(Datum d) {
		return (this.compareTo(d) == -1) ? true : false;
	}

	private void setDatum(GregorianCalendar d) {
		gregDatum = d;
	}
	
	//Override Methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((gregDatum == null) ? 0 : gregDatum.hashCode());
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
		if (gregDatum == null) {
			if (other.gregDatum != null)
				return false;
		} else if (!gregDatum.equals(other.gregDatum))
			return false;
		return true;
	}
	@Override
	public int compareTo(Datum d) {
		return d.getDatum().compareTo(this.gregDatum);
	}
	@Override
	public String toString() {
		return String.format("%s", this.gregDatum.getTime());
	}
}

