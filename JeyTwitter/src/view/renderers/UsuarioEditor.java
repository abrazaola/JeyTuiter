package view.renderers;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import view.elementos.GuiTwitterUsuario;

@SuppressWarnings("serial")
public class UsuarioEditor extends AbstractCellEditor implements TableCellEditor{

	private GuiTwitterUsuario tu;

	public UsuarioEditor(GuiTwitterUsuario tu){
		this.tu=tu;
	}

	public UsuarioEditor() {}

	@Override
	public Object getCellEditorValue() {
		return tu;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.tu = (GuiTwitterUsuario)value;
		return tu;
	}
}
