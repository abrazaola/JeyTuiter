package view.eventos.barraMenu;

import view.parents.Moveable;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
/**
 * Evento que controla la accion a realizar cuando el usuario tiene el cursor en la barra superior
 * @author Sergio Anguita
 *
 */
public class EventosDeBarra implements MouseListener, MouseMotionListener {

	private Moveable ventana;
	
	public EventosDeBarra(Moveable window){
		ventana = window;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent arg0) {}
	
	@Override
	public void mousePressed(MouseEvent e) {
		ventana.setInitialClick(e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(ventana.getInitialClick()!=null){
			// get location of Window
            int thisX = ventana.getLocation().x;
            int thisY = ventana.getLocation().y;

            // Determine how much the mouse moved since the initial click
            int xMoved = (thisX + e.getX()) - (thisX + ventana.getInitialClick().x);
            int yMoved = (thisY + e.getY()) - (thisY + ventana.getInitialClick().y);

            // Move window to this position
            int X = thisX + xMoved;
            int Y = thisY + yMoved;
            ventana.setLocation(X, Y);
		}
	}
}

