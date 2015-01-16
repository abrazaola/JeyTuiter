package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import view.elementos.Cache;
import view.elementos.paneles.PanelPerfilUsuario;
import view.ventanas.Principal;
import controller.GUIController;

public class EventoClickFotoUsuario implements MouseListener {

	private final String nombreUsuario;

	public EventoClickFotoUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public EventoClickFotoUsuario(Principal principal) {
		nombreUsuario = null;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (nombreUsuario != null){
			try {
				GUIController.getInstance().getGui().mostrarMensaje("Cargando perfil de @"+nombreUsuario);
				//Si el usuario existe en la cache 'dinamica' cargar esa version
				PanelPerfilUsuario pCache = Cache.getInstance().getPanelPerfilUsuario(nombreUsuario);
				if(pCache!=null){
					GUIController.getInstance().getGui().setPanelActual(pCache);
					GUIController.getInstance().getGui().ocultarMensajeInformativo();
				}
				else{
					//Pedirlo a Twitter
					PanelPerfilUsuario panelUsuario = new PanelPerfilUsuario(GUIController.getInstance().getUsuario(nombreUsuario));
					//Guardar panel en cache 'dinamica'
					Cache.getInstance().addPanelUsuario(nombreUsuario, panelUsuario);
					GUIController.getInstance().getGui().setPanelActual(panelUsuario);
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}			
		}
		else{
			GUIController.getInstance().getGui().setPanelActual(GUIController.getInstance().getGui().getPaneles()[Principal.TUITSUSUARIO]);
		}
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
