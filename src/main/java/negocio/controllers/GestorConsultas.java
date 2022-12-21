package negocio.controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import negocio.controllers.ConsultasException.*;
import negocio.entities.*;

public class GestorConsultas {

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
	
	public double consultarIngresos(TipoCurso tipo, Date fechaInicio, Date fechaFin) throws SQLException, TipoCursoErroneoException {
		comprobarTipo(tipo);
		CursoPropio curso = new CursoPropio();
		return curso.cursoPropioDao.listarIngresos(tipo, fechaInicio, fechaFin);
	}

	public List<CursoPropio> consultarEstadoCursos(EstadoCurso estadoCurso, Date fechaInicio, Date fechaFin) throws SQLException, EstadoCursoErroneoException {
		comprobarEstado(estadoCurso);
		CursoPropio curso = new CursoPropio();
		return curso.cursoPropioDao.listarCursosPorEstado(estadoCurso, fechaInicio, fechaFin);
	}
	
	public List<CursoPropio> listarCursosPorDirector(ProfesorUCLM profesor) throws ParseException, SQLException, ProfesorErroneoException{
		if (profesor==null || profesor.getDni()==null || profesor.getDni().equals(""))
			throw new ProfesorErroneoException("Profesor no tiene DNI o es nulo");
		
		CursoPropio curso = new CursoPropio();
	    Date dateInicio = formatter.parse("01-01-1000");      
	    Date dateFin = formatter.parse("01-01-3000");  
	    
		return curso.cursoPropioDao.listarCursosPorDirector(profesor, dateInicio, dateFin);
	}
	
	public List<CursoPropio> listarCursosPorEstado(EstadoCurso estado) throws ParseException, SQLException, EstadoCursoErroneoException{
		comprobarEstado(estado);
		CursoPropio curso = new CursoPropio(); 
		Date dateInicio = formatter.parse("01-01-1000");
		Date dateFin = formatter.parse("01-01-3000");
		return curso.cursoPropioDao.listarCursosPorEstado(estado, dateInicio, dateFin);
	}
	
	public List<CursoPropio> listarTodosCursos() throws SQLException{
		CursoPropio curso = new CursoPropio();	
		return curso.cursoPropioDao.listarCursos();
	}
	
	public List<Profesor> listarProfesores() throws SQLException{
		Profesor profesor = new Profesor();	
		return profesor.profesorDao.listarProfesores();
	}
	
	public List<ProfesorUCLM> listarProfesoresUCLM() throws SQLException{
		ProfesorUCLM profesor = new ProfesorUCLM();	
		return profesor.profesorUCLMDao.listarProfesores();
	}
	
	public List<Centro> listarCentros() throws SQLException{
		Centro centro = new Centro();	
		return centro.centroDao.listarCentros();
	}
	
	private void comprobarEstado(EstadoCurso estado) throws EstadoCursoErroneoException{
		if(estado==null) throw new EstadoCursoErroneoException("El estado del curso es nulo") ;
	}
	
	private void comprobarTipo(TipoCurso tipo) throws TipoCursoErroneoException{
		if(tipo==null) throw new TipoCursoErroneoException("El tipo del curso es nulo") ;
	}
}