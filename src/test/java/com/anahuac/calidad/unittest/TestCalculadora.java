package com.anahuac.calidad.unittest;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCalculadora {

	private Calculadora calculadora;
	
	@Before
	public void setup() {
		calculadora = new Calculadora();
		System.out.println("Este es el before");
	}
	
	@BeforeClass
	public static void setupClass() {
		System.out.println("Before Class");
	}
	
	@Test
	public void testSuma() {
		double resultadoEsperado = 5;
		
		double resultadoEjecucion = calculadora.suma(2, 3);
		
		assertThat(resultadoEjecucion, equalTo(resultadoEsperado));
	}
	
	@Test
	public void testResta() {
		double resultadoEsperado = 5;
		
		double resultadoEjecucion = calculadora.resta(10, 5);
		
		assertThat(resultadoEjecucion, equalTo(resultadoEsperado));
	}
	
	@Test
	public void testDivision() {
		double resultadoEsperado = 5;
		
		double resultadoEjecucion = calculadora.division(10, 2);
		
		assertThat(resultadoEjecucion, equalTo(resultadoEsperado));
	}
	
	@Test
	public void testDivisionEntreCero() {
		
		double resultadoEsperado = Double.POSITIVE_INFINITY;
		
		double resultadoEjecucion = calculadora.division(10, 0);
		
		assertThat(resultadoEjecucion, equalTo(resultadoEsperado));
		
	}
	
	@Test
	public void testDivisionEntreNegativo() {
		double resultadoEsperado = -2;
		
		double resultadoEjecucion = calculadora.division(10, -5);
		
		assertThat(resultadoEjecucion, equalTo(resultadoEsperado));
	}
	
	@Test
	public void testMultiplicacion() {
		double resultadoEsperado = 10;
		
		double resultadoEjecucion = calculadora.multiplicacion(2, 5);
		
		assertThat(resultadoEjecucion, equalTo(resultadoEsperado));
	}
	
	@Test(expected = ArithmeticException.class)
	public void testDivisionEntera() {
		int resultadoEjecucion = calculadora.divisionEntera(10, 0);

	}
	
	@After
	public void clean() {
		System.out.println("After");
	}
	
	@AfterClass
	public static void cleanClass() {
		System.out.println("After Class");
	}

}
