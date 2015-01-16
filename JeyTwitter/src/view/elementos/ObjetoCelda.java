package view.elementos;

import javax.swing.Icon;

import model.Tweet;

public interface ObjetoCelda {

	public int tipoObjeto();

	public String getNombreReal();

	public String getNombreUsuario();

	public Icon getImagenUsuario();

	public String getMensaje();

	public String getTiempo();

	public int getHeight();

	public Tweet getTweet();
}
