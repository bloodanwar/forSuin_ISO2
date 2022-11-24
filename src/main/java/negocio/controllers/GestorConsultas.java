package negocio.controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import negocio.entities.*;
import persistencia.CursoPropioDAO;

public class GestorConsultas {

	/**
	 * 
	 * @param tipo
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public List<CursoPropio> consultarIngresos(TipoCurso tipo, Date fechaInicio, Date fechaFin) {
		// TODO - implement GestorConsultas.consultarIngresos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param estadoCurso
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public List<CursoPropio> consultarEstadoCursos(EstadoCurso estadoCurso, Date fechaInicio, Date fechaFin) {
		// TODO - implement GestorConsultas.consultarEstadoCursos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public List<CursoPropio> listarEdicionesCursos(Date fechaInicio, Date fechaFin) {
		// TODO - implement GestorConsultas.listarEdicionesCursos
		throw new UnsupportedOperationException();
	}
	
	
	public List<CursoPropio> listarCursosPorDirector(ProfesorUCLM profesor) throws ParseException{
		CursoPropio curso = new CursoPropio();
		List<CursoPropio> listaCursos = null;
	    
		try {
			listaCursos = curso.cursoPropioDao.listarCursosPorDirector(profesor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaCursos;
	}
	
	
	public List<CursoPropio> listarCursosPorEstado(EstadoCurso estado) throws ParseException{
		CursoPropio curso = new CursoPropio();
		List<CursoPropio> listaCursos = null;   
	    
		try {
			listaCursos = curso.cursoPropioDao.listarCursosPorEstado(estado);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCursos;
	}
	
	public List<CursoPropio> listarTodosCursos(){
		CursoPropio curso = new CursoPropio();	
		List<CursoPropio> cursos = null;
		
		try {
			cursos = curso.cursoPropioDao.listarCursos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cursos;
	}
}