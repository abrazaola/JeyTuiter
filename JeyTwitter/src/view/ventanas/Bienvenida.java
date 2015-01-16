package view.ventanas;

import util.Util;
import view.elementos.botones.BotonUI;
import view.elementos.input.CampoCodeAuth;
import view.eventos.welcome.EventoClickEmpezar;
import view.eventos.welcome.EventoWelcomeContinuar;
import view.parents.CustomJFrame;

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class Bienvenida extends CustomJFrame {

	private JPanel panelCero;
	private JPanel panelUno;
	private CampoCodeAuth codeField;
	private BotonUI lblOK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bienvenida frame = new Bienvenida();
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
	public Bienvenida() {
		super(468, 329);
		init();
	}

	public void init(){

		getMainPanel().setBorder(null);
		getMainPanel().setBackground(Color.DARK_GRAY);
		setDisposeWindow(false);
		setTitle("Bienvenido a "+Util.APP_TITULO);
		getMainPanel().setLayout(null);
		
		panelUno = new JPanel();
		panelUno.setBorder(null);
		panelUno.setBackground(Color.BLACK);
		panelUno.setBounds(0, 0, 468, 304);
		panelUno.setVisible(false);
		panelUno.setLayout(null);
		getMainPanel().add(panelUno);
		
		codeField = new CampoCodeAuth(this);
		codeField.setBounds(80, 156, 308, 58);
		panelUno.add(codeField.getInputField());
		panelUno.add(codeField.getImagenFondo());
		panelUno.add(codeField);

		JLabel lblAuthCodeTexto = new JLabel("");
		lblAuthCodeTexto.setIcon(new ImageIcon(Bienvenida.class.getResource("/res/images/authCode_text.png")));
		lblAuthCodeTexto.setBounds(70, 19, 332, 109);
		panelUno.add(lblAuthCodeTexto);

		BotonUI lblContinuar = new BotonUI("Continuar");
		lblContinuar.setFont(Util.getFont("Roboto-Regular", Font.PLAIN, 16));
		lblContinuar.setBounds(151, 240, 164, 43);
		panelUno.add(lblContinuar);
		lblContinuar.addMouseListener(new EventoWelcomeContinuar(this));

		panelCero = new JPanel();
		panelCero.setBorder(null);
		panelCero.setBackground(Color.BLACK);
		panelCero.setBounds(0, 0, 468, 304);
		panelCero.setLayout(null);
		getMainPanel().add(panelCero);

		JLabel lblTextoBienvenida = new JLabel("");
		lblTextoBienvenida.setIcon(new ImageIcon(Bienvenida.class.getResource("/res/images/textoBienvenida.png")));
		lblTextoBienvenida.setBounds(21, 129, 406, 94);
		panelCero.add(lblTextoBienvenida);

		lblOK = new BotonUI("Empezar");
		panelCero.add(lblOK);
		lblOK.setBounds(160, 242, 164, 43);
		lblOK.addMouseListener(new EventoClickEmpezar(this));

		JLabel lblWelcome = new JLabel("Bienvenido");
		panelCero.add(lblWelcome);
		lblWelcome.setBounds(21, 45, 257, 65);
		lblWelcome.setFont(Util.getFont("trebuc", Font.PLAIN, 50));
		lblWelcome.setForeground(Color.LIGHT_GRAY);

		final JLabel lblBackimg = new JLabel();
		panelCero.add(lblBackimg);
		lblBackimg.setBounds(0, 0, 468, 301);
		lblBackimg.setLabelFor(lblBackimg);
		ImageIcon imagen = new ImageIcon(Bienvenida.class.getResource("/res/images/bg_welcome.png"));
		lblBackimg.setIcon(imagen);

		setLocationRelativeTo(null);
	}

	public JPanel getPanel(int numero){
		//Sustituir los paneles por un array de paneles
		//return panel[i];
		if(numero==1){
			return panelUno;
		}
		else if(numero == 0){
			return panelCero;
		}
		else return null;
	}
	
	/**
	* @return devuelve el codigo de autorizacion introducido por el usuario
	*/
	public String getCodigo(){
		return codeField.getInputField().getText();
	}
	
	/**
	* Establece el codigo de autorizacion
	* @param code codigo de autorizacion a introducir
	*/
	public void setCodigo(String code){
		codeField.setText(code);
	}

	public BotonUI getLblOK() {
		return lblOK;
	}

	public void setLblOK(BotonUI lblOK) {
		this.lblOK = lblOK;
	}

	public CampoCodeAuth getCodeField() {
		return codeField;
	}

	public void setCodeField(CampoCodeAuth codeField) {
		this.codeField = codeField;
	}
}
