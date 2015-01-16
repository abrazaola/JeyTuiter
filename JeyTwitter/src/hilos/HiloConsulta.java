package hilos;

import controller.sql.Interaccion;
import model.Tweet;

public class HiloConsulta extends Thread{
	private Tweet tweet;
	private String nomUsuario;
	private String imagenForm;
	public HiloConsulta(Tweet temp, String nombreUsuario, String imagenFormato)
	{
		this.tweet = temp;
		this.nomUsuario = nombreUsuario;
		this.imagenForm = imagenFormato;
	}
	
	public void run(){
		long adolf = System.currentTimeMillis();
		Interaccion.insertarTweet(tweet, nomUsuario, imagenForm);
		System.out.println(System.currentTimeMillis()-adolf + " ms");
	}
}
