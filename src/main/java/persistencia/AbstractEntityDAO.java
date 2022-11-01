package persistencia;

import java.sql.SQLException;
import java.util.Date;

public abstract class AbstractEntityDAO<E> {
	private String id;
	private Date fechaCreacion;
	private Date fechaActualizacion;

	public abstract E get(String id) throws SQLException;

	public abstract int insert(E entity) throws SQLException;

	public abstract E update(E entity) throws SQLException;

	public abstract int delete(E entity) throws SQLException;

	public void operation() throws SQLException {
		GestorBD.getInstancia().operation();
	}
}