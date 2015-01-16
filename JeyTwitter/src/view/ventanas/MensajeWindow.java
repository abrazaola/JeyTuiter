package view.ventanas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import util.Util;
import view.eventos.barraMenu.EventoDispose;
import view.eventos.barraMenu.EventosDeBarra;
import view.parents.CustomJDialog;
import view.parents.CustomJFrame;
/**
 * Muestra un mensaje pop up al usuario con informacion.
 * @author Sergio Anguita
 *
 */
@SuppressWarnings("serial")
public class MensajeWindow extends CustomJDialog {

	/**
	 * Metodo main de prueba
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MensajeWindow frame = new MensajeWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Establece el fondo de la ventana
	 */
	private void initMensajeWindow(){
		imagenBackground = "/res/images/mensajeVentanaNormal.png";
	}

	/**
	 * Contructor por defecto
	 * @param parent	ventana padre
	 */
	public MensajeWindow(CustomJFrame parent) {
		super();
		initMensajeWindow();
		ventanaPadre = parent;
		iniciarElementos();
		setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
	}
	/**
	 * 
	 * @param parent	ventana padre
	 * @param textoPositivo		texto del boton 'cancelar'	
	 * @param textoNegativo		texto del boton 'aceptar'
	 */
	public MensajeWindow(CustomJFrame parent, String textoPositivo, String textoNegativo){
		super();
		initMensajeWindow();
		ventanaPadre = parent;
		setBotonNegativo(textoNegativo);
		setBotonPositivo(textoPositivo);
		iniciarElementos();
	}

	/**
	 * Contructor por defecto
	 */
	public MensajeWindow() {
		initMensajeWindow();
		setBotonNegativo("Cancelar");
		setBotonPositivo("Aceptar");
		iniciarElementos();
	}

	/**
	 * Inicializa los elementos de la ventana
	 */
	private void iniciarElementos() {

		disposeWindow = true;

		setAlwaysOnTop(true);
		setBounds(100, 100, 443, 177);


		//Construccion de objetos
		JLabel lblFondo = new JLabel();

		//Configuracion de los objetos

		btnAceptar.setLocation(315, 125);
		btnCancelar.setLocation(212, 125);

		lblCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCerrar.setBounds(393, 12, 29, 23);

		lblFondo.setIcon(new ImageIcon(MensajeWindow.class.getResource(imagenBackground)));
		lblFondo.setBounds(0, 0, 443, 174);


		lblMensajeAMostrar.setForeground(Color.DARK_GRAY);
		lblMensajeAMostrar.setFont(Util.getFont("Roboto-Regular", Font.PLAIN, 14));
		lblMensajeAMostrar.setBounds(21, 46, 397, 71);


		lblTitulodeLaVentana.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulodeLaVentana.setBounds(21, 18, 362, 15);
		lblTitulodeLaVentana.setFont(Util.getFont("Roboto-Regular", Font.PLAIN, 14));
		lblTitulodeLaVentana.setForeground(Color.WHITE);

		JLabel lblArrastrar = new JLabel();
		lblArrastrar .setBounds(10, 12, 373, 23);
		lblArrastrar.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));	

		//Listeners
		lblCerrar.addMouseListener(new EventoDispose(this));
		lblArrastrar.addMouseListener(new EventosDeBarra(this));
		lblArrastrar.addMouseMotionListener(new EventosDeBarra(this));

		//Se anyaden al panel principal que tiene layout null
		getContentPane().add(lblArrastrar);	
		getContentPane().add(lblMensajeAMostrar);
		getContentPane().add(lblTitulodeLaVentana);
		getContentPane().add(lblCerrar);
		getContentPane().add(lblFondo);

		setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f)); //Lo hace transparente el ultimo valor es el nivel de transparencia
		setLocationRelativeTo(ventanaPadre);
	}
}
