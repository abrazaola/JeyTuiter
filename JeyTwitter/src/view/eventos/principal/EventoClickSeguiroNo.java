package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.GUIController;
import twitter4j.TwitterException;
import view.elementos.botones.BotonSeguir;

public class EventoClickSeguiroNo implements MouseListener {

	private final BotonSeguir b;
	private final String usuario;
	public EventoClickSeguiroNo(BotonSeguir botonSeguir, String usuario) {
		b = botonSeguir;
		this.usuario = usuario;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Cambia el icono del boton de seguir a dejar de seguir
		if (!b.isSiguiendo()) {
			System.out.println("Le sigo");
			try {
				GUIController.getInstance().seguirUsuario(usuario);
			} catch (TwitterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			b.setIcon(b.getImagenOff());
		} else {
			System.out.println("No le estoy siguiendo");
			try {
				GUIController.getInstance().dejarDeSeguirUsuario(usuario);
			} catch (TwitterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			b.setIcon(b.getImagenOn());
		}
		b.setSiguiendo(!b.isSiguiendo());
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
