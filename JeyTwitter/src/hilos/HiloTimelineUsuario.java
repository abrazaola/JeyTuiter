package hilos;

import java.io.IOException;
import java.util.ArrayList;

import model.Tweet;
import twitter4j.Paging;
import util.Util;
import view.elementos.Cache;
import view.elementos.GUITweet;
import view.elementos.ObjetoCelda;
import view.elementos.paneles.PanelPerfilUsuario;
import controller.GUIController;

public class HiloTimelineUsuario extends Thread{

	PanelPerfilUsuario panel;
	
	public HiloTimelineUsuario(PanelPerfilUsuario panelPerfilUsuario) {
		panel = panelPerfilUsuario;
	}
	

	public void run(){
		ArrayList<ObjetoCelda> listaObjetos = new ArrayList<ObjetoCelda>();
		try {
			ArrayList<Tweet> li = GUIController.getInstance().obtenerTimelineDeUsuario(panel.getUs().getNombreUsuario(), new Paging(Util.MAX_TWEETS));
			for (Tweet tweet : li) {
				listaObjetos.add(0, new GUITweet(Util.calcularFecha(tweet.getUltimaFechaActualizacion()), tweet));
				panel.getTablaTweetsUsuario().insertarNuevo(listaObjetos.get(0));
				panel.getTablaTweetsUsuario().actualizarAltoFilas();
			}
			Cache.getInstance().addPanelUsuario(panel.getUs().getNombreUsuario(), panel);
			
			boolean activo = false;
			ArrayList<Thread> hilosActivos = AlmacenHilos.lista;
			for (Thread t : hilosActivos) {
				if(t.isAlive()) {
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
