package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Huespedes;

public class HuespedesDAO {
	private Connection connection;
	
	
	public HuespedesDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void guardar(Huespedes huesped) {
		
		try {
			
			String sql = "INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, idReserva) VALUES (?,?,?,?,?,?)";
			
			try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				
					pstm.setString(1, huesped.getNombre());
					pstm.setString(2, huesped.getApellido());
					pstm.setDate(3, huesped.getFechaNacimiento());
					pstm.setString(4, huesped.getNacionalidad());
					pstm.setString(5, huesped.getTelefono());
					pstm.setInt(6, huesped.getIdReserva());
					
					pstm.execute();
					
					try(ResultSet rst = pstm.getGeneratedKeys()){
						while(rst.next()) {
							huesped.setId(rst.getInt(1));
						}
					}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void actualizar(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer idReserva, Integer id) {
		
		try(PreparedStatement pstm = connection.prepareStatement
				("UPDATE huespedes SET nombre = ?, apellido = ?, fecha_nacimiento = ?, nacionalidad = ?, telefono = ?, idReserva = ?, id = ?")){
			
			pstm.setString(1, nombre);
			pstm.setString(2, apellido);
			pstm.setDate(3, fechaNacimiento);
			pstm.setString(4, nacionalidad);
			pstm.setString(5, telefono);
			pstm.setInt(6, idReserva);
			pstm.setInt(7, id);
			pstm.execute();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Huespedes> listarHuespedes(){
		
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {
			
			String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, idReserva FROM huespedes";
			
			try(PreparedStatement pstm = connection.prepareStatement(sql)){
				
				pstm.execute();
				
				transformarResultSetEnHuesped(huespedes, pstm);
			}
			return huespedes;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Huespedes> buscarId(String id){
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {
			
			String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, idReserva FROM huespedes WHERE idReserva = ?";
			
			try(PreparedStatement pstm = connection.prepareStatement(sql)){
				
				pstm.setString(1, id);
				pstm.execute();
				
				transformarResultSetEnHuesped(huespedes, pstm);
			}
			return huespedes;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void eliminar(Integer id)  {
		
		try(PreparedStatement pstm = connection.prepareStatement("DELETE FORM huespedes WHERE id = ?")) {
			
				pstm.setInt(1, id);
				pstm.execute();			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private void transformarResultSetEnHuesped(List<Huespedes> huespedes, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Huespedes producto = new Huespedes(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4), rst.getString(5), rst.getString(6), rst.getInt(7));
				huespedes.add(producto);
			}
		}				
	}
		
	
}
