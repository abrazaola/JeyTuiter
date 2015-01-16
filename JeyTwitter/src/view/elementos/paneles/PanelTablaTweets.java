package view.elementos.paneles;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import view.elementos.ObjetoCelda;
import view.models.tablasPrincipal.TablaTweetsUsuarios;

@SuppressWarnings("serial")
public class PanelTablaTweets extends JPanel {

	private JScrollPane scrollpane;
	private TablaTweetsUsuarios tabla;

	public PanelTablaTweets(TablaTweetsUsuarios tabla){
		super();
		scrollpane = new JScrollPane();
		this.tabla = tabla;
		init();
	}

	private void init() {
		scrollpane = new JScrollPane();
		scrollpane.setViewportBorder(null);
		setLayout(new BorderLayout(0, 0));
		scrollpane.setBorder(null);
		scrollpane.getVerticalScrollBar().setUnitIncrement(5);
		//se muestra en el scrollpane
		scrollpane.setViewportView(tabla);
		add(scrollpane, BorderLayout.CENTER);
	}

	/**
	 * @return the tabla
	 */
	public TablaTweetsUsuarios getTabla() {
		return tabla;
	}

	/**
	 * @param tabla the tabla to set
	 */
	public void setTabla(TablaTweetsUsuarios tabla) {
		this.tabla = tabla;
	}

	public void insertarNuevo(ObjetoCelda o) {
		tabla.insertarNuevo(o);
	}

	public void insertarLista(ArrayList<ObjetoCelda> l) {
		tabla.insertarLista(l);
	}
}
