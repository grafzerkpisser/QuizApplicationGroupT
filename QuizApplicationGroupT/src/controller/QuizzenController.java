package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import persistentie.DataHandler;
import model.facades.QuizFacade;
import model.tableModels.OpdrachtTableModel;
import model.tableModels.QuizTableModel;
import view.BeheerQuizzen;
import view.QuizApplicationMain;

public class QuizzenController {
	private BeheerQuizzen theView;
	private QuizFacade theModel;
	
	public QuizzenController(BeheerQuizzen theView, QuizFacade theModel){
		this.theView = theView;
		this.theModel = theModel;
		QuizTableModel quizTableModel = theModel.getQuizTableModel();
		this.theView.setQuizOverviewTable(quizTableModel);
		
		this.theView.addTerugActionListener(new TerugListener());
		this.theView.addToevoegenQuizActionListener(new ToevoegenListener());
		this.theView.addWijzigenQuizActionListener(new WijzigenListener());
		this.theView.addVerwijderenQuizActionListener(new VerwijderenListener());
		this.theView.addTerugNaarQuizOverviewActionListener(new TerugNaarOverviewListener());
		this.theView.addVoegOpdrachtToActionListener(new ToevoegenOpdrachtAanQuizListener());
		this.theView.addVerwijderOpdrachtActionListener(new VerwijderenOpdrachtVanQuizListener());
		
	}
	
	//Overview Listeners
	class VerwijderenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(theView.showUserMessageYesNo("Wilt u deze quiz verwijderen?", "Verwijderen") == JOptionPane.YES_OPTION){
			try {
				theModel.verwijderQuizVanCatalogus(theView.getSelectedRowFromTable());
			} catch (Exception e1) {
				theView.displayErrorMessage(e1.toString());
			}
			}
		}
		
	}	
	class WijzigenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				
			
				theModel.resetQuiz();
				theView.addRegistreerQuizActionListener(new UpdateQuizListener());
				
				theModel.setQuiz(theView.getSelectedRowFromTable());
				theView.setOnderwerp(theModel.getQuizOnderwerp());
				theView.setStatus(theModel.getQuizStatus());
				theView.setCmbLeerjaar(theModel.getLeerjaar());
				theView.setCmbLeraar(theModel.getLeraar());
				theView.setIsTest(theModel.getIsTest());
				theView.setIsUniekeDeelname(theModel.getUniekeDeelname());
				OpdrachtTableModel opdrachtTableModel = theModel.getDummyOpdrachtTableModel();
				OpdrachtTableModel opdrachtTableModelQuiz = theModel.getOpdrachtToAddTableModel();
				theView.setOpdrachtTable(opdrachtTableModel);
				theView.setOpdrachtAddedTable(opdrachtTableModelQuiz);
				theView.activeerDetailPanel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				theView.displayErrorMessage(e1.toString());
			}

			
		}
		
	}	
	class ToevoegenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.activeerDetailPanel();
			theModel.resetQuiz();
			theView.resetScreen();
			theView.addRegistreerQuizActionListener(new RegistreerQuizListener());
			OpdrachtTableModel opdrachtTableModel;
			try {
				opdrachtTableModel = theModel.getDummyOpdrachtTableModel();
				OpdrachtTableModel opdrachtTableModelQuiz = theModel.getOpdrachtToAddTableModel();
				theView.setOpdrachtTable(opdrachtTableModel);
				theView.setOpdrachtAddedTable(opdrachtTableModelQuiz);
			} catch (Exception e1) {
				theView.displayErrorMessage(e1.toString());
			}	
		}
		
	}	
	class TerugListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			theView.sluitFrame();
			
			QuizApplicationMain theViewMain = new QuizApplicationMain();
			DataHandler theModelMain = new DataHandler(theModel.getDbStrategy());
			@SuppressWarnings("unused")
			OverviewController theController = new OverviewController(theViewMain, theModelMain);
			theViewMain.setVisible(true);		
		}
		
	}
	
	//Detail Listeners
	class TerugNaarOverviewListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			theView.removeActionListenerFromRegistreer();
			theView.activeerOverview();
			
		}
		
	}
	class RegistreerQuizListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			/*
			theModel.setQuiz(theView.getOnderwerp(),
					theView.getLeerjaar(),theView.getIsTest(), theView.getIsUniekeDeelname(), theView.getLeraar());
			try {
				for(int i = 0; i < theView.getRowCountQuizOpdrachtTable() ; i=i+1){
				theModel.linkOpdrachtToQuiz(i);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				theView.displayErrorMessage(e1.toString());
			}
			try {
				theModel.linkQuizToQuizCatalogus();
			} catch (Exception e1) {
				theView.displayErrorMessage(e1.toString());
			}
			try {
				theModel.save();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				theView.displayErrorMessage(e1.toString());
			}
			*/
		}
		
	}
	class UpdateQuizListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theModel.setQuizOnderwerp(theView.getOnderwerp());
			theModel.setQuizLeerjaar(theView.getLeerjaar());
			theModel.setQuizLeraar(theView.getLeraar());
			theModel.setQuizStatus(theView.getQuizStatus());
			theModel.setUniekeDeelname(theView.getIsUniekeDeelname());
			theModel.setIsTest(theView.getIsTest());
			try {
				theModel.updateQuiz();
			} catch (Exception e1) {
				theView.displayErrorMessage(e1.toString());
			}
			try {
				theModel.save();
			} catch (IOException e1) {
				theView.displayErrorMessage(e1.toString());

			}
			
		}
		
	}
	class ToevoegenOpdrachtAanQuizListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		theModel.addOpdrachtToQuiz(theView.getSelectedRowFromOpdrachtTable());
		}
		
	}
	class VerwijderenOpdrachtVanQuizListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theModel.removeOpdrachtFromQuiz(theView.getSelectedRowFromQuizOpdrachtTable());
		}
		
	}
	
}
