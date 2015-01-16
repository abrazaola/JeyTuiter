package view.eventos.botonesGenericos;
/**
 * Evento que controla el cambio de estado del boton binario (on off)
 * @author Sergio Anguita
 */
import view.elementos.botones.BinaryButton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventoClickBinaryButton implements MouseListener {
	
	private final BinaryButton bbuton;

	public EventoClickBinaryButton(BinaryButton binaryButton) {
		bbuton = binaryButton;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		boolean isOn = bbuton.getEstado();
		if(isOn){
			bbuton.setEstado(false);
			bbuton.setIcon(bbuton.getImagenOff());
		}
		else{
			bbuton.setEstado(true);
			bbuton.setIcon(bbuton.getImagenOn());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
