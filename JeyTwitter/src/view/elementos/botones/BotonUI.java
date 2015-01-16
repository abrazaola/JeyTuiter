package view.elementos.botones;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import util.Util;
import view.eventos.botonesGenericos.EventosButton;
import view.ventanas.MensajeWindow;

@SuppressWarnings("serial")
public class BotonUI extends Button{

	public BotonUI(){
		super();
		init();
	}

	public BotonUI(String texto){
		super(texto);
		init();
	}
	/**
	 * Inicializa el contenido
	 */
	private void init() {
		anchoBoton = 164;
		altoBoton = 43;

		//Caracteristicas del boton
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFont(Util.getFont("Roboto-Regular", Font.PLAIN, 16)); //new Font("Quark", Font.PLAIN, 20)
		setForeground(Color.WHITE);
		setHorizontalAlignment(SwingConstants.CENTER);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setSize(anchoBoton, altoBoton);

		//se definen las imagenes de cada estado del boton
		setImagenClick("/res/botones/botonUIClick.png");
		setImagenHover("/res/botones/botonUIHover.png");
		setImagenNormal("/res/botones/botonUINormal.png");

		setIcon(new ImageIcon(MensajeWindow.class.getResource(getImagenNormal())));

		//Se le anyade el listener que controlara las imagenes dependiendo del estado del raton
		addMouseListener(new EventosButton(this));
	}

	/**
	 * Establece el estado del boton en modo click
	 */
	public void click() {
		System.out.println(getImagenClick());
		setIcon(new ImageIcon(MensajeWindow.class.getResource(getImagenClick())));
	}
	/**
	 * Establece el estado del boton en modo entered
	 */
	public void entered() {
		setIcon(new ImageIcon(MensajeWindow.class.getResource(getImagenHover())));
	}
	/**
	 * Establece el estado del boton en modo exited
	 */
	public void exited() {
		setIcon(new ImageIcon(MensajeWindow.class.getResource(getImagenNormal())));
	}
}
