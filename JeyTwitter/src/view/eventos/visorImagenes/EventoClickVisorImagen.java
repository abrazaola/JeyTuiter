package view.eventos.visorImagenes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import util.Util;
import view.ventanas.VisorImagen;

public class EventoClickVisorImagen implements MouseListener {

	private final VisorImagen v;
	
	public EventoClickVisorImagen(VisorImagen visorImagen) {
		v = visorImagen;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Util.ocultarImagenDifuso(v, 20);
		v.setVisible(false);
		v.dispose();
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
