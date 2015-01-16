package view.elementos.botones;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import util.Util;
import view.eventos.botonesGenericos.EventosButton;

@SuppressWarnings("serial")
public class BotonNegroCuadrado extends Button {

	public BotonNegroCuadrado(){
		super();
		init();
	}

	public BotonNegroCuadrado(String string) {
		super(string);
		init();
	}

	/**
	 * Inicializa el contenido
	 */
	private void init() {

		anchoBoton = 74;
		altoBoton = 36;

		//se definen las imagenes de cada estado del boton
		setImagenClick("/res/images/fastTweet/bnpressed.png");
		setImagenHover("/res/images/fastTweet/bnhover.png");
		setImagenNormal("/res/images/fastTweet/bnnormal.png");

		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFont(Util.getFont("Roboto-Regular", Font.PLAIN, 14));
		setForeground(Color.WHITE);
		setHorizontalAlignment(SwingConstants.CENTER);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setIcon(new ImageIcon(BotonNegroCuadrado.class.getResource(getImagenNormal())));
		setSize(anchoBoton, altoBoton);

		//Se le anyade el listener basico que las cambia dependiendo del estado
		addMouseListener(new EventosButton(this));
	}
}
