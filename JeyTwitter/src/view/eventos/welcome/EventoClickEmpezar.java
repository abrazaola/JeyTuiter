package view.eventos.welcome;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import controller.GUIController;
import util.Util;
import view.parents.CustomJFrame;
import view.ventanas.Bienvenida;

public class EventoClickEmpezar implements MouseListener {

	private CustomJFrame ventana;

	public EventoClickEmpezar(CustomJFrame welcome) {
		ventana = welcome;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("Conectando con Twitter...");
					System.out.println("Pidiendo autorizacion...");
					GUIController.getInstance().solicitarCodigo();
				} catch (Exception e) {
					Util.showError(ventana, "Conexion rechazada", "Imposible conectar con "+e.getMessage(),"Cancelar","Reiniciar");
				}
			}
		}).start();
		desplazarJPanel();
	}

	private void desplazarJPanel() {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				JPanel panel = ( (Bienvenida) ventana ).getPanel(0);
				JPanel panelMostrar = ( (Bienvenida) ventana ).getPanel(1);
				panelMostrar.setVisible(true);
				ventana.getMainPanel().add(panelMostrar);
				int w = panel.getWidth(), h = panel.getHeight();
				for (int i = 0; i < panel.getHeight(); i=i+2) {
					panel.setBounds(0, i, w, h);
					panel.setVisible(true);
					Util.pausar(10);
				}
			}
		}).start();
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
