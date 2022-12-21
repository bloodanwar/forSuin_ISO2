package persistencia;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.List;
import negocio.entities.*;

public class ProfesorDAO {

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public int crearNuevoProfesor(Profesor profesor) throws SQLException {
		Date fechaCreacion =  new Date();
		Date fechaActualizacion = fechaCreacion;
		
		return GestorBD.getInstancia().insert("INSERT INTO profesor (dni, nombre, apellidos, doctor, fechaCreacion, fechaActualizacion) VALUES ('"
			+ profesor.getDni()+"', '"
			+ profesor.getNombre()+"', '"
			+ profesor.getApellidos()+"', "
			+ profesor.isDoctor()+", '"
			+ dateFormat.format(fechaCreacion)+"', '"
			+ dateFormat.format(fechaActualizacion)+"')");
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
		Date fechaActualizacion = new Date();
		
		return GestorBD.getInstancia().update("UPDATE profesor SET "
				+ "nombre='" + profesor.getNombre() + "', "
				+ "apellidos='" + profesor.getApellidos() + "', "
				+ "doctor=" + profesor.isDoctor() + ", "
				+ "fechaActualizacion='" + dateFormat.format(fechaActualizacion)
				+ "' WHERE dni='"+profesor.getDni() + "'");
	}
	
	public int eliminarProfesor(Profesor profesor) throws SQLException {
		return GestorBD.getInstancia().delete("DELETE FROM profesor WHERE dni='"+profesor.getDni()+"'");
	}
	
	public List<Profesor> listarProfesores() throws SQLException {
		Vector profesoresDatos =  GestorBD.getInstancia().select("SELECT * FROM profesor");
		
		List<Profesor> listaProfes = new ArrayList<>();
		for (int i=0; i<profesoresDatos.size(); i++) {
			Vector profDatosTemp = (Vector) profesoresDatos.get(i);
			
			String dni=(String) profDatosTemp.get(0);
			String nombre=(String) profDatosTemp.get(1);
			String apellidos= (String) profDatosTemp.get(2);
			boolean doctor=(Boolean) profDatosTemp.get(3);
			
			listaProfes.add(new Profesor(dni, nombre, apellidos,doctor));
		}
		return listaProfes;
	}
}