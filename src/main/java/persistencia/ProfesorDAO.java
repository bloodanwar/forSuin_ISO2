package persistencia;

import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import negocio.entities.*;

public class ProfesorDAO {

	public int crearNuevoProfesor(Profesor profesor) throws SQLException {
		Date fechaCreacion =  new Date();
		Date fechaActualizacion = fechaCreacion;
		
		return GestorBD.getInstancia().insert("INSERT INTO profesor (dni, nombre, apellidos, doctor, fechaCreacion, fechaActualizacion) VALUES ('"
		+ profesor.getDni()+"', '"
		+ profesor.getNombre()+"', '"
		+ profesor.getApellidos()+"', "
		+ profesor.isDoctor()+", "
		+ fechaCreacion+", "
		+ fechaActualizacion+")");
	}

	public Profesor seleccionarProfesor(Profesor profesor) throws SQLException {
		Vector datosProfesor = GestorBD.getInstancia().select("SELECT * FROM profesor WHERE dni='"+profesor.getDni()+"'");
		
		datosProfesor = (Vector) datosProfesor.get(0);
		
		String dni=(String) datosProfesor.get(0);
		String nombre=(String) datosProfesor.get(1);
		String apellidos= (String) datosProfesor.get(2);
		boolean doctor=(Boolean) datosProfesor.get(3);
		
		return new Profesor(dni, nombre, apellidos, doctor);
	}

	public int editarProfesor(Profesor profesor) throws SQLException {
		//HABLAR CON RICARDO: el return type se ha cambiado a integer, originalmente era Profesor
		
		Date fechaActualizacion = new Date();
		
		return GestorBD.getInstancia().update("UPDATE profesor SET "
				+ "nombre='" + profesor.getNombre() + "', "
				+ "apellidos='" + profesor.getApellidos() + "', "
				+ "doctor=" + profesor.isDoctor() + ", "
				+ "fechaActualizacion=" + fechaActualizacion
				+ " WHERE dni='"+profesor.getDni());
	}
	
	public int eliminarProfesor(Profesor profesor) throws SQLException {
		return GestorBD.getInstancia().delete("DELETE FROM profesor WHERE dni='"+profesor.getDni()+"'");
	}
}