package persistencia;

import java.sql.SQLException;
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

	public Matricula seleccionarMatricula(Matricula matricula) throws SQLException {

	}

	public Matricula editarMatricula(Matricula matricula) throws SQLException {

	}

	public int eliminarMatricula(Matricula matricula) throws SQLException {

	}

	public List<Matricula> listarMatriculasPorCurso(CursoPropio curso) throws SQLException {

	}

}