package com.vpr.base;

import java.time.LocalDate;
import java.util.Date;

import org.bson.types.ObjectId;

public class Coche implements Comparable {
	//Atributos
	private ObjectId _id;
	private String marca;
	private String modelo;
	private float precio;
	private LocalDate fechaCompra;
	private Propietario propietario;
	
	//Constructor
	public Coche() {
		propietario = new Propietario();
		propietario.setNombre("Victor");
		propietario.setApellidos("Padilla");
		propietario.setFechaNacimiento(LocalDate.now());
	}

	
	//Metodos
	public ObjectId getId() {
		return _id;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public float getPrecio() {
		return precio;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public void setId(ObjectId objectId) {
		this._id = objectId;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	
	@Override
	public String toString() {
		return marca+" "+modelo;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Coche))
			return false;
		
		Coche coche = (Coche) obj;
		
		if(marca.equals(coche.getMarca()) && modelo.equals(coche.getModelo()))
			return true;
		
		return false;
	}


	@Override
	public int hashCode() {
		return toString().hashCode();
	}


	@Override
	public int compareTo(Object o) {
		return 0;
	}
	
}
