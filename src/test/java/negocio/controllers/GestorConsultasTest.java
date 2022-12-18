package negocio.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import negocio.entities.TipoCurso;
import negocio.entities.Centro;
import negocio.entities.CursoPropio;
import negocio.entities.EstadoCurso;
import negocio.entities.Profesor;
import negocio.entities.ProfesorUCLM;


public class GestorConsultasTest {
	private GestorConsultas g=null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@Before
	public void setUp() throws Exception{
		g= new GestorConsultas();
	}
	
	@Test
	public void cp1() throws Exception{
		TipoCurso tipo = TipoCurso.MASTER;
		Date fechaInicio = dateFormat.parse("01-01-2000"); 
		Date fechaFin= dateFormat.parse("12-09-2001");
		EstadoCurso estadoCurso= EstadoCurso.PROPUESTO;
		ProfesorUCLM profesor=new ProfesorUCLM("12457890Y");
		
		double resultadoConsultarIngresos = 0.0;
		List<CursoPropio> resultadoConsultarEstadoCursos=null;
		List<CursoPropio> resultadoListarCursosPorDirector=null;
		List<CursoPropio> resultadoCursosPorEstado=null;
		List<CursoPropio> resultadoListarTodosCursos=null;
		List<Profesor> resultadoListarProfesores=null;
		List<ProfesorUCLM> resultadoListarProfesoresUCLM;
		List<Centro> resultadoListarCentros;
		
		try{
			resultadoConsultarIngresos = g.consultarIngresos(tipo, fechaInicio, fechaFin);
			resultadoConsultarEstadoCursos= g.consultarEstadoCursos(estadoCurso, fechaInicio, fechaFin);
			resultadoListarCursosPorDirector= g.listarCursosPorDirector(profesor);
			resultadoCursosPorEstado=g.listarCursosPorEstado(estadoCurso);
			
			resultadoListarTodosCursos = g.listarTodosCursos();
			resultadoListarProfesores = g.listarProfesores();
			resultadoListarProfesoresUCLM = g.listarProfesoresUCLM();
			resultadoListarCentros = g.listarCentros();
		} catch (Exception e){
			fail("HA TIRADO UNA EXCEPCION");
		}
		
		CursoPropio curso01 = new CursoPropio(
				"01", 
				"Cocina", 
				90, 
				dateFormat.parse("10-09-2020"), 
				dateFormat.parse("20-09-2020"), 
				45.5, 
				1, 
				EstadoCurso.PROPUESTO, 
				TipoCurso.MASTER, 
				new Centro("UCLM ALB"),
				new ProfesorUCLM("12457890Y"), 
				new ProfesorUCLM("23568907X"),
				null);
		
		CursoPropio curso02 = new CursoPropio(
				"02", 
				"Robotica", 
				20, 
				dateFormat.parse("25-09-2020"), 
				dateFormat.parse("30-09-2020"), 
				6.66, 
				7, 
				EstadoCurso.VALIDADO, 
				TipoCurso.EXPERTO, 
				new Centro("UCLM CUE"),
				new ProfesorUCLM("23568907X"), 
				new ProfesorUCLM("09764312U"),
				null);
		
		CursoPropio curso03 = new CursoPropio(
				"03", 
				"Gestion de datos", 
				1, 
				dateFormat.parse("15-11-2020"), 
				dateFormat.parse("20-11-2020"), 
				66.6, 
				12, 
				EstadoCurso.TERMINADO, 
				TipoCurso.CORTA_DURACION, 
				new Centro("UCLM TAL"),
				new ProfesorUCLM("12457890Y"), 
				new ProfesorUCLM("09764312U"),
				null);
				
		CursoPropio curso04 = new CursoPropio(
				"04", 
				"Administracion de empresas", 
				12, 
				dateFormat.parse("05-10-2021"), 
				dateFormat.parse("10-10-2021"), 
				2.22, 
				98, 
				EstadoCurso.TERMINADO, 
				TipoCurso.FORMACION_CONTINUA, 
				new Centro("UCLM GUA"),
				new ProfesorUCLM("12457890Y"), 
				new ProfesorUCLM("14709633I"),
				null);
		
		CursoPropio curso05 = new CursoPropio(
				"05", 
				"Gestion de sistemas de informacion", 
				2, 
				dateFormat.parse("01-09-2021"), 
				dateFormat.parse("15-09-2021"), 
				999.99, 
				56, 
				EstadoCurso.VALIDADO, 
				TipoCurso.MICROCREDENCIALES, 
				new Centro("UCLM CIU"),
				new ProfesorUCLM("14709633I"), 
				profesor,
				null);
				
		CursoPropio curso06 = new CursoPropio(
				"06",
				"Hackathon",
				1, 
				dateFormat.parse("07-12-2021"),
				dateFormat.parse("14-12-2021"),
				1.0,
				23,
				EstadoCurso.PROPUESTO,
				TipoCurso.CORTA_DURACION,
				new Centro("UCLM ALM"),
				new ProfesorUCLM("12457890Y"),
				new ProfesorUCLM("98653214Z"),
				null);
	
		CursoPropio curso07 = new CursoPropio(
				"07",
				"Educacion social",
				48, 
				dateFormat.parse("01-09-2021"),
				dateFormat.parse("30-09-2021"),
				67.5,
				5,
				EstadoCurso.PROPUESTO,
				TipoCurso.ESPECIALISTA,
				new Centro("UCLM TOL"),
				new ProfesorUCLM("98653214Z"),
				profesor,
				null);
		
		CursoPropio curso08 = new CursoPropio(
				"08",
				"Inclusion adultos en nuevas tecnologias",
				17, 
				dateFormat.parse("06-10-2022"),
				dateFormat.parse("12-10-2022"),
				220.6,
				73,
				EstadoCurso.TERMINADO,
				TipoCurso.FORMACION_AVANZADA,
				new Centro("UCLM ALB"),
				new ProfesorUCLM("98653214Z"),
				new ProfesorUCLM("23568907X"),
				null);
				
		CursoPropio curso09 = new CursoPropio(
				"09", 
				"Instrumentos", 
				15, 
				dateFormat.parse("12-12-2022"), 
				dateFormat.parse("14-12-2022"), 
				45.5, 
				21, 
				EstadoCurso.TERMINADO, 
				TipoCurso.EXPERTO, 
				new Centro("UCLM ALM"),
				new ProfesorUCLM("98653214Z"), 
				new ProfesorUCLM("14709633I"),
				null);
		
		CursoPropio curso10 = new CursoPropio(
				"10", 
				"Pedagogia infantil", 
				2, 
				dateFormat.parse("15-11-2022"), 
				dateFormat.parse("18-11-2022"), 
				19.95, 
				134,
				EstadoCurso.VALIDADO,
				TipoCurso.CORTA_DURACION, 
				new Centro("UCLM GUA"),
				new ProfesorUCLM("14709633I"),
				new ProfesorUCLM("23568907X"), 
				null);
		
		Profesor profesor01 = new Profesor(
				"12457890Y",
				"Jose",
				"Perez Esteban",
				true);
		
		Profesor profesor02 = new Profesor(
				"09764312U",
				"Antonio",
				"Santos Ruiz",
				false);
				
		Profesor profesor03 = new Profesor(
				"23568907X",
				"Marcos",
				"Fernandez Toledano",
				false);
		
		Profesor profesor04 = new Profesor(
				"98653214Z",
				"Miguel",
				"Romeo Orozco",
				true);
				
		Profesor profesor05 = new Profesor(
				"14709633I",
				"Laura",
				"Mendo Palomeque",
				true);
		
		Profesor profesor06 = new Profesor(
				"07412588O",
				"Ana",
				"Gomez Munoz",
				false);
		
		Profesor profesor07 = new Profesor(
				"25885236P",
				"Elena",
				"Parro Lopez",
				false);
		
		Profesor profesor08 = new Profesor(
				"45738298T",
				"Alba",
				"Vicario Corrochano",
				true);
		
		Profesor profesor09 = new Profesor(
				"60863916B",
				"David",
				"Marquez Alvarez",
				true);
				
		Profesor profesor10 = new Profesor(
				"92659487C",
				"Luis",
				"Garatea Hidalgo",
				false);
		
		List<CursoPropio> esperadoConsultarEstadoCursos=new ArrayList<>();
		List<CursoPropio> esperadoListarCursosPorDirector=new ArrayList<>();
		esperadoListarCursosPorDirector.add(curso05);
		esperadoListarCursosPorDirector.add(curso07);

		List<CursoPropio> esperadoListarCursosPorEstado=new ArrayList<>();
		esperadoListarCursosPorEstado.add(curso01);
		esperadoListarCursosPorEstado.add(curso06);	
		esperadoListarCursosPorEstado.add(curso07);		
		
		List<CursoPropio> esperadoListarTodosCursos=new ArrayList<>();
		esperadoListarTodosCursos.add(curso01);
		esperadoListarTodosCursos.add(curso02);
		esperadoListarTodosCursos.add(curso03);
		esperadoListarTodosCursos.add(curso04);
		esperadoListarTodosCursos.add(curso05);
		esperadoListarTodosCursos.add(curso06);
		esperadoListarTodosCursos.add(curso07);
		esperadoListarTodosCursos.add(curso08);
		esperadoListarTodosCursos.add(curso09);
		esperadoListarTodosCursos.add(curso10);
		
		List<Profesor> esperadoListarProfesores=new ArrayList<>();
		esperadoListarProfesores.add(profesor01);
		esperadoListarProfesores.add(profesor02);
		esperadoListarProfesores.add(profesor03);
		esperadoListarProfesores.add(profesor04);
		esperadoListarProfesores.add(profesor05);
		esperadoListarProfesores.add(profesor06);
		esperadoListarProfesores.add(profesor07);
		esperadoListarProfesores.add(profesor08);
		esperadoListarProfesores.add(profesor09);
		esperadoListarProfesores.add(profesor10);
		
		assertTrue(resultadoConsultarIngresos==0.0);
		assertEquals(esperadoConsultarEstadoCursos, resultadoConsultarEstadoCursos);
		assertEquals(esperadoListarCursosPorDirector, resultadoListarCursosPorDirector);
		assertEquals(esperadoListarCursosPorEstado, resultadoCursosPorEstado);
		assertEquals(esperadoListarTodosCursos, resultadoListarTodosCursos);
		assertEquals(esperadoListarProfesores, resultadoListarProfesores);
		/**
		assertTrue(resultadoListarProfesoresUCLM==);
		assertTrue(resultadoListarCentros==);
		**/
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