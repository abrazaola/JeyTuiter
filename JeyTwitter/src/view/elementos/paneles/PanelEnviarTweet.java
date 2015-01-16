package view.elementos.paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import util.Util;
import view.elementos.botones.BotonEnviar;
import view.eventos.principal.EventoClickEnviarTweet;
import view.eventos.principal.EventoKeyListenerTweet;
import view.ventanas.Principal;

@SuppressWarnings("serial")
public class PanelEnviarTweet extends JPanel {

	private final Principal ventanaPadre;
	private BotonEnviar btnEnviar;
	private JTextArea txtMensaje;
	private JLabel lblContador;

	public PanelEnviarTweet(Principal ventanaPadre){
		super();
		this.ventanaPadre = ventanaPadre;
		btnEnviar = new BotonEnviar();
		txtMensaje = new JTextArea();
		lblContador = new JLabel("140");
		init();
	}

	private void init() {
		setLayout(new BorderLayout(0, 0));

		btnEnviar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JScrollPane scrollPaneMensaje = new JScrollPane();
		scrollPaneMensaje.setBorder(null);
		scrollPaneMensaje.getVerticalScrollBar().setUnitIncrement(5);

		txtMensaje.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtMensaje.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Escriba su Tweet", TitledBorder.CENTER, TitledBorder.TOP, null, Color.LIGHT_GRAY));
		txtMensaje.setLineWrap(true);
		txtMensaje.setWrapStyleWord(true);
		txtMensaje.setFont(Util.getFont("Roboto-regular", Font.PLAIN, 16));


		lblContador.setBorder(null);
		lblContador.setBackground(Color.WHITE);
		lblContador.setHorizontalAlignment(SwingConstants.CENTER);
		lblContador.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblContador.setBackground(SystemColor.inactiveCaptionBorder);

		add(btnEnviar, BorderLayout.EAST);
		add(lblContador, BorderLayout.SOUTH);
		add(scrollPaneMensaje, BorderLayout.CENTER);
		scrollPaneMensaje.setViewportView(txtMensaje);

		//Eventos
		btnEnviar.addMouseListener(new EventoClickEnviarTweet(this));
		txtMensaje.addKeyListener(new EventoKeyListenerTweet(this));
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
	 * @return the lblContador
	 */
	public String getContador() {
		return lblContador.getText();
	}

	/**
	 * @param lblContador the lblContador to set
	 */
	public void setContador(String lblContador) {
		this.lblContador.setText(lblContador);
	}

	public BotonEnviar getBtnEnviar() {
		return btnEnviar;
	}

	public void setBtnEnviar(BotonEnviar btnEnviar) {
		this.btnEnviar = btnEnviar;
	}

	public JLabel getLblContador() {
		return lblContador;
	}

	public void setLblContador(JLabel lblContador) {
		this.lblContador = lblContador;
	}

	public Principal getVentanaPadre() {
		return ventanaPadre;
	}

}
