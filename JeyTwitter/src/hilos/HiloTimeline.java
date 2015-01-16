package hilos;

import java.io.IOException;
import java.util.ArrayList;

import model.Tweet;
import util.Util;
import view.elementos.GUITweet;
import view.elementos.ObjetoCelda;
import view.elementos.paneles.PanelTablaTweets;
import controller.GUIController;
import controller.sql.Interaccion;

public class HiloTimeline extends Thread{

	private PanelTablaTweets panel;

	public HiloTimeline(PanelTablaTweets panelTimeLine) {
		panel = panelTimeLine;
	}
	public void run(){
		ArrayList<ObjetoCelda> listaObjetos = new ArrayList<ObjetoCelda>();
		ArrayList<Tweet> timeline;
		long masReciente = 0;
		if(GUIController.getInstance().getGui().getPanelTimeLine().getTabla().getListaObjetos()!=null){
			listaObjetos = GUIController.getInstance().getGui().getPanelTimeLine().getTabla().getListaObjetos();
			for(ObjetoCelda celda : listaObjetos)
			{
				long temporal = celda.getTweet().getUltimaFechaActualizacion().getTime();
				if(masReciente<temporal)
					masReciente = temporal;
			}
		}
		try {
			timeline = Interaccion.extraerTweets(GUIController.getInstance().getUsuarioRegistrado().getNombreUsuario());
			for (Tweet tweet : timeline) {
				long fecha = tweet.getUltimaFechaActualizacion().getTime();
				if(masReciente<fecha){
					GUITweet g = new GUITweet(Util.calcularFecha(fecha), tweet);
					listaObjetos.add(0, g);
					panel.getTabla().insertarNuevo(listaObjetos.get(0));
					panel.getTabla().actualizarAltoFilas();
					if(fecha>masReciente)
						masReciente = fecha;
				}
			}
		} catch (IOException e1) {}

		try {
			ArrayList<Tweet> li = GUIController.getInstance().mostrarTimeline(Util.MAX_TWEETS);
			for (Tweet tweet : li) {
				if(tweet.getUltimaFechaActualizacion().getTime()>masReciente){
					GUITweet g = new GUITweet(Util.calcularFecha(tweet.getUltimaFechaActualizacion()), tweet);
					listaObjetos.add(0, g);
					Interaccion.insertarTweet(tweet, GUIController.getInstance().getUsuarioRegistrado().getNombreUsuario(), "png");
					panel.getTabla().insertarNuevo(listaObjetos.get(0));
					panel.getTabla().actualizarAltoFilas();
				}
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