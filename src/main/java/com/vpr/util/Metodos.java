package com.vpr.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JOptionPane;

public class Metodos {
	public static Date toDate(LocalDate fecha) {
		return new Date(java.sql.Date.valueOf(fecha).getTime());
	}
	
	public static LocalDate toLocalDate(Date fecha) {
		return new java.sql.Date(fecha.getTime()).toLocalDate();
	}
	
	public static String formatMoneda(float cantidad) {
		DecimalFormat df = new DecimalFormat("0,## €");
		return df.format(cantidad);
	}
	
	public static float parseMoneda(String cantidad) throws ParseException {
		DecimalFormat df = new DecimalFormat("0,## €");
		return df.parse(cantidad).floatValue();
	}
	
	public static void mensajeInformacion(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void mensajeError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
}
