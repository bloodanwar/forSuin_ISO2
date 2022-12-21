package negocio.controllers;

import java.sql.SQLException;
import java.text.ParseException;

import negocio.controllers.CursoException.*;
import negocio.entities.*;

public class GestorPropuestasCursos {

	//TODO - crear excepcion para cuando operaciones sql devuelvan 0
	
	public void realizarPropuestaCurso(CursoPropio cursoPropuesto) throws SQLException, CursoNoCreadoException, CursoErroneoException {
		comprobarSiTieneIdEdicion(cursoPropuesto);
		if (cursoPropuesto.cursoPropioDao.crearNuevoCurso(cursoPropuesto) == 0) {
			throw new CursoNoCreadoException("No se ha realizado la propuesta del curso");
		}
	}

	public void editarPropuestaCurso(CursoPropio cursoEditado) throws SQLException, CursoNoEditadoException, ParseException, CursoErroneoException, CursoNoExisteException {
		comprobarSiTieneIdEdicion(cursoEditado);
		if (cursoEditado.cursoPropioDao.editarCurso(cursoEditado) == 0) {
			throw new CursoNoEditadoException("No se ha realizado la edición de la propuesta del curso");
		}
	}
	
	public void eliminarPropuestaCurso(CursoPropio curso) throws SQLException, CursoNoEliminadoException, CursoErroneoException {
		comprobarSiTieneIdEdicion(curso);
		if (curso.cursoPropioDao.eliminarCursoPropio(curso) == 0) {
			throw new CursoNoEliminadoException("No se ha eliminado la propuesta del curso");
		}
	}

	public EstadoCurso evaluarPropuesta(CursoPropio curso) throws SQLException, ParseException, CursoErroneoException, CursoNoExisteException {
		comprobarSiTieneIdEdicion(curso);
		CursoPropio cursoTemp = curso.cursoPropioDao.seleccionarCurso(curso);
		return cursoTemp.estado;
	}
	
	public void altaCursoAprobado(CursoPropio curso) throws SQLException, CursoNoEditadoException, ParseException, CursoErroneoException, CursoNoExisteException {
		comprobarSiTieneIdEdicion(curso);
		curso.estado = EstadoCurso.VALIDADO;
		if (curso.cursoPropioDao.editarCurso(curso) == 0) {
			throw new CursoNoEditadoException("No se ha dado de alta el curso");
		}
	}
	
	private void comprobarSiTieneIdEdicion(CursoPropio curso) throws CursoErroneoException {
		boolean idCursoError = false;
		boolean edicionCursoError = false;
		if (curso == null) throw new CursoErroneoException("Curso nulo");
		if (curso.getId()==null || curso.getId().equals("")) idCursoError = true;
		if (curso.getEdicion()<=0) edicionCursoError = true;
		
		if (!idCursoError && !edicionCursoError) return;
		else if (idCursoError && edicionCursoError) throw new CursoErroneoException("Curso sin id ni edicion");
		else {
			if (idCursoError) throw new CursoErroneoException("Curso sin id");
			if (edicionCursoError) throw new CursoErroneoException("Curso sin edicion");
		}	
	}
}