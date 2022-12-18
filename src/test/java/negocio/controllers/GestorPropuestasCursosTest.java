package negocio.controllers;

import org.junit.Before;
import org.junit.Test;

import negocio.entities.CursoPropio;

public class GestorPropuestasCursosTest {
	
	private GestorPropuestasCursos g = null;
	
	@Before
	public void setUp() throws Exception{
		g= new GestorPropuestasCursos();
	}
	
	@Test
	public void cp1() {
		String id = "02";
		int edicion = -4;
		CursoPropio curso = new CursoPropio(id, edicion);
	}

	@Test
	public void cp2() {
		String id = "";
		int edicion = 0;
		CursoPropio curso = new CursoPropio(id, edicion);
	}

	@Test
	public void cp3() {
		String id = "02";
		int edicion = 5;
		CursoPropio curso = new CursoPropio(id, edicion);
	}

	@Test
	public void cp4() {
		CursoPropio curso = null;
	}
}
