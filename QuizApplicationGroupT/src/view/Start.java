package view;

import controller.OverviewController;
import persistentie.CsvFileHandler;
import persistentie.DataHandler;
import persistentie.DbStrategy;
import persistentie.DerbyDatabaseHandler;

public class Start {

	public static void main(String[] args) {
		QuizApplicationMain theView = new QuizApplicationMain();
		DbStrategy dbStrategy = new CsvFileHandler();
		DataHandler theModel = new DataHandler(dbStrategy);
		@SuppressWarnings("unused")
		OverviewController theController = new OverviewController(theView, theModel);
		theView.setVisible(true);

	}

}
