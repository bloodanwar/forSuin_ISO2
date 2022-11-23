package negocio.entities;

import java.util.*;
import persistencia.*;

public class CursoPropio {

	public Collection<Matricula> matriculas;
	public Centro centro;
	public ProfesorUCLM director;
	public ProfesorUCLM secretario;
	public Collection<Materia> materias;
	public EstadoCurso estado;
	public TipoCurso tipo;
	public CursoPropioDAO cursoPropioDao;
	private String id;
	private String nombre;
	private int ECTS;
	private Date fechaInicio;
	private Date fechaFin;
	private double tasaMatricula;
	private int edicion;
	
	
	public CursoPropio() {
		cursoPropioDao = new CursoPropioDAO();
	}
	
	public CursoPropio(String id) {
		cursoPropioDao = new CursoPropioDAO();
		this.id = id;
	}
	
	public CursoPropio(String id, String nombre, int eCTS, Date fechaInicio, Date fechaFin, double tasaMatricula, int edicion, EstadoCurso estado, TipoCurso tipo, Centro centro, ProfesorUCLM secretario, ProfesorUCLM director) {
		cursoPropioDao = new CursoPropioDAO();
		this.id = id;
		this.nombre = nombre;
		this.ECTS = eCTS;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tasaMatricula = tasaMatricula;
		this.edicion = edicion;
		this.estado = estado;
		this.tipo = tipo;
		this.centro = centro;
		this.secretario=secretario;
		this.director=director;
	}
	
	public String toString() {
		/**String strMaterias = "";
		Materia[] arrayMaterias = (Materia[]) materias.toArray();
		for (int i=0; i<materias.size();i++) {
			strMaterias+=arrayMaterias[i].toString();
		}
		**/
		return "Curso:"
				+"\n\tId: "+id
				+"\n\tNombre: "+nombre
				+"\n\tECTS: "+ECTS
				+"\n\tfechaInicio: "+fechaInicio.toString()
				+"\n\tfechaFin: "+fechaFin.toString()
				+"\n\ttasaMatricula: "+tasaMatricula
				+"\n\tEdición: "+edicion
				+"\n\tEstado:"+estado.toString()
				+"\n\tTipo: "+tipo.toString()
				+"\n\tCentro: "+centro.getNombre()
				+"\n\tSecretario: "+secretario.getDni()
				+"\n\tDirector: "+director.getDni()
				//+"\n\tMaterias: "+strMaterias
				;
		
	}
	
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	
	public int getECTS() { return ECTS; }
	public void setECTS(int eCTS) { ECTS = eCTS; }
	
	public Date getFechaInicio() { return fechaInicio; }
	public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }
	
	public Date getFechaFin() { return fechaFin; }
	public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }
	
	public double getTasaMatricula() { return tasaMatricula; }
	public void setTasaMatricula(double tasaMatricula) { this.tasaMatricula = tasaMatricula; }
	
	public int getEdicion() { return edicion; }
	public void setEdicion(int edicion) { this.edicion = edicion; }	
}