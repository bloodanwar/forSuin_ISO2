package persistencia;

import negocio.entities.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

public class ProfesorUCLMDAO {

	//profesor_DNI, categoriaProfesor, centro_nombre, fechaCreacion, fechaActualizacion

	public int crearNuevoProfesorUCLM(ProfesorUCLM profesor) throws SQLException {
		Date fechaCreacion =  new Date();
		Date fechaActualizacion = fechaCreacion;
		
		return GestorBD.getInstancia().insert("INSERT INTO profesorUCLM (profesor_DNI, categoriaProfesor, centro_nombre, fechaCreacion, fechaActualizacion) VALUES ('"
				+ profesor.getDni()+"', '"
				+ profesor.categoria.toString()+"', ''"
				+ profesor.centroAdscripcion.getNombre()+"', "
				+ fechaCreacion+", "
				+ fechaActualizacion+")");	
	}

	public ProfesorUCLM seleccionarProfesorUCLM(ProfesorUCLM profesor) throws SQLException {
		Vector datosProfUCLM = GestorBD.getInstancia().select("SELECT * FROM profesorUCLM AS puclm JOIN profesor AS p ON puclm.profesor_DNI = p.dni WHERE puclm.profesor_DNI='"+profesor.getDni()+"'");
		datosProfUCLM = (Vector) datosProfUCLM.get(0);
		
		//columnas tras el join:
		//p.dni, p.nombre, p.apellidos, p.doctor, p.fechaCreacion, p.fechaActualizacion, puclm.profesor_DNI, puclm.categoriaProfesor, puclm.centro_nombre, puclm.fechaCreacion, puclm.fechaActualizacion		
		
		String dni=(String) datosProfUCLM.get(0);
		String nombre=(String) datosProfUCLM.get(1);
		String apellidos= (String) datosProfUCLM.get(2);
		boolean doctor=(Boolean) datosProfUCLM.get(3);
		CategoriaProfesor categoria = CategoriaProfesor.valueOf((String) datosProfUCLM.get(7));
		Centro centro = new Centro((String) datosProfUCLM.get(8));
		
		return new ProfesorUCLM(dni, nombre, apellidos, doctor, categoria, centro);
	}

	public int editarProfesorUCLM(ProfesorUCLM profesor) throws SQLException {
		Date fechaActualizacion=new Date();
		return GestorBD.getInstancia().update("UPDATE profesorUCLM SET "
				+ "categoriaProfesor='" +profesor.categoria.toString()+"','"
				+ "centro_nombre='"+profesor.centroAdscripcion.getNombre()+"',"
				+ "fechaActualizacion'"+fechaActualizacion
				+ " WHERE profesor_DNI='"+profesor.getDni()+"'");
	}

	public int eliminarProfesorUCLM(ProfesorUCLM profesor) throws SQLException {
		return GestorBD.getInstancia().delete("DELETE FROM profesorUCLM WHERE profesor_DNI='"+profesor.getDni()+"'");
	}
}