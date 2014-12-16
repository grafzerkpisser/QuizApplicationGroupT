package model.tableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Opdracht;
public class OpdrachtTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Opdracht> opdrachten;
	private String[] columnNames = { "Omschrijving" };

	public OpdrachtTableModel(List<Opdracht> opdrachten) {
		this.opdrachten = opdrachten;
	}

	public Opdracht getOpdrachtAt(int row) {
		return opdrachten.get(row);
	}

	@Override
	public int getColumnCount() {

		return 1;
	}

	public String getColumnName(int col) {
		return columnNames[col];

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public Object getValueAt(int row, int col) {
		return opdrachten.get(row).toString();
	}

	@Override
	public int getRowCount() {

		return opdrachten.size();
	}

	public void addOpdracht(Opdracht opdracht) {
		opdrachten.add(opdracht);
		int lastRow = opdrachten.size() - 1;
		fireTableRowsInserted(lastRow, lastRow);
	}

	public void removeRow(int row) {
		opdrachten.remove(row);
		fireTableRowsDeleted(row, row);
	}

}
