package view.eventos.celdaTweet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.elementos.GUITweet;
import controller.GUIController;

public class EventoClickBtnFavorito implements MouseListener {

	private final GUITweet t;

	public EventoClickBtnFavorito(GUITweet guiTweet) {
		t = guiTweet;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		long codigo = t.getTweet().getCodigo();

		if(GUIController.getInstance().marcarFavorito(codigo)) {
			t.getTweet().setEsFavorito(true);
			t.getBtnFavorito().setClicado(true);
		} else {
			t.getBtnFavorito().setClicado(false);
			t.getTweet().setEsFavorito(false);
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
