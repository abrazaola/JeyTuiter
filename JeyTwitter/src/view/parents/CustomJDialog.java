package view.parents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.Util;
import view.elementos.botones.CoolBlueButton;


@SuppressWarnings("serial")
public abstract class CustomJDialog extends JDialog implements Moveable{

	private static final String TITULO = Util.APP_TITULO;

	protected Boolean estado = null;
	private JPanel contentPane;
	private Point initialClick, lastPosition;

	protected CoolBlueButton btnCancelar;
	protected CoolBlueButton btnAceptar;
	protected String imagenBackground;
	protected JLabel lblMensajeAMostrar;
	protected JLabel lblTitulodeLaVentana;
	protected JLabel lblCerrar;
	protected Component ventanaPadre;

	protected boolean disposeWindow;

	public CustomJDialog() {
		// TODO Auto-generated constructor stub
		super();
		Util.asignarLFSO();
		btnCancelar = new CoolBlueButton();
		btnAceptar = new CoolBlueButton();
		lblMensajeAMostrar = new JLabel();
		lblTitulodeLaVentana = new JLabel();
		lblCerrar = new JLabel();

		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CustomJFrame.class.getResource(Util.APP_ICONO)));
		//Application.getApplication().setDockIconImage(new ImageIcon("Football.png").getImage());
		setUndecorated(true);
		setResizable(false);
		setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		setTitle(TITULO);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		getContentPane().add(btnCancelar);
		getContentPane().add(btnAceptar);

		//Eventos

		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				estado = false;
				dispose();
			}
		});

		lblCerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});

		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				estado = true;
				dispose();
			}
		});
	}

	public CustomJDialog(int w, int h) {
		this();
		setBounds(0, 0, w, h);
	}

	public JPanel getMainPanel(){
		return contentPane;
	}

	/**
	 * @return the initialClick
	 */
	public Point getInitialClick() {
		return initialClick;
	}

	/**
	 * @param initialClick the initialClick to set
	 */
	public void setInitialClick(Point initialClick) {
		this.initialClick = initialClick;
	}

	/**
	 * @return the lastPosition
	 */
	public Point getLastPosition() {
		return lastPosition;
	}

	/**
	 * @param lastPosition the lastPosition to set
	 */

	public void setLastPosition(Point lastPosition) {
		this.lastPosition = lastPosition;
	}

	public Boolean getEstado(){
		if(estado==null)
			return null;
		return estado.booleanValue();
	}

	/**
	 * @return the tituloVentana
	 */
	public String getTituloVentana() {
		return lblTitulodeLaVentana.getText();
	}

	/**
	 * @param tituloVentana the tituloVentana to set
	 */
	public void setTituloVentana(String tituloVentana) {
		lblTitulodeLaVentana.setText(tituloVentana);
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return lblMensajeAMostrar.getText();
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		lblMensajeAMostrar.setText(mensaje);
	}

	/**
	 * @return the botonPositivo
	 */
	public String getBotonPositivo() {
		return btnAceptar.getText();
	}

	/**
	 * @param botonPositivo the botonPositivo to set
	 */
	public void setBotonPositivo(String botonPositivo) {
		btnAceptar.setText(botonPositivo);
	}

	/**
	 * @return the botonNegativo
	 */
	public String getBotonNegativo() {
		return btnCancelar.getText();
	}

	/**
	 * @param botonNegativo the botonNegativo to set
	 */
	public void setBotonNegativo(String botonNegativo) {
		btnCancelar.setText(botonNegativo);
	}

	@Override
	public boolean isDisposeWindow() {
		return true;
	}
	@Override
	public void setImagenIconos(ImageIcon imageIcon) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void setExtendedState(int iconified) {
		// TODO Auto-generated method stub

	}
}
