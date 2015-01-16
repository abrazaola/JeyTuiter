package view.eventos.barraMenu;

/**
 * Evento que controla la accion a realizar cuando el usuario clica en el boton minimizar de la barra superior
 * @author Sergio Anguita
 */
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import util.Util;
import view.parents.Moveable;


public class EventoClickMinimizar implements MouseListener {

	private final Moveable ventana;

	public EventoClickMinimizar(Moveable ventana) {
		this.ventana = ventana;
	}

	@Override
	public void mouseClicked(MouseEvent arg0){
		Point currentLocation = ventana.getLocation();
		ventana.setLastPosition(currentLocation);

		Dimension lowerBorder = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		Float op = 1.0f;

		if(Util.isMac()){
			Util.ocultarImagenDifuso(ventana);
			ventana.setVisible(true);
		}
		else{
			for (int i = currentLocation.y; i < lowerBorder.height+ventana.getHeight(); i++) {
				currentLocation = new Point(currentLocation.x, i);
				op = op - 0.001f;
				if(op > 0f){
					ventana.setOpacity(op);
					ventana.setLocation(currentLocation);
				}
			}
		}
		ventana.setLocation(ventana.getLastPosition());
		ventana.setExtendedState(JFrame.ICONIFIED);

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		ventana.setImagenIconos(new ImageIcon(EventoClickMaximizar.class.getResource("/res/images/botonera/minimizarHover.png")));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		ventana.setImagenIconos(new ImageIcon(EventoClickMaximizar.class.getResource("/res/images/botonera/botonesNormales.png")));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		ventana.setImagenIconos(new ImageIcon(EventoClickMaximizar.class.getResource("/res/images/botonera/minimizarClick.png")));
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		ventana.setImagenIconos(new ImageIcon(EventoClickMaximizar.class.getResource("/res/images/botonera/botonesNormales.png")));
	}

}
