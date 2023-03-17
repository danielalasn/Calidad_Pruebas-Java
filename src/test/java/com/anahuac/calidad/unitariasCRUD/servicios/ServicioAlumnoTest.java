package com.anahuac.calidad.unitariasCRUD.servicios;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.anahuac.calidad.unitariasCRUD.dao.DAOAlumno;
import com.anahuac.calidad.unitariasCRUD.entidades.Alumno;

public class ServicioAlumnoTest {

	private DAOAlumno dao;
	private ServicioAlumno servicio;
	public HashMap<Integer, Alumno> baseDeDatos;
	
	@Before
	public void setUp() throws Exception {
		dao = Mockito.mock(DAOAlumno.class);
		servicio = new ServicioAlumno(dao);
		baseDeDatos = new HashMap<Integer, Alumno>();
	}

	@Test
	public void createAlumno() {
		
		when(dao.crearAlumno(any(Alumno.class))).thenAnswer(new Answer<Integer>(){
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				int tamAntes = baseDeDatos.size();
				Alumno arg = (Alumno) invocation.getArguments()[0];
				baseDeDatos.put(tamAntes+1, arg);
				return tamAntes+1;
			}
		});
		
		int tamAntes = baseDeDatos.size();
		
		Alumno nuevoAlumno;
		nuevoAlumno = new Alumno(baseDeDatos.size(),"Daniel","Alas", "daniel.alas1609@gmail.com");
		
		servicio.agregarAlumno(nuevoAlumno);
		
		int tamDespues = baseDeDatos.size();
		
		assertThat(tamDespues, is(tamAntes+1));
	}
	
	@Test
	public void eliminarAlumno() {
		Alumno defaultAlumno;
		defaultAlumno = new Alumno(baseDeDatos.size(),"Daniel","Alas", "daniel.alas1609@gmail.com");
		baseDeDatos.put(0, defaultAlumno);
		
		when(dao.borrarAlumno(any(Alumno.class))).thenAnswer(new Answer<Boolean>(){
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				Alumno arg = (Alumno) invocation.getArguments()[0];
				
				int id = arg.getID();
				baseDeDatos.remove(id, arg);
				return true;
			}
		});
		
		int tamAntes = baseDeDatos.size();
		
		servicio.bajaAlumno(defaultAlumno);
		
		int tamDespues = baseDeDatos.size();
		
		assertThat(tamDespues, is(tamAntes-1));
	}
	
	@Test
	public void actualizaEmail() {
		Alumno defaultAlumno;
		defaultAlumno = new Alumno(baseDeDatos.size(),"Daniel","Alas", "daniel.alas1609@gmail.com");
		baseDeDatos.put(0, defaultAlumno);
		
		
		when(dao.actualizarEmail(any(Alumno.class),anyString())).thenAnswer(new Answer<Alumno>(){
			public Alumno answer(InvocationOnMock invocation) throws Throwable {
				Alumno arg = (Alumno) invocation.getArguments()[0];
				String newEmail = (String) invocation.getArguments()[1];
				arg.setEmail(newEmail);
				int id = arg.getID();
				baseDeDatos.put(id, arg);
				return arg;
			}
		});
		
		String nuevoEmail = "nuevoEmail";
		
		
		Alumno nuevoAlumno;
		nuevoAlumno = servicio.actualizarEmail(defaultAlumno, nuevoEmail);
		
		assertThat(nuevoAlumno.getEmail(), is(nuevoEmail));
	}
	
	@Test
	public void consultaLista() {
		Alumno defaultAlumno;
		defaultAlumno = new Alumno(baseDeDatos.size(),"Daniel","Alas", "daniel.alas1609@gmail.com");
		baseDeDatos.put(0, defaultAlumno);
		
		
		when(dao.retrieveAlumno(anyInt())).thenAnswer(new Answer<Alumno>(){
			public Alumno answer(InvocationOnMock invocation) throws Throwable {
				int arg = (int) invocation.getArguments()[0];;
				
				Alumno datosAlumno;
				datosAlumno = baseDeDatos.get(arg);
				
				return datosAlumno;
			}
		});
		
		Alumno alumnoObtenido;
		alumnoObtenido = servicio.consultarDatosAlumno(0);
		
		assertThat(defaultAlumno, is(alumnoObtenido));
	}
}
