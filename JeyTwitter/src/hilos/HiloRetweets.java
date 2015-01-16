package hilos;

import java.io.IOException;
import java.util.ArrayList;

import model.Tweet;
import util.Util;
import view.elementos.GUITweet;
import view.elementos.ObjetoCelda;
import view.elementos.paneles.PanelTablaTweets;
import controller.GUIController;

public class HiloRetweets extends Thread{

	PanelTablaTweets panel;

	public HiloRetweets(PanelTablaTweets panelRetweets) {
		panel = panelRetweets;
	}

	public void run(){
		ArrayList<ObjetoCelda> listaObjetos = new ArrayList<ObjetoCelda>();
		try {
			ArrayList<Tweet> li = GUIController.getInstance().mostrarRetuits();
			for (Tweet tweet : li) {
				listaObjetos.add(0, new GUITweet(Util.calcularFecha(tweet.getUltimaFechaActualizacion()), tweet));
				panel.getTabla().insertarNuevo(listaObjetos.get(0));
				panel.getTabla().actualizarAltoFilas();
			}
			boolean activo = false;
			ArrayList<Thread> hilosActivos = AlmacenHilos.lista;
			for (Thread t : hilosActivos) {
				if(t.isAlive() && !t.equals(this)) {
					activo = true;
					break;
				}
			}

			if(!activo) {
				GUIController.getInstance().getGui().ocultarMensajeInformativo();				
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
