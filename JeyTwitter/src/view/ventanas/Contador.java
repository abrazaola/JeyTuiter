package view.ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import view.parents.InvisibleJFrame;
/**
 * Ventana que muestra un contador con el numero de seguidores/tweets... de un usuario
 * @author Sergio Anguita
 */
@SuppressWarnings("serial")
public class Contador extends InvisibleJFrame {

	private JLabel cantidad;
	/**
	 * Metodo main de prueba
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contador frame = new Contador(15241789);
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
	public Contador() {
		super("/res/images/ImagenContador.png");
		setType(Type.UTILITY);
		cantidad = new JLabel();
		init();
	}

	/**
	 * 
	 * @param cantidad Numero exacto de seguidores/seguidos/tweets de un usuario
	 */
	public Contador(int cantidad) {
		super("/res/images/ImagenContador.png");
		this.cantidad = new JLabel("");
		setCantidad(cantidad);
		init();
	}

	/**
	 * Asigna a la ventana el numero de seguidores introducido. Ademas se encarga de parsear el numero en caso
	 * de ser superior a 10000 mostrando 15.2K en vez de 152000.
	 * @param valor Numero exacto de seguidores/seguidos/tweets de un usuario
	 */
	public void setCantidad(int valor) {
		if(valor >= 0 && valor < 10000)
			cantidad.setText(String.valueOf(valor));
		else if(valor >= 10000 && valor < 100000){
			String str = String.valueOf(valor);
			String dec = str.substring(0,2);
			String u = str.substring(2,3);
			cantidad.setText(dec+"."+u+"K");
		}
		else if(valor >= 100000 && valor < 1000000){
			String str = String.valueOf(valor);
			String dec = str.substring(0,3);
			String u = str.substring(3,4);
			cantidad.setText(dec+"."+u+"K");
		}
		else if(valor >= 1000000 && valor < 10000000){
			String str = String.valueOf(valor);
			String dec = str.substring(0,1);
			String u = str.substring(1,2);
			cantidad.setText(dec+"."+u+"M");
		}
		else if(valor > 10000000){
			String str = String.valueOf(valor);
			String dec = str.substring(0,2);
			String u = str.substring(2,3);
			cantidad.setText(dec+"."+u+"M");
		}
		else{
			cantidad.setText("-");
		}
	}

	/**
	 * Inicializa los elementos de la ventana
	 */
	public void init(){
		getContentPane().setLayout(null);
		setBounds(0, 0, 113, 59);
		setLocationRelativeTo(null);
		cantidad.setBounds(0, 5, 113, 37);
		cantidad.setFont(new Font("Roboto", Font.PLAIN, 20));
		cantidad.setForeground(Color.WHITE);
		cantidad.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(cantidad);
		getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		contentPane.add(fondo);
	}
}
