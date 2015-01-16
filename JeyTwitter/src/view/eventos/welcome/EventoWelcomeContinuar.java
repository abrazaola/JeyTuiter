package view.eventos.welcome;

import hilos.HiloCargarDatos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.ventanas.Bienvenida;

public class EventoWelcomeContinuar implements MouseListener {

	private Bienvenida ventana;
	public EventoWelcomeContinuar(Bienvenida welcome) {
		ventana = welcome;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		ventana.getLblOK().setEnabled(false);
		ventana.getLblOK().setVisible(false);
		new HiloCargarDatos(ventana).start();
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
