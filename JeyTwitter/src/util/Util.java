package util;

import view.parents.Moveable;
import view.ventanas.MensajeWindow;
import view.ventanas.VentanaError;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Contiene la mayoria de las variables estaticas asi como metodos y utilidades necesarias para la aplicacion
 * @author Sergio Anguita
 *
 */
public class Util {

	public static boolean DEBUG = false;

	public static final int ANCHO_PANTALLA = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int ALTO_PANTALLA = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static final int MAX_TWEETS = 2;
	public static final int MILISEGUNDOS_RECARGA_TIMELINE = 1000*60*5;

	public static final String 
	APP_TITULO = "JeyTuiter",
	APP_VERSION = "1.0",
	APP_ICONO = "/res/images/JeyIcon.png",
	FICHERO_XML = "config.xml",
	SQLITE_NOMBRE_BBDD = "JeyTuiterSQL.sqlite";

	public static final String URL_SVN = "https://code.google.com/p/proyecto-p4-2013-2014-equipo-01/";

	public static String[] principal =
		{
		"Timeline",
		"Menciones",
		"Retweets",
		"Favoritos",
		"Busqueda",
		"Estadistica"
		};

	/**
	 * Cierra la ventana mostrando un mensaje de aviso
	 * @param parent	ventana padre
	 * @throws InvalidInputException
	 */
	public static void cerrarVentana(Moveable parent) throws InvalidInputException{
		showMessage(parent, "Cerrar "+APP_TITULO, "Desea realmente cerrar?", "Si", "No");
	}
	
	/**
	 * Muestra un mensaje en pantalla
	 * @param parent	ventana padre
	 * @param titulo	titulo de la ventana a mostrar	
	 * @param mensaje	mensaje de la ventana	
	 * @param textoAceptar	texto del mensaje del boton 'Aceptar'
	 * @param textoCancelar texto del mensaje del boton 'Cancelar'
	 * @return	devuelve true si el usuario ha clicado en aceptar, false si ha clicado en cancelar o null si ha cerrado la ventana
	 * @throws InvalidInputException
	 */
	public static Boolean showMessage(Moveable parent, String titulo, String mensaje, String textoAceptar, String textoCancelar) throws InvalidInputException{
		MensajeWindow mw = new MensajeWindow();
		mw.setTituloVentana(titulo);
		mw.setMensaje(mensaje);
		mw.setBotonPositivo(textoAceptar);
		mw.setBotonNegativo(textoCancelar);
		mw.setLocationRelativeTo((Component) parent);
		mw.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		if(titulo.length()>50 || mensaje.length()>60 || textoAceptar.length()>11 || textoCancelar.length()>11)
			throw new InvalidInputException("Unexpected input string length. Check input Strings");
		mw.setVisible(true);
		//System.out.println("Estado de la respuesta: "+mw.getEstado());
		return mw.getEstado();
	}

	/**
	 * Escala el tamaño de la imagen al tamaño del componente	
	 * @param comp	componente de Swing que contiene la imagen
	 * @return	devuelve un Objeto ImageIcon con la imagen reescalada
	 */
	public static ImageIcon escalarImagen(Component comp){
		ImageIcon fot = (ImageIcon) ( (JLabel) comp ).getIcon();
		Icon icono = new ImageIcon(fot.getImage().getScaledInstance(comp.getWidth(), comp.getHeight(), Image.SCALE_SMOOTH));
		return (ImageIcon) icono;
	}

	/**
	 * Muestra una ventana con una animacion de transparencia
	 * @param comp	ventana a mostrar
	 */
	public static void mostrarImagenDifuso(Component comp) {
		mostrarImagenDifuso(comp, 25);
	}
	
	/**
	 * Muestra una ventana con una animacion de transparencia
	 * @param comp ventana a mostrar
	 * @param time	intervalo de tiempo entre cada nivel de transparencia. Controla la velocidad con la que la ventan aparece
	 */
	public static void mostrarImagenDifuso(Component comp, int time) {
		comp.setVisible(false);
		float opacidad=0.f;
		((JFrame) comp).setOpacity(opacidad);
		comp.setVisible(true);
		for (opacidad = 0.f; opacidad < 1.0f; opacidad+=0.1f ) {
			pausar(time);
			((JFrame) comp).setOpacity(opacidad);
		}
		pausar(time);
		((JFrame) comp).setOpacity(1.0f);
		((JFrame) comp).setVisible(true);
	}

