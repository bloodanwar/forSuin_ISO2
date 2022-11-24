package negocio.controllers;

import java.sql.SQLException;
import java.util.Date;

import negocio.entities.*;

public class GestorMatriculacion {

	//TODO - crear excepcion para cuando operaciones sql devuelvan 0
	
	public void realizarMatriculacion(CursoPropio curso, Estudiante estudiante) throws SQLException {
		Date fecha = new Date();
		Matricula matricula = new Matricula(fecha, false, 0, null, curso, estudiante);
		matricula.matriculaDAO.crearNuevaMatricula(matricula);
	}

	public void realizarPagoMatricula(CursoPropio curso, Estudiante estudiante) throws SQLException {
		Matricula matricula = new Matricula(estudiante, curso);
		matricula.matriculaDAO.seleccionarMatricula(matricula);
		matricula.setPagado(true);
		matricula.matriculaDAO.editarMatricula(matricula);
	}

	private void realizarPagoTarjeta(CursoPropio curso, Estudiante estudiante) throws SQLException {
		Matricula matricula = new Matricula(estudiante, curso);
		matricula.matriculaDAO.seleccionarMatricula(matricula);
		matricula.tipoPago = ModoPago.TARJETA_CREDITO;
		matricula.matriculaDAO.editarMatricula(matricula);
	}

	private void realizarPagoTransferencia(CursoPropio curso, Estudiante estudiante) throws SQLException {
		Matricula matricula = new Matricula(estudiante, curso);
		matricula.matriculaDAO.seleccionarMatricula(matricula);
		matricula.tipoPago = ModoPago.TRANSFERENCIA;
		matricula.matriculaDAO.editarMatricula(matricula);
	}

	private void operation() {
		// TODO - implement GestorMatriculacion.operation
		throw new UnsupportedOperationException();
	}

}