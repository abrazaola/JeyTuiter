package view.renderers;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import view.elementos.GUITweet;

@SuppressWarnings("serial")
public class TweetRenderer extends DefaultTableCellRenderer{

	/**
	 * Se sobreescribe el metodo que se encarga de visualizar los tweets  en las celdas del JTable.
	 */
	public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column){
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		GUITweet l = (GUITweet)value;
		return l;
	}
}
