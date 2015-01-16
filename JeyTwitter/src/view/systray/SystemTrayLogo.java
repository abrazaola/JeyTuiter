package view.systray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import javax.swing.ImageIcon;

import util.Util;

/**
 * Clase necesaria para mostrar el programa en la barra de tareas
 * @author Sergio Anguita
 *
 */
public class SystemTrayLogo {
	
	private static SystemTrayLogo bar;
	private PopupMenu popup;
	private TrayIcon trayIcon;
    private SystemTray tray;
    private Image icono;
	private boolean existe;
	private String titulo;
    
	/**
	 * Main de prueba
	 * @param args
	 */
	public static void main(String[] args){
		SystemTrayLogo s = new SystemTrayLogo();
		s.mostrar();
		s.enviarError(Util.APP_TITULO+" "+Util.APP_VERSION, "Soy un mensaje emergente de prueba");
		SystemTrayLogo l = SystemTrayLogo.getInstace();
		l.mostrar();
		l.enviarMensaje("Descargando datos", Util.APP_TITULO+" esta descargando los datos mas recientes");
	}
	
	public static SystemTrayLogo getInstace(){
		if(bar==null){
			bar = new SystemTrayLogo();
		}
		return bar;
	}
	
	/**
	 * Constructor
	 */
	private SystemTrayLogo(){
		popup = new PopupMenu("Menu");
	    tray = SystemTray.getSystemTray();
	    icono = (new ImageIcon(SystemTrayLogo.class.getResource("/res/images/icon.png"))).getImage();
	    titulo = Util.APP_TITULO;
	    trayIcon = new TrayIcon(icono, titulo);
	}
	
	public PopupMenu getPopup() {
		return popup;
	}

	public void setPopup(PopupMenu popup) {
		this.popup = popup;
	}

	public TrayIcon getTrayIcon() {
		return trayIcon;
	}

	public void setTrayIcon(TrayIcon trayIcon) {
		this.trayIcon = trayIcon;
	}

	public SystemTray getTray() {
		return tray;
	}

	public void setTray(SystemTray tray) {
		this.tray = tray;
	}

	public Image getIcono() {
		return icono;
	}

	public void setIcono(Image icono) {
		this.icono = icono;
	}
	
	/**
	 * Muestra un globo emergente con el icono de Warning
	 * @param titulo	Titulo del globo emergente a mostrar
	 * @param texto		Texto del globo emergente
	 */
	public void enviarAviso(String titulo, String texto) {
		mostrar();
		trayIcon.displayMessage(titulo, texto, TrayIcon.MessageType.WARNING);
	}
	
	/**
	 * Muestra un globo emergente con el icono de Error
	 * @param titulo	Titulo del globo emergente a mostrar
	 * @param texto		Texto del globo emergente
	 */
	public void enviarError(String titulo, String texto) {
		mostrar();
		trayIcon.displayMessage(titulo, texto, TrayIcon.MessageType.ERROR);
	}
	
	/**
	 * Muestra un globo emergente con el icono de Informacion
	 * @param titulo	Titulo del globo emergente a mostrar
	 * @param texto		Texto del globo emergente
	 */
	public void enviarMensaje(String titulo, String texto) {
		mostrar();
		trayIcon.displayMessage(titulo, texto, TrayIcon.MessageType.INFO);
	}
	
	/**
	 * Anyade el icono en la barra de tareas
	 */
	public void mostrar() {
		if(!existe){
			//Check the SystemTray support
			if (!SystemTray.isSupported()) {
				Util.debug("SystemTray no soportado");
			}
			else{
				//La otra opcion
				MenuItem btnAbrir = new MenuItem("Abrir");
				btnAbrir.addActionListener(new EventoClickAbrirSystray());
				popup.add(btnAbrir);
				popup.addSeparator();
				//Opcion de abrir
				MenuItem exitItem = new MenuItem("Cerrar");
				exitItem.addActionListener(new EventoClickCerrarSystray());
				popup.add(exitItem);
				trayIcon.setPopupMenu(popup);
				try {
					tray.add(trayIcon);
					trayIcon.setImageAutoSize(true);
					existe = true;
				} catch (AWTException e) {
					System.out.println("Ocurrio un error al usar el icono seleccionado --> "+e.getMessage());
				}
			}
		}
	}
	
	/**
	 * Oculta el icono de la barra de tareas
	 */
	public void ocultar() {
		tray.remove(trayIcon);
		existe=false;
	}

	public void setTitulo(String desc) {
		getTrayIcon().setToolTip(titulo+" - "+desc);
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
}
