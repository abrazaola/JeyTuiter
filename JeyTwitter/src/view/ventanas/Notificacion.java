package view.ventanas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Tweet;
import util.Util;
import view.eventos.notificacion.EventoCerrarNotificacion;
import view.eventos.notificacion.EventoClickImagenNotificacion;
import view.parents.InvisibleJFrame;

@SuppressWarnings("serial")
public class Notificacion extends InvisibleJFrame {

	public static final int TWEET = 0;
	public static final int RETWEET = 1;
	public static final int MENCION = 2;
	public static final int FOLLOW = 3;
	public static final int UNFOLLOW = 4;

	private JTextArea txtMensaje;
	private JLabel lblHora;
	private JLabel lblusuario;
	private JLabel lblImagenUsuario;
	private JLabel lblCerrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notificacion frame = new Notificacion();
					frame.setTipoNotificacion(Notificacion.TWEET);
					frame.ajustarApantalla();
					frame.mostrar(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Notificacion() {
		super("/res/images/notif/notification_tweet.png");
		init();
	}

	public Notificacion(Tweet tweet) {
		super("/res/images/notif/notification_tweet.png");
		init();
		setMensaje(tweet.getTexto());
		setHora(tweet.getUltimaFechaActualizacion().toString());
		setUsuario(tweet.getNombreUsuario());
		setImagenUsuario(new ImageIcon(tweet.getImagenUsuario()));
	}

	public void init(){
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblCerrar = new JLabel("X");
		lblCerrar.addMouseListener(new EventoCerrarNotificacion(this));
		lblCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCerrar.setBackground(Color.DARK_GRAY);
		lblCerrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrar.setFont(Util.getFont("Roboto-regular", Font.PLAIN, 16));
		lblCerrar.setForeground(Color.WHITE);
		lblCerrar.setBounds(392, 11, 23, 22);
		contentPane.add(lblCerrar);

		txtMensaje = new JTextArea();
		txtMensaje.setWrapStyleWord(true);
		txtMensaje.setLineWrap(true);
		txtMensaje.setEditable(false);
		txtMensaje.setFocusable(false);
		txtMensaje.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		txtMensaje.setForeground(Color.WHITE);
		txtMensaje.setFont(Util.getFont("Roboto-regular", Font.PLAIN, 12));
		//Util.getFont("mirda", Font.PLAIN, 16)

		txtMensaje.setText("Este es un comentario de prueba en una notificacion de JeyTuiter. El comentario tiene que ser largo para ver como se muestra un mensaje cuando no entra en la notificacion. Pero por lo que veo todavia tengo que escribir mas para que entre");
		txtMensaje.setBounds(114, 41, 301, 61);
		contentPane.add(txtMensaje);

		lblHora = new JLabel("13:00");
		lblHora.setFont(Util.getFont("mirda", Font.PLAIN, 16));
		lblHora.setForeground(Color.WHITE);
		lblHora.setBounds(336, 11, 44, 22);
		contentPane.add(lblHora);

		lblusuario = new JLabel("@Usuario");
		lblusuario.setFont(Util.getFont("Roboto-regular", Font.PLAIN, 14));
		lblusuario.setForeground(Color.WHITE);
		lblusuario.setBounds(114, 11, 212, 22);
		contentPane.add(lblusuario);

		lblImagenUsuario = new JLabel("Imagen");
		lblImagenUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblImagenUsuario.setBounds(6, 11, 94, 91);
		lblImagenUsuario.setIcon(Util.getImagenRedondeada(new ImageIcon(Notificacion.class.getResource("/res/images/userTest.jpg")), 20));
		lblImagenUsuario.setIcon(Util.escalarImagen(lblImagenUsuario));
		lblImagenUsuario.addMouseListener(new EventoClickImagenNotificacion(this));
		contentPane.add(lblImagenUsuario);

		contentPane.add(fondo);
		//panel para que el fondo se vea en window builder
		JPanel EstePanelEsSoloParaQueElFondoSeVea = new JPanel();
		EstePanelEsSoloParaQueElFondoSeVea.setBackground(Color.DARK_GRAY);
		EstePanelEsSoloParaQueElFondoSeVea.setBounds(0, 0, 425, 143);
		contentPane.add(EstePanelEsSoloParaQueElFondoSeVea);

		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addMouseListener(new EventoCerrarNotificacion(this));
		txtMensaje.addMouseListener(new EventoCerrarNotificacion(this));
	}

	/**
	 * @return the txtMensaje
	 */
	public String getMensaje() {
		return txtMensaje.getText();
	}

	/**
	 * @param txtMensaje the txtMensaje to set
	 */
	public void setMensaje(String txtMensaje) {
		this.txtMensaje.setText(txtMensaje);
	}

	/**
	 * @return the lblHora
	 */
	public JLabel getHora() {
		return lblHora;
	}

	/**
	 * @param lblHora the lblHora to set
	 */
	public void setHora(String lblHora) {
		this.lblHora.setText(lblHora);
	}

	/**
	 * @return the lblusuario
	 */
	public String getUsuario() {
		return lblusuario.getText();
	}

	/**
	 * @param lblusuario the lblusuario to set
	 */
	public void setUsuario(String lblusuario) {
		this.lblusuario.setText(lblusuario);
	}

	/**
	 * @return the lblImagenUsuario
	 */
	public Icon getImagenUsuario() {
		return lblImagenUsuario.getIcon();
	}

	/**
	 * @param ImagenUsuario the lblImagenUsuario to set
	 */
	public void setImagenUsuario(ImageIcon imagenUsuario) {
		if(imagenUsuario!=null){
			lblImagenUsuario.setIcon(Util.getImagenRedondeada(imagenUsuario, 20));
			lblImagenUsuario.setIcon(Util.escalarImagen(lblImagenUsuario));
		}
	}

	public void setTipoNotificacion(int tipo) throws Exception{

		/*
		 * 	public static final int TWEET = 0;
			public static final int RETWEET = 1;
			public static final int MENCION = 2;
			public static final int FOLLOW = 3;
			public static final int UNFOLLOW = 4;
		 */
		String[] imagenes = new String[5];
		imagenes[0] = "/res/images/notif/notification_tweet.png";
		imagenes[1] = "/res/images/notif/notification_retweet.png";
		imagenes[2] = "/res/images/notif/notification_mencion.png";
		imagenes[3] = "/res/images/notif/notification_follower.png";
		imagenes[4] = "/res/images/notif/notification_unfollow.png";

		setImagenFondo(imagenes[tipo]);
	}

	public void ajustarApantalla(){
		//valores para windows
		if(Util.isWin()){
			int offsetX = 10; //cantidad de pixeles de margen vertical derecho
			int offsetY = 50; //cantidad de pixeles de margen horizontal inferior
			setBounds(
					Util.ANCHO_PANTALLA-getImagenFondo().getIconWidth()-offsetX,
					Util.ALTO_PANTALLA-getImagenFondo().getIconHeight()-offsetY, 
					getImagenFondo().getIconWidth(), getImagenFondo().getIconHeight()
					);
		}
		else{
			int offsetX = 10; //cantidad de pixeles de margen vertical derecho
			int offsetY = 50; //cantidad de pixeles de margen horizontal inferior
			setBounds(
					Util.ANCHO_PANTALLA-getImagenFondo().getIconWidth()-offsetX,
					0+offsetY, 
					getImagenFondo().getIconWidth(), getImagenFondo().getIconHeight()
					);
		}
	}
}
