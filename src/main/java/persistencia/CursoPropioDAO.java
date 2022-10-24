package persistencia;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import negocio.entities.*;

public class CursoPropioDAO {

	public int crearNuevoCurso(CursoPropio curso) throws SQLException {
		Date fechaCreacion =  new Date();
		Date fechaActualizacion = fechaCreacion;

		return GestorBD.getInstancia().insert("INSERT INTO cursoPropio (id, nombre, ECTS, fechaInicio, fechaFin, tasaMatricula, edicion, estadoCurso, tipoCurso, centro_nombre, secretario_Profesor_DNI, director_Profesor_DNI, fechaCreacion, fechaActualizacion) VALUES ('"
				+ curso.getId()+"', '"
				+ curso.getNombre()+"', "
				+ curso.getECTS()+", "
				+ curso.getFechaInicio()+", "
				+ curso.getFechaFin()+", "
				+ curso.getTasaMatricula()+", '"
				+ curso.getEstado().toString()+"', '"
				+ curso.getTipo().toString()+"', '"
				+ curso.getCentro().getNombre()+"', '"
				+ curso.getSecretario().getDni()+"', '"
				+ curso.getDirector().getDni()+"', '"
				+ fechaCreacion+", "
				+ fechaActualizacion+")");
	}

	/**
	 * 
	 * @param curso
	 * @throws SQLException 
	 */
	public CursoPropio seleccionarCurso(CursoPropio curso) throws SQLException {
		Vector datosCurso = GestorBD.getInstancia().select("SELECT * FROM cursoPropio WHERE id='"+curso.getNombre()+"'");
		datosCurso = (Vector) datosCurso.get(0);

		String id = (String) datosCurso.get(0);
		String nombre = (String) datosCurso.get(1);
		int ECTS = (Integer) datosCurso.get(2);
		Date fechainicio = (Date) datosCurso.get(3);
		Date fechafin = (Date) datosCurso.get(4);
		double tasaMatricula = (Double) datosCurso.get(5);
		int edicion = (Integer) datosCurso.get(6);
		EstadoCurso estado = EstadoCurso.valueOf((String) datosCurso.get(7));
		TipoCurso tipo = TipoCurso.valueOf((String) datosCurso.get(8));
		Centro centro = new Centro((String) datosCurso.get(9));
		ProfesorUCLM secretario = new ProfesorUCLM((String) datosCurso.get(10));
		ProfesorUCLM director = new ProfesorUCLM((String) datosCurso.get(11));

		return new CursoPropio(id, nombre, ECTS, fechainicio, fechafin, tasaMatricula, edicion, estado, tipo, centro, secretario, director);
	}

	/**
	 * 
	 * @param curso
	 * @throws SQLException 
	 */
	public int editarCurso(CursoPropio curso) throws SQLException {
		//HABLAR CON RICARDO: el return type se ha cambiado a integer, originalmente era CursoPropio

		Date fechaActualizacion = new Date();

		return GestorBD.getInstancia().update("UPDATE cursoPropio SET "
				+ "nombre='" + curso.getNombre() + "', "
				+ "ECTS=" + curso.getECTS() + ", "
				+ "fechaInicio=" + curso.getFechaInicio() + ", "
				+ "fechaFin=" + curso.getFechaFin() + ", "
				+ "tasaMatricula=" + curso.getTasaMatricula() + ", "
				+ "edicion=" + curso.getEdicion() + ", "
				+ "estadoCurso='" + curso.getEstado().toString() + "', "
				+ "tipoCurso='" + curso.getTipo().toString() + "', "
				+ "centro_nombre=" + curso.getCentro().getNombre() + ", "
				+ "secretario_Profesor_DNI='" + curso.getSecretario().getDni() + "', "
				+ "director_Profesor_DNI='" + curso.getDirector().getDni() + "', "
				+ "fechaActualizacion=" + fechaActualizacion
				+ " WHERE id='"+curso.getId()+"'");
	}

	public List<CursoPropio> listarCursosPorEstado(EstadoCurso estado, Date fechaInicio, Date fechaFin) {

	}


	public double listarIngresos(TipoCurso tipo, Date fechaInicio, Date fechaFin) {

	}

	public List<CursoPropio> listarEdicionesCursos(Date fechaInicio, Date fechaFin) throws SQLException {
		Vector listaEdicicionDatos = GestorBD.getInstancia().select("SELECT * FROM cursoPropio WHERE fechaInicio = " + fechaInicio + " AND fechaFin = " + fechaFin); //buscar fecha y eso
		
		List<CursoPropio> listaEdiciones = null;
		
		for (int i=0; i<listaEdicicionDatos.size(); i++) {
			Vector lEdicnDatosTemp = (Vector) listaEdicicionDatos.get(i);
			
			String id = (String) lEdicnDatosTemp.get(0);
			String nombre = (String) lEdicnDatosTemp.get(1);
			int ECTS = (Integer) lEdicnDatosTemp.get(2);
			Date fechainicio = (Date) lEdicnDatosTemp.get(3);
			Date fechafin = (Date) lEdicnDatosTemp.get(4);
			double tasaMatricula = (Double) lEdicnDatosTemp.get(5);
			int edicion = (Integer) lEdicnDatosTemp.get(6);
			EstadoCurso estado = EstadoCurso.valueOf((String) lEdicnDatosTemp.get(7));
			TipoCurso tipo = TipoCurso.valueOf((String) lEdicnDatosTemp.get(8));
			Centro centro = new Centro((String) lEdicnDatosTemp.get(9));
			ProfesorUCLM secretario = new ProfesorUCLM((String) lEdicnDatosTemp.get(10));
			ProfesorUCLM director = new ProfesorUCLM((String) lEdicnDatosTemp.get(11));
					
			listaEdiciones.add(new CursoPropio(id, nombre, ECTS, fechainicio, fechafin, tasaMatricula, edicion, estado, tipo, centro, secretario, director));
		}
		
		return listaEdiciones;
	}
}