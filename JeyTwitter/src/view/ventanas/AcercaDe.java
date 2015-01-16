package view.ventanas;

import view.elementos.URLLabel;
import util.Util;
import view.elementos.botones.BotonId;
import view.eventos.principal.EventoClickImagenEscudo;
import view.parents.CustomJDialogWithBar;
import view.parents.CustomJFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
/**
 * Ventana de acerca de que muestra la infomacion sobre los desarrolladores de la aplicacion
 * @author Sergio Anguita
 */
@SuppressWarnings("serial")
public class AcercaDe extends CustomJDialogWithBar {

	private static final Color COLOR_FONDO = Color.BLACK;
	private static final String TITULO = "Acerca de "+Util.APP_TITULO;
	/**
	 * Metodo main de prueba
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AcercaDe frame = new AcercaDe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Contructor por defecto
	 */
	public AcercaDe() {
		super(315,418); //315,418
		init();
	}

	public AcercaDe(CustomJFrame principal) {
		this();
		setLocationRelativeTo(principal);
	}

	/**
	 * Inicializa los elementos de la ventana
	 */
	private void init() {
		setDisposeWindow(true);
		setAlwaysOnTop(true);
		setTitle(TITULO);
		getMainPanel().setBackground(COLOR_FONDO);
		JLabel lblDev = new JLabel("Desarrollado por:");
		lblDev.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 18));
		lblDev.setForeground(Color.LIGHT_GRAY);
		lblDev.setHorizontalAlignment(SwingConstants.CENTER);
		getMainPanel().add(lblDev, BorderLayout.NORTH);
		//lblUrl = new URLLabel("pagina web de deusto","www.deusto.es");
		URLLabel lblJeytwitterV = new URLLabel(Util.APP_TITULO+" v"+Util.APP_VERSION+" - Licencia GPL3", Util.URL_SVN);
		lblJeytwitterV.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 18));
		lblJeytwitterV.setForeground(Color.ORANGE);
		lblJeytwitterV.setHorizontalAlignment(SwingConstants.CENTER);
		getMainPanel().add(lblJeytwitterV, BorderLayout.SOUTH);
		
		JPanel panelNombresEscudo = new JPanel();
		panelNombresEscudo.setBackground(COLOR_FONDO);
		getMainPanel().add(panelNombresEscudo, BorderLayout.CENTER);
		panelNombresEscudo.setLayout(new BorderLayout(0, 0));
		
		JLabel lblEscudo = new JLabel();
		lblEscudo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEscudo.setIcon(new ImageIcon(AcercaDe.class.getResource("/res/images/letreros/JeyTwitterVersion.png")));
		panelNombresEscudo.add(lblEscudo, BorderLayout.SOUTH);
		lblEscudo.addMouseListener(new EventoClickImagenEscudo(this));
		
		JPanel panelNombres = new JPanel();
		panelNombres.setBackground(COLOR_FONDO);
		panelNombresEscudo.add(panelNombres, BorderLayout.CENTER);
		panelNombres.setLayout(new BorderLayout(0, 0));
		
		BotonId lblAitor = new BotonId("/res/images/letreros/AB_id.png", "/res/images/letreros/tuiterAPI.png");
		panelNombres.add(lblAitor, BorderLayout.NORTH);
		
		BotonId lblRuben = new BotonId("/res/images/letreros/RG_id.png", "/res/images/letreros/SQLiteAPI.png");
		panelNombres.add(lblRuben, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(COLOR_FONDO);
		panelNombres.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		BotonId lblSergioA = new BotonId("/res/images/letreros/SA_id.png", "/res/images/letreros/GUI.png");
		panel.add(lblSergioA, BorderLayout.NORTH);
		
		BotonId lblSergioR = new BotonId("/res/images/letreros/SR_id.png", "/res/images/letreros/ErroryEvent.png");
		panel.add(lblSergioR, BorderLayout.SOUTH);
	}
}
