package view.elementos.botones;

import javax.swing.ImageIcon;

import util.Util;

@SuppressWarnings("serial")
public class BotonDosEstados extends Button{

	private String imagenClicado;
	private boolean isClicado;

	public void setClicado(boolean b) {
		isClicado = b;
		Util.debug("Is clicado? "+isClicado);
		if(b){
			setIcon(new ImageIcon(BotonDosEstados.class.getResource(getImagenClicado())));
		}
		else{
			setIcon(new ImageIcon(BotonDosEstados.class.getResource(getImagenNormal())));
		}
	}

	public boolean isClicado(){
		return isClicado;
	}

	public void setNormal(){
		if(isClicado)
			setIcon(new ImageIcon(Button.class.getResource(getImagenClicado())));
		else
			setIcon(new ImageIcon(Button.class.getResource(getImagenNormal())));
	}

	/**
	 * @return the imagenClicado
	 */
	public String getImagenClicado() {
		return imagenClicado;
	}

	/**
	 * @param imagenClicado the imagenClicado to set
	 */
	public void setImagenClicado(String imagenClicado) {
		this.imagenClicado = imagenClicado;
	}

}
