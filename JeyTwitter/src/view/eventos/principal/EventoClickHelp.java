package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.ventanas.Ayuda;
import view.ventanas.Principal;

public class EventoClickHelp implements MouseListener {

	public EventoClickHelp(Principal principal) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		new Ayuda().setVisible(true);
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
