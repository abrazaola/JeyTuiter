package view.parents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import util.Util;
import view.eventos.barraMenu.EventoClickCerrar;
import view.eventos.barraMenu.EventoClickMinimizar;
import view.eventos.barraMenu.EventoMaximizarDesdeBarra;
import view.eventos.barraMenu.EventosDeBarra;

@SuppressWarnings("serial")
public abstract class CustomJFrame extends JFrame implements Moveable{

	//Constantes
	protected static final int altoBarra = 26;
	private final static int tamBoton = 16;
	private final static String RUTA_ICONO = Util.APP_ICONO;

	private Point initialClick, lastPosition;
	private JLabel lblCerrar;
	private JLabel tituloVentana;
	private JLabel lblMaximizar;
	private JLabel lblMinimizar;
	private JLabel lblImagenFondo;
	private JPanel panelContenido;
	private JPanel panelBarra;
	private JLabel lblBotonesBotonera;
	private boolean disposeWindow;


	/**
	 * Create the frame.
	 */
	public CustomJFrame(int ancho, int alto) {
		Util.asignarLFSO();
		setBounds(0, 0, ancho, alto);
		init();
		initBarra();
	}

	private void init() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CustomJFrame.class.getResource(RUTA_ICONO)));
		//Application.getApplication().setDockIconImage(new ImageIcon("Football.png").getImage());
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelBarra = new JPanel();

		panelContenido = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelBarra, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
				.addComponent(panelContenido, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panelBarra, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelContenido, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))
				);
		panelContenido.setLayout(new BorderLayout(0, 0));
		panelBarra.setLayout(null);
		getContentPane().setLayout(groupLayout);
	}

	private void initBarra() {
		tituloVentana = new JLabel();
		tituloVentana.setHorizontalAlignment(SwingConstants.LEFT); //SwingConstants.CENTER
		tituloVentana.setForeground(Color.WHITE);
		tituloVentana.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		tituloVentana.setBounds(10, 0, getWidth()-70, altoBarra);
		tituloVentana.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 13));
		panelBarra.add(tituloVentana);

		lblCerrar = new JLabel();
		lblCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCerrar.setBounds(getWidth()-24, 3, tamBoton, tamBoton);
		panelBarra.add(lblCerrar);

		lblMaximizar = new JLabel();
		lblMaximizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMaximizar.setBounds(getWidth()-45, 3, tamBoton, tamBoton);
		panelBarra.add(lblMaximizar);

		lblMinimizar = new JLabel();
		lblMinimizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMinimizar.setBounds(getWidth()-67, 3, tamBoton, tamBoton);
		panelBarra.add(lblMinimizar);

		lblBotonesBotonera = new JLabel();
		lblBotonesBotonera.setIcon(new ImageIcon(CustomJFrame.class.getResource("/res/images/botonera/botonesNormales.png")));
		lblBotonesBotonera.setBounds(getWidth()-69, 0, 69, 26);
		panelBarra.add(lblBotonesBotonera);

		lblImagenFondo = new JLabel("");
		lblImagenFondo.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		lblImagenFondo.setIcon(new ImageIcon(CustomJFrame.class.getResource("/res/images/barra.png")));
		lblImagenFondo.setBounds(0, 0, getWidth(), altoBarra);
		lblImagenFondo.setIcon(Util.escalarImagen(lblImagenFondo));
		panelBarra.add(lblImagenFondo);

		//Eventos-Listeners
		lblCerrar.addMouseListener(new EventoClickCerrar(this));
		lblMinimizar.addMouseListener(new EventoClickMinimizar(this));
		lblImagenFondo.addMouseMotionListener(new EventosDeBarra(this));
		lblImagenFondo.addMouseListener(new EventosDeBarra(this));
		addWindowListener(new EventoMaximizarDesdeBarra(this));

		setLocationRelativeTo(null);
	}

	public void mostrar(){
		Util.mostrarImagenDifuso(this);
		setVisible(true);
	}

	public void mostrar(int pausar){
		Util.mostrarImagenDifuso(this, pausar);
		setVisible(true);
	}

	public void cerrar(){
		Util.ocultarImagenDifuso(this);
		setVisible(false);
	}
	@Override
	public void dispose(){
		Util.ocultarImagenDifuso(this);
		setVisible(false);
		super.dispose();
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
		if(lastPosition==null)
			setLocationRelativeTo(null);
		return lastPosition;
	}

	/**
	 * @param lastPosition the lastPosition to set
	 */
	public void setLastPosition(Point lastPosition) {
		this.lastPosition = lastPosition;
	}

	public JPanel getMainPanel() {
		return panelContenido;
	}
	@Override
	public void setTitle(String titulo){
		super.setTitle(titulo);
		tituloVentana.setText(titulo);
	}

	public JLabel getLblCerrar(){
		return lblCerrar;
	}
	public JLabel getLblMaximizar(){
		return lblMaximizar;
	}

	public JLabel getLblImagenFondoBarra() {
		return lblImagenFondo;
	}

	public JLabel getLblMinimizar() {
		return lblMinimizar;
	}

	public void setImagenIconos(ImageIcon icono) {
		lblBotonesBotonera.setIcon(icono);
	}

	/**
	 * @return the disposeWindow
	 */
	public boolean isDisposeWindow() {
		return disposeWindow;
	}

	/**
	 * @param disposeWindow the disposeWindow to set
	 */
	public void setDisposeWindow(boolean disposeWindow) {
		this.disposeWindow = disposeWindow;
	}

}
