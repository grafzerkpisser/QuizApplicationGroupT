package model.facades;

import java.util.List;

import persistentie.DbStrategy;
import model.Opdracht;
import model.OpdrachtCatalogus;
import model.tableModels.OpdrachtTableModel;

public class OpdrachtFacade {

	private OpdrachtCatalogus opdrachtCatalogus;
	private DbStrategy dbStrategy;
	private OpdrachtTableModel opdrachtTableModel;
	
	public OpdrachtFacade(OpdrachtCatalogus opdrachtCatalogus, DbStrategy dbStrategy){
		this.opdrachtCatalogus = opdrachtCatalogus;
		this.dbStrategy = dbStrategy;
	}
	
	
	
	
	//Getters
	public DbStrategy getDbStrategy(){
		return this.dbStrategy;
	}
	public List<Opdracht> getOpdrachtenLijst(){
		return opdrachtCatalogus.getOpdrachtenLijst();
	}
	public OpdrachtTableModel getOpdrachtTableModel() {
		opdrachtTableModel = new OpdrachtTableModel(opdrachtCatalogus.getOpdrachtenLijst());
		return opdrachtTableModel;
	}
	
	
}
