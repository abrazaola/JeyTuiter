package view.renderers;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import view.elementos.botones.BinaryButton;

@SuppressWarnings("serial")
public class BinaryButtonRenderer extends DefaultTableCellRenderer{

	/**
	 * Se sobreescribe el metodo que se encarga de visualizar los datos en las celdas del JTable y se le obliga
	 * a que muestre un icono
	 */
	public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column){
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		BinaryButton l = (BinaryButton)value;
		return l;
	}
}
