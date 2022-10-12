package persistencia;

import java.util.Date;

public abstract class AbstractEntityDAO<E> {

	private String id;
	private Date fechaCreacion;
	private Date fechaActualizacion;

	/**
	 * 
	 * @param id
	 */
	public E get(String id) {
		// TODO - implement AbstractEntityDAO.get
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param entity
	 */
	public int insert(E entity) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param entity
	 */
	public E update(E entity) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param entity
	 */
	public int delete(E entity) {
		throw new UnsupportedOperationException();
	}

	public void operation() {
		throw new UnsupportedOperationException();
	}
}
