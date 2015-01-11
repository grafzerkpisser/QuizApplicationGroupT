package persistentie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import model.Opdracht;
import model.OpdrachtCatalogus;
import model.QuizCatalogus;
import model.QuizOpdracht;
import model.Quiz;
import enumerations.QuizStatus;

public class DataHandler {
	private DbStrategy dbStrategy;
	private List<Opdracht> opdrachten = new ArrayList<Opdracht>();
	private List<Quiz> quizzen = new ArrayList<Quiz>();
	private List<QuizOpdrachtMapper> quizOpdrachtMappingList;
	private QuizCatalogus quizCatalogus;
	private OpdrachtCatalogus opdrachtCatalogus;

	public DataHandler() {
		this.dbStrategy = new CsvFileHandler();
	}

	public DataHandler(DbStrategy strategy) {
		this.dbStrategy = strategy;
	}

	public List<Opdracht> getOpdrachten() {
		return opdrachten;
	}

	public List<Quiz> getQuizzen() {
		return quizzen;
	}

	public QuizCatalogus getQuizCatalogus() {
		return quizCatalogus;
	}

	public OpdrachtCatalogus getOpdrachtCatalogus() {
		return opdrachtCatalogus;
	}

	public DbStrategy getDbStrategy() {
		return this.dbStrategy;
	}

	public void setDbStrategy(DbStrategy dbStrategy) {
		this.dbStrategy = dbStrategy;
	}

	public void bewaarCatalogi(OpdrachtCatalogus opdrachtCatalogus, QuizCatalogus quizCatalogus, List<QuizOpdracht> quizOpdrachten)
			throws IOException {
		for (Opdracht o : opdrachtCatalogus.getOpdrachtenLijst()) {
			opdrachten.add(o);
		}
		for (Quiz q : quizCatalogus.getQuizLijst()) {
			quizzen.add(q);
			// for(QuizOpdracht qo:q.getQuizOpdrachten())
			// {
			// quizOpdrachten.add(qo);
			// }
		}

		dbStrategy.bewaarOpdrachten(opdrachten);
		dbStrategy.bewaarQuizzen(quizzen);
		dbStrategy.bewaarQuizOpdrachten(quizOpdrachten);
	}

	public void vulCatalogi() throws Exception {
		opdrachten = dbStrategy.leesOpdrachten();
		quizzen = dbStrategy.leesQuizzen();
		quizOpdrachtMappingList = dbStrategy.leesQuizOpdrachten();
		mapper();
	}

	public void mapper() throws Exception {
		quizCatalogus = new QuizCatalogus();
		opdrachtCatalogus = new OpdrachtCatalogus();
		for (QuizOpdrachtMapper qom : quizOpdrachtMappingList) {
			Quiz q = null;
			if (quizCatalogus.getQuizLijst().stream().filter(o -> o.getQuizId().equals(qom.getQuizId())).findFirst().isPresent()) {
				q = quizCatalogus.getQuizLijst().stream().filter(o -> o.getQuizId().equals(qom.getQuizId()))
						.collect(singletonCollector());
				quizCatalogus.verwijderQuiz(q);
			} else {
				q = quizzen.stream().filter(o -> o.getQuizId().equals(qom.getQuizId())).collect(singletonCollector());
			}
			Opdracht o = opdrachten.stream().filter(a -> a.getOpdrachtId().equals(qom.getOpdrachtId()))
					.collect(singletonCollector());
			if (!opdrachtCatalogus.getOpdrachtenLijst().stream().filter(op -> op.getOpdrachtId().equals(o.getOpdrachtId()))
					.findFirst().isPresent()) {
				opdrachtCatalogus.voegOpdrachtToe(o);
			}

			QuizStatus quizStatus = q.getQuizStatus();
			q.setQuizStatus(QuizStatus.IN_CONSTRUCTIE);
			// QuizOpdracht.koppelOpdrachtAanQuiz(q, o, qom.maxScore);
			q.setQuizStatus(quizStatus);
			quizCatalogus.voegQuizToe(q);
		}

	}

	public void mapper2() throws Exception {
		quizCatalogus = new QuizCatalogus();
		opdrachtCatalogus = new OpdrachtCatalogus();

		for (Quiz q : quizCatalogus.getQuizLijst()) {

			if (quizCatalogus.getQuizLijst().stream().findFirst().isPresent()) {
				q = quizCatalogus.getQuizLijst().stream().collect(singletonCollector());

			} else {
				q = quizzen.stream().collect(singletonCollector());
			}
			Opdracht o = opdrachten.stream().collect(singletonCollector());
			if (!opdrachtCatalogus.getOpdrachtenLijst().stream().findFirst().isPresent()) {
				opdrachtCatalogus.voegOpdrachtToe(o);
			}

			QuizStatus quizStatus = q.getQuizStatus();
			q.setQuizStatus(QuizStatus.IN_CONSTRUCTIE);
			// QuizOpdracht.koppelOpdrachtAanQuiz(q, o, qom.maxScore);
			q.setQuizStatus(quizStatus);
			quizCatalogus.voegQuizToe(q);
		}

	}

	public static <T> Collector<T, ?, T> singletonCollector() {
		return Collectors.collectingAndThen(Collectors.toList(), list -> {
			if (list.size() != 1) {
				throw new IllegalStateException();
			}
			return list.get(0);
		});
	}

	public static class QuizOpdrachtMapper {
		private UUID opdrachtId;
		private UUID quizId;
		private Integer maxScore;

		protected QuizOpdrachtMapper(UUID opdrachtId, UUID quizId, Integer maxScore) {
			this.opdrachtId = opdrachtId;
			this.quizId = quizId;
			this.maxScore = maxScore;
		}

		public UUID getOpdrachtId() {
			return opdrachtId;
		}

		public UUID getQuizId() {
			return quizId;
		}

		public Integer getMaxScore() {
			return maxScore;
		}

	}
}
