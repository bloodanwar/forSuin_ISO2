package negocio.controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import negocio.entities.*;
import persistencia.CursoPropioDAO;

public class GestorConsultas {

	//TODO - crear excepcion para cuando operaciones sql devuelvan 0

	
	public List<CursoPropio> consultarIngresos(TipoCurso tipo, Date fechaInicio, Date fechaFin) {
		// TODO - implement GestorConsultas.consultarIngresos
		throw new UnsupportedOperationException();
	}

	public List<CursoPropio> consultarEstadoCursos(EstadoCurso estadoCurso, Date fechaInicio, Date fechaFin) {
		// TODO - implement GestorConsultas.consultarEstadoCursos
		throw new UnsupportedOperationException();
	}

	public List<CursoPropio> listarEdicionesCursos(Date fechaInicio, Date fechaFin) throws SQLException {
		CursoPropio curso = new CursoPropio();
		return curso.cursoPropioDao.listarEdicionesCursos(fechaInicio, fechaFin);
	}
	
	
	public List<CursoPropio> listarCursosPorDirector(ProfesorUCLM profesor) throws SQLException {
		CursoPropio curso = new CursoPropio();
		List<CursoPropio> listaCursos = null;
		
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");      
	    
	    try {
	    	Date dateInicio = formatter.parse("1990-01-01");      
	    	Date dateFin = formatter.parse("2990-01-01");
	    
			listaCursos = curso.cursoPropioDao.listarCursosPorDirector(profesor, dateInicio, dateFin);
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }

		return listaCursos;
	}
	
	
	public List<CursoPropio> listarCursosPorEstado(EstadoCurso estado) throws SQLException {
		CursoPropio curso = new CursoPropio();
		List<CursoPropio> listaCursos = null;
		
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");    
	    
	    try {
	    	Date dateInicio = formatter.parse("1990-01-01");      
	    	Date dateFin = formatter.parse("2990-01-01");
	    
			listaCursos = curso.cursoPropioDao.listarCursosPorEstado(estado, dateInicio, dateFin);
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    
		return listaCursos;
	}
	
}