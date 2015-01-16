package view.systray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.GUIController;

public class EventoClickAbrirSystray implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Ha clicado en abir");
		GUIController.getInstance().getGui().requestFocus();
	}

}
