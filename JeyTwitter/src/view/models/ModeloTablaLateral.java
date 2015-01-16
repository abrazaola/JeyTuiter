package view.models;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import view.elementos.ObjetoCelda;


@SuppressWarnings("serial")
public abstract class ModeloTablaLateral extends AbstractTableModel{

	protected String[] nombresSetting;
	protected Object rowData[][];
	protected String columnNames[];
	protected ArrayList<ObjetoCelda> lista;

	public ModeloTablaLateral() {
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	public abstract int getRowCount();

	public abstract Object getValueAt(int row, int column);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public abstract Class getColumnClass(int column);

	public void setValueAt(Object value, int row, int column) {
		rowData[row][column] = value;
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public void insertarElemento(ObjetoCelda e) {
		// Anyade un elemento en la primera posicion de la tabla
		lista.add(0, e);
		actualizarContenidoTabla();
	}
	public void insertarElementoLista(ObjetoCelda e) {
		// Anyade un elemento en la primera posicion de la tabla
		lista.add(0, e);
	}
	public void actualizarContenidoTabla(){
		fireTableRowsInserted(0, lista.size()-1);
	}

	public ArrayList<ObjetoCelda> getLista(){
		return lista;
	}

	public void setLista(LinkedList<ObjetoCelda> l){
		lista.clear();
		lista.addAll(l);
		fireTableRowsInserted(0, lista.size()-1);
	}

	public void clear(){
		lista.clear();
	}

}
