package model;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import twitter4j.Status;
import util.Util;
import view.elementos.Cache;
import view.elementos.GUITweet;

public class Tweet {
	private long codigo;
	private String nombreUsuario;
	private String nombreReal;
	private Date ultimaFechaActualizacion;
	private Image imagenUsuario;
	private Image imagenDelTweet;
	private String texto;
	private boolean esRetweet;
	private boolean esFavorito;
	
	public Tweet(long codigo, String nombreUsuario, String nombreReal,
			Date ultimaFechaActualizacion, Image imagenUsuario, String texto,
			boolean esRetweet, boolean esFavorito, Image imageDelTweet) {
		this.codigo = codigo;
		this.nombreUsuario = nombreUsuario;
		this.nombreReal = nombreReal;
		this.ultimaFechaActualizacion = ultimaFechaActualizacion;
		this.imagenDelTweet = imageDelTweet;
		this.texto = texto;
		this.esRetweet = esRetweet;
		this.esFavorito = esFavorito;
		this.imagenUsuario = imagenUsuario;
	}
	
	public Tweet(Status s) {
		this.codigo = s.getId();
		this.nombreUsuario = s.getUser().getScreenName();
		this.nombreReal = s.getUser().getName();
		this.ultimaFechaActualizacion = s.getCreatedAt();
		this.imagenDelTweet = null;
		this.texto = s.getText();
		this.esRetweet = s.isRetweeted();
		this.esFavorito = s.isFavorited();
		try {
			ImageIcon ii = Cache.getInstance().getImagenesUsuario(nombreUsuario);
			if(ii==null){	
				this.imagenUsuario = ImageIO.read(new URL(s.getUser().getBiggerProfileImageURL()));
				ImageIcon temp = Util.getImagenRedondeada(new ImageIcon(imagenUsuario), GUITweet.REDONDEO);
				Cache.getInstance().addImagenesUsuario(nombreUsuario, temp);
			}
			else{
				this.imagenUsuario = ii.getImage();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreReal() {
		return nombreReal;
	}

	public void setNombreReal(String nombreReal) {
		this.nombreReal = nombreReal;
	}

	public Date getUltimaFechaActualizacion() {
		return ultimaFechaActualizacion;
	}

	public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
		this.ultimaFechaActualizacion = ultimaFechaActualizacion;
	}

	public Image getImagenUsuario() {
		return imagenUsuario;
	}

	public void setImagenUsuario(Image imagenUsuario) {
		this.imagenUsuario = imagenUsuario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public boolean esRetweet() {
		return esRetweet;
	}

	public void setEsRetweet(boolean esRetweet) {
		this.esRetweet = esRetweet;
	}

	public boolean esFavorito() {
		return this.esFavorito;
	}

	public void setEsFavorito(boolean esFavorito) {
		this.esFavorito = esFavorito;
	}

	public Image getImagenDelTweet() {
		return imagenDelTweet;
	}

	public void setImagenDelTweet(Image imagenDelTweet) {
		this.imagenDelTweet = imagenDelTweet;
	}
	
}
