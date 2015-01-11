package model.facades;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import enumerations.Leerjaar;
import enumerations.Leraar;
import enumerations.QuizStatus;
import persistentie.DbStrategy;
import model.Opdracht;
import model.OpdrachtCatalogus;
import model.Quiz;
import model.QuizCatalogus;
import model.QuizOpdracht;
import model.tableModels.OpdrachtTableModel;
import model.tableModels.QuizTableModel;

public class QuizFacade {

	private QuizCatalogus quizCatalogus;
	private Quiz quiz;
	private List<Opdracht> opdrachten;
	private List<Opdracht> dummyOpdrachten;
	private List<Opdracht> opdrachtenToAdd;
	private DbStrategy dbStrategy;
	private QuizTableModel quizTableModel;
	private OpdrachtTableModel dummyOpdrachtTableModel;
	private OpdrachtTableModel opdrachtToAddTableModel;
	private OpdrachtCatalogus opdrachtCatalogus;
	private List<QuizOpdracht> quizOpdrachten;
	
	public QuizFacade(QuizCatalogus quizCatalogus, List<Opdracht> opdrachten,
			OpdrachtCatalogus opdrachtCatalogus, DbStrategy dbStrategy) {
		this.quizCatalogus = quizCatalogus;
		this.opdrachten = opdrachten;
		this.dbStrategy = dbStrategy;
		this.opdrachtCatalogus = opdrachtCatalogus;
	}

	// Setters
	public void setQuizCatalogus(QuizCatalogus quizCatalougs) {
		this.quizCatalogus = quizCatalougs;
	}
	public void setOpdrachten(List<Opdracht> opdrachten) {
		this.opdrachten = opdrachten;
	}
	public void setDbStrategy(DbStrategy strategy) {
		this.dbStrategy = strategy;
	}
	public void setQuiz(int row) throws Exception {
		if(row == -1)
		{
			throw new Exception("Geen quiz geselecteerd");
		}
		this.quiz = quizTableModel.getQuizAt(row);
	}
	public void setQuiz(UUID id, String onderwerp, Leerjaar leerjaar, Boolean isTest, Boolean isUniekeDeelname, Leraar leraar){
		quiz = new Quiz(id, onderwerp, leerjaar, isTest, isUniekeDeelname, leraar);
	}
	public void setQuizOnderwerp(String onderwerp) {
		quiz.setOnderwerp(onderwerp);
	}
	public void setQuizLeraar(Leraar leraar) {
		quiz.setLeraar(leraar);
	}
	public void setQuizLeerjaar(Leerjaar leerjaar) {
		quiz.setLeerjaar(leerjaar);
	}
	public void setQuizStatus(QuizStatus quizStatus) {
		quiz.setQuizStatus(quizStatus);
	}
	public void setIsTest(Boolean isTest){
		quiz.setIsTest(isTest);
	}
	public void setUniekeDeelname(Boolean isUniekeDeelname){
		quiz.setIsUniekeDeelname(isUniekeDeelname);
	}
	
	// Getters
	public List<Quiz> getQuizLijst() {
		return quizCatalogus.getQuizLijst();
	}
	public List<Opdracht> getOpdrachtLijst() {
		return opdrachten;
	}
	public DbStrategy getDbStrategy() {
		return this.dbStrategy;
	}
	public QuizTableModel getQuizTableModel() {
		quizTableModel = new QuizTableModel(quizCatalogus);
		return quizTableModel;
	}
	public OpdrachtTableModel getDummyOpdrachtTableModel() throws Exception{
		dummyOpdrachten = new ArrayList<Opdracht>();
		for(Opdracht o: opdrachten){
			dummyOpdrachten.add(new Opdracht(o));
		}
		initializeTables();
		dummyOpdrachtTableModel = new OpdrachtTableModel(dummyOpdrachten);
	
		return dummyOpdrachtTableModel;
	}
	public OpdrachtTableModel getOpdrachtToAddTableModel(){
		
		opdrachtToAddTableModel = new OpdrachtTableModel(opdrachtenToAdd);
		
		return opdrachtToAddTableModel;
	}
	public String getQuizOnderwerp() {
		return quiz.getOnderwerp();
	}
	public Leraar getLeraar() {
		return quiz.getLeraar();
	}
	public Leerjaar getLeerjaar() {
		return quiz.getLeerjaar();
	}
	public QuizStatus getQuizStatus() {
		return quiz.getQuizStatus();
	}
	public Boolean getIsTest(){
		return quiz.getIsTest();
	}
	public Boolean getUniekeDeelname(){
		return quiz.getIsUniekeDeelname();
	}
	
	// Methods
 	public void updateQuiz() throws Exception {
		quizCatalogus.UpdateQuiz(quiz);
	}
	public void resetQuiz(){
		quiz = null;
		dummyOpdrachten = null;
		opdrachtenToAdd = null;
	}
	private void initializeTables() throws Exception {
		if(opdrachtenToAdd == null){
			opdrachtenToAdd = new ArrayList<Opdracht>();
		}
		if (quiz != null) {
			for (Opdracht o : dummyOpdrachten) {
				for (QuizOpdracht qo : quiz.getQuizOpdrachten()) {
					if (qo.getOpdracht().getOpdrachtId()
							.equals(o.getOpdrachtId())) {
						Boolean r = dummyOpdrachten.remove(o);
						if(r == false){
							throw new Exception("Fout in het opladen van het scherm");
						}
						opdrachtenToAdd.add(o);
					}
				}
			}
		}
		
	}
	public void addOpdrachtToQuiz(int row){
	Opdracht o = dummyOpdrachtTableModel.getOpdrachtAt(row);
	dummyOpdrachtTableModel.removeRow(row);
	opdrachtToAddTableModel.addOpdracht(o);
	}
	public void removeOpdrachtFromQuiz(int row){
		Opdracht o = opdrachtToAddTableModel.getOpdrachtAt(row);
		opdrachtToAddTableModel.removeRow(row);
		dummyOpdrachtTableModel.addOpdracht(o);
	}
	public void linkOpdrachtToQuiz(int row) throws Exception{
		Opdracht o = opdrachtToAddTableModel.getOpdrachtAt(row);
		QuizOpdracht qo = new QuizOpdracht(quiz, o, 0);
		quiz.voegQuizOpdrachtToe(qo);
		for(Opdracht opdracht : opdrachtCatalogus.getOpdrachtenLijst()){
			if(opdracht.getOpdrachtId().equals(o.getOpdrachtId())){
				opdracht.voegQuizOpdrachtToe(qo);
			}
		}
	}
	public void linkQuizToQuizCatalogus() throws Exception{
		quizTableModel.addQuiz(quiz);
	}
	public void save() throws IOException{
		dbStrategy.bewaarOpdrachten(opdrachtCatalogus.getOpdrachtenLijst());
		dbStrategy.bewaarQuizzen(quizCatalogus.getQuizLijst());
		fillQuizOpdrachten();
		dbStrategy.bewaarQuizOpdrachten(quizOpdrachten);
	}
	private void fillQuizOpdrachten(){
		quizOpdrachten = new ArrayList<QuizOpdracht>();
		for(Quiz q : quizCatalogus.getQuizLijst()){
			quizOpdrachten.addAll(q.getQuizOpdrachten());
		}
	}
	public void verwijderQuizVanCatalogus(int row) throws Exception{
		if(row == -1){
			throw new Exception("Geen quiz geselecteerd");
		}
		Quiz q = quizTableModel.getQuizAt(row);
		quizTableModel.removeRow(row);
		quizCatalogus.verwijderQuiz(q);
		save();
	}
	
	
}
