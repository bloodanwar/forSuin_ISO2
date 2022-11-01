package negocio.entities;

import java.util.*;
import persistencia.*;

public class Centro {
	public Collection<CursoPropio> cursoPropios;
	public Collection<ProfesorUCLM> plantilla;
	public CentroDAO centroDao;
	private String nombre;
	private String localizacion;
	private int attribute;
	
	public Centro(){
		centroDao = new CentroDAO();
	}
	
	public Centro(String nombre){
		centroDao = new CentroDAO();
		this.nombre = nombre;
	}
	
	
	
	public Centro(String nombre, String localizacion, int attribute) {
		centroDao = new CentroDAO();
		this.nombre = nombre;
		this.localizacion = localizacion;
		this.attribute = attribute;
	}

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) {	this.nombre = nombre; }

	public String getLocalizacion() { return localizacion; }
	public void setLocalizacion(String localizacion) { this.localizacion = localizacion; }

	public int getAtributo() { return attribute; }
	public void setAttribute(int attribute) { this.attribute = attribute; }
}