package negocio.controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import negocio.controllers.CursoException.CursoErroneoException;
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
		
		try {
			g.realizarPropuestaCurso(curso);
		} catch (CursoErroneoException e) {
			
		} catch (Exception e) {
			fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try {
			g.editarPropuestaCurso(curso);
		} catch (CursoErroneoException e) {
			
		} catch (Exception e) {
			fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try {
			g.eliminarPropuestaCurso(curso);
		} catch (CursoErroneoException e) {
			
		} catch (Exception e) {
			fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		return;
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
