package hilos;

import java.awt.Component;
import java.io.FileNotFoundException;

import util.Util;
import view.elementos.GUITweet;
import view.ventanas.VisorImagen;
import controller.GUIController;

public class HiloMostrarImagenTweet extends Thread {

private final GUITweet g;
	
	public HiloMostrarImagenTweet(GUITweet guiTweet) {
		g = guiTweet;
	}

	public void run(){
		Component parent = GUIController.getInstance().getGui();
		try {
			VisorImagen vimg = new VisorImagen(parent, g.getImagenTweetOriginal());
			vimg.open();
		} catch (FileNotFoundException e) {
			Util.showError(parent, "Error al procesar", "No se puede mostrar la imagen correctamente", "Cancelar", "Aceptar");
		}
	}
}
