package controller;

import hilos.HiloEstadistica;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import model.Tweet;
import model.Usuario;
import twitter4j.MediaEntity;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;
import twitter4j.auth.AccessToken;
import util.Util;
import view.elementos.GUITweet;
import view.elementos.GuiTwitterUsuario;
import view.elementos.ObjetoCelda;
import view.elementos.paneles.PanelBusqueda;
import view.elementos.paneles.PanelEstadistica;
import view.ventanas.Principal;
import _launcher.Launcher;
import controller.sql.Interaccion;


public class GUIController {
	// Keys de la API console
	private final static String CONSUMER_KEY = "KRiUVHsXKNRDHVIGxYJ7w";
	private final static String CONSUMER_KEY_SECRET = "BDwwg2NBUjY48OcTB2818sp7E7L32AzhNLdgt82ZVQ";
	private static final String HOST = "http://www.twitter.com";
	private static GUIController instancia = null; 

	private static TwitterService t;
	private boolean online;
	private Principal gui;

	/* Metodos para el funcionamiento del singleton */
	public GUIController() {}

	private static void crearInstancia() {
		if (instancia == null) { 
			instancia = new GUIController();
			try {        	
				instancia.autenticar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public static GUIController getInstance() {
		crearInstancia();
		return instancia;
	}
	/* Fin de los metodos para el funcionamiento del singleton */


	public void autenticar() throws Exception {
		t = new TwitterService(CONSUMER_KEY, CONSUMER_KEY_SECRET);
	}


	public ArrayList<Tweet> mostrarTimeline() throws IOException {
		ResponseList<Status> listaTL;
		ArrayList<Tweet> timeline = new ArrayList<Tweet>();
		//Recuperar tweets de la BD
		timeline = Interaccion.extraerTweets(GUIController.getInstance().getUsuarioRegistrado().getNombreUsuario());
		//Cargar nuevos
		if(hayConexion()){
			try {
				listaTL = t.getTimeline();
				for (Status each : listaTL) {
					Tweet t;
					t = new Tweet(each);
					timeline.add(0, t);
				}
			} catch (TwitterException e) {
				// Error al recuperar el timeline
				System.err.println("Error al recuperar el timeline "+e.getMessage());
			}
		}
		else{
			Util.showError(null, "Datos no disponibles", "Se requiere conexiona Internet para iniciar", "Aceptar", "Cancelar");
		}
		return timeline;
	}

	public ArrayList<Tweet> mostrarTimeline(int max) throws IOException {
		ResponseList<Status> listaTL;
		ArrayList<Tweet> timeline = new ArrayList<Tweet>();
		//Cargar nuevos
		if(hayConexion()){
			try {
				for (int j = 1; j <= max; j++) {
					listaTL = t.getTimeline(new Paging(j));
					for (Status each : listaTL) {
						Tweet t;
						t = new Tweet(each);
						timeline.add(0, t);
					}
				}
			} catch (TwitterException e) {
				// Error al recuperar el timeline
				System.err.println("Error al recuperar el timeline "+e.getMessage());
			}
		}
		else{
			Util.showError(null, "Datos no disponibles", "Se requiere conexiona Internet para iniciar", "Aceptar", "Cancelar");
		}
		return timeline;
	}

	public ArrayList<Tweet> obtenerTimelineDeUsuario(String usuario, Paging paging) throws MalformedURLException, IOException {
		ArrayList<Tweet> objetosTweet = new ArrayList<Tweet>();

		try {
			for (int i = 1; i <= paging.getPage(); i++) {
				ResponseList<Status> statuses = t.getTimelineFromUser(usuario, new Paging(i));
				for (Status each : statuses) {
					Tweet t = new Tweet(each);
					objetosTweet.add(0, t);
				}
			}
		} catch (TwitterException e) {

		}
		return objetosTweet;
	}

	public static boolean existeUsuario(String nombre){
		boolean existe = true;

		try{
			t.getUsuario(nombre);
			existe = true;
		}catch(Exception e){
			existe = false;
		}

		return existe;
	}
	public ArrayList<Tweet> mostrarMenciones() throws MalformedURLException, IOException {
		ResponseList<Status> listaTL;
		ArrayList<Tweet> timeline = new ArrayList<Tweet>();

		if (hayConexion()) {
			try {
				listaTL = t.getMentions();
				for (Status each : listaTL) {
					Tweet t;
					t = new Tweet(each);
					timeline.add(0, t);
				}
			} catch (TwitterException e) {
				// Error al recuperar el timeline
				e.printStackTrace();
			}
		}
		return timeline;
	}


	public ArrayList<Tweet> mostrarRetuits() throws IOException {
		ResponseList<Status> listaTL;
		ArrayList<Tweet> timeline = new ArrayList<Tweet>();

		if (hayConexion()) {
			try {
				listaTL = t.getRetweetsOfMe();
				for (Status each : listaTL) {
					Tweet t;
					t = new Tweet(each);
					timeline.add(0, t);
				}
			} catch (TwitterException e) {
				// Error al recuperar el timeline
				e.printStackTrace();
			}
		}
		return timeline;
	}


	public BufferedImage pedirImagen(URL url) throws IOException {
		return ImageIO.read(url);
	}


	public ArrayList<Tweet> mostrarPerfil() throws IOException {
		ResponseList<Status> listaTL;
		ArrayList<Tweet> timeline = new ArrayList<Tweet>();

		if (hayConexion()) {
			try {
				listaTL = t.getProfileTuits();
				for (Status each : listaTL) {
					Tweet t;
					t = new Tweet(each);
					timeline.add(0, t);
				}
			} catch (TwitterException e) {
				// Error al recuperar el timeline
				e.printStackTrace();
			}
		}
		return timeline;
	}


	public ArrayList<Tweet> mostrarFavoritos() throws IOException {
		ResponseList<Status> listaTL;
		ArrayList<Tweet> timeline = new ArrayList<Tweet>();

		if (hayConexion()) {
			try {
				listaTL = t.getFavorites();
				for (Status each : listaTL) {
					Tweet t;
					t = new Tweet(each);
					timeline.add(0, t);
				}
			} catch (TwitterException e) {
				// Error al recuperar el timeline
				e.printStackTrace();
			}
		}
		return timeline;
	}

	public boolean marcarRetuit(long codigo) {
		try {
			t.retweetear(codigo);
			return true;
		} catch (TwitterException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean marcarFavorito(long codigo) {
		try {
			t.favorito(codigo);
			return true;
		} catch (TwitterException e) {
			try {
				t.desfavear(codigo);
			} catch (TwitterException e1) {
			}
			return false;
		}
	}

	public void responderTuit(long codigo, String texto) {
		StatusUpdate respuesta = new StatusUpdate(texto);
		respuesta.setInReplyToStatusId(codigo);

		try {
			t.tweet(respuesta);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	public Usuario getUsuario(long id) throws IOException {
		User user = null;
		Usuario u = null;
		try {
			user = t.getUsuario(id);

			if (hayConexion()) {
				u = new Usuario(user);
			} else {
				ArrayList<Usuario> credenciales = Interaccion.extraerUsuarios();
				u = (Usuario)credenciales.get(0);
			}
		} catch (IllegalStateException | TwitterException e) {
			e.printStackTrace();
		}

		return u;
	}

	public Usuario getUsuario(String screenName) throws IOException {
		User user = null;
		Usuario u = null;
		try {
			user = t.getUsuario(screenName);

			if (hayConexion()) {
				u = new Usuario(user);
			} else {
				ArrayList<Usuario> credenciales = Interaccion.extraerUsuarios();
				u = (Usuario)credenciales.get(0);
			}
		} catch (IllegalStateException | TwitterException e) {
			e.printStackTrace();
		}

		return u;
	}


	public Usuario getUsuarioRegistrado() throws IOException {
		User user = null;
		Usuario u = null;
		try {
			Launcher.mostrarMensaje("Obteniendo usuario...");

			if (hayConexion()) {
				user = t.getUsuarioRegistrado();
				u = new Usuario(user);
			} else {
				ArrayList<Usuario> credenciales = Interaccion.extraerUsuarios();
				u = (Usuario)credenciales.get(0);
			}
		} catch (IllegalStateException | TwitterException e) {
			System.err.println("Error al obtener el usuario autorizado: "+e.getMessage());
		}

		return u;
	}


	public static void stalker(PanelEstadistica pe, String usuario, int numPaginas, String ruta) throws MalformedURLException, IOException {
		new HiloEstadistica(pe, t, usuario, numPaginas, ruta).start();
	}

	public boolean hayConexion() {
		@SuppressWarnings("unused")
		InetAddress address;
		try {
			URL obj = new URL(HOST);
			URLConnection conn = obj.openConnection();

			//get all headers
			Map<String, List<String>> map = conn.getHeaderFields();

			//get header by 'key'
			//String server = conn.getHeaderField("Server");
			if(map.isEmpty()){
				//System.err.println("Comprobacion de cabezera HTTP fallida");
				//Util.debug("Comprobando mediante resolucion de Host...");
				//try resolving host
				address = InetAddress.getByName(HOST);
			}
			online = true;
		} catch (IOException e) {
			System.err.println("Comprobacion de resolucion de Host fallida");
			online = false;
		}
		return online;
	}


	public void solicitarCodigo() {
		try {
			t.pedirCodigo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean recuperarTokenUsuarioGuardado() {
		ArrayList<Usuario> usuariosAlmacenados = Interaccion.extraerUsuarios();
		System.out.println("El tamanyoo de la tabla usuarios es: "+usuariosAlmacenados.size());
		Launcher.mostrarMensaje("Recuperando credenciales...");
		if (usuariosAlmacenados.size() > 0) {
			// Hay resultados, aunque solo esperamos una fila.
			// Asignamos el token y a otra cosa
			System.out.println("El token de la BBDD es "+usuariosAlmacenados.get(0).getToken());
			Launcher.mostrarMensaje("Esperando respuesta de Twitter...");
			t.recuperarToken(usuariosAlmacenados.get(0).getToken(), usuariosAlmacenados.get(0).getTokenSecreto());
			return true;
		} else {
			System.out.println("No hay usuarios guardados.");

			return false;
		}

	}

	public MediaEntity[] getMedias(long idTweet) throws TwitterException {
		return t.getMediaEntities(idTweet);
	}

	public URLEntity[] getURLs(long idTweet) throws TwitterException {
		return t.getURLEntities(idTweet);
	}

	public UserMentionEntity[] getMenciones(long idTweet) throws TwitterException {
		return t.getMentionEntities(idTweet);
	}

	public void guardarUsuario(String codigo){
		try {
			AccessToken accessToken = t.crearToken(codigo);

			Usuario u = new Usuario(t.getUsuarioRegistrado(), accessToken.getToken(), accessToken.getTokenSecret());
			// Guardar en la BBDD
			Interaccion.introducirUsuario(u);		
		} catch (TwitterException e) {
			System.out.println("Error al autenticarse");
			e.printStackTrace();
		}
	}


	public Tweet enviarTweet(String texto) {
		// Se supone que recoge el texto del textfield de turno
		try {
			StatusUpdate update = new StatusUpdate(texto);
			Status s = t.tweet(update);
			return new Tweet(s);
		} catch (TwitterException e) {
			// Error al tuitear, mostrar mensaje
		}
		return null;
	}

	public ArrayList<ObjetoCelda> buscarTweets(String str) throws IOException{
		ArrayList<ObjetoCelda> listaTweets = new ArrayList<ObjetoCelda>();
		Util.debug("iniciando busqueda de tweets...");
		List<Status> tweets = t.buscarTuit(str);
		Util.debug("mostrando tweets...");
		for (Status t : tweets) {
			listaTweets.add(0, new GUITweet(Util.calcularFecha(t.getCreatedAt()), new Tweet(t)));
			actualizarPanelBusqueda(listaTweets);
		}
		Util.debug("busqueda finalizada.");
		return listaTweets;
	}

	public ArrayList<ObjetoCelda> buscarUsuarios(String str, int maxPages) {
		ArrayList<ObjetoCelda> usuarios = new ArrayList<ObjetoCelda>();
		Util.debug("iniciando busqueda de usuarios...");
		ArrayList<User> users = t.buscarUsuario(str, maxPages);
		Util.debug("mostrando tweets...");
		for (User user : users) {
			ImageIcon i;
			URL urlImage;
			try {
				urlImage = new URL(user.getBiggerProfileImageURL());
				Image imageProfile = ImageIO.read(urlImage);
				i = new ImageIcon(imageProfile);
				boolean isfriend = isAmigo(t.getUsuarioRegistrado().getScreenName(), user.getScreenName());
				usuarios.add(0, new GuiTwitterUsuario(user.getName(), user.getScreenName(), i, user.getDescription(), isfriend));
				actualizarPanelBusqueda(usuarios);
			} catch (IOException | IllegalStateException | TwitterException e) {
				System.err.println("Error al buscar: "+e.getMessage());
			}
		}
		Util.debug("busqueda finalizada.");
		return usuarios;
	}


	public void actualizarPanelBusqueda(ArrayList<ObjetoCelda> users) {
		PanelBusqueda pb = new PanelBusqueda(users);
		gui.setPanelBusqueda(pb);
	}

	public boolean isAmigo(String user1, String user2) {
		return t.esSeguidor(user1, user2);
	}

	public void seguirUsuario(String nombreUsuario) throws TwitterException {
		t.seguirUsuario(nombreUsuario);
	}

	public void dejarDeSeguirUsuario(String nombreUsuario) throws TwitterException {
		t.dejarDeSeguirUsuario(nombreUsuario);
	}

	public Principal getGui() {
		return gui;
	}


	public void setGui(Principal gui) {
		this.gui = gui;
	}
}