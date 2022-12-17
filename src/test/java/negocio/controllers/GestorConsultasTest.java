package negocio.controllers;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

import negocio.entities.TipoCurso;
import negocio.entities.EstadoCurso;
import negocio.entities.ProfesorUCLM;


public class GestorConsultasTest {
	private GestorConsultas g=null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@Before
	public void setUp() throws Exception{
		g= new GestorConsultas();
	}
	
	@Test
<<<<<<< HEAD
	public void cp1() throws Exception{
		TipoCurso tipo = TipoCurso.MASTER;
		Date fechaInicio = dateFormat.parse("01-01-2000"); 
		Date fechaFin= dateFormat.parse("12-09-2001");
		EstadoCurso estadoCurso= EstadoCurso.PROPUESTO;
		ProfesorUCLM profesor=new ProfesorUCLM("12457890Y");
		
		g.consultarIngresos(tipo, fechaInicio, fechaFin);
		g.consultarEstadoCursos(estadoCurso, fechaInicio, fechaFin);
		g.listarEdicionesCursos(fechaInicio, fechaFin);
		g.listarCursosPorDirector(profesor);
		g.listarCursosPorEstado(estadoCurso);
		g.listarTodosCursos();
		g.listarProfesores();
		g.listarProfesorUCLM();
		g.listarCentros();
=======
	public void testConsultarIngresos() {
		//g.consultarIngresos();
		throw new RuntimeException("not yet implemented");
	} 

	@Test
	public void testConsultarEstadoCursos() {
		throw new RuntimeException("not yet implemented");
>>>>>>> 06f32b58eaee0bc904c46adb8ed5cf95a8702464
	}
	
	@Test
	public void cp2() throws Exception{
		TipoCurso tipo = TipoCurso.EXPERTO;
		Date fechaInicio = dateFormat.parse("01-01-2000"); 
		Date fechaFin= dateFormat.parse("12-09-2001");
		EstadoCurso estadoCurso= EstadoCurso.VALIDADO;
		ProfesorUCLM profesor=new ProfesorUCLM("11111111A");
	}
	
	@Test
	public void cp3() throws Exception{
		TipoCurso tipo = TipoCurso.ESPECIALISTA;
		Date fechaInicio = dateFormat.parse("01-01-2000"); 
		Date fechaFin= dateFormat.parse("12-09-2001");
		EstadoCurso estadoCurso= EstadoCurso.PROPUESTA_RECHAZADA;
		ProfesorUCLM profesor=new ProfesorUCLM("");
	}
	
	@Test
	public void cp4() throws Exception{
		TipoCurso tipo = TipoCurso.FORMACION_AVANZADA;
		Date fechaInicio = dateFormat.parse("01-01-2000"); 
		Date fechaFin= dateFormat.parse("12-09-2001");
		EstadoCurso estadoCurso= EstadoCurso.EN_MATRICULACION;
		ProfesorUCLM profesor=null; 
	}
	
	@Test
	public void cp5() throws Exception{
		TipoCurso tipo = TipoCurso.FORMACION_CONTINUA;
		Date fechaInicio = dateFormat.parse("01-01-2000"); 
		Date fechaFin= dateFormat.parse("12-09-2001");
		EstadoCurso estadoCurso= EstadoCurso.EN_IMPARTIZICION;
		ProfesorUCLM profesor=new ProfesorUCLM("12457890Y");
	}
	
	@Test
	public void cp6() throws Exception{
		TipoCurso tipo = TipoCurso.MICROCREDENCIALES;
		Date fechaInicio = dateFormat.parse("01-01-2000"); 
		Date fechaFin= dateFormat.parse("12-09-2001");
		EstadoCurso estadoCurso= EstadoCurso.TERMINADO;
		ProfesorUCLM profesor=new ProfesorUCLM("11111111A");
	}
	
	@Test
	public void cp7() throws Exception{
		TipoCurso tipo = TipoCurso.CORTA_DURACION;
		Date fechaInicio = dateFormat.parse("01-01-2000"); 
		Date fechaFin= dateFormat.parse("12-09-2001");
		EstadoCurso estadoCurso= null;
		ProfesorUCLM profesor=new ProfesorUCLM("");
	}
	
	@Test
	public void cp8() throws Exception{
		TipoCurso tipo = TipoCurso.VERANO;
		Date fechaInicio = dateFormat.parse("01-01-2000"); 
		Date fechaFin= dateFormat.parse("12-09-2001");
		EstadoCurso estadoCurso= EstadoCurso.PROPUESTO;
		ProfesorUCLM profesor=null;
	}
	
	@Test
	public void cp9() throws Exception{
		TipoCurso tipo = TipoCurso.MAYORES;
		Date fechaInicio = dateFormat.parse("01-01-2000"); 
		Date fechaFin= dateFormat.parse("12-09-2001");
		EstadoCurso estadoCurso= EstadoCurso.VALIDADO;
		ProfesorUCLM profesor=new ProfesorUCLM("12457890Y");
	}
	
	@Test
	public void cp10() throws Exception{
		TipoCurso tipo = null;
		Date fechaInicio = dateFormat.parse("01-01-2000"); 
		Date fechaFin= dateFormat.parse("12-09-2001");
		EstadoCurso estadoCurso= EstadoCurso.PROPUESTA_RECHAZADA;
		ProfesorUCLM profesor=new ProfesorUCLM("11111111A");
	}
}