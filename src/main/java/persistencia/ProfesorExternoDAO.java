package persistencia;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import negocio.entities.*;

public class ProfesorExternoDAO {
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public int crearNuevoProfesorExterno(ProfesorExterno profesor) throws SQLException {

		Date fechaCreacion =  new Date();
		Date fechaActualizacion = fechaCreacion;

		return GestorBD.getInstancia().insert("INSERT INTO profesorExterno (profesor_Dni, titulacion, fechaCreacion, fechaActualizacion) VALUES ('"
			+ profesor.getDni()+"', "
			+ profesor.getTitulacion()+"', '"
			+ dateFormat.format(fechaCreacion)+"', '"
			+ dateFormat.format(fechaActualizacion)+"')");
	}

	public ProfesorExterno seleccionarProfesorExterno(ProfesorExterno profesor) throws SQLException {
		Vector datosProfesorExterno = GestorBD.getInstancia().select("SELECT * FROM profesorExterno AS pex JOIN profesor AS p ON pex.profesor_DNI = p.dni WHERE pex.profesor_DNI='"+profesor.getDni()+"'");
		datosProfesorExterno = (Vector) datosProfesorExterno.get(0);
		
		//columnas tras el join:
		//p.dni, p.nombre, p.apellidos, p.doctor, p.fechaCreacion, p.fechaActualizacion, pex.profesor_Dni, pex.titulacion, pex.fechaCreacion, pex.fechaActualizacion
		
		String dni=(String) datosProfesorExterno.get(0);
		String nombre=(String) datosProfesorExterno.get(1);
		String apellidos= (String) datosProfesorExterno.get(2);
		boolean doctor=(Boolean) datosProfesorExterno.get(3);
		String titulacion=(String) datosProfesorExterno.get(7);
		
		return new ProfesorExterno(dni, nombre, apellidos, doctor, titulacion);
	}

	public int editarProfesorExterno(ProfesorExterno profesor) throws SQLException {
		Date fechaActualizacion = new Date();

		return GestorBD.getInstancia().update("UPDATE profesorExterno SET "
				+ "dni'" + profesor.getDni() + "', "
				+ "titulacion=" + profesor.getTitulacion() + ", "
				+ "fechaActualizacion='" + dateFormat.format(fechaActualizacion)
				+ "' WHERE dni='"+profesor.getDni()+"'");
	}

	public int eliminarProfesorExterno(ProfesorExterno profesor) throws SQLException {
		return GestorBD.getInstancia().delete("DELETE FROM profesorExterno WHERE profesor_DNI='"+profesor.getDni()+"'");
	}
}