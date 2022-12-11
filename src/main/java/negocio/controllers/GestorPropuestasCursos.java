package negocio.controllers;

import java.sql.SQLException;
import java.text.ParseException;

import negocio.controllers.CursoException.*;
import negocio.entities.*;

public class GestorPropuestasCursos {

	//TODO - crear excepcion para cuando operaciones sql devuelvan 0
	
	public void realizarPropuestaCurso(CursoPropio cursoPropuesto) throws SQLException, CursoNoCreadoException {
		if (cursoPropuesto.cursoPropioDao.crearNuevoCurso(cursoPropuesto) == 0) {
			throw new CursoNoCreadoException("No se ha realizado la propuesta del curso");
		}
	}

	public void editarPropuestaCurso(CursoPropio cursoEditado) throws SQLException, CursoNoEditadoException, ParseException {
		if (cursoEditado.cursoPropioDao.editarCurso(cursoEditado) == 0) {
			throw new CursoNoEditadoException("No se ha realizado la edición de la propuesta del curso");
		}
	}
	
	public void eliminarPropuestaCurso(CursoPropio curso) throws SQLException, CursoNoEliminadoException {
		if (curso.cursoPropioDao.eliminarCursoPropio(curso) == 0) {
			throw new CursoNoEliminadoException("No se ha eliminado la propuesta del curso");
		}
	}

	public EstadoCurso evaluarPropuesta(CursoPropio curso) {
		return curso.estado;
	}
	
	public void altaCursoAprobado(CursoPropio curso) throws SQLException, CursoNoEditadoException, ParseException {
		curso.estado = EstadoCurso.VALIDADO;
		if (curso.cursoPropioDao.editarCurso(curso) == 0) {
			throw new CursoNoEditadoException("No se ha dado de alta el curso");
		}
	}

}