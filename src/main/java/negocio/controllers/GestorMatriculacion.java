package negocio.controllers;

import java.sql.SQLException;
import java.util.Date;

import negocio.controllers.MatriculaException.*;
import negocio.entities.*;

public class GestorMatriculacion {

	//TODO - crear excepcion para cuando operaciones sql devuelvan 0
	
	public void realizarMatriculacion(CursoPropio curso, Estudiante estudiante) throws SQLException, MatriculaNoCreadaException, MatriculaErroneaException {
		Date fecha = new Date();
		Matricula matricula = new Matricula(fecha, false, 0, null, curso, estudiante);
		comprobarSiTieneTituloEstudiante(matricula);
		if (matricula.matriculaDAO.crearNuevaMatricula(matricula) == 1) {
			throw new MatriculaNoCreadaException("Matrícula no creada");
		}
	}

	public void realizarPagoMatricula(CursoPropio curso, Estudiante estudiante) throws SQLException, MatriculaNoEditadaException {
		Matricula matricula = new Matricula(estudiante, curso);
		matricula.matriculaDAO.seleccionarMatricula(matricula);
		matricula.setPagado(true);
		if (matricula.matriculaDAO.editarMatricula(matricula) == 0) {
			throw new MatriculaNoEditadaException("No se ha podido actualizar el estado del pago");
		}
	}

	public void realizarPagoTarjeta(CursoPropio curso, Estudiante estudiante) throws SQLException, MatriculaNoEditadaException {
		Matricula matricula = new Matricula(estudiante, curso);
		matricula.matriculaDAO.seleccionarMatricula(matricula);
		matricula.tipoPago = ModoPago.TARJETA_CREDITO;
		if (matricula.matriculaDAO.editarMatricula(matricula) == 0) {
			throw new MatriculaNoEditadaException("No se ha podido actualizar el modo de pago");
		}
	}

	public void realizarPagoTransferencia(CursoPropio curso, Estudiante estudiante) throws SQLException, MatriculaNoEditadaException {
		Matricula matricula = new Matricula(estudiante, curso);
		matricula.matriculaDAO.seleccionarMatricula(matricula);
		matricula.tipoPago = ModoPago.TRANSFERENCIA;
		if (matricula.matriculaDAO.editarMatricula(matricula) == 0) {
			throw new MatriculaNoEditadaException("No se ha podido actualizar el modo de pago");
		}
	}
	
	private void comprobarSiTieneTituloEstudiante(Matricula matricula) throws MatriculaErroneaException {
		boolean tituloMatriculaError = false;
		boolean estudianteMatriculaError = false;
		
		if (matricula.titulo==null || matricula.titulo.getId().equals("") || matricula.titulo.getId() == null || matricula.titulo.getEdicion()<=0) {
			tituloMatriculaError = true;
		}
		if (matricula.estudiante == null || matricula.estudiante.getDni().equals("") || matricula.estudiante.getDni()==null) {
			estudianteMatriculaError = true;
		}
		
		if(!tituloMatriculaError && !estudianteMatriculaError) {
			return;
		} else if (tituloMatriculaError && estudianteMatriculaError) {
			throw new MatriculaErroneaException("Matricula no tiene título ni estudiante");
		} else {
			if (tituloMatriculaError) throw new MatriculaErroneaException("Matricula no tiene título");
			if (estudianteMatriculaError) throw new MatriculaErroneaException("Matricula no tiene estudiante");
		}
	}


}