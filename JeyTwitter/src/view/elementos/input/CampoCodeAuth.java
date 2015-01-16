package view.elementos.input;

import java.awt.Font;

import javax.swing.ImageIcon;

import util.Util;
import view.ventanas.Bienvenida;

@SuppressWarnings("serial")
public class CampoCodeAuth extends InputField{

	public CampoCodeAuth(Bienvenida b){
		super();
		inputCode.setBounds(88, 165, 300, 48);
		inputCode.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 40));
		imagenFondo.setIcon(new ImageIcon(InputField.class.getResource("/res/images/textInput/IntroCodeField_Normal.png")));
		imagenFondo.setBounds(80, 155, 308, 58);

	}

	@Override
	/**
	 * Evalua el contenido del campo de datos introducido por el usuario para satisfacer
	 * la condicion  establecida
	 * @return devuelve true si cumple la condicion. Devuelve false si no la cumple
	 */
	public boolean evaluate() {
		//Este metodo hace que cuando detecta un codigo de 7 digitos 
		//lo establece como correcto
		boolean condicion = getInputField().getText().length()==7 && getInputField().getText().matches("[0-9]+");
		if(condicion)
			setModoCorrecto();
		else
			setModoError();
		return condicion;
	}

	@Override
	/**
	 * Establece los limites del campo de busqueda teniendo en cuenta que la imagen de fondo
	 * y el campo de texto deben estar visualmente alineados
	 */
	public void setBounds(int x, int y, int w, int h){
		super.setBounds(x, y, 308, 58); //300, 48
		inputCode.setBounds(x+10, y+5, 300, 48);
		imagenFondo.setBounds(x, y, 308, 58);
	}
	@Override
	/**
	 * Establece las dimensiones en ancho y alto del campo de busqueda teniendo en cuenta que la imagen de fondo
	 * y el campo de texto deben estar visualmente alineados
	 */
	public void setLocation(int x, int y){
		super.setLocation(x, y);
		inputCode.setLocation(x+10, y+5);
		imagenFondo.setLocation(x, y);
	}
	/**
	 * Establece el campo de busqueda como erroneo y muestra una imagen al usuario advirtiendole del error
	 */
	public void setModoError(){
		getImagenFondo().setIcon(new ImageIcon(InputField.class.getResource("/res/images/textInput/IntroCodeField_Error.png")));
	}
	/**
	 * Establece el campo de busqueda como correcto y muestra una imagen de confirmacion al usuario
	 */
	public void setModoCorrecto(){
		getImagenFondo().setIcon(new ImageIcon(InputField.class.getResource("/res/images/textInput/IntroCodeField_Ok.png")));
	}
	/**
	 * Establece el campo de busqueda en el modo inicial.
	 */
	public void setModoNormal(){
		getImagenFondo().setIcon(new ImageIcon(InputField.class.getResource("/res/images/textInput/IntroCodeField_Normal.png")));
	}
}
