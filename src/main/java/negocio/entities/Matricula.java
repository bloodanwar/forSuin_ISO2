package negocio.entities;

import java.util.Date;

import persistencia.*;

public class Matricula {

	Estudiante estudiante;
	CursoPropio titulo;
	ModoPago tipoPago;
	public MatriculaDAO matriculaDAO;
	private Date fecha;
	private boolean pagado;
	private int attribute;
	
	public Matricula() {
		matriculaDAO = new MatriculaDAO();
	}
	
	public Date getFecha() { return fecha; }
	public void setFecha(Date fecha) { this.fecha = fecha; }
	
	public boolean isPagado() { return pagado; }
	public void setPagado(boolean pagado) {	this.pagado = pagado; }
	
	public int getAttribute() { return attribute; }
	public void setAttribute(int attribute) { this.attribute = attribute; }	
}

