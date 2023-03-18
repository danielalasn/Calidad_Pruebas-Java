package com.anahuac.calidad.unitariasCRUD.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.anahuac.calidad.unitariasCRUD.entidades.Alumno;

public class DAOAlumnoMySQL implements IDAOAlumno {

	@Override
	public int crearAlumno(Alumno a) {
		Connection connection = getConnectionMySQL();
		  int result =0;
		  try {
		   // Declare statement query to run
		   PreparedStatement preparedStatement;
		   preparedStatement = connection
		     .prepareStatement("insert INTO Alumno(nombre,apellido,email) values(?,?,?)",Statement.RETURN_GENERATED_KEYS);
		   // Set the values to match in the ? on query
		   
//		   preparedStatement.setString(1, a.getID());
		   
		   preparedStatement.setString(1, a.getNombre());
		   preparedStatement.setString(2, a.getApellido());
		   preparedStatement.setString(3, a.getEmail());
		   

		   // Return the result of connection nad statement
		   if (preparedStatement.executeUpdate() >= 1) {
			   ResultSet rs = preparedStatement.getGeneratedKeys();
			   rs.next();
			   int auto_id = rs.getInt(1);
			   result = auto_id;
		   }
		   System.out.println("\n");
		   System.out.println("Alumno añadido con exito");
		   System.out.println(">> Return: " + result + "\n");
		   // Close connection with the database
		   connection.close();
		   preparedStatement.close();

		  } catch (Exception e) {
		   System.out.println(e);
		  }
		  // Return statement
		  return result;
	}

	@Override
	public boolean borrarAlumno(Alumno alumno) {
		Connection connection = getConnectionMySQL();
		  boolean result = false;

		  try {
		   // Declare statement query to run
		   PreparedStatement preparedStatement;
		   preparedStatement = connection.prepareStatement("Delete from Alumno WHERE id = ?");
		   // Set the values to match in the ? on query
		   preparedStatement.setInt(1, alumno.getID());

		   // Return the result of connection and statement
		   if (preparedStatement.executeUpdate() >= 1) {
		    result = true;
		   }
		   System.out.println("\n");
		   System.out.println("Alumno eliminado con exito");
		   System.out.println(">> Return: " + result + "\n");
		   // Close connection with the database
		   connection.close();
		   preparedStatement.close();

		  } catch (Exception e) {
		   System.out.println(e);
		  }
		  // Return statement
		  return result;
	}

	@Override
	public Alumno retrieveAlumno(int iD) {
		Connection connection = getConnectionMySQL();
		  PreparedStatement preparedStatement;
		  ResultSet rs;
		  boolean result = false;

		  Alumno retrieved = null;

		  try {
		   // Declare statement query to run
		   preparedStatement = connection.prepareStatement("SELECT * from Alumno WHERE id = ?");
		   // Set the values to match in the ? on query
		   preparedStatement.setInt(1, iD);
		   rs = preparedStatement.executeQuery();

		   // Obtain the pointer to the data in generated table
		   rs.next();

		   int retrivedId = rs.getInt(1);
		   String retrivedName = rs.getString(2);
		   String retrievedApellido = rs.getString(3);
		   String retrivedEmail = rs.getString(4);
		   
		   

		   retrieved = new Alumno(retrivedId, retrivedName,retrievedApellido, retrivedEmail);

		      // Close connection with the database
		   connection.close();
		   rs.close();
		   preparedStatement.close();

		  } catch (Exception e) {
		   System.out.println(e);
		  }
		  // Return statement
		  return retrieved;
	}

	@Override
	public List<Alumno> retrieveAllAlumnos() {
		Connection connection = getConnectionMySQL();
		  PreparedStatement preparedStatement;
		  ResultSet rs;
		  boolean result = false;

		  Alumno retrieved = null;

		  List<Alumno> listaAlumnos = null;
		  
		  try {
		   // Declare statement query to run
		   preparedStatement = connection.prepareStatement("SELECT * from Alumno");
		   // Set the values to match in the ? on query
		   rs = preparedStatement.executeQuery();

		   // Obtain the pointer to the data in generated table
		   while (rs.next()) {

			   int retrivedId = rs.getInt(1);
			   String retrivedName = rs.getString(2);
			   String retrievedApellido = rs.getString(3);
			   String retrivedEmail = rs.getString(4);
	
			   retrieved = new Alumno(retrivedId, retrivedName,retrievedApellido, retrivedEmail);
			   listaAlumnos.add(retrieved);
		   }
		   
			   connection.close();
			   rs.close();
			   preparedStatement.close();
	
			  } catch (Exception e) {
			   System.out.println(e);
			  }
			  return listaAlumnos;
		  
		}

	@Override
	public Alumno actualizarEmail(Alumno alumno, String email) {
		Connection connection = getConnectionMySQL();
		  boolean result = false;

		  try {
		   // Declare statement query to run
		   PreparedStatement preparedStatement;
		   preparedStatement = connection.prepareStatement("UPDATE Alumno SET email = ? WHERE id = ?");
		   // Set the values to match in the ? on query
		   preparedStatement.setString(1, alumno.getEmail());
		   preparedStatement.setInt(2, alumno.getID());

		   // Return the result of connection and statement
		   if (preparedStatement.executeUpdate() >= 1) {
		    result = true;
		   }
		   System.out.println("\n");
		   System.out.println("Correo de alumno con ID: " + alumno.getID() + " actualizado");
		   System.out.println(">> Return: " + result + "\n");
		   // Close connection with the database
		   connection.close();
		   preparedStatement.close();

		  } catch (Exception e) {
		   System.out.println(e);
		  }
		  // Return statement
		  return alumno;
	}
	
	public void resetSequencia() {
		Connection connection = getConnectionMySQL();
		  boolean result = false;

		  try {
		   // Declare statement query to run
		   PreparedStatement preparedStatement;
		   preparedStatement = connection.prepareStatement("ALTER TABLE Alumno AUTO_INCREMENT = ?");
		   preparedStatement.setInt(1, 0);
		   

		   // Return the result of connection and statement
		   if (preparedStatement.executeUpdate() >= 1) {
		    result = true;
		   }
		   // Close connection with the database
		   connection.close();
		   preparedStatement.close();

		  } catch (Exception e) {
		   System.out.println(e);
		  }
		  // Return statement
	}
			
	public Connection getConnectionMySQL() {

		  Connection con = null;
		  try {
		   // Establish the driver connector
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   // Set the URI for connecting the MySql database
		   con = DriverManager.getConnection("jdbc:mysql://localhost:3307/calidad", "root", "123456");
		  } catch (Exception e) {
		   System.out.println(e);
		  }
		  return con;
		 }
}

//ALTER TABLE tablename AUTO_INCREMENT = value
