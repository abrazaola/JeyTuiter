package view.parents;

import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.Util;

@SuppressWarnings("serial")
public abstract class InvisibleJFrame extends JFrame implements Moveable{

	protected JPanel contentPane;
	protected JLabel fondo;
	private String imagenfondo;
	private ImageIcon icono;


	/**
	 * Create the frame.
	 */
	public InvisibleJFrame() {
		setUndecorated(true);
		setType(Type.POPUP);
		setResizable(false);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);

		fondo = new JLabel();

		init();
	}

	/**
	 * Create the frame.
	 */
	public InvisibleJFrame(String imagenFondo) {
		this.imagenfondo=imagenFondo;
		icono = new ImageIcon(InvisibleJFrame.class.getResource(imagenFondo));
		setUndecorated(true);
		setType(Type.POPUP);
		setResizable(false);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, icono.getIconWidth(), icono.getIconHeight());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);

		fondo = new JLabel();
		fondo.setIcon(icono);

		init();
		ajustarImagen();
	}

	private void init() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InvisibleJFrame.class.getResource(Util.APP_ICONO)));
		setBackground(new Color(1.0f,1.0f,1.0f,0.0f)); //Lo hace transparente el ultimo valor es el nivel de transparencia
		fondo.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
	}

	private void ajustarImagen() {
		icono = new ImageIcon(InvisibleJFrame.class.getResource(imagenfondo));
		if(icono!=null){
			fondo.setIcon(icono);
			fondo.setBounds(0, 0, icono.getIconWidth(), icono.getIconHeight());
			setBounds(0, 0, icono.getIconWidth(), icono.getIconHeight());
		}
		setLocationRelativeTo(null);
	}

	/**
	 * @param icono the icono to set
	 */
	public void setImagenFondo(ImageIcon icono) {
		fondo.setIcon(icono);
		this.icono = icono;
	}

	public void setImagenFondo(String ruta){
		setImagenFondo(new ImageIcon(InvisibleJFrame.class.getResource(ruta)));
	}

	/**
	 * @return the icono
	 */
	public ImageIcon getImagenFondo() {
		return icono;
	}


	public void mostrar(int pausar){
		Util.mostrarImagenDifuso(this, pausar);
		setVisible(true);
	}
	@Override
	public void dispose(){
		if(isVisible()){
			Util.ocultarImagenDifuso(this);
		}
		super.dispose();
	}

	@Override
	public Point getInitialClick() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInitialClick(Point initialClick) {
		// TODO Auto-generated method stub

	}

	@Override
	public Point getLastPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLastPosition(Point lastPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDisposeWindow() {
		return true;
	}

	@Override
	public void setImagenIconos(ImageIcon imageIcon) {
		// TODO Auto-generated method stub

	}
}
