package persistencia;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import negocio.entities.*;

public class EstudianteDAO {

	//dni, nombre, apellidos, titulacion, cualificacion
	
	public int crearNuevoEstudiante(Estudiante estudiante) throws SQLException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date fechaCreacion =  new Date();
		Date fechaActualizacion = fechaCreacion;
		
		return GestorBD.getInstancia().insert("INSERT INTO estudiante (dni, nombre, apellidos, titulacion, cualificacion, fechaCreacion, fechaActualizacion) VALUES ('"
				+ estudiante.getDni()+"', '"
				+ estudiante.getNombre()+"', '"
				+ estudiante.getApellidos()+"', "
				+ estudiante.getTitulacion()+"', '"
				+ estudiante.getCualificacion()+"', '"
				+ dateFormat.format(fechaCreacion)+"', '"
				+ dateFormat.format(fechaActualizacion)+"')");
	}

	public Estudiante seleccionarEstudiante(Estudiante estudiante) throws SQLException {
		Vector datosEstudiante = GestorBD.getInstancia().select("SELECT * FROM estudiante WHERE dni='"+estudiante.getDni()+"'");
		datosEstudiante = (Vector) datosEstudiante.get(0);
		
		String dni = (String) datosEstudiante.get(0);
		String nombre = (String) datosEstudiante.get(1);
		String apellidos = (String) datosEstudiante.get(2);
		String titulacion = (String) datosEstudiante.get(3);
		String cualificacion = (String) datosEstudiante.get(4);
		
		//TODO - Falta obtener las matriculas de este estudiante
		
		return new Estudiante(dni, nombre, apellidos, titulacion, cualificacion);
	}

	public int editarEstudiante(Estudiante estudiante) throws SQLException {
		//HABLAR CON RICARDO: el return type se ha cambiado a integer, originalmente era Estudiante
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date fechaActualizacion = new Date();

		return GestorBD.getInstancia().update("UPDATE estudiante SET "
				+ "nombre='" + estudiante.getNombre() + "', "
				+ "apellidos='" + estudiante.getApellidos() + "', "
				+ "titulacion='" + estudiante.getTitulacion() + "', "
				+ "cualificacion='" + estudiante.getCualificacion() + "', "
				+ "fechaActualizacion='" + dateFormat.format(fechaActualizacion)
				+ "' WHERE dni='"+estudiante.getDni()+"'");
	}

	public int eliminarEstudiante(Estudiante estudiante) throws SQLException {
		return GestorBD.getInstancia().delete("DELETE FROM estudiante WHERE dni='" + estudiante.getDni() + "'");
	}
}