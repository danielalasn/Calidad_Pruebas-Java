package com.anahuac.calidad.integracion;

import java.io.File;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.anahuac.calidad.unitariasCRUD.dao.DAOAlumnoMySQL;
import com.anahuac.calidad.unitariasCRUD.entidades.Alumno;

public class DAOAlumnoTest extends DBTestCase{

	private DAOAlumnoMySQL daoMySql;
	
	public DAOAlumnoTest() {
		  System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,"com.mysql.cj.jdbc.Driver");
		  System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://127.0.0.1:3307/calidad");
		  System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,"root");
		  System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,"123456"); 
		 }
	
	protected IDataSet getDataSet() throws Exception {
		  // TODO Auto-generated method stub
		  return new FlatXmlDataSetBuilder().build(new File("src/resources/iniDB.xml"));
		 } 
	
	@Before
	 public void setUp() throws Exception {
	  // Initialize DAO
	  daoMySql = new DAOAlumnoMySQL(); 
	  // Set the initial condition of the database
	  IDatabaseConnection connection = getConnection(); 
	  try {
	   DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
	  } catch(Exception e) {
	   fail("Error in setup: "+ e.getMessage()); 
	  } finally {
	   connection.close(); 
	  }
	 }

	@After
	public void tearDown() throws Exception {
	}

	@Test
	 public void testAgregarAlumno() {
	
		daoMySql.resetSequencia();
	  Alumno alumno = new Alumno("alumno4", "apellido4", "hola4@hola.com");
	  
	  daoMySql.crearAlumno(alumno);
	  
	  // Verify data in database
	  try {
	   // This is the full database
	   IDataSet databaseDataSet = getConnection().createDataSet(); 
	   
	   ITable actualTable = databaseDataSet.getTable("Alumno");
	   
	   // Read XML with the expected result
	   IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/insert_result.xml"));
	   ITable expectedTable = expectedDataSet.getTable("Alumno");
	   
	   Assertion.assertEquals(expectedTable, actualTable);
	   
	   
	  } catch (Exception e) {
	   // TODO: handle exception
	   fail("Error in insert test: " + e.getMessage());
	  } 
	 }
	
	@Test
	 public void testAgregarAlumnoConteo() {
	  // Verify data in database
	  try {
	   // This is the full database
	  Alumno alumno = new Alumno("alumno4", "apellido4", "hola4@hola.com");
	  daoMySql.crearAlumno(alumno);
	 
	  IDataSet databaseDataSet = getConnection().createDataSet(); 
	  ITable actualTable = databaseDataSet.getTable("Alumno");
	  int numFilas = actualTable.getRowCount();
	  
	   // Read XML with the expected result
	   
	   assertThat(numFilas, is(4));
	   
	   
	  } catch (Exception e) {
	   // TODO: handle exception
	   fail("Error in insert test: " + e.getMessage());
	  } 
	 }
	
	@Test
	public void testBorrarAlumno() {
		Alumno alumno = new Alumno(3, "alumno3", "apellido3", "hola3@hola.com");
		
		daoMySql.borrarAlumno(alumno);
		
		// Verify data in database
		try {
			// This is the full database
			IDataSet databaseDataSet = getConnection().createDataSet(); 
			
			ITable actualTable = databaseDataSet.getTable("Alumno");
			
			// Read XML with the expected result
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/delete_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("Alumno");
			
			Assertion.assertEquals(expectedTable, actualTable);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in delete test: " + e.getMessage());
		}	
	}
	
	@Test
	public void testUpdateEmail() {
		Alumno alumno = new Alumno(1, "alumno1", "apellido1", "hola1@hola.com");
		
		String nuevoCorreo = "emailUpdated@gmail.com";
		// Set the change in the email field
		alumno.setEmail(nuevoCorreo);
		// call update method
		daoMySql.actualizarEmail(alumno, nuevoCorreo);
		
		// Verify data in database
		try {
			// This is the full database
			IDataSet databaseDataSet = getConnection().createDataSet(); 
			
			ITable actualTable = databaseDataSet.getTable("Alumno");
			
			// Read XML with the expected result
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/update_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("Alumno");
			
			Assertion.assertEquals(expectedTable, actualTable);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in update email test: " + e.getMessage());
		}	
	} 
	
	@Test
	public void testRetrieveAlumno() {
//		Alumno alumno = new Alumno(1, "alumno1", "apellido1", "hola1@hola.com");
		
		// call update method
		daoMySql.retrieveAlumno(1);
		
		// Verify data in database
		try {
			// This is the full database
			
		
			ITable actualTable = getConnection().createQueryTable("Alumno", "SELECT * from Alumno WHERE id = 1");
			
			// Read XML with the expected result
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/retrieve_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("Alumno");
			
			Assertion.assertEquals(expectedTable, actualTable);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in update email test: " + e.getMessage());
		}	
	} 
	
	@Test
	public void testRetrieveAlumnoObjeto() {
		Alumno alumno = daoMySql.retrieveAlumno(1);
		
		// Verify data in database
		try {
			// This is the full database
			
		
			ITable actualTable = getConnection().createQueryTable("Alumno", "SELECT * from Alumno WHERE id = 1");
			
			int idObtenido = (int) actualTable.getValue(0,"id");
			assertThat(idObtenido, is(alumno.getID()));
			
			String nombreObtenido = (String) actualTable.getValue(0,"nombre");
			assertThat(nombreObtenido, is(alumno.getNombre()));
			
			String apellidoObtenido = (String) actualTable.getValue(0,"apellido");
			assertThat(apellidoObtenido, is(alumno.getApellido()));
			
			String emailObtenido = (String) actualTable.getValue(0,"email");
			assertThat(emailObtenido, is(alumno.getEmail()));
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in update email test: " + e.getMessage());
		}	
	} 
	
	@Test
	public void testRetrieveAllAlumnos() {
//		Alumno alumno = new Alumno(1, "alumno1", "apellido1", "hola1@hola.com");
		
		// call update method
		daoMySql.retrieveAllAlumnos();
		
		// Verify data in database
		try {
			// This is the full database
			IDataSet databaseDataSet = getConnection().createDataSet(); 
			
			ITable actualTable = databaseDataSet.getTable("Alumno");
			
			// Read XML with the expected result
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/iniDB.xml"));
			ITable expectedTable = expectedDataSet.getTable("Alumno");
			
			Assertion.assertEquals(expectedTable, actualTable);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in retrieve all test: " + e.getMessage());
		}	
	} 

}
