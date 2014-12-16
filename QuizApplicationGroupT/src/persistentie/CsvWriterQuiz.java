package persistentie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Quiz;
import enumerations.*;

public class CsvWriterQuiz {
	public static void bewaar(List<Quiz>quizzen) throws IOException{
		FileWriter writer = new FileWriter("quizzen.ser");
		String delimiter = "#";
		for(Quiz quiz:quizzen)
		{
			writer.append(quiz.getQuizId().toString());
			writer.append(delimiter);
			writer.append(quiz.getOnderwerp().toString());
			writer.append(delimiter);
			writer.append(quiz.getIsTest().toString());
			writer.append(delimiter);
			writer.append(quiz.getIsUniekeDeelname().toString());
			writer.append(delimiter);
			writer.append(quiz.getLeerjaar().toString());
			writer.append(delimiter);
			writer.append(quiz.getQuizStatus().toString());
			writer.append(delimiter);
			writer.append(quiz.getLeraar().toString());
			writer.append("\n");
		}
		
		writer.flush();
	    writer.close();
	}
	public static List<Quiz> lees() throws IOException{
		BufferedReader br = null;
		String file = "quizzen.ser";
		String line = "";
		String delimiter = "#";
		
		List<Quiz> quizzenLijst = new ArrayList<Quiz>();
		br = new BufferedReader(new FileReader(file));
		while((line=br.readLine())!=null){
			String[] q = line.split(delimiter);
			UUID quizId = UUID.fromString(q[0]);
			String onderwerp = q[1];
			Boolean isTest = Boolean.valueOf(q[2]);
			Boolean isUniekeDeelname = Boolean.valueOf(q[3]);
			Leerjaar leerjaar =  Leerjaar.valueOf(q[4]);
			QuizStatus quizStatus = QuizStatus.valueOf(q[5]);
			Leraar leraar = Leraar.valueOf(q[6]);
			Quiz quiz = new Quiz(onderwerp, leerjaar, isTest, isUniekeDeelname, leraar);
			quiz.setQuizId(quizId);
			quiz.setQuizStatus(quizStatus);
			quizzenLijst.add(quiz);
		}
		br.close();
		return quizzenLijst;
	}
}
