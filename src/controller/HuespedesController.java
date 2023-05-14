package controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import dao.HuespedesDAO;
import factory.ConnectionFactory;
import model.Huespedes;

public class HuespedesController {
	
	private HuespedesDAO huespedDAO;
	
	public HuespedesController() {
		
		Connection connection = new ConnectionFactory().recuperarConexion();
		this.huespedDAO = new HuespedesDAO(connection);	
	}
	
	public void guardar(Huespedes huespedes) {
		this.huespedDAO.guardar(huespedes);
	}
	
	public void actualizar(String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono, Integer idReserva, Integer id) {
		
		this.huespedDAO.actualizar(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, idReserva, id);
	}
	
	public List<Huespedes> listarHuespedes(){
		return this.huespedDAO.listarHuespedes();
	}
	
	public List<Huespedes> buscarHuespedesId(String id){
		return this.huespedDAO.buscarId(id);
	}
	
	public void eliminar(Integer id) {
		this.huespedDAO.eliminar(id);
	}

}
