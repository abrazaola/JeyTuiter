package view.models;

import util.Util;
import view.elementos.botones.MenuButton;

@SuppressWarnings("serial")
public class ModeloTablaPrincipal extends ModeloTablaLateral implements DataGenerate {

	public ModeloTablaPrincipal() {
		super();
		nombresSetting = Util.principal;
		columnNames = new String[1];
		columnNames[0] = "Accion";
		rowData=generarDatos();
	}

	public Object[][] generarDatos() {
		Object[][] datos = new Object[nombresSetting.length][columnNames.length];

		MenuButton prim = new MenuButton(nombresSetting[0]);
		prim.click();
		datos[0][0]= prim;

		for (int i = 1; i < nombresSetting.length; i++) {
			MenuButton b = new MenuButton(nombresSetting[i]);
			datos[i][0]= b;
		}
		return datos;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int column) {
		return MenuButton.class;
	}

	@Override
	public int getRowCount() {
		return rowData.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return rowData[row][column];
	}
}
