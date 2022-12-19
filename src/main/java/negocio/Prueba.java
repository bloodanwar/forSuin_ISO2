package negocio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import negocio.entities.*;
import negocio.controllers.GestorConsultas;

public class Prueba {


	private static GestorConsultas g= new GestorConsultas();
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	public static void main (String args[]){
		TipoCurso tipo = TipoCurso.EXPERTO;
		Date fechaInicio = null;
		Date fechaFin = null;
		
		try{		
			fechaInicio = dateFormat.parse("01-01-2000"); 
			fechaFin= dateFormat.parse("12-09-2001");
		} catch(Exception e){
			System.out.println("guatafac");
		}
		
		EstadoCurso estadoCurso= EstadoCurso.VALIDADO;
		ProfesorUCLM profesor=new ProfesorUCLM("11111111A");
		
		double resultadoConsultarIngresos = 0.0;
		List<CursoPropio> resultadoConsultarEstadoCursos=null;
		List<CursoPropio> resultadoListarCursosPorDirector=null;
		List<CursoPropio> resultadoCursosPorEstado=null;
		
		try{
			resultadoConsultarEstadoCursos= g.consultarEstadoCursos(estadoCurso, fechaInicio, fechaFin);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		System.out.println(resultadoConsultarEstadoCursos);
	}
}