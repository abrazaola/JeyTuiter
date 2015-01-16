package view.eventos.barraMenu;

import view.parents.Moveable;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import util.Util;
/**
 * Evento que controla la accion a realizar cuando el usuario clica en el boton cerrar de la barra superior
 * @author Sergio Anguita
 */

public class EventoClickCerrar implements MouseListener {

	private Moveable parent;
	
	public EventoClickCerrar(Moveable parent){
		this.parent = parent;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		boolean dispose = false;
		dispose = parent.isDisposeWindow();
		if(dispose){
			Util.ocultarImagenDifuso(parent);
			parent.dispose();
		}
		else{
			try {
				boolean respPositiva = Util.showMessage(parent, "Cerrar","Desea realmente cerrar "+Util.APP_TITULO+"?", "Cerrar", "Cancelar");
				if(respPositiva){
					parent.dispose();
					System.exit(0);
				}
			} catch (Exception e) {}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		parent.setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/CloseHover.png")));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		parent.setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/botonesNormales.png")));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		parent.setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/closeClick.png")));
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		parent.setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/botonesNormales.png")));
	}
}