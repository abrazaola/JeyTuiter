package view.eventos.fastTweet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.ventanas.TweetRapido;

public class EventoAtrasFastTuit implements MouseListener {
	
	private final TweetRapido fastTuit;

	public EventoAtrasFastTuit(TweetRapido fastTuit) {
		this.fastTuit = fastTuit;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		fastTuit.getCeldaTweet().getBtnResponder().setClicado(false);
		fastTuit.dispose();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
