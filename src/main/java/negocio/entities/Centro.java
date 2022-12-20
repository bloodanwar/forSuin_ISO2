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
	
	@Override
	public String toString(){
		return "Centro:"
		+"\nNombre: "+this.getNombre()
		+"\nLocalizacion: "+this.getLocalizacion()
		+"\nnAtributo: "+this.getAtributo()
		+"\n";	
	}
	
	@Override
	public boolean equals(Object obj){
		if(this == obj) return true;
		if (obj == null) return this != null;
		if (getClass() != obj.getClass()) return false;
		Centro centroObj = (Centro) obj; 
		if (this.getNombre() == null){
			if (centroObj.getNombre()!=null) return false;
		} else {
			if (!(this.getNombre().equals(centroObj.getNombre()))) return false;
		}
		if (this.getLocalizacion() == null){
			if (centroObj.getNombre()!=null) return false;
		} else {
			if (!(this.getNombre().equals(centroObj.getNombre()))) return false;
		}
		if (this.getAtributo()!=centroObj.getAtributo()) return false;
		
		return true;
	}

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) {	this.nombre = nombre; }

	public String getLocalizacion() { return localizacion; }
	public void setLocalizacion(String localizacion) { this.localizacion = localizacion; }

	public int getAtributo() { return attribute; }
	public void setAttribute(int attribute) { this.attribute = attribute; }
}