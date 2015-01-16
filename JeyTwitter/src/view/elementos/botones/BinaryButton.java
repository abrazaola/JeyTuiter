package view.elementos.botones;

import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import view.eventos.botonesGenericos.EventoClickBinaryButton;

/**
 * Clase que simula un boton de dos posiciones. El boton puede estar encendido o apagado.
 * Ideal para operaciones binarias.
 */
@SuppressWarnings("serial")
public class BinaryButton extends Button{

	private boolean estado;

	private static final String IMAGEN_ON = "/res/botones/botonOn.png";
	private static final String IMAGEN_OFF = "/res/botones/botonOff.png";

	public BinaryButton(){
		super();
		init();
	}

	public BinaryButton(String texto){
		super(texto);
		init();
	}

	/**
	 * Inicializa los elementos
	 */
	private void init() {
		anchoBoton = 161;
		altoBoton = 85;

		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setHorizontalAlignment(SwingConstants.CENTER);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setIcon(getImagenOff());
		setSize(anchoBoton, altoBoton);
		//Listener
		addMouseListener(new EventoClickBinaryButton(this));
	}

	/**
	 * @return devuelve la imagen asignada al estado ON
	 */
	public  ImageIcon getImagenOn() {
		return new ImageIcon(BinaryButton.class.getResource(IMAGEN_ON));
	}

	/**
	 * @return devuelve la imagen asignada al estado OFF
	 */
	public  ImageIcon getImagenOff() {
		return new ImageIcon(BinaryButton.class.getResource(IMAGEN_OFF));
	}

	/**
	 * @return devuelve el estado actual del boton: TRUE - FALSE, ON - OFF
	 */
	public boolean getEstado() {
		return estado;
	}

	/**
	 * @param establece el estado del boton
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
		if(estado)
			setIcon(getImagenOn());
		else
			setIcon(getImagenOff());
	}
}
