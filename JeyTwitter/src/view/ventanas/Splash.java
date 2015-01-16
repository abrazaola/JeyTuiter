package view.ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import util.Util;
import view.parents.InvisibleJFrame;

@SuppressWarnings("serial")
public class Splash extends InvisibleJFrame {

	//Constantes
	private static final String TITULO = "Iniciando "+Util.APP_TITULO+"...";
	private static final String IMG_SPLASH = "/res/images/Splash.png";
	//solo de prueba
	public static long inicio;
	private JLabel lblIniciando;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Splash frame = new Splash();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Splash() {
		super(IMG_SPLASH);
		inicio = System.currentTimeMillis();
		setTitle(TITULO);
		contentPane.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		contentPane.setLayout(null);
		getContentPane().setLayout(null);

		lblIniciando = new JLabel("Iniciando...");
		lblIniciando.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciando.setBounds(109, 248, 332, 28);
		lblIniciando.setForeground(Color.WHITE);
		lblIniciando.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 14));
		getContentPane().add(lblIniciando);
		contentPane.add(fondo);
	}

	public String getTextoMensaje(){
		return lblIniciando.getText();
	}

	public void setTextoMensaje(String texto){
		lblIniciando.setText(texto);
	}
}
