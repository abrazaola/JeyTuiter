package hilos;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import view.elementos.GUITweet;
import view.ventanas.TweetRapido;

public class HiloResponder extends Thread{
	
	private final GUITweet t;
	private final MouseEvent event;
	
	public HiloResponder(GUITweet t, MouseEvent event) {
		this.t = t;
		this.event = event;
	}

	public void run(){
		t.getBtnResponder().setClicado(true);
		String usuario = t.getNombreUsuario();
		
		TweetRapido tr = new TweetRapido(t, usuario);
		String usuariosMencionados = "";
		ArrayList<String> todos = t.getUsuarioMencionado();
		for (String usmencionado : todos) {
			usuariosMencionados+=" "+usmencionado;
		}
		usuariosMencionados = usuariosMencionados.trim();
		tr.setMensaje(usuariosMencionados+" ");
		tr.colocarVentana(tr, event.getXOnScreen(), event.getYOnScreen());
		tr.getTxtMensaje().setCaretPosition(tr.getTxtMensaje().getText().length());
		tr.setVisible(true);
	}
}
