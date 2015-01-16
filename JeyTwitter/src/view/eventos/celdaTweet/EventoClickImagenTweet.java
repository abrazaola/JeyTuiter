package view.eventos.celdaTweet;

import hilos.HiloMostrarImagenTweet;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.elementos.GUITweet;

public class EventoClickImagenTweet implements MouseListener {

	private final GUITweet g;

	public EventoClickImagenTweet(GUITweet guiTweet) {
		g = guiTweet;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		new HiloMostrarImagenTweet(g).start();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//establece el borde de color gris
		g.setBordeImagenTweet(3,3,3,3,new Color(220,220,220));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//establece el borde de color blanco
		g.setBordeImagenTweet(3,3,3,3, new Color(255, 255, 255));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
