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
		TipoCurso tipo = TipoCurso.MASTER;
		List<Centro> resultadoConsultarEstadoCursos=null;
		EstadoCurso estadoCurso= EstadoCurso.PROPUESTO;
		ProfesorUCLM profesor=new ProfesorUCLM("12457890Y");
		
		try{
			Date fechaInicio = dateFormat.parse("01-01-2000"); 
			Date fechaFin= dateFormat.parse("12-09-2001");
			resultadoConsultarEstadoCursos = g.listarCentros();

		} catch (Exception e){
			e.printStackTrace();
		}
		
		System.out.println(resultadoConsultarEstadoCursos);
	}
}
