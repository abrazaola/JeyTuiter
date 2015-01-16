package view.elementos.paneles;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import util.Util;
import view.eventos.estadistica.EventoClickIniciar;
import controller.GUIController;

@SuppressWarnings("serial")
public class PanelEstadistica extends JPanel{
	private JTextField txtNombreUsuario;
	private String rutaDestino ="vacio";
	private JButton btnNewButton;
	private JButton btnIniciar;

	public PanelEstadistica() throws IOException{
		setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);

		JScrollPane scrollPaneOpciones = new JScrollPane();
		tabbedPane.addTab("Opciones", null, scrollPaneOpciones, null);

		JPanel panelOpciones = new JPanel();
		scrollPaneOpciones.setViewportView(panelOpciones);
		panelOpciones.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panelOpciones.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		btnIniciar = new JButton("Iniciar");
		panel.add(btnIniciar, BorderLayout.CENTER);

		JLabel lblNombreUsuario = new JLabel("Introduzca el nombre de usuario");
		panelOpciones.add(lblNombreUsuario, BorderLayout.NORTH);

		txtNombreUsuario = new JTextField(GUIController.getInstance().getUsuarioRegistrado().getNombreUsuario());
		scrollPaneOpciones.setColumnHeaderView(txtNombreUsuario);
		txtNombreUsuario.setColumns(10);
		btnIniciar.addMouseListener(new EventoClickIniciar(this));

		JScrollPane scrollPaneResultados = new JScrollPane();
		scrollPaneResultados.getVerticalScrollBar().setUnitIncrement(1);
		tabbedPane.addTab("Resultados", null, scrollPaneResultados, null);

		JPanel panelResultados = new JPanel();
		scrollPaneResultados.setViewportView(panelResultados);
		panelResultados.setLayout(new BorderLayout(0, 0));

		JLabel lblSinResultados = new JLabel();
		lblSinResultados.setHorizontalAlignment(SwingConstants.CENTER);
		panelResultados.add(lblSinResultados, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		panelResultados.add(panel_2, BorderLayout.NORTH);

		JLabel lblAbrirCarpetaDe = new JLabel("Abrir carpeta de destino:");
		panel_2.add(lblAbrirCarpetaDe);

		btnNewButton = new JButton("Abrir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//cuando pulsamos el boton

				//comprobamos que se ha introducido una ruta
				if(rutaDestino=="vacio"){
					Util.showError(null, "error", "Ruta de destino no seleccionada/no existente", "Todo va Jey", "aceptar");
				}else{
					//con este codigo abrimos la carpeta de destino
					File myFile = new File(rutaDestino);
					String path = myFile.getAbsolutePath();
					File dir = new File(path.substring(0, path.lastIndexOf(File.separator)));
					//comprobamos que el escritorio es compatible
					if (Desktop.isDesktopSupported()) {
						try {
							Desktop.getDesktop().open(dir);
						} catch (Exception e1) {
							Util.showError(null, "error", "Lo siento, tu ordenador no es MAC", "Cancelar", "Aceptar");
						}
					}
				}
			}
		});

		panel_2.add(btnNewButton);
	}

	//obtiene el texto del usuario del textField
	public String getTxt(){
		return txtNombreUsuario.getText();
	}

	public JTextField getTxtNombreUsuario() {
		return txtNombreUsuario;
	}

	public void setTxtNombreUsuario(JTextField txtNombreUsuario) {
		this.txtNombreUsuario = txtNombreUsuario;
	}

	public String getRutaDestino() {
		return rutaDestino;
	}

	public void setRutaDestino(String rutaDestino) {
		this.rutaDestino = rutaDestino;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}

	public JButton getBtnIniciar() {
		return btnIniciar;
	}

	public void setBtnIniciar(JButton btnIniciar) {
		this.btnIniciar = btnIniciar;
	}
}
