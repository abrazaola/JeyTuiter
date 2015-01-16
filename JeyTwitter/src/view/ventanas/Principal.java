package view.ventanas;

import hilos.AlmacenHilos;
import hilos.HiloFavoritos;
import hilos.HiloMenciones;
import hilos.HiloRetweets;
import hilos.HiloTimeline;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import model.Usuario;
import util.Util;
import view.elementos.botones.BotonSeguir;
import view.elementos.paneles.PanelBusqueda;
import view.elementos.paneles.PanelEnviarTweet;
import view.elementos.paneles.PanelEstadistica;
import view.elementos.paneles.PanelPerfilUsuario;
import view.elementos.paneles.PanelTablaTweets;
import view.eventos.principal.EventoCambiarColoBoton;
import view.eventos.principal.EventoCambiarPanelClick;
import view.eventos.principal.EventoClickAcercaDe;
import view.eventos.principal.EventoClickDesautorizar;
import view.eventos.principal.EventoClickFotoUsuario;
import view.eventos.principal.EventoClickHelp;
import view.models.ModeloTablaPrincipal;
import view.models.tablasPrincipal.TablaTweetsUsuarios;
import view.parents.CustomJFrame;
import view.renderers.UIButtonRenderer;
import _launcher.Launcher;
import controller.GUIController;

@SuppressWarnings("serial")
public class Principal extends CustomJFrame {

	//Constantes
	private static final Color COLOR_PANEL = new Color(64, 64, 64);

	public static final int TUITSUSUARIO = 0;
	public static final int TIMELINE = 1;
	public static final int MENCIONES = 2;
	public static final int RETUITS = 3;
	public static final int FAVORITOS = 4;
	public static final int BUSQUEDA = 5;
	public static final int ESTADISTICA = 6;

	private JTable tablaMenu;
	private BotonSeguir btnDejarDeSeguir;
	private JPanel[] panelesPrincipales;

	private PanelTablaTweets timeLine, menciones, retweets, favoritos;
	private PanelPerfilUsuario panelUsuario;
	private PanelEnviarTweet panelInferior;
	private PanelBusqueda panelBusqueda;
	private PanelEstadistica panel_stats;

	private JPanel panelMostrandoActual;
	private JPanel panelVista;
	private JLabel lblImagen;

	private static Usuario usuarioActual;
	private JPanel panelInformativo;
	private JLabel lblMensajeInformativo;
	private JLabel spinningWheel;


