package controller.sql;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import view.elementos.ObjetoCelda;
import model.Tweet;
import model.Usuario;

public class Interaccion 
{
	static SQLiteManager gestor = SQLiteManager.getInstance();
	/**
	 * Introduce los credenciales de un nuevo usuario, sin tener que cargar con ello el resto de valores
	 * @param usuario
	 * @param codigo
	 * @param codigoSecreto
	 * @return
	 */
	public static boolean introducirCredenciales(String usuario, String codigo, String codigoSecreto)
	{
		return 	gestor.enviarComando("INSERT INTO Usuario (nombreUsuario, token, secretToken) VALUES ('"+usuario+"','"+codigo+"','"+codigoSecreto+"')");
	}
	/**
	 * Introduce los datos completos del usuario
	 * @param introducir
	 * @return
	 */
	public static boolean introducirUsuario(Usuario introducir)
	{
		boolean comprobar = true;
		comprobar = comprobar && gestor.enviarComando("INSERT INTO Usuario(nombreUsuario, nombreReal, token, secretToken, biografia, numeroSeguidos, numeroSeguidores, numeroTweets, numeroFavoritos, fechaActualizacion) VALUES ('"+introducir.getNombreUsuario()+"','"+introducir.getNombreReal()+"','"+introducir.getToken()+"','"+introducir.getTokenSecreto()+"','"+introducir.getBiografia()+"',"+introducir.getNumeroSiguiendo()+","+introducir.getNumeroSeguidores()+","+introducir.getNumeroTweets()+","+introducir.getNumeroFavoritos()+",'"+introducir.getUltimaFechaActualizacion().toString()+"')");
		comprobar = comprobar && actualizarImagenPerfil(introducir.getNombreUsuario(), introducir.getImagen(), "png");
		return comprobar;
	}
	/**
	 * Borra el usuario indicado
	 * @param usuario
	 * @return
	 */
	public static boolean borrarCredenciales(String usuario)
	{
		return 	gestor.enviarComando("DELETE FROM Usuario WHERE nombreUsuario = '"+usuario+"'");
	}
	/**
	 * Borra los tweets del usuario indicado
	 * @param usuario
	 * @return
	 */
	public static boolean borrarTweets(String usuario)
	{
		return 	gestor.enviarComando("DELETE FROM Tienen WHERE nombreUsuario = '"+usuario+"'");
	}
	/**
	 * Extrae todos lso usuarios registrados
	 * @return
	 */
	public static ArrayList<Usuario> extraerCredenciales()
	{
		gestor.enviarComando("SELECT * FROM Usuario");
		try {
			ResultSet extraidos = gestor.getResultSet();
			ArrayList<Usuario> temporal = new ArrayList<Usuario>();

			while(extraidos.next())
			{
				Usuario tempUsuario = new Usuario(extraidos.getString("nombreUsuario"), extraidos.getString("token"), extraidos.getString("secretToken"));
				temporal.add(tempUsuario);
			}				

			return temporal;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean actualizarNombreReal(String nombreUsuario, String nombreReal)
	{
		return 	gestor.enviarComando("UPDATE Usuario SET nombreReal = '"+nombreReal+"' WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	
	public static boolean actualizarBiografia(String nombreUsuario, String biografia)
	{
		return 	gestor.enviarComando("UPDATE Usuario SET biografia = '"+biografia+"' WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	
	public static boolean actualizarNumSeguidores(String nombreUsuario, int numSeguidores)
	{
		return 	gestor.enviarComando("UPDATE Usuario SET numeroSeguidores = "+numSeguidores+" WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	
	public static boolean actualizarNumSeguidos(String nombreUsuario, int numSeguidos)
	{
		return 	gestor.enviarComando("UPDATE Usuario SET numeroSeguidos = "+numSeguidos+" WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	
	public static boolean actualizarNumTweets(String nombreUsuario, int numTweets)
	{
		return 	gestor.enviarComando("UPDATE Usuario SET numeroTweets = "+numTweets+" WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	
	public static boolean actualizarNumFavoritos(String nombreUsuario, int numFaves)
	{
		return 	gestor.enviarComando("UPDATE Usuario SET numeroFavoritos = "+numFaves+" WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	/**
	 * Actualiza la imagen de perfil del usuario
	 * @param nombreUsuario
	 * @param imagen
	 * @param formato
	 * @return
	 */
	public static boolean actualizarImagenPerfil(String nombreUsuario, Image imagen, String formato)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		try {
			ImageIO.write((RenderedImage)imagen, formato, baos);
		} catch (IOException e) {
			e.printStackTrace();
		}  
		byte[] data = baos.toByteArray(); 
		return gestor.enviarImagen("UPDATE Usuario SET imagen = ? WHERE nombreUsuario = '"+nombreUsuario+"'", data);
	}
	/**
	 * Borra los credenciales de un usuario
	 * @return
	 */
	public static boolean borrarTodosLosCredenciales() {
		return 	gestor.enviarComando("DELETE FROM Usuario");
	}
	/**
	 * Extrae todos los usuarios que haya almacenados
	 * @return
	 */
	public static ArrayList<Usuario> extraerUsuarios()
	{
		gestor.enviarComando("SELECT * FROM Usuario");
		try {
			ResultSet extraidos = gestor.getResultSet();
			ArrayList<Usuario> temporal = new ArrayList<Usuario>();
			while(extraidos.next())
			{
				Usuario tempUsuario = new Usuario(extraidos.getString("nombreUsuario"),
						extraidos.getString("token"),
						extraidos.getString("secretToken"),
						extraidos.getString("nombreReal"),
						extraidos.getString("biografia"),	
						null,	
						extraidos.getDate("fechaActualizacion"),	
						extraidos.getInt("numeroTweets"),
						extraidos.getInt("numeroFavoritos"),
						extraidos.getInt("numeroSeguidos"),	
						extraidos.getInt("numeroSeguidores"));
				temporal.add(tempUsuario);
			}
			cargarImagenesUsuarios(temporal);
			return temporal;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Cierra la base de datos
	 */
	public static void cerrarBase()
	{
		gestor.disconnet();
	}
	/**
	 * Extrae todos los tweets con toda la información para una cuenta de usuario
	 * @param cuenta
	 * @return
	 */
	public static ArrayList<Tweet> extraerTweets(String cuenta)
	{
		gestor.enviarComando("SELECT T.* FROM Tweet T, Tienen TI WHERE T.codigo=TI.codigo AND TI.nombreUsuario='"+cuenta+"'");
		try {
			ResultSet extraidos = gestor.getResultSet();
			ArrayList<Tweet> temporal = new ArrayList<Tweet>();
			while(extraidos.next())
			{
				boolean esRetweet = false;
				boolean esFav = false;
				if(extraidos.getInt("esRetweet")==1)
					esRetweet = true;
				if(extraidos.getInt("esFavorito")==1)
					esFav = true;
				
				Tweet tempTweet = new Tweet(extraidos.getLong("codigo"),
						extraidos.getString("nombreUsuario"),
						extraidos.getString("nombreReal"),
						new Date(extraidos.getLong("fechaActualizacion")),	
						null,
						extraidos.getString("texto"),	
						esRetweet,	
						esFav,
						null);
				temporal.add(tempTweet);
			}
			cargarImagenesTweets(temporal);
			cargarImagenesTweetsContenido(temporal);
			return temporal;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	private static void cargarImagenesUsuarios(ArrayList<Usuario> usuarios)
	{
		for(Usuario temp: usuarios)
		{
			try{
				temp.setImagen(gestor.getImageUsuario(temp.getNombreUsuario()));
			}catch(Exception e){
			}
		}
	}
	private static void cargarImagenesTweets(ArrayList<Tweet> tweets)
	{
		for(Tweet temp: tweets)
		{
			try{
				temp.setImagenUsuario(gestor.getImageTweet(temp.getCodigo()));
			}catch(Exception e){
			}
		}
	}
	private static void cargarImagenesTweetsContenido(ArrayList<Tweet> tweets)
	{
		for(Tweet temp: tweets)
		{
			try{
				temp.setImagenDelTweet(gestor.getImageTweetContenido(temp.getCodigo()));
			}catch(Exception e){
			}
		}
	}
	
	public static boolean insertarTweet(Tweet añadir, String nombreUsuario, String formatoImagen)
	{
		int fav = 0;
		int ret = 0;
		if(añadir.esFavorito())
			fav = 1;
		if(añadir.esRetweet())
			ret = 1;
		System.out.println(gestor.enviarComando("INSERT INTO Tweet(codigo, fechaActualizacion, nombreUsuario, nombreReal, texto, esRetweet, esFavorito) VALUES ("+añadir.getCodigo()+","+añadir.getUltimaFechaActualizacion().getTime()+", '"+añadir.getNombreUsuario()+"','"+añadir.getNombreReal()+"','"+añadir.getTexto()+"',"+ret+","+fav+")"));
		System.out.println(gestor.enviarComando("INSERT INTO Tienen VALUES ('"+nombreUsuario+"',"+añadir.getCodigo()+")"));
		try{
			actualizarImagenTweet(añadir.getImagenUsuario(), formatoImagen, añadir.getCodigo());
		}catch(IllegalArgumentException E)
		{
			return true;
		}
		return true;
	}
	
	public static synchronized boolean insertarObjetosCelda(List<ObjetoCelda> añadir, String nombreUsuario, String formatosImagenes)
	{
		boolean correcto = true;
		for(ObjetoCelda temp: añadir)
		{
			correcto = correcto && insertarTweet(temp.getTweet(), nombreUsuario, formatosImagenes);
		}
		return correcto;
	}
	
	public static synchronized boolean insertarTweets(List<Tweet> añadir, String nombreUsuario, String formatosImagenes)
	{
		boolean correcto = true;
		for(Tweet temp: añadir)
		{
			correcto = correcto && insertarTweet(temp, nombreUsuario, formatosImagenes);
		}
		return correcto;
	}
	
	public static synchronized boolean actualizarImagenTweetContenido(Image imagen, String formato, long codTweet)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		try {
			ImageIO.write((RenderedImage)imagen, formato, baos);
		} catch (IOException e) {
			e.printStackTrace();
		}  
		byte[] data = baos.toByteArray(); 
		return gestor.enviarImagen("UPDATE Tweet SET imagenTweet = ? WHERE codigo = "+codTweet+"", data);
	}
	/**
	 * Actualiza la imagen del Tweet, la del perfil del usuario, para ello se pasa la imagen, su formato y el código del tweet
	 * @param imagen
	 * @param formato
	 * @param codTweet
	 * @return
	 */
	public static boolean actualizarImagenTweet(Image imagen, String formato, long codTweet)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		try {
			ImageIO.write((RenderedImage)imagen, formato, baos);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}  
		byte[] data = baos.toByteArray(); 
		return gestor.enviarImagen("UPDATE Tweet SET imagenUsuario = ? WHERE codigo = "+codTweet+"", data);
	}
	/**
	 * Restaura la base de datos a sus valores originales
	 */
	public static void reiniciarBase()
	{
		gestor.enviarComando("DROP TABLE IF EXISTS Usuario;");
		gestor.enviarComando("DROP TABLE IF EXISTS Tweet;");
		gestor.enviarComando("DROP TABLE IF EXISTS Tienen;");
		crearEstructura();
	}
	/**
	 * Crea las tablas si no existen
	 */
	public static void crearEstructura()
	{
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Usuario (nombreUsuario text NOT NULL,nombreReal text, token text NOT NULL, secretToken text, biografia text, imagen blob, numeroSeguidos integer, numeroSeguidores integer, numeroTweets integer, numeroFavoritos integer, fechaActualizacion BIGINT,PRIMARY KEY(nombreUsuario));");		
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Tweet (codigo BIGINT NOT NULL, fechaActualizacion DATETIME, nombreUsuario TEXT, nombreReal TEXT, imagenUsuario blob, imagenTweet blob, texto TEXT, esRetweet integer, esFavorito integer, PRIMARY KEY(codigo));");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Tienen (nombreUsuario text NOT NULL,codigo text NOT NULL,PRIMARY KEY(nombreUsuario,codigo),CONSTRAINT nombreUsuario FOREIGN KEY (nombreUsuario) REFERENCES Usuario (nombreUsuario) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT codigo FOREIGN KEY (codigo) REFERENCES Tweet (codigo) ON DELETE CASCADE ON UPDATE CASCADE);");
	}
	public static void main(String[]args) throws IOException
	{
		reiniciarBase();
	}

}
