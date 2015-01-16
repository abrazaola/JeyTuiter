package view.elementos.botones;

import java.awt.Cursor;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.eventos.principal.EventoClickSeguiroNo;
import controller.GUIController;

/**
 * Clase que simula un boton de dos posiciones. El boton puede estar encendido o apagado.
 * Ideal para operaciones binarias.
 * @author Sergio Anguita
 */
@SuppressWarnings("serial")
public class BotonSeguir extends JLabel{

	private boolean estado;

	private static final String IMAGEN_SEGUIR = "/res/botones/seguir/followingIcon.png";
	private static final String IMAGEN_DEJAR_SEGUIR = "/res/botones/seguir/UnfollowIcon.png";
	private String nombreUsuario;
	public BotonSeguir(String nombreUsuario){
		super();
		this.nombreUsuario = nombreUsuario;
		init();
	}
	/**
	 * Inicializa el contenido
	 */
	private void init() {
		int anchoBoton = 161;
		int altoBoton = 85;
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		try {
			if(GUIController.getInstance().hayConexion()){
				if (!GUIController.getInstance().isAmigo(GUIController.getInstance().getUsuarioRegistrado().getNombreUsuario(), nombreUsuario)) {
					setIcon(getImagenOff());
				} else {
					setIcon(getImagenOn());
				}
			}
			else{
				setIcon(getImagenOff());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setSize(anchoBoton, altoBoton);
		//Listener
		addMouseListener(new EventoClickSeguiroNo(this, nombreUsuario));
	}

	/**
	 * @return devuelve la imagen asignada al estado ON
	 */
	public  ImageIcon getImagenOn() {
		return new ImageIcon(BotonSeguir.class.getResource(IMAGEN_SEGUIR));
	}

	/**
	 * @return devuelve la imagen asignada al estado OFF
	 */
	public  ImageIcon getImagenOff() {
		return new ImageIcon(BotonSeguir.class.getResource(IMAGEN_DEJAR_SEGUIR));
	}

	/**
	 * @return devuelve el estado actual del boton: TRUE - FALSE, ON - OFF
	 */
	public boolean isSiguiendo() {
		return estado;
	}

	/**
	 * @param establece el estado del boton
	 */
	public void setSiguiendo(boolean estado) {
		this.estado = estado;
		if(estado)
			setIcon(getImagenOn());
		else
			setIcon(getImagenOff());
	}
}
