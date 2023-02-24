package com.anahuac.calidad.doubles;

public class SubDependency {

	public String getClassName(){
		
		return this.getClassName();		
	}
	
	public int addTwo (int i)
	{
		return i+2;
	}
}
