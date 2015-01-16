package view.eventos.celdaTweet;

import hilos.HiloResponder;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.elementos.GUITweet;

public class EventoClickBtnResponder implements MouseListener {

	private final GUITweet t;

	public EventoClickBtnResponder(GUITweet guiTweet) {
		t = guiTweet;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		new HiloResponder(t, event).start();
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
