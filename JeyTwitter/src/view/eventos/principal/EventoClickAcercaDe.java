package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.ventanas.AcercaDe;
import view.ventanas.Principal;

public class EventoClickAcercaDe implements MouseListener {
	
	private final Principal p;

	public EventoClickAcercaDe(Principal principal) {
		p = principal;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		AcercaDe ad = new AcercaDe();
		ad.setLocationRelativeTo(p);
		ad.setVisible(true);
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
