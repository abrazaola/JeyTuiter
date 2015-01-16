package view.elementos.paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import view.elementos.ObjetoCelda;
import view.elementos.botones.BotonBuscar;
import view.elementos.input.CampoBusqueda;
import view.eventos.principal.EventoClickBuscar;
import view.models.tablasPrincipal.ModeloTablaTweetUsuarios;
import view.models.tablasPrincipal.TablaTweetsUsuarios;

@SuppressWarnings("serial")
public class PanelBusqueda extends JPanel {

	private CampoBusqueda busq;
	private TablaTweetsUsuarios tablaBusqueda;
	private ArrayList<ObjetoCelda> objeto;

	public PanelBusqueda(){
		super();
		init();
	}

	public PanelBusqueda(ArrayList<ObjetoCelda> lista){
		super();
		objeto = lista;
		init();
	}

	public void init(){
		setLayout(new BorderLayout(0, 0));

		JPanel panel_sup = new JPanel();
		add(panel_sup, BorderLayout.NORTH);
		panel_sup.setLayout(new BorderLayout(0,0));
		panel_sup.setBorder(new LineBorder(Color.WHITE, 5));
		panel_sup.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));

		BotonBuscar lblBusqueda = new BotonBuscar();
		lblBusqueda.setIcon(new ImageIcon(PanelBusqueda.class.getResource("/res/images/principal/botonBusqueda_normal.png")));
		panel_sup.add(lblBusqueda, BorderLayout.EAST);
		lblBusqueda.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		lblBusqueda.addMouseListener(new EventoClickBuscar(this));

		JPanel panel_BusquedaSup = new JPanel();
		panel_sup.add(panel_BusquedaSup, BorderLayout.CENTER);
		panel_BusquedaSup.setLayout(null);

		busq = new CampoBusqueda();
		panel_BusquedaSup.add(busq);
		busq.setBounds(0, 0, 360, 39);
		panel_BusquedaSup.add(busq.getInputField(), BorderLayout.WEST);
		panel_BusquedaSup.add(busq.getImagenFondo(), BorderLayout.WEST);
		panel_BusquedaSup.setBackground(Color.WHITE);
		busq.getInputField().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));

		JPanel panelBusquedaTabla = new JPanel();
		add(panelBusquedaTabla, BorderLayout.CENTER);
		panelBusquedaTabla.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneTablaBusqueda = new JScrollPane();
		scrollPaneTablaBusqueda.getVerticalScrollBar().setUnitIncrement(5);
		panelBusquedaTabla.add(scrollPaneTablaBusqueda, BorderLayout.CENTER);

		tablaBusqueda = new TablaTweetsUsuarios(objeto);
		scrollPaneTablaBusqueda.setViewportView(tablaBusqueda);
	}

	/**
	 * @return the busq
	 */
	public CampoBusqueda getBusq() {
		return busq;
	}

	public String getBusqString() {
		return busq.getInputField().getText();
	}

	/**
	 * @param busq the busq to set
	 */
	public void setBusq(CampoBusqueda busq) {
		this.busq = busq;
	}

	/**
	 * @return the tablaBusqueda
	 */
	public TablaTweetsUsuarios getTablaResultadosBusqueda() {
		return tablaBusqueda;
	}

	/**
	 * @param tablaBusqueda the tablaBusqueda to set
	 */
	public void setTablaResultadosBusqueda(TablaTweetsUsuarios tablaBusqueda) {
		this.tablaBusqueda = tablaBusqueda;
	}

	/**
	 * @param busq the busq to set
	 */
	public void actualizarTabla() {
		if(objeto!=null && objeto.size()>0)
			tablaBusqueda.setModel(new ModeloTablaTweetUsuarios(objeto));
		tablaBusqueda.repaint();
	}

	/**
	 * @return the objeto
	 */
	public ArrayList<ObjetoCelda> getObjeto() {
		return objeto;
	}

	/**
	 * @param objeto the objeto to set
	 */
	public void setObjeto(ArrayList<ObjetoCelda> objeto) {
		this.objeto = objeto;
	}
}
