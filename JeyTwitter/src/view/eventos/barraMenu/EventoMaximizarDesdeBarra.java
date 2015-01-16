package view.eventos.barraMenu;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import view.parents.CustomJFrame;
import view.parents.Moveable;
/**
 * Evento que controla la accion a realizar cuando el usuario maximiza de nuevo la aplicacion
 * @author Sergio Anguita
 *
 */
public class EventoMaximizarDesdeBarra implements WindowListener {

	private final Moveable ventana;

	public EventoMaximizarDesdeBarra(Moveable customJFrame) {
		ventana = customJFrame;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0) {}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		ventana.setLocation(((CustomJFrame) ventana).getLastPosition());
		ventana.setOpacity(1.0f);
		ventana.setVisible(true);
	}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}

}
