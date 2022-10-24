package persistencia;

import java.util.Date;
import java.util.List;

import negocio.entities.*;

public class MatriculaDAO {

//fecha, pagado, atributo, modoPago, cursoPropio_id, estudiante_dni

	public int crearNuevaMatricula(Matricula matricula) throws SQLException {
		Date fechaCreacion= new Date();
		Date fechaActualizacion = fechaCreacion;
		
		return GestorBD.getInstancia().insert("INSERT INTO matricula (fecha, pagado, atributo, modoPago, cursopROPIO_id, estudiante_dni) VALUES ('"
				+matricula.get
	}

	/**
	 * 
	 * @param matricula
	 */
	public Matricula seleccionarMatricula(Matricula matricula) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param matricula
	 */
	public Matricula editarMatricula(Matricula matricula) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param matricula
	 */
	public int eliminarMatricula(Matricula matricula) {
		// TODO - implement MatriculaDAO.eliminarMatricula
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param curso
	 */
	public List<Matricula> listarMatriculasPorCurso(CursoPropio curso) {
		// TODO - implement MatriculaDAO.listarMatriculasPorCurso
		throw new UnsupportedOperationException();
	}

}