package view.elementos.paneles;

import hilos.HiloTimelineUsuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import model.Usuario;
import util.Util;
import view.elementos.ObjetoCelda;
import view.elementos.botones.BotonSeguir;
import view.elementos.botones.CoolBlueButton;
import view.eventos.principal.EventoVerContadorPerfilusuario;
import view.models.tablasPrincipal.TablaTweetsUsuarios;
import view.ventanas.Contador;
import controller.GUIController;

@SuppressWarnings("serial")
public class PanelPerfilUsuario extends JPanel {
	
	//Constantes
	private static final Color COLOR_FONDO = new Color(24,22,23);
	
	private JLabel lblImagenUsuario;
	private JLabel lblImagenFondo;
	private JLabel lbluser;
	private JLabel lblNombreApellidos;
	private JTextArea lblBiografia;
	private BotonSeguir btnDejarDeSeguir;
	private CoolBlueButton btnTweets;
	private CoolBlueButton btnFavoritos;
	private CoolBlueButton btnSeguidores;
	private CoolBlueButton btnSiguiendo;
	private TablaTweetsUsuarios tablaTweetsUsuario;
	private ArrayList<ObjetoCelda> listaObjetos;
	private long idUsuario;
	
	private Contador cTweets, cFavoritos, cSiguiendo, cSeguidores;
	private Usuario us;
	
	private HiloTimelineUsuario hiloTimeline;
	
	public PanelPerfilUsuario(Usuario u){
		super();
		us = u;
		
		cTweets = new Contador();
		cFavoritos = new Contador();
		cSiguiendo = new Contador();
		cSeguidores = new Contador();
		
		lblImagenUsuario = new JLabel(new ImageIcon(us.getImagen()));
		lblImagenFondo = new JLabel("");
		lbluser = new JLabel();
		setUser(us.getNombreUsuario());
		lblNombreApellidos = new JLabel(us.getNombreReal());
		lblBiografia = new JTextArea(us.getBiografia());
		
		btnDejarDeSeguir = new BotonSeguir(u.getNombreUsuario());
		
		btnTweets = new CoolBlueButton("Tweets");
		btnFavoritos = new CoolBlueButton("Favoritos");
		btnSeguidores = new CoolBlueButton("Seguidores");
		btnSiguiendo = new CoolBlueButton("Siguiendo");
		tablaTweetsUsuario = new TablaTweetsUsuarios(listaObjetos);
		
		
		init();
		hiloTimeline = new HiloTimelineUsuario(this);
		hiloTimeline.start();
	}
	
	/**
	 * @param lblImagenUsuario
	 * @param lblImagenFondo
	 * @param lbluser
	 * @param lblNombreApellidos
	 * @param lblBiografia
	 * @param tablaTweetsUsuario
	 */
	public PanelPerfilUsuario(JLabel lblImagenUsuario, JLabel lblImagenFondo, JLabel lbluser,
			JLabel lblNombreApellidos, JTextArea lblBiografia, TablaTweetsUsuarios tablaTweetsUsuario) {
		super();
		this.lblImagenUsuario = lblImagenUsuario;
		this.lblImagenFondo = lblImagenFondo;
		this.lbluser = lbluser;
		this.lblNombreApellidos = lblNombreApellidos;
		this.lblBiografia = lblBiografia;
		this.tablaTweetsUsuario = tablaTweetsUsuario;
		init();
	}
	
	public PanelPerfilUsuario() {
		// TODO Auto-generated constructor stub
	}

