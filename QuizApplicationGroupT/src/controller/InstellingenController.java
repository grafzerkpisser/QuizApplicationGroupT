package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.facades.OpdrachtFacade;
import view.BeheerInstellingen;
import view.QuizApplicationMain;
import persistentie.*;

public class InstellingenController {

	@SuppressWarnings("unused")
	private OpdrachtFacade theModel;
	private BeheerInstellingen theView;
	private QuizApplicationMain theView2;
	private DataHandler theModel2;
	private DbStrategy dbStr;

	@SuppressWarnings("unchecked")
	public InstellingenController(OpdrachtFacade theModel, BeheerInstellingen theView) {
		this.theView = theView;
		this.theModel = theModel;
		theModel2 = new DataHandler();
		this.theView.changeStrategyKeuzeActionListner(new OkKnopListener());
		theView.comboBox.addItem("File Schrijver/Lezer");
		theView.comboBox.addItem("Database");

	}

	class OkKnopListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (theView.getComboBox().getSelectedIndex() == 1) {
				dbStr = new DerbyDatabaseHandler();
				theModel2 = new DataHandler(dbStr);
				
			} else {
				dbStr = new CsvFileHandler();
				theModel2.setDbStrategy(dbStr);
			}
			theView2 = new QuizApplicationMain();
			@SuppressWarnings("unused")
			OverviewController theController = new OverviewController(theView2, theModel2);
			theView.dispose();
			theView2.setVisible(true);

		}
	}
}
