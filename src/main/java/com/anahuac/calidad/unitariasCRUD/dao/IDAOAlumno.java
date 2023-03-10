package com.anahuac.calidad.unitariasCRUD.dao;

import java.util.List;

import com.anahuac.calidad.unitariasCRUD.entidades.Alumno;

public interface IDAOAlumno {
	public int crearAlumno(Alumno alumno);
		
	public boolean borrarAlumno(Alumno alumno);
	
	public Alumno retrieveAlumno(int iD);
	
	public List <Alumno> retrieveAllAlumnos ();
		
	public Alumno actualizarEmail(Alumno alumno, String email);
	
}
