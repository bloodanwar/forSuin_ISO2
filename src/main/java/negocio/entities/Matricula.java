package negocio.entities;

import java.util.Date;

import persistencia.*;

public class Matricula {

	public Estudiante estudiante;
	public CursoPropio titulo;
	public ModoPago tipoPago;
	public MatriculaDAO matriculaDAO;
	private Date fecha;
	private boolean pagado;
	private int attribute;
	
	
	public Matricula(){
		matriculaDAO = new MatriculaDAO();
	}
	
	public Matricula(Estudiante estudiante, CursoPropio titulo){
		matriculaDAO = new MatriculaDAO();
		this.estudiante = estudiante;
		this.titulo = titulo;
	}
	
	public Matricula(Date fecha, boolean pagado, int attribute, ModoPago tipoPago, CursoPropio titulo, Estudiante estudiante) {
		matriculaDAO = new MatriculaDAO();
		this.estudiante = estudiante;
		this.titulo = titulo;
		this.tipoPago = tipoPago;
		this.fecha = fecha;
		this.pagado = pagado;
		this.attribute = attribute;
	}

	public Date getFecha() { return fecha; }
	public void setFecha(Date fecha) { this.fecha = fecha; }
	
	public boolean isPagado() { return pagado; }
	public void setPagado(boolean pagado) {	this.pagado = pagado; }
	
	public int getAttribute() { return attribute; }
	public void setAttribute(int attribute) { this.attribute = attribute; }	
	
	public ModoPago getTipoPago(){return tipoPago;}
	public void setTipoPago(ModoPago tipoPago) {this.tipoPago = tipoPago;}
}