package view.eventos.condiciones;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import util.InvalidInputException;
import util.Util;
import view.ventanas.TerminosCondiciones;

public class EventoClickCondiciones implements MouseListener {

	TerminosCondiciones tc;
	public EventoClickCondiciones(TerminosCondiciones terminosCondiciones) {
		tc = terminosCondiciones;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (tc.getCheckbox().isSelected()) {
			tc.setEstado(true);
			tc.cerrar();
		}
		else{
			try {
				Util.showMessage(tc, "Atencion", "Debe aceptar las condiciones de uso", "Continuar", "Cancelar");
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
