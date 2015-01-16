package hilos;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import util.Util;
import view.elementos.ObjetoCelda;
import controller.GUIController;
import controller.sql.Interaccion;

public class HiloGuardarDB extends Thread {

	private boolean play;

	public HiloGuardarDB() {
		play = true;
	}


	public void run(){

		while(play){
			Util.pausar(2000);
			ArrayList<Thread> hilos = AlmacenHilos.lista;
			boolean activo = false;
			for (Thread t : hilos) {
				if(t.isAlive()) {
					activo = true;
					break;
				}
			}

			if(!activo) {
				//guardar contenido en la bd;
				//Solo guardar la timeline
				String nombre;
				try {
					JOptionPane.showMessageDialog(null, "introduccinedo daker.");
					nombre = GUIController.getInstance().getUsuarioRegistrado().getNombreUsuario();
					ArrayList<ObjetoCelda> lista = GUIController.getInstance().getGui().getPanelTimeLine().getTabla().getListaObjetos();
					Interaccion.insertarObjetosCelda(lista, nombre, "png");
					play = false;
				} catch (IOException e) {}
			}
		}
	}
}
