package hilos;

import model.Tweet;
import controller.sql.Interaccion;

public class HiloInsertarTweet extends Thread {

	private Tweet insertar;
	private String usuario;
	public HiloInsertarTweet(Tweet tweet, String usuario) {
		insertar = tweet;
		this.usuario = usuario;
	}
	public HiloInsertarTweet() {

	}
	public void run(){
		Interaccion.insertarTweet(insertar, usuario, "png");
	}
	public Tweet getInsertar() {
		return insertar;
	}
	public void setInsertar(Tweet insertar) {
		this.insertar = insertar;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
