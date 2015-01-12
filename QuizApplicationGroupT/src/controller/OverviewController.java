package controller;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.facades.OpdrachtFacade;
import model.facades.QuizFacade;
import persistentie.DataHandler;
import view.BeheerOpdrachten;
import view.BeheerQuizzen;
import view.QuizApplicationMain;
import view.BeheerInstellingen;
import model.facades.InstellingFacade;


public  class OverviewController {

	private QuizApplicationMain theView;
	private DataHandler theModel;
	public OverviewController(QuizApplicationMain theView, DataHandler theModel){
		this.theView = theView;
		this.theModel = theModel;
		this.theView.addAfsluitenListener(new AfsluitenListener());
		this.theView.addBeherenVanOpdrachtenListener(new BeherenOpdrachtenListener());
		this.theView.addBeherenVanQuizzenListener(new BeherenQuizListener());
		this.theView.addBeheerInstellingen(new BeheerInstellingenListner());
		theView.setTxtrText(theModel.getDbStrategy().toString());
		try {
			this.theModel.vulCatalogi();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			theView.displayErrorMessage(e.toString() + " VUl CATALOGI");
			
		}
		}
	

	class BeheerInstellingenListner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.dispose();
			BeheerInstellingen beheerInstelView = new BeheerInstellingen();
			OpdrachtFacade opdrachtModel = new OpdrachtFacade(theModel.getOpdrachtCatalogus(),theModel.getDbStrategy());
			@SuppressWarnings("unused")
			InstellingenController instellingController = new InstellingenController(opdrachtModel,beheerInstelView);
			beheerInstelView.setVisible(true);
								
		}
		
	}
	
	class BeherenOpdrachtenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.dispose();
			BeheerOpdrachten opdrachtView = new BeheerOpdrachten();
			OpdrachtFacade opdrachtModel = new OpdrachtFacade(theModel.getOpdrachtCatalogus(),theModel.getDbStrategy());
			@SuppressWarnings("unused")
			OpdrachtenController opdrachtController = new OpdrachtenController(opdrachtModel, opdrachtView);
			opdrachtView.setVisible(true);
		}
		
	}
	class BeherenQuizListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			theView.dispose();
			BeheerQuizzen quizView = new BeheerQuizzen();
			QuizFacade quizModel = new QuizFacade(theModel.getQuizCatalogus(), theModel.getOpdrachten(), theModel.getOpdrachtCatalogus(), theModel.getDbStrategy());
			@SuppressWarnings("unused")
			QuizzenController quizController = new QuizzenController(quizView, quizModel);
			
			quizView.setVisible(true);
			
		}
		
	}
	class AfsluitenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.dispose();
			
		}
		
		
	}
	
	
}
