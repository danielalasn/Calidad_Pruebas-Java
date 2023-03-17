package com.anahuac.calidad.integracion;

import static org.junit.Assert.*;

import java.io.File;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.anahuac.calidad.unitariasCRUD.dao.DAOAlumnoMySQL;
import com.anahuac.calidad.unitariasCRUD.entidades.Alumno;

public class DAOAlumnoTest extends DBTestCase{

	private DAOAlumnoMySQL daoMySql;
	
	public DAOAlumnoTest() {
		  System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,"com.mysql.cj.jdbc.Driver");
		  System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://localhost:3307/calidad");
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
	 public void testAddAlumno() {
	  Alumno alumno = new Alumno("alumno001", "apellido", "hola1@hola.com");
	  
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

}
