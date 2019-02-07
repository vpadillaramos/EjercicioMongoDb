package com.vpr.holamongo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import com.vpr.base.Coche;
import com.vpr.ui.Vista;
import com.vpr.util.Metodos;

public class Controlador implements ActionListener {
	//Atributos
	private Modelo modelo;
	private Vista vista;
	private enum Accion{
		NUEVO, GUARDAR, BORRAR, CANCELAR, MODIFICAR
	}
	private Accion accion;
	private Coche cocheActual;
	
	//Constructor
	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		
		inicio();
	}
	
	//Metodos
	private void inicio() {
		modoEdicion(false);
		modelo.conectar();
		vista.addListeners(this);
		refrescar();
	}
	
	private void refrescar() {
		vista.panelBusqueda.refrescarLista(modelo.getCoches());
	}

	public void actionPerformed(ActionEvent e) {
		switch(Accion.valueOf(e.getActionCommand())) {
		case NUEVO:
			nuevo();
			break;
		case GUARDAR:
			guardar();
			break;
		case BORRAR:
			borrar();
			break;
		case CANCELAR:
			cancelar();
			break;
		case MODIFICAR:
			modificar();
			break;
		}
	}
	
	private void limpiar() {
		vista.tfModelo.setText("");
		vista.tfMarca.setText("");
		vista.tfPrecio.setText("");
		vista.fechaCompra.setText("");
	}
	
	private void modoEdicion(boolean b) {
		if(b) {
			vista.tfModelo.setEditable(b);
			vista.tfMarca.setEditable(b);
			vista.tfPrecio.setEditable(b);
			vista.fechaCompra.setEnabled(b);
			vista.botonesCrud.modoEdicion(b);
			
			vista.tfMarca.requestFocus();
		}
		else {
			vista.tfModelo.setEditable(b);
			vista.tfMarca.setEditable(b);
			vista.tfPrecio.setEditable(b);
			vista.fechaCompra.setEnabled(b);
			vista.botonesCrud.modoEdicion(b);
		}
	}
	
	private void nuevo() {
		accion = Accion.NUEVO;
		limpiar();
		modoEdicion(true);
	}
	
	private void guardar() {
		Coche coche = null;
		
		
		if(accion == Accion.MODIFICAR) {
			cocheActual = vista.getSeleccionado();
		}
		else if(accion == Accion.GUARDAR) {
			coche = new Coche();
		}
		
		
		coche.setMarca(vista.tfMarca.getText());
		coche.setModelo(vista.tfModelo.getText());
		coche.setPrecio(Float.parseFloat(vista.tfPrecio.getText()));
		coche.setFechaCompra(vista.fechaCompra.getDate());
		
		if(accion == Accion.MODIFICAR) {
			modelo.modificar(cocheActual);
			Metodos.mensajeInformacion("Modificado", "Coche modificado correctamente");
		}
		else if(accion == Accion.GUARDAR) {
			coche = new Coche();
			modelo.guardar(coche);
			Metodos.mensajeInformacion("Guardado", "Coche guardado correctamente");
		}
		
		refrescar();
		limpiar();
		modoEdicion(false);
		Metodos.mensajeInformacion("Añadido", "Coche añadido correctamente");
	}
	
	private void borrar() {
		modelo.borrar(vista.getSeleccionado());
		Metodos.mensajeInformacion("Borrado", "Coche borrado correctamente");
		refrescar();
	}
	
	private void cancelar() {
		modoEdicion(false);
	}
	
	private void modificar() {
		accion = Accion.MODIFICAR;
	}
}