	public Principal(Usuario usuario) throws IOException {
		super(600, 700);
		usuarioActual = usuario;
		panelesPrincipales = new JPanel[7];
		panelInferior = new PanelEnviarTweet(this);
		panelBusqueda = new PanelBusqueda();
		panel_stats = new PanelEstadistica();
		lblImagen = new JLabel(usuario.getNombreUsuario());
		try {
			panelUsuario = new PanelPerfilUsuario(usuarioActual);
			timeLine = new PanelTablaTweets(new TablaTweetsUsuarios(0));
			menciones = new PanelTablaTweets(new TablaTweetsUsuarios(0));
			retweets  = new PanelTablaTweets(new TablaTweetsUsuarios(0));
			favoritos = new PanelTablaTweets(new TablaTweetsUsuarios(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Launcher.mostrarMensaje("Cargando ultimos detalles...");
		init();
		generarDatos();
		if(!GUIController.getInstance().hayConexion()){
			setColorFondoMensajeInformativo(Color.RED);
			setColorTextoMensajeInformativo(Color.WHITE);
			setTextoMensajeInformativo("Esta usando "+Util.APP_TITULO+" sin conexion");
			mostrarMensajeInformativo();
		}
		else{
			ocultarMensajeInformativo();
		}
		long ahora = System.currentTimeMillis();
		int s = (int) (ahora - Splash.inicio)/1000;
		System.out.println("Tiempo desde el inicio de la aplicacion: "+s+" segundos - "+s/60.0+" minutos");
		lanzarHiloRecargaTimeline();
	}
	public void lanzarHiloRecargaTimeline()
	{
		TimerTask timerTask = new TimerTask() 
		{ 
			public void run() 
			{ 
				HiloTimeline actualizar = new HiloTimeline(getPanelTimeLine());
				actualizar.start();
			} 
		}; 
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, Util.MILISEGUNDOS_RECARGA_TIMELINE, Util.MILISEGUNDOS_RECARGA_TIMELINE);
	}
	public void mostrarDatos(){
		HiloMenciones m = new HiloMenciones(getPanelMenciones());
		HiloFavoritos f = new HiloFavoritos(getPanelFavoritos());
		HiloRetweets r = new HiloRetweets(getPanelRetweets());
		HiloTimeline t = new HiloTimeline(getPanelTimeLine());

		AlmacenHilos.lista.add(0, m);
		AlmacenHilos.lista.add(0, f);
		AlmacenHilos.lista.add(0, r);
		AlmacenHilos.lista.add(0, t);

		GUIController.getInstance().getGui().mostrarMensaje("Actualizando...");
		m.start();
		f.start();
		r.start();
		t.start();

	}
	public void mostrarSpinWheelInformativa(boolean b) {
		spinningWheel.setVisible(b);
	}

	public void init(){
		setTitle(Util.APP_TITULO);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getMainPanel().setBackground(Color.DARK_GRAY);
		getMainPanel().setBorder(new EmptyBorder(0, 0, 0, 0));
		getMainPanel().setLayout(new BorderLayout(0, 0));

		new ArrayList<Thread>();

		JPanel panelApp = new JPanel();
		panelApp.setDoubleBuffered(false);
		panelApp.setEnabled(false);
		panelApp.setFocusTraversalKeysEnabled(false);
		panelApp.setFocusable(false);
		panelApp.setRequestFocusEnabled(false);
		panelApp.setBorder(new LineBorder(new Color(0, 0, 0)));
		getMainPanel().add(panelApp);
		panelApp.setLayout(new BorderLayout(0, 0));
		panelApp.add(panelInferior, BorderLayout.SOUTH);

		panelInformativo = new JPanel();
		panelInformativo.setBackground(Color.WHITE);
		panelApp.add(panelInformativo, BorderLayout.NORTH);

		lblMensajeInformativo = new JLabel(" ");
		lblMensajeInformativo.setBorder(new MatteBorder(5, 0, 3, 0, (Color) new Color(0, 0, 0, 0)));
		lblMensajeInformativo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensajeInformativo.setText("Mensaje");
		lblMensajeInformativo.setForeground(Color.BLACK);
		lblMensajeInformativo.setFont(Util.getFont("roboto-regular", Font.PLAIN, 12));
		lblMensajeInformativo.setVisible(false);
		panelInformativo.setLayout(new BorderLayout(0, 0));

		spinningWheel = new JLabel("");
		spinningWheel.setBorder(new MatteBorder(2, 0, 3, 0, (Color) new Color(0, 0, 0, 0)));
		spinningWheel.setIcon(new ImageIcon(Principal.class.getResource("/res/images/bolita giratoria.gif")));
		panelInformativo.add(spinningWheel, BorderLayout.EAST);
		panelInformativo.add(lblMensajeInformativo);

		panelVista = new JPanel();
		panelVista.setLayout(new BorderLayout(0, 0));
		panelApp.add(panelVista, BorderLayout.CENTER);
		panelVista.setVisible(true);

		JPanel panelIzq = new JPanel();
		panelIzq.setFocusTraversalKeysEnabled(false);
		panelIzq.setFocusable(false);
		panelIzq.setRequestFocusEnabled(false);
		panelIzq.setBackground(Color.DARK_GRAY);
		getMainPanel().add(panelIzq, BorderLayout.WEST);
		panelIzq.setLayout(new BorderLayout(0, 0));

		lblImagen.setOpaque(true);
		lblImagen.setBorder(new MatteBorder(11, 4, 4, 4, COLOR_PANEL));
		lblImagen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblImagen.setForeground(Color.WHITE);
		lblImagen.setBackground(Color.DARK_GRAY);
		lblImagen.setFont(Util.getFont("Roboto-regular", Font.PLAIN, 14));
		lblImagen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblImagen.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblImagen.setVerticalAlignment(SwingConstants.BOTTOM);
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setSize(100,100);
		setImagenUsuario(new ImageIcon(usuarioActual.getImagen()));
		panelIzq.add(lblImagen, BorderLayout.NORTH);

		lblImagen.addMouseListener(new EventoClickFotoUsuario(this));

		tablaMenu = new JTable();
		tablaMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaMenu.setShowHorizontalLines(false);
		tablaMenu.setShowVerticalLines(false);
		tablaMenu.setShowGrid(false);
		tablaMenu.setBackground(Color.DARK_GRAY);
		tablaMenu.setRowHeight(40);
		tablaMenu.setModel(new ModeloTablaPrincipal());
		tablaMenu.getColumnModel().getColumn(0).setCellRenderer(new UIButtonRenderer());

		//Eventos tabla izquierda
		tablaMenu.addMouseListener(new EventoCambiarColoBoton(this));
		tablaMenu.addMouseListener(new EventoCambiarPanelClick(this));

		panelIzq.add(tablaMenu, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panelIzq.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblConfig = new JLabel("Cerrar sesion");
		lblConfig.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblConfig.setForeground(Color.WHITE);
		lblConfig.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblConfig.setHorizontalTextPosition(SwingConstants.CENTER);
		lblConfig.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfig.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 14));
		lblConfig.setSize(new Dimension(30,30));
		lblConfig.setIcon(new ImageIcon(Principal.class.getResource("/res/tempIcons/configIcon.png")));
		lblConfig.setIcon(Util.escalarImagen(lblConfig));
		panel.add(lblConfig, BorderLayout.NORTH);
		lblConfig.addMouseListener(new EventoClickDesautorizar(this));

		JLabel lblHelp = new JLabel("Ayuda");
		lblHelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHelp.setForeground(Color.WHITE);
		lblHelp.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblHelp.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelp.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 14));
		lblHelp.setSize(new Dimension(30,30));
		lblHelp.setIcon(new ImageIcon(Principal.class.getResource("/res/tempIcons/helpIcon.png")));
		lblHelp.setIcon(Util.escalarImagen(lblHelp));
		panel.add(lblHelp, BorderLayout.CENTER);
		lblHelp.addMouseListener(new EventoClickHelp(this));

