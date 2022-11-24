package persistencia;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;

import negocio.entities.*;

public class CursoPropioDAO {

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public int crearNuevoCurso(CursoPropio curso) throws SQLException {
		Date fechaCreacion =  new Date();
		Date fechaActualizacion = fechaCreacion;

		int contador = 0;
		
		contador += GestorBD.getInstancia().insert("INSERT INTO cursoPropio (id, nombre, ECTS, fechaInicio, fechaFin, tasaMatricula, edicion, estadoCurso, tipoCurso, centro_nombre, secretario_Profesor_DNI, director_Profesor_DNI, fechaCreacion, fechaActualizacion) VALUES ('"
				+ curso.getId()+"', '"
				+ curso.getNombre()+"', "
				+ curso.getECTS()+", '"
				+ dateFormat.format(curso.getFechaInicio())+"', '"
				+ dateFormat.format(curso.getFechaFin())+"', "
				+ curso.getTasaMatricula()+", "
				+ curso.getEdicion()+", '"
				+ curso.estado.toString()+"', '"
				+ curso.tipo.toString()+"', '"
				+ curso.centro.getNombre()+"', '"
				+ curso.secretario.getDni()+"', '"
				+ curso.director.getDni()+"', '"
				+ dateFormat.format(fechaCreacion)+"', '"
				+ dateFormat.format(fechaActualizacion)+"')");
		
		Materia[] materias = curso.materias.toArray(new Materia[curso.materias.size()]);

		for (int i=0; i<materias.length; i++){
			contador += materias[i].materiaDao.crearNuevaMateria(materias[i], curso.getId());
		}
		
		if (contador == curso.materias.size() + 1){
			return 1;	
		} else {
			return 0;
		}
	}

	public CursoPropio seleccionarCurso(CursoPropio curso) throws SQLException, ParseException {
		Vector datosCurso = GestorBD.getInstancia().select("SELECT * FROM cursoPropio WHERE id='"+curso.getNombre()+"'");
		datosCurso = (Vector) datosCurso.get(0);

		String id = (String) datosCurso.get(0);
		String nombre = (String) datosCurso.get(1);
		int ECTS = (Integer) datosCurso.get(2);
		Date fechainicio = dateFormat.parse((String) datosCurso.get(3));
		Date fechafin = dateFormat.parse((String) datosCurso.get(4));
		double tasaMatricula = (Double) datosCurso.get(5);
		int edicion = (Integer) datosCurso.get(6);
		EstadoCurso estado = EstadoCurso.valueOf((String) datosCurso.get(7));
		TipoCurso tipo = TipoCurso.valueOf((String) datosCurso.get(8));
		Centro centro = new Centro((String) datosCurso.get(9));
		ProfesorUCLM secretario = new ProfesorUCLM((String) datosCurso.get(10));
		ProfesorUCLM director = new ProfesorUCLM((String) datosCurso.get(11));
		
		CursoPropio cursoDevolver = new CursoPropio(id, nombre, ECTS, fechainicio, fechafin, tasaMatricula, edicion, estado, tipo, centro, secretario, director);
		
		List<Matricula> matriculasCurso = new Matricula().matriculaDAO.listarMatriculasPorCurso(cursoDevolver);
		cursoDevolver.matriculas = matriculasCurso;
		
		return curso;
	}

	public int editarCurso(CursoPropio curso) throws SQLException {
		//HABLAR CON RICARDO: el return type se ha cambiado a integer, originalmente era CursoPropio
		Date fechaActualizacion = new Date();

		int contador = 0;
		
		Materia[] materias = curso.materias.toArray(new Materia[curso.materias.size()]);
		for (int i=0; i<materias.length; i++){
			contador += materias[i].materiaDao.editarMateria(materias[i], curso.getId());
		}
		
		contador+= GestorBD.getInstancia().update("UPDATE cursoPropio SET "
				+ "nombre='" + curso.getNombre() + "', "
				+ "ECTS=" + curso.getECTS() + ", "
				+ "fechaInicio='" + dateFormat.format(curso.getFechaInicio()) + "', "
				+ "fechaFin='" + dateFormat.format(curso.getFechaFin()) + "', "
				+ "tasaMatricula=" + curso.getTasaMatricula() + ", "
				+ "edicion=" + curso.getEdicion() + ", "
				+ "estadoCurso='" + curso.estado.toString() + "', "
				+ "tipoCurso='" + curso.tipo.toString() + "', "
				+ "centro_nombre=" + curso.centro.getNombre() + ", "
				+ "secretario_Profesor_DNI='" + curso.secretario.getDni() + "', "
				+ "director_Profesor_DNI='" + curso.director.getDni() + "', "
				+ "fechaActualizacion='" + dateFormat.format(fechaActualizacion)
				+ "' WHERE id='"+curso.getId()+"'");
		
		if (contador == curso.materias.size() + 1){
			return 1;	
		} else {
			return 0;
		}
	}
	
