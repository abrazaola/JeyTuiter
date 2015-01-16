package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import util.Util;
import view.ventanas.Principal;
import controller.GUIController;
import controller.sql.Interaccion;

public class EventoClickDesautorizar implements MouseListener {
	
	private final Principal p;
	
	public EventoClickDesautorizar(Principal principal) {
		p = principal;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Boolean b = Util.showError(p, "Desautorizar cuenta", "Desea cerrar sesion?", "No", "Si");
		if(b.booleanValue()){
			GUIController.getInstance().getGui().mostrarMensaje("Eliminando credenciales...");
			Interaccion.cerrarBase();
			new File(Util.SQLITE_NOMBRE_BBDD).delete();
			p.cerrar();
			System.exit(0);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}