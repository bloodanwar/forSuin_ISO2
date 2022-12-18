package negocio.controllers;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import negocio.controllers.CursoException.CursoErroneoException;
import negocio.controllers.CursoException.CursoNoEditadoException;
import negocio.controllers.CursoException.CursoNoEliminadoException;
import negocio.controllers.CursoException.CursoNoExisteException;
import negocio.entities.Centro;
import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.ProfesorUCLM;
import negocio.entities.TipoCurso;

public class GestorPropuestasCursosTest {
	
	private GestorPropuestasCursos g = null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@Before
	public void setUp() throws Exception{
		g= new GestorPropuestasCursos();
	}
	
	@Test
	public void cp1() {
		String id = "02";
		int edicion = -4;
		CursoPropio curso = new CursoPropio(id, edicion);
		
		try { g.realizarPropuestaCurso(curso);
		} catch (CursoErroneoException e) {	System.out.println(e);
		} catch (Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try { g.editarPropuestaCurso(curso);
		} catch (CursoErroneoException e) { System.out.println(e);
		} catch (Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try { g.eliminarPropuestaCurso(curso);
		} catch (CursoErroneoException e) { System.out.println(e);
		} catch (Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try { g.evaluarPropuesta(curso);
		} catch (CursoErroneoException e) { System.out.println(e);
		} catch (Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try { g.altaCursoAprobado(curso);
		} catch (CursoErroneoException e) { System.out.println(e);
		} catch (Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		return;
	}

	@Test
	public void cp2() {
		String id = "";
		int edicion = 0;
		CursoPropio curso = new CursoPropio(id, edicion);
		
		try { g.realizarPropuestaCurso(curso);
		} catch (CursoErroneoException e) {	System.out.println(e);
		} catch (Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try { g.editarPropuestaCurso(curso);
		} catch (CursoErroneoException e) { System.out.println(e);
		} catch (Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try { g.eliminarPropuestaCurso(curso);
		} catch (CursoErroneoException e) { System.out.println(e);
		} catch (Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try { g.evaluarPropuesta(curso);
		} catch (CursoErroneoException e) { System.out.println(e);
		} catch (Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try { g.altaCursoAprobado(curso);
		} catch (CursoErroneoException e) { System.out.println(e);
		} catch (Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());
		}
	}

	@Test
	public void cp3() {
		String id = "02";
		int edicion = 5;
		CursoPropio curso = new CursoPropio(id, edicion);
		
		try { g.realizarPropuestaCurso(curso);
		} catch (NullPointerException e) {	System.out.println(e);
		} catch (Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());
		}
	}

	@Test
	public void cp4() {
		CursoPropio curso = null;
		
		try { g.realizarPropuestaCurso(curso);
		} catch (CursoErroneoException e) {	System.out.println(e);
		} catch (Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try { g.editarPropuestaCurso(curso);
		} catch (CursoErroneoException e) { System.out.println(e);
		} catch (Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try { g.eliminarPropuestaCurso(curso);
		} catch (CursoErroneoException e) { System.out.println(e);
		} catch (Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try { g.evaluarPropuesta(curso);
		} catch (CursoErroneoException e) { System.out.println(e);
		} catch (Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try { g.altaCursoAprobado(curso);
		} catch (CursoErroneoException e) { System.out.println(e);
		} catch (Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());
		}
	}
	
	@Test
	public void cp5() { //captura SQLException
		String id = "02";
		int edicion = 5;
		CursoPropio curso = new CursoPropio(id, edicion);
		curso.estado = EstadoCurso.PROPUESTO;
		curso.tipo = TipoCurso.VERANO;
		curso.centro = new Centro("Ribera del Tajo");
		curso.secretario = new ProfesorUCLM("alfonsoSIU");
		curso.director = new ProfesorUCLM("ascodetodo");
		
		try {
			curso.setFechaInicio(dateFormat.parse("11-09-2001"));
			curso.setFechaFin(dateFormat.parse("01-11-2012"));
		} catch (ParseException e1) {
			fail("Fechas mal construidas");
			e1.printStackTrace();
		}
		
		try { g.realizarPropuestaCurso(curso);
		} catch (SQLException e) {	System.out.println(e);
		} catch (Exception e) { fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try {
			g.editarPropuestaCurso(curso);
		} catch (CursoNoExisteException e) { System.out.println(e);
		} catch (Exception e) {	fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try {
			g.eliminarPropuestaCurso(curso);
		} catch (CursoNoEliminadoException e) { System.out.println(e);
		} catch (Exception e) {	fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try {
			g.evaluarPropuesta(curso);
		} catch (CursoNoExisteException e) { System.out.println(e);
		} catch (Exception e) {	fail("Ocurrió excepción no esperada: "+e.toString());
		}
		
		try {
			g.altaCursoAprobado(curso);
		} catch (CursoNoExisteException e) { System.out.println(e);
		} catch (Exception e) {	fail("Ocurrió excepción no esperada: "+e.toString());
		}
	}
	
	public void cp6(){
		String id = "02";
		int edicion = 5;
		/**
		CursoPropio curso = new CursoPropio(
				id,
				edicion,
				
				);
		
		curso.estado = EstadoCurso.PROPUESTO;
		curso.tipo = TipoCurso.VERANO;
		curso.centro = new Centro("Ribera del Tajo");
		curso.secretario = new ProfesorUCLM("alfonsoSIU");
		curso.director = new ProfesorUCLM("ascodetodo");
		//materia= new Materia("Historia de España");
		 
		 */
	}
}