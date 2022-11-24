package negocio.controllers;

import java.sql.SQLException;

import negocio.entities.*;

public class GestorPropuestasCursos {

	public void realizarPropuestaCurso(CursoPropio cursoPropuesto) {
		// TODO - implement GestorPropuestasCursos.realizarPropuestaCurso
		try {
			cursoPropuesto.cursoPropioDao.crearNuevoCurso(cursoPropuesto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param curso
	 */
	public void editarPropuestaCurso(CursoPropio cursoEditado) {
		// TODO - implement GestorPropuestasCursos.editarPropuestaCurso
		try {
			cursoEditado.cursoPropioDao.editarCurso(cursoEditado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param curso
	 */
	public void eliminarPropuestaCurso(CursoPropio cursoEditado) {
		// TODO - implement GestorPropuestasCursos.editarPropuestaCurso
		try {
			cursoEditado.cursoPropioDao.eliminarCursoPropio(cursoEditado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param curso
	 */
	public EstadoCurso evaluarPropuesta(CursoPropio curso) {
		// TODO - implement GestorPropuestasCursos.evaluarPropuesta
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param curso
	 */
	public void altaCursoAprobado(CursoPropio curso) {
		// TODO - implement GestorPropuestasCursos.altaCursoAprobado
		throw new UnsupportedOperationException();
	}

}