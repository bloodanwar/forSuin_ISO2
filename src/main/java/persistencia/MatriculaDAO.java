package persistencia;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import negocio.entities.*;

public class MatriculaDAO {

//fecha, pagado, atributo, modoPago, cursoPropio_id, estudiante_dni

	public int crearNuevaMatricula(Matricula matricula) throws SQLException {
		Date fechaCreacion= new Date();
		Date fechaActualizacion = fechaCreacion;
		
		return GestorBD.getInstancia().insert("INSERT INTO matricula (fecha, pagado, atributo, modoPago, cursoPropio_id, estudiante_dni, fechaCreacion, fechaActualizacion) VALUES ('"
				+ matricula.getFecha()+"', '"
				+ matricula.isPagado()+"', '"
				+ matricula.getAttribute()+", '"
				+ matricula.tipoPago.toString()+ ", '"
				+ matricula.titulo.getId()+"', '"
				+ matricula.estudiante.getDni()+"', '"
				+ fechaCreacion+", "
				+ fechaActualizacion+")");
	}

	public Matricula seleccionarMatricula(Matricula matricula) throws SQLException { 
		
		Vector datosMatricula = GestorBD.getInstancia().select("SELECT * FROM matricula WHERE cursoPropio_id='"+matricula.titulo.getId()+"' AND estudiante_dni='"+matricula.estudiante.getDni()+"'");
		datosMatricula = (Vector) datosMatricula.get(0);

		Date fecha= (Date) datosMatricula.get(0);
		Boolean pagado = (Boolean) datosMatricula.get(1);
		int attribute = (Integer) datosMatricula.get(2);
		ModoPago tipoPago = ModoPago.valueOf((String) datosMatricula.get(3));
		CursoPropio cursoPropio = new CursoPropio((String) datosMatricula.get(4));
		Estudiante estudiante = new Estudiante((String) datosMatricula.get(5));
		
		Matricula matriculaDevolver = new Matricula(fecha, pagado, attribute, tipoPago, cursoPropio, estudiante);
		
		return matriculaDevolver;
	}
	
	public int editarMatricula(Matricula matricula) throws SQLException {
		Date fechaActualizacion = new Date();

		return GestorBD.getInstancia().update("UPDATE matricula SET "
				+ "fecha='" + matricula.getFecha() + "', "
				+ "pagado=" + matricula.isPagado() + ", "
				+ "attribute=" + matricula.getAttribute() + ", "
				+ "modopago=" + matricula.tipoPago.toString() + ", "
				+ "id=" + matricula.titulo.getId() + ", "
				+ "dni=" + matricula.estudiante.getDni() + ", "
				+ "fechaActualizacion=" + fechaActualizacion
				+ " WHERE id='"+matricula.titulo.getId()+"'");
	}

	public int eliminarMatricula(Matricula matricula) throws SQLException {
		return GestorBD.getInstancia().delete("DELETE FROM matricula WHERE id='"+matricula.titulo.getId()+"'");
	}

	public List<Matricula> listarMatriculasPorCurso(CursoPropio curso) throws SQLException {
		Vector matriculasDatos = GestorBD.getInstancia().select("SELECT * FROM matricula WHERE cursoPropio_id = '"+curso.getId()+"'");
		
		List <Matricula> listaMatriculas= null;
		
		for (int i=0; i<matriculasDatos.size(); i++){
			Vector matDatosTemp = (Vector) matriculasDatos.get(i);

			Date fecha= (Date) matDatosTemp.get(0);
			Boolean pagado = (Boolean) matDatosTemp.get(1);
			int attribute = (Integer) matDatosTemp.get(2);
			ModoPago tipoPago = ModoPago.valueOf((String) matDatosTemp.get(3));
			CursoPropio cursoPropio = new CursoPropio((String) matDatosTemp.get(4));
			Estudiante estudiante = new Estudiante((String) matDatosTemp.get(5));
			
			listaMatriculas.add(new Matricula(fecha, pagado, attribute, tipoPago, cursoPropio, estudiante));
		}
		
		return listaMatriculas; 
	}
}