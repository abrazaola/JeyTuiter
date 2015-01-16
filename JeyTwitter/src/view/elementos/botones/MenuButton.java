package view.elementos.botones;

import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MenuButton extends BotonUI{

	public MenuButton(String texto){
		super(texto);
		init();
	}

	public MenuButton(){
		super();
		init();
	}

	/**
	 * Inicializa los elementos
	 */
	private void init() {
		anchoBoton = 224;
		altoBoton = 44;

		setImagenClick("/res/botones/elementoMenuClick.png");
		setImagenHover("/res/botones/elementoMenuHover.png");
		setImagenNormal("/res/botones/elementoMenuNormal.png");

		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setHorizontalAlignment(SwingConstants.CENTER);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setIcon(new ImageIcon(MenuButton.class.getResource(getImagenNormal())));
		setSize(anchoBoton, altoBoton);
	}
}
