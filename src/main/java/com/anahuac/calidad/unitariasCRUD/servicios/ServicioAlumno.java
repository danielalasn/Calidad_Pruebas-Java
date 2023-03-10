package com.anahuac.calidad.unitariasCRUD.servicios;

import java.util.List;

import com.anahuac.calidad.unitariasCRUD.dao.DAOAlumno;
import com.anahuac.calidad.unitariasCRUD.entidades.Alumno;

public class ServicioAlumno {
	
	public DAOAlumno dao;
	
	public ServicioAlumno(DAOAlumno dao) {
		this.dao = dao;
	}
	
	public Alumno agregarAlumno(Alumno alumno){
		
		int nuevoAlumnoID = dao.crearAlumno(alumno);
		alumno.setiD(nuevoAlumnoID);
		
		return alumno;
	}
	
	public boolean bajaAlumno(Alumno alumno){
		boolean baja = dao.borrarAlumno(alumno);
		return baja;
	}
	
	public Alumno actualizarEmail(Alumno alumno, String nuevoCorreo){
		Alumno nuevoEmail = dao.actualizarEmail(alumno, nuevoCorreo);
		return nuevoEmail;
		
	}
	public List<Alumno> consultarLista(){
		return dao.retrieveAllAlumnos();
	}
	
	public Alumno consultarDatosAlumno(int id){
		return dao.retrieveAlumno(id);
	}
}
