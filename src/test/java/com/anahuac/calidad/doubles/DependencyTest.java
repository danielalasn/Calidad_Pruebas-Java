package com.anahuac.calidad.doubles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class DependencyTest {

	Dependency dependency;
	SubDependency subDependency = Mockito.mock(SubDependency.class);
	
	@Before
	public void setUp() throws Exception {
		dependency = new Dependency(subDependency);
	}

	@After
	public void tearDown() throws Exception {
	}
	
//	//@Test
//	public void getClassName() {
//		System.out.println(dependency.getClassName());
//	}
//	
//	//@Test
//	public void getSubDependencyClassName() {
//		System.out.println(dependency.getSubdependencyClassname());
//	}
//	
//	@Test
//	public void subDependencyTest() {
//		System.out.println(subDependency.getClassName());
//		when(subDependency.getClassName()).thenReturn("Nombre de la clase de subDependency");
//		System.out.println(subDependency.getClassName());
//		
//		when(subDependency.getClassName()).thenReturn("segundo nombre");
//		System.out.println(subDependency.getClassName());
//		
//		//when(subDependency.getClassName()).thenThrow(Exception.class);
//		//System.out.println(subDependency.getClassName());
//	}
//	
//	@Test
//	public void pruebasConArgumentos(){
//		double resultadoEsperado = 4;
//		when(subDependency.addTwo(5)).thenReturn(4);
//		double resultadoEjecucion = subDependency.addTwo(5);
//		assertThat(resultadoEjecucion, equalTo(resultadoEsperado));
//	}
//	
//	@Test
//	public void pruebasConMatcher(){
//		double resultadoEsperado = 502;
//		when(subDependency.addTwo(anyInt())).thenReturn(502);
//		double resultadoEjecucion = subDependency.addTwo(500);
//		assertThat(resultadoEjecucion, equalTo(resultadoEsperado));
//	}
	
	@Test
	public void testAnswer(){
		when(subDependency.addTwo(anyInt())).thenAnswer(new Answer<Integer>(){
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				int arg = (Integer) invocation.getArguments()[0];
				return arg + 20;
			}
		});
		assertEquals (30, subDependency.addTwo(10));
	}
}

