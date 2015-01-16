package view.elementos.input;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public abstract class InputField extends JLabel{

	protected JTextField inputCode;
	protected JLabel imagenFondo;

	/**
	 * 
	 */
	public InputField() {
		super();
		inputCode = new JTextField();
		imagenFondo = new JLabel();
		init();
	}
	/**
	 * Inicializa el objeto y sus propiedades
	 */
	private void init() {
		inputCode.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		inputCode.setOpaque(false);
		inputCode.setBorder(null);
		inputCode.setAutoscrolls(false);
	}

	/**
	 * @return devuelve un objeto de tipo JTextField
	 */
	public JTextField getInputField() {
		return inputCode;
	}

	/**
	 * @param asigna un objeto de tipo Jtextfield al actual
	 */
	public void setInputField(JTextField inputCode) {
		this.inputCode = inputCode;
	}

	/**
	 * 
	 * @return devuelve el objeto JLabel que tiene la imagen de fondo
	 */
	public JLabel getImagenFondo() {
		return imagenFondo;
	}
	/**
	 * Asigna un nuevo objeto jlabel que se encargara de cambiar la imagen de fondo
	 * @param imagenFondo	JLabel a usar como imagen de fondo
	 */
	public void setImagenFondo(JLabel imagenFondo) {
		this.imagenFondo = imagenFondo;
	}
	/**
	 * Evalua el campo de entrada en base a una condicion definida.
	 * @return devuelve true si el contenido cumple la condicion y false si no la cumple
	 */
	public abstract boolean evaluate();
}
