package view.eventos.principal;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.elementos.paneles.PanelEnviarTweet;

public class EventoKeyListenerTweet implements KeyListener {
	
	private final PanelEnviarTweet panel;
	
	public EventoKeyListenerTweet(PanelEnviarTweet panelEnviarTweet) {
		panel = panelEnviarTweet;
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {

		// TODO Auto-generated method stub
		panel.setContador(String.valueOf(140 - panel.getMensaje().length()));
		if (panel.getMensaje().length()>140 || panel.getMensaje().length() == 0){
			panel.getBtnEnviar().setEnabled(false);
			panel.getLblContador().setForeground(Color.RED);
		} else {
			panel.getBtnEnviar().setEnabled(true);
			panel.getLblContador().setForeground(Color.BLACK);
		}
		
	
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
