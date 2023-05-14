package factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	public DataSource dataSourse;
	
	
	public ConnectionFactory() {
		
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/alurahotel?useTimezone=true&serverTinezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("Toor");
		
		this.dataSourse = comboPooledDataSource;
	}
	
	public Connection recuperarConexion() {
		
		try {
			return this.dataSourse.getConnection();
			
		}catch(SQLException e) {
			
			throw new RuntimeException(e);
		}
		
	}

}