	/**
	 * Oculta una ventana con una animacion de transparencia
	 * @param parent	ventana a ocultar
	 */
	public static void ocultarImagenDifuso(Moveable parent) {
		ocultarImagenDifuso(parent, 25);
	}

	/**
	 * Oculta una ventana con una animacion de transparencia
	 * @param comp ventana a mostrar
	 * @param time	intervalo de tiempo entre cada nivel de transparencia. Controla la velocidad con la que la ventan aparece
	 */
	public static void ocultarImagenDifuso(Moveable parent, int time) {
		float opacidad=1.0f;
		((Window) parent).setOpacity(opacidad);
		parent.setVisible(true);
		for (opacidad = 1.0f; opacidad > 0.0f; opacidad-=0.1f ) {
			pausar(time);
			((Window) parent).setOpacity(opacidad);
		}
		pausar(time);
		((Window) parent).setOpacity(0.0f);
		((Window) parent).setVisible(false);
		pausar(time);
	}
	
	/**
	 * Pausa la ejecucion del programa durante un tiempo determinado
	 * @param i	tiempo de pausar en ms
	 */
	public static void pausar(int i) {
		try {Thread.sleep(i);} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Asigna el Look and Feel del sistema operativo en el que se ejecuta la aplicacion
	 */
	public static void asignarLFSO() {
		//Al tener activado el look and feel de Nimbus algunas ventanas con
		//transparencia se volvian opacas. Ahora esta desactivado
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//Se supone que suaviza el movimiento de las scrollbars
			UIManager.put("List.lockToPositionOnScroll", Boolean.FALSE);
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Genera objetos fuente con las fuentes personalizadas
	 * @param name	nombre de la fuente
	 * @param tipo	tipo de la fuente: negrita, cursiva, normal
	 * @param tamano	tamaño de la fuente
	 * @return devulve la funete especificada por parametros. En caso de haber algun error, devuelve la fuente predeterminada por el sistema
	 */
	public static Font getFont(String name, int tipo, float tamano) {
		Font font = new JLabel().getFont();	//carga la fuente por defecto
		if (name != null) {
			try {
				InputStream is = Util.class.getResourceAsStream("/res/fonts/"+name+".ttf");
				//System.out.println("Leyendo "+"/res/fonts/"+name+".ttf");
				//System.out.println(is);
				font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(tipo, tamano);
			} catch (Exception ex) {
				Util.debug(name + " not loaded.  Using default font.");
				font.deriveFont(tipo, tamano);
			}
		}
		if(font==null)
			font = new JLabel().getFont().deriveFont(tipo, tamano);
		return font;
	}
	
	/**
	 * Redondea los bordes de una imagen
	 * @param image	imagen	
	 * @param cornerRadius	grado de redondeo.
	 * @return	devuelve la imagen redondeada
	 */
	private static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
	    int w = image.getWidth();
	    int h = image.getHeight();
	    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2 = output.createGraphics();

	    // This is what we want, but it only does hard-clipping, i.e. aliasing
	    // g2.setClip(new RoundRectangle2D ...)

	    // so instead fake soft-clipping by first drawing the desired clip shape
	    // in fully opaque white with antialiasing enabled...
	    g2.setComposite(AlphaComposite.Src);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setColor(Color.WHITE);
	    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

	    // ... then compositing the image on top,
	    // using the white shape from above as alpha source
	    g2.setComposite(AlphaComposite.SrcAtop);
	    g2.drawImage(image, 0, 0, null);

	    g2.dispose();

	    return output;
	}
	
	/**
	 * Convierte una Image a un objeto BufferedImage
	 * @param img	imagen a convertir
	 * @return
	 */
	private static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	/**
	 * Calcula una imagen redondeada dada la imagen y el grado de redondeo
	 * @param imagenOriginal imagen original a redondear
	 * @param redondeo	grado de redondeo
	 * @return
	 */
	public static ImageIcon getImagenRedondeada(ImageIcon imagenOriginal, int redondeo){
		BufferedImage imagenRedondeada = Util.toBufferedImage(imagenOriginal.getImage());
		imagenRedondeada = Util.makeRoundedCorner(imagenRedondeada, redondeo);
		return new ImageIcon(imagenRedondeada);
	}

	/**
	 * Muestra un mensaje en consola solo si el atributo DEBUG es true.
	 * @param string	mensaje a mostrar
	 */
	public static void debug(String string) {
		if(DEBUG)
			if (string!=null) {
				System.out.println(string);
			}
			else {
				System.err.println("[Object NULL]");
			}
	}

	/**
	 * Muestra una ventana de error
	 * @param parent	ventana padre
	 * @param lblTitulodeLaVentana	Titulo de la ventana	
	 * @param lblMensajeAMostrar	mensaje
	 * @param textoBotonBlanco		texto del boton cancelar
	 * @param textoBotonRojo		texto del boton aceptar
	 * @return
	 */
	public static Boolean showError(Component parent, String lblTitulodeLaVentana, String lblMensajeAMostrar, String textoBotonBlanco, String textoBotonRojo) {
		VentanaError error = new VentanaError(parent, lblTitulodeLaVentana,lblMensajeAMostrar,textoBotonBlanco,textoBotonRojo);
		error.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		error.setVisible(true);
		return error.getEstado();
	}

	/**
	 * 
	 * @return devuelve el nombre del sistema operativo
	 */
	public static String getOS(){
		return System.getProperty("os.name").toLowerCase();
	}

	/**
	 * 
	 * @return devuelve true si el S.O es mac os. Falso si no lo es
	 */
	public static boolean isMac(){
		return getOS().contains("mac os");
	}

	/**
	 * 
	 * @return devuelve true si el S.O es Microsoft Windows. Falso si no lo es
	 */
	public static boolean isWin(){
		return getOS().contains("windows");
	}

	/**
	 * 
	 * @return devuelve true si el S.O esta basado en Linux. Falso si no lo es
	 */
	public static boolean isNix(){
		return getOS().contains("nix") || getOS().contains("ubuntu") || getOS().contains("debian");
	}
	
	/**
	 * Calcula la fecha de creacion de un Tweet
	 * @param d	long que contiene la fecha
	 * @return 	devuelve una String con la fecha correctamente escrita
	 */
	public static String calcularFecha(long d)
	{
		System.out.println(d);
		return calcularFecha(new Date(d));
	}
	public static String calcularFecha(Date d) {
		Calendar currCal = Calendar.getInstance();
		currCal.setTime(new Date(System.currentTimeMillis()));
	    int currYear = currCal.get(Calendar.YEAR);
	    int currMonth = currCal.get(Calendar.MONTH)+1;
	    int currDay = currCal.get(Calendar.DAY_OF_MONTH);
	    int currHourDay = currCal.get(Calendar.HOUR_OF_DAY);
	    int currMin = currCal.get(Calendar.MINUTE);
	    int currSec = currCal.get(Calendar.SECOND);
	    Util.debug("Actual: "+currYear+" "+currMonth+" "+currDay+" "+currHourDay+" "+currMin+" "+currSec);
	    currCal.setTime(d);
	    int year = currCal.get(Calendar.YEAR);
	    int month = currCal.get(Calendar.MONTH)+1;
	    String nombreMes = new SimpleDateFormat("MMM").format(currCal.getTime());
	    int day = currCal.get(Calendar.DAY_OF_MONTH);
	    int hourDay = currCal.get(Calendar.HOUR_OF_DAY);
	    int min = currCal.get(Calendar.MINUTE);
	    int sec = currCal.get(Calendar.SECOND);
	    Util.debug("Fecha tweet: "+year+" "+month+" "+day+" "+hourDay+" "+min+" "+sec);
	    if(currYear==year){
	    	if(currMonth == month){
	    		if(currDay==day){
	    			if(currHourDay==hourDay){
	    				if(currMin==min){
	    					if(currSec==sec){
	    						return "ahora";
	    					}
	    					else{
	    						return "hace "+String.valueOf(currSec-sec)+" seg";
	    					}
	    				}
	    				else{
	    					return "hace "+String.valueOf(currMin-min)+" min";
	    				}
	    			}
	    			else{
	    				return" hoy, "+hourDay+"h";
	    			}
	    		}
	    		//ayer
	    		else if(currDay==day-1){
	    			return "ayer,  "+hourDay+"h";
	    		}
	    		//anteayer 
	    		else if(currDay==day-2){
	    			return "anteayer a las "+hourDay+"h";
	    		}
	    		else{
	    			return String.valueOf(day)+" de "+nombreMes;
	    		}
	    	}
	    	else{
	    		return day+" de "+nombreMes;
	    	}
	    }
	    else{
	    	return day+" de "+nombreMes+" de "+year;
	    }
	}
}