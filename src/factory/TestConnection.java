package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
	
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		Connection connection = connectionFactory.recuperarConexion();
		
		System.out.println("PROBANDO LA APERTURA DE LA CONEXION!!!");
		
		
		connection.close();
		
		System.out.println("CONEXION CERRADA!!!");
	}

}
