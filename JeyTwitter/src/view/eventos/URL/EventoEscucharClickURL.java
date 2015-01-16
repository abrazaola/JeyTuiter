package view.eventos.URL;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import util.InvalidInputException;
import view.elementos.Cache;
import view.elementos.paneles.PanelPerfilUsuario;
import controller.GUIController;


public class EventoEscucharClickURL implements HyperlinkListener {

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			if (Desktop.isDesktopSupported()) {
				try {
					String url = e.getDescription();
					System.out.println(url);
					if(url.startsWith("@"))
						abrirPerfil(url);
					else if(url.startsWith("#"));
					//do something
					else
						abrirHTTP(url);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidInputException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	private void abrirPerfil(final String url) throws InvalidInputException {
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (url != null){
					try {
						String usuarioSinArroba = url.replace("@", "");
						GUIController.getInstance().getGui().mostrarMensaje("Cargando perfil de @"+usuarioSinArroba);
						//Si el usuario existe en la cache 'dinamica' cargar esa version
						PanelPerfilUsuario pCache = Cache.getInstance().getPanelPerfilUsuario(usuarioSinArroba);
						if(pCache!=null){
							GUIController.getInstance().getGui().setPanelActual(pCache);
							GUIController.getInstance().getGui().ocultarMensajeInformativo();
						}
						else{
							//Pedirlo a Twitter
							PanelPerfilUsuario panelUsuario = new PanelPerfilUsuario(GUIController.getInstance().getUsuario(usuarioSinArroba));
							//Guardar panel en cache 'dinamica'
							Cache.getInstance().addPanelUsuario(usuarioSinArroba, panelUsuario);
							panelUsuario.getTablaTweetsUsuario().actualizarAltoFilas();
							GUIController.getInstance().getGui().setPanelActual(panelUsuario);
						}
					} catch (IOException e) {
						System.err.println(e.getMessage());
					}			
				}
			}
		}).start();
	}

	private void abrirHTTP(final String url) throws IOException, URISyntaxException {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Desktop.getDesktop().browse(new URI(url));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		}).start();
	}
}
