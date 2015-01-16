package view.elementos.botones;


@SuppressWarnings("serial")
public class BotonEnviar extends BotonUI{

	public BotonEnviar(){
		super();
		init();
	}

	/**
	 * Inicializa el contenido
	 */
	private void init() {
		//se definen las imagenes de cada estado del boton
		setEnabled(true);
		setImagenClick("/res/images/enviar-pressed.png");
		setImagenHover("/res/images/enviar-hover.png");
		setImagenNormal("/res/images/enviar-normal.png");
		setNormal();
	}
}
