package _launcher;
import java.io.File;
import java.io.IOException;

import controller.GUIController;
import controller.sql.Interaccion;
import util.Util;
import view.systray.SystemTrayLogo;
import view.ventanas.Principal;
import view.ventanas.Splash;
import view.ventanas.Bienvenida;
import view.ventanas.TerminosCondiciones;

/**
 * Inicia la aplicacion
 * @author Sergio Anguita
 *
 */
public class Launcher {
	
	private final static Splash spl = new Splash();
	
			
	public static void main(String[] args) throws IOException {

		if(!new File(Util.SQLITE_NOMBRE_BBDD).exists()){
			TerminosCondiciones t = new TerminosCondiciones();
			t.setLocationRelativeTo(null);
			t.setModal(true);
			t.setVisible(true);
			if(!t.isCondicionesAceptadas()){
				return;
			}
			Interaccion.crearEstructura();//Crea la estructura de la BD si no esta el archivo *.sqlite
		}
		spl.mostrar(5);
		
		GUIController g = new GUIController();
		mostrarMensaje("Conectando con Twitter");
		if(!g.hayConexion()){
			mostrarMensaje("Ejecutandose en modo Offline");
			//No hay conexion a internet
			boolean resp = Util.showError(null, "Error de conexion", Util.APP_TITULO+" se mostrara en modo offline", "Cancelar", "Aceptar");
			if(resp){
				/*Se debe comprobar que el usuario tiene los credenciales guardados en la bd*/
				if(GUIController.getInstance().recuperarTokenUsuarioGuardado())
					mostrarPrincipal();
				else{
					//El usuario no existe y no tiene Internet para autentificarse por lo tanto salir
					Util.showError(null, Util.APP_TITULO+" se cerrara", "No tiene sesion iniciada en "+Util.APP_TITULO+".", "Cancelar", "Aceptar");
					spl.dispose();
					System.exit(1);
				}
			}
			else{
				//No hay internet y el usuario no quiere usarlo en modo offline
				spl.dispose();
				System.exit(1);
			}
		}
		else{
			//Si hay conexion a internet
			//Se evalua el token de acceso
			mostrarMensaje("Autentificando usuario...");
			if (GUIController.getInstance().recuperarTokenUsuarioGuardado())
				mostrarPrincipal();
			else
				mostrarBienvenida();
		}
		spl.dispose();
	}
	/**
	 * Muestra un mensaje en la splash inicial
	 * @param texto mensaje a mostrar
	 */
	public static void mostrarMensaje(String texto) {
		if(spl!=null && spl.isVisible()){
			spl.setTextoMensaje(texto);
		}
	}

	/**
	 * Muestra la ventana de bienvenida
	 */
	private static void mostrarBienvenida() {
		Bienvenida wc = new Bienvenida();
		wc.setVisible(true);
	}

	/**
	 * Muestra la ventana principal
	 */
	private static void mostrarPrincipal() {
		// Tenemos token, lanzamos la ventana principal
		//Este usuario es el usuario que tiene la sesion de twitter abierta y que tiene que ser cargado
		//de la bd o online dependiendo de si esta conectado o no
		SystemTrayLogo l = SystemTrayLogo.getInstace();
		l.mostrar();
		Launcher.mostrarMensaje("Esperando datos...");
		Principal p;
		try {
			p = new Principal(GUIController.getInstance().getUsuarioRegistrado());
			GUIController.getInstance().setGui(p);
			p.setPanelActual(p.getPaneles()[Principal.TIMELINE]);
			p.setVisible(true);
			p.mostrarDatos();
		} catch (IOException e) {
			e.printStackTrace();
		}
		spl.dispose();
	}
}
