package view.systray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoClickCerrarSystray implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Ha clicado en cerrar");
		System.exit(0);
	}

}
