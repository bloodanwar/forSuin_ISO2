package persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import negocio.entities.*;

public class MateriaDAO {

	//nombre, horas, fechaInicio, fechaFin, cursoPropio_id, responsable_Profesor_DNI, fechaCreacion, fechaActualizacion
	
	public int crearNuevaMateria(Materia materia, String cursoPropioID) throws SQLException {
		Date fechaCreacion =  new Date();
		Date fechaActualizacion = fechaCreacion;

		return GestorBD.getInstancia().insert("INSERT INTO materia (nombre, horas, fechaInicio, fechaFin, cursoPropio_id, responsable_Profesor_DNI, fechaCreacion, fechaActualizacion) VALUES ('"
				+ materia.getNombre()+"', "
				+ materia.getHoras()+", "
				+ materia.getFechaInicio()+", "
				+ materia.getFechaFin()+", '"
				+ cursoPropioID+"', '"
				+ materia.responsable.getDni()+"', "
				+ fechaCreacion+", "
				+ fechaActualizacion+")");
	}

	public Materia seleccionarMateria(Materia materia) throws SQLException {
		Vector datosMateria = GestorBD.getInstancia().select("SELECT * FROM materia WHERE nombre='"+materia.getNombre()+"'");
		datosMateria = (Vector) datosMateria.get(0);
		
		String nombre = (String) datosMateria.get(0);
		double horas = (Double) datosMateria.get(1);
		Date fechaInicio = (Date) datosMateria.get(2);
		Date fechaFin = (Date) datosMateria.get(3);
		Profesor responsable = new Profesor((String) datosMateria.get(5));
		
		return new Materia(nombre, horas, fechaInicio, fechaFin, responsable);
	}

	public int editarMateria(Materia materia, String cursoPropioID) throws SQLException {
		//HABLAR CON RICARDO: el return type se ha cambiado a integer, originalmente era Materia

		Date fechaActualizacion = new Date();

		return GestorBD.getInstancia().update("UPDATE materia SET "
				+ "horas=" + materia.getHoras() + ", "
				+ "fechaInicio=" + materia.getFechaInicio() + ", "
				+ "fechaFin=" + materia.getFechaFin() + ", "
				+ "cursoPropio_id='" + cursoPropioID + "', "
				+ "responsable_Profesor_DNI='" + materia.responsable.getDni() + "', "
				+ "fechaActualizacion=" + fechaActualizacion
				+ " WHERE nombre='"+materia.getNombre()+"'");
	}

	public int eliminarMateria(Materia materia) throws SQLException {
		return GestorBD.getInstancia().delete("DELETE FROM materia WHERE nombre='"+materia.getNombre()+	"'");
	}

	public List<Materia> listarMateriasPorCurso(CursoPropio curso) throws SQLException {
		Vector listaMateriaDatos = GestorBD.getInstancia().select("SELECT * FROM materia WHERE cursoPropio_id = '" + curso.getId() + "'");
		
		List<Materia> listaMateria = new ArrayList<>();
		
		for (int i=0; i<listaMateriaDatos.size(); i++) {
			Vector lMateriaDatosTemp = (Vector) listaMateriaDatos.get(i);
			
			String nombre = (String) lMateriaDatosTemp.get(0);
			double horas = (Double) lMateriaDatosTemp.get(1);
			Date fechaInicio = (Date) lMateriaDatosTemp.get(2);
			Date fechaFin = (Date) lMateriaDatosTemp.get(3);
			Profesor responsable = new Profesor((String) lMateriaDatosTemp.get(5));
			
			listaMateria.add(new Materia(nombre, horas, fechaInicio, fechaFin, responsable));
		}
		
		return listaMateria;
	}

}