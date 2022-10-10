package persistencia;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.derby.jdbc.EmbeddedDriver;

public class GestorBD {
	// Conexion con la base de datos
	protected static Connection mBD = null;

	public GestorBD() throws Exception {
	    crearBaseDeDatos();
		conectarBD();
		desconectarBD();
	}

	public GestorBD conectarBD() throws Exception {
		Driver derbyEmbeddedDriver = new EmbeddedDriver();
		DriverManager.registerDriver(derbyEmbeddedDriver);
		mBD = DriverManager.getConnection("" + BDConstantes.DRIVER + ":" + BDConstantes.DBNAME + ";create=false",
				BDConstantes.DBUSER, BDConstantes.DBPASS);
		return this;
	}

	public void desconectarBD() throws Exception {
		mBD.close();
	}

	/**
	 * 
	 * @param sql
	 */
	public void select(String sql) {
		// TODO - implement GestorBD.select
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param sql
	 */
	public int insert(String sql) {
		// TODO - implement GestorBD.insert
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param sql
	 */
	public int update(String sql) {
		// TODO - implement GestorBD.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param sql
	 */
	public int delete(String sql) {
		// TODO - implement GestorBD.delete
		throw new UnsupportedOperationException();
	}

	public void operation() {
		// TODO - implement GestorBD.operation
		throw new UnsupportedOperationException();
	}

	// TODO BORRAR FUNCI�N
	public void crearBaseDeDatos() throws Exception {
		try {
		    Connection connection = DriverManager.getConnection(BDConstantes.CONNECTION_STRING, BDConstantes.DBUSER, BDConstantes.DBPASS);
		    System.out.println("New derby database created");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
