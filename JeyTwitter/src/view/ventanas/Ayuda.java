package view.ventanas;

import util.Util;
import view.parents.CustomJDialogWithBar;

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.JScrollPane;

import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class Ayuda extends CustomJDialogWithBar {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ayuda frame = new Ayuda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ayuda() {
		super(600, 700);
		init();
	}

	public void init(){

		getMainPanel().setBorder(null);
		getMainPanel().setBackground(Color.DARK_GRAY);
		getMainPanel().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		getMainPanel().add(scrollPane, BorderLayout.CENTER);
		
		JLabel lblImagenAyuda = new JLabel();
		lblImagenAyuda.setIcon(new ImageIcon(Ayuda.class.getResource("/res/images/ayuda.png")));
		scrollPane.setViewportView(lblImagenAyuda);
		setDisposeWindow(true);
		setTitle("Ayuda de "+Util.APP_TITULO);
		setLocationRelativeTo(null);
	}
}
