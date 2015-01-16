package hilos;

import java.util.Date;

import model.Tweet;
import util.Util;
import view.elementos.GUITweet;
import view.elementos.paneles.PanelEnviarTweet;
import view.models.tablasPrincipal.TablaTweetsUsuarios;
import controller.GUIController;

public class HiloEnviarTweet extends Thread {

	private PanelEnviarTweet panel;

	public HiloEnviarTweet(PanelEnviarTweet panel) {
		this.panel = panel;
	}

	public void run(){
		String mensaje = panel.getMensaje();
		panel.setMensaje("");
		Tweet t = GUIController.getInstance().enviarTweet(mensaje);
		if(t!=null) {
			TablaTweetsUsuarios tablaTimeLine = panel.getVentanaPadre().getPanelTimeLine().getTabla();
			TablaTweetsUsuarios tablaUsuario = panel.getVentanaPadre().getPanelUsuario().getTablaTweetsUsuario();
			GUITweet gt = new GUITweet(Util.calcularFecha(new Date(System.currentTimeMillis())), t);
			tablaTimeLine.insertarNuevo(gt);
			tablaUsuario.insertarNuevo(gt);
			tablaTimeLine.actualizarAltoFilas();
			tablaUsuario.actualizarAltoFilas();
		} else {
			GUIController.getInstance().getGui().mostrarMensaje("Ocurrio un error al enviar el tweet");
		}                       

	}
}