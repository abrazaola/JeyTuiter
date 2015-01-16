package view.eventos.botonesGenericos;

/**
 * Evento que controla las acciones a realizar en los diferentes estado de un boton (normal - click - hover)
 * @author Sergio Anguita
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.elementos.botones.Button;

public class EventosButton implements MouseListener {

	Button boton;

	public EventosButton(Button button) {
		boton = button;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		boton.setHover();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		boton.setNormal();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		boton.setClick();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		boton.setNormal();
	}

}
