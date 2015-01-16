package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import view.ventanas.Principal;

public class EventoCambiarPanelClick implements MouseListener {

	private final Principal v;

	public EventoCambiarPanelClick(Principal principal) {
		v = principal;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTable t = v.getTablaMenu();
		int opcion = t.getSelectedRow();
		v.setPanelActual(v.getPaneles()[opcion+1]);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
