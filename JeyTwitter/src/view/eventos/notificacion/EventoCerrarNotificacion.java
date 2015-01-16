package view.eventos.notificacion;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.ventanas.Notificacion;

public class EventoCerrarNotificacion implements MouseListener {

	private final Notificacion not;

	public EventoCerrarNotificacion(Notificacion notificacion) {
		not = notificacion;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		not.dispose();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
