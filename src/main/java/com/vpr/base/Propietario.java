package com.vpr.base;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Propietario implements Comparable {
	//Atributos
	private Object _id;
	private String nombre;
	private String apellidos;
	private LocalDate fechaNacimiento;
	private Set<Coche> coches;
	
	//Constructor
	public Propietario() {
		coches = new HashSet<>();
	}
	
	
	//Metodos
	public Object get_id() {
		return _id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public Set<Coche> getCoches() {
		return coches;
	}

	public void set_id(Object _id) {
		this._id = _id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setCoches(Set<Coche> coches) {
		this.coches = coches;
	}


	@Override
	public String toString() {
		return nombre + " " + apellidos;
	}


	@Override
	public boolean equals(Object obj) {
		
		if(!(obj instanceof Propietario)) //si obj no es un objeto tipo Propietario
			return false;
		
		Propietario propietario = (Propietario) obj;
	
		if(nombre.equals(propietario.getNombre()) && apellidos.equals(propietario.getApellidos()))
			return true;
		
		return false;
	}


	//Devuelve un identificador unico, en este caso
	//devolvera el mismo identificador si su nombre y apellidos son
	//los mismos
	@Override
	public int hashCode() {
		return toString().hashCode();
	}


	@Override
	public int compareTo(Object obj) {
		return 0;
	}
}
