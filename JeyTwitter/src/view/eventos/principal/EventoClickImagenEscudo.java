package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import view.ventanas.AcercaDe;
import view.ventanas.VisorImagen;

public class EventoClickImagenEscudo implements MouseListener {

	private AcercaDe acercaDe;
	public EventoClickImagenEscudo(AcercaDe acercaDe) {
		this.acercaDe = acercaDe;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					new VisorImagen(acercaDe, "/res/images/ppt.jpg").open();
				} catch (FileNotFoundException e) {}
			}
		}).start();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
