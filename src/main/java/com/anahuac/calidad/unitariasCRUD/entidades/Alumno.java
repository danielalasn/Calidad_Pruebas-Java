package com.anahuac.calidad.unitariasCRUD.entidades;

public class Alumno {
	private int iD;
	private String nombre;
	private String apellido;
	private String email;
	
	public int getID() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Alumno(int iD, String nombre, String apellido, String email) {
		super();
		this.iD = iD;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}
	
	public Alumno(String nombre, String apellido, String email) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}
	
}
