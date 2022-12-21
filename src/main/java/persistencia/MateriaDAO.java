package persistencia;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import negocio.entities.*;

public class MateriaDAO {

	//nombre, horas, fechaInicio, fechaFin, cursoPropio_id, cursoPropio_edicion, responsable_Profesor_DNI, fechaCreacion, fechaActualizacion
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public int crearNuevaMateria(Materia materia, String cursoPropioID, int cursoPropioEdicion) throws SQLException {
		Date fechaCreacion =  new Date();
		Date fechaActualizacion = fechaCreacion;
		
		return GestorBD.getInstancia().insert("INSERT INTO materia (nombre, horas, fechaInicio, fechaFin, cursoPropio_id, cursoPropio_edicion, responsable_Profesor_DNI, fechaCreacion, fechaActualizacion) VALUES ('"
				+ materia.getNombre()+"', "
				+ materia.getHoras()+", '"
				+ dateFormat.format(materia.getFechaInicio())+"', '"
				+ dateFormat.format(materia.getFechaFin())+"', '"
				+ cursoPropioID+"', "
				+ cursoPropioEdicion+", '"
				+ materia.responsable.getDni()+"', '"
				+ dateFormat.format(fechaCreacion)+"', '"
				+ dateFormat.format(fechaActualizacion)+"')");
	}

	public Materia seleccionarMateria(Materia materia, String cursoPropioID, int cursoPropioEdicion) throws SQLException {
		Vector datosMateria = GestorBD.getInstancia().select("SELECT * FROM materia WHERE nombre='"+materia.getNombre()+"' AND cursoPropio_id='"+cursoPropioID+"' AND cursoPropioEdicion="+cursoPropioEdicion);
		datosMateria = (Vector) datosMateria.get(0);
		
		String nombre = (String) datosMateria.get(0);
		double horas = (Double) datosMateria.get(1);
		Date fechaInicio = (Date) datosMateria.get(2);
		Date fechaFin = (Date) datosMateria.get(3);
		Profesor responsable = new Profesor((String) datosMateria.get(6));
		
		return new Materia(nombre, horas, fechaInicio, fechaFin, responsable);
	}

	public int editarMateria(Materia materia, String cursoPropioID, int cursoPropioEdicion) throws SQLException {
		Date fechaActualizacion = new Date();

		return GestorBD.getInstancia().update("UPDATE materia SET "
				+ "horas=" + materia.getHoras() + ", "
				+ "fechaInicio='" + dateFormat.format(materia.getFechaInicio()) + "', "
				+ "fechaFin='" + dateFormat.format(materia.getFechaFin()) + "', "
				+ "responsable_Profesor_DNI='" + materia.responsable.getDni() + "', '"
				+ "fechaActualizacion='" + dateFormat.format(fechaActualizacion)
				+ "' WHERE nombre='"+materia.getNombre()+"' AND cursoPropio_id='"+cursoPropioID+"' AND cursoPropio_edicion="+cursoPropioEdicion);
	}

	public int eliminarMateria(Materia materia, String cursoPropioID, int cursoPropioEdicion) throws SQLException {
		return GestorBD.getInstancia().delete("DELETE FROM materia WHERE nombre='"+materia.getNombre()+"' AND cursoPropio_id='"+cursoPropioID+"' AND cursoPropio_edicion="+cursoPropioEdicion);
	}

	public List<Materia> listarMateriasPorCurso(CursoPropio curso) throws SQLException {
		Vector listaMateriaDatos = GestorBD.getInstancia().select("SELECT * FROM materia WHERE cursoPropio_id = '" + curso.getId() + "' AND cursoPropio_edicion="+curso.getEdicion());
		
		List<Materia> listaMateria = new ArrayList<>();
		
		for (int i=0; i<listaMateriaDatos.size(); i++) {
			Vector lMateriaDatosTemp = (Vector) listaMateriaDatos.get(i);
			
			String nombre = (String) lMateriaDatosTemp.get(0);
			double horas = (Double) lMateriaDatosTemp.get(1);
			Date fechaInicio = (Date) lMateriaDatosTemp.get(2);
			Date fechaFin = (Date) lMateriaDatosTemp.get(3);
			Profesor responsable = new Profesor((String) lMateriaDatosTemp.get(6));
			
			listaMateria.add(new Materia(nombre, horas, fechaInicio, fechaFin, responsable));
		}
		
		return listaMateria;
	}
}