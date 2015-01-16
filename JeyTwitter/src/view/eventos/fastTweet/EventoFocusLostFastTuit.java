package view.eventos.fastTweet;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import view.ventanas.TweetRapido;

public class EventoFocusLostFastTuit implements FocusListener {
	
	private final TweetRapido fastTuit;
	private boolean gained = false;

	public EventoFocusLostFastTuit(TweetRapido fastTuit) {
		this.fastTuit = fastTuit;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		gained = true;
	}

	@Override
	public void focusLost(FocusEvent e) {
		if ( gained ){
			fastTuit.getCeldaTweet().getBtnResponder().setClicado(false);
			fastTuit.dispose();
		}
	}
}
