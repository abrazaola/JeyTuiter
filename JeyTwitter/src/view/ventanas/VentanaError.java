package view.ventanas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import util.Util;
import view.eventos.barraMenu.EventoDispose;
import view.eventos.barraMenu.EventosDeBarra;
import view.parents.CustomJDialog;

@SuppressWarnings("serial")
public class VentanaError extends CustomJDialog {

	/**
	 * @param lblCancelar
	 * @param lblAceptar
	 * @param lblMensajeAMostrar
	 * @param lblTitulodeLaVentana
	 */

	private String botonRojo;
	private String botonBlanco;
	private String lblMensajeAMostrar0;
	private String lblTitulodeLaVentana0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaError frame = new VentanaError(null, "Error desconocido","Se ha producido un error critico","Cancelar","Reiniciar");
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
	public VentanaError(Component parent, String lblTitulodeLaVentana, String lblMensajeAMostrar, String lblBotonBlanco, String lblBotonRojo) {
		this.botonRojo = lblBotonRojo;
		this.botonBlanco = lblBotonBlanco;
		this.lblMensajeAMostrar0 = lblMensajeAMostrar;
		this.lblTitulodeLaVentana0 = lblTitulodeLaVentana;
		ventanaPadre = parent;
		init();
	}

	/**
	 * Create the frame.
	 */
	public VentanaError() {
		init();
	}

	private void init() {

		disposeWindow = true;

		JLabel lblImagenFondo = new JLabel("");
		ImageIcon imagen = new ImageIcon(VentanaError.class.getResource("/res/images/JeyTwitterError.png"));
		lblImagenFondo.setBounds(0, 0, imagen.getIconWidth(), imagen.getIconHeight());
		lblImagenFondo.setIcon(imagen);
		setBounds(100, 100, imagen.getIconWidth(), imagen.getIconHeight());
		getContentPane().setLayout(null);
		JLabel lblAceptar = new JLabel("");
		JLabel lblCancelar = new JLabel("");

		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);

		getContentPane().add(lblCerrar);
		getContentPane().add(lblAceptar);
		getContentPane().add(lblCancelar);
		getContentPane().add(lblMensajeAMostrar);
		getContentPane().add(lblTitulodeLaVentana);
		getContentPane().add(lblImagenFondo);

		lblCancelar.setText(botonBlanco);
		lblAceptar.setText(botonRojo);
		lblMensajeAMostrar.setText(lblMensajeAMostrar0);
		lblTitulodeLaVentana.setText(lblTitulodeLaVentana0);

		lblCancelar.setBounds(28, 120, 89, 32);
		lblCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCancelar.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 14));
		lblCancelar.setForeground(Color.BLACK);
		lblCancelar.setHorizontalAlignment(SwingConstants.CENTER);

		lblAceptar.setBounds(280, 122, 89, 30);
		lblAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAceptar.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 14));
		lblAceptar.setForeground(Color.WHITE);
		lblAceptar.setHorizontalAlignment(SwingConstants.CENTER);

		lblTitulodeLaVentana.setBounds(49, 19, 299, 20);
		lblTitulodeLaVentana.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		lblTitulodeLaVentana.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 14));
		lblTitulodeLaVentana.setForeground(Color.WHITE);

		lblMensajeAMostrar.setBounds(27, 52, 340, 60);
		lblMensajeAMostrar.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 14));
		lblMensajeAMostrar.setForeground(Color.DARK_GRAY);

		lblCerrar.setBounds(361, 13, 21, 29);
		lblCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		//Listeners
		lblCerrar.addMouseListener(new EventoDispose(this));
		lblTitulodeLaVentana.addMouseListener(new EventosDeBarra(this));
		lblTitulodeLaVentana.addMouseMotionListener(new EventosDeBarra(this));

		getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		setBackground(new Color(1.0f,1.0f,1.0f,0.0f));

		lblCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				estado = false;
				dispose();
			}
		});

		lblAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				estado = true;
				dispose();
			}
		});

		setLocationRelativeTo(ventanaPadre);
	}
}
