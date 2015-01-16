package view.models.tablasPrincipal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import util.Util;
import view.elementos.GUITweet;
import view.elementos.GuiTwitterUsuario;
import view.elementos.ObjetoCelda;
import view.renderers.TweetEditor;
import view.renderers.TweetRenderer;
import view.renderers.UsuarioEditor;
import view.renderers.UsuarioRenderer;

@SuppressWarnings("serial")
public class TablaTweetsUsuarios extends JTable {

	//Constantes
	public static final int SOLO_USUARIOS = 0;
	public static final int SOLO_TWEETS = 1;
	public static final int ALTO_FILA = 90;

	private ArrayList<ObjetoCelda> listaObjetos;
	private int tipoTabla;

	public TablaTweetsUsuarios(int tipo) {
		super();
		tipoTabla = tipo;
		init();
	}

	public TablaTweetsUsuarios(ArrayList<ObjetoCelda> objeto) {
		super();
		this.listaObjetos = objeto;
		init();
		actualizarAltoFilas();
	}

	private  void init() {;
	setFocusable(true);
	setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	setTableHeader(null);
	setRowSelectionAllowed(true);
	setShowHorizontalLines(false);
	setShowVerticalLines(false);
	setCellSelectionEnabled(true);
	setShowGrid(false);
	//setRowHeight(ALTO_FILA);
	setFont(Util.getFont("Roboto-Light", Font.PLAIN, 18));
	setBorder(null);
	setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	setFocusTraversalPolicyProvider(true);
	setFocusCycleRoot(true);
	setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	setBackground(Color.BLACK);

	if(listaObjetos!=null && listaObjetos.size()>0){
		actualizarTabla(new ModeloTablaTweetUsuarios(listaObjetos));
	}
	else
		setRenderCelda();
	}

	/**
	 * 
	 */
	private  void actualizarTabla(ModeloTablaTweetUsuarios modeloTabla) {
		setModel(modeloTabla);
		tipoTabla = listaObjetos.get(0).tipoObjeto();
		setRenderCelda();
	}

	private  void setRenderCelda(){
		if(tipoTabla == SOLO_TWEETS){
			setDefaultRenderer(GUITweet.class, new TweetRenderer());
			setDefaultEditor(GUITweet.class, new TweetEditor());
		}
		else if(tipoTabla == SOLO_USUARIOS){
			setDefaultRenderer(GuiTwitterUsuario.class, new UsuarioRenderer());
			setDefaultEditor(GuiTwitterUsuario.class, new UsuarioEditor());
		}
		repaint();
	}

	public  boolean isCellEditable(int row, int column){
		return true;
	}

	public void insertarNuevo(ObjetoCelda o){
		ModeloTablaTweetUsuarios modelo;
		modelo = new ModeloTablaTweetUsuarios(listaObjetos);
		modelo.insertarElemento(o);
		listaObjetos = modelo.getLista();
		actualizarTabla(modelo);
	}

	public  void insertarLista(ArrayList<ObjetoCelda> l) {
		ModeloTablaTweetUsuarios modelo;
		modelo = new ModeloTablaTweetUsuarios(listaObjetos);

		for (ObjetoCelda o : l) {
			modelo.insertarElemento(o);
			actualizarAltoFilas();
		}
		modelo.actualizarContenidoTabla();
		listaObjetos = modelo.getLista();
		actualizarTabla(modelo);
	}

	public  void actualizar(ArrayList<ObjetoCelda> l) {
		ModeloTablaTweetUsuarios modelo;
		l = listaObjetos;
		modelo = new ModeloTablaTweetUsuarios(l);
		modelo.actualizarContenidoTabla();
		actualizarTabla(modelo);
	}

	private void actualizarAltoFila(int fila){
		Component comp = prepareRenderer(getCellRenderer(fila, 0), fila, 0);
		int alto = Math.max(1, comp.getPreferredSize().height);
		setRowHeight(fila, alto+30);
	}

	public void actualizarAltoFilas(){
		for (int row = 0; row < getRowCount(); row++){
			actualizarAltoFila(row);
		}
	}

	public void limpiar() {
		ModeloTablaTweetUsuarios model =  new ModeloTablaTweetUsuarios(new ArrayList<ObjetoCelda>());
		model.clear();
		setModel(model);
	}

	public  ArrayList<ObjetoCelda> getListaObjetos(){
		return listaObjetos;
	}
}