	public int eliminarCursoPropio(CursoPropio curso) throws SQLException {
		int contador = 0;
		
		Materia[] materias = curso.materias.toArray(new Materia[curso.materias.size()]);
		for (int i=0; i<materias.length; i++){
			contador += materias[i].materiaDao.eliminarMateria(materias[i],curso.getId());
		}
		
		contador+= GestorBD.getInstancia().delete("DELETE FROM cursoPropio WHERE id='"+curso.getId()+"'");
		if (contador == curso.materias.size() + 1){
			return 1;	
		} else {
			return 0;
		}
	}

	public List<CursoPropio> listarCursos() throws SQLException {
		Vector cursosDatos=  GestorBD.getInstancia().select("SELECT * FROM cursoPropio");
		List <CursoPropio> listaCursos=new ArrayList<>();
		
		for(int i=0; i<cursosDatos.size(); i++) {
			Vector curDatosTemp=(Vector) cursosDatos.get(i);
			
			String id = (String) curDatosTemp.get(0);
			String nombre = (String) curDatosTemp.get(1);
			int ECTS = (Integer)curDatosTemp.get(2);
			Date fechainicio = (Date) curDatosTemp.get(3);
			Date fechafin = (Date) curDatosTemp.get(4);
			double tasaMatricula = (Double) curDatosTemp.get(5);
			int edicion = (Integer) curDatosTemp.get(6);
			EstadoCurso estadoObtenido = EstadoCurso.valueOf((String) curDatosTemp.get(7));
			TipoCurso tipo = TipoCurso.valueOf((String) curDatosTemp.get(8));
			Centro centro = new Centro((String) curDatosTemp.get(9));
			ProfesorUCLM secretario = new ProfesorUCLM((String) curDatosTemp.get(10));
			ProfesorUCLM director = new ProfesorUCLM((String) curDatosTemp.get(11));
			
			CursoPropio cursoDevolver = new CursoPropio(id, nombre, ECTS, fechainicio, fechafin, tasaMatricula, edicion, estadoObtenido, tipo, centro, secretario, director);
			
			List<Matricula> matriculasCurso = new Matricula().matriculaDAO.listarMatriculasPorCurso(cursoDevolver);
			cursoDevolver.matriculas = matriculasCurso;
			
			listaCursos.add(cursoDevolver);
		}
		
		return listaCursos;

	}

	public List<CursoPropio> listarCursosPorEstado(EstadoCurso estado, Date fechaInicio, Date fechaFin) throws SQLException {
		Vector cursosDatos=  GestorBD.getInstancia().select("SELECT * FROM cursoPropio WHERE estadoCurso = '"+estado.toString()+"' AND fechaInicio >= '" + dateFormat.format(fechaInicio) + "' AND fechaFin <= '" + dateFormat.format(fechaFin) + "'");
		List <CursoPropio> listaCursos=new ArrayList<>();
		
		for(int i=0; i<cursosDatos.size(); i++) {
			Vector curDatosTemp=(Vector) cursosDatos.get(i);
			
			String id = (String) curDatosTemp.get(0);
			String nombre = (String) curDatosTemp.get(1);
			int ECTS = (Integer)curDatosTemp.get(2);
			Date fechainicio = (Date) curDatosTemp.get(3);
			Date fechafin = (Date) curDatosTemp.get(4);
			double tasaMatricula = (Double) curDatosTemp.get(5);
			int edicion = (Integer) curDatosTemp.get(6);
			EstadoCurso estadoObtenido = EstadoCurso.valueOf((String) curDatosTemp.get(7));
			TipoCurso tipo = TipoCurso.valueOf((String) curDatosTemp.get(8));
			Centro centro = new Centro((String) curDatosTemp.get(9));
			ProfesorUCLM secretario = new ProfesorUCLM((String) curDatosTemp.get(10));
			ProfesorUCLM director = new ProfesorUCLM((String) curDatosTemp.get(11));
			
			CursoPropio cursoDevolver = new CursoPropio(id, nombre, ECTS, fechainicio, fechafin, tasaMatricula, edicion, estadoObtenido, tipo, centro, secretario, director);
			
			List<Matricula> matriculasCurso = new Matricula().matriculaDAO.listarMatriculasPorCurso(cursoDevolver);
			cursoDevolver.matriculas = matriculasCurso;
			
			listaCursos.add(cursoDevolver);
		}
	
		return listaCursos;	
	}
	
