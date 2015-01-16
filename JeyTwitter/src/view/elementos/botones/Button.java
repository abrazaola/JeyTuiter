package view.elementos.botones;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.eventos.botonesGenericos.EventosButton;

@SuppressWarnings("serial")
public class Button extends JLabel{

	protected int anchoBoton;
	protected  int altoBoton;

	protected String imagenNormal, imagenClick, imagenHover;

	public Button(String texto) {
		super(texto);
		addMouseListener(new EventosButton(this));
	}

	public Button() {
		super();
		addMouseListener(new EventosButton(this));
	}

	/**
	 * @return Devuelve la ruta en el path de la aplicacion de la imagen asociada al estado normal del boton
	 */
	public String getImagenNormal() {
		return imagenNormal;
	}

	/**
	 * Establece la imagen normal del boton
	 * @param imagenNormal	ruta donde reside la imagen normal del boton
	 */
	public void setImagenNormal(String imagenNormal) {
		if(isEnabled())
			this.imagenNormal = imagenNormal;
	}

	/**
	 * @return Devuelve la ruta en el path de la aplicacion de la imagen asociada al estado click del boton
	 */
	public String getImagenClick() {
		return imagenClick;
	}

	/**
	 * Establece la imagen normal del boton
	 * @param imagenClick	ruta donde reside la imagen click del boton
	 */
	public void setImagenClick(String imagenClick) {
		if(isEnabled())
			this.imagenClick = imagenClick;
	}

	/**
	 * @return Devuelve la ruta en el path de la aplicacion de la imagen asociada al estado hover del boton
	 */
	public String getImagenHover() {
		return imagenHover;
	}

	/**
	 * Establece la imagen normal del boton
	 * @param imagenHover	ruta donde reside la imagen hover del boton
	 */
	public void setImagenHover(String imagenHover) {
		if(isEnabled())
			this.imagenHover = imagenHover;
	}

	public void setNormal(){
		setIcon(new ImageIcon(Button.class.getResource(getImagenNormal())));
	}

	public void setClick(){
		setIcon(new ImageIcon(Button.class.getResource(getImagenClick())));
	}

	public void setHover(){
		setIcon(new ImageIcon(Button.class.getResource(getImagenHover())));
	}
}
