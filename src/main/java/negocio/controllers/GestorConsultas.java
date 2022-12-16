package negocio.controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import negocio.entities.*;
import persistencia.CursoPropioDAO;

public class GestorConsultas {

	public double consultarIngresos(TipoCurso tipo, Date fechaInicio, Date fechaFin) throws SQLException {
		CursoPropio curso = new CursoPropio();
		return curso.cursoPropioDao.listarIngresos(tipo, fechaInicio, fechaFin);
	}

	public List<CursoPropio> consultarEstadoCursos(EstadoCurso estadoCurso, Date fechaInicio, Date fechaFin) throws SQLException {
		CursoPropio curso = new CursoPropio();
		return curso.cursoPropioDao.listarCursosPorEstado(estadoCurso, fechaInicio, fechaFin);
	}

	public List<CursoPropio> listarEdicionesCursos(Date fechaInicio, Date fechaFin) {
		// TODO - implement GestorConsultas.listarEdicionesCursos
		throw new UnsupportedOperationException();
	}
	
	
	public List<CursoPropio> listarCursosPorDirector(ProfesorUCLM profesor) throws ParseException{
		CursoPropio curso = new CursoPropio();
		List<CursoPropio> listaCursos = null;
		
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
	    Date dateInicio = formatter.parse("01-01-1000");      
	    Date dateFin = formatter.parse("01-01-3000");      
	    //System.out.println(dateInicio);
	    
		try {
			listaCursos = curso.cursoPropioDao.listarCursosPorDirector(profesor, dateInicio, dateFin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaCursos;
	}
	
	
	public List<CursoPropio> listarCursosPorEstado(EstadoCurso estado) throws ParseException{
		CursoPropio curso = new CursoPropio();
		List<CursoPropio> listaCursos = null;
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
	    Date dateInicio = new Date(); 
	    Date dateFin = null;
		try {
			dateInicio = formatter.parse("01-01-1000");
			dateFin = formatter.parse("01-01-3000");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}      
	    
		try {
			listaCursos = curso.cursoPropioDao.listarCursosPorEstado(estado, dateInicio, dateFin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaCursos;
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
}