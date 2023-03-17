package com.anahuac.calidad.danielAlas;

import com.anahuac.calidad.unitariasCRUD.dao.DAOAlumnoMySQL;
import com.anahuac.calidad.unitariasCRUD.entidades.Alumno;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	DAOAlumnoMySQL dao = new DAOAlumnoMySQL();
    	Alumno alumno = new Alumno("Daniel", "Alas", "daniel@gmail.com");
        System.out.println( "Daniel Alas Prueba!" );
        dao.crearAlumno(alumno);
    }
}
