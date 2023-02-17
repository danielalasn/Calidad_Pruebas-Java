package com.anahuac.calidad.unittest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestMultiplicacion {
	private Calculadora calculadora;
	private double parametro1;
	private double parametro2;
	private double resultadoEsperado;
	
	
	public TestMultiplicacion(double arg1, double arg2, double arg3) {
		parametro1 = arg1;
		parametro2 = arg2;
		resultadoEsperado = arg3;
	}
	
	@Before
	public void setup() {
		calculadora = new Calculadora();
		System.out.println("Este es el before");
	}
	
	@Parameters
	 public static List<Object[]> data(){
		 return Arrays.asList(new Object[][]{
			 {2,2,4},
			 {3,2,6},
			 {5,1,5},
			 {5,2,10},
			 {-3,0,-0.0},
			 {-5,-10,50}
			 });
	 }
	 
	 @Test
		public void testMultiplicacion() {
			double resultadoEjecucion = calculadora.multiplicacion(parametro1, parametro2);
			assertThat(resultadoEjecucion, equalTo(resultadoEsperado));
		}
	 
	 

}
