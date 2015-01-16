package view.renderers;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import view.elementos.GUITweet;

@SuppressWarnings("serial")
public class TweetEditor extends AbstractCellEditor implements TableCellEditor{

	private GUITweet l;

	public TweetEditor(GUITweet l){
		this.l=l;
	}

	public TweetEditor() {}

	@Override
	public Object getCellEditorValue() {
		return l;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.l = (GUITweet)value;
		return l;
	}
}
