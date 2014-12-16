package model.tableModels;

import javax.swing.table.AbstractTableModel;

import model.QuizCatalogus;
import model.Quiz;

public class QuizTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QuizCatalogus quizCatalogus;
	private String[]columnNames = {"Onderwerp","Leerjaar","Status"};
	
	public QuizTableModel(QuizCatalogus quizCatalogus) {
		this.quizCatalogus = quizCatalogus;
	}
	 public Quiz getQuizAt(int row){
	    	return quizCatalogus.get(row);
	    }
	public int getColumnCount() {
		return 3;
	}
	public int getRowCount() {
		return quizCatalogus.GetAantalQuizzes();
	}
	public String getColumnName(int col) {
	    	return columnNames[col];
	       
	    }
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public Class getColumnClass(int c) {
	        return getValueAt(0, c).getClass();
	    }

	public Object getValueAt(int row, int col) {
		return quizCatalogus.getWaarde(row, col);
	}

	public void addQuiz(Quiz quiz) throws Exception {
		quizCatalogus.voegQuizToe(quiz);
		int lastRow = quizCatalogus.getQuizLijst().size() - 1;
		fireTableRowsInserted(lastRow, lastRow);
	}

	public void removeRow(int row) {
		quizCatalogus.verwijderQuiz(row);
		fireTableRowsDeleted(row, row);
	}
	
}
