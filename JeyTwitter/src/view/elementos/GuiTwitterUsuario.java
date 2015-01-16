package view.elementos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

import model.Tweet;
import util.Util;
import view.elementos.botones.BotonSeguir;
import view.models.tablasPrincipal.TablaTweetsUsuarios;
import controller.GUIController;

@SuppressWarnings("serial")
public class GuiTwitterUsuario extends JPanel implements ObjetoCelda{

	private static final int ALTO = 70;
	private static final int REDONDEO = 15;

	private JLabel lblImagenUsuario;
	private BotonSeguir btnSeguir;
	private JLabel lblNombreReal;
	private JTextArea txtrBiografia;
	private JLabel lblusuario;
	private boolean siguiendo;

	public GuiTwitterUsuario(){
		super();
		lblNombreReal = new JLabel();
		txtrBiografia = new JTextArea();
		lblusuario = new JLabel();
		lblImagenUsuario = new JLabel();
		siguiendo = false;
		init();
	}

	public GuiTwitterUsuario(String name, String screenName, ImageIcon img, String description, boolean b) {
		super();
		lblImagenUsuario = new JLabel();
		lblImagenUsuario.setSize(ALTO, ALTO);
		lblNombreReal = new JLabel(name);
		txtrBiografia = new JTextArea(description);
		lblusuario = new JLabel(screenName);
		siguiendo = b;
		init();
		setImagenUsuario(img);
	}

	private void setImagenUsuario(ImageIcon img) {
		ImageIcon imagenCache = Cache.getInstance().getImagenesUsuario(getNombreUsuario());
		if(imagenCache!=null){
			lblImagenUsuario.setIcon(imagenCache);
		}
		else{
			lblImagenUsuario.setIcon(Util.getImagenRedondeada(img, REDONDEO));
			ImageIcon imagen = Util.escalarImagen(lblImagenUsuario);
			lblImagenUsuario.setIcon(imagen);
			Cache.getInstance().addImagenesUsuario(getNombreUsuario(), imagen);
		}
	}

	private void init() {
		//setBounds(10, 11, 476, 110);
		setLayout(new BorderLayout(0, 0));

		JPanel panelmagen = new JPanel();
		add(panelmagen, BorderLayout.WEST);
		panelmagen.setLayout(new BorderLayout(0, 0));
		panelmagen.add(lblImagenUsuario, BorderLayout.CENTER);

		JPanel panelTexto = new JPanel();
		add(panelTexto, BorderLayout.CENTER);
		panelTexto.setLayout(new BorderLayout(0, 0));

		JPanel panel_inferior = new JPanel();
		panelTexto.add(panel_inferior, BorderLayout.SOUTH);
		panel_inferior.setLayout(new BorderLayout(0, 0));

		JPanel panel_Superior = new JPanel();
		panelTexto.add(panel_Superior, BorderLayout.NORTH);
		panel_Superior.setLayout(new BorderLayout());

		JPanel panel_supDer = new JPanel();
		panel_Superior.add(panel_supDer, BorderLayout.EAST);
		panel_supDer.setLayout(new BorderLayout(0, 0));
		boolean esMismoUsuario = false;
		try {
			esMismoUsuario = lblusuario.getText().equals(GUIController.getInstance().getUsuarioRegistrado().getNombreUsuario());
		} catch (IOException e) {}

		if(!esMismoUsuario){
			btnSeguir = new BotonSeguir(lblusuario.getText());
			btnSeguir.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(1.0f,1.0f,1.0f,0.0f)));
			btnSeguir.setSiguiendo(siguiendo);
			panel_supDer.add(btnSeguir, BorderLayout.CENTER);
		}

		JPanel panel_supIzq = new JPanel();
		panel_Superior.add(panel_supIzq, BorderLayout.WEST);
		panel_supIzq.setLayout(new BorderLayout(0, 0));


		lblusuario.setBorder(new MatteBorder(4, 4, 4, 0, (Color) new Color(1.0f,1.0f,1.0f,0.0f)));
		lblusuario.setFont(Util.getFont("mirda", Font.BOLD, 14));
		panel_supIzq.add(lblusuario, BorderLayout.NORTH);

		lblNombreReal.setBorder(new MatteBorder(0, 4, 4, 0, (Color) new Color(1.0f,1.0f,1.0f,0.0f)));
		lblNombreReal.setFont(Util.getFont("mirda", Font.PLAIN, 14));
		panel_supIzq.add(lblNombreReal, BorderLayout.SOUTH);


		txtrBiografia.setBorder(new MatteBorder(0,5,5,0, new Color(1.0f,1.0f,1.0f,0.0f)));
		txtrBiografia.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		txtrBiografia.setOpaque(false);
		txtrBiografia.setEditable(false);
		txtrBiografia.setWrapStyleWord(true);
		txtrBiografia.setLineWrap(true);
		txtrBiografia.setFocusable(false);
		txtrBiografia.setFont(Util.getFont("mirda", Font.PLAIN, 12));
		panelTexto.add(txtrBiografia, BorderLayout.CENTER);
	}

	@Override
	public int tipoObjeto() {
		return TablaTweetsUsuarios.SOLO_USUARIOS;
	}

	@Override
	public String getNombreReal() {
		return lblNombreReal.getText();
	}

	@Override
	public String getNombreUsuario() {
		return lblusuario.getText();
	}

	@Override
	public Icon getImagenUsuario() {
		return lblImagenUsuario.getIcon();
	}

	@Override
	public String getMensaje() {
		return txtrBiografia.getText();
	}

	@Override
	public String getTiempo() {
		return "";
	}


	public JLabel getLblImagenUsuario() {
		return lblImagenUsuario;
	}


	public void setLblImagenUsuario(JLabel lblImagenUsuario) {
		this.lblImagenUsuario = lblImagenUsuario;
	}


	public BotonSeguir getBtnSeguir() {
		return btnSeguir;
	}


	public void setBtnSeguir(BotonSeguir btnSeguir) {
		this.btnSeguir = btnSeguir;
	}


	public JLabel getLblNombreReal() {
		return lblNombreReal;
	}


	public void setLblNombreReal(JLabel lblNombreReal) {
		this.lblNombreReal = lblNombreReal;
	}


	public JTextArea getTxtrBiografia() {
		return txtrBiografia;
	}


	public void setTxtrBiografia(JTextArea txtrBiografia) {
		this.txtrBiografia = txtrBiografia;
	}


	public JLabel getLblusuario() {
		return lblusuario;
	}


	public void setLblusuario(JLabel lblusuario) {
		this.lblusuario = lblusuario;
	}

	@Override
	public Tweet getTweet() {
		// TODO Auto-generated method stub
		return null;
	}






}
