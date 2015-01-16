package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import view.elementos.botones.MenuButton;
import view.ventanas.Principal;

public class EventoCambiarColoBoton implements MouseListener {

	private final JTable tablaGeneral;

	public EventoCambiarColoBoton(Principal principal) {
		tablaGeneral = principal.getTablaMenu();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int row = tablaGeneral.getSelectedRow();
		for (int i = 0; i < tablaGeneral.getRowCount(); i++) {
			MenuButton b = (MenuButton) tablaGeneral.getValueAt(i, 0);
			if(i==row)
				b.click();
			else
				b.exited();
			tablaGeneral.setValueAt(b, i, 0);
		}
		tablaGeneral.repaint();
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
