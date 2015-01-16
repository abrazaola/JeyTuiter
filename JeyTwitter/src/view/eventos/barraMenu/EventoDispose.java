package view.eventos.barraMenu;

/**
 * Evento que controla la accion a realizar cuando el usuario clica en el boton cerrar (dispose) de las ventanas
 * @author Sergio Anguita
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.parents.Moveable;

public class EventoDispose implements MouseListener {

	private final Moveable ventana;

	public EventoDispose(Moveable o){
		ventana = o;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		ventana.dispose();
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