		JLabel lblAbout = new JLabel("Acerca de");
		lblAbout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAbout.setForeground(Color.WHITE);
		lblAbout.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblAbout.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 14));
		lblAbout.setSize(new Dimension(30,30));
		lblAbout.setIcon(new ImageIcon(Principal.class.getResource("/res/tempIcons/acercaDeIcon.png")));
		lblAbout.setIcon(Util.escalarImagen(lblAbout));
		panel.add(lblAbout, BorderLayout.SOUTH);
		lblAbout.addMouseListener(new EventoClickAcercaDe(this));
	}

	public void setTextoMensajeInformativo(String str){
		lblMensajeInformativo.setText(str);
	}

	public void setColorFondoMensajeInformativo(Color c){
		lblMensajeInformativo.setBackground(c);
		panelInformativo.setBackground(c);
	}

	public void setColorTextoMensajeInformativo(Color c){
		lblMensajeInformativo.setForeground(c);
		lblMensajeInformativo.setForeground(c);
	}

	public void mostrarMensajeInformativo(){
		Color a = lblMensajeInformativo.getForeground();
		Color b = panelVista.getForeground();
		panelInformativo.setVisible(true);
		lblMensajeInformativo.setVisible(true);
		for (float i = 0.0f; i < 1.0f; i+=0.1f) {
			Color colorPanel = new Color(b.getRed(), b.getGreen(), b.getBlue());
			Color colorLabel = new Color( (a.getRed() ), (a.getGreen()), (a.getBlue()));
			panelInformativo.setForeground(colorPanel);
			lblMensajeInformativo.setForeground(colorLabel);
			Util.pausar(25);
		}
	}

	public void ocultarMensajeInformativo(){
		Color a = lblMensajeInformativo.getForeground();
		Color b = panelInformativo.getForeground();
		for (float i = 1f; i > 0.0f; i-=0.1f) {
			Color colorPanel = new Color(b.getRed(), b.getGreen(), b.getBlue());
			Color colorLabel = new Color(a.getRed(), a.getGreen(), a.getBlue());
			panelInformativo.setForeground(colorPanel);
			lblMensajeInformativo.setForeground(colorLabel);
			Util.pausar(25);
		}
		panelInformativo.setVisible(false);
		lblMensajeInformativo.setVisible(false);
	}

	public void recargarTweets(int tipo) throws IOException {
		switch (tipo){
		case 1:
			GUIController.getInstance().mostrarTimeline();
			break;
		case 2:
			GUIController.getInstance().mostrarMenciones();
			break;
		case 4:
			GUIController.getInstance().mostrarFavoritos();
			break;
		case 3:
			GUIController.getInstance().mostrarRetuits();
			break;
		case 0:
			GUIController.getInstance().mostrarPerfil();
			break;
		case 5:
			GUIController.getInstance().mostrarTimeline();
			break;
		}
	}


	private void generarDatos() {
		panelesPrincipales[0] = panelUsuario;
		panelesPrincipales[1] = timeLine;
		panelesPrincipales[2] = menciones;
		panelesPrincipales[3] = retweets;
		panelesPrincipales[4] = favoritos;
		panelesPrincipales[5] = panelBusqueda;
		panelesPrincipales[6] = panel_stats;
	}

	public PanelTablaTweets getPanelTimeLine(){
		return timeLine;
	}

	public PanelTablaTweets getPanelMenciones(){
		return menciones;
	}

	public PanelTablaTweets getPanelRetweets(){
		return retweets;
	}

	public PanelTablaTweets getPanelFavoritos(){
		return favoritos;
	}

	public JTable getTablaMenu() {
		return tablaMenu;
	}


	public void setTablaMenu(JTable tablaMenu) {
		this.tablaMenu = tablaMenu;
	}

	public void ocultarBotonSeguir(){
		btnDejarDeSeguir.setVisible(false);
	}

	public void mostrarBotonSeguir(){
		btnDejarDeSeguir.setVisible(true);
	}

	public JPanel[] getPaneles(){
		return panelesPrincipales;
	}

	public void setPanelActual(final JPanel p){
		new Thread(new Runnable() {

			@Override
			public void run() {
				if(panelMostrandoActual!=null)
					panelVista.remove(panelMostrandoActual);
				panelMostrandoActual = p;
				if(panelMostrandoActual==null)
					panelMostrandoActual = new JPanel();

				panelVista.add(panelMostrandoActual, BorderLayout.CENTER);
				getContentPane().revalidate();
				getContentPane().repaint();
			}
		}).start();
	}


	public PanelEnviarTweet getPanelInferior() {
		return panelInferior;
	}


	public void setPanelInferior(PanelEnviarTweet panelInferior) {
		this.panelInferior = panelInferior;
	}


	public PanelBusqueda getPanelBusqueda() {
		return panelBusqueda;
	}


	public void setPanelBusqueda(PanelBusqueda panelBusqueda) {
		this.panelBusqueda = panelBusqueda;
		setPanelActual(panelBusqueda);
	}


	public JLabel getImagenUsuario() {
		return lblImagen;
	}


	public void setImagenUsuario(ImageIcon imagen) {
		// Para pruebas
		lblImagen.setIcon(Util.getImagenRedondeada(imagen, 15));
		lblImagen.setIcon(Util.escalarImagen(lblImagen));
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(Usuario usuarioActual) {
		Principal.usuarioActual = usuarioActual;
	}

	public PanelPerfilUsuario getPanelUsuario() {
		return panelUsuario;
	}

	public void setPanelUsuario(PanelPerfilUsuario panelUsuario) {
		this.panelUsuario = panelUsuario;
	}

	public void mostrarMensaje(String string) {
		GUIController.getInstance().getGui().setTextoMensajeInformativo(string);
		GUIController.getInstance().getGui().setColorTextoMensajeInformativo(Color.BLACK);
		GUIController.getInstance().getGui().setColorFondoMensajeInformativo(Color.WHITE);
		GUIController.getInstance().getGui().mostrarMensajeInformativo();
	}
}