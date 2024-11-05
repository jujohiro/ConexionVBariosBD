package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static Conexion instancia; // Variable estática para el patrón Singleton
	private Connection conn;

	// Información de conexión
	private final String nombreBd = "usuario_bd";
	private final String usuario = "root";
	private final String password = "";
	private final String url = "jdbc:mysql://localhost/" + nombreBd;

	// Constructor privado para evitar instanciación externa
	private Conexion() {
		try {
			// Cargar el driver de MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establecer la conexión
			conn = DriverManager.getConnection(url, usuario, password);
			if (conn != null) {
				System.out.println("Conexion Exitosa a la BD: " + nombreBd);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Error: No se encontró el driver de la base de datos - " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error: No se pudo conectar a la base de datos - " + e.getMessage());
		}
	}

	// Método estático para obtener la única instancia
	public static Conexion getInstance() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	// Método para obtener la conexión
	public Connection getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(url, usuario, password);
				System.out.println("Conexión restablecida a la BD: " + nombreBd);
			}
		} catch (SQLException e) {
			System.out.println("Error al restablecer la conexión a la base de datos: " + e.getMessage());
		}
		return conn;
	}



	// Método para cerrar la conexión
	public void desconectar() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				System.out.println("Conexión cerrada.");
			}
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión: " + e.getMessage());
		} finally {
			conn = null;
		}
	}
}
