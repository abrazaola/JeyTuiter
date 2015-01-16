package view.eventos.celdaTweet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.elementos.GUITweet;
import controller.GUIController;

public class EventoClickBtnReTweet implements MouseListener {

	private final GUITweet t;

	public EventoClickBtnReTweet(GUITweet guiTweet) {
		t = guiTweet;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (t.getBtnRetweet().isEnabled()){
			long codigo = t.getTweet().getCodigo();
			boolean a = t.getBtnRetweet().isClicado();
			if(!a) {
				boolean b = GUIController.getInstance().marcarRetuit(codigo);
				System.out.println("Marcar retuit: "+b);
				System.out.println("Estado del boton: "+a);
				if(b){
					System.out.println("Retuit con éxito");
					t.getBtnRetweet().setClicado(true);
				}

			}
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
