package com.vpr.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.github.lgooddatepicker.components.DatePicker;
import com.vpr.base.Coche;
import com.vpr.beans.JBotonesCrud;
import com.vpr.util.Metodos;
import javax.swing.JScrollPane;
import javax.swing.JList;
import com.vpr.beans.JPanelBusqueda;
import com.vpr.holamongo.Modelo;

public class Vista extends JFrame implements FocusListener, ListSelectionListener {
	public JLabel lblMarca;
	public JLabel lblModelo;
	public JLabel lblPrecio;
	public JTextField tfMarca;
	public JLabel lblFechaDeCompra;
	public JTextField tfModelo;
	public JTextField tfPrecio;
	public DatePicker fechaCompra;
	public JBotonesCrud botonesCrud;
	public JPanelBusqueda panelBusqueda;

	
	public Vista() {
		this.setTitle("Coche");
		this.setSize(681, 445);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		lblMarca = new JLabel("Marca");
		lblMarca.setBounds(25, 59, 46, 14);
		getContentPane().add(lblMarca);
		
		lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(25, 94, 46, 14);
		getContentPane().add(lblModelo);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(25, 139, 46, 14);
		getContentPane().add(lblPrecio);
		
		tfMarca = new JTextField();
		tfMarca.setBounds(149, 56, 137, 20);
		getContentPane().add(tfMarca);
		tfMarca.setColumns(10);
		
		lblFechaDeCompra = new JLabel("Fecha de compra");
		lblFechaDeCompra.setBounds(25, 186, 102, 14);
		getContentPane().add(lblFechaDeCompra);
		
		tfModelo = new JTextField();
		tfModelo.setBounds(149, 91, 137, 20);
		getContentPane().add(tfModelo);
		tfModelo.setColumns(10);
		
		tfPrecio = new JTextField();
		tfPrecio.setBounds(149, 136, 137, 20);
		getContentPane().add(tfPrecio);
		tfPrecio.setColumns(10);
		//tfPrecio.addFocusListener(this);
		
		fechaCompra = new DatePicker();
		fechaCompra.setBounds(149, 184, 202, 29);
		getContentPane().add(fechaCompra);
		
		botonesCrud = new JBotonesCrud();
		botonesCrud.setBounds(25, 249, 231, 131);
		getContentPane().add(botonesCrud);
		botonesCrud.btNuevo.setActionCommand("NUEVO");
		botonesCrud.btCancelar.setActionCommand("CANCELAR");
		botonesCrud.btGuardar.setActionCommand("GUARDAR");
		botonesCrud.btModificar.setActionCommand("MODIFICAR");
		botonesCrud.btBorrar.setActionCommand("BORRAR");
		
		panelBusqueda = new JPanelBusqueda();
		panelBusqueda.setBounds(370, 31, 258, 266);
		getContentPane().add(panelBusqueda);
		
		this.setVisible(true);
	}

	//Metodos
	public void addListeners(ActionListener l) {
		botonesCrud.btNuevo.addActionListener(l);
		botonesCrud.btCancelar.addActionListener(l);
		botonesCrud.btGuardar.addActionListener(l);
		botonesCrud.btModificar.addActionListener(l);
		botonesCrud.btBorrar.addActionListener(l);
		panelBusqueda.lista.addListSelectionListener(this);
	}
	
	public void focusGained(FocusEvent e) {
		
	}


	public void focusLost(FocusEvent e) {
		String precio = tfPrecio.getText();
		precio = precio.replace("€", "").replace(",", ".").trim();
		tfPrecio.setText(Metodos.formatMoneda(Float.parseFloat(precio)));
	}
	
	public Coche getSeleccionado() {
		return (Coche) panelBusqueda.getSeleccionado();
	}

	@Override
	public void valueChanged(ListSelectionEvent lse) {
		if(panelBusqueda.lista.getSelectedIndex() == -1)
			return;
		
		Coche coche = (Coche) panelBusqueda.getSeleccionado();
		tfMarca.setText(coche.getMarca());
		tfModelo.setText(coche.getModelo());
		tfPrecio.setText(String.valueOf(coche.getPrecio()));
		fechaCompra.setDate(coche.getFechaCompra());
		
		botonesCrud.btBorrar.setEnabled(true);
		botonesCrud.btModificar.setEnabled(true);
	}
}
