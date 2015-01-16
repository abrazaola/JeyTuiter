package view.eventos.notificacion;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import util.InvalidInputException;
import util.Util;
import view.ventanas.Notificacion;

public class EventoClickImagenNotificacion implements MouseListener {

	public EventoClickImagenNotificacion(Notificacion notificacion) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		try {
			Util.showMessage(null, "Programar evento: ver perfil clicado", "Este evento no ha sido programado", "Vale", "Da igual");
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
