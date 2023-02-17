package com.anahuac.calidad.unittest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCalculadora.class, TestMultiplicacion.class })
public class AllTests {

}
