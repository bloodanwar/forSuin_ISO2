package negocio.controllers;

import java.sql.SQLException;

import negocio.entities.*;

public class GestorPropuestasCursos {

	public void realizarPropuestaCurso(CursoPropio cursoPropuesto) {
		// TODO - implement GestorPropuestasCursos.realizarPropuestaCurso
		CursoPropio curso = new CursoPropio();
		try {
			curso.cursoPropioDao.crearNuevoCurso(cursoPropuesto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param curso
	 */
	public void editarPropuestaCurso(CursoPropio curso) {
		// TODO - implement GestorPropuestasCursos.editarPropuestaCurso
		throw new UnsupportedOperationException();
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