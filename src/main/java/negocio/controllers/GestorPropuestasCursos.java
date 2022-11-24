package negocio.controllers;

import java.sql.SQLException;

import negocio.entities.*;

public class GestorPropuestasCursos {

	//TODO - crear excepcion para cuando operaciones sql devuelvan 0
	
	public void realizarPropuestaCurso(CursoPropio cursoPropuesto) throws SQLException {
		cursoPropuesto.cursoPropioDao.crearNuevoCurso(cursoPropuesto);
	}

	public void editarPropuestaCurso(CursoPropio cursoEditado) throws SQLException {
		cursoEditado.cursoPropioDao.editarCurso(cursoEditado);
	}

	public EstadoCurso evaluarPropuesta(CursoPropio curso) {
		// TODO - implement GestorPropuestasCursos.evaluarPropuesta
		throw new UnsupportedOperationException();
	}
	
	public void altaCursoAprobado(CursoPropio curso) {
		// TODO - es lo mismo que validar la propuesta??? Es decir, cambiar de PROPUESTA a VALIDADO
		// TODO - implement GestorPropuestasCursos.altaCursoAprobado
		throw new UnsupportedOperationException();
	}

}