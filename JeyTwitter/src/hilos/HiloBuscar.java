package hilos;

import java.io.IOException;
import java.util.ArrayList;

import view.elementos.Cache;
import view.elementos.ObjetoCelda;
import controller.GUIController;

/**
 * Ejecuta una busqueda de Tweets o Usuarios a Twitter.
 * @author Sergio Anguita
 *
 */
public class HiloBuscar extends Thread {

	private String strBusqueda;

	public HiloBuscar(String string) {
		strBusqueda = string;
	}

	public HiloBuscar() {
		
	}

	public void run(){
		strBusqueda = strBusqueda.toLowerCase();
		if(strBusqueda.startsWith("@")){
			//se buscaran usuarios
			buscarUsuarios(strBusqueda);
		}
		else{
			buscarTweets(strBusqueda);
		}
	}
	/**
	 * Busca tweets que contengan una palabra determianda
	 * @param str	palabra o frase con la que se filtrara la busqueda
	 */
	private void buscarTweets(String str) {
		mostrarMensaje("Buscando tweets...");
		try {
			//si la busqueda ha sido realizada y esta en la cache 'dinamica' mostrarla
			ArrayList<ObjetoCelda> listaTweets;
			listaTweets = Cache.getInstance().getResultadosBusquedaTweet(str);
			if(listaTweets==null || listaTweets.size()<=0){
				listaTweets = GUIController.getInstance().buscarTweets(str);
				Cache.getInstance().addResultadosBusquedaTweet(str, listaTweets);
			}
			else{
				GUIController.getInstance().actualizarPanelBusqueda(listaTweets);
			}
			mostrarMensaje("Tweets encontrados: "+listaTweets.size());
			sleep(2000);
			GUIController.getInstance().getGui().ocultarMensajeInformativo();
		} catch (IOException e) {
		} catch (InterruptedException e) {}
	}

	/**
	 * Busca Usuarios que contengan una palabra determianda
	 * @param str	palabra o frase con la que se filtrara la busqueda
	 */
	private void buscarUsuarios(String str) {
		mostrarMensaje("Buscando usuarios...");
		
		//si la busqueda ha sido realizada y esta en la cache 'dinamica' mostrarla
		ArrayList<ObjetoCelda> listaUsuarios;
		listaUsuarios = Cache.getInstance().getResultadosBusquedaUsuarios(str);
		if(listaUsuarios==null || listaUsuarios.size()<=0){
			listaUsuarios = GUIController.getInstance().buscarUsuarios(str, 2);
			Cache.getInstance().addResultadosBusquedaTweet(str, listaUsuarios);
		}
		else{
			GUIController.getInstance().actualizarPanelBusqueda(listaUsuarios);
		}
		mostrarMensaje("Usuarios encontrados: "+listaUsuarios.size());
	}

	/**
	 * Muestra un mensaje en pantalla, informativo para el usuario
	 * @param string
	 */
	private void mostrarMensaje(String string) {
		GUIController.getInstance().getGui().mostrarMensaje(string);
	}
	/**
	 * Asigna la palabra o frase con la cual se realizara la busqueda.
	 * @param str palabra o frase
	 */
	public void setStrBusqueda(String str) {
		strBusqueda = str;
	}

}
