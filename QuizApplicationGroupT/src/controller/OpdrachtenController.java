package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import persistentie.DataHandler;
import view.BeheerOpdrachten;
import view.QuizApplicationMain;
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
