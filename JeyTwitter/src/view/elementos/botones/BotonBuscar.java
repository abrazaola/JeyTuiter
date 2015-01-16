package view.elementos.botones;

import javax.swing.ImageIcon;

import view.ventanas.MensajeWindow;

@SuppressWarnings("serial")
public class BotonBuscar extends BotonUI{

	public BotonBuscar(){
		super();
		init();
	}

	/**
	 * Inicializa el contenido
	 */
	private void init() {
		//se definen las imagenes de cada estado del boton
		setImagenClick("/res/images/principal/botonBusqueda_pressed.png");
		setImagenHover("/res/images/principal/botonBusqueda_hover.png");
		setImagenNormal("/res/images/principal/botonBusqueda_normal.png");

		setIcon(new ImageIcon(MensajeWindow.class.getResource(getImagenNormal())));
	}
}
