package hilos;

import java.io.IOException;

import util.Util;
import view.systray.SystemTrayLogo;
import view.ventanas.Bienvenida;
import view.ventanas.Principal;
import controller.GUIController;

public class HiloCargarDatos extends Thread{

	private final Bienvenida ventana;
	
	public HiloCargarDatos(Bienvenida ventana) {
		this.ventana = ventana;
	}

	public void run(){
		if(ventana.getCodeField().evaluate()){
			try {
				//Se cierra la ventana de introducir el codigo
				ventana.cerrar();
				//aparece el globo emergente
				SystemTrayLogo l = SystemTrayLogo.getInstace();
				l.enviarMensaje("Descargando datos", Util.APP_TITULO+" esta descargando los datos mas recientes");
				l.setTitulo("Descargando");
				GUIController.getInstance().guardarUsuario(ventana.getCodigo());
				//Se cargan los datos
				Principal p = new Principal(GUIController.getInstance().getUsuarioRegistrado());
				GUIController.getInstance().setGui(p);
				p.setLocationRelativeTo(ventana);
				p.setPanelActual(p.getPaneles()[1]);
				p.setVisible(true);
				p.mostrarDatos();
			} catch (IllegalStateException /*| InvalidInputException*/ e) {
				ventana.setVisible(true);
				Util.showError(ventana, "Error de autentificacion", "El token no es valido", "Cancelar", "Aceptar");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
