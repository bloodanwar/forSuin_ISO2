package persistencia;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;

import negocio.controllers.MatriculaException.*;
import negocio.entities.*;

public class MatriculaDAO {

//fecha, pagado, atributo, modoPago, cursoPropio_id, estudiante_dni

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public int crearNuevaMatricula(Matricula matricula) throws SQLException {
		Date fechaCreacion= new Date();
		Date fechaActualizacion = fechaCreacion;
		
		String tipoPago = null;
		if (matricula.tipoPago != null) tipoPago = matricula.tipoPago.toString();
		
		return GestorBD.getInstancia().insert("INSERT INTO matricula (fecha, pagado, atributo, modoPago, cursoPropio_id, cursoPropio_edicion, estudiante_dni, fechaCreacion, fechaActualizacion) VALUES ('"
				+ dateFormat.format(matricula.getFecha())+"', "
				+ matricula.isPagado()+", "
				+ matricula.getAttribute()+", '"
				+ tipoPago+ "', '"
				+ matricula.titulo.getId()+"', "
				+ matricula.titulo.getEdicion()+", '"
				+ matricula.estudiante.getDni()+"', '"
				+ dateFormat.format(fechaCreacion)+"', '"
				+ dateFormat.format(fechaActualizacion)+"')");
	}

	public Matricula seleccionarMatricula(Matricula matricula) throws SQLException, MatriculaNoExisteException { 
		Vector datosMatricula = GestorBD.getInstancia().select("SELECT * FROM matricula WHERE cursoPropio_id='"+matricula.titulo.getId()+"' AND cursoPropio_edicion="+matricula.titulo.getEdicion()+" AND estudiante_dni='"+matricula.estudiante.getDni()+"'");
		if (datosMatricula.isEmpty())
			throw new MatriculaNoExisteException("No existe el curso seleccionado en la base de datos");
		
		datosMatricula = (Vector) datosMatricula.get(0);

		Date fecha= (Date) datosMatricula.get(0);
		Boolean pagado = (Boolean) datosMatricula.get(1);
		int attribute = (Integer) datosMatricula.get(2);
		ModoPago tipoPago = ModoPago.valueOf((String) datosMatricula.get(3));
		CursoPropio cursoPropio = new CursoPropio((String) datosMatricula.get(4), (int) datosMatricula.get(5));
		Estudiante estudiante = new Estudiante((String) datosMatricula.get(6));
		
		return new Matricula(fecha, pagado, attribute, tipoPago, cursoPropio, estudiante);
	}
	
	public int editarMatricula(Matricula matricula) throws SQLException {
		
		String tipoPago = null;
		if (matricula.tipoPago != null) tipoPago = matricula.tipoPago.toString();
		
		Date fechaActualizacion = new Date();

		return GestorBD.getInstancia().update("UPDATE matricula SET "
				+ "fecha='" + matricula.getFecha() + "', "
				+ "pagado=" + matricula.isPagado() + ", "
				+ "attribute=" + matricula.getAttribute() + ", "
				+ "modopago='" + tipoPago + "', "
				+ "dni='" + matricula.estudiante.getDni() + "', "
				+ "fechaActualizacion='" + dateFormat.format(fechaActualizacion)
				+ "' WHERE cursoPropio_id='"+matricula.titulo.getId()+"' AND cursoPropio_edicion="+matricula.titulo.getEdicion());
	}

	public int eliminarMatricula(Matricula matricula) throws SQLException {
		return GestorBD.getInstancia().delete("DELETE FROM matricula WHERE cursoPropio_id='"+matricula.titulo.getId()+"' AND cursoPropio_edicion="+matricula.titulo.getEdicion());
	}

	public List<Matricula> listarMatriculasPorCurso(CursoPropio curso) throws SQLException {
		Vector matriculasDatos = GestorBD.getInstancia().select("SELECT * FROM matricula WHERE cursoPropio_id = '"+curso.getId()+"'");
		
		List <Matricula> listaMatriculas=new ArrayList<>();
		
		for (int i=0; i<matriculasDatos.size(); i++){
			Vector matDatosTemp = (Vector) matriculasDatos.get(i);

			Date fecha= (Date) matDatosTemp.get(0);
			Boolean pagado = (Boolean) matDatosTemp.get(1);
			int attribute = (Integer) matDatosTemp.get(2);
			ModoPago tipoPago = ModoPago.valueOf((String) matDatosTemp.get(3));
			CursoPropio cursoPropio = new CursoPropio((String) matDatosTemp.get(4), (int) matDatosTemp.get(5));
			Estudiante estudiante = new Estudiante((String) matDatosTemp.get(6));
			
			listaMatriculas.add(new Matricula(fecha, pagado, attribute, tipoPago, cursoPropio, estudiante));
		}
		
		return listaMatriculas; 
	}
}