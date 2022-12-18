package negocio.controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import negocio.entities.*;
import persistencia.CursoPropioDAO;

public class GestorConsultas {

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
	
	public double consultarIngresos(TipoCurso tipo, Date fechaInicio, Date fechaFin) throws SQLException {
		CursoPropio curso = new CursoPropio();
		return curso.cursoPropioDao.listarIngresos(tipo, fechaInicio, fechaFin);
	}

	public List<CursoPropio> consultarEstadoCursos(EstadoCurso estadoCurso, Date fechaInicio, Date fechaFin) throws SQLException {
		CursoPropio curso = new CursoPropio();
		return curso.cursoPropioDao.listarCursosPorEstado(estadoCurso, fechaInicio, fechaFin);
	}
	
	public List<CursoPropio> listarCursosPorDirector(ProfesorUCLM profesor) throws ParseException, SQLException{
		CursoPropio curso = new CursoPropio();
	    Date dateInicio = formatter.parse("01-01-1000");      
	    Date dateFin = formatter.parse("01-01-3000");  
	    
		List<CursoPropio> listaCursos = curso.cursoPropioDao.listarCursosPorDirector(profesor, dateInicio, dateFin);
		return listaCursos;
	}
	
	public List<CursoPropio> listarCursosPorEstado(EstadoCurso estado) throws ParseException, SQLException{
		CursoPropio curso = new CursoPropio(); 
		Date dateInicio = formatter.parse("01-01-1000");
		Date dateFin = formatter.parse("01-01-3000");
		List<CursoPropio> listaCursos = curso.cursoPropioDao.listarCursosPorEstado(estado, dateInicio, dateFin);
	
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