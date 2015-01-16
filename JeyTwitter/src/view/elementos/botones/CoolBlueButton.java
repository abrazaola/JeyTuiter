package view.elementos.botones;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import util.Util;
import view.eventos.botonesGenericos.EventosButton;

/**
 * Representa un boton grafico de color azul con tres estados diferentes
 * @author Sergio Anguita
 */
@SuppressWarnings("serial")
public class CoolBlueButton extends Button{

	public CoolBlueButton(){
		super();
		init();
	}

	public CoolBlueButton(String texto){
		super(texto);
		init();
	}

	/**
	 * Inicializa los elementos
	 */
	private void init() {
		anchoBoton = 99;
		altoBoton = 29;

		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFont(Util.getFont("Roboto-Regular", Font.PLAIN, 14));
		setForeground(Color.WHITE);
		setHorizontalAlignment(SwingConstants.CENTER);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setIcon(new ImageIcon(CoolBlueButton.class.getResource("/res/botones/btn_normal.png")));
		setSize(anchoBoton, altoBoton);

		//se definen las imagenes de cada estado del boton
		setImagenClick("/res/botones/btn_click.png");
		setImagenHover("/res/botones/btn_hover.png");
		setImagenNormal("/res/botones/btn_normal.png");

		//Se le anyade el listener basico que las cambia dependiendo del estado
		addMouseListener(new EventosButton(this));
	}
}
