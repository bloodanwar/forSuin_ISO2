package persistencia;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import org.apache.derby.jdbc.EmbeddedDriver;

public class GestorBD {
	
	private static GestorBD instancia = null;
	protected static Connection mBD = null;

	public GestorBD() throws SQLException {
		conectarBD();		
	}
	
	public static GestorBD getInstancia() throws SQLException {
		if (instancia == null) {
			instancia = new GestorBD();
		}
		return instancia;
	}

	public GestorBD conectarBD() throws SQLException {
		Driver derbyEmbeddedDriver = new EmbeddedDriver();
		DriverManager.registerDriver(derbyEmbeddedDriver);
		mBD = DriverManager.getConnection("" + BDConstantes.DRIVER + ":" + BDConstantes.DBNAME + ";create=false",
				BDConstantes.DBUSER, BDConstantes.DBPASS);
		return this;
	}

	public static void desconectarBD() throws SQLException {
		mBD.close();
		instancia = null;
	}

	public Vector<Object> select(String sql) throws SQLException {
		/* Metodo para realizar una busqueda o seleccion de informacion en la base de datos.
		 * Develve un vector de vectores, donde cada uno de los vectores que contiene el vector principal representa los registros que se recuperan de la base de datos. */
		System.out.println(sql);

		Vector<Object> vectoradevolver = new Vector<Object>();
		Statement stmt = mBD.createStatement();
		ResultSet res = stmt.executeQuery(sql);
		int nColumnas = res.getMetaData().getColumnCount();
		
		while (res.next()) {
			Vector<Object> v = new Vector<Object>();
			for (int i=1; i<nColumnas+1; i++)
				v.add(res.getObject(i));
			vectoradevolver.add(v);
		}
		stmt.close();
		desconectarBD();
		return vectoradevolver;
	}

	public int insert(String sql) throws SQLException {
		return executeInsertUpdateDelete(sql);
	}

	public int update(String sql) throws SQLException {
		return executeInsertUpdateDelete(sql);	
	}

	public int delete(String sql) throws SQLException {
		return executeInsertUpdateDelete(sql);
	}
	
	public int executeInsertUpdateDelete(String sql) throws SQLException {
		System.out.println(sql);
		PreparedStatement stmt = mBD.prepareStatement(sql);
    	int res=stmt.executeUpdate();
    	stmt.close();
    	desconectarBD();
		return res;  
	}

	public void operation() {
		// TODO - implement GestorBD.operation
		throw new UnsupportedOperationException();
	}

	// TODO BORRAR FUNCIÓN
	public static void crearBaseDeDatos() throws Exception {
		try {
		    Connection connection = DriverManager.getConnection(BDConstantes.CONNECTION_STRING, BDConstantes.DBUSER, BDConstantes.DBPASS);
		    System.out.println("New derby database created");

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}