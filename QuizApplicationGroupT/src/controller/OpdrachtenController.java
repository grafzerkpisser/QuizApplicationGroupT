package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableColumn;

import persistentie.DataHandler;
import view.BeheerOpdrachten;
import view.QuizApplicationMain;
import model.Opdracht;
import model.facades.OpdrachtFacade;
import model.tableModels.OpdrachtTableModel;

public class OpdrachtenController {

	private OpdrachtFacade theModel;
	private BeheerOpdrachten theView;
	
	public OpdrachtenController(OpdrachtFacade theModel, BeheerOpdrachten theView){
		this.theView = theView;
		this.theModel = theModel;
		this.theView.addTerugToMainMenuActionListener(new TerugListener());
		this.theView.addToevoegenOpdrachtActionListener(new ToevoegenListener());
		this.theView.addTerugNaarOverviewActionListener(new TerugNaarOverviewListener());
		this.theView.addWijzigenOpdrachtActionListener(new WijzigenListener());
		this.theView.addRadioButtonMeerkeuzeItemListener(new OpdrachtTypeMeerkeuzeListener());
		this.theView.addRadioButtonOpsommingItemListener(new OpdrachtTypeOpsommingListener());
		this.theView.addRadioButtonOpdrachtItemListener(new OpdrachtTypeDefaultListener());
		this.theView.addBewaarOpdrachtActionListener(new OpdrachtBewaarListener());
		OpdrachtTableModel opdrachtTableModel = theModel.getOpdrachtTableModel();
		theView.setOpdrachtTableModel(opdrachtTableModel);
		
	}
	
	
	
	class TerugListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.sluitFrame();
			QuizApplicationMain theViewMain = new QuizApplicationMain();
			DataHandler theModelMain = new DataHandler(theModel.getDbStrategy());
			@SuppressWarnings("unused")
			OverviewController theController = new OverviewController(theViewMain, theModelMain);
			theViewMain.setVisible(true);			
		}
		
	}
	class ToevoegenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.activeerDetailPanel();
			theView.setRadioButtonDefault();
			
		}
		
	}
	class OpdrachtBewaarListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			
		}
	}
	
	class TerugNaarOverviewListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			theView.activeerOverview();
			
		}
		
	}
	class WijzigenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.activeerDetailPanel();
			Opdracht a = theModel.getOpdrachtTableModel().getOpdrachtAt(theView.tblOpdrachtOverview.getSelectedRow());
			theView.setVraag(a.getVraag());
			theView.setAntwoord(a.getJuisteAntwoord());
			theView.setMaxTijd(a.getMaxAntwoordTijd());		
			theView.setLeraar(a.getLeeraar());
		}
		
	}
	class OpdrachtTypeDefaultListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				theView.activeerDefaultPanel();
			}
			
		}
		
	}
	class OpdrachtTypeMeerkeuzeListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				theView.activeerMeerkeuzePanel();
			}
			
		}
		
	}
	class OpdrachtTypeOpsommingListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED){
				theView.activeerOpsommingPanel();
			}
			
		}
		
	}
}
