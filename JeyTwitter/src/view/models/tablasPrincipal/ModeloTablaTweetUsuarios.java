package view.models.tablasPrincipal;

import java.util.ArrayList;

import view.elementos.GUITweet;
import view.elementos.GuiTwitterUsuario;
import view.elementos.ObjetoCelda;
import view.models.DataGenerate;
import view.models.ModeloTablaLateral;

@SuppressWarnings("serial")
public class ModeloTablaTweetUsuarios extends ModeloTablaLateral implements DataGenerate {

	private int total;
	private int tipo;

	public ModeloTablaTweetUsuarios() {
		super();
		columnNames = new String[1];
		columnNames[0] = "Resultados";
		total = 0;
		rowData=generarDatos();
	}

	public ModeloTablaTweetUsuarios(ArrayList<ObjetoCelda> listaObjetos) {
		super();
		columnNames = new String[1];
		columnNames[0] = "Resultados";
		if(listaObjetos == null){
			lista = new ArrayList<ObjetoCelda>();
			tipo = TablaTweetsUsuarios.SOLO_TWEETS; // se establece un tipo por defecto
			total = 0;
		}
		else{
			lista = listaObjetos;
			total = listaObjetos.size();
			rowData=generarDatos();
		}
	}

	@Override
	/**
	 * Genera los datos de la tabla
	 */
	public Object[][] generarDatos() {
		Object[][] datos = new Object[total][1];	//Una sola columna en la tabla con 'total' filas
		if(lista.size()>0){
			tipo = lista.get(0).tipoObjeto();
		}
		for (int i = 0; i < total; i++) {
			datos[i][0]= lista.get(i);
		}

		return datos;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int column) {
		if(tipo == TablaTweetsUsuarios.SOLO_TWEETS)
			return GUITweet.class;
		return GuiTwitterUsuario.class;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		return lista.get(row);
	}
}
