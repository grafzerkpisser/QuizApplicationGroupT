package utils.textLezer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import utils.datum.Datum;

public class TextLezer {
	public String output;
	public Datum datum;

	public TextLezer() {
		output = "";
	}

	public void leesNaamDatumVanBestand() {
		Datum jongste = null;
		String naamJongste="";
		Datum oudste = null;
		String naamOudste="";
		List<String> FoutieveData = new ArrayList<String>();
		output = "";
		File file = new File("src/utils/textLezer/File.txt");
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String lijn = scanner.nextLine();
				if (lijn.contains("/")) {
					String[] velden = lijn.split("\t");
					String naam = velden[0];
					String[] datumVeld = velden[1].split("/");
					try {
						Datum d = new Datum(Integer.parseInt(datumVeld[0]),
								Integer.parseInt(datumVeld[1]),
								Integer.parseInt(datumVeld[2]));
						if(jongste == null || jongste.kleinerDan(d) )
						{
							naamJongste = naam;
							jongste = d;
						}
						if(oudste == null || oudste.kleinerDan(d) == false){
							naamOudste = naam;
							oudste = d;
						}
					} catch (IllegalArgumentException ex) {
						FoutieveData.add(velden[1].toString());
					}
				}

				if (lijn.contains("-")) {
					String[] velden = lijn.split("\t");
					String naam = velden[0];
					String[] datumVeld = velden[1].split("-");
					try {
						Datum da = new Datum(Integer.parseInt(datumVeld[0]),
								Integer.parseInt(datumVeld[1]),
								Integer.parseInt(datumVeld[2]));
						if(jongste == null || jongste.kleinerDan(da) )
						{
							naamJongste = naam;
							jongste = da;
						}
						if(oudste == null || oudste.kleinerDan(da) == false){
							naamOudste = naam;
							oudste = da;
						}
					} catch (IllegalArgumentException ex) {
						FoutieveData.add(velden[1].toString());
					}
				}
			}
			if (scanner != null)
				scanner.close();
			output += String.format("%s %n","De Jongste Persoon:");
			output += String.format("%s %s %n", naamJongste, jongste
					.getDatumInEuropeesFormaat().toString());
			output += String.format("%s %n","De Ouderste Persoon:");
			output += String.format("%s %s %n", naamOudste, oudste
					.getDatumInEuropeesFormaat().toString());
			output += String.format("%s %n","Verschil in dagen:");
			output += String.format("%s %n",jongste.verschilInDagen(oudste));
			output += String.format("%s %n","Foutieve Data:");
			for(String s:FoutieveData){
				output += String.format("%s %n",s);
			}
		} catch (FileNotFoundException ex) {
			System.out.println("bestand niet gevonden");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void leesNaamDatumVanBestandBijlage(File file) {
		output = "";
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String lijn = scanner.nextLine();
				if (lijn.contains("/")) {
					String[] velden = lijn.split("\t");
					String naam = velden[0];
					String[] datumVeld = velden[1].split("/");
					try {
						Datum d = new Datum(Integer.parseInt(datumVeld[0]),
								Integer.parseInt(datumVeld[1]),
								Integer.parseInt(datumVeld[2]));

						datum = d;
						output += String.format("%s %s %n", naam, datum
								.getDatumInEuropeesFormaat().toString());
					} catch (IllegalArgumentException ex) {
						output += " Foutieve datum!! \n";
					}

				}
				if (lijn.contains("-")) {
					String[] velden = lijn.split("\t");
					String naam = velden[0];
					String[] datumVeld = velden[1].split("-");
					try {
						Datum da = new Datum(Integer.parseInt(datumVeld[0]),
								Integer.parseInt(datumVeld[1]),
								Integer.parseInt(datumVeld[2]));
						datum = da;
						output += String.format("%s %s %n", naam, datum
								.getDatumInEuropeesFormaat().toString());
					} catch (IllegalArgumentException ex) {
						output += " Foutieve datum!! \n";

					}
				}
			}

			if (scanner != null)
				scanner.close();
		} catch (FileNotFoundException ex) {
			System.out.println("bestand niet gevonden");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void main(String[] args) {
		try {
			TextLezer textL = new TextLezer();
			textL.leesNaamDatumVanBestand();
			System.out.println(textL.output);
		} catch (Exception ex) {
			System.out.println("Foutmelding: " + ex.getMessage());
		}

	}
}
