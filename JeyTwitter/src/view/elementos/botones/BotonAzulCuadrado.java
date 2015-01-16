package view.elementos.botones;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import util.Util;
import view.eventos.botonesGenericos.EventosButton;

@SuppressWarnings("serial")
public class BotonAzulCuadrado extends Button {

	public BotonAzulCuadrado(){
		super();
		init();
	}

	public BotonAzulCuadrado(String string) {
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
		setImagenClick("/res/images/fastTweet/anpressed.png");
		setImagenHover("/res/images/fastTweet/anhover.png");
		setImagenNormal("/res/images/fastTweet/annormal.png");

		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFont(Util.getFont("Roboto-Regular", Font.PLAIN, 14));
		setForeground(Color.WHITE);
		setHorizontalAlignment(SwingConstants.CENTER);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setIcon(new ImageIcon(BotonAzulCuadrado.class.getResource(getImagenNormal())));
		setSize(anchoBoton, altoBoton);

		//Se le a�ade el listener basico que las cambia dependiendo del estado
		addMouseListener(new EventosButton(this));
	}
}
