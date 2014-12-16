package persistentie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.QuizOpdracht;
import persistentie.DataHandler;
import persistentie.DataHandler.QuizOpdrachtMapper;

public class CsvWriterQuizOpdracht {
	public static void bewaar(List<QuizOpdracht>quizOpdrachten) throws IOException{
		FileWriter writer = new FileWriter("quizOpdrachten.ser");
		String delimiter = "#";
		for(QuizOpdracht quizOpdracht:quizOpdrachten)
		{
			writer.append(quizOpdracht.getOpdracht().getOpdrachtId().toString());
			writer.append(delimiter);
			writer.append(quizOpdracht.getQuiz().getQuizId().toString());
			writer.append(delimiter);
			writer.append(quizOpdracht.getMaxScore().toString());
			writer.append(delimiter);
			writer.append("\n");
		}
		
		writer.flush();
	    writer.close();
	}
	public static List<QuizOpdrachtMapper> lees() throws IOException{
		BufferedReader br = null;
		String file = "quizOpdrachten.ser";
		String line = "";
		String delimiter = "#";
		
		List<QuizOpdrachtMapper> quizOpdrachtLijst = new ArrayList<QuizOpdrachtMapper>();
		br = new BufferedReader(new FileReader(file));
		while((line=br.readLine())!=null){
			String[] qo = line.split(delimiter);
			UUID opdrachtId = UUID.fromString(qo[0]);
			UUID quizId = UUID.fromString(qo[1]);
			Integer maxScore = Integer.parseInt(qo[2]);
			QuizOpdrachtMapper quizOpdracht = new DataHandler.QuizOpdrachtMapper(opdrachtId, quizId, maxScore);
			quizOpdrachtLijst.add(quizOpdracht);
		}
		br.close();
		return quizOpdrachtLijst;
	}
}