	private void init() {
		setAlignmentY(Component.TOP_ALIGNMENT);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_usuarioGeneral = new JPanel();
		add(panel_usuarioGeneral, BorderLayout.NORTH);
		panel_usuarioGeneral.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_perfilImagen = new JPanel();
		panel_usuarioGeneral.add(panel_perfilImagen, BorderLayout.NORTH);
		
		panel_perfilImagen.add(lblImagenUsuario);
		lblImagenUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenUsuario.setIcon(Util.getImagenRedondeada(new ImageIcon(us.getImagen()), 150));
		
		panel_perfilImagen.add(lblImagenFondo);
		
		JPanel panel_perfilDesc = new JPanel();
		panel_usuarioGeneral.add(panel_perfilDesc, BorderLayout.CENTER);
		panel_perfilDesc.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_descSup = new JPanel(new BorderLayout());
		panel_perfilDesc.add(panel_descSup, BorderLayout.NORTH);
		
		lbluser.setHorizontalAlignment(0);
		lbluser.setFont(Util.getFont("mirda", Font.BOLD, 18));
		panel_descSup.add(lbluser, BorderLayout.NORTH);
		
		lblNombreApellidos.setFont(Util.getFont("mirda", Font.PLAIN, 18));
		lblNombreApellidos.setHorizontalAlignment(0);
		panel_descSup.add(lblNombreApellidos,BorderLayout.SOUTH);
		
		JPanel panel_perfilBio = new JPanel(new BorderLayout());
		panel_perfilDesc.add(panel_perfilBio, BorderLayout.CENTER);
		
		lblBiografia.setRequestFocusEnabled(false);
		lblBiografia.setOpaque(false);
		lblBiografia.setFocusable(false);
		lblBiografia.setBorder(null);
		lblBiografia.setEditable(false);
		lblBiografia.setWrapStyleWord(true);
		lblBiografia.setLineWrap(true);
		lblBiografia.setFont(Util.getFont("mirda", Font.PLAIN, 14));
		lblBiografia.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		lblBiografia.setBorder(new MatteBorder(0, 20, 0, 20, new Color(1.0f,1.0f,1.0f,0.0f)));
		
		panel_perfilBio.add(lblBiografia, BorderLayout.CENTER);
		
		JPanel panel_perfilBtnUnfollow = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_perfilBtnUnfollow.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_perfilDesc.add(panel_perfilBtnUnfollow, BorderLayout.SOUTH);
		
		panel_perfilBtnUnfollow.add(btnDejarDeSeguir);
		
		try {
			if(us.getNombreUsuario().equals(GUIController.getInstance().getUsuarioRegistrado().getNombreUsuario())){
				// Es el usuario registrado
				this.getBtnDejarDeSeguir().setVisible(false);
			} else {
				// Es un usuario ajeno
				this.getBtnDejarDeSeguir().setVisible(true);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel panel_perfilBotonera = new JPanel();
		panel_usuarioGeneral.add(panel_perfilBotonera, BorderLayout.SOUTH);
		panel_perfilBotonera.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_perfilBotonera.add(btnTweets);
		btnTweets.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		panel_perfilBotonera.add(btnFavoritos);
		btnFavoritos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		panel_perfilBotonera.add(btnSeguidores);
		btnSeguidores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		panel_perfilBotonera.add(btnSiguiendo);
		btnSiguiendo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		//Eventos botones usuario
		btnTweets.addMouseListener(new EventoVerContadorPerfilusuario(this, cTweets, EventoVerContadorPerfilusuario.TWEETS) );
		btnFavoritos.addMouseListener(new EventoVerContadorPerfilusuario(this, cFavoritos, EventoVerContadorPerfilusuario.FAVORITOS));
		btnSeguidores.addMouseListener(new EventoVerContadorPerfilusuario(this, cSiguiendo, EventoVerContadorPerfilusuario.SEGUIDORES));
		btnSiguiendo.addMouseListener(new EventoVerContadorPerfilusuario(this, cSeguidores, EventoVerContadorPerfilusuario.SIGUIENDO));

		JScrollPane scrollPaneUsuario = new JScrollPane();
		scrollPaneUsuario.setViewportBorder(null);
		scrollPaneUsuario.setBorder(null);
		scrollPaneUsuario.getVerticalScrollBar().setUnitIncrement(5);

		//se muestra en el scrollpane
		scrollPaneUsuario.setViewportView(tablaTweetsUsuario);
		add(scrollPaneUsuario, BorderLayout.CENTER);
	}

	/**
	 * @return the lblImagenUsuario
	 */
	public Icon getImagenUsuario() {
		return lblImagenUsuario.getIcon();
	}

	/**
	 * @param lblImagenUsuario the lblImagenUsuario to set
	 */
	public void setImagenUsuario(ImageIcon lblImagenUsuario) {
		this.lblImagenUsuario.setIcon(lblImagenUsuario);
	}

	/**
	 * @return the lblImagenFondo
	 */
	public Icon getImagenFondo() {
		return lblImagenFondo.getIcon();
	}

	/**
	 * @param lblImagenFondo the lblImagenFondo to set
	 */
	public void setImagenFondo(ImageIcon lblImagenFondo) {
		this.lblImagenFondo.setIcon(lblImagenFondo);
	}

	/**
	 * @return the lbluser
	 */
	public String getUser() {
		return lbluser.getText();
	}

	/**
	 * @param lbluser the lbluser to set
	 */
	public void setUser(String user) {
		this.lbluser.setText("@"+user);
	}

	/**
	 * @return the lblNombreApellidos
	 */
	public String getNombreApellidos() {
		return lblNombreApellidos.getText();
	}

	/**
	 * @param lblNombreApellidos the lblNombreApellidos to set
	 */
	public void setNombreApellidos(String NombreApellidos) {
		this.lblNombreApellidos.setText(NombreApellidos);
	}

	/**
	 * @return the lblBiografia
	 */
	public String getBiografia() {
		return lblBiografia.getText();
	}

	/**
	 * @param lblBiografia the lblBiografia to set
	 */
	public void setBiografia(String biografia) {
		this.lblBiografia.setText(biografia);
	}

	/**
	 * @return the btnDejarDeSeguir
	 */
	public BotonSeguir getBtnDejarDeSeguir() {
		return btnDejarDeSeguir;
	}

	/**
	 * @param btnDejarDeSeguir the btnDejarDeSeguir to set
	 */
	public void setBtnDejarDeSeguir(BotonSeguir btnDejarDeSeguir) {
		this.btnDejarDeSeguir = btnDejarDeSeguir;
	}

	/**
	 * @return the tablaTweetsUsuario
	 */
	public TablaTweetsUsuarios getTablaTweetsUsuario() {
		return tablaTweetsUsuario;
	}

	/**
	 * @param tablaTweetsUsuario the tablaTweetsUsuario to set
	 */
	public void setTablaTweetsUsuario(TablaTweetsUsuarios tablaTweetsUsuario) {
		this.tablaTweetsUsuario = tablaTweetsUsuario;
	}
	
	public void actualizarTabla(){
		
	}

	public Usuario getUs() {
		return us;
	}

	public void setUs(Usuario us) {
		this.us = us;
	}
	public JLabel getLblImagenUsuario() {
		return lblImagenUsuario;
	}
	public void setLblImagenUsuario(JLabel lblImagenUsuario) {
		this.lblImagenUsuario = lblImagenUsuario;
	}
	public JLabel getLblImagenFondo() {
		return lblImagenFondo;
	}
	public void setLblImagenFondo(JLabel lblImagenFondo) {
		this.lblImagenFondo = lblImagenFondo;
	}
	public JLabel getLbluser() {
		return lbluser;
	}
	public void setLbluser(JLabel lbluser) {
		this.lbluser = lbluser;
	}
	public JLabel getLblNombreApellidos() {
		return lblNombreApellidos;
	}
	public void setLblNombreApellidos(JLabel lblNombreApellidos) {
		this.lblNombreApellidos = lblNombreApellidos;
	}
	public JTextArea getLblBiografia() {
		return lblBiografia;
	}
	public void setLblBiografia(JTextArea lblBiografia) {
		this.lblBiografia = lblBiografia;
	}
	public CoolBlueButton getBtnTweets() {
		return btnTweets;
	}
	public void setBtnTweets(CoolBlueButton btnTweets) {
		this.btnTweets = btnTweets;
	}
	public CoolBlueButton getBtnFavoritos() {
		return btnFavoritos;
	}
	public void setBtnFavoritos(CoolBlueButton btnFavoritos) {
		this.btnFavoritos = btnFavoritos;
	}
	public CoolBlueButton getBtnSeguidores() {
		return btnSeguidores;
	}
	public void setBtnSeguidores(CoolBlueButton btnSeguidores) {
		this.btnSeguidores = btnSeguidores;
	}
	public CoolBlueButton getBtnSiguiendo() {
		return btnSiguiendo;
	}
	public void setBtnSiguiendo(CoolBlueButton btnSiguiendo) {
		this.btnSiguiendo = btnSiguiendo;
	}
	public ArrayList<ObjetoCelda> getListaObjetos() {
		return listaObjetos;
	}
	public void setListaObjetos(ArrayList<ObjetoCelda> listaObjetos) {
		this.listaObjetos = listaObjetos;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Contador getcTweets() {
		return cTweets;
	}
	public void setcTweets(Contador cTweets) {
		this.cTweets = cTweets;
	}
	public Contador getcFavoritos() {
		return cFavoritos;
	}
	public void setcFavoritos(Contador cFavoritos) {
		this.cFavoritos = cFavoritos;
	}
	public Contador getcSiguiendo() {
		return cSiguiendo;
	}
	public void setcSiguiendo(Contador cSiguiendo) {
		this.cSiguiendo = cSiguiendo;
	}
	public Contador getcSeguidores() {
		return cSeguidores;
	}
	public void setcSeguidores(Contador cSeguidores) {
		this.cSeguidores = cSeguidores;
	}
	public static Color getColorFondo() {
		return COLOR_FONDO;
	}

	public HiloTimelineUsuario getHiloTimeline() {
		return hiloTimeline;
	}

	public void setHiloTimeline(HiloTimelineUsuario hiloTimeline) {
		this.hiloTimeline = hiloTimeline;
	}

}
