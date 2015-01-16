package hilos;

import util.Util;
import view.ventanas.TweetRapido;
import controller.GUIController;

public class HiloResponderTweet extends Thread {
	
	private TweetRapido tuit;

	public HiloResponderTweet(TweetRapido fastTuit) {
		tuit = fastTuit;
	}

	public void run(){
		GUIController.getInstance().responderTuit(tuit.getTuit().getCodigo(), tuit.getMensaje());
		tuit.dispose();
		GUIController.getInstance().getGui().mostrarMensaje("Enviando tweet...");
		Util.pausar(2000);
	}
}