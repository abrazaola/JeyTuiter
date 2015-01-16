package view.eventos.estadistica;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;

import util.Util;
import view.elementos.paneles.PanelEstadistica;
import controller.GUIController;

public class EventoClickIniciar implements MouseListener {

	//numero de paginas de 199 tuits que descargar
	int numPaginas = 13;
	private PanelEstadistica pe;
	public EventoClickIniciar(PanelEstadistica panelEstadistica) {
		pe = panelEstadistica;
	}

	public void mouseClicked(MouseEvent e) {
			pe.getBtnIniciar().setEnabled(false);
					String ruta = "";
					String texto = pe.getTxt();
					if(texto!=null && texto.length()>0 && GUIController.existeUsuario(texto)){
						//Creamos un JfileChooser para que se abra la ruta donde se guardaran los datos
						//Le decimos tambien que solo seleccione directorios
						JFileChooser fileChooser = new JFileChooser();
						fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						int seleccion = fileChooser.showSaveDialog(null);
						//Si el boton que pincha el usuario es el de aceptar (estan el de aceptar y cancelar, creo)
						if (seleccion == JFileChooser.APPROVE_OPTION){
							//Recoge la ruta
							ruta = fileChooser.getCurrentDirectory().getAbsolutePath();
							//imprime un mensaje ROJO por consola con la ruta
							try {
								//si la ruta no existe
								if(!new File(ruta+"/estadistica_JeyTuiter").exists()){
									new File(ruta+"/estadistica_JeyTuiter").mkdir();
								}

								//si la carpeta del usuario no existe, creala
								if(!new File(ruta+"/estadistica_JeyTuiter/"+texto).exists()){
									new File(ruta+"/estadistica_JeyTuiter/"+texto).mkdir();
								}

								//modifica la ruta de destico donde ya por fin almacena los archivos generados
								pe.setRutaDestino(ruta+"/estadistica_JeyTuiter/"+texto);
								//LLama a GUIcontroller
								GUIController.stalker(pe, texto, numPaginas, ruta+"/estadistica_JeyTuiter/"+texto);
								//Grafica.iniciar(nombre, numPaginas, ruta+"/estadistica_JeyTuiter/"+nombre);
							} catch (Exception e1) {
								//Si algo va mal, lanzamos un mensaje de error
								Util.showError(null, "Error creando las carpetas de las rutas", "", "Cancelar", "Aceptar");
							} 
						}else{
							//dejamos el boton como estaba
							pe.getBtnIniciar().setEnabled(true);

						}
					}else{
						//si la condicion no se cumple, es porque no existe el usuario o no se ha introducido uno en el textfield
						Util.showError(null, "Error", "Nombre de usuario no existe/no especificado", "Cancelar", "Aceptar");
						pe.getBtnIniciar().setEnabled(true);

					}
				}
			

	//funciones de click que no usaremos
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
