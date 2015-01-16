package view.eventos.principal;

import hilos.HiloBuscar;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.elementos.paneles.PanelBusqueda;

public class EventoClickBuscar implements MouseListener {

	private PanelBusqueda pb;
	private static HiloBuscar hb;
	
	public EventoClickBuscar(PanelBusqueda panelBusqueda) {
		pb = panelBusqueda;
		hb = new HiloBuscar();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(!hb.isAlive()){
			pb.getTablaResultadosBusqueda().limpiar();
			String str = pb.getBusqString();
			hb.setStrBusqueda(str);
			hb.start();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