	public List<CursoPropio> listarCursosPorDirector(ProfesorUCLM director, Date fechaInicio, Date fechaFin) throws SQLException {
		Vector cursosDatos=  GestorBD.getInstancia().select("SELECT * FROM cursoPropio WHERE director_Profesor_DNI = '"+director.getDni()+"' AND fechaInicio >= '" + dateFormat.format(fechaInicio) + "' AND fechaFin <= '" + dateFormat.format(fechaFin) + "'");
		List <CursoPropio> listaCursos=new ArrayList<>();
		
		for(int i=0; i<cursosDatos.size(); i++) {
			Vector curDatosTemp=(Vector) cursosDatos.get(i);
			
			String id = (String) curDatosTemp.get(0);
			String nombre = (String) curDatosTemp.get(1);
			int ECTS = (Integer) curDatosTemp.get(2);
			Date fechainicio = (Date) curDatosTemp.get(3);
			Date fechafin = (Date) curDatosTemp.get(4);
			double tasaMatricula = (Double) curDatosTemp.get(5);
			int edicion = (Integer) curDatosTemp.get(6);
			EstadoCurso estado = EstadoCurso.valueOf((String) curDatosTemp.get(7));
			TipoCurso tipo = TipoCurso.valueOf((String) curDatosTemp.get(8));
			Centro centro = new Centro((String) curDatosTemp.get(9));
			ProfesorUCLM secretario = new ProfesorUCLM((String) curDatosTemp.get(10));
			ProfesorUCLM directorObtenido = new ProfesorUCLM((String) curDatosTemp.get(11));
			
			CursoPropio cursoDevolver = new CursoPropio(id, nombre, ECTS, fechainicio, fechafin, tasaMatricula, edicion, estado, tipo, centro, secretario, directorObtenido);
			
			List<Matricula> matriculasCurso = new Matricula().matriculaDAO.listarMatriculasPorCurso(cursoDevolver);
			cursoDevolver.matriculas = matriculasCurso;
			
			listaCursos.add(cursoDevolver);
		}

		return listaCursos;	
	}

	public double listarIngresos(TipoCurso tipo, Date fechaInicio, Date fechaFin) throws SQLException {
		Vector listaCursos = GestorBD.getInstancia().select("SELECT * FROM cursoPropio WHERE tipoCurso = '"+ tipo.toString() +"' AND fechaInicio >= '" + dateFormat.format(fechaInicio) + "' AND fechaFin <= '" + dateFormat.format(fechaFin) + "'");
		
		double ingresosTotales = 0;
		
		for (int i=0; i<listaCursos.size(); i++) {
			Vector datosCursoTemp = (Vector) listaCursos.get(i);
			
			String id = (String) datosCursoTemp.get(0);
			double tasaMatricula = (Double) datosCursoTemp.get(5);
			
			CursoPropio cursoTemp = new CursoPropio(id);
			
			List<Matricula> matriculasCurso = new Matricula().matriculaDAO.listarMatriculasPorCurso(cursoTemp);
			
			for (int j = 0; j<matriculasCurso.size();j++) {
				if (matriculasCurso.get(j).isPagado())
					ingresosTotales+=tasaMatricula;
			}
		}		
		
		return ingresosTotales;
	}

	public List<CursoPropio> listarEdicionesCursos(Date fechaInicio, Date fechaFin) throws SQLException {
		Vector listaEdicicionDatos = GestorBD.getInstancia().select("SELECT * FROM cursoPropio WHERE fechaInicio = '" + dateFormat.format(fechaInicio) + "' AND fechaFin <= '" + dateFormat.format(fechaFin) + "'");
		
		List<CursoPropio> listaEdiciones = new ArrayList<>();
		
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
			
			CursoPropio cursoDevolver = new CursoPropio(id, nombre, ECTS, fechainicio, fechafin, tasaMatricula, edicion, estado, tipo, centro, secretario, director);
			
			List<Matricula> matriculasCurso = new Matricula().matriculaDAO.listarMatriculasPorCurso(cursoDevolver);
			cursoDevolver.matriculas = matriculasCurso;
					
			listaEdiciones.add(cursoDevolver);
		}
		
		return listaEdiciones;
	}
}