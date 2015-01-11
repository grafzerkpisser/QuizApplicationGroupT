package persistentie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import model.OpdrachtMeerkeuze;
import model.Opdracht;
import model.OpdrachtOpsomming;
import utils.datum.Datum;
import enumerations.Leraar;
import enumerations.OpdrachtCategorie;

public class CsvWriterOpdracht {
	public static void bewaar(List<Opdracht>opdrachten) throws IOException{
		FileWriter writer = new FileWriter("opdrachten.ser");
		String delimiter = "#";
		for (Opdracht opdracht : opdrachten) {
			writer.append(opdracht.getClass().toString());
			writer.append(delimiter);
			writer.append(opdracht.getOpdrachtId().toString());
			writer.append(delimiter);
			writer.append(opdracht.getVraag());
			writer.append(delimiter);
			writer.append(opdracht.getJuisteAntwoord());
			writer.append(delimiter);
			writer.append(opdracht.getAanmaakDatum().getDatumInEuropeesFormaat());
			writer.append(delimiter);
			writer.append(opdracht.getJuisteAntwoord());
			writer.append(delimiter);
			for(String hint:opdracht.getAntwoordHints())
			{
				writer.append(hint);
				writer.append("/");
			}
			writer.append(delimiter);
			writer.append(opdracht.getLeeraar().toString());
			writer.append(delimiter);
			writer.append(opdracht.getMaxaantaPogingen().toString());
			writer.append(delimiter);
			writer.append(opdracht.getMaxAntwoordTijd().toString());
			writer.append(delimiter);
			writer.append(opdracht.getOpdrachtCategorie().toString());
			writer.append(delimiter);
			if(opdracht instanceof OpdrachtMeerkeuze){
				for(String keuze : ((OpdrachtMeerkeuze)opdracht).getKeuzes()){
				writer.append(keuze);
				writer.append("/");
				}
				writer.append(delimiter);
			}
			if(opdracht instanceof OpdrachtOpsomming){
				writer.append(((OpdrachtOpsomming)opdracht).getInJuisteVolgorde().toString());
				writer.append(delimiter);
				writer.append(((OpdrachtOpsomming)opdracht).getValideerTekst());
				writer.append(delimiter);
			}
			writer.append("\n");
			
		}
		writer.flush();
	    writer.close();
	}
	public static List<Opdracht> Lees() throws NumberFormatException, IllegalArgumentException, IOException{
		BufferedReader br = null;
		String file = "opdrachten.ser";
		String line = "";
		String delimiter = "#";
		
		List<Opdracht> opdrachtenLijst = new ArrayList<Opdracht>();
		br = new BufferedReader(new FileReader(file));
		while((line=br.readLine())!=null){
			String[] c = line.split(delimiter);
			Opdracht opdracht = null;
			UUID id = UUID.fromString(c[1]);
			String vraag = c[2];
			String juisteAntwoord =c [3];
			Integer maxAantalPogingen= Integer.parseInt(c[8]);
			ArrayList<String> antwoordHints = new ArrayList<String>(Arrays.asList(c[6].split("/")));
			
			
			Integer maxAntwoordTijd=Integer.parseInt(c[9]);
			Leraar leeraar = Leraar.valueOf(c[7]);
			OpdrachtCategorie opdrachtCategorie = OpdrachtCategorie.valueOf(c[10]);
			
			switch (c[0]) {
			case "class model.OpdrachtMeerkeuze":
				ArrayList<String> keuzes = new ArrayList<String>(Arrays.asList(c[11].split("/")));
				opdracht = new OpdrachtMeerkeuze(id, vraag, juisteAntwoord, maxAantalPogingen, 
						antwoordHints, maxAntwoordTijd, keuzes, leeraar, opdrachtCategorie);
				break;
			case "class model.OpdrachtOpsomming":
				
				Boolean inJuisteVolgorde = Boolean.valueOf(c[11]);
				opdracht = new OpdrachtOpsomming(id, vraag, juisteAntwoord, maxAantalPogingen,
						antwoordHints, maxAntwoordTijd, inJuisteVolgorde, leeraar, opdrachtCategorie);
				break;
			case "class model.Opdracht":
				opdracht = new Opdracht(id, vraag, juisteAntwoord, maxAantalPogingen, 
						antwoordHints, maxAntwoordTijd, leeraar, opdrachtCategorie);
				break;
			default:
				break;
			}
			opdracht.setAanmaakDatum(new Datum(c[4]));
			opdracht.setOpdrachtId(UUID.fromString(c[1]));
			opdrachtenLijst.add(opdracht);
		}
		br.close();
		return opdrachtenLijst;
	}
}
